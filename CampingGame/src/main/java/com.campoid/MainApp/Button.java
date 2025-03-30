package main.java.com.campoid.MainApp;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Button {
    MainApp mainApp;
    int x, y, width, height;
    Runnable callback;
    boolean ran = false;
    BufferedImage texture;
    public Button(MainApp mainApp, int x, int y, int width, int height, String texturePath, Runnable callback) {
        this.mainApp = mainApp;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.callback = callback;
        texture = mainApp.loadImage(texturePath);
    }
    public void update() {
        int mx = mainApp.mouseH.getMouseX();
        int my = mainApp.mouseH.getMouseX();
        if (mx > x && mx < x + width && my > y && my < y + height && !ran) {
            if (mainApp.mouseH.isLeftButtonClicked()) {
                ran = true;
                this.callback.run();
            }
        }
    }
    public void draw(Graphics2D g2) {
        g2.setColor(Color.BLUE);
        g2.drawRect(x, y, width, height);
        g2.drawImage(texture, x, y, width, height, null);
    }
}
