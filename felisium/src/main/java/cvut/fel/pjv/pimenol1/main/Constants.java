package cvut.fel.pjv.pimenol1.main;

public class Constants {
    public static final int ORIGINAL_TILE_SIZE = 16;
    public static final int SCALE = 4;
    public static final int TILE_SIZE = SCALE * ORIGINAL_TILE_SIZE;

    //SCREEN SETTINGS
    public static final int MAX_SCREEN_COL = 16;
    public static final int MAX_SCREEN_ROW = 12;
    public static final int SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COL;
    public static final int SCREEN_HIGH = TILE_SIZE * MAX_SCREEN_ROW;

    // WORLD SETTINGS
    public static final int MAX_WORLD_COL = 50;
    public static final int MAX_WORLD_ROW = 50;
    public static final int MAX_WORLD_HIGH = TILE_SIZE * MAX_WORLD_ROW;
    public static final int MAX_WORLD_WIDTH = TILE_SIZE * MAX_WORLD_COL;

    public static final int FPS = 100;

    public static GameState gameState;
    public static GameState gameStatePlay;



}
