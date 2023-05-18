package cvut.fel.pjv.pimenol1.inventorys;

import cvut.fel.pjv.pimenol1.entity.Player;
import cvut.fel.pjv.pimenol1.main_pjv.MusicPlayer;

public class Fish extends Items {
    private MusicPlayer musicPlayer = new MusicPlayer();

    public Fish(int i, int x, int y) {
        super("fish",i,x,y);
        name = "fish";
        collision = true;
    }

    @Override
    public void pickUp(Player player, int inx) {
        musicPlayer.play("/music/UrrCat.wav");
        musicPlayer.stop();

    }
}
