package main.java.com.campoid.MainApp;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;

public class Tree {
    private MainApp mainApp;
    public int worldX;
    public int worldY;
    public int x = (int) Math.round(Math.random() * 1000);
    public int y = (int) Math.round(Math.random() * 1000);
    public BufferedImage texture;
    public Tree(MainApp mainApp, int worldX, int worldY) {
       this.mainApp = mainApp;
       this.worldX = worldX;
       this.worldY = worldY;
       texture = mainApp.loadImage("tree1.png");
    }

    public void update() {

    }
    public void draw(Graphics2D g2) {
        g2.drawImage(texture, worldX - mainApp.player1.worldX,  worldY - mainApp.player1.worldY, null);
    }
}
