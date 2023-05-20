package cvut.fel.pjv.pimenol1.entity;

import cvut.fel.pjv.pimenol1.inventorys.Item;
import cvut.fel.pjv.pimenol1.main.*;
import cvut.fel.pjv.pimenol1.utils.CheckerCollision;
import cvut.fel.pjv.pimenol1.utils.KeyHandler;
import cvut.fel.pjv.pimenol1.utils.MusicPlayer;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Player extends Entity {

    private final PlayingPage pp;
    private final KeyHandler kh;
    private MusicPlayer musicPlayer = new MusicPlayer();
    BufferedImage sleep, rightSocks1, rightSocks2, leftSocks1, leftSocks2, upSocks1, upSocks2, downSocks1, downSocks2;
    BufferedImage tileInventorys;

    public final int xScreen, yScreen;

    private boolean hasWings = false;
    private int keyCount = 0;
    private boolean hasSocks = false;
    private int socksTimer = 0;

    public ArrayList<Item> items = new ArrayList<>();

    //    private  int wingsTimer=0;
    public KeyHandler getKh() {
        return kh;
    }


    public Player(PlayingPage pp, KeyHandler kh) {
        super("player", "cat");
        //ok
        this.pp = pp;
        this.kh = kh;

        this.xScreen = (Constants.SCREEN_WIDTH / 2) - (Constants.TILE_SIZE / 2);
        this.yScreen = (Constants.SCREEN_HIGH / 2) - (Constants.TILE_SIZE / 2);

        this.hitBox = new Rectangle(1 * Constants.SCALE, 9 * Constants.SCALE, 13 * Constants.SCALE, 6 * Constants.SCALE);
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
        this.maxLife=9;
        this.life=maxLife;
    }

    public void getPlayerImg() {
        sleep = setup("player", "cat_sleep");

        rightSocks1 = setup("player", "cat_socks_right_1");
        rightSocks2 = setup("player", "cat_socks_right_2");
        leftSocks1 = setup("player", "cat_socks_left_1");
        leftSocks2 = setup("player", "cat_socks_left_2");
        downSocks1 = setup("player", "cat_socks_down_1");
        downSocks2 = setup("player", "cat_socks_down_2");
        upSocks1 = setup("player", "cat_socks_up_1");
        upSocks2 = setup("player", "cat_socks_up_2");

    }

    @Override
    public void update() {
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
            CheckerCollision.checkTile(this, pp.getTileManager());

            int objInx = CheckerCollision.checkObject(this, true, pp);
            pickUpObj(objInx);

            int npcInx = CheckerCollision.checkEntity(this, pp.npc); //TODO: something with npc

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
        if (items.size()>=5){
            pp.getUi().writeMessage("Your bag is full!");
            return;
        }
        pp.obj[inx].pickUp(this, inx);
        items.add(pp.obj[inx]);
        pp.obj[inx] = null;

    }
    public void drawItems(Graphics2D g2){
        int x=655;
        int y=50;
        for(Item item: items){
            g2.drawImage(item.img,x,y,null);
            x+=Constants.TILE_SIZE+8;
        }
    }

    @Override
    public void draw(Graphics2D g2, PlayingPage pp) {
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

        drawItems(g2);
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

