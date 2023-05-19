package cvut.fel.pjv.pimenol1.main;

import cvut.fel.pjv.pimenol1.background.TileManager;
import cvut.fel.pjv.pimenol1.entity.Entity;
import cvut.fel.pjv.pimenol1.entity.Player;
import cvut.fel.pjv.pimenol1.inventorys.Item;
import cvut.fel.pjv.pimenol1.utils.AssetSetter;
import cvut.fel.pjv.pimenol1.utils.MusicPlayer;

import javax.swing.*;
import java.awt.*;

public class PlayingPage extends JPanel implements Page {

    private GamePanel gp;
    private final TileManager tileManager = new TileManager();
    public Player player;
    public Item[] obj = new Item[10];
    public Entity[] npc= new Entity[10];

    private AssetSetter assetSetter = new AssetSetter(this);
    private UI ui = new UI();
    MusicPlayer musicPlayer;

    private boolean isPause = false;

    public PlayingPage(GamePanel gp) {
        this.gp = gp;
        player = new Player(this, gp.getKh());
    }

    public void startGame() {
        musicPlayer = new MusicPlayer();
        musicPlayer.play("/music/musBegin.wav");
        musicPlayer.changeIntensity(0.5F);
        setUpGame();
        Constants.gameState=GameState.PLAY;
    }

    public void setUpGame() {
        assetSetter.setObjects();
        assetSetter.setNPC();
    }

    public void endGame() {
        musicPlayer.stop();
    }

    @Override
    public void update() {
        player.update();
    }

    @Override
    public void draw(Graphics2D g2) {
        if(!isPause){
            tileManager.draw(g2, player);
            drawObjects(g2);
            drawNPC(g2);
            ui.drawGame(g2);
            player.draw(g2,this);

        } else{
            ui.drawPause(g2);
        }

    }

    private void drawNPC(Graphics2D g2){
        for (Entity entity : npc) {
            if (entity != null) {
                entity.draw(g2,this);
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


    public boolean isPause() {return isPause;}

    public void setPause(boolean pause) {isPause = pause;}

    public TileManager getTileManager() {
        return tileManager;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Item[] getObj() {
        return obj;
    }

    public void setObj(Item[] obj) {
        this.obj = obj;
    }

    public AssetSetter getAssetSetter() {
        return assetSetter;
    }

    public void setAssetSetter(AssetSetter assetSetter) {
        this.assetSetter = assetSetter;
    }

    public UI getUi() {
        return ui;
    }

    public void setUi(UI ui) {
        this.ui = ui;
    }

    public MusicPlayer getMusicPlayer() {
        return musicPlayer;
    }

    public void setMusicPlayer(MusicPlayer musicPlayer) {
        this.musicPlayer = musicPlayer;
    }
}

