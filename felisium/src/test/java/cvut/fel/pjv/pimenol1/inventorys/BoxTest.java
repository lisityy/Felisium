package cvut.fel.pjv.pimenol1.inventorys;

import cvut.fel.pjv.pimenol1.entity.Entity;
import cvut.fel.pjv.pimenol1.entity.NPC_catan;
import cvut.fel.pjv.pimenol1.entity.Player;
import cvut.fel.pjv.pimenol1.main.Felisium;
import cvut.fel.pjv.pimenol1.pages.PlayingPage;
import cvut.fel.pjv.pimenol1.utils.KeyHandler;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoxTest {

    private Player player;
    private PlayingPage playingPage;
    private Felisium felisium;
    Box box;
    private Entity cat;

    @BeforeEach
    public void setUp() {
        felisium = new Felisium();
        playingPage = new PlayingPage(felisium);
        KeyHandler keyHandler = new KeyHandler(felisium);
        player = new Player(playingPage, keyHandler);

        player.setIndexCat(0);
        playingPage.npc[0]=new NPC_catan(playingPage, "NPC_catan", "catan", 10,10);

        box = new Box(1, 0, 0);

        player.setHitCat(true);
        player.setCatsLeft(2);
        player.getPp().getUi().setCatLeft(player.getCatsLeft());
        player.getPp().obj[box.index] = box;
    }

    @Test
    public void useItemTest_dontHitCat(){
        player.setHitCat(false);

        boolean result = box.useItem(player);

        assertFalse(result);
    }
    @Test
    public void useItemTest_changeCatLeft(){

        box.useItem(player);

        assertEquals(1, player.getCatsLeft());
    }
    @Test
    public void useItemTest_canTakeChange(){

        box.useItem(player);

        assertFalse(player.getPp().obj[box.index].canTake);
    }
    @Test
    public void useItemTest_addBoxToMap(){

        box.useItem(player);

        assertEquals("box", player.getPp().obj[box.index].name);
    }

    @Test
    public void useItemTest_changeCatLeftUi(){

        box.useItem(player);

        assertEquals(1, player.getPp().getUi().getCatLeft());
    }

    @Test
    public void useItemTest_removeCatFromMap(){

        box.useItem(player);

        assertNull(player.getPp().npc[box.index]);
    }

}