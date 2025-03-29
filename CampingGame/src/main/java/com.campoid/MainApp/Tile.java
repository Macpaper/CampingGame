package main.java.com.campoid.MainApp;

import java.awt.*;
import java.util.Random;

public class Tile {
    public int x, y, row, col, width, height, count;
    Color colors[] = {Color.GRAY, Color.RED, Color.BLUE, Color.GREEN };

    public Color color;
    public Tile(int x, int y, int row, int col, int width, int height, int count) {
        this.x = x;
        this.y = y;
        this.row = row;
        this.col = col;
        this.width = width;
        this.height = height;
        this.count = count;
        Random random = new Random();
        int randomIndex = random.nextInt(colors.length);
        this.color = colors[randomIndex];

    }

    public void update() {

    }

    public void draw(Graphics2D g2, int offsetX, int offsetY) {
        int isoX = (col - row) * (width / 2) - offsetX;
        int isoY = (col + row) * (height / 4) - offsetY;
        int[] xPoints = {
          isoX,
          isoX + width / 2,
          isoX,
          isoX - width / 2
        };

        int[] yPoints = {
          isoY,
          isoY + height / 4,
          isoY + height / 2,
          isoY + height / 4
        };
        g2.setColor(color);
        g2.fillPolygon(xPoints, yPoints, 4);
        g2.setColor(Color.BLACK);
        g2.drawPolygon(xPoints, yPoints, 4);
    }
}
