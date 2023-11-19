import fileOperation.FileReader;
import fileOperation.FileWriter;
import model.CasinoBalanceData;
import model.LegalPlayer;
import model.MatchData;
import model.PlayerData;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        List<PlayerData> playerDataList = new ArrayList<>();
        List<MatchData> matchDataList = new ArrayList<>();
        Set<String> playerIdData = new TreeSet<>();
        List<PlayerData> illegalPlayers = new ArrayList<>();
        List<LegalPlayer> legalPlayers = new ArrayList<>();
        CasinoBalanceData casinoBalanceData = new CasinoBalanceData();

        try {
            fileOperation.FileReader.readPlayerData("./src/player_data.txt", playerDataList, playerIdData);
            FileReader.readMatchData("./src/match_data.txt", matchDataList);
            PlayerProcessor.processPlayers(playerDataList, matchDataList, playerIdData, illegalPlayers, legalPlayers, casinoBalanceData);
            FileWriter.writeFile("./src/results.txt", illegalPlayers, legalPlayers, casinoBalanceData);
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}