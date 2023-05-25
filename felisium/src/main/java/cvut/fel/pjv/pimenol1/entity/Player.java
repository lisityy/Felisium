package cvut.fel.pjv.pimenol1.entity;

import cvut.fel.pjv.pimenol1.main.Constants;
import cvut.fel.pjv.pimenol1.main.GameState;
import cvut.fel.pjv.pimenol1.inventorys.Bag;
import cvut.fel.pjv.pimenol1.inventorys.Weapon;
import cvut.fel.pjv.pimenol1.pages.PlayingPage;
import cvut.fel.pjv.pimenol1.utils.CheckerCollision;
import cvut.fel.pjv.pimenol1.utils.KeyHandler;
import cvut.fel.pjv.pimenol1.utils.MusicPlayer;
import cvut.fel.pjv.pimenol1.utils.Utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

/**
 * The Player class represents a player entity in the game.
 * It extends the Entity class and contains methods for player movement, interaction with objects and entities,
 * and handling player attributes such as health and inventory.
 */
public class Player extends Entity {

    private final PlayingPage pp;
    private final KeyHandler kh;
    private final MusicPlayer musicPlayer = new MusicPlayer();

    protected BufferedImage sleep, rightSocks1, rightSocks2, leftSocks1, leftSocks2, upSocks1, upSocks2, downSocks1, downSocks2;
    protected BufferedImage attackUp1, attackUp2, attackLeft, attackRight, attackDown1, attackDown2;
    protected BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;

    protected final int xScreen, yScreen;

    private boolean attaking = false;
    private boolean useValeriana = false;
    private boolean hasSocks = false;
    private boolean hitDoor = false;
    private boolean hitCat = false;

    private int timerValeriana = 0;
    private int timerWing = 0;
    private int socksTimer = 0;

    private int catsLeft = 0;
    private int indexDoor;
    private int indexCat;

    private int damage = 2;

    public Bag bag;

    /**
     * Constructor for the Player class.
     *
     * @param pp The PlayingPage object.
     * @param kh The KeyHandler object.
     */
    public Player(PlayingPage pp, KeyHandler kh) {
        super("cat", pp);
        this.pp = pp;
        this.kh = kh;

        this.xScreen = (Constants.SCREEN_WIDTH / 2) - (Constants.TILE_SIZE / 2);
        this.yScreen = (Constants.SCREEN_HIGH / 2) - (Constants.TILE_SIZE / 2);

        this.hitBox = new Rectangle(Constants.SCALE, 9 * Constants.SCALE, 13 * Constants.SCALE, 6 * Constants.SCALE);
        this.defaultHitBoxX = hitBox.x;
        this.defaultHitBoxY = hitBox.y;

        setDefultValues();
        getPlayerImg();
    }

    private void setDefultValues() {
        this.xWorld = Constants.TILE_SIZE * 23;
        this.yWorld = Constants.TILE_SIZE * 21;
        this.speed = 4;
        this.direction = "up";
        this.maxLife = 9;
        this.life = maxLife;
        this.bag = new Bag(this);
        this.maxSprite = 2;
        this.maxTimeInvincible = 120;
        bag.addWeapon(new Weapon("swort", 99, -1, -1), pp.getUi());
    }

    private void getPlayerImg() {
        String path = "player";
        sleep = setup(path, "cat_sleep");
        up1 = setup(path, name + "_up_1");
        down1 = setup(path, name + "_down_1");
        right1 = setup(path, name + "_right_1");
        left1 = setup(path, name + "_left_1");
        right2 = setup(path, name + "_right_2");
        up2 = setup(path, name + "_up_2");
        down2 = setup(path, name + "_down_2");
        left2 = setup(path, name + "_left_2");

        rightSocks1 = setup(path, "cat_socks_right_1");
        rightSocks2 = setup(path, "cat_socks_right_2");
        leftSocks1 = setup(path, "cat_socks_left_1");
        leftSocks2 = setup(path, "cat_socks_left_2");
        downSocks1 = setup(path, "cat_socks_down_1");
        downSocks2 = setup(path, "cat_socks_down_2");
        upSocks1 = setup(path, "cat_socks_up_1");
        upSocks2 = setup(path, "cat_socks_up_2");

        attackUp1 = setup(path, "cat_up_attack1");
        attackUp2 = setup(path, "cat_up_attack2");
        attackLeft = setup(path, "cat_left_attack");
        attackRight = setup(path, "cat_right_attack");
        attackDown1 = setup(path, "cat_down_attack1");
        attackDown2 = setup(path, "cat_down_attack2");

        attackUp1 = Utils.scaleImg(attackUp1, Constants.TILE_SIZE * 2, Constants.TILE_SIZE * 2);
        attackUp2 = Utils.scaleImg(attackUp2, Constants.TILE_SIZE * 2, Constants.TILE_SIZE * 2);
        attackLeft = Utils.scaleImg(attackLeft, Constants.TILE_SIZE * 2, Constants.TILE_SIZE * 2);
        attackRight = Utils.scaleImg(attackRight, Constants.TILE_SIZE * 2, Constants.TILE_SIZE * 2);
        attackDown1 = Utils.scaleImg(attackDown1, Constants.TILE_SIZE * 2, Constants.TILE_SIZE * 2);
        attackDown2 = Utils.scaleImg(attackDown2, Constants.TILE_SIZE * 2, Constants.TILE_SIZE * 2);

    }

    /**
     * Updates the player's state and handles player actions based on user input.
     */
    @Override
    public void update() {
        int countCat = 0;
        for (Entity n : pp.npc) {
            if (n != null) {
                countCat++;
            }
        }
        this.catsLeft = countCat - 1;

        if (life <= 0) {
            Constants.gameStatePlay = GameState.GAMEOVER;
            return;
        }

        if (catsLeft == 0 && hitCat && Objects.equals(pp.npc[indexCat].name, "queenCat")) {
            Constants.gameStatePlay = GameState.WIN;
            return;
        }


        if (hasSocks) {
            socksTimer++;
            if (socksTimer > 500) {
                hasSocks = false;
                speed -= 2;
                socksTimer = 0;
            }
        }
        if (haveWing) {
            timerWing++;
            if (timerWing > 1000) {
                haveWing = false;
                timerWing = 0;
            }
        }

        if (useValeriana) {
            timerValeriana++;
            if (timerValeriana > 500) {
                timerValeriana = 0;
                useValeriana = false;
            }
            if (kh.isUpPressed()) {
                direction = "down";
            } else if (kh.isDownPressed()) {
                direction = "up";
            } else if (kh.isLeftPressed()) {
                direction = "right";
            } else if (kh.isRightPressed()) {
                direction = "left";
            }
        } else {
            if (kh.isUpPressed()) {
                direction = "up";
            } else if (kh.isDownPressed()) {
                direction = "down";
            } else if (kh.isLeftPressed()) {
                direction = "left";
            } else if (kh.isRightPressed()) {
                direction = "right";
            }
        }

        if (kh.isDownPressed() || kh.isUpPressed() || kh.isRightPressed() || kh.isLeftPressed()) {

            // Check tile collision
            collisionOn = false;
            hitDoor = false;
            CheckerCollision.checkTile(this, pp.getTileManager());

            // Check object collision
            int objInx = CheckerCollision.checkObject(this, true, pp);
            pickUpObj(objInx);

            // Check NPC collision
            int npcInx = CheckerCollision.checkEntity(this, pp.npc);
            connectNPC(npcInx);

            // Check alien collision
            int alienInx = CheckerCollision.checkEntity(this, pp.getAliens());
            connectAlien(alienInx);

            if (!collisionOn && !hitDoor) {
                switch (direction) {
                    case "up" -> yWorld -= speed;
                    case "down" -> yWorld += speed;
                    case "left" -> xWorld -= speed;
                    case "right" -> xWorld += speed;
                }
            }

            spriteTimer++;
            if (spriteTimer > 12) {
                if (spriteNum == 0) {
                    spriteNum = 1;
                } else if (spriteNum == 1) {
                    spriteNum = 0;
                }
                spriteTimer = 0;
            }
        } else if (!attaking) {
            waitTimer++;
            if (waitTimer > 250) {
                direction = "sleep";
                waitTimer = 0;
            }
        }
        if (kh.isSpacePressed()) {
            attaking = true;
            attack();
        } else {
            attaking = false;
        }
        if (invincible) {
            timerInvincible++;
            if (timerInvincible > maxTimeInvincible) {
                timerInvincible = 0;
                invincible = false;
            }
        }
        pp.getUi().setCountHeart(life);
        bag.update();
    }

    /**
     * Draws the player on the screen.
     *
     * @param g2 The Graphics2D object.
     * @param pp The PlayingPage object.
     */
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
        } else if (attaking) {
            img = switch (direction) {
                case "up" -> spriteNum == 1 ? attackUp1 : attackUp2;
                case "down" -> spriteNum == 1 ? attackDown1 : attackDown2;
                case "left" -> attackLeft;
                case "right" -> attackRight;
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
        bag.drawBag(g2);
        if (invincible) {
            g2.setComposite(AlphaComposite.getInstance((AlphaComposite.SRC_OVER), 0.3f));
        }
        g2.drawImage(img, xScreen, yScreen, null);
        g2.setComposite(AlphaComposite.getInstance((AlphaComposite.SRC_OVER), 1f));
    }


    private void connectNPC(int index) {
        if (index != 999) {
            hitCat = true;
            indexCat = index;

            if (kh.isEnterPressed()) {
                pp.npc[index].speak();
            }
        } else {
            hitCat = false;
        }
    }

    public void connectAlien(int i) {
        if (i != 999) {
            if (!invincible) {
                life -= pp.getAliens()[i].getDamage();
                invincible = true;
            }
        }
    }

    /**
     * Initiates an attack by the player.
     * Calculates damage and applies it to the targeted alien if there is a collision.
     */
    public void attack() {
        int monsterIndex = CheckerCollision.checkEntity(this, pp.getAliens());

        if (monsterIndex != 999 && !pp.getAliens()[monsterIndex].invincible) {

            int damage = calculateDamage();
            pp.getAliens()[monsterIndex].takeDamage(damage);
        }
    }

    private int calculateDamage() {
        return damage;
    }


    /**
     * Picks up an object if there is a collision with the player.
     *
     * @param inx The index of the collided object in the object array.
     */
    public void pickUpObj(int inx) {
        if (inx == 999) return;
        if (!pp.obj[inx].canTake) {
            if (Objects.equals(pp.obj[inx].name, "door")) {
                hitDoor = true;
                indexDoor = inx;
            }
        } else {
            musicPlayer.play("/music/UrrCat.wav");
            musicPlayer.stop();
            boolean addToBag = bag.addItem(pp.obj[inx], pp.getUi());
            if (addToBag)
                pp.obj[inx] = null;
        }
    }

    public int getCatsLeft() {
        return catsLeft;
    }

    public void setCatsLeft(int catsLeft) {
        this.catsLeft = catsLeft;
    }

    public void setUseValeriana(boolean useValeriana) {
        this.useValeriana = useValeriana;
    }

    public void setHasSocks(boolean hasSocks) {
        this.hasSocks = hasSocks;
    }

    public boolean isHitDoor() {
        return hitDoor;
    }

    public void setHitDoor(boolean hitDoor) {
        this.hitDoor = hitDoor;
    }

    public PlayingPage getPp() {
        return pp;
    }

    public int getIndexDoor() {
        return indexDoor;
    }

    public boolean isHitCat() {
        return hitCat;
    }

    public void setHitCat(boolean hitCat) {
        this.hitCat = hitCat;
    }

    public int getIndexCat() {
        return indexCat;
    }

    public int getTimerWing() {
        return timerWing;
    }

    public void setIndexCat(int indexCat) {
        this.indexCat = indexCat;
    }

    public int getxScreen() {
        return xScreen;
    }

    public int getyScreen() {
        return yScreen;
    }

    @Override
    public int getDamage() {
        return damage;
    }

}

