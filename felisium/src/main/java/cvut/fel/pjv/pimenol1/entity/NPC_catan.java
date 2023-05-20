package cvut.fel.pjv.pimenol1.entity;

import cvut.fel.pjv.pimenol1.main.PlayingPage;

public class NPC_catan extends Entity{

    public NPC_catan(PlayingPage pp, String path, String name, int x, int y) {
        super(name, pp);
        this.xWorld = x;
        this.yWorld = y;
        direction = "up";
        collisionOn = true;
        sizeSubImg = 32;
        defultHitBoxX = hitBox.x;
        defultHitBoxY = hitBox.y;
        maxSprite=24;
        maxTimeUpdate=30;
        sum=50;

        getEntityImg(path);
    }
}
