package cvut.fel.pjv.pimenol1.inventorys;

import cvut.fel.pjv.pimenol1.entity.Player;
import cvut.fel.pjv.pimenol1.main_pjv.Constants;
import cvut.fel.pjv.pimenol1.main_pjv.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Door extends Objects {
    public Door(int i, int x, int y) {
        super("door",i,x,y);
        name = "door";
        collision = true;
    }

    @Override
    public void pickUp(Player player, int inx) {
        if (player.getKeyCount() > 0) {
            player.getGp().obj[inx] = null;
            player.setKeyCount(player.getKeyCount() - 1);
        }
    }
}
