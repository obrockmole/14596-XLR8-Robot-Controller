package org.firstinspires.ftc.teamcode.Systems;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class RunnableAction {
    private ExecutorService executor;
    private Runnable action;
    private Timer timer;

    public RunnableAction() {
        this(() -> {});
    }

    public RunnableAction(Runnable action) {
        executor = Executors.newSingleThreadExecutor();
        this.action = action;
        timer = new Timer();
    }

    public ExecutorService getExecutor() {
        return executor;
    }

    public RunnableAction setExecutor(ExecutorService executor) {
        this.executor = executor;
        return this;
    }

    public Runnable getAction() {
        return action;
    }

    public RunnableAction setAction(Runnable action) {
        this.action = action;
        return this;
    }

    public void run() {
        executor.submit(action);
        timer.restart();
    }

    public void start() {
        run();
    }

    public boolean awaitTermination(long timeout) {
        try {
            return executor.awaitTermination(timeout, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
    }

    public boolean isShutdown() {
        return executor.isShutdown();
    }

    public boolean isTerminated() {
        return executor.isTerminated();
    }

    public void shutdown() {
        executor.shutdown();
    }

    public void shutdownNow() {
        executor.shutdownNow();
    }

    public float getTimeElapsed() {
        return timer.getTime();
    } 
}
