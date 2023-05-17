package cvut.fel.pjv.pimenol1.main_pjv;

import cvut.fel.pjv.pimenol1.inventorys.*;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObjects() {

        gp.obj[1] = new Wing(1, 23 * Constants.TILE_SIZE, 40 * Constants.TILE_SIZE);

        gp.obj[0] = new Key(0, 23 * Constants.TILE_SIZE, 7 * Constants.TILE_SIZE);

        gp.obj[3] = new Door(3, 10 * Constants.TILE_SIZE, 11 * Constants.TILE_SIZE);

        gp.obj[4] = new Socks(4, 11 * Constants.TILE_SIZE, 9 * Constants.TILE_SIZE);

        gp.obj[5] = new Box(5, 39 * Constants.TILE_SIZE, 11 * Constants.TILE_SIZE);

        gp.obj[6] = new Fish(6, 22 * Constants.TILE_SIZE, 5 * Constants.TILE_SIZE);

        gp.obj[7] = new Valeriana(7, 9 * Constants.TILE_SIZE, 28 * Constants.TILE_SIZE);
    }

    public void setObjWorld(int index, int numCol, int numRow) {
        gp.obj[index].worldX = numCol * Constants.TILE_SIZE;
        gp.obj[index].worldY = numRow * Constants.TILE_SIZE;
    }

}
