package main.java.com.campoid.MainApp;

public class Campfire extends Item {

    public Campfire(MainApp mainApp, double x, double y) {
        super(mainApp, "campfire.png", x, y);
    }

    @Override
    public void consume() {
        isAlive = false;
        mainApp.player1.crafting = true;
        System.out.println("Consume Wood");
        isAlive = false;
        Campfire c = new Campfire(mainApp, mainApp.player1.worldX , mainApp.player1.worldY );
        mainApp.items.add(c);
    }
}
