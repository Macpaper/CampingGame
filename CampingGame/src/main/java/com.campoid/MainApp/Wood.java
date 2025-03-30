package main.java.com.campoid.MainApp;

public class Wood extends Item {

    public Wood(MainApp mainApp, String textureName, double x, double y) {
        super(mainApp, textureName, x, y);
    }

    @Override
    public String toString() {
        return "wood";
    }

    @Override
    public void consume() {
        System.out.println("Consume Wood");
        isAlive = false;
        Campfire c = new Campfire(mainApp, position.x, position.y);
        mainApp.items.add(c);


    }

}