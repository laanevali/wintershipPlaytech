import model.CasinoBalanceData;
import model.LegalPlayer;
import model.MatchData;
import model.PlayerData;

import java.util.List;
import java.util.Set;

public class PlayerProcessor {
    public static void processPlayers(
            List<PlayerData> playerDataList,
            List<MatchData> matchDataList,
            Set<String> playerIdData,
            List<PlayerData> illegalPlayers,
            List<LegalPlayer> legalPlayers,
            CasinoBalanceData casinoBalanceData
    ) {
        for (String id : playerIdData){
            try {
                List<PlayerData> playerOperations = playerDataList.stream()
                        .filter(p -> p.getId().equals(id))
                        .toList();

                double casinoBalance = 0;
                double coinBalance = 0;
                double playerOutcome = 0;
                double allGames = 0;
                double wonGames = 0;

                for (PlayerData operation : playerOperations) {
                    switch (operation.getTransaction()) {
                        case "DEPOSIT" -> coinBalance += operation.getCoinAmount();
                        case "WITHDRAW" -> {
                            coinBalance -= operation.getCoinAmount();
                            if (coinBalance < 0) {
                                illegalPlayers.add(operation);
                                throw new RuntimeException("Withdraw amount exceeds player's available coin balance");
                            }
                        }
                        case "BET" -> {
                            if (operation.getCoinAmount() > coinBalance) {
                                illegalPlayers.add(operation);
                                throw new RuntimeException("Bet amount exceeds player's available coin balance");
                            }
                            MatchData matchData;
                            try {
                                matchData = matchDataList.stream()
                                        .filter(m -> m.getId().equals(operation.getMatchId()))
                                        .findFirst()
                                        .orElseThrow();
                            } catch (Exception e) {
                                illegalPlayers.add(operation);
                                throw new RuntimeException("Corresponding match id not found");
                            }
                            allGames++;
                            if (matchData.getResult().equals(operation.getBet())) {
                                wonGames++;
                                if (matchData.getResult().equals("A")) {
                                    playerOutcome += operation.getCoinAmount() * matchData.getRateA();
                                } else if (matchData.getResult().equals("B")) {
                                    playerOutcome += operation.getCoinAmount() * matchData.getRateB();
                                } else {
                                    throw new RuntimeException("Player's bet wasn't found");
                                }
                            } else if (!matchData.getResult().equals("DRAW") &&
                                    !matchData.getResult().equals(operation.getBet())
                            ) {
                                playerOutcome -= operation.getCoinAmount();
                            }
                        }
                        default ->
                                throw new IllegalStateException("Unexpected operation: " + operation.getTransaction());
                    }
                }
                coinBalance += playerOutcome;
                casinoBalance -= playerOutcome;

                casinoBalanceData.setCasinoBalance(casinoBalance);
                legalPlayers.add(new LegalPlayer(id, coinBalance,wonGames/allGames));
            } catch (RuntimeException e) {
                System.out.println("Player error message: " + e.getMessage());
            }
        }
    }
}
