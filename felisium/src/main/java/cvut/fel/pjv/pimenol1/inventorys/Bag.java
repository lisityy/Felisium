package cvut.fel.pjv.pimenol1.inventorys;

import cvut.fel.pjv.pimenol1.entity.Player;
import cvut.fel.pjv.pimenol1.main.Constants;
import cvut.fel.pjv.pimenol1.main.UI;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Objects;

public class Bag {
    public ArrayList<Item> items = new ArrayList<>();
    public ArrayList<Weapon> weapons = new ArrayList<>();

    private BagCell[] bagCell = new BagCell[5];
    private BagCell[] weaponCell = new BagCell[1];
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

    public boolean addItem(Item item, UI ui) {
        if (items.size() >= 5) {
            ui.writeMessage("Your bag is full!");
            return false;
        }
        items.add(item);
        return true;
    }

    public void addWeapon(Weapon w, UI ui) {
        if (weapons.size() >= 1) {
            ui.writeMessage("You already have weapon!");
            return;
        }
        weapons.add(w);
    }

    public void useItem(int index) {
        if (items.size() > index) {
            boolean used = items.get(index).useItem(player);
            if (used) {
                items.remove(index);
            }
        }
    }


    public void initBagCell() {
        int x = 650;
        int y = 20;

        for (int i = 0; i < bagCell.length; i++) {
            bagCell[i] = new BagCell(x, y, i);
            x += Constants.TILE_SIZE + 10;
        }

        y = y + Constants.TILE_SIZE + 15;
        x -= (Constants.TILE_SIZE + 10);
        for (int i = 0; i < weaponCell.length; i++) {
            weaponCell[i] = new BagCell(x, y, 99);
            x += Constants.TILE_SIZE + 10;
        }
    }

    public void drawBag(Graphics2D g2) {
        for (BagCell bc : bagCell)
            bc.draw(g2);
        for (BagCell weapon : weaponCell) {
            weapon.draw(g2);
        }

        int x = 655;
        int y = 25;
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).use && Objects.equals(items.get(i).name, "wing")) {
                float t = 1 - (float) player.getTimerWing() / 1000;
                g2.setComposite(AlphaComposite.getInstance((AlphaComposite.SRC_OVER), t));
                g2.drawImage(items.get(i).img, x, y, null);
                g2.setComposite(AlphaComposite.getInstance((AlphaComposite.SRC_OVER), 1f));
                if (t == 0) {
                    items.remove(i);
                }
            } else {
                g2.drawImage(items.get(i).img, x, y, null);
                x += Constants.TILE_SIZE + 8;
            }
        }
        g2.drawImage(weapons.get(0).img, 950, y + Constants.TILE_SIZE + 10, null);
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
