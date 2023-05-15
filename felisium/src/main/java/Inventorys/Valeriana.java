package Inventorys;

import Entity.Player;
import main_pjv.GamePannel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Valeriana extends Objects{
    public Valeriana(GamePannel gp) {
        name="valeriana";
        collision=true;
        try{
            img= ImageIO.read(getClass().getResourceAsStream("/objects/valeriana.png"));
            img=utils.scaleImg(img,gp.getTileSize(),gp.getTileSize());
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void pickUp(Player player,int inx){

    }
}
