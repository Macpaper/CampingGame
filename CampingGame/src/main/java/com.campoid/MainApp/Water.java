package main.java.com.campoid.MainApp;

public class Water extends Item {
    public Water(MainApp mainApp,double x, double y) {
        super(mainApp, "water.png", x, y);
    }

    @Override
    public void consume() {
        isAlive = false;
        mainApp.player1.drink(20);
    }

    @Override
    public String toString() {
        return "water";
    }
}
