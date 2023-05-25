package cvut.fel.pjv.pimenol1.inventorys;

import cvut.fel.pjv.pimenol1.entity.Player;

public class Collar extends Item {
    public Collar(int i, int x, int y) {
        super("collar" , i,x,y);
        name="collar";
        collision=true;
    }
}
