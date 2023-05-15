package Entity;

import background.Tile;
import main_pjv.GamePannel;
import main_pjv.KeyHandler;
import main_pjv.MusicPlayer;
import main_pjv.Utils;

import javax.imageio.ImageIO;
import javax.sound.midi.Soundbank;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity {

    private final GamePannel gp;
    private final KeyHandler kh;
    private MusicPlayer musicPlayer = new MusicPlayer();
    BufferedImage rightSocks1, rightSocks2, leftSocks1, leftSocks2, upSocks1, upSocks2, downSocks1, downSocks2;

    public final int xScreen, yScreen;

    private boolean hasWings = false;
    private int keyCount = 0;
    private boolean hasSocks = false;
    private int socksTimer = 0;

    //    private  int wingsTimer=0;
    public KeyHandler getKh() {
        return kh;
    }


    public Player(GamePannel gp, KeyHandler kh) {
        //ok
        this.gp = gp;
        this.kh = kh;

        this.xScreen = (gp.getScreenWidth() / 2) - (gp.getTileSize() / 2);
        this.yScreen = (gp.getScreenHeight() / 2) - (gp.getTileSize() / 2);

        this.hitBox = new Rectangle(1*gp.getScale(), 9*gp.getScale(), 13*gp.getScale(), 6*gp.getScale());
        this.defultHitBoxX = hitBox.x;
        this.defultHitBoxY = hitBox.y;
        setDefultValues();
        getPlayerImg();
    }

    public void setDefultValues() {

        this.xWorld = gp.getTileSize() * 23;
        this.yWorld = gp.getTileSize() * 21;
        this.speed = 4;
        this.direction = "up";
    }

    public BufferedImage setup(String imageName) {
        Utils utils = new Utils();
        BufferedImage img = null;
        try {
            img = ImageIO.read(getClass().getResourceAsStream("/players/" + imageName + ".png"));
            img = utils.scaleImg(img, gp.getTileSize(), gp.getTileSize());
        } catch (IOException e) {
            System.out.println("Error reading img player:" + e.getMessage());
            e.printStackTrace();
        }
        return img;
    }

    public void getPlayerImg() {

        rightSocks1 = setup("cat_socks_right_1");
        rightSocks2 = setup("cat_socks_right_2");
        leftSocks1 = setup("cat_socks_left_1");
        leftSocks2 = setup("cat_socks_left_2");
        downSocks1 = setup("cat_socks_down_1");
        downSocks2 = setup("cat_socks_down_2");
        upSocks1 = setup("cat_socks_up_1");
        upSocks2 = setup("cat_socks_up_2");

        up1 = setup("cat_up_1");
        up2 = setup("cat_up_2");
        down1 = setup("cat_down_1.1");
        down2 = setup("cat_down_2.2");
        right1 = setup("catfat_right_1");
        right2 = setup("catfat_right_2");
        left1 = setup("catfat_left_1");
        left2 = setup("catfat_left_2");
        sleep = setup("cat_sleep");

    }

    public void update() {
        //ok
        if (kh.isDownPressed() || kh.isUpPressed() || kh.isRightPressed() || kh.isLeftPressed()) {

            if (kh.isUpPressed()) {
                direction = "up";
            } else if (kh.isDownPressed()) {
                direction = "down";
            } else if (kh.isLeftPressed()) {
                direction = "left";
            } else if (kh.isRightPressed()) {
                direction = "right";
            }

            // CHECK TILE COLLISION
            collitionOn = false;
            gp.getCheckerCollision().checkTile(this);

            int objInx = gp.getCheckerCollision().checkObject(this, true);
            pickUpObj(objInx);

            if (hasSocks) {
                socksTimer++;
                if (socksTimer > 500) {
                    hasSocks = false;
                    speed -= 2;
                }
            }
            if (hasWings) {

            }

            if (!collitionOn || hasWings) {
                switch (direction) {
                    case "up" -> yWorld -= speed;
                    case "down" -> yWorld += speed;
                    case "left" -> xWorld -= speed;
                    case "right" -> xWorld += speed;
                }
            }

            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }

        } else {
            waitCounter++;
            if (waitCounter > 250) {
                direction = "sleep";
                waitCounter = 0;
            }
        }
    }

    public void pickUpObj(int inx) {
        if (inx == 999) return;
        gp.obj[inx].pickUp(this, inx);
    }

    public void draw(Graphics2D g2) {
        BufferedImage img;
        if (hasSocks) {
            img = switch (direction) {
                case "up" -> spriteNum == 1 ? upSocks1 : upSocks2;
                case "down" -> spriteNum == 1 ? downSocks1 : downSocks2;
                case "left" -> spriteNum == 1 ? leftSocks1 : leftSocks2;
                case "right" -> spriteNum == 1 ? rightSocks1 : rightSocks2;
                case "sleep" -> sleep;
                default -> null;
            };

        } else {
            img = switch (direction) {
                case "up" -> spriteNum == 1 ? up1 : up2;
                case "down" -> spriteNum == 1 ? down1 : down2;
                case "left" -> spriteNum == 1 ? left1 : left2;
                case "right" -> spriteNum == 1 ? right1 : right2;
                case "sleep" -> sleep;
                default -> null;
            };
        }
        g2.drawImage(img, xScreen, yScreen, null);
    }


    public GamePannel getGp() {
        return gp;
    }

    public MusicPlayer getMusicPlayer() {
        return musicPlayer;
    }

    public void setMusicPlayer(MusicPlayer musicPlayer) {
        this.musicPlayer = musicPlayer;
    }

    @Override
    public int getxScreen() {
        return xScreen;
    }

    @Override
    public int getyScreen() {
        return yScreen;
    }

    public boolean isHasWings() {
        return hasWings;
    }

    public void setHasWings(boolean hasWings) {
        this.hasWings = hasWings;
    }

    public int getKeyCount() {
        return keyCount;
    }

    public void setKeyCount(int keyCount) {
        this.keyCount = keyCount;
    }

    public boolean isHasSocks() {
        return hasSocks;
    }

    public void setHasSocks(boolean hasSocks) {
        this.hasSocks = hasSocks;
    }
}

