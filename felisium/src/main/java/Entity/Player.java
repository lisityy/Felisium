package Entity;

import main_pjv.GamePannel;
import main_pjv.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {

    private final GamePannel gp;
    private final KeyHandler kh;

    private final int xScreen, yScreen;

    public int getxScreen() {return xScreen;}

    public int getyScreen() {return yScreen;}

    public GamePannel getGp() {
        return gp;
    }

    public KeyHandler getKh() {
        return kh;
    }


    public Player(GamePannel gp, KeyHandler kh) {
        super();
        this.gp = gp;
        this.kh = kh;

        this.xScreen= gp.getScreenWidth()/2 - (gp.getTileSize()/2);
        this.yScreen=gp.getScreenHeight()/2 - (gp.getTileSize()/2);

        this.hitBox = new Rectangle();
//        this.hitBox.x=xScreen;
//        this.hitBox.y=yScreen;
//        this.hitBox.height=gp.getTileSize();
//        this.hitBox.width=gp.getTileSize();
        this.hitBox.x=8;
        this.hitBox.y=28;
        this.hitBox.height=20;
        this.hitBox.width=32;

        setDefultValues();
        getPlayerImg();
    }
    public void drawHitbox(Graphics g){
        g.setColor(Color.red);
        g.drawRect(hitBox.x, hitBox.y, hitBox.width, hitBox.height);
    }

    public void setDefultValues() {
        this.xWorld = gp.getTileSize()*21;
        this.yWorld = gp.getTileSize()*23;
        this.speed = 4;
        this.direction = "up";
    }

    public void getPlayerImg() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/players/cat_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/players/cat_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/players/cat_down_1.1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/players/cat_down_2.2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/players/catfat_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/players/catfat_right_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/players/catfat_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/players/catfat_left_2.png"));
            sleep = ImageIO.read(getClass().getResourceAsStream("/players/cat_sleep.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {

        if (kh.isUpPressed()) {
            direction = "up";
        } else if (kh.isDownPressed()) {
            direction = "down";
        } else if (kh.isLeftPressed()) {
            direction = "left";
        } else if (kh.isRightPressed()) {
            direction = "right";
        } else {
            waitCounter++;
            if (waitCounter > 100) {
                direction = "sleep";
            }
            return;
        }
        // CHECK TILE COLLISION
        gp.getCheckerCollision().checkTile(this);
        if(!collitionOn){
            switch (direction) {
                case "up" -> yWorld -= speed;
                case "down" -> yWorld += speed;
                case "left" -> xWorld -= speed;
                case "right" -> xWorld += speed;
            }
        }
        waitCounter = 0;
        spriteCounter++;
        if (spriteCounter > 7) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }

    }

    public void draw(Graphics2D g2) {

        BufferedImage img = switch (direction) {
            case "up" -> spriteNum == 1 ? up1 : up2;
            case "down" -> spriteNum == 1 ? down1 : down2;
            case "left" -> spriteNum == 1 ? left1 : left2;
            case "right" -> spriteNum == 1 ? right1 : right2;
            case "sleep" -> sleep;
            default -> null;
        };
        g2.drawImage(img, xScreen, yScreen, gp.getTileSize(), gp.getTileSize(), null);
        System.out.println("xW: "+this.xWorld);
        System.out.println("yW "+this.yWorld);
//        this.drawHitbox(g2);
    }

}
