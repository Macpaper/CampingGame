package main.java.com.campoid.MainApp;

public class Log extends Item {
    public int x;
    public int y;
    public Log(MainApp mainApp, String textureName, double x, double y) {
        super(mainApp, textureName, x, y);
    }

    @Override
    public String toString() {
        return "log";
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
