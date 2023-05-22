package cvut.fel.pjv.pimenol1.inventorys;

import cvut.fel.pjv.pimenol1.main.Constants;
import cvut.fel.pjv.pimenol1.main.PlayingPage;
import cvut.fel.pjv.pimenol1.utils.Utils;
import cvut.fel.pjv.pimenol1.entity.Player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;

public class Item implements Serializable {
    public transient BufferedImage img;
    public String name;
    public boolean collision = false;
    public boolean canTake = true;
    public int worldX, worldY;
    protected int index;
    protected boolean use = false;

    protected Rectangle hitBox = new Rectangle(0, 0, 48, 48);
    protected int defultHitBoxX = 0;
    protected int defultHitBoxY = 0;
    protected Utils utils = new Utils();

    public Item(String nameFile, int index, int worldX, int worldY) {
        this.index = index;
        this.worldX = worldX;
        this.worldY = worldY;
        try {
            img = ImageIO.read(getClass().getResourceAsStream("/objects/" + nameFile + ".png"));
            img = utils.scaleImg(img, Constants.TILE_SIZE, Constants.TILE_SIZE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2, PlayingPage pp) {
        int screenX = worldX - pp.player.xWorld + pp.player.xScreen;
        int screenY = worldY - pp.player.yWorld + pp.player.yScreen;

        if (worldX + Constants.TILE_SIZE > pp.player.xWorld - pp.player.xScreen
                && worldX - Constants.TILE_SIZE < pp.player.xWorld + pp.player.xScreen
                && worldY + Constants.TILE_SIZE > pp.player.yWorld - pp.player.yScreen
                && worldY - Constants.TILE_SIZE < pp.player.yWorld + pp.player.yScreen) {
            g2.drawImage(img, screenX, screenY, null);
        }
    }

    public boolean useItem(Player player) {
        return false;
    }

    public void pickUp(Player player, int inx) {
        return;
    }

    public BufferedImage getImg() {
        return img;
    }

    public void setImg(BufferedImage img) {
        this.img = img;
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

    public int getWorldX() {
        return worldX;
    }

    public void setWorldX(int worldX) {
        this.worldX = worldX;
    }

    public int getWorldY() {
        return worldY;
    }

    public void setWorldY(int worldY) {
        this.worldY = worldY;
    }

    public Rectangle getHitBox() {
        return hitBox;
    }

    public void setHitBox(Rectangle hitBox) {
        this.hitBox = hitBox;
    }

    public int getDefultHitBoxX() {
        return defultHitBoxX;
    }

    public void setDefultHitBoxX(int defultHitBoxX) {
        this.defultHitBoxX = defultHitBoxX;
    }

    public int getDefultHitBoxY() {
        return defultHitBoxY;
    }

    public void setDefultHitBoxY(int defultHitBoxY) {
        this.defultHitBoxY = defultHitBoxY;
    }
}
