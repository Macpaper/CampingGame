package main.java.com.campoid.MainApp;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;

public class MainApp extends JPanel implements Runnable {

    public final int G_WIDTH = 1280;
    public final int G_HEIGHT = 960;
    Dimension dimension = new Dimension(G_WIDTH, G_HEIGHT);
    Thread thread = new Thread(this);
    public boolean running = true;
    public boolean gameOver = false;
    BufferedImage test1;
    MouseHandler mouseH = new MouseHandler();
    KeyHandler keyH = new KeyHandler();

    Player player1 = new Player(this, G_WIDTH / 2, G_HEIGHT / 2);
    Map map = new Map(this);
    InventoryGrid inventory = new InventoryGrid();
    ArrayList<Tree> trees = new ArrayList<>();
    ArrayList<Rabbit> rabbits = new ArrayList<>();
    ArrayList<Animal> animals = new ArrayList<>();

    public MainApp() {
        setFocusable(true);
        setPreferredSize(dimension);
        addKeyListener(keyH);
        addMouseListener(mouseH);
        addMouseMotionListener(mouseH);
        try {
            URL imageURL = getClass().getResource("/images/dirt.png");
            System.out.println(imageURL.toString());
            test1 = ImageIO.read(imageURL);
            System.out.println("Loaded image");
        } catch (Exception e) {
            e.printStackTrace();
        }
        thread.start();
        generateTrees();
        for (int i = 0; i < 30; i++) {
            int x = (int)Math.round(Math.random() * 1000);
            int y = (int)Math.round(Math.random() * 1000);
            Rabbit r = new Rabbit(this, x, y);
            rabbits.add(r);
        }
        for (int i = 0; i < 30; i++) {
            int x = (int)Math.round(Math.random() * 1000);
            int y = (int)Math.round(Math.random() * 1000);
            BlackBear r = new BlackBear(this, x, y);
            animals.add(r);
        }
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
        if (!gameOver) {
            player1.update();
        }
        player1.update();
        inventory.update();
        for (Tree tree : trees) {
            tree.update();
        }
        for (Rabbit rabbit : rabbits) {
            rabbit.update();
        }
        for (Animal animal : animals) {
            animal.update();
        }
    }
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.RED);
        g2.fillRect(0, 0, G_WIDTH, G_HEIGHT);
        map.draw(g2);
        player1.draw(g2);
        inventory.draw(g2);
        g2.drawImage(test1, 0, 0, null);
//        trees.sort(Comparator.comparingInt(tree -> tree.y));
        for (Tree tree : trees) {
            tree.draw(g2);
        }
        for (Rabbit tree : rabbits) {
            tree.draw(g2);
        }
        for (Animal animal : animals) {
            animal.draw(g2);
        }
    }

    public BufferedImage loadImage(String imageName) {
        BufferedImage texture;
        try {
            URL imageURL = getClass().getResource("/images/" + imageName);
            texture = ImageIO.read(imageURL);
            System.out.println("Loaded tree");
            return texture;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    private void generateTrees() {
        for (int i = 0; i < 20; i++) {
            Tree t = new Tree(this, (int)Math.round(Math.random() * 1000), (int)Math.round(Math.random() * 1000));
            trees.add(t);
        }
    }
}
