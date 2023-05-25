package cvut.fel.pjv.pimenol1.inventorys;

import cvut.fel.pjv.pimenol1.entity.Player;

public class Valeriana extends Item {
    public Valeriana(int i, int x, int y) {
        super("valeriana", i, x, y);
        name = "valeriana";
        collision = true;
    }

    @Override
    public boolean useItem(Player player){
        player.setLife(player.getLife()+10);
        player.setUseValeriana(true);
        return true;
    }

}
