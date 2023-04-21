package org.firstinspires.ftc.teamcode.Systems;

public class Timer {
    private float startTime, endTime;
    private boolean running;

    public Timer() {}

    public void start() {
        if (running) return;
        startTime = System.nanoTime();
        running = true;
    }

    public void stop() {
        if (!running) return;
        endTime = System.nanoTime();
        running = false;
    }

    public void restart() {
        startTime = System.nanoTime();
        running = true;
    }

    public float getTime() {
        if (running)
            return (int)((System.nanoTime() - startTime) / 1000000);
        else
            return (int)((endTime - startTime) / 1000000);
    }

    public float getTimeSeconds() {
        return getTime() / 1000;
    }

    public float getStartTime() {
        return startTime;
    }

    public float getEndTime() {
        return endTime;
    }

    public boolean isRunning() {
        return running;
    }
}
