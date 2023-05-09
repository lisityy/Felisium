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
     protected int defultHitBoxX, defultHitBoxY;

     public int getxWorld() {
          return xWorld;
     }

     public void setxWorld(int xWorld) {
          this.xWorld = xWorld;
     }

     public int getyWorld() {
          return yWorld;
     }

     public void setyWorld(int yWorld) {
          this.yWorld = yWorld;
     }

     public int getxScreen() {
          return xScreen;
     }

     public void setxScreen(int xScreen) {
          this.xScreen = xScreen;
     }

     public int getyScreen() {
          return yScreen;
     }

     public void setyScreen(int yScreen) {
          this.yScreen = yScreen;
     }

     public int getSpeed() {
          return speed;
     }

     public void setSpeed(int speed) {
          this.speed = speed;
     }

     public BufferedImage getUp1() {
          return up1;
     }

     public void setUp1(BufferedImage up1) {
          this.up1 = up1;
     }

     public BufferedImage getUp2() {
          return up2;
     }

     public void setUp2(BufferedImage up2) {
          this.up2 = up2;
     }

     public BufferedImage getDown1() {
          return down1;
     }

     public void setDown1(BufferedImage down1) {
          this.down1 = down1;
     }

     public BufferedImage getDown2() {
          return down2;
     }

     public void setDown2(BufferedImage down2) {
          this.down2 = down2;
     }

     public BufferedImage getLeft1() {
          return left1;
     }

     public void setLeft1(BufferedImage left1) {
          this.left1 = left1;
     }

     public BufferedImage getLeft2() {
          return left2;
     }

     public void setLeft2(BufferedImage left2) {
          this.left2 = left2;
     }

     public BufferedImage getRight1() {
          return right1;
     }

     public void setRight1(BufferedImage right1) {
          this.right1 = right1;
     }

     public BufferedImage getRight2() {
          return right2;
     }

     public void setRight2(BufferedImage right2) {
          this.right2 = right2;
     }

     public BufferedImage getSleep() {
          return sleep;
     }

     public void setSleep(BufferedImage sleep) {
          this.sleep = sleep;
     }

     public String getDirection() {
          return direction;
     }

     public void setDirection(String direction) {
          this.direction = direction;
     }

     public int getSpriteCounter() {
          return spriteCounter;
     }

     public void setSpriteCounter(int spriteCounter) {
          this.spriteCounter = spriteCounter;
     }

     public int getWaitCounter() {
          return waitCounter;
     }

     public void setWaitCounter(int waitCounter) {
          this.waitCounter = waitCounter;
     }

     public int getSpriteNum() {
          return spriteNum;
     }

     public void setSpriteNum(int spriteNum) {
          this.spriteNum = spriteNum;
     }

     public int getWaitNum() {
          return waitNum;
     }

     public void setWaitNum(int waitNum) {
          this.waitNum = waitNum;
     }

     public boolean isCollitionOn() {
          return collitionOn;
     }

     public void setCollitionOn(boolean collitionOn) {
          this.collitionOn = collitionOn;
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
