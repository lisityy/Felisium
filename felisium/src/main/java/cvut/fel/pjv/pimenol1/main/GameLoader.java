package cvut.fel.pjv.pimenol1.main;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class GameLoader {

    public static GameData loadGame(String filePath) {

        try (FileInputStream fileIn = new FileInputStream(filePath);
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {

            GameData gameData = (GameData) objectIn.readObject();
            System.out.println("Game loaded successfully.");
            return gameData;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}

