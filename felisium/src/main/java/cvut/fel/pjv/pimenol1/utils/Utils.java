package cvut.fel.pjv.pimenol1.utils;

import cvut.fel.pjv.pimenol1.main.Felisium;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;

public class Utils {

    /**
     * Scales an image to the specified width and height.
     * @param original the original image
     * @param width the desired width
     * @param height the desired height
     * @return the scaled image
     */
    public static BufferedImage scaleImg(BufferedImage original, int width, int height) {

        BufferedImage scaleImg = new BufferedImage(width, height, original.getType());
        Graphics2D g2 = scaleImg.createGraphics();
        g2.drawImage(original, 0, 0, width, height, null);
        g2.dispose();

        return scaleImg;
    }

    /**
     * Loads an image from the specified path and filename.
     * @param path the path of the image file
     * @param imageName the name of the image file
     * @return the loaded image
     */
    public static BufferedImage load_image(String path, String imageName) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(Utils.class.getResourceAsStream("/" + path + "/" + imageName + ".png"));

        } catch (IOException e) {
            Felisium.logger.log(Level.SEVERE, "Error reading image {}: {}");
            e.printStackTrace();
        }
        return img;
    }
}
