package main.java.com.campoid.MainApp;

public abstract class Entity {
    public Vec2 position;
    public int width;
    public int height;
    public Entity(double x, double y, int width, int height) {
        position = new Vec2(x, y);
        this.width = width;
        this.height = height;
    }
    public boolean collides(Entity a, Entity b) {
        return  a.position.x + a.width > b.position.x &&
                a.position.x < b.position.x + b.width &&
                a.position.y + a.height > b.position.y && a.position.y < b.position.y + b.height;
    }
}
