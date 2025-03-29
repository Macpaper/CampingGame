package main.java.com.campoid.MainApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;

public class InventoryGrid extends JPanel {
    private static final int SLOT_SIZE = 50;  // Size of each box
    private static final int GAP = 5;         // Spacing between slots
    private static final int ROWS = 1;        // One row (Minecraft hotbar style)
    private static final int COLUMNS = 9;     // 9 slots like Minecraft

    private final HashMap<Point, String> items = new HashMap<>(); // Stores item names in slots
    private String heldItem = null; // Holds an item when dragging

    public InventoryGrid() {



    }

    private Point getSlotAt(Point p) {
        int col = p.x / (SLOT_SIZE + GAP);
        int row = p.y / (SLOT_SIZE + GAP);
        if (col < COLUMNS && row < ROWS) return new Point(col, row);
        return null;
    }

    public void update(){
        repaint();
    }

    public void draw(Graphics2D g2 ) {
        g2.setColor(Color.black);
        g2.drawRect(0, 0, SLOT_SIZE, SLOT_SIZE);
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
