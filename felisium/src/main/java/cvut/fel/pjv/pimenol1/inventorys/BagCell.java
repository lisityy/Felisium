package cvut.fel.pjv.pimenol1.inventorys;

import cvut.fel.pjv.pimenol1.main.Constants;
import cvut.fel.pjv.pimenol1.utils.Utils;

import java.awt.*;
import java.awt.image.BufferedImage;


public class BagCell  {

    private final int cellWidth = Constants.TILE_SIZE + 10;
    private final int callHeight = Constants.TILE_SIZE + 10;

    private int xPos, yPos, number, index;
    private BufferedImage[] images = new BufferedImage[3];
    private boolean mouseOver, mousePressed;
    private Rectangle hitBox;

    /**
     * Constructs a BagCell object with the specified parameters.
     *
     * @param xPos The x-coordinate of the BagCell in screen.
     * @param yPos The y-coordinate of the BagCell in screen.
     * @param indexCell The index of the BagCell in bag array.
     */
    public BagCell(int xPos, int yPos, int indexCell) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.number = indexCell;
        loadButtonImages();
        initHitBox();
    }

    /**
     * Initializes the hitbox of the BagCell with using x position and y position.
     */
    private void initHitBox() {
        this.hitBox = new Rectangle(xPos, yPos, cellWidth, callHeight);
    }

    /**
     * Draws the BagCell on the screen.
     *
     * @param g2 The Graphics2D object to draw the BagCell.
     */
    public void draw(Graphics2D g2) {
        if (this.number == 99) {
            g2.drawImage(images[2], xPos, yPos, cellWidth, callHeight, null);
        } else {
            g2.drawImage(images[index], xPos, yPos, cellWidth, callHeight, null);
        }
    }

    /**
     * Updates the state of the BagCell.
     */
    public void update() {
        this.index = 0;
        if (this.mouseOver) {
            this.index = 1;
        }
        if (this.mousePressed) {
            this.index = 1;
        }
    }

    /**
     * Loads the images for the BagCell.
     */
    private void loadButtonImages() {
        images[0] = Utils.load_image("tiles", "blueTile");
        images[1] = Utils.load_image("tiles", "blueTilePressed");
        images[2] = Utils.load_image("tiles", "blueTile2");

        images[0] = Utils.scaleImg(images[0], Constants.TILE_SIZE + 10, Constants.TILE_SIZE + 10);
        images[1] = Utils.scaleImg(images[1], Constants.TILE_SIZE + 10, Constants.TILE_SIZE + 10);
        images[2] = Utils.scaleImg(images[2], Constants.TILE_SIZE + 10, Constants.TILE_SIZE + 10);
    }


    public int getNumber() {
        return number;
    }

    /**
     * Resets the boolean flags of the BagCell.
     */
    public void resetBooleans() {
        this.mouseOver = false;
        this.mousePressed = false;
    }


    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }


    public boolean isMousePressed() {
        return mousePressed;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

    public Rectangle getHitBox() {
        return hitBox;
    }
}

