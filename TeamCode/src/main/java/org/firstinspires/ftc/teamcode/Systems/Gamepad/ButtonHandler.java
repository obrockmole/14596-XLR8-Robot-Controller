package org.firstinspires.ftc.teamcode.Systems.Gamepad;

import java.util.function.BooleanSupplier;

public class ButtonHandler {
    private boolean currentState, previousState;
    private final BooleanSupplier button;

    public ButtonHandler(Gamepad gamepad, GamepadButtons.Button button) {
        this.button = () -> gamepad.getButton(button);
        currentState = this.button.getAsBoolean();
        previousState = currentState;
    }

    public boolean isDown() {
        return button.getAsBoolean();
    }

    public boolean isUp() {
        return !button.getAsBoolean();
    }

    public ButtonHandler onDown(Runnable func) {
        if (isDown())
            func.run();
        return this;
    }

    public ButtonHandler onUp(Runnable func) {
        if (isUp())
            func.run();
        return this;
    }

    public ButtonHandler onPress(Runnable func) {
        if (!previousState && currentState)
            func.run();
        return this;
    }

    public ButtonHandler onRelease(Runnable func) {
        if (previousState && !currentState)
            func.run();
        return this;
    }

    public ButtonHandler onChange(Runnable func) {
        if (previousState != currentState)
            func.run();
        return this;
    }

    public void update() {
        previousState = currentState;
        currentState = button.getAsBoolean();
    }
}
