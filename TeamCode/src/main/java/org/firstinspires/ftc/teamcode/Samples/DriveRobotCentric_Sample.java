package org.firstinspires.ftc.teamcode.Samples;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.teamcode.Systems.Drivetrain;
import org.firstinspires.ftc.teamcode.Systems.Gamepad.Gamepad;
import org.firstinspires.ftc.teamcode.Systems.Gamepad.GamepadButtons.Stick;
import org.firstinspires.ftc.teamcode.Systems.Motors.Motor;
import org.firstinspires.ftc.teamcode.Systems.Motors.MotorLookupTable;
import org.firstinspires.ftc.teamcode.Systems.Sensors.BatteryVoltageSensor;

@Disabled
@TeleOp(group = "Samples")
public class DriveRobotCentric_Sample extends OpMode {
    Drivetrain drivetrain;

    Gamepad driver; //This example uses a custom gamepad. See Gamepad_Sample.java for more information

    @Override
    public void init() {
        driver = new Gamepad(gamepad1); //Initializes custom gamepad

        //Initialize the motors and IMU
        //This example uses custom motors. See Motor_Sample.java for more information
        Motor frontLeft = new Motor(hardwareMap, "frontLeft", MotorLookupTable.GOBILDA_435, Motor.Mode.POWER, 10, false);
        Motor backLeft = new Motor(hardwareMap, "backLeft", MotorLookupTable.GOBILDA_435, Motor.Mode.POWER, 10, false);
        Motor frontRight = new Motor(hardwareMap, "frontRight", MotorLookupTable.GOBILDA_435, Motor.Mode.POWER, 10, false);
        Motor backRight = new Motor(hardwareMap, "backRight", MotorLookupTable.GOBILDA_435, Motor.Mode.POWER, 10, false);
        IMU imu = hardwareMap.get(IMU.class, "imu");

        drivetrain = new Drivetrain(frontLeft, backLeft, frontRight, backRight, imu, new BatteryVoltageSensor(hardwareMap)); //Assign the motors and IMU to the drivetrain
    }

    @Override
    public void loop() {
        driver.update(); //Update the gamepad

        /*
          The drivetrain has two drive modes: field centric and robot centric.
          Robot centric is the default drive mode. The robot will always move in relation to the position its facing.
          This is controlled with the standardDrive() method and joystick values.
         */
        drivetrain.fieldCentricDrive(driver.getStickY(Stick.LEFT_STICK), driver.getStickX(Stick.LEFT_STICK), driver.getStickX(Stick.RIGHT_STICK))
                .log(telemetry) //Log drivetrain data to the telemetry
                .update(); //Update the object.
    }
}
