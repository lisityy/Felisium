package Inventorys;

import Entity.Player;
import main_pjv.GamePannel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Door extends Objects {
    public Door(GamePannel gp) {
        name = "door";
        collision = true;
        try {
            img = ImageIO.read(getClass().getResourceAsStream("/objects/doorMain.png"));
            img = utils.scaleImg(img, gp.getTileSize(), gp.getTileSize());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void pickUp(Player player, int inx) {
        if (player.getKeyCount() > 0) {
            player.getGp().obj[inx] = null;
            player.setKeyCount(player.getKeyCount() - 1);
        }
    }
}
