package org.firstinspires.ftc.teamcode.Systems.Gamepad;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.firstinspires.ftc.teamcode.Systems.Gamepad.GamepadButtons.TouchpadFinger;
import java.util.function.BooleanSupplier;

public class TouchpadHandler {
    private Gamepad gamepad;
    private boolean currentState, previousState;
    private Vector2D currentPos, previousPos;
    private BooleanSupplier fingerBool;
    private TouchpadFinger finger;

    public TouchpadHandler(Gamepad gamepad, TouchpadFinger finger) {
        this.gamepad = gamepad;
        this.finger = finger;

        switch (finger) {
            case FINGER_1:
                this.fingerBool = () -> gamepad.getGamepad().touchpad_finger_1;
                break;
            case FINGER_2:
                this.fingerBool = () -> gamepad.getGamepad().touchpad_finger_2;
        }

        currentState = this.fingerBool.getAsBoolean();
        previousState = currentState;

        currentPos = new Vector2D(getX(), getY());
        previousPos = currentPos;
    }

    public Vector2D getPosition() {
        return currentPos;
    }

    public Vector2D getPositionDelta() {
        return new Vector2D(getXDelta(), getYDelta());
    }

    public double getX() {
        double fingerX = 0;
        if (isDown()) switch (finger) {
            case FINGER_1:
                fingerX = gamepad.getGamepad().touchpad_finger_1_x;
                break;
            case FINGER_2:
                fingerX = gamepad.getGamepad().touchpad_finger_2_x;
                break;
            default:
                break;
        }
        return fingerX;
    }

    public double getXDelta() {
        return currentPos.getX() - previousPos.getX();
    }

    public double getY() {
        double fingerY = 0;
        if (isDown()) switch (finger) {
            case FINGER_1:
                fingerY = gamepad.getGamepad().touchpad_finger_1_y;
                break;
            case FINGER_2:
                fingerY = gamepad.getGamepad().touchpad_finger_2_y;
                break;
            default:
                break;
        }
        return fingerY;
    }

    public double getYDelta() {
        return currentPos.getY() - previousPos.getY();
    }

    public boolean isDown() {
        return fingerBool.getAsBoolean();
    }

    public boolean isUp() {
        return !fingerBool.getAsBoolean();
    }

    public TouchpadHandler onDown(Runnable func) {
        if (isDown())
            func.run();
        return this;
    }

    public TouchpadHandler onUp(Runnable func) {
        if (isUp())
            func.run();
        return this;
    }

    public TouchpadHandler onPress(Runnable func) {
        if (!previousState && currentState) {
            func.run();
        }
        return this;
    }

    public TouchpadHandler onRelease(Runnable func) {
        if (previousState && !currentState) {
            func.run();
        }
        return this;
    }

    public TouchpadHandler onChange(Runnable func) {
        if (previousState != currentState) {
            func.run();
        }
        return this;
    }

    public void update() {
        previousState = currentState;
        currentState = fingerBool.getAsBoolean();

        previousPos = currentPos;
        currentPos = new Vector2D(getX(), getY());
    }
}
