package main.java.com.campoid.MainApp;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public boolean placing = false;
    public boolean down = false;
    public boolean left = false;
    public boolean right = false;
    public boolean up = false;
    public boolean shooting = false;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        handleKeys(e.getKeyCode(), true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        handleKeys(e.getKeyCode(), false);
    }

    private void handleKeys(int code, boolean isPressed) {
        //System.out.println("fuck");
		if (code == KeyEvent.VK_W) {
			up = isPressed;
		}
		if (code == KeyEvent.VK_S) {
			down = isPressed;
		}
		if (code == KeyEvent.VK_A) {
			left = isPressed;
		}
		if (code == KeyEvent.VK_D) {
			right = isPressed;
		}
    }
}
