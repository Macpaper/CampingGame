package main.java.com.campoid.MainApp;

public class Vec2 {
    public double x, y;

    // Constructor
    public Vec2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // Zero Vector
    public static final Vec2 ZERO = new Vec2(0, 0);

    // Copy method
    public Vec2 copy() {
        return new Vec2(this.x, this.y);
    }

    // Add another vector
    public Vec2 add(Vec2 v) {
        return new Vec2(this.x + v.x, this.y + v.y);
    }

    // Subtract another vector
    public Vec2 subtract(Vec2 v) {
        return new Vec2(this.x - v.x, this.y - v.y);
    }

    // Multiply by a scalar
    public Vec2 scale(double scalar) {
        return new Vec2(this.x * scalar, this.y * scalar);
    }

    // Get magnitude (length) of the vector
    public double magnitude() {
        return Math.sqrt(x * x + y * y);
    }

    // Normalize (convert to unit vector)
    public Vec2 normalize() {
        double mag = magnitude();
        return (mag == 0) ? new Vec2(0, 0) : new Vec2(x / mag, y / mag);
    }

    // Dot product
    public double dot(Vec2 v) {
        return this.x * v.x + this.y * v.y;
    }

    // Rotate by an angle (radians)
    public Vec2 rotate(double angle) {
        double cosA = Math.cos(angle);
        double sinA = Math.sin(angle);
        return new Vec2(x * cosA - y * sinA, x * sinA + y * cosA);
    }

    // Calculate the angle (direction) of this vector
    public double angle() {
        return Math.atan2(y, x);
    }

    // Distance between two vectors
    public double distance(Vec2 v) {
        double dx = this.x - v.x;
        double dy = this.y - v.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    // Check equality
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Vec2) {
            Vec2 v = (Vec2) obj;
            return this.x == v.x && this.y == v.y;
        }
        return false;
    }

    // Convert to String
    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
