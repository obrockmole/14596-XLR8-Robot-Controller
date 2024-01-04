package org.firstinspires.ftc.teamcode.Systems.Servos;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class representing a group of position servos that are controlled together.
 */
public class NewPositionServoGroup extends ArrayList<PositionServo> {
    /**
     * Constructs a new NewPositionServoGroup object with a list of the given servos.
     *
     * @param servos The servos to be included in the NewPositionServoGroup.
     */
    public NewPositionServoGroup(@NonNull PositionServo... servos) {
        super(Arrays.asList(servos));
    }

    /**
     * Returns the servo at the specified index in the NewPositionServoGroup.
     *
     * @param servo The index of the servo to return.
     * @return The servo at the specified index in the NewPositionServoGroup.
     */
    public PositionServo getServo(int servo) {
        return get(servo);
    }

    /**
     * Sets the servo at the specified index in the NewPositionServoGroup.
     *
     * @param servo    The index of the servo to set.
     * @param newServo The servo to set at the specified index in the NewPositionServoGroup.
     * @return The current NewPositionServoGroup instance.
     */
    public NewPositionServoGroup setServo(int servo, @NonNull PositionServo newServo) {
        set(servo, newServo);
        return this;
    }

    /**
     * Adds a servo to the NewPositionServoGroup.
     *
     * @param newServo The servo to add to the NewPositionServoGroup.
     * @return The current NewPositionServoGroup instance.
     */
    public NewPositionServoGroup addServo(@NonNull PositionServo newServo) {
        add(newServo);
        return this;
    }

    /**
     * Checks if the servo at the specified index in the NewPositionServoGroup is reversed.
     *
     * @param servo The index of the servo to check.
     * @return True if the servo's direction is reversed, false otherwise.
     */
    public boolean isReversed(int servo) {
        return get(servo).isReversed();
    }

    /**
     * Sets the direction of the servo at the specified index in the NewPositionServoGroup.
     *
     * @param servo    The index of the servo to set.
     * @param reversed Whether the servo's direction is reversed.
     * @return The current NewPositionServoGroup instance.
     */
    public NewPositionServoGroup setReversed(int servo, boolean reversed) {
        get(servo).setReversed(reversed);
        return this;
    }

    /**
     * Returns the scale range of all servos in the NewPositionServoGroup.
     *
     * @return The scale range of all servos in the NewPositionServoGroup.
     */
    public double[] getScaleRange() {
        return get(0).getScaleRange();
    }

    /**
     * Sets the scale range of all servos in the NewPositionServoGroup.
     *
     * @param minScale The minimum scale for the servos' range.
     * @param maxScale The maximum scale for the servos' range.
     * @return The current NewPositionServoGroup instance.
     */
    public NewPositionServoGroup setScaleRange(double minScale, double maxScale) {
        for (PositionServo servo : this)
            servo.setScaleRange(minScale, maxScale);
        return this;
    }

    /**
     * Returns the current position of all servos in the NewPositionServoGroup.
     *
     * @return The current position of all servos in the NewPositionServoGroup.
     */
    public double getPosition() {
        return get(0).getPosition();
    }

    /**
     * Returns the target position of all servos in the NewPositionServoGroup.
     *
     * @return The target position of all servos in the NewPositionServoGroup.
     */
    public double getTargetPosition() {
        return get(0).getTargetPosition();
    }

    /**
     * Sets the target position of all servos in the NewPositionServoGroup.
     *
     * @param targetPosition The target position to set for all servos in the NewPositionServoGroup.
     * @return The current NewPositionServoGroup instance.
     */
    public NewPositionServoGroup setTargetPosition(double targetPosition) {
        for (PositionServo servo : this)
            servo.setTargetPosition(targetPosition);
        return this;
    }

    /**
     * Toggles the target position of all servos in the NewPositionServoGroup between 0 and 1.
     *
     * @return The current NewPositionServoGroup instance.
     */
    public NewPositionServoGroup toggleTargetPosition() {
        for (PositionServo servo : this)
            servo.toggleTargetPosition();
        return this;
    }

    /**
     * Toggles the target position of all servos in the NewPositionServoGroup between two given positions.
     *
     * @param positionOne The first position for the servos.
     * @param positionTwo The second position for the servos.
     * @return The current NewPositionServoGroup instance.
     */
    public NewPositionServoGroup toggleTargetPosition(double positionOne, double positionTwo) {
        for (PositionServo servo : this)
            servo.toggleTargetPosition(positionOne, positionTwo);
        return this;
    }

    /**
     * Returns the position error of the servo at the specified index in the NewPositionServoGroup.
     *
     * @param servo The index of the servo to return the position error.
     * @return The position error of the servo at the specified index in the NewPositionServoGroup.
     */
    public double getPositionError(int servo) {
        return get(servo).getPositionError();
    }

    /**
     * Returns the CSV header of all servos in the NewPositionServoGroup.
     *
     * @return The CSV header of all servos in the NewPositionServoGroup.
     */
    public String getCSVHeader() {
        return get(0).getCSVHeader();
    }

    /**
     * Returns the CSV data of the servo at the specified index in the NewPositionServoGroup.
     *
     * @param servo The index of the servo to return the CSV data.
     * @return The CSV data of the servo at the specified index in the NewPositionServoGroup.
     */
    public String getCSVData(int servo) {
        return get(servo).getCSVData();
    }

    /**
     * Updates the position of all servos in the NewPositionServoGroup to their target positions.
     */
    public void update() {
        for (PositionServo servo : this)
            servo.update();
    }

    /**
     * Logs the data of all servos in the NewPositionServoGroup to telemetry.
     *
     * @param telemetry The Telemetry object used to log the data.
     * @param hardwareMap The HardwareMap object used to get the name of the servos.
     * @return The current NewPositionServoGroup instance.
     */
    public NewPositionServoGroup log(Telemetry telemetry, HardwareMap hardwareMap) {
        for (PositionServo servo : this) {
            servo.log(telemetry, hardwareMap);
            telemetry.addLine();
        }

        return this;
    }
}
