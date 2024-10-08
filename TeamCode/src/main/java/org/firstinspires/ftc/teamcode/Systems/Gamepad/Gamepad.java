package org.firstinspires.ftc.teamcode.Systems.Gamepad;

import com.qualcomm.robotcore.util.Range;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.firstinspires.ftc.teamcode.Systems.Gamepad.GamepadButtons.Button;
import org.firstinspires.ftc.teamcode.Systems.Gamepad.GamepadButtons.Stick;
import org.firstinspires.ftc.teamcode.Systems.Gamepad.GamepadButtons.TouchpadFinger;
import org.firstinspires.ftc.teamcode.Systems.Gamepad.GamepadButtons.Trigger;

import java.util.ArrayList;
import java.util.HashMap;

public class Gamepad {
    com.qualcomm.robotcore.hardware.Gamepad gamepad;

    private final HashMap<Button, ButtonHandler> gamepadButtons;
    private final HashMap<Trigger, TriggerHandler> gamepadTriggers;
    private final HashMap<TouchpadFinger, TouchpadHandler> gamepadTouchpad;

    public Gamepad(com.qualcomm.robotcore.hardware.Gamepad gamepad) {
        this.gamepad = gamepad;
        gamepadButtons = new HashMap<>();
        gamepadTriggers = new HashMap<>();
        gamepadTouchpad = new HashMap<>();

        for (Button button : Button.values())
            gamepadButtons.put(button, new ButtonHandler(this, button));

        for (Trigger trigger : Trigger.values())
            gamepadTriggers.put(trigger, new TriggerHandler(this, trigger, 0.5));

        for (TouchpadFinger finger : TouchpadFinger.values())
            gamepadTouchpad.put(finger, new TouchpadHandler(this, finger));
    }

    public com.qualcomm.robotcore.hardware.Gamepad getGamepad() {
        return gamepad;
    }

    public Gamepad setGamepad(com.qualcomm.robotcore.hardware.Gamepad gamepad) {
        this.gamepad = gamepad;
        return this;
    }

    public boolean atRest() {
        return gamepad.atRest();
    }

    public boolean getButton(Button button) {
        boolean buttonValue = false;
        switch (button) {
            case A:
            case CROSS:
                buttonValue = gamepad.a;
                break;
            case B:
            case CIRCLE:
                buttonValue = gamepad.b;
                break;
            case X:
            case SQUARE:
                buttonValue = gamepad.x;
                break;
            case Y:
            case TRIANGLE:
                buttonValue = gamepad.y;
                break;
            case DPAD_UP:
                buttonValue = gamepad.dpad_up;
                break;
            case DPAD_DOWN:
                buttonValue = gamepad.dpad_down;
                break;
            case DPAD_LEFT:
                buttonValue = gamepad.dpad_left;
                break;
            case DPAD_RIGHT:
                buttonValue = gamepad.dpad_right;
                break;
            case LEFT_BUMPER:
            case L1:
                buttonValue = gamepad.left_bumper;
                break;
            case RIGHT_BUMPER:
            case R1:
                buttonValue = gamepad.right_bumper;
                break;
            case LEFT_STICK:
                buttonValue = gamepad.left_stick_button;
                break;
            case RIGHT_STICK:
                buttonValue = gamepad.right_stick_button;
                break;
            case START:
            case OPTIONS:
                buttonValue = gamepad.start;
                break;
            case BACK:
            case SHARE:
                buttonValue = gamepad.back;
                break;
            case GUIDE:
            case PS:
                buttonValue = gamepad.guide;
                break;
            case TOUCHPAD:
                buttonValue = gamepad.touchpad;
                break;
            default:
                break;
        }

        return buttonValue;
    }

    public double getTrigger(Trigger trigger) {
        return gamepadTriggers.get(trigger).getValue();
    }

    public double getStickX(Stick stick) {
        double stickValue = 0;
        switch (stick) {
            case LEFT_STICK:
                stickValue = gamepad.left_stick_x;
                break;
            case RIGHT_STICK:
                stickValue = gamepad.right_stick_x;
                break;
            default:
                break;
        }
        return stickValue;
    }

    public double getStickY(Stick stick) {
        double stickValue = 0;
        switch (stick) {
            case LEFT_STICK:
                stickValue = gamepad.left_stick_y;
                break;
            case RIGHT_STICK:
                stickValue = gamepad.right_stick_y;
                break;
            default:
                break;
        }
        return stickValue;
    }

    public Vector2D getFingerPosition(TouchpadFinger finger) {
        return gamepadTouchpad.get(finger).getPosition();
    }

    public Vector2D getFingerPositionDelta(TouchpadFinger finger) {
        return gamepadTouchpad.get(finger).getPositionDelta();
    }

    public double getFingerX(TouchpadFinger finger) {
        return gamepadTouchpad.get(finger).getX();
    }

    public double getFingerXDelta(TouchpadFinger finger) {
        return gamepadTouchpad.get(finger).getXDelta();
    }

    public double getFingerY(TouchpadFinger finger) {
        return gamepadTouchpad.get(finger).getY();
    }

    public double getFingerYDelta(TouchpadFinger finger) {
        return gamepadTouchpad.get(finger).getYDelta();
    }

    public boolean isDown(Button button) {
        return gamepadButtons.get(button).isDown();
    }

    public boolean isDown(Trigger trigger) {
        return gamepadTriggers.get(trigger).isDown();
    }

    public boolean isDown(TouchpadFinger finger) {
        return gamepadTouchpad.get(finger).isDown();
    }

    public boolean isUp(Button button) {
        return gamepadButtons.get(button).isUp();
    }

    public boolean isUp(Trigger trigger) {
        return gamepadTriggers.get(trigger).isUp();
    }

    public boolean isUp(TouchpadFinger finger) {
        return gamepadTouchpad.get(finger).isUp();
    }

    public Gamepad onDown(Button button, Runnable func) {
        gamepadButtons.get(button).onDown(func);
        return this;
    }

    public Gamepad onDown(Trigger trigger, Runnable func) {
        gamepadTriggers.get(trigger).onDown(func);
        return this;
    }

    public Gamepad onDown(TouchpadFinger finger, Runnable func) {
        gamepadTouchpad.get(finger).onDown(func);
        return this;
    }

    public Gamepad onUp(Button button, Runnable func) {
        gamepadButtons.get(button).onUp(func);
        return this;
    }

    public Gamepad onUp(Trigger trigger, Runnable func) {
        gamepadTriggers.get(trigger).onUp(func);
        return this;
    }

    public Gamepad onUp(TouchpadFinger finger, Runnable func) {
        gamepadTouchpad.get(finger).onUp(func);
        return this;
    }

    public Gamepad onPress(Button button, Runnable func) {
        gamepadButtons.get(button).onPress(func);
        return this;
    }

    public Gamepad onPress(Trigger trigger, Runnable func) {
        gamepadTriggers.get(trigger).onPress(func);
        return this;
    }

    public Gamepad onPress(TouchpadFinger finger, Runnable func) {
        gamepadTouchpad.get(finger).onPress(func);
        return this;
    }

    public Gamepad onRelease(Button button, Runnable func) {
        gamepadButtons.get(button).onRelease(func);
        return this;
    }

    public Gamepad onRelease(Trigger trigger, Runnable func) {
        gamepadTriggers.get(trigger).onRelease(func);
        return this;
    }

    public Gamepad onRelease(TouchpadFinger finger, Runnable func) {
        gamepadTouchpad.get(finger).onRelease(func);
        return this;
    }

    public Gamepad onChange(Button button, Runnable func) {
        gamepadButtons.get(button).onChange(func);
        return this;
    }

    public Gamepad onChange(Trigger trigger, Runnable func) {
        gamepadTriggers.get(trigger).onChange(func);
        return this;
    }

    public Gamepad onChange(TouchpadFinger finger, Runnable func) {
        gamepadTouchpad.get(finger).onChange(func);
        return this;
    }

    public ButtonHandler getGamepadButton(Button button) {
        return gamepadButtons.get(button);
    }

    public TriggerHandler getGamepadTrigger(Trigger trigger) {
        return gamepadTriggers.get(trigger);
    }

    public TouchpadHandler getTouchpadFinger(TouchpadFinger finger) {
        return gamepadTouchpad.get(finger);
    }

    public Gamepad rumble(int duration) {
        gamepad.rumble(1, 1, duration);
        return this;
    }

    public Gamepad rumble(double motor1, double motor2, int duration) {
        gamepad.rumble(motor1, motor2, duration);
        return this;
    }

    public Gamepad rumbleBlips(int count) {
        gamepad.rumbleBlips(count);
        return this;
    }

    public Gamepad rumbleBlips(int count, int runDuration, int stopDuration) {
        RumbleEffect effect = new RumbleEffect();

        for (int i = 0; i < count; i++) {
            effect.addStep(1, 1, runDuration)
                    .addStep(0,0,stopDuration);
        }

        runRumbleEffect(effect);
        return this;
    }

    public Gamepad rumbleBlips(double power, int count, int runDuration, int stopDuration) {
        RumbleEffect effect = new RumbleEffect();

        for (int i = 0; i < count; i++) {
            effect.addStep(power, power, runDuration)
                    .addStep(0,0,stopDuration);
        }

        runRumbleEffect(effect);
        return this;
    }

    public Gamepad rumbleBlips(double motor1, double motor2, int count, int runDuration, int stopDuration) {
        RumbleEffect effect = new RumbleEffect();

        for (int i = 0; i < count; i++) {
            effect.addStep(motor1, motor2, runDuration)
                    .addStep(0,0,stopDuration);
        }

        runRumbleEffect(effect);
        return this;
    }

    public void runRumbleEffect(RumbleEffect effect) {
        gamepad.runRumbleEffect(effect.build());
    }

    public Gamepad stopRumble() {
        gamepad.stopRumble();
        return this;
    }

    public boolean isRumbling() {
        return gamepad.isRumbling();
    }

    private double scaleColor(int color) {
        return Math.round(Range.scale(Range.clip(color, 0, 255), 0, 255, 0, 1));
    }

    public Gamepad setLEDColor(int r, int g, int b, int duration) {
        gamepad.setLedColor(scaleColor(r), scaleColor(g), scaleColor(b), duration);
        return this;
    }

    public Gamepad ledFlash(int r, int g, int b, int count, int runDuration, int stopDuration) {
        LEDEffect effect = new LEDEffect();

        for (int i = 0; i < count; i++) {
            effect.addStep(scaleColor(r), scaleColor(g), scaleColor(b), runDuration)
                    .addStep(0, 0, 0, stopDuration);
        }

        runLEDEffect(effect);
        return this;
    }

    public Gamepad ledFlash(int r, int g, int b, int count) {
        return ledFlash(r, g, b, count, 250, 100);
    }

    public void runLEDEffect(LEDEffect effect) {
        gamepad.runLedEffect(effect.build());
    }

    public void update() {
        for (Button button : Button.values())
            gamepadButtons.get(button).update();

        for (Trigger trigger : Trigger.values())
            gamepadTriggers.get(trigger).update();

        for (TouchpadFinger finger : TouchpadFinger.values())
            gamepadTouchpad.get(finger).update();
    }

    public static class RumbleEffect {
        private com.qualcomm.robotcore.hardware.Gamepad.RumbleEffect.Builder builder;
        private ArrayList<Step> steps;

        public RumbleEffect() {
            builder = new com.qualcomm.robotcore.hardware.Gamepad.RumbleEffect.Builder();
        }

        public com.qualcomm.robotcore.hardware.Gamepad.RumbleEffect.Builder getBuilder() {
            return builder;
        }

        public RumbleEffect setBuilder(com.qualcomm.robotcore.hardware.Gamepad.RumbleEffect.Builder builder) {
            this.builder = builder;
            return this;
        }

        public ArrayList<Step> getSteps() {
            return steps;
        }

        public RumbleEffect setSteps(ArrayList<Step> steps) {
            this.steps = steps;
            return this;
        }

        public RumbleEffect addStep(Step step) {
            steps.add(step);
            return this;
        }

        public RumbleEffect addStep(double motor1, double motor2, int duration) {
            steps.add(new Step(motor1, motor2, duration));
            return this;
        }

        public RumbleEffect removeStep(int index) {
            steps.remove(index);
            return this;
        }

        public com.qualcomm.robotcore.hardware.Gamepad.RumbleEffect build() {
            for (Step step : steps) builder.addStep(step.motor1, step.motor2, step.duration);
            return builder.build();
        }

        public static class Step {
            private final double motor1, motor2;
            private final int duration;

            public Step(double motor1, double motor2, int duration) {
                this.motor1 = motor1;
                this.motor2 = motor2;
                this.duration = duration;
            }
        }
    }

    public static class LEDEffect {
        private com.qualcomm.robotcore.hardware.Gamepad.LedEffect.Builder builder;
        private ArrayList<Step> steps;
        private boolean repeating;

        public LEDEffect() {
            builder = new com.qualcomm.robotcore.hardware.Gamepad.LedEffect.Builder();
        }

        public com.qualcomm.robotcore.hardware.Gamepad.LedEffect.Builder getBuilder() {
            return builder;
        }

        public LEDEffect setBuilder(com.qualcomm.robotcore.hardware.Gamepad.LedEffect.Builder builder) {
            this.builder = builder;
            return this;
        }

        public ArrayList<Step> getSteps() {
            return steps;
        }

        public LEDEffect setSteps(ArrayList<Step> steps) {
            this.steps = steps;
            return this;
        }

        public LEDEffect addStep(Step step) {
            steps.add(step);
            return this;
        }

        public LEDEffect addStep(double r, double g, double b, int duration) {
            steps.add(new Step(r, g, b, duration));
            return this;
        }

        public LEDEffect removeStep(int index) {
            steps.remove(index);
            return this;
        }

        public boolean isRepeating() {
            return repeating;
        }

        public LEDEffect setRepeating(boolean repeating) {
            this.repeating = repeating;
            builder.setRepeating(repeating);
            return this;
        }

        public com.qualcomm.robotcore.hardware.Gamepad.LedEffect build() {
            for (Step step : steps) builder.addStep(step.r, step.g, step.b, step.duration);
            return builder.build();
        }

        public static class Step {
            private final double r, g, b;
            private final int duration;

            public Step(double r, double g, double b, int duration) {
                this.r = r;
                this.g = g;
                this.b = b;
                this.duration = duration;
            }
        }
    }
}
