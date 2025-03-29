package main.java.com.campoid.MainApp;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Tile {
    public int x, y, row, col, width, height, count;
    Color colors[] = {Color.GRAY, Color.RED, Color.BLUE, Color.GREEN };
    public BufferedImage texture;

    public Color color;
    public Tile(int x, int y, int row, int col, int width, int height, int count) {
        String texturePath = "";
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
        try {
            texture = ImageIO.read(new File(texturePath));
            texture = transformToIsometric(texture);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {

    }

    private BufferedImage transformToIsometric(BufferedImage image) {
        int isoWidth = width;
        int isoHeight = height / 2;
        BufferedImage isometricImage = new BufferedImage(isoWidth, isoHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = isometricImage.createGraphics();

        AffineTransform transform = new AffineTransform();
        transform.translate(isoWidth / 2.0, 0);
        transform.shear(-0.5, 0.5);
        transform.scale(1.0, 0.5);

        g2d.drawImage(image, transform, null);
        g2d.dispose();
        return isometricImage;
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
//        g2.setColor(color);
//        g2.fillPolygon(xPoints, yPoints, 4);
//        g2.setColor(Color.BLACK);
//        g2.drawPolygon(xPoints, yPoints, 4);
        g2.drawImage(texture, isoX - (width / 2), isoY, null);
    }
}
