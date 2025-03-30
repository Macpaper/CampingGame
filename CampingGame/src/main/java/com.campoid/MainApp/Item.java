package main.java.com.campoid.MainApp;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Item {
    public Vec2 position;
    protected boolean isAlive;
    public BufferedImage texture;
    protected int width;
    protected int height;

    public MainApp mainApp;
    public Item(MainApp mainApp, String textureName, Vec2 position) {
        this.mainApp = mainApp;
        this.position = position;
        this.isAlive = true;
        texture = mainApp.loadImage(textureName);
    }
    public void update() {

    }
    public void draw(Graphics2D g2) {
//        g2.drawImage(texture, `(int)(position.x - mainApp.player1.worldX), (int)(position.y - mainApp.player1.worldX), width, height, null);
        int screenX = (int)position.x - mainApp.player1.worldX + mainApp.G_WIDTH / 2;
        int screenY = (int)position.y - mainApp.player1.worldY + mainApp.G_HEIGHT / 2;

        g2.drawImage(texture, screenX, screenY, 50, 50, null);
        g2.drawString("World X: " + position.x, screenX, screenY - 20);
        g2.drawString("World Y: " + position.y, screenX, screenY);
    }
    public Vec2 getPosition() {
        return position;
    }
    public void setPosition(Vec2 position) {
        this.position = position;
    }
}
