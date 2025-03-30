package main.java.com.campoid.MainApp;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player {
    private double x = 0;
    private double y = 0;
    private double dx = 0;
    private double dy = 0;
    private double ax = 0;
    private double ay = 0;
    private double width = 50;
    private double height = 50;
    public int worldX = 0;
    public int worldY = 0;
    public CameraKnob cameraKnob;
    private MainApp mainApp;
    private BufferedImage texture;
    public int hunger = 100;
    public int thirst = 100;

    public Player(MainApp mainApp, double x, double y) {
        worldX = (int)x;
        worldY = (int)y;
        this.x = x;
        this.y = y;
        this.mainApp = mainApp;
        cameraKnob = new CameraKnob(x, y, 0, 0);
        texture = mainApp.loadImage("playerPlaceholdr.png");
    }

    public void collideAnimals() {
        for (Animal animal : mainApp.animals) {
            double px = worldX;
            double py = worldY;
            double ax = animal.position.x;
            double ay = animal.position.y;
            double distX = px - ax;
            double distY = py - ay;
            double dist = Math.sqrt(distX * distX + distY * distY);
            if (px + width > ax && px < ax + animal.width && py + height > ay && py < ay + animal.height) {
                animal.health = 0;
                System.out.println("animal health die");
            }
        }
    }

    public void update() {
        collideAnimals();
        this.dx = 0;
        this.dy = 0;
        this.ax = 0;
        this.ay = 0;
        if (System.currentTimeMillis() % 100 == 0) {
            hunger -= 2;
            thirst -= 3;
        }
        if (hunger <= 0 || thirst <= 0) {
            mainApp.gameOver = true;
        }
        if (mainApp.keyH.down) {
            this.dy = 10;
        }
        if (mainApp.keyH.left) {
            this.dx = -10;
        }
        if (mainApp.keyH.up) {
            this.dy = -10;
            //System.out.println("fuck you");
        }
        if (mainApp.keyH.right) {
            this.dx = 10;
        }
        this.worldX += this.dx;
        this.worldY += this.dy;
        this.dx += this.ax;
        this.dy += this.ay;
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(texture, (int) this.x+mainApp.G_WIDTH/2, (int)this.y+mainApp.G_HEIGHT/2, (int)this.width, (int)this.height, null);
        g2.setColor(new Color(0, 250, 0));
        g2.fillRect(50, 50, (int)(((double)hunger / 100) * 250), 30);
        g2.setColor(new Color(0, 0, 250));
        g2.fillRect(50, 100, (int)(((double)thirst / 100) * 250), 30);
        g2.setColor(Color.BLACK);
        g2.drawString("Hunger", 50, 75);
        g2.drawString("Thirst", 50, 125);
        g2.drawString("Player World X: " + worldX, 200, 20);
        g2.drawString("Player World Y: " + worldY, 200, 40);
    }
    public double getX() {
        return this.x;
    }
    public double getY() {
        return this.y;
    }
    public void setX(double x) {
        this.x = x;
    }
    public void setY(double y) {
        this.y = y;
    }
    public double getWidth() {
        return this.width;
    }
    public double getHeight() {
        return this.height;
    }
}
