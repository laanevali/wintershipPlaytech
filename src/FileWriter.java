import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class FileWriter {
    public static void writeFile(
            String filePath,
            List<PlayerData> illegalPlayers,
            List<LegalPlayer> legalPlayers,
            CasinoBalanceData casinoBalanceData) {
        try (PrintWriter m = new PrintWriter(new java.io.FileWriter(filePath))) {
            for (LegalPlayer player: legalPlayers) {
                m.print(player.getPlayerId());
                m.print(" ");
                m.print((int)(player.getBalance()));
                m.print(" ");
                m.print(Math.round((player.getWinRate())*100)/100.0);
                m.println();
            }
            m.println();
            for (PlayerData operation: illegalPlayers) {
                m.print(operation.getId());
                m.print(" ");
                m.print(operation.getTransaction());
                m.print(" ");
                if (!operation.getMatchId().isEmpty()) {
                    m.print(operation.getMatchId());
                } else {
                    m.print("null");
                }
                m.print(" ");
                m.print((int)(operation.getCoinAmount()));
                m.print(" ");
                if (!operation.getBet().isEmpty()) {
                    m.print(operation.getBet());
                } else {
                    m.print("null");
                }
                m.println();
            }
            m.println();
            m.print((int)(casinoBalanceData.getCasinoBalance()));

            m.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
