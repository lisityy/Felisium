package cvut.fel.pjv.pimenol1.aliens;

import cvut.fel.pjv.pimenol1.entity.Entity;
import cvut.fel.pjv.pimenol1.main.Constants;
import cvut.fel.pjv.pimenol1.pages.PlayingPage;
import cvut.fel.pjv.pimenol1.utils.CheckerCollision;
import cvut.fel.pjv.pimenol1.utils.Utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * The Alien class represents an alien entity in the game.
 * It extends the Entity class.
 */
public class Alien extends Entity {

    protected int damage = 1;
    private BufferedImage[] attackImgs = new BufferedImage[5];

    /**
     * Constructs a new Alien object with the specified parameters.
     *
     * @param path The path of the alien's image.
     * @param name The name of the alien.
     * @param x    The x-coordinate of the alien's position.
     * @param y    The y-coordinate of the alien's position.
     * @param pp   The PlayingPage object.
     */
    public Alien(String path, String name, int x, int y, PlayingPage pp) {
        super(name, pp);

        xWorld = x;
        yWorld = y;
        sum = 25;

        speed = 1;
        maxLife = 5;
        life = maxLife;
        maxTimeUpdate = 30;

        hitBox.height = Constants.TILE_SIZE + sum;
        hitBox.width = Constants.TILE_SIZE + sum;
        defaultHitBoxX = hitBox.x;
        defaultHitBoxY = hitBox.y;

        direction = "right";
        maxSprite = 4;
        this.timeUpdate = 12;
        getEntityImg("enemy");
        loadAttackImg();

    }

    /**
     * Loads the attack images for the alien.
     */
    private void loadAttackImg() {
        BufferedImage t = Utils.load_image("enemy", "alien_attack");
        for (int i = 0; i < 5; i++) {
            attackImgs[i] = t.getSubimage(i * sizeSubImg, 0, sizeSubImg, sizeSubImg);
            attackImgs[i] = Utils.scaleImg(attackImgs[i], Constants.TILE_SIZE + sum, Constants.TILE_SIZE + sum);
        }
    }

    /**
     * Initiates an attack action for the alien.
     */
    public void attack() {
        direction = "attack";
    }

    /**
     * Updates the state of the alien.
     */
    public void update() {
        getRandomDirection(150);
        collisionOn = false;
        CheckerCollision.checkTile(this, pp.getTileManager());
        CheckerCollision.checkObject(this, false, pp);
        CheckerCollision.checkEntity(this, pp.npc);
        CheckerCollision.checkEntity(this, pp.getAliens());
        boolean connectPlayer = CheckerCollision.checkPlayer(this, pp.player);
        if (connectPlayer) {
            attack();
        }

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
            Random random = new Random();
            timeUpdate = random.nextInt(maxTimeUpdate);
        }
        if (invincible) {
            timerInvincible++;
            if (timerInvincible > maxTimeInvincible) {
                timerInvincible = 0;
                invincible = false;
            }
        }

    }

    
    @Override
    public void draw(Graphics2D g2, PlayingPage pp) {
        BufferedImage img = switch (direction) {
            case "up" -> up_a[spriteNum];
            case "down" -> down_a[spriteNum];
            case "left" -> left_a[spriteNum];
            case "right" -> right_a[spriteNum];
            case "attack" -> attackImgs[spriteNum];
            default -> null;
        };

        int screenX = xWorld - pp.player.xWorld + pp.player.getxScreen();
        int screenY = yWorld - pp.player.yWorld + pp.player.getyScreen();


        if (xWorld + Constants.TILE_SIZE > pp.player.xWorld - pp.player.getxScreen()
                && xWorld - Constants.TILE_SIZE < pp.player.xWorld + pp.player.getxScreen()
                && yWorld + Constants.TILE_SIZE > pp.player.yWorld - pp.player.getyScreen()
                && yWorld - Constants.TILE_SIZE < pp.player.yWorld + pp.player.getyScreen()) {
            if (invincible) {
                g2.setComposite(AlphaComposite.getInstance((AlphaComposite.SRC_OVER), 0.5f));
            }
            g2.drawImage(img, screenX, screenY, null);
            if (invincible) {
                g2.setComposite(AlphaComposite.getInstance((AlphaComposite.SRC_OVER), 1f));
            }
        }
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
