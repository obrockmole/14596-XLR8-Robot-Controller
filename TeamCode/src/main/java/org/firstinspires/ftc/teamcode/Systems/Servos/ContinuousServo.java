package org.firstinspires.ftc.teamcode.Systems.Servos;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Class representing a continuous rotation servo with additional functionality.
 */
public class ContinuousServo {
    CRServo servo;
    double targetPower = 0;

    /**
     * Constructs a new ContinuousServo object with a CRServo.
     *
     * @param servo The CRServo object that this ContinuousServo will control.
     * @param reversed Whether the servo's direction is reversed.
     */
    public ContinuousServo(CRServo servo, boolean reversed) {
        this.servo = servo;
        this.servo.setDirection(reversed ? CRServo.Direction.REVERSE : CRServo.Direction.FORWARD);
    }

    /**
     * Constructs a new ContinuousServo object with the HardwareMap and device name.
     *
     * @param hardwareMap The HardwareMap object used to get the CRServo object.
     * @param name The name of the CRServo object in the HardwareMap.
     * @param reversed Whether the servo's direction is reversed.
     */
    public ContinuousServo(HardwareMap hardwareMap, String name, boolean reversed) {
        this(hardwareMap.get(CRServo.class, name), reversed);
    }

    /**
     * Returns the CRServo object that this ContinuousServo controls.
     *
     * @return The CRServo object that this ContinuousServo controls.
     */
    public CRServo getServo() {
        return servo;
    }

    /**
     * Sets the CRServo object that this ContinuousServo will control.
     *
     * @param servo The CRServo object to control.
     * @return The current ContinuousServo instance.
     */
    public ContinuousServo setServo(CRServo servo) {
        this.servo = servo;
        return this;
    }

    /**
     * Sets the CRServo object that this ContinuousServo will control using the HardwareMap and a device name.
     *
     * @param hardwareMap The HardwareMap object used to get the CRServo object.
     * @param name The name of the CRServo object in the HardwareMap.
     * @return The current ContinuousServo instance.
     */
    public ContinuousServo setServo(HardwareMap hardwareMap, String name) {
        this.servo = hardwareMap.get(CRServo.class, name);
        return this;
    }

    /**
     * Checks if the servo's direction is reversed.
     *
     * @return True if the servo's direction is reversed, false otherwise.
     */
    public boolean isReversed() {
        return servo.getDirection() == CRServo.Direction.REVERSE;
    }

    /**
     * Sets the direction of the servo.
     *
     * @param reversed Whether the servo's direction is reversed.
     * @return The current ContinuousServo instance.
     */
    public ContinuousServo setReversed(boolean reversed) {
        servo.setDirection(reversed ? CRServo.Direction.REVERSE : CRServo.Direction.FORWARD);
        return this;
    }

    /**
     * Returns the current power of the servo.
     *
     * @return The current power of the servo.
     */
    public double getPower() {
        return servo.getPower();
    }

    /**
     * Sets the power of the servo.
     *
     * @param power The power to set for the servo.
     */
    private void setPower(double power) {
        servo.setPower(power);
    }

    /**
     * Returns the target power of the servo.
     *
     * @return The target power of the servo.
     */
    public double getTargetPower() {
        return targetPower;
    }

    /**
     * Sets the target power of the servo.
     *
     * @param targetPower The target power to set for the servo.
     * @return The current ContinuousServo instance.
     */
    public ContinuousServo setTargetPower(double targetPower) {
        this.targetPower = targetPower;
        return this;
    }

    /**
     * Returns the power error of the servo.
     *
     * @return The power error of the servo.
     */
    public double getPowerError() {
        return Math.abs(targetPower - getPower());
    }

    /**
     * Returns the CSV header for the servo's data.
     *
     * @return The CSV header as a string.
     */
    public String getCSVHeader() {
        return "CurrentPower,TargetPower,PowerError";
    }

    /**
     * Returns the CSV data of the servo's current state.
     *
     * @return The CSV data as a string.
     */
    public String getCSVData() {
        return String.format("%s,%s,%s", getPower(), getTargetPower(), getPowerError());
    }

    /**
     * Updates the power of the servo to the target power.
     */
    public void update() {
        servo.setPower(targetPower);
    }

    /**
     * Logs the servo's data to telemetry.
     *
     * @param telemetry The Telemetry object used to log the data.
     * @param hardwareMap The HardwareMap object used to get the name of the servo.
     * @return The current ContinuousServo instance.
     */
    public ContinuousServo log(Telemetry telemetry, HardwareMap hardwareMap) {
        telemetry.addData("Servo", hardwareMap.getNamesOf(servo).toArray()[0]);
        telemetry.addData("Current Power", getPower());
        telemetry.addData("Target Power", getTargetPower());
        telemetry.addData("Reversed", isReversed());

        return this;
    }
}
