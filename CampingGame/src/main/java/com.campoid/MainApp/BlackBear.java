package main.java.com.campoid.MainApp;

import javax.swing.text.Position;
import java.awt.*;

public class BlackBear extends Animal implements Predator {

    private GameTimer moveTimer;
    public AIState state;
    int worldX;
    int worldY;
    public BlackBear(MainApp mainApp, int worldX, int worldY) {
        super(mainApp, "blackBear.png", new Vec2(worldX, worldY), 100, 100, 20);
        this.worldX = worldX;
        this.worldY = worldY;
        state = AIState.SLEEPING;
        Runnable callback = () -> {
            int numRand = MainApp.randInt(1, 3);
            if (numRand == 1) {
                state = AIState.SLEEPING;
                velocity.x = 0;
                velocity.y = 0;
            }
            if (numRand == 2) {
                state = AIState.AGGRO;
            }
            if (numRand == 3) {
                state = AIState.WANDERING;
                int speed = 2;
                velocity.x = (int)Math.round(Math.random() * speed - speed/2);
                velocity.y = (int)Math.round(Math.random() * speed - speed/2);
            }
            int dx = mainApp.player1.worldX - worldX;
            int dy = mainApp.player1.worldY - worldY;
            if (Math.sqrt(dx * dx + dy * dy) < 300)  {
                state = AIState.AGGRO;
            }
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
        if (state == AIState.AGGRO) {
            int px = mainApp.player1.worldX;
            int py = mainApp.player1.worldY;
            Vec2 dir = new Vec2(px - worldX, py - worldY);
            dir = dir.normalize();
            velocity.x = dir.x * 5;
            velocity.y = dir.y * 5;
        }
        super.update();
        int dx = mainApp.player1.worldX - worldX;
        int dy = mainApp.player1.worldY - worldY;
        if (Math.sqrt(dx * dx + dy * dy) < 300)  {
            state = AIState.AGGRO;
        }

        moveTimer.update();
    }
    public void draw(Graphics2D g2) {
        super.draw(g2);
    }
    @Override
    public String toString() {
        return "blackbear";
    }
}
