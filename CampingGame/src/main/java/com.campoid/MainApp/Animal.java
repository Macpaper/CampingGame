package main.java.com.campoid.MainApp;

import javax.swing.text.Position;
import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Animal {
    protected int health;
    public Vec2 position;
    public Vec2 velocity;
    protected boolean isAlive;
    protected int speed;
    public BufferedImage texture;
    protected int width;
    protected int height;

    public MainApp mainApp;
    public Animal(MainApp mainApp, String textureName, Vec2 position) {
        this.mainApp = mainApp;
        this.position = position;
        this.velocity = new Vec2(0, 0);
        this.isAlive = true;
        this.health = 100;
        texture = mainApp.loadImage(textureName);
    }
    public abstract void move(Vec2 direction);
    public abstract void eat();
    public void update() {
        position = position.add(velocity);
    }
    public void draw(Graphics2D g2) {
//        g2.drawImage(texture, (int)(position.x - mainApp.player1.worldX), (int)(position.y - mainApp.player1.worldX), width, height, null);
        g2.drawImage(texture, (int)position.x - mainApp.player1.worldX, (int)position.y - mainApp.player1.worldY, 50, 50, null);
    }
    public void setHealth(int health) {
        this.health = health;
    }
    public int getHealth() {
        return health;
    }
    public Vec2 getPosition() {
        return position;
    }
    public void setPosition(Vec2 position) {
        this.position = position;
    }
}
