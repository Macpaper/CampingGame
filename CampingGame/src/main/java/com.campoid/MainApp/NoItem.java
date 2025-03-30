package main.java.com.campoid.MainApp;

public class NoItem extends Item {
    public NoItem(MainApp mainApp, String textureName, double x, double y) {
        super(mainApp, "none", x, y);
    }

    @Override
    public void consume() {

    }
}
