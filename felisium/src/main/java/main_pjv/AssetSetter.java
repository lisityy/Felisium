package main_pjv;

import Objects.*;

public class AssetSetter {
    GamePannel gp;

    public AssetSetter(GamePannel gp) {
        this.gp = gp;
    }

    public void setObjects() {
//        gp.obj[0] = new Wing();
//        setObjWorld(0,23,7);

        gp.obj[1] =new Wing();
        setObjWorld(1,23,40);

        gp.obj[0] = new Key();
        setObjWorld(0,23, 7);

        gp.obj[3] = new Door();
        setObjWorld(3,23, 10);
    }

    public void setObjWorld(int index, int numCol, int numRow) {
        gp.obj[index].worldX = numCol * gp.getTileSize();
        gp.obj[index].worldY = numRow * gp.getTileSize();
    }

}
