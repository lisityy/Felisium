package main_pjv;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class UI {

    GamePannel gp;
    Font font04B;
    Font vermir;

    public UI(GamePannel gp) {
        this.gp = gp;
        try {
            InputStream is=getClass().getResourceAsStream("/text/vermirVibe.ttf");
            assert is != null;
            font04B = Font.createFont(Font.TRUETYPE_FONT, is);

        } catch (Exception e) {
            System.out.println("Error read font: " + e.getMessage());
        }


    }

    public void draw(Graphics2D g2) {
        g2.setFont(font04B.deriveFont(Font.PLAIN, 40F));
        g2.setColor(Color.WHITE);

        g2.drawString("LIVE", 100, 100);
    }
}
