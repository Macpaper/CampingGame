package main.java.com.campoid.MainApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InventoryGrid extends JPanel {
    private static final int SLOT_SIZE = 50;  // Size of each box
    private static final int GAP = 5;         // Spacing between slots
    private static final int ROWS = 1;        // One row (Minecraft hotbar style)
    private static final int COLUMNS = 9;     // 9 slots like Minecraft

    private final HashMap<Point, String> items = new HashMap<>(); // Stores item names in slots
    private String heldItem = null; // Holds an item when dragging
    private String[] listOfItems = new String[COLUMNS];

    private String itemToAdd = null;
    private int slotToAddTo = -1;

    private BufferedImage[] itemsToDraw = new BufferedImage[COLUMNS];


    private HashMap<Integer, ArrayList<Integer>> indexToSlotXY = new HashMap<Integer, ArrayList<Integer>>();
    private HashMap<BufferedImage, ArrayList<Integer>> texturesAndLoc = new HashMap<>();
    MainApp mainApp;
    BufferedImage texture;
    public InventoryGrid(MainApp mainApp) {

        this.mainApp = mainApp;

    }

    private Point getSlotAt(Point p) {
        int col = p.x / (SLOT_SIZE + GAP);
        int row = p.y / (SLOT_SIZE + GAP);
        if (col < COLUMNS && row < ROWS) return new Point(col, row);
        return null;
    }

    public boolean addItem(String item) {
        for (int i = 0; i < listOfItems.length; i++) {
            if (listOfItems[i] == null){
                itemToAdd = item;
                slotToAddTo = i;
                listOfItems[i] = item;
                return true;
            }
        }
        return false;
    }

    public void update(){
        int x = 400 + slotToAddTo * (SLOT_SIZE + GAP);
        int y = 800;

        if(itemToAdd != null){
            texture = mainApp.loadImage(itemToAdd);
        }




    }

    public void draw(Graphics2D g2 ) {
        g2.setColor(Color.BLACK);
        for(int i = 0; i < 9; i++){
            int x = 400 + i * (SLOT_SIZE + GAP);
            int y = 700;
            g2.drawRect(x, y, SLOT_SIZE, SLOT_SIZE);
            var loc = new ArrayList<>(List.of(x, y));
            indexToSlotXY.put(i, loc);
        }

        if(slotToAddTo != -1 && itemToAdd != null){
            var loc = indexToSlotXY.get(slotToAddTo);
            texturesAndLoc.put(texture, loc);

            itemToAdd = null;
            slotToAddTo = -1;
        }
        for (Map.Entry<BufferedImage, ArrayList<Integer>> entry : texturesAndLoc.entrySet()) {
            var image = entry.getKey();
            ArrayList<Integer> location = entry.getValue();
            g2.drawImage(image, location.get(0), location.get(1), 50, 50, null);

        }

    }






    /*public static void main(String[] args) {
        JFrame frame = new JFrame("Minecraft-Style Inventory");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(new InventoryGrid());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }*/
}
