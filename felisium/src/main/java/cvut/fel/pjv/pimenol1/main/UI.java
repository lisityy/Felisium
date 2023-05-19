package cvut.fel.pjv.pimenol1.main;

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
    BufferedImage tileInventorys;

    public UI() {
        try {
            InputStream is = getClass().getResourceAsStream("/text/vermirVibe.ttf");
            myFont = Font.createFont(Font.TRUETYPE_FONT, is);

            tileInventorys= Utils.load_image("tiles","pinkTile");
            tileInventorys=Utils.scaleImg(tileInventorys, Constants.TILE_SIZE+5, Constants.TILE_SIZE+5);
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
        g2.setColor(new Color(58,0,137));

        gameTime += (double) 1 / Constants.FPS;
        if (haveMessage) {
            g2.drawString(message, 100, 100);
            MessageTimer++;
            if (MessageTimer > 200) {
                haveMessage = false;
                MessageTimer = 0;
            }
        }
        int x = 650;
        int y = 50;
        for (int i = 0; i < 5; i++) {
            g2.drawImage(tileInventorys, x, y, null);
            x += Constants.TILE_SIZE + 10;
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


}
