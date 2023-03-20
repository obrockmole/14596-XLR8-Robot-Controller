package org.firstinspires.ftc.teamcode.Systems;

import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * The Motor class represents a motor component that can be used to in a variety of different ways to control the robot.
 * It can operate in two modes, power or position mode.
 * In power mode, the motor's power can be set based on an index of a pre-determined list of powers.
 *
 * In position mode, the motor can be moved to a specific encoder position based on an index of a pre-determined list of positions through a PIDF function.
 */
public class Motor {
    DcMotorEx motor;
    boolean reversed = false;

    double targetPower = 0;
    double speedScale = 1;

    PIDController pidController;
    int targetPosition = 0;
    double p, i, d, f, ticksPerDegree;

    /**
     * The mode of operation for a motor.
     */
    public enum Mode {
        POWER,
        POSITION,
        DISABLED
    }
    Mode mode = Mode.POWER;

    /**
     * Gets the controlled DcMotorEx motor.
     *
     * @return the controlled motor
     */
    public DcMotorEx getMotor() {
        return motor;
    }

    /**
     * Sets the controlled DcMotorEx motor.
     *
     * @param motor the DcMotorEx object that represents the motor
     * @return the updated Motor object
     */
    public Motor setMotor(DcMotorEx motor) {
        this.motor = motor;
        return this;
    }

    /**
     * Gets the current mode of operation for the motor.
     *
     * @return the motor's current mode
     */
    public Mode getMode() {
        return mode;
    }

    /**
     * Sets the mode of operation for the motor.
     *
     * @param mode the mode of operation to set
     * @return the updated Motor object
     */
    public Motor setMode(Mode mode) {
        this.mode = mode;
        return this;
    }

    /**
     * Gets the reverse state of the motor.
     *
     * @return if the motor is reversed or not
     */
    public boolean isReversed() {
        return reversed;
    }

    /**
     * Sets whether the motor should be reversed or not.
     *
     * @param reversed the new reverse state of the motor
     * @return the updated Motor object
     */
    public Motor setRevered(boolean reversed) {
        this.reversed = reversed;
        return this;
    }

    /**
     * Gets the current speed scale for the motor.
     *
     * @return the current speed scale
     */
    public double getSpeedScale() {
        return speedScale;
    }

    /**
     * Sets the current speed scale for the motor.
     *
     * @param speedScale the new speed scale
     * @return the updated Motor object
     */
    public Motor setSpeedScale(double speedScale) {
        this.speedScale = speedScale;
        return this;
    }

    /**
     * Constructor for a Motor object.
     *
     * @param motor the DcMotorEx object to be controlled
     * @param mode the motor's mode
     * @param ticksPerDegree the number of encoder ticks per degree
     * @param reversed is the motor reversed
     */
    public Motor(DcMotorEx motor, Mode mode, double ticksPerDegree, boolean reversed) {
        this.motor = motor;
        this.mode = mode;
        this.ticksPerDegree = ticksPerDegree;
        this.reversed = reversed;
    }

    /**
     * Constructor for a Motor object with hardwareMap.
     *
     * @param hardwareMap the HardwareMap object to be used to retrieve the motor
     * @param name the name of the motor in the configuration
     * @param mode the motor's mode
     * @param ticksPerDegree the number of encoder ticks per degree
     * @param reversed is the motor reversed
     */
    public Motor(HardwareMap hardwareMap, String name, Mode mode, double ticksPerDegree, boolean reversed) {
        this.motor = hardwareMap.get(DcMotorEx.class, name);
        this.mode = mode;
        this.ticksPerDegree = ticksPerDegree;
        this.reversed = reversed;
    }

    /**
     * Gets the current power applied to the motor.
     *
     * @return the motor's current power
     */
    public double getPower() {
        return motor.getPower();
    }

    /**
     * Sets the power level of the motor.
     *
     * @param power the new power level
     * @return the updated Motor object
     */
    public Motor setPower(double power) {
        motor.setPower(power * speedScale);
        return this;
    }

    /**
     * Gets the target power for the motor.
     *
     * @return the target power
     */
    public double getTargetPower() {
        return targetPower;
    }

    /**
     * Sets the target power level for the motor.
     *
     * @param power the target power level to set
     * @return the updated Motor object
     */
    public Motor setTargetPower(double power) {
        targetPower = power;
        return this;
    }

    /**
     * Gets the current position of the motor.
     *
     * @return the motor's current position
     */
    public int getCurrentPosition() {
        return motor.getCurrentPosition();
    }

    /**
     * Gets the target position for the motor.
     *
     * @return the target position
     */
    public int getTargetPosition() {
        return targetPosition;
    }

    /**
     * Sets the target position level for the motor.
     *
     * @param position the target position level to set
     * @return the updated Motor object
     */
    public Motor setTargetPosition(int position) {
        targetPosition = position;
        return this;
    }

    /**
     * Get the current p, i, d, and f values that control the PIDF function for the motor.
     *
     * @return list containing the p, i, d, and f values
     */
    public ArrayList<Double> getPIDF() {
        return new ArrayList<>(Arrays.asList(p, i, d, f));
    }

    /**
     * Set the PIDF function's p, i, d, and f values.
     *
     * @param p the new p value
     * @param i the new i value
     * @param d the new d value
     * @param f the new f value
     * @return the updated Motor object
     */
    public Motor setPIDF(double p, double i, double d, double f) {
        this.p = p;
        this.i = i;
        this.d = d;
        this.f = f;
        pidController.setPID(p, i, d);
        return this;
    }

    /**
     * Gets the current PIDF p value.
     *
     * @return the current p value
     */
    public double getP() {
        return p;
    }

    /**
     * Set the p value for the PIDF.
     *
     * @param p the new p value
     * @return the updated Motor object
     */
    public Motor setP(double p) {
        this.p = p;
        return this;
    }

    /**
     * Gets the current PIDF i value.
     *
     * @return the current i value
     */
    public double getI() {
        return i;
    }

    /**
     * Set the i value for the PIDF.
     *
     * @param i the new i value
     * @return the updated Motor object
     */
    public Motor setI(double i) {
        this.i = i;
        return this;
    }

    /**
     * Gets the current PIDF d value.
     *
     * @return the current d value
     */
    public double getD() {
        return d;
    }

    /**
     * Set the d value for the PIDF.
     *
     * @param d the new d value
     * @return the updated Motor object
     */
    public Motor setD(double d) {
        this.d = d;
        return this;
    }

    /**
     * Gets the current PIDF f value.
     *
     * @return the current f value
     */
    public double getF() {
        return f;
    }

    /**
     * Set the f value for the PIDF.
     *
     * @param f the new f value
     * @return the updated Motor object
     */
    public Motor setF(double f) {
        this.f = f;
        return this;
    }

    /**
     * Get the current encoder ticks per degree value for the motor.
     *
     * @return the ticks per degree
     */
    public double getTicksPerDegree() {
        return ticksPerDegree;
    }

    /**
     * Set the encoder ticks per degree for the motor
     *
     * @param ticksPerDegree the new ticks per degree
     * @return the updated Motor object
     */
    public Motor setTicksPerDegree(double ticksPerDegree) {
        this.ticksPerDegree = ticksPerDegree;
        return this;
    }

    /**
     * Calculates the desired motor power through a PIDF function using the current position and target position.
     *
     * @return the desired power
     */
    public double calculatePIDF() {
        double pid = pidController.calculate(getCurrentPosition(), getTargetPosition() * (reversed ? -1 : 1));
        double ff = Math.cos(Math.toRadians((getTargetPosition() * (reversed ? -1 : 1)) / getTicksPerDegree())) * f;

        return pid + ff;
    }

    /**
     * Stop the motor and reset the encoder back to zero.
     */
    public void resetEncoder() {
        DcMotor.RunMode currentRunMode = motor.getMode();
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(currentRunMode);
    }

    /**
     * Update the motor and sets its power based on its current mode and travel direction.
     */
    public void update() {
        double power;

        switch (mode) {
            case POWER:
                power = targetPower * (reversed ? -1 : 1);
                break;

            case POSITION:
                power = calculatePIDF();
                break;

            default:
                power = 0;
                break;
        }

        setPower(power);
    }
}
