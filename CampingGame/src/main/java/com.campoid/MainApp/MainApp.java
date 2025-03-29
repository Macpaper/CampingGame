package main.java.com.campoid.MainApp;

import javax.swing.*;
import java.awt.*;

public class MainApp extends JPanel implements Runnable {

    public final int G_WIDTH = 800;
    public final int G_HEIGHT = 600;
    Dimension dimension = new Dimension(G_WIDTH, G_HEIGHT);
    Thread thread = new Thread(this);
    public boolean running = true;

    public MainApp() {
        setPreferredSize(dimension);

        thread.start();
    }

    @Override
    public void run() {
        while (running) {
            update(); // update game logic
            repaint(); // draw visuals of game
            try {
                Thread.sleep(17);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void update() {

    }
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.RED);
        g2.fillRect(0, 0, G_WIDTH, G_HEIGHT);
    }
}
