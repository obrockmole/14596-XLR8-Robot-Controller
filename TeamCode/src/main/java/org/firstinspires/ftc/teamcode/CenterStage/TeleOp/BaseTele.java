package org.firstinspires.ftc.teamcode.CenterStage.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.CenterStage.Robot;
import org.firstinspires.ftc.teamcode.Systems.Gamepad.Gamepad;

public class BaseTele extends OpMode {
    protected Gamepad driver;
    protected Gamepad manipulator;

    protected Robot robot;

    public void init() {
        driver = new Gamepad(gamepad1);
        manipulator = new Gamepad(gamepad2);

        robot = new Robot(hardwareMap);
        robot.initialize();
    }

    public void start() {
        robot.liftDeployment.start();
    }

    public void loop() {}

    public void update() {
        driver.update();
        manipulator.update();

        robot.update(true);
    }

    public void log() {
        robot.log(telemetry, false, false);

        telemetry.addLine("-----Other-----");
        telemetry.addData("Runtime", getRuntime());
        telemetry.update();
    }
}
