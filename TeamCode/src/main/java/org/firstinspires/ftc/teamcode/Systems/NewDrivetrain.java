package org.firstinspires.ftc.teamcode.Systems;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.Systems.Motors.Motor;
import org.firstinspires.ftc.teamcode.Systems.Odometry.Odometry;
import org.firstinspires.ftc.teamcode.Systems.Odometry.OdometryPod;
import org.firstinspires.ftc.teamcode.Systems.Sensors.BatteryVoltageSensor;
import org.firstinspires.ftc.teamcode.Systems.Sensors.IMU;
import com.qualcomm.robotcore.hardware.IMU.Parameters;

import java.util.ArrayList;
import java.util.Arrays;

public class NewDrivetrain {
    private Motor frontLeft, backLeft, frontRight, backRight;
    private Odometry odometry;
    private IMU imu;
    private BatteryVoltageSensor batteryVoltageSensor;

    int speedScale = 1;

    public NewDrivetrain() {}

    public NewDrivetrain(Motor frontLeft, Motor backLeft, Motor frontRight, Motor backRight, Odometry odometry, IMU imu) {
        this.frontLeft = frontLeft;
        this.backLeft = backLeft;
        this.frontRight = frontRight;
        this.backRight = backRight;

        this.odometry = odometry;

        this.imu = imu;
    }

    public Motor getFrontLeft() {
        return frontLeft;
    }

    public NewDrivetrain setFrontLeft(Motor frontLeft) {
        this.frontLeft = frontLeft;
        return this;
    }

    public Motor getBackLeft() {
        return backLeft;
    }

    public NewDrivetrain setBackLeft(Motor backLeft) {
        this.backLeft = backLeft;
        return this;
    }

    public Motor getFrontRight() {
        return frontRight;
    }

    public NewDrivetrain setFrontRight(Motor frontRight) {
        this.frontRight = frontRight;
        return this;
    }

    public Motor getBackRight() {
        return backRight;
    }

    public NewDrivetrain setBackRight(Motor backRight) {
        this.backRight = backRight;
        return this;
    }

    public ArrayList<Motor> getMotors() {
        return (ArrayList<Motor>) Arrays.asList(frontLeft, backLeft, frontRight, backRight);
    }

    public NewDrivetrain setMotors(Motor frontLeft, Motor backLeft, Motor frontRight, Motor backRight) {
        this.frontLeft = frontLeft;
        this.backLeft = backLeft;
        this.frontRight = frontRight;
        this.backRight = backRight;
        return this;
    }

    public Odometry getOdometry() {
        return odometry;
    }

    public NewDrivetrain setOdometry(Odometry odometry) {
        this.odometry = odometry;
        return this;
    }

    public NewDrivetrain setOdometryPods(OdometryPod... pods) {
        odometry = new Odometry(pods);
        return this;
    }

    public int getSpeedScale() {
        return speedScale;
    }

    public NewDrivetrain setSpeedScale(int speedScale) {
        this.speedScale = speedScale;
        return this;
    }

    public NewDrivetrain standardDrive(double forward, double rightward, double rotational) {
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

    public NewDrivetrain fieldCentricDrive(double forward, double rightward, double rotational) {
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

    public NewDrivetrain setIMU(IMU imu) {
        this.imu = imu;
        return this;
    }

    public NewDrivetrain setIMU(HardwareMap hardwareMap, String name) {
        imu = hardwareMap.get(IMU.class, name);
        return this;
    }

    public NewDrivetrain setIMUOrientation(RevHubOrientationOnRobot orientation) {
        imu.setOrientation(orientation);
        return this;
    }

    public NewDrivetrain setIMUOrientation(RevHubOrientationOnRobot.LogoFacingDirection logoFacingDirection, RevHubOrientationOnRobot.UsbFacingDirection usbFacingDirection) {
        imu.setOrientation(logoFacingDirection, usbFacingDirection);
        return this;
    }

    public NewDrivetrain resetIMUYaw() {
        imu.resetYaw();
        return this;
    }

    public BatteryVoltageSensor getBatteryVoltageSensor() {
        return batteryVoltageSensor;
    }

    public NewDrivetrain setBatteryVoltageSensor(BatteryVoltageSensor batteryVoltageSensor) {
        this.batteryVoltageSensor = batteryVoltageSensor;
        return this;
    }

    public NewDrivetrain update() {
        frontLeft.update();
        backLeft.update();
        frontRight.update();
        backRight.update();

        odometry.update();

        return this;
    }

    public NewDrivetrain log(Telemetry telemetry) {
        telemetry.addLine("-----Motor Powers-----");
        telemetry.addData("Front Left Power", frontLeft.getPower());
        telemetry.addData("Back Left Power", backLeft.getPower());
        telemetry.addData("Front Right Power", frontRight.getPower());
        telemetry.addData("Back Right Power", backRight.getPower());

        telemetry.addLine();
        telemetry.addLine("-----Motor Positions-----");
        telemetry.addData("Front Left Position", frontLeft.getCurrentPosition());
        telemetry.addData("Back Left Position", backLeft.getCurrentPosition());
        telemetry.addData("Front Right Position", frontRight.getCurrentPosition());
        telemetry.addData("Back Right Position", backRight.getCurrentPosition());

        telemetry.addLine();
        telemetry.addLine("-----Odometry Positions-----");
        telemetry.addData("Left Pod Position", odometry.getCurrentPosition(0));
        telemetry.addData("Right Pod Position", odometry.getCurrentPosition(1));
        telemetry.addData("Center Pod Position", odometry.getCurrentPosition(2));

        telemetry.addLine();
        telemetry.addLine("-----Odometry Retraction-----");
        telemetry.addData("Left Pod Retracted", odometry.isRetracted(0));
        telemetry.addData("Right Pod Retracted", odometry.isRetracted(1));
        telemetry.addData("Center Pod Retracted", odometry.isRetracted(2));

        telemetry.addLine();
        telemetry.addLine("-----IMU Headings-----");
        telemetry.addData("Yaw", imu.getYawDegrees());
        telemetry.addData("Pitch", imu.getPitchDegrees());
        telemetry.addData("Roll", imu.getRollDegrees());

        telemetry.addLine();
        telemetry.addLine("-----Battery Voltage-----");
        telemetry.addData("Voltage", batteryVoltageSensor.getBatteryVoltage());

        telemetry.addLine();

        return this;
    }
}
