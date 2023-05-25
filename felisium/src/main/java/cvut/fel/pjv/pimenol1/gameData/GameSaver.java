package cvut.fel.pjv.pimenol1.gameData;

import com.fasterxml.jackson.databind.ObjectMapper;
import cvut.fel.pjv.pimenol1.entity.Entity;
import cvut.fel.pjv.pimenol1.inventorys.Item;
import cvut.fel.pjv.pimenol1.main.Felisium;
import cvut.fel.pjv.pimenol1.pages.PlayingPage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;

public class GameSaver {

    /**
     * Saves the game data to a file.
     *
     * @param gameData the game data to be saved
     * @param filePath the path of the file to save the data
     */
    public static void saveGame(GameData gameData, String filePath) {
        try {
            Felisium.logger.log(Level.INFO, "Saving game data to file: {0}", filePath);
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonData = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(gameData);
            Files.writeString(Paths.get(filePath), jsonData);
        } catch (IOException e) {
            Felisium.logger.log(Level.SEVERE, "Failed to save game data to file: {0}", filePath);
            e.printStackTrace();
        }
    }

    /**
     * Adds the current game information to a GameData object.
     *
     * @param pp the PlayingPage object containing the game information
     * @return the GameData object with the updated game information
     */
    public static GameData addInformation(PlayingPage pp) {
        GameData gameData = new GameData();
        ArrayList<String> tempString = new ArrayList<>();
        ArrayList<Integer> tempX = new ArrayList<>();
        ArrayList<Integer> tempY = new ArrayList<>();
        ArrayList<Integer> tempLive = new ArrayList<>();

        // Adding player bag items
        for (Item item : pp.player.bag.items) {
            tempString.add(item.name);
        }
        gameData.setBagsItemsNames(new ArrayList<>(tempString));
        tempString.clear();

        // Adding aliens information
        for (Entity alien : pp.getAliens()) {
            if (alien != null) {
                tempX.add(alien.xWorld);
                tempY.add(alien.yWorld);
                tempLive.add(alien.getLife());
            }
        }
        gameData.setAliensLive(new ArrayList<>(tempLive));
        gameData.setAliensX(new ArrayList<>(tempX));
        gameData.setAliensY(new ArrayList<>(tempY));
        tempX.clear();
        tempY.clear();
        tempLive.clear();

        // Adding objects information
        for (Item item : pp.obj) {
            if (item != null) {
                tempString.add(item.name);
                tempX.add(item.worldX);
                tempY.add(item.worldY);
            }
        }
        gameData.setItemsName(new ArrayList<>(tempString));
        gameData.setItemsX(new ArrayList<>(tempX));
        gameData.setItemsY(new ArrayList<>(tempY));
        tempX.clear();
        tempY.clear();
        tempString.clear();

        // Adding NPCs information
        for (Entity cat : pp.npc) {
            if (cat != null) {
                tempX.add(cat.xWorld);
                tempY.add(cat.yWorld);
                tempString.add(cat.name);
            }
        }
        gameData.setCatsName(new ArrayList<>(tempString));
        gameData.setCatY(new ArrayList<>(tempY));
        gameData.setCatX(new ArrayList<>(tempX));
        tempX.clear();
        tempY.clear();
        tempString.clear();

        // Adding player information
        gameData.setPlayerLife(pp.player.getLife());
        gameData.setPlayerxWorld(pp.player.xWorld);
        gameData.setPlayeryWorld(pp.player.yWorld);

        return gameData;
    }


}

