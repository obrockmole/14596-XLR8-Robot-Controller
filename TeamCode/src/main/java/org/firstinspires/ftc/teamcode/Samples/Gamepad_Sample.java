package org.firstinspires.ftc.teamcode.Samples;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Systems.Gamepad.Gamepad;
import org.firstinspires.ftc.teamcode.Systems.Gamepad.GamepadButtons.Button;
import org.firstinspires.ftc.teamcode.Systems.Gamepad.GamepadButtons.Stick;
import org.firstinspires.ftc.teamcode.Systems.Gamepad.GamepadButtons.Trigger;

@Disabled
@TeleOp(group = "Samples")
public class Gamepad_Sample extends OpMode {
    Gamepad driver, manipulator;

    int dPressed = 0, dReleased = 0, dChanged = 0, mPressed = 0, mReleased = 0, mChanged = 0;

    @Override
    public void init() {
        //Reassigning the gamepad objects to the driver and manipulator custom gamepads
        driver = new Gamepad(gamepad1);
        manipulator = new Gamepad(gamepad2);
    }

    @Override
    public void loop() {
        //Driver Gamepad
        driver.onPress(Button.A, () -> dPressed++) //onPress() fires once when the button is pressed
                .onRelease(Button.A, () -> dReleased++) //onRelease() fires once when the button is released
                .onChange(Button.A, () -> dChanged++) //onChange() fires once when the button is pressed or released
                .update();

        //Manipulator Gamepad
        manipulator.onPress(Trigger.RIGHT_TRIGGER, () -> mPressed++) //onPress() fires once when the trigger exceeds 0.5 being pressed
                .onRelease(Trigger.RIGHT_TRIGGER, () -> mReleased++) //onRelease() fires once when the trigger is released from being pressed
                .onChange(Trigger.RIGHT_TRIGGER, () -> mChanged++) //onChange() fires once when the button is pressed or released
                .update();

        /*
            Other methods include:
            isDown() - returns true if the button is pressed
            isUp() - returns true if the button is not pressed
            onDown() - runs the lambda function if the button is pressed
            onUp() - runs the lambda function if the button is not pressed
            getFingerX() - returns the x position of the finger on the touchpad
            getFingerY() - returns the y position of the finger on the touchpad
            rumble() - rumbles the gamepad
            stopRumble() - stops the gamepad from rumbling
            setLEDColor() - sets the color of the gamepad's LED

            And many more.
         */

        //Telemetry to display gamepad changes and values for gamepad1
        telemetry.addLine("Driver A Pressed: " + dPressed);
        telemetry.addLine("Driver A Released: " + dReleased);
        telemetry.addLine("Driver A Changed: " + dChanged);
        telemetry.addLine("Driver Left Stick X Value: " + driver.getStickX(Stick.LEFT_STICK));
        telemetry.addLine("Driver Left Stick Y Value: " + driver.getStickY(Stick.LEFT_STICK));

        telemetry.addLine();
        telemetry.addLine("----------------------------------------");
        telemetry.addLine();

        //Telemetry to display gamepad changes and values for gamepad1
        telemetry.addLine("Manipulator Right Trigger Pressed: " + mPressed);
        telemetry.addLine("Manipulator Right Trigger Released: " + mReleased);
        telemetry.addLine("Manipulator Right Trigger Changed: " + mChanged);
        telemetry.addLine("Manipulator Right Stick X Value: " + manipulator.getStickX(Stick.RIGHT_STICK));
        telemetry.addLine("Manipulator Right Stick Y Value: " + manipulator.getStickY(Stick.RIGHT_STICK));
    }
}
