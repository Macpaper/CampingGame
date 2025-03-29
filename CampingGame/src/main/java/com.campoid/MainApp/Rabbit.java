package main.java.com.campoid.MainApp;

import com.sun.tools.javac.Main;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Rabbit extends Animal {
    MainApp mainApp;
    public int worldX;
    public int worldY;
    public Vec2 position;
    public Vec2 velocity;

    private BufferedImage texture;
    private GameTimer moveTimer;
    public Rabbit(MainApp mainApp, int worldX, int worldY) {
        super(mainApp, "meat.png", new Vec2(worldX, worldY));
        this.mainApp = mainApp;
        position = new Vec2(worldX, worldY);
        velocity = new Vec2(3, 3);

        texture = mainApp.loadImage("meat.png");

        Runnable callback = () -> {
            velocity.x = (int)Math.round(Math.random() * 10 - 5);
            velocity.y = (int)Math.round(Math.random() * 10 - 5);
        };
        moveTimer = new GameTimer(2000, 100, callback);
        moveTimer.start();
    }

    @Override
    public void move(Vec2 direction) {

    }

    @Override
    public void eat() {

    }

    public void update() {
        position = position.add(velocity);
//        position.x += velocity.x;
//        position.y += velocity.y;
        moveTimer.update();
    }
    public void draw(Graphics2D g2) {
        g2.drawImage(texture, (int)position.x - mainApp.player1.worldX, (int)position.y - mainApp.player1.worldY, 50, 50, null);
    }
}
