package org.firstinspires.ftc.teamcode.Systems;

import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class SinglePowerMotor {
    DcMotorEx motor;
    double targetPower = 0;
    boolean reversed = false;

    /**
     * The mode of operation for a motor.
     */
    public enum Mode {
        ENABLED,
        DISABLED
    }

    SinglePowerMotor.Mode mode = Mode.ENABLED;

    public SinglePowerMotor(DcMotorEx motor) {
        this.motor = motor;
    }

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
     * @return the updated SinglePowerMotor object
     */
    public SinglePowerMotor setMotor(DcMotorEx motor) {
        this.motor = motor;
        return this;
    }

    /**
     * Gets the current mode of operation for the motor.
     *
     * @return the motor's current mode
     */
    public SinglePowerMotor.Mode getMode() {
        return mode;
    }

    /**
     * Sets the mode of operation for the motor.
     *
     * @param mode the mode of operation to set
     * @return the updated SinglePowerMotor object
     */
    public SinglePowerMotor setMode(SinglePowerMotor.Mode mode) {
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
     * @return the updated SinglePowerMotor object
     */
    public SinglePowerMotor setRevered(boolean reversed) {
        this.reversed = reversed;
        return this;
    }

    /**
     * Gets the current power of the motor.
     *
     * @return the current power
     */
    public double getPower() {
        return motor.getPower();
    }

    /**
     * Sets the target power value for the motor.
     *
     * @param power the target power
     * @return the updated SinglePowerMotor object
     */
    public SinglePowerMotor setPower(double power) {
        motor.setPower(power);
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
     * @return the updated SinglePowerMotor object
     */
    public SinglePowerMotor setTargetPower(double power) {
        targetPower = power;
        return this;
    }

    void log(Telemetry telemetry) {
        telemetry.addData("Current power: ", getPower());
    }

    void update() {
        if (mode == Mode.DISABLED) return;
        setPower(targetPower * (reversed ? -1 : 1));
    }
}
