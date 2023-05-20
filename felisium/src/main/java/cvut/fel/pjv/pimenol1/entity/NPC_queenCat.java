package cvut.fel.pjv.pimenol1.entity;

import cvut.fel.pjv.pimenol1.main.Constants;
import cvut.fel.pjv.pimenol1.main.PlayingPage;
import cvut.fel.pjv.pimenol1.utils.Utils;

public class NPC_queenCat extends Entity {
    int sum = 45;

    public NPC_queenCat(PlayingPage pp, String path, String name, int x, int y) {
        super(name, pp);
        this.xWorld = x;
        this.yWorld = y;
        direction = "up";
        collitionOn = true;
        sizeSubImg = 512;
        maxSprite = 2;
        timeUpdate = 50;

        hitBox.height = Constants.TILE_SIZE + sum;
        hitBox.width = Constants.TILE_SIZE + sum;
        defultHitBoxX = hitBox.x;
        defultHitBoxY = hitBox.y;

        getEntityImg(path);

        for (int i = 0; i < maxSprite; i++) {

            left_a[i] = Utils.scaleImg(left_a[i], Constants.TILE_SIZE + sum, Constants.TILE_SIZE + sum);
            right_a[i] = Utils.scaleImg(right_a[i], Constants.TILE_SIZE + sum, Constants.TILE_SIZE + sum);
            up_a[i] = Utils.scaleImg(up_a[i], Constants.TILE_SIZE + sum, Constants.TILE_SIZE + sum);
            down_a[i] = Utils.scaleImg(down_a[i], Constants.TILE_SIZE + sum, Constants.TILE_SIZE + sum);
        }
    }
}
