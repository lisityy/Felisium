package cvut.fel.pjv.pimenol1.inventorys;

import cvut.fel.pjv.pimenol1.main.Constants;
import cvut.fel.pjv.pimenol1.main.Felisium;
import cvut.fel.pjv.pimenol1.pages.PlayingPage;
import cvut.fel.pjv.pimenol1.utils.Utils;
import cvut.fel.pjv.pimenol1.entity.Player;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import javax.imageio.ImageIO;

/**
 * Represents an item in the game.
 */
public class Item implements Serializable {
    public transient BufferedImage img;
    public String name;
    public boolean collision = false;
    public boolean canTake = true;
    public int worldX, worldY;
    protected int index;
    protected boolean use = false;

    protected Rectangle hitBox = new Rectangle(0, 0, 48, 48);
    protected int defaultHitBoxX = 0;
    protected int defaultHitBoxY = 0;
    protected Utils utils = new Utils();

    /**
     * Constructs an item with the specified parameters.
     *
     * @param nameFile The name of the image file for the item.
     * @param index    The index of the item in array objects.
     * @param worldX   The x-coordinate of the item in the game world.
     * @param worldY   The y-coordinate of the item in the game world.
     */
    public Item(String nameFile, int index, int worldX, int worldY) {
        this.index = index;
        this.worldX = worldX;
        this.worldY = worldY;
        try {
            img = ImageIO.read(getClass().getResourceAsStream("/objects/" + nameFile + ".png"));
            img = utils.scaleImg(img, Constants.TILE_SIZE, Constants.TILE_SIZE);
        } catch (IOException e) {
            Felisium.logger.log(Level.SEVERE, "Error reading image file for item.", e);
        }
    }

    /**
     * Draws the item on the screen.
     *
     * @param g2 The Graphics2D object to draw the item.
     * @param pp The PlayingPage object.
     */
    public void draw(Graphics2D g2, PlayingPage pp) {
        int screenX = worldX - pp.player.xWorld + pp.player.getxScreen();
        int screenY = worldY - pp.player.yWorld + pp.player.getyScreen();

        if (worldX + Constants.TILE_SIZE > pp.player.xWorld - pp.player.getxScreen()
                && worldX - Constants.TILE_SIZE < pp.player.xWorld + pp.player.getxScreen()
                && worldY + Constants.TILE_SIZE > pp.player.yWorld - pp.player.getyScreen()
                && worldY - Constants.TILE_SIZE < pp.player.yWorld + pp.player.getyScreen()) {
            g2.drawImage(img, screenX, screenY, null);
        }
    }

    /**
     * Different actions with items.
     *
     * @param player The player who uses the item.
     * @return True if the item was used successfully, false otherwise.
     */
    public boolean useItem(Player player) {
        return false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public boolean isCollision() {
        return collision;
    }


    public void setCollision(boolean collision) {
        this.collision = collision;
    }


    public Rectangle getHitBox() {
        return hitBox;
    }


    public int getDefaultHitBoxX() {
        return defaultHitBoxX;
    }

    public int getDefaultHitBoxY() {
        return defaultHitBoxY;
    }
}