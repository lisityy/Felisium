package Objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Wing extends Objects{
    public Wing() {
        name="wing";
        collision=true;
        try{
            img= ImageIO.read(getClass().getResourceAsStream("/objects/wing.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
