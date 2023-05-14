package Inventorys;

import Entity.Player;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Valeriana extends Objects{
    public Valeriana() {
        name="valeriana";
        collision=true;
        try{
            img= ImageIO.read(getClass().getResourceAsStream("/objects/valeriana.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void pickUp(Player player,int inx){

    }
}
