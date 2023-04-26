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
    private int level;
    private int armor;
    private final int xScreen, yScreen;

    public int getxScreen() {
        return xScreen;
    }

    public int getyScreen() {
        return yScreen;
    }

    public GamePannel getGp() {
        return gp;
    }

    public KeyHandler getKh() {
        return kh;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public Player(GamePannel gp, KeyHandler kh) {
        super();
        this.gp = gp;
        this.kh = kh;
        this.xScreen= gp.getScreenWidth()/2 - (gp.getTileSize()/2);
        this.yScreen=gp.getScreenHeight()/2;
        setDefultValues();
        getPlayerImg();
    }

    public void setDefultValues() {
        this.xWorld = gp.getTileSize()*23;
        this.yWorld = gp.getTileSize()*21;
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
            rigth2 = ImageIO.read(getClass().getResourceAsStream("/players/catfat_right_2.png"));
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
            yWorld-=speed;

        } else if (kh.isDownPressed()) {
            direction = "down";
            yWorld+=speed;
        } else if (kh.isLeftPressed()) {
            direction = "left";
            xWorld-=speed;

        } else if (kh.isRightPressed()) {
            direction = "right";
            xWorld+=speed;

        } else {
            waitCounter++;
            if (waitCounter > 100) {
                direction = "sleep";
            }
            return;
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
            case "right" -> spriteNum == 1 ? right1 : rigth2;
            case "sleep" -> sleep;
            default -> null;
        };
        g2.drawImage(img, xScreen, yScreen, gp.getTileSize(), gp.getTileSize(), null);

    }

}
