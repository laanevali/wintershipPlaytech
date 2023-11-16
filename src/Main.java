import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) throws IOException {
        List<PlayerData> playerDataList = new ArrayList<>();
        List<MatchData> matchDataList = new ArrayList<>();
        Set<String> playerIdData = new TreeSet<>();

        BufferedReader pData = new BufferedReader(new FileReader("./src/player_data.txt"));
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
            //System.out.println(pDataLine);
        }
        pData.close();

        BufferedReader mData = new BufferedReader(new FileReader("./src/match_data.txt"));
        String mDataLine = mData.readLine();
        while (mDataLine != null){
            String[] line = mDataLine.split(",");
            String id = line[0];
            double rateA = Double.parseDouble(line[1]);
            double rateB = Double.parseDouble(line[2]);
            String result = line[3];
            MatchData matchData = new MatchData(id, rateA, rateB, result);
            matchDataList.add(matchData);
            mDataLine = mData.readLine();
            //System.out.println(mDataLine);
        }
        mData.close();

        
    }
}