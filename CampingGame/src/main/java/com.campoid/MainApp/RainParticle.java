package main.java.com.campoid.MainApp;

import java.awt.*;

public class RainParticle {
    int x, y;
    long timer = System.currentTimeMillis();
    public RainParticle(int x, int y) {
       this.x = x;
       this.y = y;
    }
    boolean deleteMe = false;
    public void update() {
        x -= 25;
        y += 35;
        if (System.currentTimeMillis() - timer > 1000) {
            deleteMe = true;
        }
    }
    public void draw(Graphics2D g2) {
        g2.setColor(Color.BLUE);
        g2.drawLine(x, y, x - 5, y + 7);
    }
}
