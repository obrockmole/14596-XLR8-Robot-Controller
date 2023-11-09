package org.firstinspires.ftc.teamcode.Systems;

public class Stopwatch {
    private double startTime, endTime;
    private boolean running;

    public Stopwatch() {}

    public Stopwatch start() {
        if (!running) {
            startTime = System.nanoTime();
            running = true;
        }
        return this;
    }

    public Stopwatch begin() {
        return start();
    }

    public Stopwatch stop() {
        if (running) {
            endTime = System.nanoTime();
            running = false;
        }
        return this;
    }

    public Stopwatch end() {
        return stop();
    }

    public Stopwatch restart() {
        startTime = System.nanoTime();
        running = true;
        return this;
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
