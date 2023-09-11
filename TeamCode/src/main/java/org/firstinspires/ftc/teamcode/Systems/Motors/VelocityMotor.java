package org.firstinspires.ftc.teamcode.Systems.Motors;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class VelocityMotor {
    DcMotorEx motor;
    MotorLookupTable motorType;

    double targetVelocity = 0;

    boolean reversed = false;

    public VelocityMotor(DcMotorEx motor, MotorLookupTable motorType, boolean reversed) {
        this.motor = motor;
        this.motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        this.motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        this.motorType = motorType;
        setReversed(reversed);
    }

    public VelocityMotor(HardwareMap hardwareMap, String name, MotorLookupTable motorType, boolean reversed) {
        this(hardwareMap.get(DcMotorEx.class, name), motorType, reversed);
    }

    public DcMotorEx getMotor() {
        return motor;
    }

    public VelocityMotor setMotor(DcMotorEx motor) {
        this.motor = motor;
        return this;
    }

    public VelocityMotor setMotor(HardwareMap hardwareMap, String name) {
        this.motor = hardwareMap.get(DcMotorEx.class, name);
        return this;
    }

    public MotorLookupTable getMotorType() {
        return motorType;
    }

    public VelocityMotor setMotorType(MotorLookupTable motorType) {
        this.motorType = motorType;
        return this;
    }

    public boolean isReversed() {
        return motor.getDirection() == DcMotor.Direction.REVERSE;
    }

    public VelocityMotor setReversed(boolean reversed) {
        motor.setDirection(reversed ? DcMotor.Direction.REVERSE : DcMotor.Direction.FORWARD);
        return this;
    }

    public double getCurrentPosition() {
        return motor.getCurrentPosition();
    }

    public double getPower() {
        return motor.getPower();
    }

    public double getTargetVelocity() {
        return targetVelocity;
    }

    public VelocityMotor setTargetVelocity(double targetVelocity) {
        this.targetVelocity = targetVelocity;
        return this;
    }

    public double getVelocity() {
        return motor.getVelocity();
    }

    public VelocityMotor setVelocity(double velocity) {
        motor.setVelocity(velocity);
        return this;
    }

    public double getVelocityError() {
        return Math.abs(targetVelocity - getVelocity());
    }

    public int getFreeRPM() {
        return motorType.freeRPM;
    }

    public double getRPM() {
        return motorType.RPM;
    }

    public double getTicksPerRotation() {
        return motorType.TPR;
    }

    public double getTicksPerDegree() {
        return motorType.TPD;
    }

    public double getTicksPerSecond() {
        return motorType.TPS;
    }

    public double getGearRatio() {
        return motorType.gearRatio;
    }

    public int getEncoderResolution() {
        return motorType.resolution;
    }

    public PIDFCoefficients getPIDF() {
        return motor.getPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public VelocityMotor setPIDF(PIDFCoefficients pidf) {
        motor.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidf);
        return this;
    }

    public VelocityMotor setPIDF(double p, double i, double d, double f) {
        return setPIDF(new PIDFCoefficients(p, i, d, f));
    }

    public double getP() {
        return getPIDF().p;
    }

    public VelocityMotor setP(double p) {
        return setPIDF(p, getI(), getD(), getF());
    }

    public double getI() {
        return getPIDF().i;
    }

    public VelocityMotor setI(double i) {
        return setPIDF(getP(), i, getD(), getF());
    }

    public double getD() {
        return getPIDF().d;
    }

    public VelocityMotor setD(double d) {
        return setPIDF(getP(), getI(), d, getF());
    }

    public double getF() {
        return getPIDF().f;
    }

    public VelocityMotor setF(double f) {
        return setPIDF(getP(), getI(), getD(), f);
    }

    public void resetEncoder() {
        DcMotor.RunMode currentRunMode = motor.getMode();
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(currentRunMode);
    }

    public String getCSVData() {
        return String.format(",%s,%s,%s", getVelocity(), getTargetVelocity(), getVelocityError());
    }

    public void update() {
        setVelocity(targetVelocity);
    }

    public VelocityMotor log(Telemetry telemetry, HardwareMap hardwareMap) {
        telemetry.addData("Motor", hardwareMap.getNamesOf(motor).toArray()[0]);
        telemetry.addData("Reversed", isReversed());
        telemetry.addData("Target Velocity", getTargetVelocity());
        telemetry.addData("Current Velocity", getVelocity());
        telemetry.addData("PIDF", getPIDF().toString());
        return this;
    }
}
