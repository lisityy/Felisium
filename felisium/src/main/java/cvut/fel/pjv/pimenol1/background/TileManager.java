package cvut.fel.pjv.pimenol1.background;

import cvut.fel.pjv.pimenol1.entity.Player;
import cvut.fel.pjv.pimenol1.main.Constants;
import cvut.fel.pjv.pimenol1.main.Felisium;
import cvut.fel.pjv.pimenol1.utils.Utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.logging.Level;

/**
 * The TileManager class manages the tiles used in the game's map.
 */
public class TileManager {

    private final Tile[] tiles;
    private int[][] mapData;


    /**
     * Constructs a new TileManager object.
     * Initializes the tiles array and loads the map data.
     */
    public TileManager() {

        this.tiles = new Tile[50];

        mapData = new int[Constants.MAX_WORLD_COL][Constants.MAX_WORLD_ROW];

        getTileImg();
        loadMap("/maps/map.txt");
    }

    /**
     * Sets up a tile with the given index, image name, and collision property.
     *
     * @param index     The index of the tile.
     * @param imageName The name of the image file for the tile.
     * @param collision The collision property of the tile.
     */
    public void setup(int index, String imageName, boolean collision) {
        Utils utils = new Utils();
        try {
            tiles[index] = new Tile();
            tiles[index].setImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/" + imageName + ".png"))));
            tiles[index].setImage(utils.scaleImg(tiles[index].getImage(), Constants.TILE_SIZE, Constants.TILE_SIZE));
            tiles[index].setCollision(collision);
        } catch (IOException e) {
            Felisium.logger.log(Level.SEVERE, "Error setting up tile: " + e.getMessage(), e);
        }
    }

    /**
     * Loads the tile images and sets up the tiles array.
     */
    public void getTileImg() {
        for (int i = 1; i < 38; i++) {
            if (i < 10) {
                setup(i, "00" + i, false);
            } else if (i == 17) {
                setup(16, "0" + i, true);
            } else if (i == 16) {
                setup(17, "0" + i, false);
            } else if (i > 17) {
                setup(i, "0" + i, true);
            } else {
                setup(i, "0" + i, false);
            }
        }
    }

    /**
     * Loads the map data from the given file path.
     *
     * @param filePath The file path of the map data file.
     */
    public void loadMap(String filePath) {
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
            Felisium.logger.log(Level.SEVERE, "Error loading map: " + e.getMessage(), e);
        }
    }

    /**
     * Draws the tiles on the screen.
     *
     * @param g2     The Graphics2D object used for drawing.
     * @param player The player object.
     */
    public void draw(Graphics2D g2, Player player) {
        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < Constants.MAX_WORLD_ROW && worldRow < Constants.MAX_WORLD_ROW) {
            int tileNum = mapData[worldCol][worldRow];
            int worldX = worldCol * Constants.TILE_SIZE;
            int worldY = worldRow * Constants.TILE_SIZE;
            int screenX = worldX - player.xWorld + player.getxScreen();
            int screenY = worldY - player.yWorld + player.getyScreen();

            if (worldX + Constants.TILE_SIZE > player.xWorld - player.getxScreen()
                    && worldX - Constants.TILE_SIZE < player.xWorld + player.getxScreen()
                    && worldY + Constants.TILE_SIZE > player.yWorld - player.getyScreen()
                    && worldY - Constants.TILE_SIZE < player.yWorld + player.getyScreen()) {
                g2.drawImage(tiles[tileNum].getImage(), screenX, screenY, null);
            }

            worldCol++;

            if (worldCol == Constants.MAX_WORLD_COL) {
                worldCol = 0;
                worldRow++;
            }

        }
    }

    public Tile[] getTiles() {
        return tiles;
    }

    public int[][] getMapData() {
        return mapData;
    }
}
