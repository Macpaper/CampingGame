package main.java.com.campoid.MainApp;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class AudioPlayer {
    private Clip clip;

    public AudioPlayer(String soundFile) {
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File(soundFile));
            clip = AudioSystem.getClip();
            clip.open(audioIn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void play() {
        if (clip != null) {
            if (clip.isRunning()) {
                clip.stop();
            }
            clip.setFramePosition(0);
            clip.start();
        }
//        new Thread(() -> {
//            try {
//                AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File(soundFile));
//                Clip clip = AudioSystem.getClip();
//                clip.open(audioIn);
//                clip.start();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }).start();
    }
}
