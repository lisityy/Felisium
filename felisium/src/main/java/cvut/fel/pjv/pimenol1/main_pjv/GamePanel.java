package cvut.fel.pjv.pimenol1.main_pjv;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.CardLayout;

public class GamePanel extends JPanel implements Runnable {

    private Thread gameThread;
    private final KeyHandler kh = new KeyHandler(this);
    private MainMenuPage mainMenuPanel;
    private PlayingPage playingPage;

    public GamePanel() {
        this.setPreferredSize(new Dimension(Constants.SCREEN_WIDTH, Constants.SCREEN_HIGH));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(kh);
        this.setFocusable(true);
        Constants.gameState = GameState.MAIN;
        mainMenuPanel = new MainMenuPage();
        playingPage = new PlayingPage(this);
        add(mainMenuPanel, "Main menu");
        add(playingPage, "Play");

    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = (double) 1000000000 / Constants.FPS;
        double nexDrowTime = System.nanoTime() + drawInterval;
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
    }

    public void update() {
        switch (Constants.gameState) {
            case PLAY -> {
                playingPage.update();
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

        switch (Constants.gameState) {
            case PLAY -> {
                playingPage.draw(g2);
            }
            case MAIN -> {
                mainMenuPanel.draw(g2);
            }
            case SETTINGS -> {

            }
            case WIN -> {

            }
        }

        g2.dispose();
    }

    public KeyHandler getKh() {
        return kh;
    }

    public MainMenuPage getMainMenuPanel() {
        return mainMenuPanel;
    }

    public void setMainMenuPanel(MainMenuPage mainMenuPanel) {
        this.mainMenuPanel = mainMenuPanel;
    }

    public PlayingPage getPlayingPage() {
        return playingPage;
    }

    public void setPlayingPage(PlayingPage playingPage) {
        this.playingPage = playingPage;
    }

    public void setGameThread(Thread gameThread) {
        this.gameThread = gameThread;
    }
}