package cvut.fel.pjv.pimenol1.entity;

import cvut.fel.pjv.pimenol1.main.Constants;
import cvut.fel.pjv.pimenol1.pages.PlayingPage;

public class NPC_catan extends Entity {

    public NPC_catan(PlayingPage pp, String path, String name, int x, int y) {
        super(name, pp);
        this.xWorld = x;
        this.yWorld = y;
        String[] dir = {"up", "down", "left", "right"};
        direction = dir[random.nextInt(10) % 4];
        collisionOn = true;
        sizeSubImg = 32;
        maxSprite = 24;
        maxTimeUpdate = 30;
        sum = Constants.TILE_SIZE;

        hitBox.x=Constants.TILE_SIZE/4+10;
        hitBox.y=Constants.TILE_SIZE/4+10;
        defaultHitBoxX = hitBox.x;
        defaultHitBoxY = hitBox.y;

        getEntityImg(path);
    }

    @Override
    public void update() {
        spriteTimer++;
        if (spriteTimer > timeUpdate) {
            spriteNum = (spriteNum + 1) % maxSprite;
            spriteTimer = 0;
            haveDialog = false;
            timeUpdate = random.nextInt(maxTimeUpdate);
        }
    }
}
