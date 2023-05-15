package Inventorys;

import Entity.Player;
import main_pjv.GamePannel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Socks extends Objects {
    public Socks(GamePannel gp) {
        name = "socks";
        collision = true;
        try {
            img = ImageIO.read(getClass().getResourceAsStream("/objects/socks.png"));
            img=utils.scaleImg(img,2*gp.getTileSize()/3,2*gp.getTileSize()/3);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void pickUp(Player player, int inx) {
        player.speed += 2;
        player.getGp().obj[inx] = null;
        player.setHasSocks(true);

    }
}