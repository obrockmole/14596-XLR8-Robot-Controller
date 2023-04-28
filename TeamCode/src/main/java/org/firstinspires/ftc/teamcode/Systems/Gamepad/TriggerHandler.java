package org.firstinspires.ftc.teamcode.Systems.Gamepad;

import org.firstinspires.ftc.teamcode.Systems.Gamepad.GamepadButtons.Trigger;

public class TriggerHandler {
    private Gamepad gamepad;
    private Trigger trigger;

    private boolean currentState, previousState;
    private double limit;

    public TriggerHandler(Gamepad gamepad, Trigger trigger, double limit) {
        this.gamepad = gamepad;
        this.trigger = trigger;
        this.limit = limit;

        currentState = false;
        previousState = false;
    }

    public double getLimit() {
        return limit;
    }

    public TriggerHandler setLimit(double limit) {
        this.limit = limit;
        return this;
    }

    public double getValue() {
        double triggerValue = 0;
        switch (trigger) {
            case LEFT_TRIGGER:
                triggerValue = gamepad.getGamepad().left_trigger;
                break;
            case RIGHT_TRIGGER:
                triggerValue = gamepad.getGamepad().right_trigger;
                break;
            default:
                break;
        }
        return triggerValue;
    }

    public boolean isDown() {
        return currentState;
    }

    public boolean isDown(Runnable func) {
        if (currentState) {
            func.run();
            return true;
        }
        return false;
    }

    public boolean isUp() {
        return !currentState;
    }

    public boolean isUp(Runnable func) {
        if (!currentState) {
            func.run();
            return true;
        }
        return false;
    }

    public boolean onPress() {
        return (!previousState && currentState);
    }

    public boolean onPress(Runnable func) {
        if (!previousState && currentState) {
            func.run();
            return true;
        }
        return false;
    }

    public boolean onRelease() {
        return (previousState && !currentState);
    }

    public boolean onRelease(Runnable func) {
        if (previousState && !currentState) {
            func.run();
            return true;
        }
        return false;
    }

    public boolean onChange() {
        return (previousState != currentState);
    }

    public boolean onChange(Runnable func) {
        if (previousState != currentState) {
            func.run();
            return true;
        }
        return false;
    }

    public void update() {
        previousState = currentState;
        currentState = getValue() > this.limit;
    }
}
