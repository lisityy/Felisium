package cvut.fel.pjv.pimenol1.inventorys;

import cvut.fel.pjv.pimenol1.entity.Player;
import cvut.fel.pjv.pimenol1.utils.MusicPlayer;

public class Key extends Item {

    private MusicPlayer musicPlayer = new MusicPlayer();

    public Key(int i, int x, int y) {
        super("key",i,x,y);
        name = "key";
        collision = true;
    }

    @Override
    public boolean useItem(Player player){
        if(player.isHitDoor()){
            player.setHitDoor(false);
            player.getPp().obj[player.getIndexDoor()]=null;
            return true;
        }
        return false;
    }
}
