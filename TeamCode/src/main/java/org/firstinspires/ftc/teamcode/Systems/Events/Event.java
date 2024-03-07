package org.firstinspires.ftc.teamcode.Systems.Events;

import java.util.concurrent.Callable;

public class Event {
    private Callable<Boolean> condition;
    private Runnable action;

    private boolean executed = true;

    public Event(Callable<Boolean> condition, Runnable action) {
        this.condition = condition;
        this.action = action;
    }

    public void reset() {
        this.executed = false;
    }

    public void execute() {
        if (executed) return;

        try {
            if (condition.call()) {
                action.run();
                executed = true;
            }
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean hasExecuted() {
        return executed;
    }

    public void setCondition(Callable<Boolean> condition) {
        this.condition = condition;
    }

    public void setAction(Runnable action) {
        this.action = action;
    }
}