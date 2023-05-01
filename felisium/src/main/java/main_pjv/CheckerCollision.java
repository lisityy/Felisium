package main_pjv;

import Entity.Entity;

public class CheckerCollision {
    GamePannel gp;

    public CheckerCollision(GamePannel gp) {
        this.gp = gp;
    }

//    public void checkTile(Entity entity) {
//        entity.collitionOn = false;
//        int entityLeftWorldX = entity.xWorld + entity.hitBox.x;
//        int entityRightWorldX = entity.xWorld + entity.hitBox.x + entity.hitBox.width;
//        int entityTopWorldY = entity.yWorld + entity.hitBox.y;
//        int entityBottomWorldY = entity.yWorld + entity.hitBox.y + entity.hitBox.height;
//
//        int entityLeftCol = entityLeftWorldX / gp.getTileSize();
//        int entityRightCol = entityRightWorldX / gp.getTileSize();
//        int entityUpRow = entityTopWorldY / gp.getTileSize();
//        int entityDownRow = entityBottomWorldY / gp.getTileSize();
//
//        int tileNum1, tileNum2;
//
//        switch (entity.direction) {
//            case "up" -> {
//                entityUpRow = (entityTopWorldY - entity.speed) / gp.getTileSize();
//                tileNum1 = gp.gettManager().getMapData()[entityLeftCol][entityUpRow];
//                tileNum2 = gp.gettManager().getMapData()[entityRightCol][entityUpRow];
//                if (gp.gettManager().getTiles()[tileNum1].collision || gp.gettManager().getTiles()[tileNum2].collision) {
//                    entity.collitionOn = true;
//                }
//            }
//            case "down" -> {
//                entityDownRow = (entityBottomWorldY + entity.speed) / gp.getTileSize();
//                tileNum1 = gp.gettManager().getMapData()[entityLeftCol][entityDownRow];
//                tileNum2 = gp.gettManager().getMapData()[entityRightCol][entityDownRow];
//                if (gp.gettManager().getTiles()[tileNum1].collision || gp.gettManager().getTiles()[tileNum2].collision) {
//                    entity.collitionOn = true;
//                }
//            }
//            case "left" -> {
//                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.getTileSize();
//                tileNum1 = gp.gettManager().getMapData()[entityLeftCol][entityUpRow];
//                tileNum2 = gp.gettManager().getMapData()[entityLeftCol][entityDownRow];
//                if (gp.gettManager().getTiles()[tileNum1].collision || gp.gettManager().getTiles()[tileNum2].collision) {
//                    entity.collitionOn = true;
//                }
//            }
//            case "right" -> {
//                entityRightCol = (entityRightWorldX + entity.speed) / gp.getTileSize();
//                tileNum1 = gp.gettManager().getMapData()[entityRightCol][entityUpRow];
//                tileNum2 = gp.gettManager().getMapData()[entityRightCol][entityDownRow];
//                if (gp.gettManager().getTiles()[tileNum1].collision || gp.gettManager().getTiles()[tileNum2].collision) {
//                    entity.collitionOn = true;
//                }
//
//            }
//            default -> entity.collitionOn = false;
//        }

//    }

    public void checkTile(Entity entity) {
        entity.collitionOn = false;
        int entityLeftWorldX = entity.xWorld + entity.hitBox.x;
        int entityRightWorldX = entity.xWorld + entity.hitBox.x + entity.hitBox.width;
        int entityTopWorldY = entity.yWorld + entity.hitBox.y;
        int entityBottomWorldY = entity.yWorld + entity.hitBox.y + entity.hitBox.height;

        int entityLeftCol = entityLeftWorldX / gp.getTileSize();
        int entityRightCol = entityRightWorldX / gp.getTileSize();
        int entityUpRow = entityTopWorldY / gp.getTileSize();
        int entityDownRow = entityBottomWorldY / gp.getTileSize();

        int tileNum1, tileNum2;
        switch (entity.direction) {
            case "up":
                entityUpRow = (entityTopWorldY - entity.speed) / gp.getTileSize();
                if (entityUpRow >= 0 && entityUpRow < gp.getMaxWorldRow()) {
                    tileNum1 = gp.gettManager().getMapData()[entityLeftCol][entityUpRow];
                    tileNum2 = gp.gettManager().getMapData()[entityRightCol][entityUpRow];
                    if (tileNum1 >= 0 && tileNum1 < gp.gettManager().getTiles().length && gp.gettManager().getTiles()[tileNum1].collision
                            || tileNum2 >= 0 && tileNum2 < gp.gettManager().getTiles().length && gp.gettManager().getTiles()[tileNum2].collision) {
                        entity.collitionOn = true;
                    }
                }
                break;
            case "down":
                entityDownRow = (entityBottomWorldY + entity.speed) / gp.getTileSize();
                if (entityDownRow >= 0 && entityDownRow < gp.getMaxWorldRow()) {
                    tileNum1 = gp.gettManager().getMapData()[entityLeftCol][entityDownRow];
                    tileNum2 = gp.gettManager().getMapData()[entityRightCol][entityDownRow];
                    if (tileNum1 >= 0 && tileNum1 < gp.gettManager().getTiles().length && gp.gettManager().getTiles()[tileNum1].collision
                            || tileNum2 >= 0 && tileNum2 < gp.gettManager().getTiles().length && gp.gettManager().getTiles()[tileNum2].collision) {
                        entity.collitionOn = true;
                    }
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.getTileSize();
                if (entityLeftCol >= 0 && entityLeftCol < gp.getMaxWorldCol()) {
                    tileNum1 = gp.gettManager().getMapData()[entityLeftCol][entityUpRow];
                    tileNum2 = gp.gettManager().getMapData()[entityLeftCol][entityDownRow];
                    if (tileNum1 >= 0 && tileNum1 < gp.gettManager().getTiles().length && gp.gettManager().getTiles()[tileNum1].collision
                            || tileNum2 >= 0 && tileNum2 < gp.gettManager().getTiles().length && gp.gettManager().getTiles()[tileNum2].collision) {
                        entity.collitionOn = true;
                    }
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed) / gp.getTileSize();
                if(entityRightCol>=0 && entityRightCol < gp.getMaxWorldCol()){
                    tileNum1 = gp.gettManager().getMapData()[entityRightCol][entityUpRow];
                    tileNum2 = gp.gettManager().getMapData()[entityRightCol][entityDownRow];
                    if (tileNum1 >= 0 && tileNum1 < gp.gettManager().getTiles().length && gp.gettManager().getTiles()[tileNum1].collision
                            || tileNum2 >= 0 && tileNum2 < gp.gettManager().getTiles().length && gp.gettManager().getTiles()[tileNum2].collision) {
                        entity.collitionOn = true;
                    }
                }
                break;
            default:
                entity.collitionOn = false;
                break;
        }
    }

}