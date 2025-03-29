package main.java.com.campoid.MainApp;

import java.awt.*;

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

    public Player(MainApp mainApp, double x, double y) {
        this.x = x;
        this.y = y;
        this.mainApp = mainApp;
        cameraKnob = new CameraKnob(x, y, 0, 0);
    }

    public void update() {
        this.dx = 0;
        this.dy = 0;
        this.ax = 0;
        this.ay = 0;
        if (mainApp.keyH.down) {
            this.dy = 5;
        }
        if (mainApp.keyH.left) {
            this.dx = -5;
        }
        if (mainApp.keyH.up) {
            this.dy = -5;
            //System.out.println("fuck you");
        }
        if (mainApp.keyH.right) {
            this.dx = 5;
        }
        this.worldX += this.dx;
        this.worldY += this.dy;
        this.dx += this.ax;
        this.dy += this.ay;
    }
    public void draw(Graphics2D g2) {
        g2.setColor(Color.BLUE);
        g2.fillRect((int)this.x, (int)this.y, (int)this.width, (int)this.height);
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
