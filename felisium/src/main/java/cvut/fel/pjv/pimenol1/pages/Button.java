package cvut.fel.pjv.pimenol1.pages;

import cvut.fel.pjv.pimenol1.main.Constants;
import cvut.fel.pjv.pimenol1.main.Felisium;
import cvut.fel.pjv.pimenol1.main.GameState;
import cvut.fel.pjv.pimenol1.utils.Utils;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * The Button class represents a button element in the game.
 */
public class Button {
    private final int buttonWidth = 290;
    private final int buttonHight = 75;
    private int originButtonWidth;
    private int originButtonHight;
    private int xPos, yPos, rowIndex, index;
    private GameState state;
    private BufferedImage[] images = new BufferedImage[3];
    private boolean mouseOver, mousePressed;
    private Rectangle hitBox;

    /**
     * Initializes a Button object.
     *
     * @param xPos The x-position of the button.
     * @param yPos The y-position of the button.
     * @param rowIndex The index of the button's row.
     * @param nameFile The name of the image file for the button.
     * @param originButtonWidth The original width of the button image.
     * @param originButtonHight The original height of the button image.
     * @param state The game state associated with the button.
     */
    public Button(int xPos, int yPos, int rowIndex, String nameFile, int originButtonWidth, int originButtonHight, GameState state) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.rowIndex = rowIndex;
        this.originButtonHight = originButtonHight;
        this.originButtonWidth = originButtonWidth;
        this.state = state;
        loadButtonImages(nameFile);
        initHitBox();
    }

    /**
     * Initializes the hit box of the button.
     */
    private void initHitBox() {
        this.hitBox = new Rectangle(xPos, yPos, buttonWidth, buttonHight);
    }

    /**
     * Draws the button on the graphics context.
     *
     * @param g2 The Graphics2D object to draw on.
     */
    public void draw(Graphics2D g2) {
        g2.drawImage(images[index], xPos, yPos, buttonWidth, buttonHight, null);
    }

    /**
     * Updates the button's state based on mouse interactions.
     */
    public void update() {
        this.index = 0;
        if (this.mouseOver)
            this.index = 1;
        if (this.mousePressed)
            this.index = 2;
    }

    /**
     * Loads the button images from the image file.
     *
     * @param nameFile The name of the image file.
     */
    private void loadButtonImages(String nameFile) {
        BufferedImage temp = Utils.load_image("button", nameFile);
        for (int i = 0; i < images.length; i++)
            images[i] = temp.getSubimage(i * originButtonWidth, rowIndex * originButtonHight, originButtonWidth, originButtonHight);
    }

    /**
     * Applies the game state associated with the button for the "play" action.
     */
    public void applyGameStatePlay() {
        Constants.gameStatePlay = state;
        Felisium.logger.info("Button " + state + " pressed.");
    }

    /**
     * Applies the game state associated with the button.
     */
    public void applyGameState() {
        Constants.gameState = state;
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
}
