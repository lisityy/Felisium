package cvut.fel.pjv.pimenol1.inventorys;

import cvut.fel.pjv.pimenol1.entity.Player;
import cvut.fel.pjv.pimenol1.main.Constants;

public class Socks extends Item {
    public Socks(int i, int x, int y) {
        super("socks",i,x,y);
        name = "socks";
        collision = true;
//        img = utils.scaleImg(img, 2 * Constants.TILE_SIZE / 3, 2 * Constants.TILE_SIZE / 3);
    }

    @Override
    public boolean useItem(Player player) {
        player.speed += 2;
        player.setHasSocks(true);
        return true;
    }
}