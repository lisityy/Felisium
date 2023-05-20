package cvut.fel.pjv.pimenol1.entity;

import cvut.fel.pjv.pimenol1.main.PlayingPage;
import cvut.fel.pjv.pimenol1.main.UI;
import cvut.fel.pjv.pimenol1.utils.Utils;

public class NPC_blackCat extends Entity {

    public NPC_blackCat(PlayingPage pp, String path, String name, int x, int y) {
        super(name, pp);
        this.xWorld = x;
        this.yWorld = y;
        direction = "up";
        collitionOn = true;
        sizeSubImg = 16;

        getEntityImg(path);
    }

}
