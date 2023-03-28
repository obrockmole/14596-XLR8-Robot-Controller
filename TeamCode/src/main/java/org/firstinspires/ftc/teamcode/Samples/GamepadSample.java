package org.firstinspires.ftc.teamcode.Samples;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Systems.Gamepad.Gamepad;
import org.firstinspires.ftc.teamcode.Systems.Gamepad.GamepadButtons.Button;

//@Disabled
@TeleOp
public class GamepadSample extends OpMode {
    Gamepad driver, manipulator;

    int dPressed = 0, dReleased = 0, dChanged = 0, mPressed = 0, mReleased = 0, mChanged = 0;

    @Override
    public void init() {
        driver = new Gamepad(gamepad1);
        manipulator = new Gamepad(gamepad2);
    }

    @Override
    public void loop() {
        if (driver.onPress(Button.A))
            dPressed++;
        if (driver.onRelease(Button.A))
            dReleased++;
        if (driver.onChange(Button.A))
            dChanged++;

        if (manipulator.onPress(Button.A))
            mPressed++;
        if (manipulator.onRelease(Button.A))
            mReleased++;
        if (manipulator.onChange(Button.A))
            mChanged++;

        telemetry.addLine("Driver A Pressed: " + dPressed);
        telemetry.addLine("Driver A Released: " + dReleased);
        telemetry.addLine("Driver A Changed: " + dChanged);
        telemetry.addLine();
        telemetry.addLine("Manipulator A Pressed: " + mPressed);
        telemetry.addLine("Manipulator A Released: " + mReleased);
        telemetry.addLine("Manipulator A Changed: " + mChanged);
    }
}
