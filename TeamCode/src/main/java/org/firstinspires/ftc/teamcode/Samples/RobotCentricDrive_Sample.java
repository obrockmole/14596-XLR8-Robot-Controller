package org.firstinspires.ftc.teamcode.Samples;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.teamcode.Systems.Drivetrain;
import org.firstinspires.ftc.teamcode.Systems.Gamepad.Gamepad;
import org.firstinspires.ftc.teamcode.Systems.Gamepad.GamepadButtons.Stick;
import org.firstinspires.ftc.teamcode.Systems.Motors.Motor;

@Disabled
@TeleOp(group = "Samples")
public class RobotCentricDrive_Sample extends OpMode {
    Drivetrain drivetrain; //This example uses custom motors. See MotorSample.java for more information
    Gamepad driver;

    @Override
    public void init() {
        //Initialize the motors and IMU
        Motor frontLeft = new Motor(hardwareMap, "frontLeft", Motor.Mode.POWER, 751.8 / 360, 10, false);
        Motor backLeft = new Motor(hardwareMap, "backLeft", Motor.Mode.POWER, 751.8 / 360, 10, false);
        Motor frontRight = new Motor(hardwareMap, "frontRight", Motor.Mode.POWER, 751.8 / 360, 10, false);
        Motor backRight = new Motor(hardwareMap, "backRight", Motor.Mode.POWER, 751.8 / 360, 10, false);
        IMU imu = hardwareMap.get(IMU.class, "imu");

        drivetrain = new Drivetrain(frontLeft, backLeft, frontRight, backRight, imu); //Assign the motors and IMU to the drivetrain

        driver = new Gamepad(gamepad1); //Assign gamepad1 to the driver
    }

    @Override
    public void loop() {
        driver.update();
        /*
          The drivetrain has two drive modes: field centric and robot centric.
          Robot centric is the default drive mode. The robot will always move in relation to the position its facing.
          This is controlled with the standardDrive() method and joystick values.
         */
        drivetrain.standardDrive(driver.getStickY(Stick.LEFT_STICK), driver.getStickX(Stick.LEFT_STICK), driver.getStickX(Stick.RIGHT_STICK));

        drivetrain.log(telemetry);
        drivetrain.update(); //Update the drivetrain. This sets the motor powers
    }
}
