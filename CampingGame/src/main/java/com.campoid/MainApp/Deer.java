package main.java.com.campoid.MainApp;

import java.awt.*;

public class Deer extends Animal implements Prey{
    private GameTimer moveTimer;

    public Deer(MainApp mainApp, int worldX, int worldY) {
        super(mainApp, "DD.png", new Vec2(worldX, worldY), 40, 40);

        Runnable callback = () -> {
            velocity.x = (int)Math.round(Math.random() * 10 - 5);
            velocity.y = (int)Math.round(Math.random() * 10 - 5);
        };
        moveTimer = new GameTimer(2000, 1000000, callback);
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
