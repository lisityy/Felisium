package cvut.fel.pjv.pimenol1.utils;

import cvut.fel.pjv.pimenol1.inventorys.*;
import cvut.fel.pjv.pimenol1.main_pjv.Constants;
import cvut.fel.pjv.pimenol1.main_pjv.PlayingPage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class AssetSetter {
    PlayingPage pp;

    public AssetSetter(PlayingPage pp) {
        this.pp = pp;
    }

    public void setObjects() {
        pp.obj[1] = new Wing(1, 23 * Constants.TILE_SIZE, 40 * Constants.TILE_SIZE);

        pp.obj[0] = new Key(0, 23 * Constants.TILE_SIZE, 7 * Constants.TILE_SIZE);

        pp.obj[3] = new Door(3, 10 * Constants.TILE_SIZE, 11 * Constants.TILE_SIZE);

        pp.obj[4] = new Socks(4, 11 * Constants.TILE_SIZE, 9 * Constants.TILE_SIZE);

        pp.obj[5] = new Box(5, 39 * Constants.TILE_SIZE, 11 * Constants.TILE_SIZE);

        pp.obj[6] = new Fish(6, 22 * Constants.TILE_SIZE, 5 * Constants.TILE_SIZE);

        pp.obj[7] = new Valeriana(7, 9 * Constants.TILE_SIZE, 28 * Constants.TILE_SIZE);
    }


}
