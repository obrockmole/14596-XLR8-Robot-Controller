package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.teamcode.Systems.Drivetrain;
import org.firstinspires.ftc.teamcode.Systems.Gamepad.Gamepad;
import org.firstinspires.ftc.teamcode.Systems.Motors.Motor;

public class BaseTele extends OpMode {
    Drivetrain drivetrain;
    Gamepad driver, manipulator;

    public void init() {
        Motor frontLeft = new Motor(hardwareMap, "frontLeft", Motor.Mode.POWER, 751.8 / 360, 10, false);
        Motor backLeft = new Motor(hardwareMap, "backLeft", Motor.Mode.POWER, 751.8 / 360, 10, false);
        Motor frontRight = new Motor(hardwareMap, "frontRight", Motor.Mode.POWER, 751.8 / 360, 10, false);
        Motor backRight = new Motor(hardwareMap, "backRight", Motor.Mode.POWER, 751.8 / 360, 10, false);
        IMU imu = hardwareMap.get(IMU.class, "imu");

        drivetrain = new Drivetrain(frontLeft, backLeft, frontRight, backRight, imu);

        driver = new Gamepad(gamepad1);
        manipulator = new Gamepad(gamepad2);
    }

    public void loop() {
        driver.update();
        manipulator.update();

        drivetrain.log(telemetry);
        telemetry.update();
    }
}
