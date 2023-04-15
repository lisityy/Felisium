package Entity;

import main_pjv.GamePannel;
import main_pjv.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Player extends Entity {

    GamePannel gp;
    KeyHandler kh;
    public int level;
    public int armor;
    public Player(GamePannel gp, KeyHandler kh){
        super();
        this.gp=gp;
        this.kh=kh;
        setDefultValues();
        getPlayerImg();
    }

    public void setDefultValues(){
        x=100;
        y=100;
        speed=4;
        direction="up";
    }

    public void getPlayerImg(){
        try{
            up1= ImageIO.read(getClass().getResourceAsStream("/players/cat_up_1.png"));
            up2= ImageIO.read(getClass().getResourceAsStream("/players/cat_up_2.png"));
            down1= ImageIO.read(getClass().getResourceAsStream("/players/cat_down_1.1.png"));
            down2= ImageIO.read(getClass().getResourceAsStream("/players/cat_down_2.2.png"));
            right1= ImageIO.read(getClass().getResourceAsStream("/players/catfat_right_1.png"));
            rigth2= ImageIO.read(getClass().getResourceAsStream("/players/catfat_right_2.png"));
            left1= ImageIO.read(getClass().getResourceAsStream("/players/catfat_left_1.png"));
            left2= ImageIO.read(getClass().getResourceAsStream("/players/catfat_left_2.png"));
            sleep= ImageIO.read(getClass().getResourceAsStream("/players/cat_sleep.png"));
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void update(){
        if(kh.upPressed){
            direction="up";
            y-=speed;
        } else if(kh.downPressed){
            direction="down";
            y+=speed;
        } else if (kh.leftPressed){
            direction="left";
            x -=speed;
        } else if(kh.rightPressed){
            direction="right";
            x+=speed;
        } else{
            waitCounter++;
            if(waitCounter>100){
                direction="sleep";
//                waitNum= 2;
            }
            return;
        }
        waitCounter=0;
        spriteCounter++;
        if(spriteCounter>7){
            if(spriteNum==1){
                spriteNum=2;
            } else if(spriteNum==2){
                spriteNum=1;
            }
            spriteCounter=0;
        }
    }
    public void draw(Graphics2D g2){

        BufferedImage img = switch (direction) {
            case "up" -> spriteNum==1? up1: up2;
            case "down" -> spriteNum==1? down1: down2;
            case "left" -> spriteNum==1? left1 : left2;
            case "right" -> spriteNum==1 ? right1: rigth2;
            case "sleep" ->  sleep;
            default -> null;
        };
        g2.drawImage(img, x, y, gp.tileSize, gp.tileSize, null);

//        g2.setColor(Color.blue);
//        g2.fillRect(x,y, gp.tileSize, gp.tileSize);
    }

}
