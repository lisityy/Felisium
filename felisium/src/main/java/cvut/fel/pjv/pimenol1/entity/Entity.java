package cvut.fel.pjv.pimenol1.entity;

import cvut.fel.pjv.pimenol1.main.Constants;
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
    int spriteCounter = 0, waitCounter = 0;
    int spriteNum = 1, waitNum = 1;

    public boolean collitionOn = false;
    public Rectangle hitBox = new Rectangle(0, 0, Constants.TILE_SIZE, Constants.TILE_SIZE);
    protected int defultHitBoxX, defultHitBoxY;

    public Entity(String path, String name) {
        this.name=name;
        getEntityImg(path);
    }

    public int getDefultHitBoxX() {
        return defultHitBoxX;
    }

    public int getDefultHitBoxY() {
        return defultHitBoxY;
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
}
