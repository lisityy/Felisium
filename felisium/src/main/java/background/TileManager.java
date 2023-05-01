package background;

import Entity.Player;
import main_pjv.GamePannel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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

        loadMap("/tiles/world01.txt");
        getTileImg();
    }

    public void getTileImg() {

        try {
            this.tiles[0] = new Tile();
            this.tiles[0].img = ImageIO.read(getClass().getResourceAsStream("/tiles/grassMy.png"));

            this.tiles[1] = new Tile();
            this.tiles[1].img = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
//            this.tiles[1].collision=true;

            this.tiles[2] = new Tile();
            this.tiles[2].img = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
            this.tiles[2].collision=true;

            this.tiles[3] = new Tile();
            this.tiles[3].img = ImageIO.read(getClass().getResourceAsStream("/tiles/01.png"));

            this.tiles[4] = new Tile();
            this.tiles[4].img = ImageIO.read(getClass().getResourceAsStream("/tiles/little_tree.png"));
            this.tiles[4].collision=true;

            this.tiles[5] = new Tile();
            this.tiles[5].img = ImageIO.read(getClass().getResourceAsStream("/tiles/sand.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String path) {
        mapData = new int[gp.getMaxWorldCol()][gp.getMaxWorldRow()];

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(path)));
            for (int r = 0; r < gp.getMaxWorldRow(); r++) {
                String line = br.readLine();
                String[] nums = line.split(" ");
                for (int c = 0; c < gp.getMaxWorldCol(); c++) {
                    mapData[r][c] = Integer.parseInt(nums[c]);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {

        for (int i = 0; i < gp.getMaxWorldCol(); i++) {
            for (int j = 0; j < gp.getMaxWorldRow(); j++) {
                int tileNum = mapData[i][j];
                int lworldX = j * gp.getTileSize();
                int lworldY = i * gp.getTileSize();
                if (lworldX + gp.getTileSize() > gp.player.xWorld - gp.player.getxScreen()
                        && lworldX - gp.getTileSize() < gp.player.xWorld + gp.player.getxScreen()
                        && lworldY + gp.getTileSize() > gp.player.yWorld - gp.player.getyScreen()
                        && lworldY - gp.getTileSize() < gp.player.yWorld + gp.player.getyScreen()) {

                    int lscreenX = lworldX - gp.player.xWorld + gp.player.getxScreen();
                    int lscreenY = lworldY - gp.player.yWorld + gp.player.getyScreen();
                    g2.drawImage(tiles[tileNum].img, lscreenX, lscreenY, gp.getTileSize(), gp.getTileSize(), null);
                }

            }
        }
    }
}
