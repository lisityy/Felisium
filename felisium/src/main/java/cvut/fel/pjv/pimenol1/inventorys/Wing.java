package cvut.fel.pjv.pimenol1.inventorys;

import cvut.fel.pjv.pimenol1.entity.Player;
import cvut.fel.pjv.pimenol1.main_pjv.GamePanel;
import cvut.fel.pjv.pimenol1.main_pjv.MusicPlayer;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Wing extends Objects {

    private MusicPlayer musicPlayer = new MusicPlayer();

    public Wing(int i, int x, int y) {
        super("wing", i, x, y);
        name = "wing";
        collision = true;
    }

    @Override
    public void pickUp(Player player, int inx) {
        musicPlayer.play("/music/UrrCat.wav");
        musicPlayer.stop();
        player.getGp().obj[inx] = null;
        player.setHasWings(true);
        player.getGp().getUi().setGameFinished(true);
    }

}
