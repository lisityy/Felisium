package cvut.fel.pjv.pimenol1.inventorys;

import cvut.fel.pjv.pimenol1.entity.Player;
import cvut.fel.pjv.pimenol1.main_pjv.Constants;
import cvut.fel.pjv.pimenol1.main_pjv.GamePanel;
import cvut.fel.pjv.pimenol1.main_pjv.MusicPlayer;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Key extends Objects {

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
        player.getGp().obj[inx] = null;
        player.setKeyCount(player.getKeyCount() + 1);
        player.getGp().getUi().writeMessage("You got a key!");
    }
}
