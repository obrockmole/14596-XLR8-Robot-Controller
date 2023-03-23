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

    public float getTime() {
        if (running)
            return (System.nanoTime() - startTime) / 1000000;
        else
            return (endTime - startTime) / 1000000;
    }

    public float getTimeSeconds() {
        if (running)
            return (System.nanoTime() - startTime) / 1000000000;
        else
            return (endTime - startTime) / 1000000000;
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
