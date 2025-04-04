package main.java.com.campoid.MainApp;


import java.awt.*;
import java.util.ArrayList;

public class Map {
    private final MainApp mainApp;
    // Map will have an ArrayList of tiles. This is a 1D list where the x, y, and image is stored on the tiles object.
    ArrayList<Tile> tiles = new ArrayList<>();
    // This is the literal number of tiles in the world.
    public int tilesHorizontal = 20;
    public int tileSize = 120;
    public int tilesVertical = 20;
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
                Tile tile = new Tile(mainApp, col * tileSize, row * tileSize, row, col, tileSize, tileSize, count);
                tiles.add(tile);
                count++;
            }
        }
    }

    public int getIsoMapWidth() {
        return 1;
    }

    public int getLeftBound() {
        return ((tilesHorizontal * tileSize) / 2) + (mainApp.G_WIDTH /2);
    }
    public int getRightBound() {
        return ((tilesHorizontal * tileSize) / 2) - (mainApp.G_WIDTH / 2);
    }

    public int getTopBound() {
        return -(tileSize / 4);
    }
    public int getBottomBound() {
        return (tilesVertical * (tileSize / 4)) + (tilesHorizontal * (tileSize / 4)) - (mainApp.G_HEIGHT / 2);
    }

    public void update() {
        for (Tile tile : tiles) {
            tile.update();
        }
        position.x = mainApp.player1.worldX;
        position.y = mainApp.player1.worldY;
//        position.x = Math.max(getLeftBound(), Math.min(mainApp.player1.worldX, getRightBound()));
//        position.y = Math.max(getTopBound(), Math.min(mainApp.player1.worldY, getBottomBound()));
    }

    public void draw(Graphics2D g2) {
//        int offsetX =
        for (Tile tile : tiles) {
            tile.draw(g2, (int)position.x, (int)position.y);
        }
    }
}
