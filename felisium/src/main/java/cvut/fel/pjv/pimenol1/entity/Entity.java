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
    protected BufferedImage left, right, up, down;
    protected BufferedImage[] left_a, right_a, up_a, down_a;

    protected int sizeSubImg = 30;
    public String direction;
    protected int spriteCounter = 0, waitCounter = 0;
    protected int spriteNum = 0, waitNum = 1, maxSprite = 1;
    protected int timeUpdate = 12;
    protected int maxLife, life;


    public boolean collitionOn = false;
    public Rectangle hitBox = new Rectangle(0, 0, Constants.TILE_SIZE, Constants.TILE_SIZE);
    protected int defultHitBoxX, defultHitBoxY;

    public Entity(String name) {
        this.name = name;
    }

    public void update() {
        spriteCounter++;
        if (spriteCounter > timeUpdate) {
            spriteNum = (spriteNum + 1) % maxSprite;
            spriteCounter=0;

        }

    }

    public void draw(Graphics2D g2, PlayingPage pp) {
        BufferedImage img = switch (direction) {
            case "up" -> up_a[spriteNum];
            case "down" -> down_a[spriteNum];
            case "left" -> left_a[spriteNum];
            case "right" -> right_a[spriteNum];
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
        left_a = new BufferedImage[maxSprite];
        right_a = new BufferedImage[maxSprite];
        up_a = new BufferedImage[maxSprite];
        down_a = new BufferedImage[maxSprite];

        left = Utils.load_image(path, name + "_left");
        right = Utils.load_image(path, name + "_right");
        up = Utils.load_image(path, name + "_up");
        down = Utils.load_image(path, name + "_down");

        for (int i = 0; i < maxSprite; i++) {
            left_a[i] = left.getSubimage(i * sizeSubImg, 0, sizeSubImg, sizeSubImg);
            right_a[i] = right.getSubimage(i * sizeSubImg, 0, sizeSubImg, sizeSubImg);
            up_a[i] = up.getSubimage(i * sizeSubImg, 0, sizeSubImg, sizeSubImg);
            down_a[i] = down.getSubimage(i * sizeSubImg, 0, sizeSubImg, sizeSubImg);

            left_a[i] = Utils.scaleImg(left_a[i], Constants.TILE_SIZE, Constants.TILE_SIZE);
            right_a[i] = Utils.scaleImg(right_a[i], Constants.TILE_SIZE, Constants.TILE_SIZE);
            up_a[i] = Utils.scaleImg(up_a[i], Constants.TILE_SIZE, Constants.TILE_SIZE);
            down_a[i] = Utils.scaleImg(down_a[i], Constants.TILE_SIZE, Constants.TILE_SIZE);
        }

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

    public int getSpriteCounter() {
        return spriteCounter;
    }

    public int getWaitCounter() {
        return waitCounter;
    }

    public int getSpriteNum() {
        return spriteNum;
    }

    public int getWaitNum() {
        return waitNum;
    }

    public int getMaxSprite() {
        return maxSprite;
    }

    public int getTimeUpdate() {
        return timeUpdate;
    }
}
