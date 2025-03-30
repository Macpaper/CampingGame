package main.java.com.campoid.MainApp;

import java.awt.*;

public class BrownBear extends Animal implements Predator{
    private GameTimer moveTimer;
    public boolean aggro = false;
    public BrownBear(MainApp mainApp, int worldX, int worldY) {
        super(mainApp, "BB.png", new Vec2(worldX, worldY), 50, 50, 50);

        Runnable callback = () -> {
            int speed = 5;
            if (!aggro) {
//                velocity.x = (int)Math.round(Math.random() * speed - speed/2);
//                velocity.y = (int)Math.round(Math.random() * speed - speed/2);
            }
        };
        moveTimer = new GameTimer(5000, 1000000, callback);
        moveTimer.start();
    }

    private void handleAggro() {
        double dx = mainApp.player1.worldX - position.x;
        double dy = mainApp.player1.worldY - position.y;

        double dist = Math.sqrt(dx * dx + dy * dy);
        if (dist < 300) {
            aggro = true;
        } else {
            aggro = false;
        }

        if (aggro) {
//            velocity.x =
        }
    }

    @Override
    public void move(Vec2 direction) {

    }

    @Override
    public void eat() {

    }

    public void update() {
        super.update();
        handleAggro();
        moveTimer.update();
    }
    public void draw(Graphics2D g2) {
        super.draw(g2);
    }

    @Override
    public String toString() {
        return "brownBear";
    }
}
