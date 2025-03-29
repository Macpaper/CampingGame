package main.java.com.campoid.MainApp;

import javax.swing.text.Position;

public abstract class Animal {
    protected int health;
    protected Vec2 position;
    protected Boolean isAlive;
    protected int speed;
    public Animal(Vec2 position) {
        this.position = position;
        this.isAlive = true;
        this.health = 100;
    }
    public abstract void move(Vec2 direction);
    public abstract void eat();
    public abstract void update();
    public void setHealth(int health) {
        this.health = health;
    }
    public int getHealth() {
        return health;
    }
    public Vec2 getPosition() {
        return position;
    }
    public void setPosition(Vec2 position) {
        this.position = position;
    }
}
