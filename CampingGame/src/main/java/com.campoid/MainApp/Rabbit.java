package main.java.com.campoid.MainApp;

import com.sun.tools.javac.Main;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Rabbit {
    MainApp mainApp;
    public int worldX;
    public int worldY;
    public Vec2 position;
    public Vec2 velocity;

    private BufferedImage texture;
    public Rabbit(MainApp mainApp, int worldX, int worldY) {
        this.mainApp = mainApp;
//        this.worldX = worldX;
//        this.worldY = worldY;
        position = new Vec2(worldX, worldY);
        velocity = new Vec2(3, 3);

        texture = mainApp.loadImage("meat.png");
    }
    public void update() {
        position = position.add(velocity);
//        position.x += velocity.x;
//        position.y += velocity.y;
    }
    public void draw(Graphics2D g2) {
        g2.drawImage(texture, (int)position.x - mainApp.player1.worldX, (int)position.y - mainApp.player1.worldY, 50, 50, null);
    }
}
