package cvut.fel.pjv.pimenol1.inventorys;

import cvut.fel.pjv.pimenol1.entity.Player;
import cvut.fel.pjv.pimenol1.main_pjv.Constants;
import cvut.fel.pjv.pimenol1.main_pjv.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Collar extends Objects{
    public Collar(int i, int x, int y) {
        super("collar" , i,x,y);
        name="collar";
        collision=true;
    }

    @Override
    public void pickUp(Player player,int inx){

    }
}
