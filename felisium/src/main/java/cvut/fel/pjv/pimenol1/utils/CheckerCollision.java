package cvut.fel.pjv.pimenol1.utils;

import cvut.fel.pjv.pimenol1.background.TileManager;
import cvut.fel.pjv.pimenol1.entity.Entity;
import cvut.fel.pjv.pimenol1.background.Tile;
import cvut.fel.pjv.pimenol1.entity.Player;
import cvut.fel.pjv.pimenol1.main.Constants;
import cvut.fel.pjv.pimenol1.pages.PlayingPage;

public class CheckerCollision {
    /**
     * Checks collision in map.
     * @param entity the entity to check collision for
     * @param tm the tile manager
     */
    public static void checkTile(Entity entity, TileManager tm) {
        int tileSize = Constants.TILE_SIZE;

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
                tileNum1 = tm.getMapData()[leftCol][topRow];
                tileNum2 = tm.getMapData()[rightCol][topRow];
            }
            case "down" -> {
                bottomRow = (bottom + entity.speed) / tileSize;
                tileNum1 = tm.getMapData()[leftCol][bottomRow];
                tileNum2 = tm.getMapData()[rightCol][bottomRow];
            }
            case "left" -> {
                leftCol = (left - entity.speed) / tileSize;
                tileNum1 = tm.getMapData()[leftCol][topRow];
                tileNum2 = tm.getMapData()[leftCol][bottomRow];
            }
            case "right" -> {
                rightCol = (right + entity.speed) / tileSize;
                tileNum1 = tm.getMapData()[rightCol][topRow];
                tileNum2 = tm.getMapData()[rightCol][bottomRow];
            }
            default -> {
                return;
            }
        }

        Tile[] tiles = tm.getTiles();
        if (tiles[tileNum1].isCollision() || tiles[tileNum2].isCollision()) {
            if (!entity.isHaveWing()) {
                entity.collisionOn = true;
            } else if (tileNum1 == 18 || tileNum2 == 18) {
                entity.collisionOn = true;
            }
        }
    }

    /**
     * Checks collision with objects.
     * @param entity the entity to check collision for
     * @param isPlayer true if the entity is a player, false otherwise
     * @param pp the PlayingPage instance
     * @return the index of the collided object
     */
    public static int checkObject(Entity entity, boolean isPlayer, PlayingPage pp) {
        int index = 999;
        for (int i = 0; i < pp.obj.length; i++) {

            if (pp.obj[i] != null) {
                entity.hitBox.x = entity.xWorld + entity.hitBox.x;
                entity.hitBox.y = entity.yWorld + entity.hitBox.y;

                pp.obj[i].getHitBox().x = pp.obj[i].worldX + pp.obj[i].getHitBox().x;
                pp.obj[i].getHitBox().y = pp.obj[i].worldY + pp.obj[i].getHitBox().y;
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

                if (entity.hitBox.intersects(pp.obj[i].getHitBox())) {
                    if (pp.obj[i].collision) {
                        entity.collisionOn = true;
                    }
                    if (isPlayer) {
                        index = i;
                    }
                }

                entity.hitBox.x = entity.getDefaultHitBoxX();
                entity.hitBox.y = entity.getDefaultHitBoxY();

                pp.obj[i].getHitBox().x = pp.obj[i].getDefaultHitBoxX();
                pp.obj[i].getHitBox().y = pp.obj[i].getDefaultHitBoxY();
            }

        }

        return index;
    }

    /**
     * Checks collision entity with entities.
     * @param entity the entity to check collision for
     * @param npc the array of NPCs
     * @return the index of the collided entity
     */
    public static int checkEntity(Entity entity, Entity[] npc) {
        int index = 999;
        for (int i = 0; i < npc.length; i++) {

            if (npc[i] != null) {
                entity.hitBox.x = entity.xWorld + entity.hitBox.x;
                entity.hitBox.y = entity.yWorld + entity.hitBox.y;

                npc[i].hitBox.x = npc[i].xWorld + npc[i].hitBox.x;
                npc[i].hitBox.y = npc[i].yWorld + npc[i].hitBox.y;
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

                if (entity.hitBox.intersects(npc[i].hitBox)) {
                    if (npc[i] != entity) {
                        entity.collisionOn = true;
                        index = i;
                    }
                }

                entity.hitBox.x = entity.getDefaultHitBoxX();
                entity.hitBox.y = entity.getDefaultHitBoxY();

                npc[i].hitBox.x = npc[i].getDefaultHitBoxX();
                npc[i].hitBox.y = npc[i].getDefaultHitBoxY();
            }

        }

        return index;
    }

    /**
     * Checks collision with the player.
     * @param entity the entity to check collision for
     * @param player the player entity
     * @return true if collision with player occurred, false otherwise
     */
    public static boolean checkPlayer(Entity entity, Player player) {

        boolean contactPlayer = false;

        entity.hitBox.x = entity.xWorld + entity.hitBox.x;
        entity.hitBox.y = entity.yWorld + entity.hitBox.y;

        player.hitBox.x = player.xWorld + player.hitBox.x;
        player.hitBox.y = player.yWorld + player.hitBox.y;

        switch (entity.direction) {
            case "up" -> entity.hitBox.y -= entity.speed;
            case "down" -> entity.hitBox.y += entity.speed;
            case "left" -> entity.hitBox.x -= entity.speed;
            case "right" -> entity.hitBox.x += entity.speed;
        }
        if (entity.hitBox.intersects(player.hitBox)) {
            entity.collisionOn = true;
            contactPlayer = true;
        }

        entity.hitBox.x = entity.getDefaultHitBoxX();
        entity.hitBox.y = entity.getDefaultHitBoxY();
        player.hitBox.x = player.getDefaultHitBoxX();
        player.hitBox.y = player.getDefaultHitBoxY();

        return contactPlayer;
    }

}
