package cvut.fel.pjv.pimenol1.main;

import cvut.fel.pjv.pimenol1.utils.Utils;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.InputStream;

public class MainMenuPage implements Page {

    private MenuButton[] buttons = new MenuButton[3];
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
        buttons[0] = new MenuButton(85 * Constants.SCALE, (int) (50 * Constants.SCALE), 0, GameState.NEWGAME);
        buttons[1] = new MenuButton(85 * Constants.SCALE, (int) (80 * Constants.SCALE), 1, GameState.PLAY);
        buttons[2] = new MenuButton(95 * Constants.SCALE, (int) (110 * Constants.SCALE), 2, GameState.WIN);
    }

    public boolean isInButton(MouseEvent e, MenuButton mb) {
        return mb.getHitBox().contains(e.getX(), e.getY());
    }

    @Override
    public void update() {
        for (MenuButton mb : buttons)
            mb.update();
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.setFont(myFont.deriveFont(Font.PLAIN, 150F));
        g2.setColor(new Color(58,0,137));

        g2.drawImage(backgroundImage, 0, 0, null);
        for (MenuButton mb : buttons)
            mb.draw(g2);

        g2.drawString("FELISIUM", 230, 150);
    }

    public void mousePressed(MouseEvent e) {
        for (MenuButton mb : buttons) {
            if (isInButton(e, mb))
                mb.setMousePressed(true);
        }
    }

    public void mouseReleased(MouseEvent e) {
        for (MenuButton mb : buttons) {
            if (isInButton(e, mb)) {
                if (mb.isMousePressed())
                    mb.applyGameState();
                break;
            }
        }
        resetButtons();
    }

    private void resetButtons() {
        for (MenuButton mb : buttons)
            mb.resetBooleans();
    }

    public void mouseMoved(MouseEvent e) {
        for (MenuButton mb : buttons)
            mb.setMouseOver(false);

        for (MenuButton mb : buttons)
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

