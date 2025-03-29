package main.java.com.campoid.MainApp;


import java.util.ArrayList;

public class Map {
    private MainApp mainApp;
    // Map will have an ArrayList of tiles. This is a 1D list where the x, y, and image is stored on the tiles object.
    ArrayList<Tile> tiles = new ArrayList<>();
    // This is the literal number of tiles in the world.
    public int tilesHorizontal = 50;
    public int tileSize = 64;
    public int tilesVertical = 50;
    public int worldWidth = tilesHorizontal * tileSize;
    public Vec2 position;

    public Map(MainApp mainApp) {
        this.mainApp = mainApp;
        createMap();
        int leftEdge = tiles.get(0).x;
        int rightEdge = tiles.get(0).y;
        position = new Vec2(leftEdge, rightEdge);
    }

    public void createMap() {
        int count = 0;
        for (int row = 0; row < tilesVertical; row++) {
            for (int col = 0; col < tilesHorizontal; col++) {
                Tile tile = new Tile(col * tileSize, row * tileSize, row, col, tileSize, tileSize, count);
                tiles.add(tile);
                count++;
            }
        }
    }

}
