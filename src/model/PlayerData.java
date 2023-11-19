package model;

public class PlayerData {
    private String id;
    private String transaction;
    private String matchId;
    private long coinAmount;
    private String bet;

    public PlayerData(String id, String transaction, String matchId, Long coinAmount, String bet) {
        this.id = id;
        this.transaction = transaction;
        this.matchId = matchId;
        this.coinAmount = coinAmount;
        this.bet = bet;
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

    public double getCoinAmount() {
        return coinAmount;
    }

    public String getBet() {
        return bet;
    }
}
