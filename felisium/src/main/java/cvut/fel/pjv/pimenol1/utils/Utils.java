package cvut.fel.pjv.pimenol1.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Utils {

    public static BufferedImage scaleImg(BufferedImage original, int width, int height) {

        BufferedImage scaleImg = new BufferedImage(width, height, original.getType());
        Graphics2D g2 = scaleImg.createGraphics();
        g2.drawImage(original, 0, 0, width, height, null);
        g2.dispose();

        return scaleImg;
    }

    public static BufferedImage load_image(String path ,String imageName) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(Utils.class.getResourceAsStream("/"+path+"/" + imageName + ".png"));
//            img = Utils.scaleImg(img, Constants.TILE_SIZE, Constants.TILE_SIZE);
        } catch (IOException e) {
            System.out.println("Error reading img player:" + e.getMessage());
            e.printStackTrace();
        }
        return img;
    }


}
