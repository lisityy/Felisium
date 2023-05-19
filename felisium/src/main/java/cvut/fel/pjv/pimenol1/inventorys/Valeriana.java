package cvut.fel.pjv.pimenol1.inventorys;

import cvut.fel.pjv.pimenol1.entity.Player;

public class Valeriana extends Item {
    public Valeriana(int i, int x, int y) {
        super("valeriana", i, x, y);
        name = "valeriana";
        collision = true;
    }

    @Override
    public void pickUp(Player player, int inx) {

    }
}
