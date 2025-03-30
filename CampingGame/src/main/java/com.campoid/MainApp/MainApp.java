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
import java.util.HashMap;
import java.util.Objects;

public class MainApp extends JPanel implements Runnable {

    public final int G_WIDTH = 1280;
    public final int G_HEIGHT = 960;
    Dimension dimension = new Dimension(G_WIDTH, G_HEIGHT);
    Thread thread = new Thread(this);
    public boolean running = true;
    public static Font textFontBold = new Font("Arial", Font.BOLD, 25);
    public static Font textFontBoldSmall = new Font("Arial", Font.BOLD, 15);
    public static Font textFontPlain = new Font("Arial", Font.PLAIN, 20);
    public boolean gameOver = false;
    BufferedImage test1;
    MouseHandler mouseH = new MouseHandler();
    KeyHandler keyH = new KeyHandler();

    InventoryGrid inventory = new InventoryGrid(this);
    Player player1 = new Player(this, 0, 0, inventory);
    Map map;
    ArrayList<Tree> trees = new ArrayList<>();
    ArrayList<Animal> animals = new ArrayList<>();
    ArrayList<Item> items = new ArrayList<>();
    ArrayList<ParticleExplosion> explosions = new ArrayList<>();
    public HashMap<String, BufferedImage> imageMap = new HashMap<>();
    public BufferedImage dirtImage;

    private void loadImages() {
        try {
            URL imageURL = getClass().getResource("/images/dirt.png");
            dirtImage = ImageIO.read(imageURL);
            dirtImage = transformToIsometric(dirtImage);
            System.out.println("Loaded tile image");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    private BufferedImage transformToIsometric(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        int isoWidth = width + height;
        int isoHeight = (width + height) / 2;
        BufferedImage isometricImage = new BufferedImage(isoWidth, isoHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = isometricImage.createGraphics();

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

    public MainApp() {
        setFocusable(true);
        setPreferredSize(dimension);
        addKeyListener(keyH);
        addMouseListener(mouseH);
        loadImages();
        map = new Map(this);
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
        for (int i = 0; i < 10; i++) {
            int x = (int)Math.round(Math.random() * 1000);
            int y = (int)Math.round(Math.random() * 1000);
            Rabbit r = new Rabbit(this, x, y);
            animals.add(r);
        }
        for (int i = 0; i < 10; i++) {
            int x = (int)Math.round(Math.random() * 1000);
            int y = (int)Math.round(Math.random() * 1000);
            Deer r = new Deer(this, x, y);
            animals.add(r);
        }
        for (int i = 0; i < 10; i++) {
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

        for (Tree tree : trees) {
            tree.update();
        }
        animals.removeIf((a) -> !a.isAlive);
        for (Animal animal : animals) {
            animal.update();
        }
        items.removeIf((i) -> !i.isAlive); // idc if items arent alive this is for deleting awkeawkdhawkdw
        for (Item item : items) {
            item.update();
        }
        explosions.removeIf((i) -> i.deleteMe); // idc if items arent alive this is for deleting awkeawkdhawkdw
        for (ParticleExplosion e : explosions) {
            e.update();
        }
        inventory.update();
    }
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(new Color(50, 150, 40));
        g2.fillRect(0, 0, G_WIDTH, G_HEIGHT);
        map.draw(g2);
        player1.draw(g2);
        g2.drawImage(test1, 0, 0, null);
//        trees.sort(Comparator.comparingInt(tree -> tree.y));
        for (Tree tree : trees) {
            tree.draw(g2);
        }
        for (Animal animal : animals) {
            animal.draw(g2);
        }
        for (Item item : items) {
            item.draw(g2);
        }
        for (ParticleExplosion e : explosions) {
            e.draw(g2);
        }
        inventory.draw(g2);
    }

    public BufferedImage loadImage(String imageName) {
        BufferedImage texture;
        try {
            URL imageURL = getClass().getResource("/images/" + imageName);
            texture = ImageIO.read(imageURL);
//            System.out.println("Loaded tree");
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
