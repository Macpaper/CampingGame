package main.java.com.campoid.MainApp;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Random;

public class Tile {
    public int x, y, row, col, width, height, count;
    Color colors[] = {Color.GRAY, Color.RED, Color.BLUE, Color.GREEN };
    public BufferedImage texture;
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
        loadImage();
    }

    private void loadImage() {
        try {
            URL imageURL = getClass().getResource("/images/dirt.png");
            texture = ImageIO.read(imageURL);
            texture = transformToIsometric(texture);
            //System.out.println("Loaded tile image");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update() {

    }

    private BufferedImage transformToIsometric(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        int isoWidth = width + height;
        int isoHeight = (width + height) / 2;
        BufferedImage isometricImage = new BufferedImage(isoWidth, isoHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = isometricImage.createGraphics();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = image.getRGB(x, y);
                int isoX = (x - y) + height;
                int isoY = (x + y) / 2;
                isometricImage.setRGB(isoX, isoY, rgb);
            }
        }
        g2d.dispose();
        return isometricImage;
    }

    public void draw(Graphics2D g2, int offsetX, int offsetY) {

        int isoX = (col - row) * (width / 2) - offsetX;
        int isoY = (col + row) * (height / 4) - offsetY;
        g2.drawImage(texture, isoX, isoY, width + height, (width + height) / 2, null);
    }
}
