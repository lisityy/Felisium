package cvut.fel.pjv.pimenol1.inventorys;

import cvut.fel.pjv.pimenol1.entity.Player;
import cvut.fel.pjv.pimenol1.main.Constants;
import cvut.fel.pjv.pimenol1.main.UI;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Objects;

public class Bag {
    public ArrayList<Item> items = new ArrayList<>();
    BufferedImage tileBug;
    private BagCell[] bagCell = new BagCell[5];
    private Player player;

    public Bag(Player player) {
        initBagCell();
        this.player = player;
    }

    public boolean isInButton(MouseEvent e, BagCell bc) {
        return bc.getHitBox().contains(e.getX(), e.getY());
    }

    public void update() {
        for (BagCell bc : bagCell)
            bc.update();
    }

    public void addItem(Item item, UI ui) {
        if (items.size() >= 5) {
            ui.writeMessage("Your bag is full!");
            return;
        }
        items.add(item);
    }

    public void useItem(int index) {
        if(items.size()>index) {
            boolean used=items.get(index).useItem(player);
            if(used){
                items.remove(index);
            }
        }
    }


    public void initBagCell() {
        int x = 650;
        int y = 50;
        for (int i = 0; i < 5; i++) {
            bagCell[i] = new BagCell(x, y, i);
            x += Constants.TILE_SIZE + 10;
        }
    }

    public void drawBag(Graphics2D g2) {
        for (BagCell bc : bagCell)
            bc.draw(g2);

        int x = 655;
        int y = 55;
        for (Item item : items) {
            g2.drawImage(item.img, x, y, null);
            x += Constants.TILE_SIZE + 8;
        }
    }

    public void mousePressed(MouseEvent e) {
        for (BagCell bc : bagCell) {
            if (isInButton(e, bc))
                bc.setMousePressed(true);
        }
    }

    public void mouseReleased(MouseEvent e) {
        for (BagCell bc : bagCell) {
            if (isInButton(e, bc)) {
                if (bc.isMousePressed())
                    useItem(bc.getNumber());
                break;
            }
        }
        resetButtons();
    }

    private void resetButtons() {
        for (BagCell bc : bagCell)
            bc.resetBooleans();
    }

    public void mouseMoved(MouseEvent e) {
        for (BagCell bc : bagCell)
            bc.setMouseOver(false);

        for (BagCell bc : bagCell)
            if (isInButton(e, bc)) {
                bc.setMouseOver(true);
                break;
            }
    }

}
