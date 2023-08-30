package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.teamcode.Systems.Drivetrain;
import org.firstinspires.ftc.teamcode.Systems.Gamepad.Gamepad;
import org.firstinspires.ftc.teamcode.Systems.Motors.Motor;
import org.firstinspires.ftc.teamcode.Systems.Motors.MotorLookupTable;
import org.firstinspires.ftc.teamcode.Systems.Sensors.BatteryVoltageSensor;

public class BaseTele extends OpMode {
    Drivetrain drivetrain;
    Gamepad driver;
    Gamepad manipulator;

    //@Override
    public void init() {
        Motor frontLeft = new Motor(hardwareMap, "frontLeft", MotorLookupTable.GOBILDA_435, Motor.Mode.POWER, 10, false);
        Motor backLeft = new Motor(hardwareMap, "backLeft", MotorLookupTable.GOBILDA_435, Motor.Mode.POWER, 10, false);
        Motor frontRight = new Motor(hardwareMap, "frontRight", MotorLookupTable.GOBILDA_435, Motor.Mode.POWER, 10, false);
        Motor backRight = new Motor(hardwareMap, "backRight", MotorLookupTable.GOBILDA_435, Motor.Mode.POWER, 10, false);
        IMU imu = hardwareMap.get(IMU.class, "imu");

        drivetrain = new Drivetrain(frontLeft, backLeft, frontRight, backRight, imu, new BatteryVoltageSensor(hardwareMap));

        driver = new Gamepad(gamepad1);
        manipulator = new Gamepad(gamepad2);
    }

    //@Override
    public void loop() {
        driver.update();
        manipulator.update();

        drivetrain.log(telemetry);
        telemetry.update();
    }
}
