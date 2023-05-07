package main_pjv;

import Entity.Entity;
import background.Tile;

import java.awt.*;

public class CheckerCollision {
    GamePannel gp;

    public CheckerCollision(GamePannel gp) {
        this.gp = gp;
    }


//    public void checkTile(Entity entity){
//        //ok
//        int entityLeftWorldX = entity.xWorld + entity.hitBox.x;
//        int entityRightWorldX = entity.xWorld + entity.hitBox.x + entity.hitBox.width;
//        int entityTopWorldY = entity.yWorld + entity.hitBox.y;
//        int entityBottomWorldY = entity.yWorld + entity.hitBox.y + entity.hitBox.height;
//
//        int entityLeftCol = entityLeftWorldX / gp.getTileSize();
//        int entityRightCol = entityRightWorldX / gp.getTileSize();
//        int entityTopRow = entityTopWorldY / gp.getTileSize();
//        int entityBottomRow = entityBottomWorldY / gp.getTileSize();
//
//        int tileNum1, tileNum2;
//
//        switch (entity.direction) {
//            case "up" -> {
//                entityTopRow = (entityTopWorldY - entity.speed) / gp.getTileSize();
//                tileNum1 = gp.getTileManager().getMapData()[entityLeftCol][entityTopRow];
//                tileNum2 = gp.getTileManager().getMapData()[entityRightCol][entityTopRow];
//
//                if (gp.getTileManager().getTiles()[tileNum1].isCollision() || gp.getTileManager().getTiles()[tileNum2].isCollision()) {
//                    entity.collitionOn=true;
//                }
//            }
//            case "down" -> {
//                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.getTileSize();
//                tileNum1 = gp.getTileManager().getMapData()[entityLeftCol][entityBottomRow];
//                tileNum2 = gp.getTileManager().getMapData()[entityRightCol][entityBottomRow];
//
//                if (gp.getTileManager().getTiles()[tileNum1].isCollision() || gp.getTileManager().getTiles()[tileNum2].isCollision()) {
//                    entity.collitionOn=true;
//                }
//            }
//            case "left" -> {
//                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.getTileSize();
//                tileNum1 = gp.getTileManager().getMapData()[entityLeftCol][entityTopRow];
//                tileNum2 = gp.getTileManager().getMapData()[entityLeftCol][entityBottomRow];
//
//                if (gp.getTileManager().getTiles()[tileNum1].isCollision() || gp.getTileManager().getTiles()[tileNum2].isCollision()) {
//                    entity.collitionOn=true;
//                }
//
//            }
//            case "right" -> {
//                entityRightCol = (entityRightWorldX + entity.speed) / gp.getTileSize();
//                tileNum1 = gp.getTileManager().getMapData()[entityRightCol][entityTopRow];
//                tileNum2 = gp.getTileManager().getMapData()[entityRightCol][entityBottomRow];
//
//                if (gp.getTileManager().getTiles()[tileNum1].isCollision() || gp.getTileManager().getTiles()[tileNum2].isCollision()) {
//                    entity.collitionOn=true;
//                }
//            }
//        }
//
//    }
public void checkTile(Entity entity) {
    int tileSize = gp.getTileSize();

    int left = entity.xWorld + entity.hitBox.x;
    int right = left + entity.hitBox.width;
    int top = entity.yWorld + entity.hitBox.y;
    int bottom = top + entity.hitBox.height;

    int leftCol = left / tileSize;
    int rightCol = right / tileSize;
    int topRow = top / tileSize;
    int bottomRow = bottom / tileSize;

    int tileNum1, tileNum2;

    switch (entity.direction) {
        case "up" -> {
            topRow = (top - entity.speed) / tileSize;
            tileNum1 = gp.getTileManager().getMapData()[leftCol][topRow];
            tileNum2 = gp.getTileManager().getMapData()[rightCol][topRow];
        }
        case "down" -> {
            bottomRow = (bottom + entity.speed) / tileSize;
            tileNum1 = gp.getTileManager().getMapData()[leftCol][bottomRow];
            tileNum2 = gp.getTileManager().getMapData()[rightCol][bottomRow];
        }
        case "left" -> {
            leftCol = (left - entity.speed) / tileSize;
            tileNum1 = gp.getTileManager().getMapData()[leftCol][topRow];
            tileNum2 = gp.getTileManager().getMapData()[leftCol][bottomRow];
        }
        case "right" -> {
            rightCol = (right + entity.speed) / tileSize;
            tileNum1 = gp.getTileManager().getMapData()[rightCol][topRow];
            tileNum2 = gp.getTileManager().getMapData()[rightCol][bottomRow];

        }
        default -> {
            return;
        }
    }

    Tile[] tiles = gp.getTileManager().getTiles();
    if (tiles[tileNum1].isCollision() || tiles[tileNum2].isCollision()) {
        entity.collitionOn = true;
    }
}
}