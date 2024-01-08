package org.firstinspires.ftc.teamcode.Systems.Motors;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Class representing a motor with velocity control and additional functionality.
 */
public class VelocityMotor {
    DcMotorEx motor;

    double targetVelocity = 0;

    boolean reversed = false;

    /**
     * Constructs a new VelocityMotor object with a DcMotorEx.
     *
     * @param motor The DcMotorEx object that this VelocityMotor will control.
     * @param reversed Whether the motor's direction is reversed.
     */
    public VelocityMotor(DcMotorEx motor, boolean reversed) {
        this.motor = motor;
        this.motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        this.motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        setReversed(reversed);
    }

    /**
     * Constructs a new VelocityMotor object with the HardwareMap and device name.
     *
     * @param hardwareMap The HardwareMap object used to get the DcMotorEx object.
     * @param name The name of the DcMotorEx object in the HardwareMap.
     * @param reversed Whether the motor's direction is reversed.
     */
    public VelocityMotor(HardwareMap hardwareMap, String name, boolean reversed) {
        this(hardwareMap.get(DcMotorEx.class, name), reversed);
    }

    /**
     * Returns the DcMotorEx object that this VelocityMotor controls.
     *
     * @return The DcMotorEx object that this VelocityMotor controls.
     */
    public DcMotorEx getMotor() {
        return motor;
    }

    /**
     * Sets the DcMotorEx object that this VelocityMotor will control.
     *
     * @param motor The DcMotorEx object to control.
     * @return The current VelocityMotor instance.
     */
    public VelocityMotor setMotor(DcMotorEx motor) {
        this.motor = motor;
        return this;
    }

    /**
     * Sets the DcMotorEx object that this VelocityMotor will control using the HardwareMap and a device name.
     *
     * @param hardwareMap The HardwareMap object used to get the DcMotorEx object.
     * @param name The name of the DcMotorEx object in the HardwareMap.
     * @return The current VelocityMotor instance.
     */
    public VelocityMotor setMotor(HardwareMap hardwareMap, String name) {
        this.motor = hardwareMap.get(DcMotorEx.class, name);
        return this;
    }

    /**
     * Checks if the motor's direction is reversed.
     *
     * @return True if the motor's direction is reversed, false otherwise.
     */
    public boolean isReversed() {
        return motor.getDirection() == DcMotor.Direction.REVERSE;
    }

    /**
     * Sets the direction of the motor.
     *
     * @param reversed Whether the motor's direction is reversed.
     * @return The current VelocityMotor instance.
     */
    public VelocityMotor setReversed(boolean reversed) {
        motor.setDirection(reversed ? DcMotor.Direction.REVERSE : DcMotor.Direction.FORWARD);
        return this;
    }

    /**
     * Returns the current position of the motor.
     *
     * @return The current position of the motor.
     */
    public double getCurrentPosition() {
        return motor.getCurrentPosition();
    }

    /**
     * Returns the current power of the motor.
     *
     * @return The current power of the motor.
     */
    public double getPower() {
        return motor.getPower();
    }

    /**
     * Returns the current velocity of the motor.
     *
     * @return The current velocity of the motor.
     */
    public double getVelocity() {
        return motor.getVelocity();
    }

    /**
     * Sets the velocity of the motor.
     *
     * @param velocity The velocity to set for the motor.
     */
    private void setVelocity(double velocity) {
        motor.setVelocity(velocity);
    }

    /**
     * Returns the target velocity of the motor.
     *
     * @return The target velocity of the motor.
     */
    public double getTargetVelocity() {
        return targetVelocity;
    }

    /**
     * Sets the target velocity of the motor.
     *
     * @param targetVelocity The target velocity to set for the motor.
     * @return The current VelocityMotor instance.
     */
    public VelocityMotor setTargetVelocity(double targetVelocity) {
        this.targetVelocity = targetVelocity;
        return this;
    }

    /**
     * Returns the velocity error of the motor.
     *
     * @return The velocity error of the motor.
     */
    public double getVelocityError() {
        return Math.abs(targetVelocity - getVelocity());
    }

    /**
     * Returns the PIDF coefficients of the motor.
     *
     * @return The PIDF coefficients of the motor.
     */
    public PIDFCoefficients getPIDF() {
        return motor.getPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    /**
     * Sets the PIDF coefficients of the motor.
     *
     * @param pidf The PIDF coefficients to set for the motor.
     * @return The current VelocityMotor instance.
     */
    public VelocityMotor setPIDF(PIDFCoefficients pidf) {
        motor.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidf);
        return this;
    }

    /**
     * Sets the PIDF coefficients of the motor using individual P, I, D, and F values.
     *
     * @param p The P value to set for the motor's PIDF.
     * @param i The I value to set for the motor's PIDF.
     * @param d The D value to set for the motor's PIDF.
     * @param f The F value to set for the motor's PIDF.
     * @return The current VelocityMotor instance.
     */
    public VelocityMotor setPIDF(double p, double i, double d, double f) {
        return setPIDF(new PIDFCoefficients(p, i, d, f));
    }

    /**
     * Returns the P coefficient of the motor's PIDF controller.
     *
     * @return The P coefficient of the motor's PIDF controller.
     */
    public double getP() {
        return getPIDF().p;
    }

    /**
     * Sets the P coefficient of the motor's PIDF controller.
     *
     * @param p The P coefficient to set for the motor's PIDF controller.
     * @return The current VelocityMotor instance.
     */
    public VelocityMotor setP(double p) {
        return setPIDF(p, getI(), getD(), getF());
    }

    /**
     * Returns the I coefficient of the motor's PIDF controller.
     *
     * @return The I coefficient of the motor's PIDF controller.
     */
    public double getI() {
        return getPIDF().i;
    }

    /**
     * Sets the I coefficient of the motor's PIDF controller.
     *
     * @param i The I coefficient to set for the motor's PIDF controller.
     * @return The current VelocityMotor instance.
     */
    public VelocityMotor setI(double i) {
        return setPIDF(getP(), i, getD(), getF());
    }

    /**
     * Returns the D coefficient of the motor's PIDF controller.
     *
     * @return The D coefficient of the motor's PIDF controller.
     */
    public double getD() {
        return getPIDF().d;
    }

    /**
     * Sets the D coefficient of the motor's PIDF controller.
     *
     * @param d The D coefficient to set for the motor's PIDF controller.
     * @return The current VelocityMotor instance.
     */
    public VelocityMotor setD(double d) {
        return setPIDF(getP(), getI(), d, getF());
    }

    /**
     * Returns the F coefficient of the motor's PIDF controller.
     *
     * @return The F coefficient of the motor's PIDF controller.
     */
    public double getF() {
        return getPIDF().f;
    }

    /**
     * Sets the F coefficient of the motor's PIDF controller.
     *
     * @param f The F coefficient to set for the motor's PIDF controller.
     * @return The current VelocityMotor instance.
     */
    public VelocityMotor setF(double f) {
        return setPIDF(getP(), getI(), getD(), f);
    }

    /**
     * Resets the encoder of the motor.
     */
    public void resetEncoder() {
        DcMotor.RunMode currentRunMode = motor.getMode();
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(currentRunMode);
    }

    /**
     * Returns the CSV header for the motor's data.
     *
     * @return The CSV header as a string.
     */
    public String getCSVHeader() {
        return "Velocity,TargetVelocity,VelocityError";
    }

    /**
     * Returns the CSV data of the motor's current state.
     *
     * @return The CSV data as a string.
     */
    public String getCSVData() {
        return String.format("%s,%s,%s", getVelocity(), getTargetVelocity(), getVelocityError());
    }

    /**
     * Updates the velocity of the motor to the target velocity.
     */
    public void update() {
        setVelocity(targetVelocity);
    }

    /**
     * Logs the motor's data to telemetry.
     *
     * @param telemetry The Telemetry object used to log the data.
     * @param hardwareMap The HardwareMap object used to get the name of the motor.
     * @return The current VelocityMotor instance.
     */
    public VelocityMotor log(Telemetry telemetry, HardwareMap hardwareMap) {
        telemetry.addData("Motor", hardwareMap.getNamesOf(motor).toArray()[0]);
        telemetry.addData("Reversed", isReversed());
        telemetry.addData("Target Velocity", getTargetVelocity());
        telemetry.addData("Current Velocity", getVelocity());
        telemetry.addData("PIDF", getPIDF().toString());
        return this;
    }
}
