import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public class FileReader {
    public static void readPlayerData(
            String filePath,
            List<PlayerData> playerDataList,
            Set<String> playerIdData
    ) throws IOException {
        try (BufferedReader pData = new BufferedReader(new java.io.FileReader(filePath))) {
            String pDataLine = pData.readLine();
            while (pDataLine != null) {
                String[] line = pDataLine.split(",");
                String id = line[0];
                String transaction = line[1];
                String matchId = line[2];
                double coinAmount = Double.parseDouble(line[3]);
                String bet = line[2].isEmpty() ? "" : line[4];
                PlayerData playerData = new PlayerData(id, transaction, matchId, coinAmount, bet);
                playerDataList.add(playerData);
                pDataLine = pData.readLine();
                playerIdData.add(id);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void readMatchData (
            String filePath,
            List<MatchData> matchDataList
    ) {
        try (BufferedReader mData = new BufferedReader(new java.io.FileReader(filePath))) {
            String mDataLine = mData.readLine();
            while (mDataLine != null) {
                String[] line = mDataLine.split(",");
                String id = line[0];
                double rateA = Double.parseDouble(line[1]);
                double rateB = Double.parseDouble(line[2]);
                String result = line[3];
                MatchData matchData = new MatchData(id, rateA, rateB, result);
                matchDataList.add(matchData);
                mDataLine = mData.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
