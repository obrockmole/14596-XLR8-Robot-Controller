package org.firstinspires.ftc.teamcode.Systems;

public class Timer {
    private final Stopwatch stopwatch;
    private double length;
    private Runnable action;

    public Timer(double length, Runnable action) {
        stopwatch = new Stopwatch();
        this.length = length;
        this.action = action;
    }

    public double getLength() {
        return length;
    }

    public Timer setLength(double length) {
        this.length = length;
        return this;
    }

    public Runnable getAction() {
        return action;
    }

    public Timer setAction(Runnable action) {
        this.action = action;
        return this;
    }

    public Timer start() {
        stopwatch.start();
        return this;
    }

    public Timer begin() {
        start();
        return this;
    }

    public Timer stop() {
        stopwatch.stop();
        return this;
    }

    public Timer end() {
        stop();
        return this;
    }

    public Timer restart() {
        stopwatch.restart();
        return this;
    }

    public double getTimeLeft() {
        return length - stopwatch.getTime();
    }

    public double getTimeLeftSeconds() {
        return getTimeLeft() / 1000;
    }

    public boolean isRunning() {
        return stopwatch.isRunning();
    }

    public Timer update(boolean restart) {
        if (isRunning() && getTimeLeft() <= 0) {
            action.run();
            if (restart) restart();
            else stop();
        }
        return this;
    }
}
