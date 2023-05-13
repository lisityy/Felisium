package Objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Door extends Objects{
    public Door() {
        name="door";
        collision=true;
        try{
            img= ImageIO.read(getClass().getResourceAsStream("/objects/door2.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
