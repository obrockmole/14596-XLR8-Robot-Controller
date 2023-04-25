package org.firstinspires.ftc.teamcode.Samples;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Systems.Gamepad.Gamepad;
import org.firstinspires.ftc.teamcode.Systems.Gamepad.GamepadButtons.Button;
import org.firstinspires.ftc.teamcode.Systems.Gamepad.GamepadButtons.Stick;

@Disabled
@TeleOp(group = "Samples")
public class Gamepad_Sample extends OpMode {
    Gamepad driver, manipulator;

    int dPressed = 0, dReleased = 0, dChanged = 0, mPressed = 0, mReleased = 0, mChanged = 0;

    @Override
    public void init() {
        //Reassigning the gamepad objects to the driver and manipulator gamepads
        driver = new Gamepad(gamepad1);
        manipulator = new Gamepad(gamepad2);
    }

    @Override
    public void loop() {
        // Driver Gamepad
        if (driver.onPress(Button.A)) dPressed++;
        if (driver.onRelease(Button.A)) dReleased++;
        if (driver.onChange(Button.A)) dChanged++;

        // Manipulator Gamepad
        if (manipulator.onPress(Button.A)) mPressed++;
        if (manipulator.onRelease(Button.A)) mReleased++;
        if (manipulator.onChange(Button.A)) mChanged++;

        driver.update();
        manipulator.update();

        //Telemetry to display gamepad changes and values
        telemetry.addLine("Driver A Pressed: " + dPressed);
        telemetry.addLine("Driver A Released: " + dReleased);
        telemetry.addLine("Driver A Changed: " + dChanged);
        telemetry.addLine("Driver Left Stick X Value: " + driver.getStickX(Stick.LEFT_STICK));
        telemetry.addLine("Driver Left Stick Y Value: " + driver.getStickY(Stick.LEFT_STICK));

        telemetry.addLine();
        telemetry.addLine("----------------------------------------");
        telemetry.addLine();

        telemetry.addLine("Manipulator A Pressed: " + mPressed);
        telemetry.addLine("Manipulator A Released: " + mReleased);
        telemetry.addLine("Manipulator A Changed: " + mChanged);
        telemetry.addLine("Manipulator Left Stick X Value: " + manipulator.getStickX(Stick.LEFT_STICK));
        telemetry.addLine("Manipulator Left Stick Y Value: " + manipulator.getStickY(Stick.LEFT_STICK));
    }
}