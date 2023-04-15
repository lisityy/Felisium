package main_pjv;

import Entity.Player;

import javax.swing.JPanel;
import java.awt.*;

public class GamePannel extends JPanel implements Runnable {
    // SCREEN SETTINGS
    final int originalTileSize = 16;
    final int scale=3;

    public final int tileSize=originalTileSize*scale;
    final  int maxScreenCol=16;
    final int maxScreenRow=12;
    final int screenWidth=tileSize*maxScreenCol;
    final int screenHeight = tileSize*maxScreenRow;

    int FPS=60;
    KeyHandler kh=new KeyHandler();

    Thread gameThread;
    Player player=new Player(this,kh);

//    System.setProperty("sun.java2d.opengl", "true");

    public GamePannel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(kh);
        this.setFocusable(true);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000/FPS;
        double nexDrowTime = System.nanoTime()+drawInterval;

        while(gameThread!=null){
            update();
            repaint();

            try {
                double remainingTime = nexDrowTime - System.nanoTime();
                if(remainingTime<0) remainingTime=0;
                Thread.sleep((long) remainingTime/1000000);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            nexDrowTime+=drawInterval;
        }
    }
    public  void update(){
       player.update();
    }
    public  void paintComponent(Graphics g){

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        player.draw(g2);
        g2.dispose();
    }
}
