package org.firstinspires.ftc.teamcode.Testing;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Systems.Gamepad.FullCustomGamepad;

@TeleOp(group="Testing")
public class FullCustomGamepadTest extends OpMode {
    FullCustomGamepad gamepad = FullCustomGamepad.getInstance();

    @Override
    public void init() {}

    @Override
    public void loop() {
        gamepad.log(telemetry);
        telemetry.update();
    }
}
