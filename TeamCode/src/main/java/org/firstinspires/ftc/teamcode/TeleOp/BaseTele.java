package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Systems.Gamepad.Gamepad;
import org.firstinspires.ftc.teamcode.Systems.Robot;

public class BaseTele extends OpMode {
    Gamepad driver;
    Gamepad manipulator;

    Robot robot;

    @Override
    public void init() {
        robot = new Robot(hardwareMap);

        driver = new Gamepad(gamepad1);
        manipulator = new Gamepad(gamepad2);
    }

    @Override
    public void loop() {}

    public void updateSystems() {
        driver.update();
        manipulator.update();

        robot.update();
    }

    public void logSystems() {
        robot.log(telemetry);
        telemetry.addLine("-----Other-----");
        telemetry.addData("Runtime", getRuntime());
        telemetry.update();
    }
}
