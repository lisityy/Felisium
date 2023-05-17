package cvut.fel.pjv.pimenol1.main_pjv;

import cvut.fel.pjv.pimenol1.background.TileManager;
import cvut.fel.pjv.pimenol1.entity.Player;
import cvut.fel.pjv.pimenol1.inventorys.Objects;

import javax.swing.JPanel;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    // SCREEN SETTINGS
//    private final int originalTileSize = 16;
//    private final int scale = 4;
//
//    private final int tileSize = originalTileSize * scale;
//    private final int maxScreenCol = 16;
//    private final int maxScreenRow = 14;
//    private final int screenWidth = tileSize * maxScreenCol;
//    private final int screenHeight = tileSize * maxScreenRow;
//
//    //    WORLD SETTINGS
//    private final int maxWorldCol = 50;
//    private final int maxWorldRow = 50;
//    private final int maxWorldHigh = maxWorldRow * tileSize;
//    private final int maxWorldWidth = tileSize * maxWorldCol;


//    private final int FPS = 100;
    private final KeyHandler kh = new KeyHandler(this);
    private final TileManager tileManager = new TileManager(this);
    private Thread gameThread;
    public Player player = new Player(this, kh);
    private CheckerCollision checkerCollision = new CheckerCollision(this);
    private GameState gameState;

    public Objects[] obj = new Objects[10];
    private AssetSetter assetSetter = new AssetSetter(this);
    private UI ui = new UI(this);


    public GamePanel() {
        this.setPreferredSize(new Dimension(Constants.SCREEN_WIDTH, Constants.SCREEN_HIGH));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(kh);
        this.setFocusable(true);
    }

    public void setUpGame() {
        assetSetter.setObjects();
        gameState = GameState.PLAY;
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = (double) 1000000000 / Constants.FPS;
        double nexDrowTime = System.nanoTime() + drawInterval;
        MusicPlayer musicPlayer = new MusicPlayer();
        musicPlayer.play("/music/musBegin.wav");
        musicPlayer.changeIntensity(0.5F);

        while (gameThread != null) {
            update();
            repaint();

            try {
                double remainingTime = nexDrowTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;

                if (remainingTime < 0) remainingTime = 0;

                Thread.sleep((long) remainingTime);
                nexDrowTime += drawInterval;

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
        musicPlayer.stop();

    }


    public void update() {
        switch (gameState) {
            case PLAY -> {
                player.update();
            }
            case PAUSE -> {

            }
            case MAIN -> {

            }
            case SETTINGS -> {

            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        if (gameState==GameState.PLAY) {
            tileManager.draw(g2);
            drawObjects(g2);
            player.draw(g2);
        }
        ui.draw(g2);
        g2.dispose();
    }

    public void drawObjects(Graphics2D g2){
        for (Objects objects : obj) {
            if (objects != null) {
                objects.draw(g2, this);
            }
        }
    }

//    public int getOriginalTileSize() {
//        return originalTileSize;
//    }
//
//    public int getScale() {
//        return scale;
//    }
//
//    public int getTileSize() {
//        return tileSize;
//    }
//
//    public int getMaxScreenCol() {
//        return maxScreenCol;
//    }
//
//    public int getMaxScreenRow() {
//        return maxScreenRow;
//    }
//
//    public int getScreenWidth() {
//        return screenWidth;
//    }
//
//    public int getScreenHeight() {
//        return screenHeight;
//    }
//
//    public int getMaxWorldCol() {
//        return maxWorldCol;
//    }
//
//    public int getMaxWorldRow() {
//        return maxWorldRow;
//    }
//
//    public int getMaxWorldHigh() {
//        return maxWorldHigh;
//    }
//
//    public int getMaxWorldWidth() {
//        return maxWorldWidth;
//    }
//
//    public int getFPS() {
//        return FPS;
//    }

    public TileManager getTileManager() {
        return tileManager;
    }

    public void setGameThread(Thread gameThread) {
        this.gameThread = gameThread;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public CheckerCollision getCheckerCollision() {
        return checkerCollision;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public UI getUi() {
        return ui;
    }
}