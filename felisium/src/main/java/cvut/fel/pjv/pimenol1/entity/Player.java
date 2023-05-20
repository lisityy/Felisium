package cvut.fel.pjv.pimenol1.entity;

import cvut.fel.pjv.pimenol1.inventorys.Bag;
import cvut.fel.pjv.pimenol1.main.*;
import cvut.fel.pjv.pimenol1.utils.CheckerCollision;
import cvut.fel.pjv.pimenol1.utils.KeyHandler;
import cvut.fel.pjv.pimenol1.utils.MusicPlayer;
import cvut.fel.pjv.pimenol1.utils.Utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class Player extends Entity {

    private final PlayingPage pp;
    private final KeyHandler kh;
    private MusicPlayer musicPlayer = new MusicPlayer();
    BufferedImage sleep, rightSocks1, rightSocks2, leftSocks1, leftSocks2, upSocks1, upSocks2, downSocks1, downSocks2;
    BufferedImage attackUp1, attackUp2, attackLeft, attackRight, attackDown1, attackDown2;
    protected BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;

    public final int xScreen, yScreen;

    private boolean invincible = false;
    private int timerInvincible = 0, maxTimeInvincible = 120;

    private boolean hasWings = false;
    private boolean attaking = false;
    private int keyCount = 0;
    private boolean hasSocks = false;
    private boolean hitDoor = false;
    private int indexDoor;
    private int socksTimer = 0;

    private int damage = 2;

    public Bag bag;

    //    private  int wingsTimer=0;
    public KeyHandler getKh() {
        return kh;
    }


    public Player(PlayingPage pp, KeyHandler kh) {
        super("cat", pp);
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

    private void setDefultValues() {
        this.xWorld = Constants.TILE_SIZE * 23;
        this.yWorld = Constants.TILE_SIZE * 21;
        this.speed = 4;
        this.direction = "up";
        this.maxLife = 9;
        this.life = maxLife;
        this.bag = new Bag(this);
        this.maxSprite=2;
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

    @Override
    public void update() {

        if (kh.isUpPressed()) {
            direction = "up";
        } else if (kh.isDownPressed()) {
            direction = "down";
        } else if (kh.isLeftPressed()) {
            direction = "left";
        } else if (kh.isRightPressed()) {
            direction = "right";
        }

        if (kh.isDownPressed() || kh.isUpPressed() || kh.isRightPressed() || kh.isLeftPressed()) {

            // CHECK TILE COLLISION
            collisionOn = false;
            hitDoor = false;
            CheckerCollision.checkTile(this, pp.getTileManager());

            int objInx = CheckerCollision.checkObject(this, true, pp);
            pickUpObj(objInx);

            int npcInx = CheckerCollision.checkEntity(this, pp.npc);
            connectNPC(npcInx);
            int alienInx = CheckerCollision.checkEntity(this, pp.getAliens());
            connectAlien(alienInx);

            if (hasSocks) {
                socksTimer++;
                if (socksTimer > 500) {
                    hasSocks = false;
                    speed -= 2;
                }
            }

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
                System.out.println(spriteNum);
                if (spriteNum == 0) {
                    spriteNum = 1;
                } else if (spriteNum == 1) {
                    spriteNum = 0;
                }
                spriteTimer = 0;
            }
        } else {
            waitTimer++;
            if (waitTimer > 250) {
                direction = "sleep";
                waitTimer = 0;
            }
        }
        if (kh.isEnterPressed()) {
            attaking = true;
            attack();
        } else {
            attaking =false;
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

    }

    private void connectNPC(int index) {
        if (index != 999) {
            if (kh.isEnterPressed()) {
                pp.npc[index].speak();
            }
        }
    }

    private void connectAlien(int i) {
        if (i != 999) {
            if (!invincible) {
                life -= pp.getAliens()[i].getDamage();
                invincible = true;
            }
        }
    }

    public void attack() {
        // Check for nearby monsters using the appropriate collision detection mechanism
        int monsterIndex = CheckerCollision.checkEntity(this, pp.getAliens());

        // If a monster is nearby, perform the attack
        if (monsterIndex != 999) {
            Entity monster = pp.getAliens()[monsterIndex];
            int damage = calculateDamage(); // Calculate the damage based on player's stats, weapon, etc.

            // Update the monster's health
            monster.takeDamage(damage);
        }
    }

    private int calculateDamage() {
        return damage;
    }


    private void pickUpObj(int inx) {
        if (inx == 999) return;
        if (!pp.obj[inx].canTake) {
            if (Objects.equals(pp.obj[inx].name, "door")) {
                hitDoor = true;
                indexDoor = inx;
            }
        } else {
            musicPlayer.play("/music/UrrCat.wav");
            musicPlayer.stop();
            bag.addItem(pp.obj[inx], pp.getUi());
            pp.obj[inx] = null;
        }
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
}

