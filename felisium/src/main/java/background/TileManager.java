package background;

import main_pjv.GamePannel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class TileManager {

    public Tile[] tiles;
    public GamePannel gp;

    public TileManager(GamePannel gp) {
        this.gp = gp;
        tiles=new Tile[10];
    }

    public void getTileImg(){

        try {
            tiles[0]=new Tile();
            tiles[0].img= ImageIO.read(getClass().getResourceAsStream("/tiles/01.png"));

            tiles[1]=new Tile();
            tiles[1].img= ImageIO.read(getClass().getResourceAsStream("/tiles/02.png"));

            tiles[2]=new Tile();
            tiles[2].img= ImageIO.read(getClass().getResourceAsStream("/tiles/03.png"));

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
