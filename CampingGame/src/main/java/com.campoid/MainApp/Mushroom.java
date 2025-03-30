package main.java.com.campoid.MainApp;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Mushroom extends Item {
    public Mushroom(MainApp mainApp, String textureName, double x, double y) {
        super(mainApp, textureName, x, y);
    }

    @Override
    public String toString() {
        return "meat";
    }

    @Override
    public void consume() {
        isAlive = false;
        if (MainApp.randInt(1, 2) == 1) {
            mainApp.player1.eat(10);
        } else {
            mainApp.player1.addHealth(-30);
        }
    }
}
