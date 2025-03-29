package main.java.com.campoid.MainApp;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseHandler extends MouseAdapter {
    private int mouseX;
    private int mouseY;
    private boolean leftMouse;
    private boolean rightMouse;
    public MouseHandler() {
        this.mouseX = 0;
        this.mouseY = 0;
        this.leftMouse = false;
        this.rightMouse = false;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        leftMouse = false; // Reset click state on move
        rightMouse = false;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            leftMouse = true;
        }
        if (e.getButton() == MouseEvent.BUTTON3) {
            rightMouse = true;
        }
//        repaintPanel(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {
            leftMouse = false;
        }
        if (SwingUtilities.isRightMouseButton(e)) {
            rightMouse = true;
        }
//        repaintPanel(e);
    }

    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }

    public boolean isLeftButtonClicked() {
        return leftMouse;
    }
    public boolean isRightButtonClicked() {
        return rightMouse;
    }

}
