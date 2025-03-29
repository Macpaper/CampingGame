package main.java.com.campoid.MainApp;

public class Camera {
    private MainApp mainApp;
    Vec2 position = new Vec2(0, 0);
    public Camera(MainApp mainApp) {
        position.x += (mainApp.player1.cameraKnob.knobPosition.x - (mainApp.G_WIDTH / 2 - mainApp.player1.getWidth() / 2) - position.x) / 15;
        position.y += (mainApp.player1.cameraKnob.knobPosition.y - (mainApp.G_HEIGHT / 2 - mainApp.player1.getHeight() / 2) - position.y) / 15;
    }
}
