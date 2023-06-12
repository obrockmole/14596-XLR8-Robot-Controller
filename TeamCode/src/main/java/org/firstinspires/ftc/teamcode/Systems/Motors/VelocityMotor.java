package org.firstinspires.ftc.teamcode.Systems.Motors;

import com.arcrobotics.ftclib.controller.PIDController;
import com.arcrobotics.ftclib.controller.wpilibcontroller.SimpleMotorFeedforward;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.ArrayList;
import java.util.Arrays;

public class VelocityMotor {
    DcMotorEx motor;
    MotorLookupTable motorType;

    PIDController velocityController;
    SimpleMotorFeedforward feedforwardController;

    double targetVelocity = 0;
    double acceleration = 0;
    double lastVelocity = 0;
    double lastPosition = 0;
    double lastTimestamp = 0;
    double kP = 0, kI = 0, kD = 0, kS = 0, kV = 0, kA = 0;

    boolean reversed = false;

    public VelocityMotor(DcMotorEx motor, MotorLookupTable motorType, boolean reversed) {
        this.motor = motor;
        this.motorType = motorType;
        setReversed(reversed);

        velocityController = new PIDController(kP, kI, kD);
        feedforwardController = new SimpleMotorFeedforward(kS, kV, kA);
    }

    public VelocityMotor(HardwareMap hardwareMap, String name, MotorLookupTable motorType, boolean reversed) {
        this(hardwareMap.get(DcMotorEx.class, name), motorType, reversed);
    }

    DcMotorEx getMotor() {
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

    public PIDController getVelocityController() {
        return velocityController;
    }

    public VelocityMotor setVelocityController(PIDController velocityController) {
        this.velocityController = velocityController;
        return this;
    }

    public ArrayList<Double> getVelocityControllerCoefficients() {
        return new ArrayList<>(Arrays.asList(kP, kI, kD));
    }

    public VelocityMotor setVelocityControllerCoefficients(double kP, double kI, double kD) {
        this.kP = kP;
        this.kI = kI;
        this.kD = kD;
        velocityController.setPID(kP, kI, kD);
        return this;
    }

    public SimpleMotorFeedforward getFeedforwardController() {
        return feedforwardController;
    }

    public VelocityMotor setFeedforwardController(SimpleMotorFeedforward feedforwardController) {
        this.feedforwardController = feedforwardController;
        return this;
    }

    public ArrayList<Double> getFeedforwardControllerCoefficients() {
        return new ArrayList<>(Arrays.asList(kS, kV, kA));
    }

    public VelocityMotor setFeedforwardControllerCoefficients(double kS, double kV, double kA) {
        this.kS = kS;
        this.kV = kV;
        this.kA = kA;
        feedforwardController = new SimpleMotorFeedforward(kS, kV, kA);
        return this;
    }

    public double getCurrentPosition() {
        return motor.getCurrentPosition();
    }

    public double getPower() {
        return motor.getPower();
    }

    public VelocityMotor setPower(double power) {
        motor.setPower(power);
        return this;
    }

    public double getTargetVelocity() {
        return targetVelocity;
    }

    public VelocityMotor setTargetVelocity(double targetVelocity) {
        this.targetVelocity = targetVelocity;
        return this;
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

    public double getVelocity() {
        return motor.getVelocity();
    }

    public double getCorrectedVelocity() {
        double currentVelocity = getVelocity();
        double currentTime, dt;
        double velocityEstimate = 0;

        if (currentVelocity != lastVelocity) {
            currentTime = (double) System.nanoTime() / 1E9;
            dt = currentTime - lastTimestamp;
            velocityEstimate = (getCurrentPosition() - lastPosition) / dt;
            acceleration = (currentVelocity - lastVelocity) / dt;

            lastVelocity = currentVelocity;
            lastTimestamp = currentTime;
            lastPosition = getCurrentPosition();
        }

        while (Math.abs(velocityEstimate - currentVelocity) > 0x10000 / 2) {
            currentVelocity += Math.signum(velocityEstimate - currentVelocity) * 0x10000;
        }

        return currentVelocity;
    }

    public double getVelocityError() {
        return targetVelocity - getCorrectedVelocity();
    }

    public double getAcceleration() {
        return acceleration;
    }

    public void resetEncoder() {
        DcMotor.RunMode currentRunMode = motor.getMode();
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(currentRunMode);
    }

    public void update() {
        double speed = getTargetVelocity();
        double velocity = velocityController.calculate(getCorrectedVelocity(), speed) + feedforwardController.calculate(speed, getAcceleration());
        setPower(velocity / getTicksPerSecond());
    }

    public VelocityMotor log(Telemetry telemetry, HardwareMap hardwareMap) {
        telemetry.addData("Motor", hardwareMap.getNamesOf(motor));
        telemetry.addData("Reversed", isReversed());
        telemetry.addData("Target Velocity", getTargetVelocity());
        telemetry.addData("Current Velocity", getVelocity());
        telemetry.addData("Current Power", getPower());
        return this;
    }
}
