package Inventorys;

import Entity.Player;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Collar extends Objects{
    public Collar() {
        name="collar";
        collision=true;
        try{
            img= ImageIO.read(getClass().getResourceAsStream("/objects/collar.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void pickUp(Player player,int inx){

    }
}
