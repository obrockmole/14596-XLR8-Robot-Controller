package org.firstinspires.ftc.teamcode.Systems.Motors;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class representing a group of power and position motors that are controlled together.
 */
public class MotorGroup extends ArrayList<Motor> {
    /**
     * Constructs a new MotorGroup object with a list of Motors.
     *
     * @param motors The motors to be included in the MotorGroup.
     */
    public MotorGroup(@NonNull Motor... motors) {
        super(Arrays.asList(motors));
    }

    /**
     * Returns the number of motors in the MotorGroup.
     *
     * @return The number of motors in the MotorGroup.
     */
    public int getNumMotors() {
        return size();
    }

    /**
     * Returns the list of motors in the MotorGroup.
     *
     * @return The list of motors in the MotorGroup.
     */
    public ArrayList<Motor> getMotors() {
        return this;
    }

    /**
     * Sets the motors in the MotorGroup.
     *
     * @param motors The motors to be set in the MotorGroup.
     * @return The current MotorGroup instance.
     */
    public MotorGroup setMotors(@NonNull Motor... motors) {
        this.clear();
        this.addAll(Arrays.asList(motors));
        return this;
    }

    /**
     * Returns the motor at the specified index in the MotorGroup.
     *
     * @param motor The index of the motor to return.
     * @return The motor at the specified index in the MotorGroup.
     */
    public Motor getMotor(int motor) {
        return get(motor);
    }

    /**
     * Sets the motor at the specified index in the MotorGroup.
     *
     * @param motor The index of the motor to set.
     * @param newMotor The motor to set at the specified index in the MotorGroup.
     * @return The current MotorGroup instance.
     */
    public MotorGroup setMotor(int motor, @NonNull Motor newMotor) {
        set(motor, newMotor);
        return this;
    }

    /**
     * Adds a motor to the MotorGroup.
     *
     * @param newMotor The motor to add to the MotorGroup.
     * @return The current MotorGroup instance.
     */
    public MotorGroup addMotor(@NonNull Motor newMotor) {
        add(newMotor);
        return this;
    }

    /**
     * Returns the type of all motors in the MotorGroup.
     *
     * @return The type of all motors in the MotorGroup.
     */
    public MotorList getMotorType() {
        return get(0).getMotorType();
    }

    /**
     * Sets the type of all motors in the MotorGroup.
     *
     * @param motorType The type to set for all motors in the MotorGroup.
     * @return The current MotorGroup instance.
     */
    public MotorGroup setMotorType(MotorList motorType) {
        for (Motor motor : this)
            motor.setMotorType(motorType);
        return this;
    }

    /**
     * Returns the mode of all motors in the MotorGroup.
     *
     * @return The mode of all motors in the MotorGroup.
     */
    public Motor.Mode getMode() {
        return get(0).getMode();
    }

    /**
     * Sets the mode of all motors in the MotorGroup.
     *
     * @param mode The mode to set for all motors in the MotorGroup.
     * @return The current MotorGroup instance.
     */
    public MotorGroup setMode(Motor.Mode mode) {
        for (Motor motor : this)
            motor.setMode(mode);
        return this;
    }

    /**
     * Checks if the motor at the specified index in the MotorGroup is reversed.
     *
     * @param motor The index of the motor to check.
     * @return True if the motor's direction is reversed, false otherwise.
     */
    public boolean isReversed(int motor) {
        return get(motor).isReversed();
    }

    /**
     * Sets the direction of the motor at the specified index in the MotorGroup.
     *
     * @param motor The index of the motor to set.
     * @param reversed Whether the motor's direction is reversed.
     * @return The current MotorGroup instance.
     */
    public MotorGroup setReversed(int motor, boolean reversed) {
        get(motor).setReversed(reversed);
        return this;
    }

    /**
     * Returns the speed scale of all motors in the MotorGroup.
     *
     * @return The speed scale of all motors in the MotorGroup.
     */
    public double getSpeedScale() {
        return get(0).getSpeedScale();
    }

    /**
     * Sets the speed scale of all motors in the MotorGroup.
     *
     * @param speedScale The speed scale to set for all motors in the MotorGroup.
     * @return The current MotorGroup instance.
     */
    public MotorGroup setSpeedScale(double speedScale) {
        for (Motor motor : this)
            motor.setSpeedScale(speedScale);
        return this;
    }

    /**
     * Returns the tolerance for the position of all motors in the MotorGroup.
     *
     * @return The tolerance for the position of all motors in the MotorGroup.
     */
    public int getTolerance() {
        return get(0).getTolerance();
    }

    /**
     * Sets the tolerance for the position of all motors in the MotorGroup.
     *
     * @param tolerance The tolerance for the position to set for all motors in the MotorGroup.
     * @return The current MotorGroup instance.
     */
    public MotorGroup setTolerance(int tolerance) {
        for (Motor motor : this)
            motor.setTolerance(tolerance);
        return this;
    }

    /**
     * Returns the current power of all motors in the MotorGroup.
     *
     * @return The current power of all motors in the MotorGroup.
     */
    public double getPower() {
        return get(0).getPower();
    }

    /**
     * Returns the target power of all motors in the MotorGroup.
     *
     * @return The target power of all motors in the MotorGroup.
     */
    public double getTargetPower() {
        return get(0).getTargetPower();
    }

    /**
     * Sets the target power of all motors in the MotorGroup.
     *
     * @param power The target power to set for all motors in the MotorGroup.
     * @return The current MotorGroup instance.
     */
    public MotorGroup setTargetPower(double power) {
        for (Motor motor : this)
            motor.setTargetPower(power);
        return this;
    }

    /**
     * Returns the power error of the motor at the specified index in the MotorGroup.
     *
     * @param motor The index of the motor to return the power error.
     * @return The power error of the motor at the specified index in the MotorGroup.
     */
    public double getPowerError(int motor) {
        return get(motor).getPowerError();
    }

    /**
     * Returns the current position of the motor at the specified index in the MotorGroup.
     *
     * @param motor The index of the motor to return the current position.
     * @return The current position of the motor at the specified index in the MotorGroup.
     */
    public int getCurrentPosition(int motor) {
        return get(motor).getCurrentPosition();
    }

    /**
     * Returns the target position of all motors in the MotorGroup.
     *
     * @return The target position of all motors in the MotorGroup.
     */
    public int getTargetPosition() {
        return get(0).getTargetPosition();
    }

    /**
     * Sets the target position of all motors in the MotorGroup.
     *
     * @param position The target position to set for all motors in the MotorGroup.
     * @return The current MotorGroup instance.
     */
    public MotorGroup setTargetPosition(int position) {
        for (Motor motor : this)
            motor.setTargetPosition(position);
        return this;
    }

    /**
     * Checks if the motor at the specified index in the MotorGroup is at its target position.
     *
     * @param motor The index of the motor to check
     * @return True if the motor is at its target position, false otherwise.
     */
    public boolean atTargetPosition(int motor) {
        return get(motor).atTargetPosition();
    }

    /**
     * Returns the position error of the motor at the specified index in the MotorGroup.
     *
     * @param motor The index of the motor to return the position error.
     * @return The position error of the motor at the specified index in the MotorGroup.
     */
    public int getPositionError(int motor) {
        return get(motor).getPositionError();
    }

    /**
     * Returns the PIDF values of all motors in the MotorGroup.
     *
     * @return The PIDF values of all motors in the MotorGroup.
     */
    public ArrayList<Double> getPIDF() {
        return get(0).getPIDF();
    }

    /**
     * Sets the PIDF values of all motors in the MotorGroup.
     *
     * @param p The P value to set for all motors in the MotorGroup.
     * @param i The I value to set for all motors in the MotorGroup.
     * @param d The D value to set for all motors in the MotorGroup.
     * @param f The F value to set for all motors in the MotorGroup.
     * @return The current MotorGroup instance.
     */
    public MotorGroup setPIDF(double p, double i, double d, double f) {
        for (Motor motor : this)
            motor.setPIDF(p, i, d, f);
        return this;
    }

    /**
     * Returns the P value of all motors in the MotorGroup.
     *
     * @return The P value of all motors in the MotorGroup.
     */
    public double getP() {
        return get(0).getP();
    }

    /**
     * Sets the P value of all motors in the MotorGroup.
     *
     * @param p The P value to set for all motors in the MotorGroup.
     * @return The current MotorGroup instance.
     */
    public MotorGroup setP(double p) {
        for (Motor motor : this)
            motor.setP(p);
        return this;
    }

    /**
     * Returns the I value of all motors in the MotorGroup.
     *
     * @return The I value of all motors in the MotorGroup.
     */
    public double getI() {
        return get(0).getI();
    }

    /**
     * Sets the I value of all motors in the MotorGroup.
     *
     * @param i The I value to set for all motors in the MotorGroup.
     * @return The current MotorGroup instance.
     */
    public MotorGroup setI(double i) {
        for (Motor motor : this)
            motor.setI(i);
        return this;
    }

    /**
     * Returns the D value of all motors in the MotorGroup.
     *
     * @return The D value of all motors in the MotorGroup.
     */
    public double getD() {
        return get(0).getD();
    }

    /**
     * Sets the D value of all motors in the MotorGroup.
     *
     * @param d The D value to set for all motors in the MotorGroup.
     * @return The current MotorGroup instance.
     */
    public MotorGroup setD(double d) {
        for (Motor motor : this)
            motor.setD(d);
        return this;
    }

    /**
     * Returns the F value of all motors in the MotorGroup.
     *
     * @return The F value of all motors in the MotorGroup.
     */
    public double getF() {
        return get(0).getF();
    }

    /**
     * Sets the F value of all motors in the MotorGroup.
     *
     * @param f The F value to set for all motors in the MotorGroup.
     * @return The current MotorGroup instance.
     */
    public MotorGroup setF(double f) {
        for (Motor motor : this)
            motor.setF(f);
        return this;
    }


    /**
     * Returns the free RPM of all motors in the MotorGroup.
     *
     * @return The free RPM of all motors in the MotorGroup.
     */
    public int getFreeRPM() {
        return get(0).getFreeRPM();
    }

    /**
     * Returns the RPM of all motors in the MotorGroup.
     *
     * @return The RPM of all motors in the MotorGroup.
     */
    public double getRPM() {
        return get(0).getRPM();
    }

    /**
     * Returns the ticks per rotation of all motors in the MotorGroup.
     *
     * @return The ticks per rotation of all motors in the MotorGroup.
     */
    public double getTicksPerRotation() {
        return get(0).getTicksPerRotation();
    }

    /**
     * Returns the ticks per degree of all motors in the MotorGroup.
     *
     * @return The ticks per degree of all motors in the MotorGroup.
     */
    public double getTicksPerDegree() {
        return get(0).getTicksPerDegree();
    }

    /**
     * Returns the ticks per second of all motors in the MotorGroup.
     *
     * @return The ticks per second of all motors in the MotorGroup.
     */
    public double getTicksPerSecond() {
        return get(0).getTicksPerSecond();
    }

    /**
     * Returns the gear ratio of all motors in the MotorGroup.
     *
     * @return The gear ratio of all motors in the MotorGroup.
     */
    public double getGearRatio() {
        return get(0).getGearRatio();
    }

    /**
     * Returns the encoder resolution of all motors in the MotorGroup.
     *
     * @return The encoder resolution of all motors in the MotorGroup.
     */
    public int getEncoderResolution() {
        return get(0).getEncoderResolution();
    }

    /**
     * Resets the encoder of all motors in the MotorGroup.
     */
    public void resetEncoder() {
        for (Motor motor : this)
            motor.resetEncoder();
    }

    /**
     * Returns the CSV header of all motors in the MotorGroup.
     *
     * @return The CSV header of all motors in the MotorGroup.
     */
    public String getCSVHeader() {
        return get(0).getCSVHeader();
    }

    /**
     * Returns the CSV data of the motor at the specified index in the MotorGroup.
     *
     * @param motor The index of the motor to return the CSV data.
     * @return The CSV data of the motor at the specified index in the MotorGroup.
     */
    public String getCSVData(int motor) {
        return get(motor).getCSVData();
    }

    /**
     * Updates all motors in the MotorGroup based on their mode.
     */
    public void update() {
        for (Motor motor : this)
            motor.update();
    }

    /**
     * Logs the data of all motors in the MotorGroup to telemetry.
     *
     * @param telemetry The Telemetry object used to log the data.
     * @param hardwareMap The HardwareMap object used to get the name of the motors.
     * @return The current MotorGroup instance.
     */
    public MotorGroup log(Telemetry telemetry, HardwareMap hardwareMap) {
        for (Motor motor : this) {
            motor.log(telemetry, hardwareMap);
            telemetry.addLine();
        }

        return this;
    }
}
