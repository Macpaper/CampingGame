package main.java.com.campoid.MainApp;

import java.awt.*;

public class Spawner {
    MainApp main;
    int maxRabbits = 10;
    int maxBears = 10;
    int maxDeer = 10;
    int numRabbits = MainApp.randInt(3, 5);
    int numBears = 8;
    int numDeer = MainApp.randInt(1, 3);
    public Spawner(MainApp main) {
        this.main = main;
    }
    public void spawnInit() {
        for (int i = 0; i < numRabbits; i++) {
            spawnAnimal(AnimalType.RABBIT);
        }
        for (int i = 0; i < numDeer; i++) {
            spawnAnimal(AnimalType.DEER);
        }
        for (int i = 0; i < numBears; i++) {
            spawnAnimal(AnimalType.BEAR);
        }
        for (int i = 0; i < MainApp.randInt(30, 40); i++) {
            spawnTree(TreeType.TREE);
        }
        for (int i = 0; i < MainApp.randInt(10, 20); i++) {
            spawnTree(TreeType.BUSH);
        }
        for (int i = 0; i < MainApp.randInt(5, 10); i++) {
            spawnTree(TreeType.MUSHROOM);
        }
        for (int i = 0; i < MainApp.randInt(10, 20); i++) {
            Tile t = main.map.tiles.get(MainApp.randInt(0, main.map.tiles.size() - 1));
            int sx = t.x - main.G_WIDTH;
            int sy = t.y - main.G_HEIGHT;
            Water w = new Water(main, sx, sy);
            main.items.add(w);
        }
    }
    public boolean isRaining = false;
    public void update() {
        if (System.currentTimeMillis() % 1000 * MainApp.randInt(10, 20) < 10) {
            isRaining = !isRaining;
            System.out.println("raining is: " + isRaining);
        }
        if (System.currentTimeMillis() % (1000 * MainApp.randInt(2, 10)) < 20) {
            numRabbits = 0; numBears = 0; numDeer = 0;
            for (Animal a : main.animals) {
                if (a instanceof Rabbit) { numRabbits++; }
                if (a instanceof BlackBear || a instanceof BrownBear) { numBears++; }
                if (a instanceof Deer) { numDeer++; }
            }
            if (numRabbits < maxRabbits) { spawnAnimal(AnimalType.RABBIT); }
            if (numBears < maxBears) { spawnAnimal(AnimalType.BEAR); }
            if (numDeer < maxDeer) { spawnAnimal(AnimalType.DEER); }
        }
    }

    public void spawnAnimal(AnimalType animal) {
        Tile t = main.map.tiles.get(MainApp.randInt(0, main.map.tiles.size() - 1));
        int sx = t.x - main.G_WIDTH;
        int sy = t.y - main.G_HEIGHT;
        System.out.println("Spawned a: " + animal.toString() + " at: " + t.x + ", " + t.y);

        if (animal == AnimalType.RABBIT) {
            Rabbit rabbit = new Rabbit(main, sx, sy);
            main.animals.add(rabbit);
        }
        if (animal == AnimalType.BEAR) {
            BlackBear bear = new BlackBear(main, sx, sy);
            main.animals.add(bear);
        }
        if (animal == AnimalType.DEER) {
            Deer bear = new Deer(main, sx, sy);
            main.animals.add(bear);
        }
    }

    public void draw(Graphics2D g2) {
        if (isRaining) {
            for (int i = 0; i < MainApp.randInt(5, 10); i++) {
                main.addRainParticle();
            }
            if (MainApp.randInt(1, 5) == 1) {
                Tile t = main.map.tiles.get(MainApp.randInt(0, main.map.tiles.size() - 1));
                int sx = t.x - main.G_WIDTH;
                int sy = t.y - main.G_HEIGHT;
                Water w = new Water(main, sx, sy);
                main.items.add(w);
            }
        }
    }

    public void spawnTree(TreeType type) {
        Tile t = main.map.tiles.get(MainApp.randInt(0, main.map.tiles.size() - 1));
        int sx = t.x - main.G_WIDTH;
        int sy = t.y - main.G_HEIGHT;
        if (type == TreeType.TREE) {
            Tree t1 = new Tree(main, sx, sy, MainApp.randInt(100, 200), MainApp.randInt(200, 300));
            main.trees.add(t1);
        }
//        if (type == TreeType.BUSH) {
//            Tree t1 = new Tree(main, t.x, t.y, MainApp.randInt(100, 200), MainApp.randInt(200, 300));
//        }
        if (type == TreeType.MUSHROOM) {
            String mushPath = "mushroom" + MainApp.randInt(1, 2) + ".png";
            Mushroom m = new Mushroom(main, mushPath, sx, sy);
            main.items.add(m);
        }
    }

}
