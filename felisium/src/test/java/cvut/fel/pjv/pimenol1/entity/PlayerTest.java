package cvut.fel.pjv.pimenol1.entity;

import cvut.fel.pjv.pimenol1.inventorys.Box;
import cvut.fel.pjv.pimenol1.inventorys.Door;
import cvut.fel.pjv.pimenol1.inventorys.Item;
import cvut.fel.pjv.pimenol1.main.Constants;
import cvut.fel.pjv.pimenol1.main.Felisium;
import cvut.fel.pjv.pimenol1.main.GameState;
import cvut.fel.pjv.pimenol1.pages.PlayingPage;
import cvut.fel.pjv.pimenol1.pages.UI;
import cvut.fel.pjv.pimenol1.utils.KeyHandler;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlayerTest {
    private Player player;
    private PlayingPage playingPage;
    private Felisium felisium;

    @BeforeEach
    public void setUp() {
        felisium = new Felisium();
        playingPage = new PlayingPage(felisium);
        KeyHandler keyHandler = new KeyHandler(felisium);
        player = new Player(playingPage, keyHandler);
    }

    @Test
    public void testPickUpObj_AddsItemToBag() {
        Item box = new Box(0, 0, 0);
        playingPage.obj[0] = box;

        player.pickUpObj(0);

        Assertions.assertEquals(box, player.bag.items.get(0));
        Assertions.assertNull(playingPage.obj[0]);
    }

    @Test
    public void testPickUpObj_DoesNotAddItemToBagIfCannotTake() {
        Item door = new Door(0, 0, 0);
        playingPage.obj[0] = door;

        player.pickUpObj(0);

        Assertions.assertEquals(0, player.bag.items.size());
        Assertions.assertEquals(door, playingPage.obj[0]);
    }

    @Test
    public void testPickUpObj_DoesNotAddItemToBagIfIndexInvalid() {
        player.pickUpObj(999);

        Assertions.assertEquals(0, player.bag.items.size());
    }

    @Test
    public void testUpdate_deth() {
        player.life = 0;
        player.update();
        Assertions.assertEquals(GameState.GAMEOVER, Constants.gameStatePlay);
    }
}
