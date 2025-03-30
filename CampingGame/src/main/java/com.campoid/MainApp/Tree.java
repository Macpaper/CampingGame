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
    public boolean isAlive = true;
    public int y = (int) Math.round(Math.random() * 1000);
    public int width = 50;
    public int height = 50;
    public int health = 3;
    public BufferedImage texture;
    public Tree(MainApp mainApp, int worldX, int worldY, int width, int height) {
       this.mainApp = mainApp;
       this.worldX = worldX;
       this.worldY = worldY;
       this.width = width;
       this.height = height;
       texture = mainApp.loadImage("tree" + MainApp.randInt(1, 4) +".png");
    }

    public void update() {
        if (health <= 0) {
            isAlive = false;
            Log l = new Log(mainApp, "wood.png", worldX + width / 2, worldY + height);
            mainApp.items.add(l);
            mainApp.explosions.add(new ParticleExplosion(mainApp, worldX, worldY, new Color(110, 38, 14)));
        }
    }
    public void draw(Graphics2D g2) {
        int screenX = worldX - mainApp.player1.worldX + mainApp.G_WIDTH / 2;
        int screenY = worldY - mainApp.player1.worldY + mainApp.G_HEIGHT / 2;
        g2.drawImage(texture, screenX, screenY, width, height, null);
        // DEBUG
//        g2.drawString("World X: " + worldX, screenX, screenY);
//        g2.drawString("World Y: " + worldY, screenX, screenY + 20);
    }
}
