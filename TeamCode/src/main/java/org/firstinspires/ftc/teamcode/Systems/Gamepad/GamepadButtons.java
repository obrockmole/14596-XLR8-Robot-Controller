package org.firstinspires.ftc.teamcode.Systems.Gamepad;

import androidx.annotation.NonNull;

public class GamepadButtons {
    public enum Button {
        A("A"), CROSS("Cross"),
        B("B"), CIRCLE("Circle"),
        X("X"), SQUARE("Square"),
        Y("Y"), TRIANGLE("Triangle"),
        DPAD_UP("D-Pad Up"), DPAD_DOWN("D-Pad Down"), DPAD_LEFT("D-Pad Left"), DPAD_RIGHT("D-Pad Right"),
        LEFT_BUMPER("Left Bumper"), L1("Left Bumper"),
        RIGHT_BUMPER("Right Bumper"), R1("Right Bumper"),
        LEFT_STICK("Left Stick"), RIGHT_STICK("Right Stick"),
        START("Start"), OPTIONS("Options"),
        BACK("Back"), SHARE("Share"),
        GUIDE("Guide"), PS("PlayStation"),
        TOUCHPAD("Touchpad");

        private final String name;

        Button(String name) {
            this.name = name;
        }

        @NonNull
        public String toString() {
            return name;
        }
    }

    public enum Trigger {
        LEFT_TRIGGER("Left Trigger"), L2("Left Trigger"),
        RIGHT_TRIGGER("Right Trigger"), R2("Right Trigger");

        private final String name;

        Trigger(String name) {
            this.name = name;
        }

        @NonNull
        public String toString() {
            return name;
        }
    }

    public enum Stick {
        LEFT_STICK("Left Stick"), RIGHT_STICK("Right Stick");

        private final String name;

        Stick(String name) {
            this.name = name;
        }

        @NonNull
        public String toString() {
            return name;
        }
    }

    public enum TouchpadFinger {
        FINGER_1("Finger 1"), FINGER_2("Finger 2");

        private final String name;

        TouchpadFinger(String name) {
            this.name = name;
        }

        public String toString() {
            return name;
        }
    }
}
