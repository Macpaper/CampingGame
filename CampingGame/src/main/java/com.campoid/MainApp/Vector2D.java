package main.java.com.campoid.MainApp;

public class Vector2D {
    public double x, y;

    // Constructor
    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // Zero Vector
    public static final Vector2D ZERO = new Vector2D(0, 0);

    // Copy method
    public Vector2D copy() {
        return new Vector2D(this.x, this.y);
    }

    // Add another vector
    public Vector2D add(Vector2D v) {
        return new Vector2D(this.x + v.x, this.y + v.y);
    }

    // Subtract another vector
    public Vector2D subtract(Vector2D v) {
        return new Vector2D(this.x - v.x, this.y - v.y);
    }

    // Multiply by a scalar
    public Vector2D scale(double scalar) {
        return new Vector2D(this.x * scalar, this.y * scalar);
    }

    // Get magnitude (length) of the vector
    public double magnitude() {
        return Math.sqrt(x * x + y * y);
    }

    // Normalize (convert to unit vector)
    public Vector2D normalize() {
        double mag = magnitude();
        return (mag == 0) ? new Vector2D(0, 0) : new Vector2D(x / mag, y / mag);
    }

    // Dot product
    public double dot(Vector2D v) {
        return this.x * v.x + this.y * v.y;
    }

    // Rotate by an angle (radians)
    public Vector2D rotate(double angle) {
        double cosA = Math.cos(angle);
        double sinA = Math.sin(angle);
        return new Vector2D(x * cosA - y * sinA, x * sinA + y * cosA);
    }

    // Calculate the angle (direction) of this vector
    public double angle() {
        return Math.atan2(y, x);
    }

    // Distance between two vectors
    public double distance(Vector2D v) {
        double dx = this.x - v.x;
        double dy = this.y - v.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    // Check equality
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Vector2D) {
            Vector2D v = (Vector2D) obj;
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
