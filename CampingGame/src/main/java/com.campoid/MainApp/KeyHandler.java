package main.java.com.campoid.MainApp;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public boolean placing = false;
    public boolean down = false;
    public boolean left = false;
    public boolean right = false;
    public boolean up = false;
    public boolean space = false;
    public boolean shooting = false;
    public boolean eKey = false;
    boolean one = false, two = false, three = false, four = false, five = false;
    boolean six = false, seven = false, eight = false, nine = false;
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
            space = isPressed;
        }
        if (code == KeyEvent.VK_E){
            eKey = isPressed;
        }
        if (code >= KeyEvent.VK_1 && code <= KeyEvent.VK_9) {
            int number = code - KeyEvent.VK_1 + 1;
            switch (number) {
                case 1 -> one = isPressed;
                case 2 -> two = isPressed;
                case 3 -> three = isPressed;
                case 4 -> four = isPressed;
                case 5 -> five = isPressed;
                case 6 -> six = isPressed;
                case 7 -> seven = isPressed;
                case 8 -> eight = isPressed;
                case 9 -> nine = isPressed;
            }
        }
//
    }
}
