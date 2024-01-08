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

/**
 * Class representing an Inertial Measurement Unit (IMU).
 */
public class IMU {
    private com.qualcomm.robotcore.hardware.IMU imu;
    private com.qualcomm.robotcore.hardware.IMU.Parameters parameters;

    /**
     * Constructs a new IMU object with an IMU and parameters.
     *
     * @param imu The IMU object that this IMU will use.
     * @param parameters The parameters to set for the IMU.
     */
    public IMU(com.qualcomm.robotcore.hardware.IMU imu, Parameters parameters) {
        this.imu = imu;
        setParameters(parameters);
    }

    /**
     * Constructs a new IMU object with an IMU and an orientation.
     *
     * @param imu The IMU object that this IMU will use.
     * @param orientation The orientation to set for the IMU.
     */
    public IMU(com.qualcomm.robotcore.hardware.IMU imu, RevHubOrientationOnRobot orientation) {
        this(imu, new Parameters(orientation));
    }

    /**
     * Constructs a new IMU object with an IMU, a logo direction, and a USB direction.
     *
     * @param imu The IMU object that this IMU will use.
     * @param logoDirection The logo direction to set for the IMU.
     * @param usbDirection The USB direction to set for the IMU.
     */
    public IMU(com.qualcomm.robotcore.hardware.IMU imu, LogoFacingDirection logoDirection, UsbFacingDirection usbDirection) {
        this(imu, new Parameters(new RevHubOrientationOnRobot(logoDirection, usbDirection)));
    }

    /**
     * Constructs a new IMU object with a HardwareMap, a device name, and parameters.
     *
     * @param hardwareMap The HardwareMap object used to get the IMU object.
     * @param name The name of the IMU object in the HardwareMap.
     * @param parameters The parameters to set for the IMU.
     */
    public IMU(HardwareMap hardwareMap, String name, Parameters parameters) {
        this(hardwareMap.get(com.qualcomm.robotcore.hardware.IMU.class, name), parameters);
    }

    /**
     * Constructs a new IMU object with a HardwareMap, a device name, and an orientation.
     *
     * @param hardwareMap The HardwareMap object used to get the IMU object.
     * @param name The name of the IMU object in the HardwareMap.
     * @param orientation The orientation to set for the IMU.
     */
    public IMU(HardwareMap hardwareMap, String name, RevHubOrientationOnRobot orientation) {
        this(hardwareMap, name, new Parameters(orientation));
    }

    /**
     * Constructs a new IMU object with a HardwareMap, a device name, a logo direction, and a USB direction.
     *
     * @param hardwareMap The HardwareMap object used to get the IMU object.
     * @param name The name of the IMU object in the HardwareMap.
     * @param logoDirection The logo direction to set for the IMU.
     * @param usbDirection The USB direction to set for the IMU.
     */
    public IMU(HardwareMap hardwareMap, String name, LogoFacingDirection logoDirection, UsbFacingDirection usbDirection) {
        this(hardwareMap, name, new Parameters(new RevHubOrientationOnRobot(logoDirection, usbDirection)));
    }

    /**
     * Returns the IMU object that this IMU uses.
     *
     * @return The IMU object that this IMU uses.
     */
    public com.qualcomm.robotcore.hardware.IMU getIMU() {
        return imu;
    }

    /**
     * Sets the IMU object that this IMU will use.
     *
     * @param imu The IMU object to use.
     * @return The current IMU instance.
     */
    public IMU setIMU(com.qualcomm.robotcore.hardware.IMU imu) {
        this.imu = imu;
        return this;
    }

    /**
     * Returns the parameters of the IMU.
     *
     * @return The parameters of the IMU.
     */
    public Parameters getParameters() {
        return parameters;
    }

    /**
     * Sets the parameters of the IMU.
     *
     * @param parameters The parameters to set for the IMU.
     * @return The current IMU instance.
     */
    public IMU setParameters(Parameters parameters) {
        this.parameters = parameters;
        imu.initialize(parameters);
        return this;
    }

    /**
     * Sets the orientation of the IMU.
     *
     * @param orientation The orientation to set for the IMU.
     * @return The current IMU instance.
     */
    public IMU setOrientation(RevHubOrientationOnRobot orientation) {
        return setParameters(new Parameters(orientation));
    }

    /**
     * Sets the orientation of the IMU.
     *
     * @param logoDirection The logo direction to set for the IMU.
     * @param usbDirection The USB direction to set for the IMU.
     * @return The current IMU instance.
     */
    public IMU setOrientation(LogoFacingDirection logoDirection, UsbFacingDirection usbDirection) {
        return setParameters(new Parameters(new RevHubOrientationOnRobot(logoDirection, usbDirection)));
    }

    /**
     * Returns the yaw, pitch, and roll angles of the IMU.
     *
     * @return The yaw, pitch, and roll angles of the IMU.
     */
    public YawPitchRollAngles getYawPitchRoll() {
        return imu.getRobotYawPitchRollAngles();
    }

    /**
     * Returns the yaw angle of the IMU.
     *
     * @param unit The unit of measurement for the angle.
     * @return The yaw angle of the IMU.
     */
    public double getYaw(AngleUnit unit) {
        return getYawPitchRoll().getYaw(unit);
    }

    /**
     * Returns the yaw angle of the IMU in degrees.
     *
     * @return The yaw angle of the IMU in degrees.
     */
    public double getYawDegrees() {
        return getYaw(AngleUnit.DEGREES);
    }

    /**
     * Returns the yaw angle of the IMU in radians.
     *
     * @return The yaw angle of the IMU in radians.
     */
    public double getYawRadians() {
        return getYaw(AngleUnit.RADIANS);
    }

    /**
     * Returns the pitch angle of the IMU.
     *
     * @param unit The unit of measurement for the angle.
     * @return The pitch angle of the IMU.
     */
    public double getPitch(AngleUnit unit) {
        return getYawPitchRoll().getPitch(unit);
    }

    /**
     * Returns the pitch angle of the IMU in degrees.
     *
     * @return The pitch angle of the IMU in degrees.
     */
    public double getPitchDegrees() {
        return getPitch(AngleUnit.DEGREES);
    }

    /**
     * Returns the pitch angle of the IMU in radians.
     *
     * @return The pitch angle of the IMU in radians.
     */
    public double getPitchRadians() {
        return getPitch(AngleUnit.RADIANS);
    }

    /**
     * Returns the roll angle of the IMU.
     *
     * @param unit The unit of measurement for the angle.
     * @return The roll angle of the IMU.
     */
    public double getRoll(AngleUnit unit) {
        return getYawPitchRoll().getRoll(unit);
    }

    /**
     * Returns the roll angle of the IMU in degrees.
     *
     * @return The roll angle of the IMU in degrees.
     */
    public double getRollDegrees() {
        return getRoll(AngleUnit.DEGREES);
    }

    /**
     * Returns the roll angle of the IMU in radians.
     *
     * @return The roll angle of the IMU in radians.
     */
    public double getRollRadians() {
        return getRoll(AngleUnit.RADIANS);
    }

    /**
     * Resets the yaw angle of the IMU.
     *
     * @return The current IMU instance.
     */
    public IMU resetYaw() {
        imu.resetYaw();
        return this;
    }

    /**
     * Returns the angular velocity of the IMU.
     *
     * @param unit The unit of measurement for the angular velocity.
     * @return The angular velocity of the IMU.
     */
    public AngularVelocity getAngularVelocity(AngleUnit unit) {
        return imu.getRobotAngularVelocity(unit);
    }

    /**
     * Returns the angular velocity of the IMU in degrees.
     *
     * @return The angular velocity of the IMU in degrees.
     */
    public AngularVelocity getAngularVelocityDegrees() {
        return getAngularVelocity(AngleUnit.DEGREES);
    }

    /**
     * Returns the angular velocity of the IMU in radians.
     *
     * @return The angular velocity of the IMU in radians.
     */
    public AngularVelocity getAngularVelocityRadians() {
        return getAngularVelocity(AngleUnit.RADIANS);
    }

    /**
     * Returns the X component of the angular velocity of the IMU.
     *
     * @param unit The unit of measurement for the angular velocity.
     * @return The X component of the angular velocity of the IMU.
     */
    public double getAngularVelocityX(AngleUnit unit) {
        return getAngularVelocity(unit).xRotationRate;
    }

    /**
     * Returns the X component of the angular velocity of the IMU in degrees.
     *
     * @return The X component of the angular velocity of the IMU in degrees.
     */
    public double getAngularVelocityXDegrees() {
        return getAngularVelocityX(AngleUnit.DEGREES);
    }

    /**
     * Returns the X component of the angular velocity of the IMU in radians.
     *
     * @return The X component of the angular velocity of the IMU in radians.
     */
    public double getAngularVelocityXRadians() {
        return getAngularVelocityX(AngleUnit.RADIANS);
    }

    /**
     * Returns the Y component of the angular velocity of the IMU.
     *
     * @param unit The unit of measurement for the angular velocity.
     * @return The Y component of the angular velocity of the IMU.
     */
    public double getAngularVelocityY(AngleUnit unit) {
        return getAngularVelocity(unit).yRotationRate;
    }

    /**
     * Returns the Y component of the angular velocity of the IMU in degrees.
     *
     * @return The Y component of the angular velocity of the IMU in degrees.
     */
    public double getAngularVelocityYDegrees() {
        return getAngularVelocityY(AngleUnit.DEGREES);
    }

    /**
     * Returns the Y component of the angular velocity of the IMU in radians.
     *
     * @return The Y component of the angular velocity of the IMU in radians.
     */
    public double getAngularVelocityYRadians() {
        return getAngularVelocityY(AngleUnit.RADIANS);
    }

    /**
     * Returns the Z component of the angular velocity of the IMU.
     *
     * @param unit The unit of measurement for the angular velocity.
     * @return The Z component of the angular velocity of the IMU.
     */
    public double getAngularVelocityZ(AngleUnit unit) {
        return getAngularVelocity(unit).zRotationRate;
    }

    /**
     * Returns the Z component of the angular velocity of the IMU in degrees.
     *
     * @return The Z component of the angular velocity of the IMU in degrees.
     */
    public double getAngularVelocityZDegrees() {
        return getAngularVelocityZ(AngleUnit.DEGREES);
    }

    /**
     * Returns the Z component of the angular velocity of the IMU in radians.
     *
     * @return The Z component of the angular velocity of the IMU in radians.
     */
    public double getAngularVelocityZRadians() {
        return getAngularVelocityZ(AngleUnit.RADIANS);
    }

    /**
     * Returns the CSV header for the IMU's data.
     *
     * @return The CSV header as a string.
     */
    public String getCSVHeader() {
        return "Yaw,Pitch,Roll,AngularVeloX,AngularVelocY,AngularVeloZ";
    }

    /**
     * Returns the CSV data of the IMU's current state.
     *
     * @return The CSV data as a string.
     */
    public String getCSVData() {
        return String.format("%s,%s,%s,%s,%s,%s", getYawDegrees(), getPitchDegrees(), getRollDegrees(), getAngularVelocityXDegrees(), getAngularVelocityYDegrees(), getAngularVelocityZDegrees());
    }

    /**
     * Logs the IMU's data to telemetry.
     *
     * @param telemetry The Telemetry object used to log the data.
     * @param hardwareMap The HardwareMap object used to get the name of the IMU.
     * @return The current IMU instance.
     */
    public IMU log(Telemetry telemetry, HardwareMap hardwareMap) {
        telemetry.addData("Sensor", hardwareMap.getNamesOf(imu).toArray()[0]);
        telemetry.addData("Yaw (degrees)", getYawDegrees());
        telemetry.addData("Pitch (degrees)", getPitchDegrees());
        telemetry.addData("Roll (degrees)", getRollDegrees());
        return this;
    }
}
