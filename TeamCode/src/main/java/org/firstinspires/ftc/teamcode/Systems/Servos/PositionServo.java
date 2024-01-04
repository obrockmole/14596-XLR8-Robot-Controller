package org.firstinspires.ftc.teamcode.Systems.Servos;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.Arrays;

/**
 * Class representing a position servo with additional functionality.
 */
public class PositionServo {
    Servo servo;
    double targetPosition = 0;
    double[] scaleRange = new double[]{0, 1};

    /**
     * Constructs a new PositionServo object with the given parameters.
     *
     * @param servo    The Servo object that this PositionServo will control.
     * @param minScale The minimum scale for the servo's range.
     * @param maxScale The maximum scale for the servo's range.
     * @param reversed Whether the servo's direction is reversed.
     */
    public PositionServo(Servo servo, double minScale, double maxScale, boolean reversed) {
        this.servo = servo;
        setReversed(reversed);

        this.setScaleRange(minScale, maxScale);
    }

    /**
     * Constructs a new PositionServo object with the given parameters.
     *
     * @param hardwareMap The HardwareMap object used to get the Servo object.
     * @param name        The name of the Servo object in the HardwareMap.
     * @param minScale    The minimum scale for the servo's range.
     * @param maxScale    The maximum scale for the servo's range.
     * @param reversed    Whether the servo's direction is reversed.
     */
    public PositionServo(HardwareMap hardwareMap, String name, double minScale, double maxScale, boolean reversed) {
        this(hardwareMap.get(Servo.class, name), minScale, maxScale, reversed);
    }

    /**
     * Returns the Servo object that this PositionServo controls.
     *
     * @return The Servo object that this PositionServo controls.
     */
    public Servo getServo() {
        return servo;
    }

    /**
     * Sets the Servo object that this PositionServo will control.
     *
     * @param servo The Servo object to control.
     * @return The current PositionServo instance.
     */
    public PositionServo setServo(Servo servo) {
        this.servo = servo;
        return this;
    }

    /**
     * Sets the Servo object that this PositionServo will control using a HardwareMap and a name.
     *
     * @param hardwareMap The HardwareMap object used to get the Servo object.
     * @param name        The name of the Servo object in the HardwareMap.
     * @return The current PositionServo instance.
     */
    public PositionServo setServo(HardwareMap hardwareMap, String name) {
        this.servo = hardwareMap.get(Servo.class, name);
        return this;
    }

    /**
     * Checks if the servo's direction is reversed.
     *
     * @return True if the servo's direction is reversed, false otherwise.
     */
    public boolean isReversed() {
        return servo.getDirection() == Servo.Direction.REVERSE;
    }

    /**
     * Sets the direction of the servo.
     *
     * @param reversed Whether the servo's direction is reversed.
     * @return The current PositionServo instance.
     */
    public PositionServo setReversed(boolean reversed) {
        servo.setDirection(reversed ? Servo.Direction.REVERSE : Servo.Direction.FORWARD);
        return this;
    }

    /**
     * Returns the scale range of the servo.
     *
     * @return The scale range of the servo.
     */
    public double[] getScaleRange() {
        return new double[]{scaleRange[0], scaleRange[1]};
    }

    /**
     * Sets the scale range of the servo.
     *
     * @param minScale The minimum scale for the servo's range.
     * @param maxScale The maximum scale for the servo's range.
     * @return The current PositionServo instance.
     */
    public PositionServo setScaleRange(double minScale, double maxScale) {
        scaleRange = new double[]{minScale, maxScale};
        servo.scaleRange(minScale, maxScale);
        return this;
    }

    /**
     * Returns the minimum scale of the servo's range.
     *
     * @return The minimum scale of the servo's range.
     */
    public double getScaleMin() {
        return scaleRange[0];
    }

    /**
     * Sets the minimum scale of the servo's range.
     *
     * @param minScale The minimum scale for the servo's range.
     * @return The current PositionServo instance.
     */
    public PositionServo setScaleMin(double minScale) {
        scaleRange[0] = minScale;
        servo.scaleRange(minScale, scaleRange[1]);
        return this;
    }

    /**
     * Returns the maximum scale of the servo's range.
     *
     * @return The maximum scale of the servo's range.
     */
    public double getScaleMax() {
        return scaleRange[1];
    }

    /**
     * Sets the maximum scale of the servo's range.
     *
     * @param maxScale The maximum scale for the servo's range.
     * @return The current PositionServo instance.
     */
    public PositionServo setScaleMax(double maxScale) {
        scaleRange[1] = maxScale;
        servo.scaleRange(scaleRange[0], maxScale);
        return this;
    }

    /**
     * Returns the current position of the servo.
     *
     * @return The current position of the servo.
     */
    public double getPosition() {
        return servo.getPosition();
    }

    /**
     * Sets the position of the servo.
     *
     * @param position The position to set for the servo.
     */
    private void setPosition(double position) {
        servo.setPosition(position);
    }

    /**
     * Returns the target position of the servo.
     *
     * @return The target position of the servo.
     */
    public double getTargetPosition() {
        return targetPosition;
    }

    /**
     * Sets the target position of the servo.
     *
     * @param targetPosition The target position to set for the servo.
     * @return The current PositionServo instance.
     */
    public PositionServo setTargetPosition(double targetPosition) {
        this.targetPosition = targetPosition;
        return this;
    }

    /**
     * Toggles the target position of the servo between 0 and 1.
     *
     * @return The current PositionServo instance.
     */
    public PositionServo toggleTargetPosition() {
        targetPosition = (targetPosition == 1 ? 0 : 1);
        return this;
    }

    /**
     * Toggles the target position of the servo between two given positions.
     *
     * @param positionOne The first position for the servo.
     * @param positionTwo The second position for the servo.
     * @return The current PositionServo instance.
     */
    public PositionServo toggleTargetPosition(double positionOne, double positionTwo) {
        targetPosition = (targetPosition == positionOne ? positionTwo : (targetPosition == positionTwo ? positionOne : targetPosition));
        return this;
    }

    /**
     * Returns the position error of the servo.
     *
     * @return The position error of the servo.
     */
    public double getPositionError() {
        return Math.abs(targetPosition - getPosition());
    }

    /**
     * Returns the CSV header for the servo's data.
     *
     * @return The CSV header as a string.
     */
    public String getCSVHeader() {
        return "CurrentPosition,TargetPosition,PositionError";
    }

    /**
     * Returns the CSV data of the servo's current state.
     *
     * @return The CSV data as a string.
     */
    public String getCSVData() {
        return String.format("%s,%s,%s", getPosition(), getTargetPosition(), getPositionError());
    }

    /**
     * Updates the position of the servo to the target position.
     */
    public void update() {
        setPosition(targetPosition);
    }

    /**
     * Logs the servo's data to telemetry.
     *
     * @param telemetry   The Telemetry object used to log the data.
     * @param hardwareMap The HardwareMap object used to get the name of the servo.
     * @return The current PositionServo instance.
     */
    public PositionServo log(Telemetry telemetry, HardwareMap hardwareMap) {
        telemetry.addData("Servo", hardwareMap.getNamesOf(servo).toArray()[0]);
        telemetry.addData("Current Position", getPosition());
        telemetry.addData("Target Position", getTargetPosition());
        telemetry.addData("Scale Range", Arrays.toString(getScaleRange()));
        telemetry.addData("Reversed", isReversed());
        return this;
    }
}
