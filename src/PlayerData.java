public class PlayerData {
    private String id;
    private String transaction;
    private String matchId;
    private int coinAmount;
    private String bet;

    public PlayerData(String id, String transaction, String matchId, double coinAmount, String bet) {
    }

    public String getId() {
        return id;
    }

    public String getTransaction() {
        return transaction;
    }

    public String getMatchId() {
        return matchId;
    }

    public int getCoinAmount() {
        return coinAmount;
    }

    public String getBet() {
        return bet;
    }
}
