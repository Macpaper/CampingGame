package main.java.com.campoid.MainApp;

import javax.swing.*;

public class Window {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Camping Game hWAEHawhea");
        frame.add(new MainApp());
        frame.setVisible(true);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}