package cvut.fel.pjv.pimenol1.inventorys;

import cvut.fel.pjv.pimenol1.entity.Player;

public class Door extends Item {
    public Door(int i, int x, int y) {
        super("door",i,x,y);
        name = "door";
        collision = true;
    }

    @Override
    public void pickUp(Player player, int inx) {
        if (player.getKeyCount() > 0) {
            player.setKeyCount(player.getKeyCount() - 1);
        }
    }
}
