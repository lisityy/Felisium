package cvut.fel.pjv.pimenol1.pages;

import cvut.fel.pjv.pimenol1.main.Constants;
import cvut.fel.pjv.pimenol1.main.GameState;
import cvut.fel.pjv.pimenol1.pages.Button;
import cvut.fel.pjv.pimenol1.utils.Utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.InputStream;

public class UI {

    Font myFont;
    String message = "";
    boolean haveMessage = false;
    int MessageTimer = 0;
    private double gameTime = 0;
    BufferedImage heart;
    BufferedImage cat;
    private int countHeart = 9;
    private int catLeft=4;
    BufferedImage backgroundImg;
    cvut.fel.pjv.pimenol1.pages.Button[] buttonUi = new cvut.fel.pjv.pimenol1.pages.Button[5];

    public UI() {
        try {
            InputStream is = getClass().getResourceAsStream("/text/vermirVibe.ttf");
            myFont = Font.createFont(Font.TRUETYPE_FONT, is);

            heart = Utils.load_image("objects", "heart");
            heart = Utils.scaleImg(heart, Constants.TILE_SIZE, Constants.TILE_SIZE);

            cat = Utils.load_image("ui_obj","cat");
            cat=Utils.scaleImg(cat, Constants.TILE_SIZE, Constants.TILE_SIZE);

            backgroundImg = ImageIO.read(getClass().getResourceAsStream("/background/gameOver.png"));
            loadButtons();

        } catch (Exception e) {
            System.out.println("Error read font: " + e.getMessage());
        }
    }

    private void loadButtons() {
        buttonUi[0] = new cvut.fel.pjv.pimenol1.pages.Button(250, 500, 0, "pauseButtons", 120, 27, GameState.MAINMENU);
        buttonUi[1] = new cvut.fel.pjv.pimenol1.pages.Button(550, 500, 1, "pauseButtons", 120, 27, GameState.RESET);
        buttonUi[2] = new cvut.fel.pjv.pimenol1.pages.Button(400, 600, 2, "pauseButtons", 120, 27, GameState.SAVE);

        buttonUi[3] = new cvut.fel.pjv.pimenol1.pages.Button(100, 400, 0, "pauseButtons", 120, 27, GameState.MAINMENU);
        buttonUi[4] = new cvut.fel.pjv.pimenol1.pages.Button(100, 500, 1, "pauseButtons", 120, 27, GameState.RESET);
    }

    public void writeMessage(String text) {
        this.message = text;
        haveMessage = true;
    }

    public void drawGame(Graphics2D g2) {

        g2.setFont(myFont.deriveFont(Font.PLAIN, 50F));
        g2.setColor(new Color(58, 0, 137));

        gameTime += (double) 1 / Constants.FPS;
        if (haveMessage) {
            g2.drawString(message, 100, 100);
            MessageTimer++;
            if (MessageTimer > 200) {
                haveMessage = false;
                MessageTimer = 0;
            }
        }

        drawLife(g2, countHeart);
        drawCatLeft(g2, catLeft);

    }

    private void drawCatLeft(Graphics2D g2, int catLeft) {
        int x = 30;
        int y = 70* (int) (countHeart/12+1);
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

    public void drawPause(Graphics2D g2) {

        g2.setComposite(AlphaComposite.getInstance((AlphaComposite.SRC_OVER), 0.3f));
        g2.drawImage(backgroundImg, 0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HIGH, null);
        g2.setComposite(AlphaComposite.getInstance((AlphaComposite.SRC_OVER), 1f));

        g2.setFont(myFont.deriveFont(Font.PLAIN, 180F));
        g2.setColor(Color.WHITE);

        for (int i=0;i<3;i++)
            buttonUi[i].draw(g2);

        g2.drawString("PAUSE", 270, 200);
        g2.setFont(myFont.deriveFont(Font.PLAIN, 30F));
        g2.drawString("press ESC for continue", 360, 250);
    }

    public void drawGameOver(Graphics2D g2) {

        g2.setComposite(AlphaComposite.getInstance((AlphaComposite.SRC_OVER), 0.3f));
        g2.drawImage(backgroundImg, 0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HIGH, null);
        g2.setComposite(AlphaComposite.getInstance((AlphaComposite.SRC_OVER), 1f));

        g2.setFont(myFont.deriveFont(Font.PLAIN, 180F));
        g2.setColor(Color.WHITE);

        for (int i=0;i<3;i++)
            buttonUi[i].draw(g2);

        g2.drawString("GAME", 320, 200);
        g2.drawString("OVER", 320, 350);
    }

    public void drawWin(Graphics2D g2) {
        try {
            BufferedImage pauseImg = ImageIO.read(getClass().getResourceAsStream("/background/catsArm.png"));
            g2.drawImage(pauseImg, 0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HIGH, null);
        } catch (Exception e) {
            System.out.println("Problem with reading pause img: " + e);
        }
        g2.setFont(myFont.deriveFont(Font.PLAIN, 180F));

        buttonUi[3].draw(g2);
        buttonUi[4].draw(g2);

        g2.drawString("YOU", 80, 150);
        g2.drawString("WIN", 110, 270);
    }

    public void updateButton() {
        for (cvut.fel.pjv.pimenol1.pages.Button button : buttonUi)
            button.update();
    }

    public boolean isInButton(MouseEvent e, cvut.fel.pjv.pimenol1.pages.Button mb) {
        return mb.getHitBox().contains(e.getX(), e.getY());
    }

    public void mousePressed(MouseEvent e) {
        for (cvut.fel.pjv.pimenol1.pages.Button b : buttonUi) {
            if (isInButton(e, b))
                b.setMousePressed(true);
        }
    }

    public void mouseReleased(MouseEvent e) {
        for (cvut.fel.pjv.pimenol1.pages.Button b : buttonUi) {
            if (isInButton(e, b)) {
                if (b.isMousePressed())
                    b.applyGameStatePlay();
                break;
            }
        }
        resetButtons();
    }

    private void resetButtons() {
        for (cvut.fel.pjv.pimenol1.pages.Button b : buttonUi)
            b.resetBooleans();
    }

    public void mouseMoved(MouseEvent e) {
        for (cvut.fel.pjv.pimenol1.pages.Button b : buttonUi)
            b.setMouseOver(false);

        for (Button b : buttonUi)
            if (isInButton(e, b)) {
                b.setMouseOver(true);
                break;
            }
    }


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
