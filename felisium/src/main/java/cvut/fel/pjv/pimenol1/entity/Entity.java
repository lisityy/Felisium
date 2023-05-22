package cvut.fel.pjv.pimenol1.entity;

import cvut.fel.pjv.pimenol1.main.Constants;
import cvut.fel.pjv.pimenol1.pages.PlayingPage;
import cvut.fel.pjv.pimenol1.utils.CheckerCollision;
import cvut.fel.pjv.pimenol1.utils.Utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.Random;

public class Entity implements Serializable {
    protected PlayingPage pp;

    public int xWorld=0, yWorld=0;
    public int speed;
    public String name;
    protected transient BufferedImage left, right, up, down;
    protected transient BufferedImage[] left_a, right_a, up_a, down_a;
    protected int sum = 0;

    protected boolean invincible = false;
    protected int timerInvincible = 0, maxTimeInvincible = 120;

//    protected BufferedImage dialog;
    protected boolean haveDialog = false;
    protected Random random = new Random();

    protected int sizeSubImg = 30;
    public String direction;

    protected int acrionTimer = 0;
    protected int spriteTimer = 0, waitTimer = 0;
    protected int spriteNum = 0, waitNum = 1, maxSprite = 1;
    protected int timeUpdate = 12 , maxTimeUpdate=100;

    protected int maxLife, life;
    protected int damage = 0;

    protected boolean haveWing = false;
    public boolean collisionOn = false;
    public Rectangle hitBox = new Rectangle(0, 0, Constants.TILE_SIZE, Constants.TILE_SIZE);
    protected int defultHitBoxX, defultHitBoxY;


    public Entity(String name, PlayingPage pp) {
        this.name = name;
        this.pp = pp;
    }

    public void update() {
        getRandomDirection(150);
        collisionOn = false;
        CheckerCollision.checkTile(this, pp.getTileManager());
        CheckerCollision.checkObject(this, false, pp);
        CheckerCollision.checkEntity(this, pp.npc);
        CheckerCollision.checkEntity(this, pp.getAliens());
        CheckerCollision.checkPlayer(this, pp.player);

        if (!collisionOn) {
            switch (direction) {
                case "up" -> yWorld -= speed;
                case "down" -> yWorld += speed;
                case "left" -> xWorld -= speed;
                case "right" -> xWorld += speed;
            }
        }
        spriteTimer++;
        if (spriteTimer > timeUpdate) {
            spriteNum = (spriteNum + 1) % maxSprite;
            spriteTimer = 0;
            haveDialog = false;
            timeUpdate = random.nextInt(maxTimeUpdate);
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
//            if (haveDialog) {
//                g2.drawImage(dialog, screenX + Constants.TILE_SIZE + 20, screenY - 10, null);
//            }
        }

    }


    public void speak() {
//        haveDialog = true;
//        dialog = Utils.load_image("text", "dialog");
//        dialog = Utils.scaleImg(dialog, Constants.TILE_SIZE, Constants.TILE_SIZE);
    }

    public void facePlayer() {
        switch (pp.player.direction) {
            case "up" -> direction = "down";
            case "down" -> direction = "up";
            case "left" -> direction = "right";
            case "right" -> direction = "left";
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

            left_a[i] = Utils.scaleImg(left_a[i], Constants.TILE_SIZE + sum, Constants.TILE_SIZE + sum);
            right_a[i] = Utils.scaleImg(right_a[i], Constants.TILE_SIZE + sum, Constants.TILE_SIZE + sum);
            up_a[i] = Utils.scaleImg(up_a[i], Constants.TILE_SIZE + sum, Constants.TILE_SIZE + sum);
            down_a[i] = Utils.scaleImg(down_a[i], Constants.TILE_SIZE + sum, Constants.TILE_SIZE + sum);
        }

    }

    public BufferedImage setup(String path, String name) {
        BufferedImage img = null;
        img = Utils.load_image(path, name);
        img = Utils.scaleImg(img, Constants.TILE_SIZE, Constants.TILE_SIZE);
        return img;
    }


    public void getRandomDirection(int interval) {
        acrionTimer++;
        if (acrionTimer > interval) {
            int i = random.nextInt(100) + 1;

            if (i <= 25) { // 25% of the time it goes up
                direction = "up";
            }
            if (i > 25 && i <= 50) { // 25% of the time it goes down
                direction = "down";
            }
            if (i > 50 && i <= 75) { // 25% of the time it goes left
                direction = "left";
            }
            if (i > 75) { // 25% of the time it goes right
                direction = "right";
            }
            acrionTimer = 0;
        }
    }

    public void takeDamage(int damage){
        invincible=true;
        life-=damage;
    }

    public int getDefultHitBoxX() {
        return defultHitBoxX;
    }

    public int getDefultHitBoxY() {
        return defultHitBoxY;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public boolean isHaveWing() {
        return haveWing;
    }

    public void setHaveWing(boolean haveWing) {
        this.haveWing = haveWing;
    }
}
