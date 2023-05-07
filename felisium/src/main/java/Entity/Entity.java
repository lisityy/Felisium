package Entity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class Entity {
     public int xWorld, yWorld;
     public int xScreen, yScreen;
     public int speed;
     BufferedImage up1, up2, down1, down2, left1, left2, right1, right2, sleep;
     public String direction;
     int spriteCounter = 0, waitCounter = 0;
     int spriteNum = 1, waitNum = 1;

     public boolean collitionOn =false;
     public Rectangle hitBox;

}
