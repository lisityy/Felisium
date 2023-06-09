package cvut.fel.pjv.pimenol1.inventorys;

import cvut.fel.pjv.pimenol1.entity.Player;
import cvut.fel.pjv.pimenol1.utils.MusicPlayer;

public class Wing extends Item {

    private MusicPlayer musicPlayer = new MusicPlayer();

    public Wing(int i, int x, int y) {
        super("wing", i, x, y);
        name = "wing";
        collision = true;
    }

    @Override
    public boolean useItem(Player player) {
        player.setHaveWing(true);
        use = true;
        return false;
    }


}
