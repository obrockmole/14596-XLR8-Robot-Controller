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

    public void setLength(double length) {
        this.length = length;
    }

    public Runnable getAction() {
        return action;
    }

    public void setAction(Runnable action) {
        this.action = action;
    }

    public void start() {
        stopwatch.start();
    }

    public void begin() {
        start();
    }

    public void stop() {
        stopwatch.stop();
    }

    public void end() {
        stop();
    }

    public void restart() {
        stopwatch.restart();
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

    public void update(boolean restart) {
        if (isRunning() && getTimeLeft() <= 0) {
            action.run();
            if (restart) restart();
            else stop();
        }
    }
}
