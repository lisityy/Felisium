package Entity;

import java.awt.image.BufferedImage;

public class Entity {
     public int xWorld, yWorld;
     int speed;
     BufferedImage up1, up2, down1, down2, left1, left2, right1, rigth2, sleep;
     int spriteCounter = 0, waitCounter = 0;
     int spriteNum = 1, waitNum = 1;
     String direction;
    private int armor;
    private Life live;



}
