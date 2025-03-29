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
//    File saveDirectory = new File(System.getProperty("user.dir")+"/assets/images/dirt.png");
    public Color color;
    public Tile(int x, int y, int row, int col, int width, int height, int count) {
//        String texturePath = String.valueOf(Objects.requireNonNull(getClass().getResource("/assets/images/dirt.png")));
//        System.out.println();

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
//        System.out.println("FUCKING LOAD IMAGE");
//        File saveD = new File(System.getProperty("user.dir") + "/assets/images/dirt.png");
//        String path = System.getProperty("user.dir") + "/assets/images/dirt.png";

        try {
            URL imageURL = getClass().getResource("/images/dirt.png");
//            System.out.println(imageURL.toString());
            texture = ImageIO.read(imageURL);
            texture = transformToIsometric(texture);
            System.out.println("Loaded image");
        } catch (Exception e) {
            e.printStackTrace();
        }
//        System.out.println(saveD.getAbsolutePath());

//        System.out.println(String.valueOf(Objects.requireNonNull(getClass().getResource("/"))));
//        try {
//            texture = ImageIO.read(urlDirt);
//        } catch (IOException e) {
//            System.err.println("Failed to load asset: " + e.getMessage());
//            e.printStackTrace();
//        }
//        System.out.println(texture.)
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

//        AffineTransform transform = new AffineTransform();
//        transform.translate(isoWidth / 2.0, 0);
//        transform.shear(-0.5, 0.5);
//        transform.scale(1.0, 0.5);
//
//        g2d.drawImage(texture, transform, null);
//        g2d.dispose();
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
//        g2.drawImage(texture, isoX - (width / 2), isoY, null);
        g2.drawImage(texture, isoX, isoY, width + height, (width + height) / 2, null);
    }
}
