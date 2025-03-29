package main.java.com.campoid.MainApp;

public class Camera {
    private MainApp mainApp;
    Vec2 position = new Vec2(0, 0);
    public Camera(MainApp mainApp) {
        position.x += (mainApp.player.cameraKnob.x - (mainApp.G_WIDTH / 2 - mainApp.player.width / 2) - position.x) / 15;
        position.y += (mainApp.player.cameraKnob.y - (mainApp.G_HEIGHT / 2 - mainApp.player.height / 2) - position.y) / 15;
    }
}
