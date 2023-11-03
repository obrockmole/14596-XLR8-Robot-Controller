package org.firstinspires.ftc.teamcode.Systems;

public class Stopwatch {
    private double startTime, endTime;
    private boolean running;

    public Stopwatch() {}

    public void start() {
        if (running) return;
        startTime = System.nanoTime();
        running = true;
    }

    public void begin() {
        start();
    }

    public void stop() {
        if (!running) return;
        endTime = System.nanoTime();
        running = false;
    }

    public void end() {
        stop();
    }

    public void restart() {
        startTime = System.nanoTime();
        running = true;
    }

    public double getTime() {
        if (running)
            return (int)((System.nanoTime() - startTime) / 1000000);
        else
            return (int)((endTime - startTime) / 1000000);
    }

    public double getTimeSeconds() {
        return getTime() / 1000;
    }

    public double getStartTime() {
        return startTime;
    }

    public double getEndTime() {
        return endTime;
    }

    public boolean isRunning() {
        return running;
    }
}
