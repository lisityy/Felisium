package Things;

import cz.cvut.fel.pjv.GamePannel;
import cz.cvut.fel.pjv.KeyHandler;

import java.awt.*;

public class Player extends Things{

    GamePannel gp;
    KeyHandler kh;
    public Player(GamePannel gp, KeyHandler kh){
        this.gp=gp;
        this.kh=kh;
        setDefultValues();
    }

    public void setDefultValues(){
        x=100;
        y=100;
        speed=4;
    }
    public void update(){
        if(kh.upPressed){
            y-=speed;
        } else if(kh.downPressed){
            y+=speed;
        } else if (kh.leftPressed){
            x -=speed;
        } else if(kh.rightPressed){
            x+=speed;
        }
    }
    public void draw(Graphics2D g2){
        g2.setColor(Color.blue);
        g2.fillRect(x,y, gp.tileSize, gp.tileSize);
    }

}
