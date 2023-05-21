package cvut.fel.pjv.pimenol1.entity;

import cvut.fel.pjv.pimenol1.main.PlayingPage;

import java.util.Random;

public class NPC_catan extends Entity{

    public NPC_catan(PlayingPage pp, String path, String name, int x, int y) {
        super(name, pp);
        this.xWorld = x;
        this.yWorld = y;
        String [] dir={"up", "down", "left", "right"};
        direction = dir[random.nextInt(10)%4];
        collisionOn = true;
        sizeSubImg = 32;
        defultHitBoxX = hitBox.x;
        defultHitBoxY = hitBox.y;
        maxSprite=24;
        maxTimeUpdate=30;
        sum=50;

        getEntityImg(path);
    }

    @Override
    public void update(){
        spriteTimer++;
        if (spriteTimer > timeUpdate) {
            spriteNum = (spriteNum + 1) % maxSprite;
            spriteTimer = 0;
            haveDialog = false;
            timeUpdate = random.nextInt(maxTimeUpdate);
        }
    }
}
