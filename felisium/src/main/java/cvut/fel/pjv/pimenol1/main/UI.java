package cvut.fel.pjv.pimenol1.main;

import cvut.fel.pjv.pimenol1.inventorys.Heart;
import cvut.fel.pjv.pimenol1.utils.Utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;

public class UI {

    Font myFont;
    String message = "";
    boolean haveMessage = false;
    int MessageTimer = 0;
    private double gameTime = 0;
    BufferedImage heart;
    private int countHeart=9;

    public UI() {
        try {
            InputStream is = getClass().getResourceAsStream("/text/vermirVibe.ttf");
            myFont = Font.createFont(Font.TRUETYPE_FONT, is);

            heart = Utils.load_image("objects", "heart");
            heart = Utils.scaleImg(heart, Constants.TILE_SIZE, Constants.TILE_SIZE);

        } catch (Exception e) {
            System.out.println("Error read font: " + e.getMessage());
        }
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

        drawLife(g2,countHeart);

    }

    private void drawLife(Graphics2D g2, int life){
        int x=30;
        int y=30;
        int j=0;
        for (int i = 0; i < life ; i++) {
            j++;
            g2.drawImage(heart, x, y, null);
            x += Constants.TILE_SIZE/2;
            if(j>12){
                y += Constants.TILE_SIZE;
                x=30;
                j=0;
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
        g2.setFont(myFont.deriveFont(Font.PLAIN, 100F));
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


    public int getCountHeart() {
        return countHeart;
    }

    public void setCountHeart(int countHeart) {
        this.countHeart = countHeart;
    }
}
