package cvut.fel.pjv.pimenol1.entity;

import cvut.fel.pjv.pimenol1.main_pjv.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {

    private final GamePanel gp;
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


    public Player(GamePanel gp, KeyHandler kh) {
        //ok
        this.gp = gp;
        this.kh = kh;

        this.xScreen = (Constants.SCREEN_WIDTH/ 2) - (Constants.TILE_SIZE / 2);
        this.yScreen = (Constants.SCREEN_HIGH / 2) - (Constants.TILE_SIZE / 2);

        this.hitBox = new Rectangle(1*Constants.SCALE, 9*Constants.SCALE, 13*Constants.SCALE, 6*Constants.SCALE);
        this.defultHitBoxX = hitBox.x;
        this.defultHitBoxY = hitBox.y;
        setDefultValues();
        getPlayerImg();
    }

    public void setDefultValues() {

        this.xWorld = Constants.TILE_SIZE * 23;
        this.yWorld = Constants.TILE_SIZE * 21;
        this.speed = 4;
        this.direction = "up";
    }

    public BufferedImage setup(String imageName) {
        Utils utils = new Utils();
        BufferedImage img = null;
        try {
            img = ImageIO.read(getClass().getResourceAsStream("/players/" + imageName + ".png"));
            img = utils.scaleImg(img, Constants.TILE_SIZE, Constants.TILE_SIZE);
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


    public GamePanel getGp() {
        return gp;
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

    public void setHasSocks(boolean hasSocks) {
        this.hasSocks = hasSocks;
    }
}

