package cvut.fel.pjv.pimenol1.inventorys;

import cvut.fel.pjv.pimenol1.entity.Player;
import cvut.fel.pjv.pimenol1.utils.MusicPlayer;

public class Heart extends Item{

    private MusicPlayer musicPlayer= new MusicPlayer();

    public Heart(int i, int x, int y) {
        super("heart", i, x, y);
        name="heart";
        collision=true;
    }


}
