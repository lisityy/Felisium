package cvut.fel.pjv.pimenol1.entity;

import cvut.fel.pjv.pimenol1.pages.PlayingPage;

public class NPC_dranik extends  Entity{

    public NPC_dranik(PlayingPage pp, String path, String name, int x, int y) {
        super(name, pp);
        this.xWorld = x;
        this.yWorld = y;
        String[] dir = {"up", "down", "left", "right"};
        direction = dir[random.nextInt(10) % 4];
        collisionOn = true;
        sizeSubImg = 16;
        maxSprite = 8;
        maxTimeUpdate = 30;
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
//            timeUpdate = random.nextInt(maxTimeUpdate);
        }
    }
}
