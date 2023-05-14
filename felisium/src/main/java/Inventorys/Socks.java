package Inventorys;

import Entity.Player;
import main_pjv.GamePannel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Socks extends Objects {
    public Socks() {
        name = "socks";
        collision = true;
        try {
            img = ImageIO.read(getClass().getResourceAsStream("/objects/socks.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(Graphics2D g2, GamePannel gp){
        int screenX = worldX - gp.player.xWorld + gp.player.xScreen;
        int screenY = worldY - gp.player.yWorld + gp.player.yScreen;

        if (worldX + gp.getTileSize() > gp.player.xWorld - gp.player.xScreen
                && worldX - gp.getTileSize() < gp.player.xWorld + gp.player.xScreen
                && worldY + gp.getTileSize() > gp.player.yWorld - gp.player.yScreen
                && worldY - gp.getTileSize() < gp.player.yWorld + gp.player.yScreen) {
            g2.drawImage(img, screenX, screenY, 2*gp.getTileSize()/3, 2*gp.getTileSize()/3, null);
        }
    }

    @Override
    public void pickUp(Player player, int inx) {
        player.speed += 2;
        player.getGp().obj[inx] = null;
        player.setHasSocks(true);

    }
}