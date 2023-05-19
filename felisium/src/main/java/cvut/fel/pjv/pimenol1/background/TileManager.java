package cvut.fel.pjv.pimenol1.background;

import cvut.fel.pjv.pimenol1.entity.Player;
import cvut.fel.pjv.pimenol1.main.Constants;
import cvut.fel.pjv.pimenol1.utils.Utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {

    private final Tile[] tiles;
    private int[][] mapData;


    public Tile[] getTiles() {
        return tiles;
    }

    public int[][] getMapData() {
        return mapData;
    }

    public TileManager() {

        this.tiles = new Tile[10];

        mapData = new int[Constants.MAX_WORLD_COL][Constants.MAX_WORLD_ROW];

        getTileImg();
        loadMap("/maps/world01.txt");
    }



    public void setup(int index, String imageName, boolean collision) {
        Utils utils = new Utils();
        try {
            tiles[index] = new Tile();
            tiles[index].setImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/" + imageName + ".png"))));
            tiles[index].setImage(utils.scaleImg(tiles[index].getImage(), Constants.TILE_SIZE, Constants.TILE_SIZE));
            tiles[index].setCollision(collision);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void getTileImg() {
        setup(0, "pGrass", false);
        setup(1, "pinkTile", true);
        setup(2, "pWater", true);
        setup(3, "pGround", false);
        setup(4, "pTree", true);
        setup(5, "pGround", false);
        setup(6,"littleTree", true);
    }


    public void loadMap(String filePath) {
        //ok
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(is))); // reading the content of the text file

            int col = 0;
            int row = 0;

            while (col < Constants.MAX_WORLD_COL && row < Constants.MAX_WORLD_ROW) {
                String line = br.readLine();

                while (col < Constants.MAX_WORLD_COL) {
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);

                    mapData[col][row] = num;
                    col++;
                }

                if (col == Constants.MAX_WORLD_COL) {
                    col = 0;
                    row++;
                }
            }
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2, Player player) {
        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < Constants.MAX_WORLD_ROW && worldRow < Constants.MAX_WORLD_ROW ) {
            int tileNum = mapData[worldCol][worldRow];
            int worldX = worldCol * Constants.TILE_SIZE ;
            int worldY = worldRow * Constants.TILE_SIZE;
            int screenX = worldX - player.xWorld + player.xScreen;
            int screenY = worldY - player.yWorld + player.yScreen;

            if (worldX + Constants.TILE_SIZE > player.xWorld - player.xScreen
                    && worldX - Constants.TILE_SIZE < player.xWorld + player.xScreen
                    && worldY + Constants.TILE_SIZE > player.yWorld - player.yScreen
                    && worldY - Constants.TILE_SIZE < player.yWorld + player.yScreen) {
                g2.drawImage(tiles[tileNum].getImage(), screenX, screenY, null);
            }

            worldCol++;

            if (worldCol == Constants.MAX_WORLD_COL) {
                worldCol = 0;
                worldRow++;
            }

        }
    }
}
