package cvut.fel.pjv.pimenol1.inventorys;

import cvut.fel.pjv.pimenol1.entity.Player;
import cvut.fel.pjv.pimenol1.main_pjv.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Valeriana extends Objects {
    public Valeriana(int i, int x, int y) {
        super("valeriana", i, x, y);
        name = "valeriana";
        collision = true;
    }

    @Override
    public void pickUp(Player player, int inx) {

    }
}
