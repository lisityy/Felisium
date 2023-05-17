package cvut.fel.pjv.pimenol1.inventorys;

import cvut.fel.pjv.pimenol1.entity.Player;
import cvut.fel.pjv.pimenol1.main_pjv.Constants;
import cvut.fel.pjv.pimenol1.main_pjv.GamePanel;
import cvut.fel.pjv.pimenol1.main_pjv.MusicPlayer;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Fish extends Objects {
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
        player.getGp().obj[inx] = null;
    }
}
