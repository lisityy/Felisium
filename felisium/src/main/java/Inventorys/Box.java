package Inventorys;

import Entity.Player;
import main_pjv.MusicPlayer;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.nio.channels.MulticastChannel;

public class Box extends Objects{

    private MusicPlayer musicPlayer= new MusicPlayer();

    public Box() {
        name="box";
        collision=true;
        try{
            img= ImageIO.read(getClass().getResourceAsStream("/objects/box.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void pickUp(Player player,int inx){
        musicPlayer.play("/music/UrrCat.wav");
        musicPlayer.stop();
        player.getGp().obj[inx]=null;
    }
}