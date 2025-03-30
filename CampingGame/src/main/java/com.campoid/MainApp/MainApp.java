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
    State gameState = State.START;
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
    ArrayList<Mushroom> mushrooms = new ArrayList<>();
    ArrayList<ParticleExplosion> explosions = new ArrayList<>();
    public HashMap<String, BufferedImage> imageMap = new HashMap<>();
    public BufferedImage dirtImage;
    BufferedImage startBackground = loadImage("background.png");
    int startButtonWidth = 500;
    int startButtonHeight = 250;
    Spawner spawner = new Spawner(this);
    Runnable startCallback = () -> {
      this.gameState = State.GAMEPLAY;
    };
    Button startButton = new Button(this, G_WIDTH / 2 - startButtonWidth / 2,
                                    G_HEIGHT / 2 - startButtonHeight / 2, startButtonWidth, startButtonHeight,
            "startGameText.png", startCallback);


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

    private void initListeners() {
        addKeyListener(keyH);
        addMouseListener(mouseH);
        addMouseMotionListener(mouseH);
    }

    public MainApp() {
        initListeners();
        setFocusable(true);
        setPreferredSize(dimension);
        map = new Map(this);
//        generateTrees();
        spawner.spawnInit();
        int isoMapWidth = 20 * 120;
        int isoMapHeight = (map.tilesVertical + map.tilesHorizontal) * map.tileSize/2;
        player1.worldX = -G_WIDTH/2;
        player1.worldY = -G_HEIGHT/2 + isoMapHeight/4;
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
        if (gameState == State.START) {
            startButton.update();
        }
        if (gameState == State.GAMEPLAY) {
            map.update();
            if (!gameOver) {
                player1.update();
            }

            trees.removeIf((t) -> !t.isAlive);
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
            mushrooms.removeIf((i) -> !i.isAlive); // idc if items arent alive this is for deleting awkeawkdhawkdw
            for (Mushroom m : mushrooms) {
                m.update();
            }
            explosions.removeIf((i) -> i.deleteMe); // idc if items arent alive this is for deleting awkeawkdhawkdw
            for (ParticleExplosion e : explosions) {
                e.update();
            }
            inventory.update();
            spawner.update();
        }
        if (gameState == State.DEAD) {

        }
    }
    public void paintComponent(Graphics g) {
        if (gameState == State.START) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(new Color(150, 150, 150));
            g2.fillRect(0, 0, G_WIDTH, G_HEIGHT);
            g2.drawImage(startBackground, 0, 0, G_WIDTH, G_HEIGHT, null);
            startButton.draw(g2);
        }
        if (gameState == State.GAMEPLAY) {
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
            for (Mushroom e : mushrooms) {
                e.draw(g2);
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
        if (gameState == State.DEAD) {

        }
    }

    public BufferedImage loadImage(String imageName) {
        BufferedImage texture;
        try {
            URL imageURL = getClass().getResource("/images/" + imageName);
            texture = ImageIO.read(imageURL);
//            System.out.println("Loaded tree");
            return texture;
        } catch (Exception e) {
            System.out.println("not londing " + imageName);
            e.printStackTrace();
        }
        return null;
    }


//    private void generateTrees() {
//        for (int i = 0; i < 20; i++) {
//            Tree t = new Tree(this, MainApp.randInt(-800, 800), MainApp.randInt(-800, 800), MainApp.randInt(150, 300), MainApp.randInt(200, 350));
//            trees.add(t);
//        }
//    }
    public static int randInt(int low, int high) {
        return (int)(Math.random() * (high - low + 1)) + low;
    }
}
