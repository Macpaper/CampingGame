package main.java.com.campoid.MainApp;

public class Spawner {
    MainApp main;
    int maxRabbits = 10;
    int maxBears = 10;
    int maxDeer = 10;
    int numRabbits = MainApp.randInt(3, 5);
    int numBears = 1;
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
    }
    public void update() {
        if (System.currentTimeMillis() % (1000 * MainApp.randInt(2, 10)) == 0) {
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

        if (animal == AnimalType.RABBIT) {
            Rabbit rabbit = new Rabbit(main, t.x, t.y);
            main.animals.add(rabbit);
        }
        if (animal == AnimalType.BEAR) {
            BlackBear bear = new BlackBear(main, t.x, t.y);
            main.animals.add(bear);
        }
        if (animal == AnimalType.DEER) {
            Deer bear = new Deer(main, t.x, t.y);
            main.animals.add(bear);
        }
    }
}
