package main.java.com.campoid.MainApp;

public class GameTimer {
    private long duration;
    private long startTime;
    private boolean running;
    private int repeatCount;
    private int executedCount;
    private Runnable callback;

    public GameTimer(long duration, int repeatCount, Runnable callback) {
        this.duration = duration;
        this.callback = callback;
        this.executedCount = 0;
        this.repeatCount = repeatCount;
        this.running = false;
    }

    public void start() {
        this.startTime = System.currentTimeMillis();
        this.running = true;
        this.executedCount = 0;
    }

    public void stop() {
        this.running = false;
    }

    public void update() {
        long currentTime = System.currentTimeMillis();
        if (running && currentTime - startTime > duration) {
            if (executedCount < repeatCount) {
//    			System.out.println("timers looping");
                if (callback != null) {
                    callback.run();
                }
                executedCount++;
                startTime = currentTime;
            }
            if (executedCount >= repeatCount) {
                start();
                stop();
            }
        }
    }

}
