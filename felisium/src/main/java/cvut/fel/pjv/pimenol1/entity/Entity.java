package cvut.fel.pjv.pimenol1.entity;

import cvut.fel.pjv.pimenol1.main.Constants;
import cvut.fel.pjv.pimenol1.main.PlayingPage;
import cvut.fel.pjv.pimenol1.utils.Utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Entity {
    public int xWorld, yWorld;
    public int xScreen, yScreen;
    public int speed;
    public String name;
    BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;
    protected int spriteCounter = 0, waitCounter = 0;
    protected int spriteNum = 1, waitNum = 1;
    protected int maxLife, life;


    public boolean collitionOn = false;
    public Rectangle hitBox = new Rectangle(0, 0, Constants.TILE_SIZE, Constants.TILE_SIZE);
    protected int defultHitBoxX, defultHitBoxY;

    public Entity(String path, String name) {
        this.name=name;
        getEntityImg(path);
    }

    public void update() {
        spriteCounter++;
        if (spriteCounter > 12) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }

    public void draw(Graphics2D g2, PlayingPage pp) {
        BufferedImage img = switch (direction) {
            case "up" -> spriteNum == 1 ? up1 : up2;
            case "down" -> spriteNum == 1 ? down1 : down2;
            case "left" -> spriteNum == 1 ? left1 : left2;
            case "right" -> spriteNum == 1 ? right1 : right2;
            default -> null;
        };

        int screenX = xWorld - pp.player.xWorld + pp.player.xScreen;
        int screenY = yWorld - pp.player.yWorld + pp.player.yScreen;

        if (xWorld + Constants.TILE_SIZE > pp.player.xWorld - pp.player.xScreen
                && xWorld - Constants.TILE_SIZE < pp.player.xWorld + pp.player.xScreen
                && yWorld + Constants.TILE_SIZE > pp.player.yWorld - pp.player.yScreen
                && yWorld - Constants.TILE_SIZE < pp.player.yWorld + pp.player.yScreen) {
            g2.drawImage(img, screenX, screenY, null);
        }
    }


    public void getEntityImg(String path) {
        up1 = setup(path, name+"_up_1");
        up2 = setup(path, name+"_up_2");
        down1 = setup(path, name+"_down_1");
        down2 = setup(path, name+"_down_2");
        right1 = setup(path, name+"_right_1");
        right2 = setup(path, name+"_right_2");
        left1 = setup(path, name+"_left_1");
        left2 = setup(path, name+"_left_2");

    }

    public BufferedImage setup(String path, String name) {
        BufferedImage img = null;
        img = Utils.load_image(path, name);
        img = Utils.scaleImg(img, Constants.TILE_SIZE, Constants.TILE_SIZE);
        return img;
    }
    public int getDefultHitBoxX() {
        return defultHitBoxX;
    }

    public int getDefultHitBoxY() {
        return defultHitBoxY;
    }
}
