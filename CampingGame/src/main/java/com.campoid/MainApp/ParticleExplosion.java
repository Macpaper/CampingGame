package main.java.com.campoid.MainApp;

import java.awt.*;
import java.util.ArrayList;

public class ParticleExplosion {
    ArrayList<Particle> particles = new ArrayList<>();
    public boolean deleteMe = false;
    public long deleteTimer = System.currentTimeMillis();
    double x = 0;
    double y = 0;
    MainApp mainApp;
    public ParticleExplosion(MainApp mainApp, double x, double y) {
        this.mainApp = mainApp;
        this.x = x;
        this.y = y;
        for (int i = 0; i < (int)Math.round(Math.random() * 10 + 3); i++) {
            int randX = (int)Math.round(Math.random() * 10 - 5) + (int)x;
            int randY = (int)Math.round(Math.random() * 10 - 5) + (int)y;
            int rdx = (int)Math.round(Math.random() * 10 - 5);
            int rdy = (int)Math.round(Math.random() * -10 - 10);
            particles.add(new Particle(mainApp, randX, randY, rdx, rdy, new Color(250, 250, 250)));
        }
    }
    public ParticleExplosion(MainApp mainApp, double x, double y, Color color) {
        this.mainApp = mainApp;
        this.x = x;
        this.y = y;
        for (int i = 0; i < (int)Math.round(Math.random() * 10 + 3); i++) {
            int randX = (int)Math.round(Math.random() * 10 - 5) + (int)x;
            int randY = (int)Math.round(Math.random() * 10 - 5) + (int)y;
            int rdx = (int)Math.round(Math.random() * 10 - 5);
            int rdy = (int)Math.round(Math.random() * -10 - 10);
            particles.add(new Particle(mainApp, randX, randY, rdx, rdy, color));
        }
    }

    public void update() {
        if (System.currentTimeMillis() - deleteTimer > 1000) {
            deleteMe = true;
        }
        for (Particle p : particles) {
            p.update();
        }
    }
    public void draw(Graphics2D g2) {
        for (Particle p : particles) {
            p.draw(g2);
        }
    }
}
