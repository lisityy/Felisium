package cvut.fel.pjv.pimenol1.pages;

import cvut.fel.pjv.pimenol1.main.Constants;
import cvut.fel.pjv.pimenol1.main.Felisium;
import cvut.fel.pjv.pimenol1.main.GameState;
import cvut.fel.pjv.pimenol1.utils.Utils;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.font.FontRenderContext;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.logging.Level;

/**
 * The MainMenuPage class represents the main menu page of the game.
 */
public class MainMenuPage implements Page {

    private cvut.fel.pjv.pimenol1.pages.Button[] buttons = new cvut.fel.pjv.pimenol1.pages.Button[3];
    private BufferedImage backgroundImage;
    Font myFont;

    /**
     * Initializes the MainMenuPage object.
     */
    public MainMenuPage() {
        loadButtons();
        backgroundImage = Utils.load_image("background", "inCloud");
        backgroundImage = Utils.scaleImg(backgroundImage, Constants.SCREEN_WIDTH, Constants.SCREEN_HIGH);

        try {
            InputStream is = getClass().getResourceAsStream("/text/vermirVibe.ttf");
            myFont = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (Exception e) {
            Felisium.logger.log(Level.SEVERE, "Error reading font: " + e.getMessage(), e);
        }
    }

    /**
     * Loads the buttons for the main menu page.
     */
    private void loadButtons() {
        String nameFile="menuButtons";
        buttons[0] = new cvut.fel.pjv.pimenol1.pages.Button(85 * Constants.SCALE, 50 * Constants.SCALE, 0, nameFile, 120, 27, GameState.RESET);
        buttons[1] = new cvut.fel.pjv.pimenol1.pages.Button(85 * Constants.SCALE, 80 * Constants.SCALE, 1, nameFile, 120, 27, GameState.CONTINUE);
        buttons[2] = new cvut.fel.pjv.pimenol1.pages.Button(95 * Constants.SCALE, 110 * Constants.SCALE, 2, nameFile, 120, 27, GameState.EXIT);
        Felisium.logger.info("Button Position: " + buttons[0].getHitBox().getX() + ", " + buttons[0].getHitBox().getY());
        Felisium.logger.info("Button Position: " + buttons[1].getHitBox().getX() + ", " + buttons[0].getHitBox().getY());
        Felisium.logger.info("Button Position: " + buttons[2].getHitBox().getX() + ", " + buttons[0].getHitBox().getY());
    }

    @Override
    public void update() {
        for (cvut.fel.pjv.pimenol1.pages.Button mb : buttons)
            mb.update();
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.setFont(myFont.deriveFont(Font.PLAIN, 150F));
        g2.setColor(new Color(58, 0, 137));

        g2.drawImage(backgroundImage, 0, 0, null);
        for (cvut.fel.pjv.pimenol1.pages.Button mb : buttons)
            mb.draw(g2);

        g2.drawString("FELISIUM", 230, 150);
    }

    /**
     * Checks if the mouse is inside a button.
     *
     * @param e  The MouseEvent object.
     * @param mb The Button object to check against.
     * @return   True if the mouse is inside the button, false otherwise.
     */
    public boolean isInButton(MouseEvent e, cvut.fel.pjv.pimenol1.pages.Button mb) {
        return mb.getHitBox().contains(e.getX(), e.getY());
    }

    /**
     * Handles the mouse pressed event.
     *
     * @param e The MouseEvent object.
     */
    public void mousePressed(MouseEvent e) {
        for (cvut.fel.pjv.pimenol1.pages.Button mb : buttons) {
            if (isInButton(e, mb))
                mb.setMousePressed(true);
        }
    }

    /**
     * Handles the mouse released event.
     *
     * @param e The MouseEvent object.
     */
    public void mouseReleased(MouseEvent e) {
        for (cvut.fel.pjv.pimenol1.pages.Button mb : buttons) {
            if (isInButton(e, mb)) {
                if (mb.isMousePressed())
                    mb.applyGameState();
                break;
            }
        }
        resetButtons();
    }

    /**
     * Resets the button states.
     */
    private void resetButtons() {
        for (cvut.fel.pjv.pimenol1.pages.Button mb : buttons)
            mb.resetBooleans();
    }

    /**
     * Handles the mouse moved event.
     *
     * @param e The MouseEvent object.
     */
    public void mouseMoved(MouseEvent e) {
        for (cvut.fel.pjv.pimenol1.pages.Button mb : buttons)
            mb.setMouseOver(false);

        for (Button mb : buttons)
            if (isInButton(e, mb)) {
                mb.setMouseOver(true);
                break;
            }
    }

}


