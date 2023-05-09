package main_pjv;

import Objects.*;

public class AssetSetter {
    GamePannel gp;

    public AssetSetter(GamePannel gp) {
        this.gp = gp;
    }

    public void setObjects() {
        gp.obj[0] = new Wing();
        setObjWorld(0,23,7);

        gp.obj[1] =new Wing();
        setObjWorld(1,23,40);
    }

    public void setObjWorld(int index, int numCol, int numRow) {
        gp.obj[index].worldX = numCol * gp.getTileSize();
        gp.obj[index].worldY = numRow * gp.getTileSize();
    }

}
