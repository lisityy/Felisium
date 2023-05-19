package cvut.fel.pjv.pimenol1.utils;

import cvut.fel.pjv.pimenol1.main.Constants;
import cvut.fel.pjv.pimenol1.main.GamePanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseHendler implements MouseListener, MouseMotionListener {
    GamePanel gp;

    public MouseHendler(GamePanel gp) {
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
            }
        }
    }
}
