package cvut.fel.pjv.pimenol1.utils;

import cvut.fel.pjv.pimenol1.main.Constants;
import cvut.fel.pjv.pimenol1.main.Felisium;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseHendler implements MouseListener, MouseMotionListener {
    Felisium gp;

    public MouseHendler(Felisium gp) {
        this.gp=gp;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        switch (Constants.gameState){
            case MAINMENU ->{
                gp.getMainMenuPage().mousePressed(e);
            }
            case PLAY -> {
                gp.getPlayingPage().player.bag.mousePressed(e);
                    gp.getPlayingPage().getUi().mousePressed(e);
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        switch (Constants.gameState){
            case MAINMENU ->{
                gp.getMainMenuPage().mouseReleased(e);
            }
            case PLAY -> {
                gp.getPlayingPage().player.bag.mouseReleased(e);
                    gp.getPlayingPage().getUi().mouseReleased(e);
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        switch (Constants.gameState){
            case MAINMENU ->{
                gp.getMainMenuPage().mouseMoved(e);
            }
            case PLAY -> {
                gp.getPlayingPage().player.bag.mouseMoved(e);
                    gp.getPlayingPage().getUi().mouseMoved(e);
            }
        }
    }
}
