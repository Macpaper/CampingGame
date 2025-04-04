package main.java.com.campoid.MainApp;

import java.awt.*;

public class Particle {
    int x = 0;
    int y = 0;
    int dx = 0;
    int dy = 0;
    Color color = new Color(250, 250, 250);
    MainApp mainApp;
    public Particle(MainApp mainApp, int x, int y, int dx, int dy, Color color) {
        this.mainApp = mainApp;
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        this.color = color;
    }

    public void update() {
        x += dx;
        y += dy;
        dy += 2;
    }
    public void draw(Graphics2D g2) {
        g2.setColor(color);
        int screenX = x - mainApp.player1.worldX + mainApp.G_WIDTH / 2;
        int screenY = y - mainApp.player1.worldY + mainApp.G_HEIGHT / 2;
        g2.fillOval(screenX, screenY, 5, 5);
    }
}
