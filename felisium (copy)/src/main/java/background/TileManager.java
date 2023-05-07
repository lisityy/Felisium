package background;

import main_pjv.GamePannel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Objects;

public class TileManager {

    private final Tile[] tiles;
    private final GamePannel gp;
    private int[][] mapData;

    public Tile[] getTiles() {
        return tiles;
    }

    public GamePannel getGp() {
        return gp;
    }

    public int[][] getMapData() {
        return mapData;
    }

    public TileManager(GamePannel gp) {
        this.gp = gp;
        this.tiles = new Tile[10];

        mapData = new int[gp.getMaxWorldCol()][gp.getMaxWorldRow()];

        getTileImg();
        loadMap("/tiles/world01.txt");
    }

    public void setup(int index, String imageName, boolean collision) {
        try {
            tiles[index] = new Tile();
            tiles[index].setImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/" + imageName + ".png"))));
            tiles[index].setCollision(collision);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void getTileImg() {
        setup(0, "grassMy", false);
        setup(1, "wall", true);
        setup(2, "water", true);
        setup(3, "01", false);
        setup(4, "little_tree", true);
        setup(5, "sand", false);
    }


    public void loadMap(String filePath) {
        //ok
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(is))); // reading the content of the text file

            int col = 0;
            int row = 0;

            while (col < gp.getMaxWorldCol() && row < gp.getMaxWorldRow()) {
                String line = br.readLine();

                while (col < gp.getMaxWorldCol()) {
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);

                    mapData[col][row] = num;
                    col++;
                }

                if (col == gp.getMaxWorldCol()) {
                    col = 0;
                    row++;
                }
            }
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gp.getMaxWorldRow() && worldRow < gp.getMaxWorldRow()) {
            int tileNum = mapData[worldCol][worldRow];
            int worldX = worldCol * gp.getTileSize();
            int worldY = worldRow * gp.getTileSize();
            int screenX = worldX - gp.player.xWorld + gp.player.xScreen;
            int screenY = worldY - gp.player.yWorld + gp.player.yScreen;

            if (worldX + gp.getTileSize() > gp.player.xWorld - gp.player.xScreen
                    && worldX - gp.getTileSize() < gp.player.xWorld + gp.player.xScreen
                    && worldY + gp.getTileSize() > gp.player.yWorld - gp.player.yScreen
                    && worldY - gp.getTileSize() < gp.player.yWorld + gp.player.yScreen) {
                g2.drawImage(tiles[tileNum].getImage(), screenX, screenY, gp.getTileSize(), gp.getTileSize(), null);
            }

            worldCol++;

            if (worldCol == gp.getMaxWorldCol()) {
                worldCol = 0;
                worldRow++;
            }

        }
    }
}
