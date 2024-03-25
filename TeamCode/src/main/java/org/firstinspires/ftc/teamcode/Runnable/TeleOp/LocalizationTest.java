package org.firstinspires.ftc.teamcode.Runnable.TeleOp;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot.LogoFacingDirection;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot.UsbFacingDirection;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.IMU.Parameters;

import org.firstinspires.ftc.teamcode.Systems.Drivetrain;
import org.firstinspires.ftc.teamcode.Systems.Gamepad.Gamepad;
import org.firstinspires.ftc.teamcode.Systems.Gamepad.GamepadButtons.Stick;
import org.firstinspires.ftc.teamcode.Systems.Motors.Motor;
import org.firstinspires.ftc.teamcode.Systems.Motors.MotorList;
import org.firstinspires.ftc.teamcode.Systems.Odometry.Odometry;
import org.firstinspires.ftc.teamcode.Systems.Odometry.OdometryPod;
import org.firstinspires.ftc.teamcode.Systems.Sensors.Encoder;
import org.firstinspires.ftc.teamcode.Systems.Sensors.IMU;

//@Disabled
@TeleOp(group = "TeleOp", name = "Odometry Testing")
public class LocalizationTest extends OpMode {
    Drivetrain drivetrain;

    Gamepad gamepad;

    public void init() {
        drivetrain = new Drivetrain(
                new Motor(hardwareMap, "frontLeft", MotorList.GOBILDA_435, Motor.Mode.POWER, true),
                new Motor(hardwareMap, "backLeft", MotorList.GOBILDA_435, Motor.Mode.POWER, true),
                new Motor(hardwareMap, "frontRight", MotorList.GOBILDA_435, Motor.Mode.POWER, true),
                new Motor(hardwareMap, "backRight", MotorList.GOBILDA_435, Motor.Mode.POWER, true),

                new Odometry(
                        new OdometryPod(new Encoder(hardwareMap, "frontLeft", Encoder.Direction.FORWARD)),
                        new OdometryPod(new Encoder(hardwareMap, "backRight", Encoder.Direction.FORWARD)),
                        new OdometryPod(new Encoder(hardwareMap, "intake1", Encoder.Direction.REVERSE))
                ),

                new IMU(hardwareMap, "imu", new Parameters(new RevHubOrientationOnRobot(LogoFacingDirection.BACKWARD, UsbFacingDirection.UP)))
        );

        gamepad = new Gamepad(gamepad1);

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
    }

    public void loop() {
        gamepad.update();

        drivetrain.standardDrive(gamepad.getStickY(Stick.LEFT_STICK), gamepad.getStickX(Stick.LEFT_STICK), gamepad.getStickX(Stick.RIGHT_STICK));

        drivetrain.update()
                .log(telemetry);
    }
}
