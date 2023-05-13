package Objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Key extends Objects {
    public Key() {
        name="key";
        collision=true;
        try{
            img= ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
