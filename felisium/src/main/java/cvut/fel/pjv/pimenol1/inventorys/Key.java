package cvut.fel.pjv.pimenol1.inventorys;

import cvut.fel.pjv.pimenol1.entity.Player;
import cvut.fel.pjv.pimenol1.main_pjv.MusicPlayer;

public class Key extends Items {

    private MusicPlayer musicPlayer = new MusicPlayer();

    public Key(int i, int x, int y) {
        super("key",i,x,y);
        name = "key";
        collision = true;
    }

    @Override
    public void pickUp(Player player, int inx) {
        musicPlayer.play("/music/UrrCat.wav");
        musicPlayer.stop();
        player.setKeyCount(player.getKeyCount() + 1);
    }
}
