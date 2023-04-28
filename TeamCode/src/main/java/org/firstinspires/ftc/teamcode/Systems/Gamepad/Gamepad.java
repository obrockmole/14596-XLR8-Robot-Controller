package org.firstinspires.ftc.teamcode.Systems.Gamepad;

import org.firstinspires.ftc.teamcode.Systems.Gamepad.GamepadButtons.Button;
import org.firstinspires.ftc.teamcode.Systems.Gamepad.GamepadButtons.Stick;
import org.firstinspires.ftc.teamcode.Systems.Gamepad.GamepadButtons.Trigger;

import java.util.HashMap;

public class Gamepad {
    com.qualcomm.robotcore.hardware.Gamepad gamepad;

    private HashMap<Button, ButtonHandler> gamepadButtons;
    private HashMap<Trigger, TriggerHandler> gamepadTriggers;

    public Gamepad(com.qualcomm.robotcore.hardware.Gamepad gamepad) {
        this.gamepad = gamepad;
        gamepadButtons = new HashMap<>();
        gamepadTriggers = new HashMap<>();

        for (Button button : Button.values())
            gamepadButtons.put(button, new ButtonHandler(this, button));

        for (Trigger trigger : Trigger.values())
            gamepadTriggers.put(trigger, new TriggerHandler(this, trigger, 0.5));
    }

    public com.qualcomm.robotcore.hardware.Gamepad getGamepad() {
        return gamepad;
    }

    public boolean getButton(Button button) {
        boolean buttonValue = false;
        switch (button) {
            case A:
                buttonValue = gamepad.a;
                break;
            case B:
                buttonValue = gamepad.b;
                break;
            case X:
                buttonValue = gamepad.x;
                break;
            case Y:
                buttonValue = gamepad.y;
                break;
            case LEFT_BUMPER:
                buttonValue = gamepad.left_bumper;
                break;
            case RIGHT_BUMPER:
                buttonValue = gamepad.right_bumper;
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
            case LEFT_STICK:
                buttonValue = gamepad.left_stick_button;
                break;
            case RIGHT_STICK:
                buttonValue = gamepad.right_stick_button;
                break;
            case START:
                buttonValue = gamepad.start;
                break;
            case BACK:
                buttonValue = gamepad.back;
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

    //Button isDown()
    public boolean isDown(Button button) {
        return gamepadButtons.get(button).isDown();
    }

    //Trigger isDown()
    public boolean isDown(Trigger trigger) {
        return gamepadTriggers.get(trigger).isDown();
    }

    //Button isUp()
    public boolean isUp(Button button) {
        return gamepadButtons.get(button).isUp();
    }

    //Trigger isUp()
    public boolean isUp(Trigger trigger) {
        return gamepadTriggers.get(trigger).isUp();
    }

    //Button onPress()
    public boolean onPress(Button button) {
        return gamepadButtons.get(button).onPress();
    }

    public Gamepad onPress(Button button, Runnable func) {
        gamepadButtons.get(button).onPress(func);
        return this;
    }

    //Trigger onPress()
    public boolean onPress(Trigger trigger) {
        return gamepadTriggers.get(trigger).onPress();
    }

    public Gamepad onPress(Trigger trigger, Runnable func) {
        gamepadTriggers.get(trigger).onPress(func);
        return this;
    }

    //Button onRelease()
    public boolean onRelease(Button button) {
        return gamepadButtons.get(button).onRelease();
    }

    public Gamepad onRelease(Button button, Runnable func) {
        gamepadButtons.get(button).onRelease(func);
        return this;
    }

    //Trigger onRelease()
    public boolean onRelease(Trigger trigger) {
        return gamepadTriggers.get(trigger).onRelease();
    }

    public Gamepad onRelease(Trigger trigger, Runnable func) {
        gamepadTriggers.get(trigger).onRelease(func);
        return this;
    }

    //Button onChange()
    public boolean onChange(Button button) {
        return gamepadButtons.get(button).onChange();
    }

    public Gamepad onChange(Button button, Runnable func) {
        gamepadButtons.get(button).onChange(func);
        return this;
    }

    //Trigger onChange()
    public boolean onChange(Trigger trigger) {
        return gamepadTriggers.get(trigger).onChange();
    }

    public Gamepad onChange(Trigger trigger, Runnable func) {
        gamepadTriggers.get(trigger).onChange(func);
        return this;
    }

    public void update() {
        for (Button button : Button.values())
            gamepadButtons.get(button).update();

        for (Trigger trigger : Trigger.values())
            gamepadTriggers.get(trigger).update();
    }

    public ButtonHandler getGamepadButton(Button button) {
        return gamepadButtons.get(button);
    }

    public TriggerHandler getGamepadTrigger(Trigger trigger) {
        return gamepadTriggers.get(trigger);
    }
}
