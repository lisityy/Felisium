package cvut.fel.pjv.pimenol1.utils;

import cvut.fel.pjv.pimenol1.aliens.Alien;
import cvut.fel.pjv.pimenol1.entity.NPC_blackCat;
import cvut.fel.pjv.pimenol1.entity.NPC_catan;
import cvut.fel.pjv.pimenol1.entity.NPC_queenCat;
import cvut.fel.pjv.pimenol1.inventorys.*;
import cvut.fel.pjv.pimenol1.main.Constants;
import cvut.fel.pjv.pimenol1.main.PlayingPage;

public class AssetSetter  {
    PlayingPage pp;

    public AssetSetter(PlayingPage pp) {
        this.pp = pp;
    }

    public void setObjects() {
        pp.obj[1] = new Wing(1, 24 * Constants.TILE_SIZE, 40 * Constants.TILE_SIZE);

        pp.obj[0] = new Key(0, 31 * Constants.TILE_SIZE, 16 * Constants.TILE_SIZE);

        pp.obj[3] = new Door(3, 13 * Constants.TILE_SIZE, 23 * Constants.TILE_SIZE);

        pp.obj[4] = new Socks(4, 11 * Constants.TILE_SIZE, 9 * Constants.TILE_SIZE);


        pp.obj[6] = new Fish(6, 22 * Constants.TILE_SIZE, 7 * Constants.TILE_SIZE);

        pp.obj[7] = new Valeriana(7, 22 * Constants.TILE_SIZE, 28 * Constants.TILE_SIZE);

        pp.obj[5] = new Box(5, 39 * Constants.TILE_SIZE, 11 * Constants.TILE_SIZE);
        pp.obj[8] = new Box(8, 39 * Constants.TILE_SIZE, 15 * Constants.TILE_SIZE);
        pp.obj[9] = new Box(9, 30 * Constants.TILE_SIZE, 29 * Constants.TILE_SIZE);
        pp.obj[10] = new Box(10, 10 * Constants.TILE_SIZE, 39 * Constants.TILE_SIZE);

    }

    public void setNPC() {
        pp.npc[1] = new NPC_catan(pp, "NPC_catan", "catan", 22 * Constants.TILE_SIZE, 40 * Constants.TILE_SIZE);
        pp.npc[2] = new NPC_catan(pp, "NPC_catan", "catan", 37 * Constants.TILE_SIZE, 8 * Constants.TILE_SIZE);
        pp.npc[3] = new NPC_catan(pp, "NPC_catan", "catan", 20 * Constants.TILE_SIZE, 7 * Constants.TILE_SIZE);
        pp.npc[4] = new NPC_queenCat(pp, "queenCat", "queenCat", 11 * Constants.TILE_SIZE, 7 * Constants.TILE_SIZE);
    }

    public void setAliens() {
        pp.getAliens()[0] = new Alien("enemy", "enemyCalm", 23 * Constants.TILE_SIZE, 38 * Constants.TILE_SIZE, pp);
        pp.getAliens()[1] = new Alien("enemy", "enemyCalm", 22 * Constants.TILE_SIZE, 35 * Constants.TILE_SIZE, pp);

        pp.getAliens()[2] = new Alien("enemy", "enemyCalm", 37 * Constants.TILE_SIZE, 9 * Constants.TILE_SIZE, pp);
        pp.getAliens()[3] = new Alien("enemy", "enemyCalm", 36 * Constants.TILE_SIZE, 11 * Constants.TILE_SIZE, pp);

        pp.getAliens()[4] = new Alien("enemy", "enemyCalm", 22 * Constants.TILE_SIZE, 8 * Constants.TILE_SIZE, pp);
        pp.getAliens()[5] = new Alien("enemy", "enemyCalm", 22 * Constants.TILE_SIZE, 11 * Constants.TILE_SIZE, pp);

        pp.getAliens()[6] = new Alien("enemy", "enemyCalm", 12 * Constants.TILE_SIZE, 9 * Constants.TILE_SIZE, pp);
        pp.getAliens()[7] = new Alien("enemy", "enemyCalm", 12 * Constants.TILE_SIZE, 10 * Constants.TILE_SIZE, pp);
    }
}
