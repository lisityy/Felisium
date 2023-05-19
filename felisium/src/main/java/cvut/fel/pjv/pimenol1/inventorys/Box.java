package cvut.fel.pjv.pimenol1.inventorys;

import cvut.fel.pjv.pimenol1.entity.Player;
import cvut.fel.pjv.pimenol1.utils.MusicPlayer;

public class Box extends Items {

    private MusicPlayer musicPlayer= new MusicPlayer();

    public Box(int i, int x, int y) {
        super("box", i, x, y);
        name="box";
        collision=true;
    }

    @Override
    public void pickUp(Player player,int inx){
        musicPlayer.play("/music/UrrCat.wav");
        musicPlayer.stop();
    }
}