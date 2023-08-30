package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Systems.Gamepad.Gamepad;
import org.firstinspires.ftc.teamcode.Systems.Robot;

public class NewBaseTele extends OpMode {
    Robot robot;
    Gamepad driver;
    Gamepad manipulator;

    @Override
    public void init() {
        robot = new Robot(hardwareMap);

        driver = new Gamepad(gamepad1);
        manipulator = new Gamepad(gamepad2);
    }

    @Override
    public void loop() {
        driver.update();
        manipulator.update();

        robot.update()
                .log(telemetry);
        telemetry.update();
    }
}
