package main.java.com.campoid.MainApp;

public class CameraKnob {
    public Vec2 knobPosition;
    public Vec2 knobVelocity;
    public CameraKnob(double x, double y, double dx, double dy) {
        knobPosition = new Vec2(x, y);
        knobVelocity = new Vec2(dx, dy);
    }
}
