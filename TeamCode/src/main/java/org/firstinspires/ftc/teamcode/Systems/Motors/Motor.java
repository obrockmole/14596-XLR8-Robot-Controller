package org.firstinspires.ftc.teamcode.Systems.Motors;

import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class representing a power and position dc motor with additional functionality.
 */
public class Motor {
    DcMotorEx motor;
    MotorList motorType;

    double targetPower = 0;
    double speedScale = 1;

    PIDController pidController;
    int targetPosition = 0;
    int tolerance;
    double p, i, d, f;

    public enum Mode {
        POWER,
        POSITION,
        DISABLED
    }
    Mode mode;

    /**
     * Constructs a new Motor object with with a DcMotorEx.
     *
     * @param motor The DcMotorEx object that this Motor will control.
     * @param motorType The type of the motor.
     * @param mode The mode of the motor.
     * @param tolerance The tolerance for the motor's position.
     * @param reversed Whether the motor's direction is reversed.
     */
    public Motor(DcMotorEx motor, MotorList motorType, Mode mode, int tolerance, boolean reversed) {
        this.motor = motor;
        this.motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        resetEncoder();
        this.mode = mode;
        this.motorType = motorType;
        this.tolerance = tolerance;
        this.pidController = new PIDController(0, 0, 0);
        setReversed(reversed);
    }

    /**
     * Constructs a new Motor object with the HardwareMap and device name.
     *
     * @param hardwareMap The HardwareMap object used to get the DcMotorEx object.
     * @param name The name of the DcMotorEx object in the HardwareMap.
     * @param motorType The type of the motor.
     * @param mode The mode of the motor.
     * @param tolerance The tolerance for the motor's position.
     * @param reversed Whether the motor's direction is reversed.
     */
    public Motor(HardwareMap hardwareMap, String name, MotorList motorType, Mode mode, int tolerance, boolean reversed) {
        this(hardwareMap.get(DcMotorEx.class, name), motorType, mode, tolerance, reversed);
    }

    /**
     * Constructs a new Motor object with a DcMotorEx and a default tolerance of 10.
     *
     * @param motor The DcMotorEx object that this Motor will control.
     * @param motorType The type of the motor.
     * @param mode The mode of the motor.
     * @param reversed Whether the motor's direction is reversed.
     */
    public Motor(DcMotorEx motor, MotorList motorType, Mode mode, boolean reversed) {
        this(motor, motorType, mode, 10, reversed);
    }

    /**
     * Constructs a new Motor object with the HardwareMap, device name, and a default tolerance of 10.
     *
     * @param hardwareMap The HardwareMap object used to get the DcMotorEx object.
     * @param name The name of the DcMotorEx object in the HardwareMap.
     * @param motorType The type of the motor.
     * @param mode The mode of the motor.
     * @param reversed Whether the motor's direction is reversed.
     */
    public Motor(HardwareMap hardwareMap, String name, MotorList motorType, Mode mode, boolean reversed) {
        this(hardwareMap.get(DcMotorEx.class, name), motorType, mode, 10, reversed);
    }

    /**
     * Returns the DcMotorEx object that this Motor controls.
     *
     * @return The DcMotorEx object that this Motor controls.
     */
    public DcMotorEx getMotor() {
        return motor;
    }

    /**
     * Sets the DcMotorEx object that this Motor will control.
     *
     * @param motor The DcMotorEx object to control.
     * @return The current Motor instance.
     */
    public Motor setMotor(DcMotorEx motor) {
        this.motor = motor;
        return this;
    }

    /**
     * Sets the DcMotorEx object that this Motor will control using the HardwareMap and a device name.
     *
     * @param hardwareMap The HardwareMap object used to get the DcMotorEx object.
     * @param name The name of the DcMotorEx object in the HardwareMap.
     * @return The current Motor instance.
     */
    public Motor setMotor(HardwareMap hardwareMap, String name) {
        this.motor = hardwareMap.get(DcMotorEx.class, name);
        return this;
    }

    /**
     * Returns the type of the motor.
     *
     * @return The type of the motor.
     */
    public MotorList getMotorType() {
        return motorType;
    }

    /**
     * Sets the type of the motor.
     *
     * @param motorType The type of the motor.
     * @return The current Motor instance.
     */
    public Motor setMotorType(MotorList motorType) {
        this.motorType = motorType;
        return this;
    }

    /**
     * Returns the mode of the motor.
     *
     * @return The mode of the motor.
     */
    public Mode getMode() {
        return mode;
    }

    /**
     * Sets the mode of the motor.
     *
     * @param mode The mode of the motor.
     * @return The current Motor instance.
     */
    public Motor setMode(Mode mode) {
        this.mode = mode;
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
     * @return The current Motor instance.
     */
    public Motor setReversed(boolean reversed) {
        motor.setDirection(reversed ? DcMotor.Direction.REVERSE : DcMotor.Direction.FORWARD);
        return this;
    }

    /**
     * Returns the speed scale of the motor.
     *
     * @return The speed scale of the motor.
     */
    public double getSpeedScale() {
        return speedScale;
    }

    /**
     * Sets the speed scale of the motor.
     *
     * @param speedScale The speed scale of the motor.
     * @return The current Motor instance.
     */
    public Motor setSpeedScale(double speedScale) {
        this.speedScale = speedScale;
        return this;
    }

    /**
     * Returns the tolerance for the motor's position.
     *
     * @return The tolerance for the motor's position.
     */
    public int getTolerance() {
        return tolerance;
    }

    /**
     * Sets the tolerance for the motor's position.
     *
     * @param tolerance The tolerance for the motor's position.
     * @return The current Motor instance.
     */
    public Motor setTolerance(int tolerance) {
        this.tolerance = tolerance;
        return this;
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
     * Sets the direct power of the DcMotorEx.
     *
     * @param power The power of the motor.
     */
    private void setPower(double power) {
        motor.setPower(power * speedScale);
    }

    /**
     * Returns the target power of the motor.
     *
     * @return The target power of the motor.
     */
    public double getTargetPower() {
        return targetPower;
    }

    /**
     * Sets the target power of the motor.
     *
     * @param power The target power of the motor.
     * @return The current Motor instance.
     */
    public Motor setTargetPower(double power) {
        targetPower = power;
        return this;
    }

    /**
     * Returns the power error of the motor.
     *
     * @return The power error of the motor.
     */
    public double getPowerError() {
        return Math.abs(targetPower - getPower());
    }

    /**
     * Returns the current position of the motor.
     *
     * @return The current position of the motor.
     */
    public int getCurrentPosition() {
        return motor.getCurrentPosition();
    }

    /**
     * Returns the target position of the motor.
     *
     * @return The target position of the motor.
     */
    public int getTargetPosition() {
        return targetPosition;
    }

    /**
     * Sets the target position of the motor.
     *
     * @param position The target position of the motor.
     * @return The current Motor instance.
     */
    public Motor setTargetPosition(int position) {
        targetPosition = position;
        return this;
    }

    /**
     * Checks if the motor is at its target position.
     *
     * @return True if the motor is at its target position, false otherwise.
     */
    public boolean atTargetPosition() {
        return Math.abs(getCurrentPosition() - getTargetPosition()) < tolerance;
    }

    /**
     * Returns the position error of the motor.
     *
     * @return The position error of the motor.
     */
    public int getPositionError() {
        return Math.abs(targetPosition - getCurrentPosition());
    }

    /**
     * Returns the PID controller of the motor.
     *
     * @return The PID controller of the motor.
     */
    public PIDController getPidController() {
        return pidController;
    }

    /**
     * Sets the PID controller of the motor.
     *
     * @param pidController The PID controller of the motor.
     * @return The current Motor instance.
     */
    public Motor setPidController(PIDController pidController) {
        this.pidController = pidController;
        return this;
    }

    /**
     * Returns the PIDF values of the motor.
     *
     * @return The PIDF values of the motor.
     */
    public ArrayList<Double> getPIDF() {
        return new ArrayList<>(Arrays.asList(p, i, d, f));
    }

    /**
     * Sets the PIDF values of the motor.
     *
     * @param p The P value of the motor's PIDF.
     * @param i The I value of the motor's PIDF.
     * @param d The D value of the motor's PIDF.
     * @param f The F value of the motor's PIDF.
     * @return The current Motor instance.
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
     * Returns the P value of the motor's PIDF.
     *
     * @return The P value of the motor's PIDF.
     */
    public double getP() {
        return p;
    }

    /**
     * Sets the P value of the motor's PIDF.
     *
     * @param p The P value of the motor's PIDF.
     * @return The current Motor instance.
     */
    public Motor setP(double p) {
        this.p = p;
        return this;
    }

    /**
     * Returns the I value of the motor's PIDF.
     *
     * @return The I value of the motor's PIDF.
     */
    public double getI() {
        return i;
    }

    /**
     * Sets the I value of the motor's PIDF.
     *
     * @param i The I value of the motor's PIDF.
     * @return The current Motor instance.
     */
    public Motor setI(double i) {
        this.i = i;
        return this;
    }

    /**
     * Returns the D value of the motor's PIDF.
     *
     * @return The D value of the motor's PIDF.
     */
    public double getD() {
        return d;
    }

    /**
     * Sets the D value of the motor's PIDF.
     *
     * @param d The D value of the motor's PIDF.
     * @return The current Motor instance.
     */
    public Motor setD(double d) {
        this.d = d;
        return this;
    }

    /**
     * Returns the F value of the motor's PIDF.
     *
     * @return The F value of the motor's PIDF.
     */
    public double getF() {
        return f;
    }

    /**
     * Sets the F value of the motor's PIDF.
     *
     * @param f The F value of the motor's PIDF.
     * @return The current Motor instance.
     */
    public Motor setF(double f) {
        this.f = f;
        return this;
    }

    /**
     * Returns the free RPM of the motor.
     *
     * @return The free RPM of the motor.
     */
    public int getFreeRPM() {
        return motorType.freeRPM;
    }

    /**
     * Returns the RPM of the motor.
     *
     * @return The RPM of the motor.
     */
    public double getRPM() {
        return motorType.RPM;
    }

    /**
     * Returns the ticks per rotation of the motor.
     *
     * @return The ticks per rotation of the motor.
     */
    public double getTicksPerRotation() {
        return motorType.TPR;
    }

    /**
     * Returns the ticks per degree of the motor.
     *
     * @return The ticks per degree of the motor.
     */
    public double getTicksPerDegree() {
        return motorType.TPD;
    }

    /**
     * Returns the ticks per second of the motor.
     *
     * @return The ticks per second of the motor.
     */
    public double getTicksPerSecond() {
        return motorType.TPS;
    }

    /**
     * Returns the gear ratio of the motor.
     *
     * @return The gear ratio of the motor.
     */
    public double getGearRatio() {
        return motorType.gearRatio;
    }

    /**
     * Returns the encoder resolution of the motor.
     *
     * @return The encoder resolution of the motor.
     */
    public int getEncoderResolution() {
        return motorType.resolution;
    }

    /**
     * Calculates the PIDF value for the motor.
     *
     * @return The calculated PIDF value.
     */
    public double calculatePIDF() {
        double pid = pidController.calculate(getCurrentPosition(), getTargetPosition());
        double ff = Math.cos(Math.toRadians((getTargetPosition()) / getTicksPerDegree())) * f;

        return pid + ff;
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
     * Returns the CSV header of the motor.
     *
     * @return The CSV header of the motor as a string.
     */
    public String getCSVHeader() {
        return "Power,TargetPower,PowerError,Position,TargetPosition,PositionError";
    }

    /**
     * Returns the CSV data of the motor.
     *
     * @return The CSV data of the motor as a string.
     */
    public String getCSVData() {
        return String.format("%s,%s,%s,%s,%s,%s", getPower(), getTargetPower(), getPowerError(), getCurrentPosition(), getTargetPosition(), getPositionError());
    }

    /**
     * Updates the power of the motor based on its mode.
     */
    public void update() {
        double power;

        switch (mode) {
            case POWER:
                power = targetPower;
                break;

            case POSITION:
                if (Math.abs(getCurrentPosition() - getTargetPosition()) < tolerance)
                    power = getPower();
                else
                    power = calculatePIDF();
                break;

            default:
                power = 0;
                break;
        }

        setPower(power);
    }

    /**
     * Logs the motor's data to telemetry.
     *
     * @param telemetry The Telemetry object used to log the data.
     * @param hardwareMap The HardwareMap object used to get the name of the motor.
     * @return The current Motor instance.
     */
    public Motor log(Telemetry telemetry, HardwareMap hardwareMap) {
        telemetry.addData("Motor", hardwareMap.getNamesOf(motor).toArray()[0]);
        telemetry.addData("Mode", mode);
        telemetry.addData("Reversed", isReversed());
        telemetry.addData("Current Power", getPower());
        if (mode == Mode.POWER) {
            telemetry.addData("Target Power", getTargetPower());
        } else if (mode == Mode.POSITION) {
            telemetry.addData("Current Position", getCurrentPosition());
            telemetry.addData("Target Position", getTargetPosition());
            telemetry.addData("PIDF", getPIDF().toString());
        }
        return this;
    }
}
