package cvut.fel.pjv.pimenol1.inventorys;

import cvut.fel.pjv.pimenol1.entity.Player;

public class Door extends Item {
    public Door(int i, int x, int y) {
        super("door",i,x,y);
        name = "door";
        collision = true;
        canTake=false;
    }

}
