package org.firstinspires.ftc.teamcode.Systems.Sensors;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot.LogoFacingDirection;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot.UsbFacingDirection;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU.Parameters;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AngularVelocity;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

public class IMU {
    com.qualcomm.robotcore.hardware.IMU imu;
    com.qualcomm.robotcore.hardware.IMU.Parameters parameters;

    public IMU(com.qualcomm.robotcore.hardware.IMU imu, Parameters parameters) {
        this.imu = imu;
        setParameters(parameters);
    }

    public IMU(com.qualcomm.robotcore.hardware.IMU imu, RevHubOrientationOnRobot orientation) {
        this(imu, new Parameters(orientation));
    }

    public IMU(com.qualcomm.robotcore.hardware.IMU imu, LogoFacingDirection logoDirection, UsbFacingDirection usbDirection) {
        this(imu, new Parameters(new RevHubOrientationOnRobot(logoDirection, usbDirection)));
    }

    public IMU(HardwareMap hardwareMap, String name, Parameters parameters) {
        this(hardwareMap.get(com.qualcomm.robotcore.hardware.IMU.class, name), parameters);
    }

    public IMU(HardwareMap hardwareMap, String name, RevHubOrientationOnRobot orientation) {
        this(hardwareMap, name, new Parameters(orientation));
    }

    public IMU(HardwareMap hardwareMap, String name, LogoFacingDirection logoDirection, UsbFacingDirection usbDirection) {
        this(hardwareMap, name, new Parameters(new RevHubOrientationOnRobot(logoDirection, usbDirection)));
    }

    public com.qualcomm.robotcore.hardware.IMU getIMU() {
        return imu;
    }

    public IMU setIMU(com.qualcomm.robotcore.hardware.IMU imu) {
        this.imu = imu;
        return this;
    }

    public Parameters getParameters() {
        return parameters;
    }

    public IMU setParameters(Parameters parameters) {
        this.parameters = parameters;
        imu.initialize(parameters);
        return this;
    }

    public IMU setParameters(RevHubOrientationOnRobot orientation) {
        return setParameters(new Parameters(orientation));
    }

    public IMU setParameters(LogoFacingDirection logoDirection, UsbFacingDirection usbDirection) {
        return setParameters(new Parameters(new RevHubOrientationOnRobot(logoDirection, usbDirection)));
    }

    public YawPitchRollAngles getYawPitchRoll() {
        return imu.getRobotYawPitchRollAngles();
    }

    public double getYaw(AngleUnit unit) {
        return getYawPitchRoll().getYaw(unit);
    }

    public double getYawDegrees() {
        return getYaw(AngleUnit.DEGREES);
    }

    public double getYawRadians() {
        return getYaw(AngleUnit.RADIANS);
    }

    public IMU resetYaw() {
        imu.resetYaw();
        return this;
    }

    public double getPitch(AngleUnit unit) {
        return getYawPitchRoll().getPitch(unit);
    }

    public double getPitchDegrees() {
        return getPitch(AngleUnit.DEGREES);
    }

    public double getPitchRadians() {
        return getPitch(AngleUnit.RADIANS);
    }

    public double getRoll(AngleUnit unit) {
        return getYawPitchRoll().getRoll(unit);
    }

    public double getRollDegrees() {
        return getRoll(AngleUnit.DEGREES);
    }

    public double getRollRadians() {
        return getRoll(AngleUnit.RADIANS);
    }

    public AngularVelocity getAngularVelocity(AngleUnit unit) {
        return imu.getRobotAngularVelocity(unit);
    }

    public AngularVelocity getAngularVelocityDegrees() {
        return getAngularVelocity(AngleUnit.DEGREES);
    }

    public AngularVelocity getAngularVelocityRadians() {
        return getAngularVelocity(AngleUnit.RADIANS);
    }

    public double getAngularVelocityX(AngleUnit unit) {
        return getAngularVelocity(unit).xRotationRate;
    }

    public double getAngularVelocityXDegrees() {
        return getAngularVelocityX(AngleUnit.DEGREES);
    }

    public double getAngularVelocityXRadians() {
        return getAngularVelocityX(AngleUnit.RADIANS);
    }

    public double getAngularVelocityY(AngleUnit unit) {
        return getAngularVelocity(unit).yRotationRate;
    }

    public double getAngularVelocityYDegrees() {
        return getAngularVelocityY(AngleUnit.DEGREES);
    }

    public double getAngularVelocityYRadians() {
        return getAngularVelocityY(AngleUnit.RADIANS);
    }

    public double getAngularVelocityZ(AngleUnit unit) {
        return getAngularVelocity(unit).zRotationRate;
    }

    public double getAngularVelocityZDegrees() {
        return getAngularVelocityZ(AngleUnit.DEGREES);
    }

    public double getAngularVelocityZRadians() {
        return getAngularVelocityZ(AngleUnit.RADIANS);
    }

    public IMU log(Telemetry telemetry, HardwareMap hardwareMap) {
        telemetry.addData("Sensor", hardwareMap.getNamesOf(imu).toArray()[0]);
        telemetry.addData("Yaw (degrees)", getYawDegrees());
        telemetry.addData("Pitch (degrees)", getPitchDegrees());
        telemetry.addData("Roll (degrees)", getRollDegrees());
        return this;
    }
}
