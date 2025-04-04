package main.java.com.campoid.MainApp;

import com.sun.tools.javac.Main;

import java.awt.*;

public class Rabbit extends Animal implements Prey {
    private GameTimer moveTimer;

    public Rabbit(MainApp mainApp, int worldX, int worldY) {
        super(mainApp, "rabbit2.png", new Vec2(worldX, worldY), 20, 20, 3);

        width = 50;
        height = 50;
        Runnable callback = () -> {
            velocity.x = (int)Math.round(Math.random() * 10 - 5);
            velocity.y = (int)Math.round(Math.random() * 10 - 5);
//            velocity.x = 0; velocity.y = 0;
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
        if (health <= 0) {
            isAlive = false;
            Meat m = new Meat(mainApp, "meat.png", position.x, position.y);
            mainApp.items.add(m);
            mainApp.explosions.add(new ParticleExplosion(mainApp, position.x, position.y));
        }
        moveTimer.update();
    }
    public void draw(Graphics2D g2) {
        super.draw(g2);
    }

    @Override
    public String toString() {
        return "rabbit";
    }

}
