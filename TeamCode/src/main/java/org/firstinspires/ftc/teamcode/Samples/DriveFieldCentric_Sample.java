package org.firstinspires.ftc.teamcode.Samples;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot.LogoFacingDirection;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot.UsbFacingDirection;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.IMU.Parameters;

import org.firstinspires.ftc.teamcode.Systems.Drivetrain;
import org.firstinspires.ftc.teamcode.Systems.Gamepad.Gamepad;
import org.firstinspires.ftc.teamcode.Systems.Gamepad.GamepadButtons.Button;
import org.firstinspires.ftc.teamcode.Systems.Gamepad.GamepadButtons.Stick;
import org.firstinspires.ftc.teamcode.Systems.Motors.Motor;
import org.firstinspires.ftc.teamcode.Systems.Motors.MotorList;
import org.firstinspires.ftc.teamcode.Systems.Sensors.IMU;

@Disabled
@TeleOp(group = "Samples", name = "Field Centric Drive Sample")
public class DriveFieldCentric_Sample extends OpMode {
    Drivetrain drivetrain;

    Gamepad driver; //This example uses a custom gamepad. See Gamepad_Sample.java for more information

    @Override
    public void init() {
        driver = new Gamepad(gamepad1); //Initializes custom gamepad

        //Initialize the motors and IMU
        //This example uses custom motors. See Motor_Sample.java for more information
        Motor frontLeft = new Motor(hardwareMap, "frontLeft", MotorList.GOBILDA_435, Motor.Mode.POWER, 10, false);
        Motor backLeft = new Motor(hardwareMap, "backLeft", MotorList.GOBILDA_435, Motor.Mode.POWER, 10, false);
        Motor frontRight = new Motor(hardwareMap, "frontRight", MotorList.GOBILDA_435, Motor.Mode.POWER, 10, false);
        Motor backRight = new Motor(hardwareMap, "backRight", MotorList.GOBILDA_435, Motor.Mode.POWER, 10, false);
        IMU imu = new IMU(hardwareMap, "imu", new Parameters(new RevHubOrientationOnRobot(LogoFacingDirection.UP, UsbFacingDirection.FORWARD)));

        drivetrain = new Drivetrain(frontLeft, backLeft, frontRight, backRight, imu); //Assign the motors and IMU to the drivetrain. Odometry is not used in this example so it is omitted.
    }

    @Override
    public void loop() {
        driver.onPress(Button.BACK, () -> drivetrain.resetIMUYaw()) //Reset the IMU when the back button is pressed on the gamepad in order to reset orientation
                .update(); //Update the gamepad

        /*
          The drivetrain has two drive modes: field centric and robot centric.
          Field centric means the robot will always move in relation to the field regardless of orientation.
          This is controlled with the fieldCentricDrive() method and joystick values.
         */
        drivetrain.fieldCentricDrive(driver.getStickY(Stick.LEFT_STICK), driver.getStickX(Stick.LEFT_STICK), driver.getStickX(Stick.RIGHT_STICK))
                .log(telemetry) //Log drivetrain data to the telemetry
                .update(); //Update the object.
    }
}
