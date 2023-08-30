package org.firstinspires.ftc.teamcode.Systems;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.Systems.Motors.Motor;
import org.firstinspires.ftc.teamcode.Systems.Sensors.BatteryVoltageSensor;
import org.firstinspires.ftc.teamcode.Systems.Sensors.IMU;

import java.util.ArrayList;
import java.util.Arrays;

public class Drivetrain {
    private Motor frontLeft, backLeft, frontRight, backRight;
    private IMU imu;
    private BatteryVoltageSensor batteryVoltageSensor;

    int speedScale = 1;

    public Drivetrain() {}

    public Drivetrain(Motor frontLeft, Motor backLeft, Motor frontRight, Motor backRight, IMU imu, BatteryVoltageSensor batteryVoltageSensor) {
        this.frontLeft = frontLeft;
        this.backLeft = backLeft;
        this.frontRight = frontRight;
        this.backRight = backRight;

        this.imu = imu;

        this.batteryVoltageSensor = batteryVoltageSensor;
    }

    public ArrayList<Motor> getMotors() {
        return (ArrayList<Motor>) Arrays.asList(frontLeft, backLeft, frontRight, backRight);
    }

    public Drivetrain setMotors(Motor frontLeft, Motor backLeft, Motor frontRight, Motor backRight) {
        this.frontLeft = frontLeft;
        this.backLeft = backLeft;
        this.frontRight = frontRight;
        this.backRight = backRight;
        return this;
    }

    public Drivetrain resetEncoders() {
        frontLeft.resetEncoder();
        backLeft.resetEncoder();
        frontRight.resetEncoder();
        backRight.resetEncoder();
        return this;
    }

    public int getSpeedScale() {
        return speedScale;
    }

    public Drivetrain setSpeedScale(int speedScale) {
        this.speedScale = speedScale;
        return this;
    }

    public Drivetrain standardDrive(double forward, double rightward, double rotational) {
        double normalizer = Math.max(Math.abs(forward) + Math.abs(rightward) + Math.abs(rotational), 1);

        double flPower =  forward - rightward - rotational;
        double blPower =  forward + rightward - rotational;
        double frPower =  -forward - rightward - rotational;
        double brPower =  -forward + rightward - rotational;

        frontLeft.setTargetPower((flPower / normalizer) * speedScale);
        backLeft.setTargetPower((blPower / normalizer) * speedScale);
        frontRight.setTargetPower((frPower / normalizer) * speedScale);
        backRight.setTargetPower((brPower / normalizer) * speedScale);

        return this;
    }

    public Drivetrain fieldCentricDrive(double forward, double rightward, double rotational) {
        double heading = imu.getYawRadians();
        double fixedForward = forward * Math.cos(-heading) - rightward * Math.sin(-heading);
        double fixedRightward = forward * Math.sin(-heading) + rightward * Math.cos(-heading);

        double normalizer = Math.max(Math.abs(fixedForward) + Math.abs(fixedRightward) + Math.abs(rotational), 1);

        double flPower =  fixedForward - fixedRightward - rotational;
        double blPower =  fixedForward + fixedRightward - rotational;
        double frPower =  -fixedForward - fixedRightward - rotational;
        double brPower =  -fixedForward + fixedRightward - rotational;

        frontLeft.setTargetPower((flPower / normalizer) * speedScale);
        backLeft.setTargetPower((blPower / normalizer) * speedScale);
        frontRight.setTargetPower((frPower / normalizer) * speedScale);
        backRight.setTargetPower((brPower / normalizer) * speedScale);

        return this;
    }

    public IMU getIMU() {
        return imu;
    }

    public Drivetrain setIMU(IMU imu) {
        this.imu = imu;
        return this;
    }

    public BatteryVoltageSensor getBatteryVoltageSensor() {
        return batteryVoltageSensor;
    }

    public Drivetrain setBatteryVoltageSensor(BatteryVoltageSensor batteryVoltageSensor) {
        this.batteryVoltageSensor = batteryVoltageSensor;
        return this;
    }

    public void update() {
        frontLeft.update();
        backLeft.update();
        frontRight.update();
        backRight.update();
    }

    public Drivetrain log(Telemetry telemetry) {
        telemetry.addLine("-----Motor Powers-----");
        telemetry.addData("Front Left Power: ", frontLeft.getPower());
        telemetry.addData("Back Left Power: ", backLeft.getPower());
        telemetry.addData("Front Right Power: ", frontRight.getPower());
        telemetry.addData("Back Right Power: ", backRight.getPower());

        telemetry.addLine();
        telemetry.addLine("-----Current Motor Positions-----");
        telemetry.addData("Front Left Position: ", frontLeft.getCurrentPosition());
        telemetry.addData("Back Left Position: ", backLeft.getCurrentPosition());
        telemetry.addData("Front Right Position: ", frontRight.getCurrentPosition());
        telemetry.addData("Back Right Position: ", backRight.getCurrentPosition());

        telemetry.addLine();
        telemetry.addLine("-----IMU Headings-----");
        telemetry.addData("Yaw: ", imu.getYawDegrees());
        telemetry.addData("Pitch: ", imu.getPitchDegrees());
        telemetry.addData("Roll: ", imu.getRollDegrees());

        telemetry.addLine();
        telemetry.addData("Battery Voltage: ", batteryVoltageSensor.getBatteryVoltage());

        telemetry.addLine();

        return this;
    }
}
