package cvut.fel.pjv.pimenol1.entity;

import cvut.fel.pjv.pimenol1.main.Constants;
import cvut.fel.pjv.pimenol1.pages.PlayingPage;

public class NPC_queenCat extends Entity {


    public NPC_queenCat(PlayingPage pp, String path, String name, int x, int y) {
        super(name, pp);
        this.xWorld = x;
        this.yWorld = y;
        direction = "up";
        collisionOn = true;
        sizeSubImg = 512;
        maxSprite = 2;
        maxTimeUpdate = 200;
        sum = 45;

        hitBox.height = Constants.TILE_SIZE + sum;
        hitBox.width = Constants.TILE_SIZE + sum;
        defaultHitBoxX = hitBox.x;
        defaultHitBoxY = hitBox.y;

        getEntityImg(path);
    }
}
