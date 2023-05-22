package cvut.fel.pjv.pimenol1.main;

import cvut.fel.pjv.pimenol1.utils.Utils;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.InputStream;

public class MainMenuPage implements Page {

    private Button[] buttons = new Button[3];
    private BufferedImage backgroundImage;
    Font myFont;

    public MainMenuPage() {
        loadButtons();
        backgroundImage = Utils.load_image("background", "inCloud");
        backgroundImage = Utils.scaleImg(backgroundImage, Constants.SCREEN_WIDTH, Constants.SCREEN_HIGH);

        try {
            InputStream is = getClass().getResourceAsStream("/text/vermirVibe.ttf");
            myFont = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (Exception e) {
            System.out.println("Error read font: " + e.getMessage());
        }
    }

    private void loadButtons() {
        String nameFile="menuButtons";
        buttons[0] = new Button(85 * Constants.SCALE, 50 * Constants.SCALE, 0, nameFile, 120, 27, GameState.RESET);
        buttons[1] = new Button(85 * Constants.SCALE, 80 * Constants.SCALE, 1, nameFile, 120, 27, GameState.CONTINUE);
        buttons[2] = new Button(95 * Constants.SCALE, 110 * Constants.SCALE, 2, nameFile, 120, 27, GameState.EXIT);
    }

    @Override
    public void update() {
        for (Button mb : buttons)
            mb.update();
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.setFont(myFont.deriveFont(Font.PLAIN, 150F));
        g2.setColor(new Color(58, 0, 137));

        g2.drawImage(backgroundImage, 0, 0, null);
        for (Button mb : buttons)
            mb.draw(g2);

        g2.drawString("FELISIUM", 230, 150);
    }

    public boolean isInButton(MouseEvent e, Button mb) {
        return mb.getHitBox().contains(e.getX(), e.getY());
    }

    public void mousePressed(MouseEvent e) {
        for (Button mb : buttons) {
            if (isInButton(e, mb))
                mb.setMousePressed(true);
        }
    }

    public void mouseReleased(MouseEvent e) {
        for (Button mb : buttons) {
            if (isInButton(e, mb)) {
                if (mb.isMousePressed())
                    mb.applyGameState();
                break;
            }
        }
        resetButtons();
    }

    private void resetButtons() {
        for (Button mb : buttons)
            mb.resetBooleans();
    }

    public void mouseMoved(MouseEvent e) {
        for (Button mb : buttons)
            mb.setMouseOver(false);

        for (Button mb : buttons)
            if (isInButton(e, mb)) {
                mb.setMouseOver(true);
                break;
            }
    }

    public void keyPressed(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {

    }

}

