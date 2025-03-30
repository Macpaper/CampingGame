package main.java.com.campoid.MainApp;

import javax.swing.text.Position;
import java.awt.*;

public class BlackBear extends Animal implements Predator {

    private GameTimer moveTimer;

    public BlackBear(MainApp mainApp, int worldX, int worldY) {
        super(mainApp, "blackBear.png", new Vec2(worldX, worldY), 50, 50);

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
