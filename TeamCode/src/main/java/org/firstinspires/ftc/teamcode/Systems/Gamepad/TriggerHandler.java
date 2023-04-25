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

        currentState = getValue() > this.limit;
        previousState = currentState;
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

    public boolean onPress() {
        return (!previousState && currentState);
    }

    public boolean onRelease() {
        return (previousState && !currentState);
    }

    public boolean onChange() {
        return (previousState != currentState);
    }

    public void update() {
        previousState = currentState;
        currentState = getValue() > this.limit;
    }
}
