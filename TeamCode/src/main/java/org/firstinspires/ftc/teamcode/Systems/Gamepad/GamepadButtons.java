package org.firstinspires.ftc.teamcode.Systems.Gamepad;

public class GamepadButtons {
    public enum Button {
        A, CROSS,
        B, CIRCLE,
        X, SQUARE,
        Y, TRIANGLE,
        DPAD_UP, DPAD_DOWN, DPAD_LEFT, DPAD_RIGHT,
        LEFT_BUMPER, L1,
        RIGHT_BUMPER, R1,
        LEFT_STICK, RIGHT_STICK,
        START, OPTIONS,
        BACK, SHARE,
        GUIDE, PS,
        TOUCHPAD
    }

    public enum Trigger {
        LEFT_TRIGGER, L2,
        RIGHT_TRIGGER, R2
    }

    public enum Stick {
        LEFT_STICK, RIGHT_STICK
    }

    public enum TouchpadFinger {
        FINGER_1, FINGER_2
    }
}
