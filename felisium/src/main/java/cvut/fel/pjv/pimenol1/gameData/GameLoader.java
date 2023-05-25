package cvut.fel.pjv.pimenol1.gameData;

import com.fasterxml.jackson.databind.ObjectMapper;
import cvut.fel.pjv.pimenol1.aliens.Alien;
import cvut.fel.pjv.pimenol1.entity.*;
import cvut.fel.pjv.pimenol1.inventorys.*;
import cvut.fel.pjv.pimenol1.main.Felisium;
import cvut.fel.pjv.pimenol1.pages.PlayingPage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;

public class GameLoader {

    /**
     * Loads the game data from a file.
     *
     * @param filePath the path of the file to load the game data from
     * @return the loaded GameData object, or null if loading failed
     */
    public static GameData loadGame(String filePath) {
        try {
            Felisium.logger.log(Level.INFO, "Loading game data from file: {0}", filePath);
            String jsonData = Files.readString(Paths.get(filePath));
            ObjectMapper objectMapper = new ObjectMapper();
            GameData gameData = objectMapper.readValue(jsonData, GameData.class);
            Felisium.logger.log(Level.INFO, "Game data loaded successfully");
            return gameData;
        } catch (IOException e) {
            Felisium.logger.log(Level.SEVERE, "Failed to load game data from file: {0}", filePath);
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Adds the loaded game data to the PlayingPage object.
     *
     * @param gd the loaded GameData object
     * @param pp the PlayingPage object to add the game data to
     */
    public static void addGameData(GameData gd, PlayingPage pp) {
        if (gd != null) {
            ArrayList<String> tempString;

            // Set player position and life
            pp.player.xWorld = gd.getPlayerxWorld();
            pp.player.yWorld = gd.getPlayeryWorld();
            pp.player.setLife(gd.getPlayerLife());

            // Add player bag items
            tempString = gd.getBagsItemsNames();
            pp.player.bag.items.clear();
            for (int i = 0; i < tempString.size(); i++) {
                switch (tempString.get(i)) {
                    case "box" -> pp.player.bag.items.add(new Box(i, 0, 0));
                    case "fish" -> pp.player.bag.items.add(new Fish(i, 0, 0));
                    case "key" -> pp.player.bag.items.add(new Key(i, 0, 0));
                    case "socks" -> pp.player.bag.items.add(new Socks(i, 0, 0));
                    case "valeriana" -> pp.player.bag.items.add(new Valeriana(i, 0, 0));
                    case "wing" -> pp.player.bag.items.add(new Wing(i, 0, 0));
                }
            }
            tempString.clear();

            // Add aliens
            Entity[] aliens = new Entity[20];
            for (int i = 0; i < gd.getAliensX().size(); i++) {
                aliens[i] = new Alien("enemy", "enemyCalm", gd.getAliensX().get(i), gd.getAliensY().get(i), pp);
            }
            pp.setAliens(aliens);

            // Add objects
            tempString = gd.getItemsName();
            pp.obj = new Item[20];
            for (int i = 0; i < tempString.size(); i++) {
                switch (tempString.get(i)) {
                    case "box" -> pp.obj[i] = new Box(i, gd.getItemsX().get(i), gd.getItemsY().get(i));
                    case "fish" -> pp.obj[i] = new Fish(i, gd.getItemsX().get(i), gd.getItemsY().get(i));
                    case "key" -> pp.obj[i] = new Key(i, gd.getItemsX().get(i), gd.getItemsY().get(i));
                    case "socks" -> pp.obj[i] = new Socks(i, gd.getItemsX().get(i), gd.getItemsY().get(i));
                    case "valeriana" -> pp.obj[i] = new Valeriana(i, gd.getItemsX().get(i), gd.getItemsY().get(i));
                    case "wing" -> pp.obj[i] = new Wing(i, gd.getItemsX().get(i), gd.getItemsY().get(i));
                    case "door" -> pp.obj[i] = new Door(i, gd.getItemsX().get(i), gd.getItemsY().get(i));
                }
            }
            tempString.clear();

            // Add NPCs (cats)
            tempString = gd.getCatsName();
            pp.npc = new Entity[20];
            for (int i = 0; i < tempString.size(); i++) {
                switch (tempString.get(i)) {
                    case "dranik" ->
                            pp.npc[i] = new NPC_dranik(pp, "NPC_dranik", "dranik", gd.getCatX().get(i), gd.getCatY().get(i));
                    case "queenCat" ->
                            pp.npc[i] = new NPC_queenCat(pp, "queenCat", "queenCat", gd.getCatX().get(i), gd.getCatY().get(i));
                    case "catan" ->
                            pp.npc[i] = new NPC_catan(pp, "NPC_catan", "catan", gd.getCatX().get(i), gd.getCatY().get(i));
                }
            }
            tempString.clear();
        }
    }
}
