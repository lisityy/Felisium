package Inventorys;

import Entity.Player;
import main_pjv.GamePannel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Objects {
    public BufferedImage img;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;

    protected Rectangle hitBox = new Rectangle(0, 0, 48, 48);
    protected int defultHitBoxX = 0;
    protected int defultHitBoxY = 0;

    public void draw(Graphics2D g2, GamePannel gp) {
        int screenX = worldX - gp.player.xWorld + gp.player.xScreen;
        int screenY = worldY - gp.player.yWorld + gp.player.yScreen;

        if (worldX + gp.getTileSize() > gp.player.xWorld - gp.player.xScreen
                && worldX - gp.getTileSize() < gp.player.xWorld + gp.player.xScreen
                && worldY + gp.getTileSize() > gp.player.yWorld - gp.player.yScreen
                && worldY - gp.getTileSize() < gp.player.yWorld + gp.player.yScreen) {
            g2.drawImage(img, screenX, screenY, gp.getTileSize(), gp.getTileSize(), null);
        }
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
