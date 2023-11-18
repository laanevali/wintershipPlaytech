public class LegalPlayer {
    private String playerId;
    private double balance;
    private double winRate;

    public LegalPlayer(String playerId, double balance, double winRate) {
        this.playerId = playerId;
        this.balance = balance;
        this.winRate = winRate;
    }

    public String getPlayerId() { return playerId; }

    public double getBalance() { return balance; }

    public double getWinRate() {
        return winRate;
    }
}
