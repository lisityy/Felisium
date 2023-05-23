package cvut.fel.pjv.pimenol1.pages;

import cvut.fel.pjv.pimenol1.main.Constants;
import cvut.fel.pjv.pimenol1.main.Felisium;
import cvut.fel.pjv.pimenol1.main.GameState;
import cvut.fel.pjv.pimenol1.utils.Utils;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class UI {

    private Font myFont;
    private String message = "";
    private boolean haveMessage = false;
    private int messageTimer = 0;
    private BufferedImage heart;
    private BufferedImage cat;
    private int countHeart = 9;
    private int catLeft = 4;
    private BufferedImage backgroundImg;
    private Button[] buttonUi = new Button[5];

    public UI() {
        try {
            // Load font
            InputStream is = getClass().getResourceAsStream("/text/vermirVibe.ttf");
            myFont = Font.createFont(Font.TRUETYPE_FONT, is);

            // Load images
            heart = Utils.load_image("objects", "heart");
            heart = Utils.scaleImg(heart, Constants.TILE_SIZE, Constants.TILE_SIZE);

            cat = Utils.load_image("ui_obj", "cat");
            cat = Utils.scaleImg(cat, Constants.TILE_SIZE, Constants.TILE_SIZE);

            backgroundImg = ImageIO.read(getClass().getResourceAsStream("/background/gameOver.png"));
            loadButtons();
        } catch (Exception e) {
            Felisium.logger.log(Level.SEVERE, "Error loading resources: " + e.getMessage(), e);
        }
    }

    // Load UI buttons
    private void loadButtons() {
        buttonUi[0] = new Button(250, 500, 0, "pauseButtons", 120, 27, GameState.MAINMENU);
        buttonUi[1] = new Button(550, 500, 1, "pauseButtons", 120, 27, GameState.RESET);
        buttonUi[2] = new Button(400, 600, 2, "pauseButtons", 120, 27, GameState.SAVE);

        buttonUi[3] = new Button(100, 400, 0, "pauseButtons", 120, 27, GameState.MAINMENU);
        buttonUi[4] = new Button(100, 500, 1, "pauseButtons", 120, 27, GameState.RESET);
    }

    // Set a message to display on the screen
    public void writeMessage(String text) {
        this.message = text;
        haveMessage = true;
    }

    // Draw the UI during gameplay
    public void drawGame(Graphics2D g2) {
        g2.setFont(myFont.deriveFont(Font.PLAIN, 50F));
        g2.setColor(new Color(58, 0, 137));

        if (haveMessage) {
            g2.drawString(message, 100, 100);
            messageTimer++;
            if (messageTimer > 200) {
                haveMessage = false;
                messageTimer = 0;
            }
        }

        drawLife(g2, countHeart);
        drawCatLeft(g2, catLeft);
    }

    // Draw the remaining cat lives
    private void drawCatLeft(Graphics2D g2, int catLeft) {
        int x = 30;
        int y = 70 * (int) (countHeart / 12 + 1);
        int j = 0;
        for (int i = 0; i < catLeft; i++) {
            j++;
            g2.drawImage(cat, x, y, null);
            x += Constants.TILE_SIZE / 2;
            if (j > 12) {
                y += Constants.TILE_SIZE;
                x = 30;
                j = 0;
            }
        }
    }

    // Draw the player's remaining lives
    private void drawLife(Graphics2D g2, int life) {
        int x = 30;
        int y = 30;
        int j = 0;
        for (int i = 0; i < life; i++) {
            j++;
            g2.drawImage(heart, x, y, null);
            x += Constants.TILE_SIZE / 2;
            if (j > 12) {
                y += Constants.TILE_SIZE;
                x = 30;
                j = 0;
            }
        }
    }

    // Draw the pause screen
    public void drawPause(Graphics2D g2) {
        g2.setComposite(AlphaComposite.getInstance((AlphaComposite.SRC_OVER), 0.3f));
        g2.drawImage(backgroundImg, 0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HIGH, null);
        g2.setComposite(AlphaComposite.getInstance((AlphaComposite.SRC_OVER), 1f));

        g2.setFont(myFont.deriveFont(Font.PLAIN, 180F));
        g2.setColor(Color.WHITE);

        for (int i = 0; i < 3; i++)
            buttonUi[i].draw(g2);

        g2.drawString("PAUSE", 270, 200);
        g2.setFont(myFont.deriveFont(Font.PLAIN, 30F));
        g2.drawString("press ESC for continue", 360, 250);
    }

    // Draw the game over screen
    public void drawGameOver(Graphics2D g2) {
        g2.setComposite(AlphaComposite.getInstance((AlphaComposite.SRC_OVER), 0.3f));
        g2.drawImage(backgroundImg, 0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HIGH, null);
        g2.setComposite(AlphaComposite.getInstance((AlphaComposite.SRC_OVER), 1f));

        g2.setFont(myFont.deriveFont(Font.PLAIN, 180F));
        g2.setColor(Color.WHITE);

        for (int i = 0; i < 3; i++)
            buttonUi[i].draw(g2);

        g2.drawString("GAME", 320, 200);
        g2.drawString("OVER", 320, 350);
    }

    // Draw the win screen
    public void drawWin(Graphics2D g2) {
        try {
            BufferedImage pauseImg = ImageIO.read(getClass().getResourceAsStream("/background/catsArm.png"));
            g2.drawImage(pauseImg, 0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HIGH, null);
        } catch (Exception e) {
            Felisium.logger.log(Level.SEVERE, "Problem with reading pause img: " + e.getMessage(), e);
        }
        g2.setFont(myFont.deriveFont(Font.PLAIN, 180F));

        buttonUi[3].draw(g2);
        buttonUi[4].draw(g2);

        g2.drawString("YOU", 80, 150);
        g2.drawString("WIN", 110, 270);
    }

    // Update the button states
    public void updateButton() {
        for (Button button : buttonUi)
            button.update();
    }

    // Check if the mouse is inside a button
    public boolean isInButton(MouseEvent e, Button mb) {
        return mb.getHitBox().contains(e.getX(), e.getY());
    }

    // Handle mouse pressed event
    public void mousePressed(MouseEvent e) {
        for (Button b : buttonUi) {
            if (isInButton(e, b))
                b.setMousePressed(true);
        }
    }

    // Handle mouse released event
    public void mouseReleased(MouseEvent e) {
        for (Button b : buttonUi) {
            if (isInButton(e, b)) {
                if (b.isMousePressed())
                    b.applyGameStatePlay();
                break;
            }
        }
        resetButtons();
    }

    // Reset button states
    private void resetButtons() {
        for (Button b : buttonUi)
            b.resetBooleans();
    }

    // Handle mouse moved event
    public void mouseMoved(MouseEvent e) {
        for (Button b : buttonUi)
            b.setMouseOver(false);

        for (Button b : buttonUi)
            if (isInButton(e, b)) {
                b.setMouseOver(true);
                break;
            }
    }

    // Getter and setter methods
    public int getCountHeart() {
        return countHeart;
    }

    public void setCountHeart(int countHeart) {
        this.countHeart = countHeart;
    }

    public void setCatLeft(int catLeft) {
        this.catLeft = catLeft;
    }

    public int getCatLeft() {
        return catLeft;
    }
}

