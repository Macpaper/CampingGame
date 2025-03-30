package main.java.com.campoid.MainApp;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Item extends Entity {
    protected boolean isAlive;
    public BufferedImage texture;
    public boolean drawText = false;

    protected boolean inInventory = false;
    protected int inIndex = 0;
    public MainApp mainApp;
    String textureName;
    public Item(MainApp mainApp, String textureName, double x, double y) {
        super(x, y, 50, 50);
        this.mainApp = mainApp;
        this.isAlive = true;
        this.textureName = textureName;
        if (textureName != "none") {
            texture = mainApp.loadImage(textureName);
        }
    }
    public void update() {

    }
    public void draw(Graphics2D g2) {
//        g2.drawImage(texture, `(int)(position.x - mainApp.player1.worldX), (int)(position.y - mainApp.player1.worldX), width, height, null);

        if (!inInventory) {
            int screenX = (int)position.x - mainApp.player1.worldX + mainApp.G_WIDTH / 2;
            int screenY = (int)position.y - mainApp.player1.worldY + mainApp.G_HEIGHT / 2;
            if (textureName != "none") {
                g2.drawImage(texture, screenX, screenY, 50, 50, null);
            }
//            g2.drawString("World X: " + position.x, screenX, screenY - 20);
//            g2.drawString("World Y: " + position.y, screenX, screenY);
            if (drawText) {
                g2.setColor(Color.BLACK);
                g2.setFont(MainApp.textFontPlain);
                g2.drawString("E to pick up", screenX, screenY);
            }
        } else {
            position.x = 99999;
            position.y = 99999;
            int drawX = 400 + inIndex * 55;
            if (textureName != "none") {
                g2.drawImage(texture, drawX, 700, 50, 50, null);
            }
            g2.setFont(MainApp.textFontBoldSmall);
            g2.drawString(""+(inIndex + 1), drawX, 715);

        }
    }
    public Vec2 getPosition() {
        return position;
    }
    public void setPosition(Vec2 position) {
        this.position = position;
    }
    public void moveToPlayerInventory(boolean in, int index) {
        inInventory = in;
        inIndex = index;
    }
    public abstract void consume();
}
