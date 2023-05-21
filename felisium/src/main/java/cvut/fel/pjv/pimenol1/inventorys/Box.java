package cvut.fel.pjv.pimenol1.inventorys;

import cvut.fel.pjv.pimenol1.entity.Player;
import cvut.fel.pjv.pimenol1.utils.MusicPlayer;

public class Box extends Item {

    private MusicPlayer musicPlayer = new MusicPlayer();

    public Box(int i, int x, int y) {
        super("box", i, x, y);
        name = "box";
        collision = true;
    }

    @Override
    public boolean useItem(Player player) {
        if (player.isHitCat()) {
            player.setHitCat(false);
            player.getPp().obj[this.index] = new Box(this.index, player.getPp().npc[player.getIndexCat()].xWorld, player.getPp().npc[player.getIndexCat()].yWorld);
            player.getPp().obj[this.index].canTake=false;
            player.getPp().npc[player.getIndexCat()] = null;
            return true;
        }
        return false;
    }


}