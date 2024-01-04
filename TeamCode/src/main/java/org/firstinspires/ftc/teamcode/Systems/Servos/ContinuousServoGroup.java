package org.firstinspires.ftc.teamcode.Systems.Servos;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class representing a group of continuous rotation servos that are controlled together.
 */
public class ContinuousServoGroup {
    ArrayList<ContinuousServo> servos;

    /**
     * Constructs a new ContinuousServoGroup object with a list of the given servos.
     *
     * @param servos The servos to be included in the ContinuousServoGroup.
     */
    public ContinuousServoGroup(@NonNull ContinuousServo... servos) {
        this.servos = new ArrayList<>(Arrays.asList(servos));
    }

    /**
     * Returns the list of servos in the ContinuousServoGroup.
     *
     * @return The list of servos in the ContinuousServoGroup.
     */
    public ArrayList<ContinuousServo> getServos() {
        return servos;
    }

    /**
     * Sets the servos in the ContinuousServoGroup.
     *
     * @param servos The servos to be set in the ContinuousServoGroup.
     * @return The current ContinuousServoGroup instance.
     */
    public ContinuousServoGroup setServos(@NonNull ContinuousServo... servos) {
        this.servos.clear();
        this.servos = new ArrayList<>(Arrays.asList(servos));
        return this;
    }

    /**
     * Returns the servo at the specified index in the ContinuousServoGroup.
     *
     * @param servo The index of the servo to return.
     * @return The servo at the specified index in the ContinuousServoGroup.
     */
    public ContinuousServo getServo(int servo) {
        return servos.get(servo);
    }

    /**
     * Sets the servo at the specified index in the ContinuousServoGroup.
     *
     * @param servo The index of the servo to set.
     * @param newServo The servo to set at the specified index in the ContinuousServoGroup.
     * @return The current ContinuousServoGroup instance.
     */
    public ContinuousServoGroup setServo(int servo, @NonNull ContinuousServo newServo) {
        servos.set(servo, newServo);
        return this;
    }

    /**
     * Adds a servo to the ContinuousServoGroup.
     *
     * @param newServo The servo to add to the ContinuousServoGroup.
     * @return The current ContinuousServoGroup instance.
     */
    public ContinuousServoGroup addServo(@NonNull ContinuousServo newServo) {
        servos.add(newServo);
        return this;
    }

    /**
     * Checks if the servo at the specified index in the ContinuousServoGroup is reversed.
     *
     * @param servo The index of the servo to check.
     * @return True if the servo's direction is reversed, false otherwise.
     */
    public boolean isReversed(int servo) {
        return servos.get(servo).isReversed();
    }

    /**
     * Sets the direction of all servos in the ContinuousServoGroup.
     *
     * @param reversed Whether the servos' direction is reversed.
     * @return The current ContinuousServoGroup instance.
     */
    public ContinuousServoGroup setReversed(boolean reversed) {
        for (ContinuousServo servo : servos)
            servo.setReversed(reversed);
        return this;
    }

    /**
     * Returns the current power of the servo at the specified index in the ContinuousServoGroup.
     *
     * @param servo The index of the servo to return the power.
     * @return The current power of the servo at the specified index in the ContinuousServoGroup.
     */
    public double getPower(int servo) {
        return servos.get(servo).getPower();
    }

    /**
     * Returns the target power of the servo at the specified index in the ContinuousServoGroup.
     *
     * @param servo The index of the servo to return the target power.
     * @return The target power of the servo at the specified index in the ContinuousServoGroup.
     */
    public double getTargetPower(int servo) {
        return servos.get(servo).getTargetPower();
    }

    /**
     * Sets the target power of all servos in the ContinuousServoGroup.
     *
     * @param targetPower The target power to set for all servos in the ContinuousServoGroup.
     * @return The current ContinuousServoGroup instance.
     */
    public ContinuousServoGroup setTargetPower(double targetPower) {
        for (ContinuousServo servo : servos)
            servo.setTargetPower(targetPower);
        return this;
    }

    /**
     * Returns the power error of the servo at the specified index in the ContinuousServoGroup.
     *
     * @param servo The index of the servo to return the power error.
     * @return The power error of the servo at the specified index in the ContinuousServoGroup.
     */
    public double getPowerError(int servo) {
        return servos.get(servo).getPowerError();
    }

    /**
     * Returns the CSV header of all servos in the ContinuousServoGroup.
     *
     * @return The CSV header of all servos in the ContinuousServoGroup.
     */
    public String getCSVHeader() {
        return servos.get(0).getCSVHeader();
    }

    /**
     * Returns the CSV data of the servo at the specified index in the ContinuousServoGroup.
     *
     * @param servo The index of the servo to return the CSV data.
     * @return The CSV data of the servo at the specified index in the ContinuousServoGroup.
     */
    public String getCSVData(int servo) {
        return servos.get(servo).getCSVData();
    }

    /**
     * Updates the power of all servos in the ContinuousServoGroup to their target power.
     */
    public void update() {
        for (ContinuousServo servo : servos)
            servo.update();
    }

    /**
     * Logs the data of all servos in the ContinuousServoGroup to telemetry.
     *
     * @param telemetry The Telemetry object used to log the data.
     * @param hardwareMap The HardwareMap object used to get the name of the servos.
     * @return The current ContinuousServoGroup instance.
     */
    public ContinuousServoGroup log(Telemetry telemetry, HardwareMap hardwareMap) {
        for (ContinuousServo servo : servos) {
            servo.log(telemetry, hardwareMap);
            telemetry.addLine();
        }

        return this;
    }
}
