package main.java.com.campoid.MainApp;

public class Water extends Item {
    long timer = System.currentTimeMillis();
    public Water(MainApp mainApp,double x, double y) {
        super(mainApp, "water.png", x, y);
    }

    @Override
    public void consume() {
        isAlive = false;
        mainApp.player1.drink(20);

    }
    public void update() {
        super.update();
        if (System.currentTimeMillis() - timer > 20000 && !mainApp.spawner.isRaining) {
            if (MainApp.randInt(1, 10) == 1) {
                isAlive = false;
            }
        }
    }

    @Override
    public String toString() {
        return "water";
    }
}
