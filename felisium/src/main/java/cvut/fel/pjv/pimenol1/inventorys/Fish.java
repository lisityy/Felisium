package cvut.fel.pjv.pimenol1.inventorys;

import cvut.fel.pjv.pimenol1.entity.Player;
import cvut.fel.pjv.pimenol1.utils.MusicPlayer;

public class Fish extends Item {
    private MusicPlayer musicPlayer = new MusicPlayer();

    public Fish(int i, int x, int y) {
        super("fish",i,x,y);
        name = "fish";
        collision = true;
    }

    @Override
    public boolean useItem(Player player){

        player.setLife(player.getLife()+1);
        return true;
    }
}
