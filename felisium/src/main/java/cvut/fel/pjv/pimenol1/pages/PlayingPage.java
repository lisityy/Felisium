package cvut.fel.pjv.pimenol1.pages;

import cvut.fel.pjv.pimenol1.main.Constants;
import cvut.fel.pjv.pimenol1.main.Felisium;
import cvut.fel.pjv.pimenol1.main.GameState;
import cvut.fel.pjv.pimenol1.background.TileManager;
import cvut.fel.pjv.pimenol1.entity.Entity;
import cvut.fel.pjv.pimenol1.entity.Player;
import cvut.fel.pjv.pimenol1.inventorys.Item;
import cvut.fel.pjv.pimenol1.utils.AssetSetter;
import cvut.fel.pjv.pimenol1.utils.MusicPlayer;

import javax.swing.*;
import java.awt.*;
import java.io.InputStream;

public class PlayingPage extends JPanel implements Page {

    private final Felisium gp;
    private TileManager tileManager = new TileManager();
    public Player player;

    public Item[] obj = new Item[20];
    public Entity[] npc = new Entity[20];
    private Entity[] aliens = new Entity[20];

    AssetSetter assetSetter = new AssetSetter(this);
    private final UI ui = new UI();
    MusicPlayer musicPlayer= new MusicPlayer();;

    public Font myFont;

    public PlayingPage(Felisium gp) {
        this.gp = gp;

        Constants.gameStatePlay = GameState.PLAY;
        player = new Player(this, gp.getKh());
        InputStream is = getClass().getResourceAsStream("/text/vermirVibe.ttf");
        try {
            assert is != null;
            myFont = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (Exception e) {
            System.out.println("ERROR: reading font" + e);
        }
    }

    public void startGame() {
        musicPlayer.play("/music/musBegin.wav");
        musicPlayer.changeIntensity(1F);
        setUpGame();
        Constants.gameStatePlay = GameState.PLAY;
        Constants.gameState = GameState.PLAY;
        player = new Player(this, gp.getKh());
    }

    public void setUpGame() {
        assetSetter.setObjects();
        assetSetter.setNPC();
        assetSetter.setAliens();
    }

    public void endGame() {
        musicPlayer.stop();
    }

    @Override
    public void update() {
        switch (Constants.gameStatePlay) {
            case PLAY -> {
                player.update();
                aliensUpdate();
                npcUpdate();
            }
            default -> {
                ui.updateButton();
            }
        }

    }

    void aliensUpdate() {
        for (int i = 0; i < aliens.length; i++) {
            if (aliens[i] != null) {
                if (aliens[i].getLife() <= 0) {
                    aliens[i] = null;
                } else {
                    aliens[i].update();
                }
            }
        }
    }

    void npcUpdate() {
        for (Entity entity : npc) {
            if (entity != null) {
                entity.update();
            }
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        tileManager.draw(g2, player);
        drawObjects(g2);
        drawNPC(g2);
        drawEnemy(g2);
        ui.drawGame(g2);
        player.draw(g2, this);
        switch (Constants.gameStatePlay) {
            case PAUSE -> {
                ui.drawPause(g2);
            }
            case GAMEOVER -> {
                ui.drawGameOver(g2);
            }
            case WIN -> {
                ui.drawWin(g2);
            }
        }

    }

    private void drawNPC(Graphics2D g2) {
        for (Entity entity : npc) {
            if (entity != null) {
                entity.draw(g2, this);
            }
        }
    }

    private void drawObjects(Graphics2D g2) {
        for (Item items : obj) {
            if (items != null) {
                items.draw(g2, this);
            }
        }
    }

    private void drawEnemy(Graphics2D g2) {
        for (Entity alien : aliens) {
            if (alien != null) {
                alien.draw(g2, this);
            }
        }
    }

    public Entity[] getAliens() {
        return aliens;
    }


    public TileManager getTileManager() {
        return tileManager;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public UI getUi() {
        return ui;
    }

    public void setAliens(Entity[] aliens) {
        this.aliens = aliens;
    }
}

