package main.java.com.campoid.MainApp;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public boolean placing = false;
    public boolean down = false;
    public boolean left = false;
    public boolean right = false;
    public boolean up = false;
    public boolean addItem = false;
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
		if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
			up = isPressed;
		}
		if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
			down = isPressed;
		}
		if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
			left = isPressed;
		}
		if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
			right = isPressed;
		}
        if (code == KeyEvent.VK_SPACE){
            addItem = isPressed;
        }
    }
}
