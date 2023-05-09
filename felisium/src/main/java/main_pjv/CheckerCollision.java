package main_pjv;

import Entity.Entity;
import background.Tile;

import java.awt.*;

public class CheckerCollision {
    GamePannel gp;

    public CheckerCollision(GamePannel gp) {
        this.gp = gp;
    }

//    public boolean checkCollision(Entity entity){
//        entity.hitBox.x+=entity.xWorld;
//        entity.hitBox.y+=entity.yWorld;
//        switch (entity.direction){
//            case "up" -> {
//                entity.hitBox.y-= entity.speed;
//            }
//            case "down" -> {
//                entity.hitBox.y+= entity.speed;
//            }
//            case "right" -> {
//                entity.hitBox.x+=entity.speed;
//            }
//            case "left" -> {
//                entity.hitBox.x-= entity.speed;
//            }
//        }
//
//        for (Rectangle r: gp.getTileManager().getListRectTiles()){
//            r.x = r.x - entity.xWorld + entity.xScreen;
//            r.y = r.y - entity.yWorld + entity.yScreen;
//            if(entity.hitBox.intersects(r)){
//                return true;
//            }
//        }
//        return false;
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

    public int checkObject(Entity entity, boolean isPlayer) {
        int index = 999;
        for (int i = 0; i < gp.obj.length; i++) {

            if (gp.obj[i] != null) {
                entity.hitBox.x = entity.xWorld + entity.hitBox.x;
                entity.hitBox.y = entity.yWorld + entity.hitBox.y;

                gp.obj[i].getHitBox().x = gp.obj[i].worldX + gp.obj[i].getHitBox().x;
                gp.obj[i].getHitBox().y = gp.obj[i].worldY + gp.obj[i].getHitBox().y;
                switch (entity.direction) {
                    case "up" -> {
                        entity.hitBox.y -= entity.speed;
                    }
                    case "down" -> {
                        entity.hitBox.y += entity.speed;
                    }
                    case "left" -> {
                        entity.hitBox.x -= entity.speed;
                    }
                    case "right" -> {
                        entity.hitBox.x += entity.speed;
                    }
                }

                if (entity.hitBox.intersects(gp.obj[i].getHitBox())) {
                    if (gp.obj[i].collision) {
                        entity.collitionOn = true;
                    }
                    if (isPlayer) {
                        index = i;
                    }
                }

                entity.hitBox.x = entity.getDefultHitBoxX();
                entity.hitBox.y = entity.getDefultHitBoxY();

                gp.obj[i].getHitBox().x = gp.obj[i].getDefultHitBoxX();
                gp.obj[i].getHitBox().y = gp.obj[i].getDefultHitBoxY();
            }

        }

        return index;
    }
}