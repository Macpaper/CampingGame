package main.java.com.campoid.MainApp;

import javax.swing.*;
import java.awt.*;

public class MainApp extends JPanel implements Runnable {

    public final int G_WIDTH = 1280;
    public final int G_HEIGHT = 960;
    Dimension dimension = new Dimension(G_WIDTH, G_HEIGHT);
    Thread thread = new Thread(this);
    public boolean running = true;

    MouseHandler mouseH = new MouseHandler();
    KeyHandler keyH = new KeyHandler();

    Player player1 = new Player(this, G_WIDTH / 2, G_HEIGHT / 2);
    Map map = new Map(this);

    public MainApp() {
        setFocusable(true);
        setPreferredSize(dimension);
        addKeyListener(keyH);
        addMouseListener(mouseH);
        addMouseMotionListener(mouseH);
        thread.start();
    }

    @Override
    public void run() {
        while (running) {
            update(); // update game logic
            repaint(); // draw visuals of game
            try {
                Thread.sleep(33);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void update() {
        map.update();
        player1.update();
    }
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.RED);
        g2.fillRect(0, 0, G_WIDTH, G_HEIGHT);
        map.draw(g2);
        player1.draw(g2);
    }
}
