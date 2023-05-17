package cvut.fel.pjv.pimenol1.inventorys;

import cvut.fel.pjv.pimenol1.entity.Player;
import cvut.fel.pjv.pimenol1.main_pjv.Constants;
import cvut.fel.pjv.pimenol1.main_pjv.GamePanel;

import javax.imageio.ImageIO;
import java.io.Console;
import java.io.IOException;

public class Socks extends Objects {
    public Socks(int i, int x, int y) {
        super("socks",i,x,y);
        name = "socks";
        collision = true;
        img = utils.scaleImg(img, 2 * Constants.TILE_SIZE / 3, 2 * Constants.TILE_SIZE / 3);
    }

    @Override
    public void pickUp(Player player, int inx) {
        player.speed += 2;
        player.getGp().obj[inx] = null;
        player.setHasSocks(true);

    }
}