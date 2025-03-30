package main.java.com.campoid.MainApp;

import java.awt.*;

public class BrownBear extends Animal implements Predator{
    private GameTimer moveTimer;

    public BrownBear(MainApp mainApp, int worldX, int worldY) {
        super(mainApp, "BB.png", new Vec2(worldX, worldY));

        Runnable callback = () -> {
            int speed = 5;
            velocity.x = (int)Math.round(Math.random() * speed - speed/2);
            velocity.y = (int)Math.round(Math.random() * speed - speed/2);
        };
        moveTimer = new GameTimer(5000, 1000000, callback);
        moveTimer.start();
    }

    @Override
    public void move(Vec2 direction) {

    }

    @Override
    public void eat() {

    }

    public void update() {
        super.update();
        moveTimer.update();
    }
    public void draw(Graphics2D g2) {
        super.draw(g2);
    }
}
