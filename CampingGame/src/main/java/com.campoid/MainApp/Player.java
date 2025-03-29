package main.java.com.campoid.MainApp;

import java.awt.*;

public class Player {
    private double x = 0;
    private double y = 0;
    private double width = 50;
    private double height = 50;
    public Player(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void update() {

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
}
