package cvut.fel.pjv.pimenol1.main_pjv;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Utils {

    public BufferedImage scaleImg(BufferedImage original, int width, int height) {

        BufferedImage scaleImg = new BufferedImage(width, height, original.getType());
        Graphics2D g2 = scaleImg.createGraphics();
        g2.drawImage(original, 0, 0, width, height, null);
        g2.dispose();

        return scaleImg;
    }
}
