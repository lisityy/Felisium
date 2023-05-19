package cvut.fel.pjv.pimenol1.main;

import cvut.fel.pjv.pimenol1.utils.Utils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MenuButton {
    private final int buttonWidth = 290;
    private final int buttonHight = 75;
    private int xPos, yPos, rowIndex, index;
    private GameState state;
    private BufferedImage[] images = new BufferedImage[3];
    private boolean mouseOver, mousePressed;
    private Rectangle hitBox;

    public MenuButton(int xPos, int yPos, int indexButt, GameState gameState) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.rowIndex = indexButt;
        this.state = gameState;
        loadButtonImages();
        initHitBox();
    }

    private void initHitBox() {
        this.hitBox = new Rectangle(xPos, yPos, buttonWidth, buttonHight);
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(images[index], xPos, yPos, buttonWidth, buttonHight, null);
    }

    public void update() {
        this.index = 0;
        if (this.mouseOver)
            this.index = 1;
        if (this.mousePressed)
            this.index = 2;
    }

    private void loadButtonImages() {
        BufferedImage temp = Utils.load_image("button", "menuButtons");
        for (int i = 0; i < images.length; i++)
            images[i] = temp.getSubimage(i * 120, rowIndex * 27, 120, 27);
    }

    public void applyGameState() {
        Constants.gameState = state;
    }


    public boolean isMouseOver() {
        return this.mouseOver;
    }

    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    public boolean isMousePressed() {
        return this.mousePressed;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

    public Rectangle getHitBox() {
        return hitBox;
    }

    public void resetBooleans() {
        this.mouseOver = false;
        this.mousePressed = false;
    }

    public GameState getState() {
        return state;
    }
}
