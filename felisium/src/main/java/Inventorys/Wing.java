package Inventorys;

import Entity.Player;
import main_pjv.MusicPlayer;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Wing extends Objects{

    private MusicPlayer musicPlayer=new MusicPlayer();
    public Wing() {
        name="wing";
        collision=true;
        try{
            img= ImageIO.read(getClass().getResourceAsStream("/objects/wing.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void pickUp(Player player, int inx){
        musicPlayer.play("/music/UrrCat.wav");
        musicPlayer.stop();
        player.getGp().obj[inx]=null;
        player.setHasWings(true);
    }

}
