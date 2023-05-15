package main_pjv;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;

public class UI {

    GamePannel gp;
    Graphics2D g2;
    Font fontMy;
    String message = "";
    boolean haveMessage = false;
    int MessageTimer = 0;

    private boolean gamePause = false;
    private boolean gameFinished = false;
    private boolean gamePlay = true;

    private double gameTime = 0;

    public UI(GamePannel gp) {
        this.gp = gp;
        try {
            InputStream is = getClass().getResourceAsStream("/text/vermirVibe.ttf");
            assert is != null;
            fontMy = Font.createFont(Font.TRUETYPE_FONT, is);

        } catch (Exception e) {
            System.out.println("Error read font: " + e.getMessage());
        }

    }

    public void writeMessage(String text) {
        this.message = text;
        haveMessage = true;
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;
        if (gp.getGameState() == GameState.WIN) {
            winPage();
        } else if (gp.getGameState() == GameState.PAUSE) {
            pausePage();
        } else {
            playPage();
        }
    }

    public void playPage(){
        gameTime += (double) 1 / gp.getFPS();
        if (haveMessage) {
            g2.drawString(message, 100, 100);
            MessageTimer++;
            if (MessageTimer > 200) {
                haveMessage = false;
                MessageTimer = 0;
            }
        }
    }

    public void pausePage() {

        try {
            BufferedImage pauseImg = ImageIO.read(getClass().getResourceAsStream("/background/pauseMenu.png"));
            g2.drawImage(pauseImg, 0, 0, gp.getScreenWidth(), gp.getScreenHeight(), null);
        } catch (Exception e) {
            System.out.println("Problem with reading pause img: " + e);
        }
        g2.setFont(fontMy.deriveFont(Font.PLAIN, 100F));
        g2.drawString("PAUSE", 400, 700);
    }

    public void winPage() {
        gp.setGameThread(null);
        try {
            BufferedImage pauseImg = ImageIO.read(getClass().getResourceAsStream("/background/catsArm.png"));
            g2.drawImage(pauseImg, 0, 0, gp.getScreenWidth(), gp.getScreenHeight(), null);
        } catch (Exception e) {
            System.out.println("Problem with reading pause img: " + e);
        }
        g2.setFont(fontMy.deriveFont(Font.PLAIN, 180F));

        g2.drawString("YOU", 80, 150);
        g2.drawString("WIN", 110, 270);
    }

    public int getXCenterForText(String text) {
        int len = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return gp.getScreenWidth() / 2 - len / 2;
    }

    public GamePannel getGp() {
        return gp;
    }

    public void setGp(GamePannel gp) {
        this.gp = gp;
    }

    public Font getFontMy() {
        return fontMy;
    }

    public void setFontMy(Font fontMy) {
        this.fontMy = fontMy;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isHaveMessage() {
        return haveMessage;
    }

    public void setHaveMessage(boolean haveMessage) {
        this.haveMessage = haveMessage;
    }

    public int getMessageTimer() {
        return MessageTimer;
    }

    public void setMessageTimer(int messageTimer) {
        MessageTimer = messageTimer;
    }

    public boolean isGamePause() {
        return gamePause;
    }

    public void setGamePause(boolean gamePause) {
        this.gamePause = gamePause;
    }

    public boolean isGameFinished() {
        return gameFinished;
    }

    public void setGameFinished(boolean gameFinished) {
        this.gameFinished = gameFinished;
    }

    public boolean isGamePlay() {
        return gamePlay;
    }

    public void setGamePlay(boolean gamePlay) {
        this.gamePlay = gamePlay;
    }
}
