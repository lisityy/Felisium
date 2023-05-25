package cvut.fel.pjv.pimenol1.inventorys;

import cvut.fel.pjv.pimenol1.entity.Player;
import cvut.fel.pjv.pimenol1.main.Constants;
import cvut.fel.pjv.pimenol1.pages.UI;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Bag implements Serializable {

    public ArrayList<Item> items = new ArrayList<>();
    public ArrayList<Weapon> weapons = new ArrayList<>();

    private final BagCell[] bagCell = new BagCell[5];
    private final BagCell[] weaponCell = new BagCell[1];
    private final Player player;

    public Bag(Player player) {
        initBagCell();
        this.player = player;
    }

    /**
     * Checks if the mouse event is within the bounds of the specified BagCell.
     *
     * @param e  the MouseEvent
     * @param bc the BagCell to check
     * @return true if the MouseEvent is within the bounds of the BagCell, false otherwise
     */
    public boolean isInButton(MouseEvent e, BagCell bc) {
        return bc.getHitBox().contains(e.getX(), e.getY());
    }

    /**
     * Updates the Bag, including updating all the BagCells.
     */
    public void update() {
        for (BagCell bc : bagCell)
            bc.update();
    }

    /**
     * Adds an item to the Bag.
     *
     * @param item the item to add
     * @param ui   the UI object to display messages
     * @return true if the item was successfully added, false if the Bag is full
     */
    public boolean addItem(Item item, UI ui) {
        if (items.size() >= 5) {
            ui.writeMessage("Your bag is full!");
            return false;
        }
        items.add(item);
        return true;
    }

    /**
     * Adds a weapon to the Bag.
     *
     * @param w  the weapon to add
     * @param ui the UI object to display messages
     */
    public void addWeapon(Weapon w, UI ui) {
        if (weapons.size() >= 1) {
            ui.writeMessage("You already have a weapon!");
            return;
        }
        weapons.add(w);
    }

    /**
     * Uses the item at the specified index in the Bag.
     *
     * @param index the index of the item to use
     */
    public void useItem(int index) {
        if (items.size() > index) {
            boolean used = items.get(index).useItem(player);
            if (used) {
                items.remove(index);
            }
        }
    }

    /**
     * Initializes the BagCell objects in the Bag.
     */
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

    /**
     * Draws the Bag, including all the BagCells and items.
     *
     * @param g2 the Graphics2D object to draw on
     */
    public void drawBag(Graphics2D g2) {
        for (BagCell bc : bagCell)
            bc.draw(g2);
        for (BagCell weapon : weaponCell) {
            weapon.draw(g2);
        }

        int x = 655;
        int y = 25;
        for (int i = 0; i < items.size(); i++) {
            // using wings
            if (items.get(i).use && Objects.equals(items.get(i).name, "wing")) {
                float t = 1 - (float) player.getTimerWing() / 1000;
                g2.setComposite(AlphaComposite.getInstance((AlphaComposite.SRC_OVER), t));
                g2.drawImage(items.get(i).img, x, y, null);
                g2.setComposite(AlphaComposite.getInstance((AlphaComposite.SRC_OVER), 1f));
                x += Constants.TILE_SIZE + 8;
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

    /**
     * Handles the mousePressed event.
     *
     * @param e the MouseEvent
     */
    public void mousePressed(MouseEvent e) {
        for (BagCell bc : bagCell) {
            if (isInButton(e, bc))
                bc.setMousePressed(true);
        }
    }

    /**
     * Handles the mouseReleased event.
     *
     * @param e the MouseEvent
     */
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

    /**
     * Resets the mouse button states for all BagCells.
     */
    private void resetButtons() {
        for (BagCell bc : bagCell)
            bc.resetBooleans();
    }

    /**
     * Handles the mouseMoved event.
     *
     * @param e the MouseEvent
     */
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
