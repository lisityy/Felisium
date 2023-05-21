package cvut.fel.pjv.pimenol1.inventorys;

import cvut.fel.pjv.pimenol1.main.Constants;
import cvut.fel.pjv.pimenol1.utils.Utils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BagCell {

    private final int cellWidth = Constants.TILE_SIZE + 10;
    private final int callHight = Constants.TILE_SIZE + 10;

    private int xPos, yPos, number, index;
    private BufferedImage[] images = new BufferedImage[3];
    private boolean mouseOver, mousePressed;
    private Rectangle hitBox;

    public BagCell(int xPos, int yPos, int indexCell) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.number = indexCell;
        loadButtonImages();
        initHitBox();
    }

    private void initHitBox() {
        this.hitBox = new Rectangle(xPos, yPos, cellWidth, callHight);
    }

    public void draw(Graphics2D g2) {
        if(this.number == 99){
            g2.drawImage(images[2], xPos, yPos, cellWidth, callHight, null);
        } else {
            g2.drawImage(images[index], xPos, yPos, cellWidth, callHight, null);
        }
    }

    public void update() {
        this.index = 0;
        if (this.mouseOver)
            this.index = 1;
        if (this.mousePressed)
            this.index = 1;
    }

    private void loadButtonImages() {
        images[0] = Utils.load_image("tiles", "blueTile");
        images[1] = Utils.load_image("tiles", "blueTilePressed");
        images[2] = Utils.load_image("tiles", "blueTile2");

        images[0] = Utils.scaleImg(images[0], Constants.TILE_SIZE + 10, Constants.TILE_SIZE + 10);
        images[1] = Utils.scaleImg(images[1], Constants.TILE_SIZE + 10, Constants.TILE_SIZE + 10);
        images[2] = Utils.scaleImg(images[2], Constants.TILE_SIZE + 10, Constants.TILE_SIZE + 10);
    }

    public int getCellWidth() {
        return cellWidth;
    }

    public int getCallHight() {
        return callHight;
    }

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void resetBooleans() {
        this.mouseOver = false;
        this.mousePressed = false;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public BufferedImage[] getImages() {
        return images;
    }

    public void setImages(BufferedImage[] images) {
        this.images = images;
    }

    public boolean isMouseOver() {
        return mouseOver;
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

    public void setHitBox(Rectangle hitBox) {
        this.hitBox = hitBox;
    }
}
