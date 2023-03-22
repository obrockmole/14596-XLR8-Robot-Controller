package org.firstinspires.ftc.teamcode.Systems.Gamepad;

import java.util.function.BooleanSupplier;

public class ButtonHandler {
    private boolean currentState, previousState;
    private BooleanSupplier button;

    public ButtonHandler(Gamepad gamepad, GamepadButtons.Button button) {
        this.button = () -> gamepad.getButton(button);
        currentState = this.button.getAsBoolean();
        previousState = currentState;
    }

    public ButtonHandler(BooleanSupplier button) {
        this.button = button;
        currentState = this.button.getAsBoolean();
        previousState = currentState;
    }

    public boolean isDown() {
        return button.getAsBoolean();
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
        currentState = button.getAsBoolean();
    }
}
