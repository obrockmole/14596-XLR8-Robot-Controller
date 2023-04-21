package org.firstinspires.ftc.teamcode.Samples;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.teamcode.Systems.Drivetrain;
import org.firstinspires.ftc.teamcode.Systems.Gamepad.Gamepad;
import org.firstinspires.ftc.teamcode.Systems.Gamepad.GamepadButtons.Button;
import org.firstinspires.ftc.teamcode.Systems.Gamepad.GamepadButtons.Stick;
import org.firstinspires.ftc.teamcode.Systems.Motor;

@Disabled
@TeleOp
public class RobotCentricDriveSample extends OpMode {
    Drivetrain drivetrain;
    Gamepad driver;

    @Override
    public void init() {
        Motor frontLeft = new Motor(hardwareMap, "frontLeft", Motor.Mode.POWER, 751.8 / 360, false);
        Motor backLeft = new Motor(hardwareMap, "backLeft", Motor.Mode.POWER, 751.8 / 360, false);
        Motor frontRight = new Motor(hardwareMap, "frontRight", Motor.Mode.POWER, 751.8 / 360, false);
        Motor backRight = new Motor(hardwareMap, "backRight", Motor.Mode.POWER, 751.8 / 360, false);
        IMU imu = hardwareMap.get(IMU.class, "imu");

        drivetrain = new Drivetrain(frontLeft, backLeft, frontRight, backRight, imu);
    }

    @Override
    public void loop() {
        if (driver.onPress(Button.BACK)) drivetrain.resetIMU();

        drivetrain.standardDrive(driver.getStickY(Stick.LEFT_STICK), driver.getStickX(Stick.LEFT_STICK), driver.getStickX(Stick.RIGHT_STICK));

        drivetrain.log(telemetry);
    }
}
