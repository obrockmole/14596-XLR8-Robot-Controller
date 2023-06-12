package org.firstinspires.ftc.teamcode.Systems.Motors;

import androidx.annotation.NonNull;

import com.arcrobotics.ftclib.controller.PIDController;
import com.arcrobotics.ftclib.controller.wpilibcontroller.SimpleMotorFeedforward;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.ArrayList;
import java.util.Arrays;

public class VelocityMotorGroup {
    ArrayList<VelocityMotor> motors;

    public VelocityMotorGroup(@NonNull VelocityMotor... motors) {
        this.motors = new ArrayList<>(Arrays.asList(motors));
    }

    ArrayList<VelocityMotor> getMotors() {
        return motors;
    }

    public VelocityMotorGroup setMotors(@NonNull VelocityMotor... motors) {
        this.motors = new ArrayList<>(Arrays.asList(motors));
        return this;
    }

    public VelocityMotor getMotor(int motor) {
        return motors.get(motor);
    }

    public VelocityMotorGroup setMotor(int motor, @NonNull VelocityMotor newMotor) {
        motors.set(motor, newMotor);
        return this;
    }

    public MotorLookupTable getMotorType() {
        return motors.get(0).getMotorType();
    }

    public VelocityMotorGroup setMotorType(MotorLookupTable motorType) {
        for (VelocityMotor motor : motors) motor.setMotorType(motorType);
        return this;
    }

    public boolean isReversed(int motor) {
        return motors.get(motor).isReversed();
    }

    public VelocityMotorGroup setReversed(int motor, boolean reversed) {
        motors.get(motor).setReversed(reversed);
        return this;
    }

    public PIDController getVelocityController() {
        return motors.get(0).getVelocityController();
    }

    public VelocityMotorGroup setVelocityController(PIDController velocityController) {
        for (VelocityMotor motor : motors) motor.setVelocityController(velocityController);
        return this;
    }

    public ArrayList<Double> getVelocityControllerCoefficients() {
        return motors.get(0).getVelocityControllerCoefficients();
    }

    public VelocityMotorGroup setVelocityControllerCoefficients(double kP, double kI, double kD) {
        for (VelocityMotor motor : motors) motor.setVelocityControllerCoefficients(kP, kI, kD);
        return this;
    }

    public SimpleMotorFeedforward getFeedforwardController() {
        return motors.get(0).getFeedforwardController();
    }

    public VelocityMotorGroup setFeedforwardController(SimpleMotorFeedforward feedforwardController) {
        for (VelocityMotor motor : motors) motor.setFeedforwardController(feedforwardController);
        return this;
    }

    public ArrayList<Double> getFeedforwardControllerCoefficients() {
        return motors.get(0).getFeedforwardControllerCoefficients();
    }

    public VelocityMotorGroup setFeedforwardControllerCoefficients(double kS, double kV, double kA) {
        for (VelocityMotor motor : motors) motor.setFeedforwardControllerCoefficients(kS, kV, kA);
        return this;
    }

    public double getCurrentPosition() {
        return motors.get(0).getCurrentPosition();
    }

    public double getPower() {
        return motors.get(0).getPower();
    }

    public VelocityMotorGroup setPower(double power) {
        for (VelocityMotor motor : motors) motor.setPower(power);
        return this;
    }

    public double getTargetVelocity() {
        return motors.get(0).getTargetVelocity();
    }

    public VelocityMotorGroup setTargetVelocity(double targetVelocity) {
        for (VelocityMotor motor : motors) motor.setTargetVelocity(targetVelocity);
        return this;
    }

    public int getFreeRPM() {
        return motors.get(0).getFreeRPM();
    }

    public double getRPM() {
        return motors.get(0).getRPM();
    }

    public double getTicksPerRotation() {
        return motors.get(0).getTicksPerRotation();
    }

    public double getTicksPerDegree() {
        return motors.get(0).getTicksPerDegree();
    }

    public double getTicksPerSecond() {
        return motors.get(0).getTicksPerSecond();
    }

    public double getGearRatio() {
        return motors.get(0).getGearRatio();
    }

    public int getEncoderResolution() {
        return motors.get(0).getEncoderResolution();
    }

    public double getVelocity(int motor) {
        return motors.get(motor).getVelocity();
    }

    public double getCorrectedVelocity(int motor) {
        return motors.get(motor).getCorrectedVelocity();
    }

    public double getVelocityError(int motor) {
        return motors.get(motor).getVelocityError();
    }

    public double getAcceleration(int motor) {
        return motors.get(motor).getAcceleration();
    }

    public void resetEncoder() {
        for (VelocityMotor motor : motors) motor.resetEncoder();
    }

    public void update() {
        for (VelocityMotor motor : motors) motor.update();
    }

    public VelocityMotorGroup log(Telemetry telemetry, HardwareMap hardwareMap) {
        for (VelocityMotor motor : motors) {
            motor.log(telemetry, hardwareMap);
            telemetry.addLine();
        }

        return this;
    }
}
