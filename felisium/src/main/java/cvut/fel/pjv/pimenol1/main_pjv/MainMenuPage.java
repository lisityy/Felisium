package cvut.fel.pjv.pimenol1.main_pjv;

import cvut.fel.pjv.pimenol1.utils.Utils;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class MainMenuPage implements Page {

    private MenuButton[] buttons = new MenuButton[3];
    private BufferedImage backgroundImage;
    private int menuX, menuY, menuWidth, menuHeight;

    public MainMenuPage() {
        loadButtons();
        backgroundImage = Utils.load_image("background", "inCloud");
        backgroundImage = Utils.scaleImg(backgroundImage, Constants.SCREEN_WIDTH, Constants.SCREEN_HIGH);
    }

    private void loadButtons() {
        buttons[0] = new MenuButton(35 * Constants.SCALE, (int) (40 * Constants.SCALE), 0, GameState.PLAY);
        buttons[1] = new MenuButton(35 * Constants.SCALE, (int) (70 * Constants.SCALE), 1, GameState.PLAY);
        buttons[2] = new MenuButton(46 * Constants.SCALE, (int) (100 * Constants.SCALE), 2, GameState.WIN);
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
        g2.drawImage(backgroundImage, 0, 0, null);
        for (MenuButton mb : buttons)
            mb.draw(g2);
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
                System.out.println("Hit");
                mb.setMouseOver(true);
                break;
            }
    }

    public void keyPressed(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {

    }

}

