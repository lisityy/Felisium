package main_pjv;

import Inventorys.*;

public class AssetSetter {
    GamePannel gp;

    public AssetSetter(GamePannel gp) {
        this.gp = gp;
    }

    public void setObjects() {
//        gp.obj[0] = new Wing();
//        setObjWorld(0,23,7);

        gp.obj[1] =new Wing(gp);
        setObjWorld(1,23,40);

        gp.obj[0] = new Key(gp);
        setObjWorld(0,23, 7);

        gp.obj[3] = new Door(gp);
        setObjWorld(3,10, 11);

        gp.obj[4]= new Socks(gp);
        setObjWorld(4,11,9);

        gp.obj[5]= new Box(gp);
        setObjWorld(5,39,11);

        gp.obj[6]= new Fish(gp);
        setObjWorld(6,22,5);

        gp.obj[7]= new Valeriana(gp);
        setObjWorld(7,9,28);
    }

    public void setObjWorld(int index, int numCol, int numRow) {
        gp.obj[index].worldX = numCol * gp.getTileSize();
        gp.obj[index].worldY = numRow * gp.getTileSize();
    }

}
