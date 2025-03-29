package main.java.com.campoid.MainApp;

import javax.swing.text.Position;

public abstract class Animal {
    protected int health;
    protected Position position;
    protected Boolean isAlive;
    protected int speed;
    public Animal(Position position) {
        this.position = position;
        this.isAlive = true;
        this.health = 100;
    }
    public abstract void move(Direction direction);
    public abstract void eat();
    public abstract void update();
    public void setHealth(int health) {
        this.health = health;
    }
    public int getHealth() {
        return health;
    }
    public Position getPosition() {
        return position;
    }
    public void setPosition(Position position) {
        this.position = position;
    }
}
