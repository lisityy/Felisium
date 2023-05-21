package cvut.fel.pjv.pimenol1.main;

import cvut.fel.pjv.pimenol1.background.TileManager;
import cvut.fel.pjv.pimenol1.entity.Entity;
import cvut.fel.pjv.pimenol1.entity.Player;
import cvut.fel.pjv.pimenol1.inventorys.Item;
import cvut.fel.pjv.pimenol1.utils.AssetSetter;
import cvut.fel.pjv.pimenol1.utils.MusicPlayer;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class PlayingPage extends JPanel implements Page {

    private GamePanel gp;
    private final TileManager tileManager = new TileManager();
    public Player player;

    public Item[] obj = new Item[10];
    public Entity[] npc = new Entity[10];
    private Entity[] aliens = new Entity[10];

    private AssetSetter assetSetter = new AssetSetter(this);
    private UI ui = new UI();
    MusicPlayer musicPlayer;

    public Font myFont;
    private GameState state= GameState.PLAY;

    public PlayingPage(GamePanel gp) {
        this.gp = gp;
        player = new Player(this, gp.getKh());
        InputStream is = getClass().getResourceAsStream("/text/vermirVibe.ttf");
        try{
        myFont = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (Exception e){
            System.out.println("ERROR: reading font"+e);
        }
    }

    public void startGame() {
        musicPlayer = new MusicPlayer();
        musicPlayer.play("/music/musBegin.wav");
        musicPlayer.changeIntensity(0.5F);
        setUpGame();
        Constants.gameState = GameState.PLAY;
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
        switch (state){
            case PLAY -> {
                player.update();
                aliensUpdate();
                npcUpdate();
            }
            case PAUSE -> {

            }
            case GAMEOVER -> {

            }
        }

    }

    private void aliensUpdate(){
        for (int i = 0; i < aliens.length; i++) {
            if(aliens[i]!=null){
                if(aliens[i].getLife()<=0){
                    aliens[i]=null;
                }else {
                    aliens[i].update();
                }
            }
        }
    }

    private  void npcUpdate(){
        for (Entity entity : npc) {
            if (entity != null) {
                entity.update();
            }
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        switch (state){
            case PLAY -> {
                tileManager.draw(g2, player);
                drawObjects(g2);
                drawNPC(g2);
                drawEnemy(g2);
                ui.drawGame(g2);
                player.draw(g2, this);
            }
            case PAUSE -> {
                ui.drawPause(g2);
            }
            case GAMEOVER -> {

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

    public GameState getState() {
        return state;
    }

    public void setState(GameState state) {
        this.state = state;
    }
}

