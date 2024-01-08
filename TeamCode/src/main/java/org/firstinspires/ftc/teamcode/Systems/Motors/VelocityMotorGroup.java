package org.firstinspires.ftc.teamcode.Systems.Motors;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class representing a group of velocity motors that are controlled together.
 */
public class VelocityMotorGroup extends ArrayList<VelocityMotor> {
    /**
     * Constructs a new VelocityMotorGroup object with a list of VelocityMotors.
     *
     * @param motors The motors to be included in the VelocityMotorGroup.
     */
    public VelocityMotorGroup(@NonNull VelocityMotor... motors) {
        super(Arrays.asList(motors));
    }

    /**
     * Returns the list of motors in the VelocityMotorGroup.
     *
     * @return The list of motors in the VelocityMotorGroup.
     */
    ArrayList<VelocityMotor> getMotors() {
        return this;
    }

    /**
     * Sets the motors in the VelocityMotorGroup.
     *
     * @param motors The motors to be set in the VelocityMotorGroup.
     * @return The current VelocityMotorGroup instance.
     */
    public VelocityMotorGroup setMotors(@NonNull VelocityMotor... motors) {
        this.clear();
        this.addAll(Arrays.asList(motors));
        return this;
    }

    /**
     * Returns the motor at the specified index in the VelocityMotorGroup.
     *
     * @param motor The index of the motor to return.
     * @return The motor at the specified index in the VelocityMotorGroup.
     */
    public VelocityMotor getMotor(int motor) {
        return get(motor);
    }

    /**
     * Sets the motor at the specified index in the VelocityMotorGroup.
     *
     * @param motor The index of the motor to set.
     * @param newMotor The motor to set at the specified index in the VelocityMotorGroup.
     * @return The current VelocityMotorGroup instance.
     */
    public VelocityMotorGroup setMotor(int motor, @NonNull VelocityMotor newMotor) {
        set(motor, newMotor);
        return this;
    }

    /**
     * Adds a motor to the VelocityMotorGroup.
     *
     * @param newMotor The motor to add to the VelocityMotorGroup.
     * @return The current VelocityMotorGroup instance.
     */
    public VelocityMotorGroup addMotor(@NonNull VelocityMotor newMotor) {
        add(newMotor);
        return this;
    }

    /**
     * Checks if the motor at the specified index in the VelocityMotorGroup is reversed.
     *
     * @param motor The index of the motor to check.
     * @return True if the motor's direction is reversed, false otherwise.
     */
    public boolean isReversed(int motor) {
        return get(motor).isReversed();
    }

    /**
     * Sets the direction of the motor at the specified index in the VelocityMotorGroup.
     *
     * @param motor The index of the motor to set.
     * @param reversed Whether the motor's direction is reversed.
     * @return The current VelocityMotorGroup instance.
     */
    public VelocityMotorGroup setReversed(int motor, boolean reversed) {
        get(motor).setReversed(reversed);
        return this;
    }

    /**
     * Returns the current position of all motors in the VelocityMotorGroup.
     *
     * @return The current position of all motors in the VelocityMotorGroup.
     */
    public double getCurrentPosition() {
        return get(0).getCurrentPosition();
    }

    /**
     * Returns the current power of all motors in the VelocityMotorGroup.
     *
     * @return The current power of all motors in the VelocityMotorGroup.
     */
    public double getPower() {
        return get(0).getPower();
    }

    /**
     * Returns the current velocity of all motors in the VelocityMotorGroup.
     *
     * @return The current velocity of all motors in the VelocityMotorGroup.
     */
    public double getVelocity() {
        return get(0).getVelocity();
    }

    /**
     * Returns the target velocity of all motors in the VelocityMotorGroup.
     *
     * @return The target velocity of all motors in the VelocityMotorGroup.
     */
    public double getTargetVelocity() {
        return get(0).getTargetVelocity();
    }

    /**
     * Sets the target velocity of all motors in the VelocityMotorGroup.
     *
     * @param targetVelocity The target velocity to set for all motors in the VelocityMotorGroup.
     * @return The current VelocityMotorGroup instance.
     */
    public VelocityMotorGroup setTargetVelocity(double targetVelocity) {
        for (VelocityMotor motor : this)
            motor.setTargetVelocity(targetVelocity);
        return this;
    }

    /**
     * Returns the velocity of the motor at the specified index in the VelocityMotorGroup.
     *
     * @param motor The index of the motor to return the velocity.
     * @return The velocity of the motor at the specified index in the VelocityMotorGroup.
     */
    public double getVelocity(int motor) {
        return get(motor).getVelocity();
    }

    /**
     * Returns the velocity error of the motor at the specified index in the VelocityMotorGroup.
     *
     * @param motor The index of the motor to return the velocity error.
     * @return The velocity error of the motor at the specified index in the VelocityMotorGroup.
     */
    public double getVelocityError(int motor) {
        return get(motor).getVelocityError();
    }

    /**
     * Returns the PIDF coefficients of all motors in the VelocityMotorGroup.
     *
     * @return The PIDF coefficients of all motors in the VelocityMotorGroup.
     */
    public PIDFCoefficients getPIDF() {
        return get(0).getPIDF();
    }

    /**
     * Sets the PIDF coefficients of all motors in the VelocityMotorGroup.
     *
     * @param pidf The PIDF coefficients to set for all motors in the VelocityMotorGroup.
     * @return The current VelocityMotorGroup instance.
     */
    public VelocityMotorGroup setPIDF(PIDFCoefficients pidf) {
        for (VelocityMotor motor : this)
            motor.setPIDF(pidf);
        return this;
    }

    /**
     * Sets the PIDF coefficients of all motors in the VelocityMotorGroup using individual P, I, D, and F values.
     *
     * @param p The P value to set for all motors in the VelocityMotorGroup.
     * @param i The I value to set for all motors in the VelocityMotorGroup.
     * @param d The D value to set for all motors in the VelocityMotorGroup.
     * @param f The F value to set for all motors in the VelocityMotorGroup.
     * @return The current VelocityMotorGroup instance.
     */
    public VelocityMotorGroup setPIDF(double p, double i, double d, double f) {
        return setPIDF(new PIDFCoefficients(p, i, d, f));
    }

    /**
     * Returns the P coefficient of all motors in the VelocityMotorGroup.
     *
     * @return The P coefficient of all motors in the VelocityMotorGroup.
     */
    public double getP() {
        return get(0).getP();
    }

    /**
     * Sets the P coefficient of all motors in the VelocityMotorGroup.
     *
     * @param p The P coefficient to set for all motors in the VelocityMotorGroup.
     * @return The current VelocityMotorGroup instance.
     */
    public VelocityMotorGroup setP(double p) {
        return setPIDF(p, getI(), getD(), getF());
    }

    /**
     * Returns the I coefficient of all motors in the VelocityMotorGroup.
     *
     * @return The I coefficient of all motors in the VelocityMotorGroup.
     */
    public double getI() {
        return get(0).getI();
    }

    /**
     * Sets the I coefficient of all motors in the VelocityMotorGroup.
     *
     * @param i The I coefficient to set for all motors in the VelocityMotorGroup.
     * @return The current VelocityMotorGroup instance.
     */
    public VelocityMotorGroup setI(double i) {
        return setPIDF(getP(), i, getD(), getF());
    }

    /**
     * Returns the D coefficient of all motors in the VelocityMotorGroup.
     *
     * @return The D coefficient of all motors in the VelocityMotorGroup.
     */
    public double getD() {
        return get(0).getD();
    }

    /**
     * Sets the D coefficient of all motors in the VelocityMotorGroup.
     *
     * @param d The D coefficient to set for all motors in the VelocityMotorGroup.
     * @return The current VelocityMotorGroup instance.
     */
    public VelocityMotorGroup setD(double d) {
        return setPIDF(getP(), getI(), d, getF());
    }

    /**
     * Returns the F coefficient of all motors in the VelocityMotorGroup.
     *
     * @return The F coefficient of all motors in the VelocityMotorGroup.
     */
    public double getF() {
        return get(0).getF();
    }

    /**
     * Sets the F coefficient of all motors in the VelocityMotorGroup.
     *
     * @param f The F coefficient to set for all motors in the VelocityMotorGroup.
     * @return The current VelocityMotorGroup instance.
     */
    public VelocityMotorGroup setF(double f) {
        return setPIDF(getP(), getI(), getD(), f);
    }

    /**
     * Resets the encoder of all motors in the VelocityMotorGroup.
     */
    public void resetEncoder() {
        for (VelocityMotor motor : this)
            motor.resetEncoder();
    }

    /**
     * Returns the CSV header of all motors in the VelocityMotorGroup.
     *
     * @return The CSV header of all motors in the VelocityMotorGroup.
     */
    public String getCSVHeader() {
        return get(0).getCSVHeader();
    }

    /**
     * Returns the CSV data of the motor at the specified index in the VelocityMotorGroup.
     *
     * @param motor The index of the motor to return the CSV data.
     * @return The CSV data of the motor at the specified index in the VelocityMotorGroup.
     */
    public String getCSVData(int motor) {
        return get(motor).getCSVData();
    }

    /**
     * Updates the velocity of all motors in the VelocityMotorGroup to their target velocities.
     */
    public void update() {
        for (VelocityMotor motor : this)
            motor.update();
    }

    /**
     * Logs the data of all motors in the VelocityMotorGroup to telemetry.
     *
     * @param telemetry The Telemetry object used to log the data.
     * @param hardwareMap The HardwareMap object used to get the name of the motors.
     * @return The current VelocityMotorGroup instance.
     */
    public VelocityMotorGroup log(Telemetry telemetry, HardwareMap hardwareMap) {
        for (VelocityMotor motor : this) {
            motor.log(telemetry, hardwareMap);
            telemetry.addLine();
        }

        return this;
    }
}
