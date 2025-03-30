package main.java.com.campoid.MainApp;

public class Meat extends Item {

    public Meat(MainApp mainApp, String textureName, double x, double y) {
        super(mainApp, textureName, x, y);
    }

    @Override
    public String toString() {
        return "meat";
    }

    @Override
    public void consume() {
        isAlive = false;
        mainApp.player1.hunger += 10;
        if (mainApp.player1.hunger > 100) {
            mainApp.player1.hunger = 100;
        }
    }
}
