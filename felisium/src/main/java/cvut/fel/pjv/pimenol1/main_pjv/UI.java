package cvut.fel.pjv.pimenol1.main_pjv;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;

public class UI {

    Font fontMy;
    String message = "";
    boolean haveMessage = false;
    int MessageTimer = 0;
    private double gameTime = 0;

    public UI() {
        try {
            InputStream is = getClass().getResourceAsStream("/text/vermirVibe.ttf");
            fontMy = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (Exception e) {
            System.out.println("Error read font: " + e.getMessage());
        }
    }

    public void writeMessage(String text) {
        this.message = text;
        haveMessage = true;
    }

    public void drawGame(Graphics2D g2) {
        gameTime += (double) 1 / Constants.FPS;
        if (haveMessage) {
            g2.drawString(message, 100, 100);
            MessageTimer++;
            if (MessageTimer > 200) {
                haveMessage = false;
                MessageTimer = 0;
            }
        }

    }

    public void drawPause(Graphics2D g2) {
        try {
            BufferedImage pauseImg = ImageIO.read(getClass().getResourceAsStream("/background/pauseMenu.png"));
            g2.drawImage(pauseImg, 0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HIGH, null);
        } catch (Exception e) {
            System.out.println("Problem with reading pause img: " + e);
        }
        g2.setFont(fontMy.deriveFont(Font.PLAIN, 100F));
        g2.drawString("PAUSE", 400, 700);
    }

//    public void drawWin(Graphics2D g2) {
//        try {
//            BufferedImage pauseImg = ImageIO.read(getClass().getResourceAsStream("/background/catsArm.png"));
//            g2.drawImage(pauseImg, 0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HIGH, null);
//        } catch (Exception e) {
//            System.out.println("Problem with reading pause img: " + e);
//        }
//        g2.setFont(fontMy.deriveFont(Font.PLAIN, 180F));
//
//        g2.drawString("YOU", 80, 150);
//        g2.drawString("WIN", 110, 270);
//    }

//    public int getXCenterForText(String text) {
//        int len = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
//        return Constants.SCREEN_WIDTH / 2 - len / 2;
//    }


}
