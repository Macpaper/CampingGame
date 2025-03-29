package main.java.com.campoid.MainApp;

import javax.swing.text.Position;
import java.awt.*;

public class BlackBear extends Animal implements Predator{
    MainApp mainApp;
    public int worldX;

    public BlackBear(MainApp mainApp, String textureString, Vec2 pos) {
        super(mainApp, textureString, pos);
    }
    @Override
    public void move(Vec2 velocity) {

    }

    @Override
    public void eat() {

    }

    @Override
    public void update() {

    }

    @Override
    public void attack(Animal predator) {

    }
    @Override
    public void draw(Graphics2D g2) {

    }
}
