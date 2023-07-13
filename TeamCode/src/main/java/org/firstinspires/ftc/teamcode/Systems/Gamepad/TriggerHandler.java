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
            case L2:
                triggerValue = gamepad.getGamepad().left_trigger;
                break;
            case RIGHT_TRIGGER:
            case R2:
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

    public boolean isUp() {
        return !currentState;
    }

    public TriggerHandler onDown(Runnable func) {
        if (isDown())
            func.run();
        return this;
    }

    public TriggerHandler onUp(Runnable func) {
        if (isUp())
            func.run();
        return this;
    }

    public TriggerHandler onPress(Runnable func) {
        if (!previousState && currentState)
            func.run();
        return this;
    }

    public TriggerHandler onRelease(Runnable func) {
        if (previousState && !currentState)
            func.run();
        return this;
    }

    public TriggerHandler onChange(Runnable func) {
        if (previousState != currentState)
            func.run();
        return this;
    }

    public void update() {
        previousState = currentState;
        currentState = getValue() > this.limit;
    }
}
