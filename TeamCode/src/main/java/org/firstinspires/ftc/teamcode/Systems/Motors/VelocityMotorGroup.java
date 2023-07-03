package org.firstinspires.ftc.teamcode.Systems.Motors;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

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

    public double getCurrentPosition() {
        return motors.get(0).getCurrentPosition();
    }

    public double getPower() {
        return motors.get(0).getPower();
    }

    public double getTargetVelocity() {
        return motors.get(0).getTargetVelocity();
    }

    public VelocityMotorGroup setTargetVelocity(double targetVelocity) {
        for (VelocityMotor motor : motors) motor.setTargetVelocity(targetVelocity);
        return this;
    }

    public double getVelocity(int motor) {
        return motors.get(motor).getVelocity();
    }


    public double getVelocityError(int motor) {
        return motors.get(motor).getVelocityError();
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


    public PIDFCoefficients getPIDF() {
        return motors.get(0).getPIDF();
    }

    public VelocityMotorGroup setPIDF(PIDFCoefficients pidf) {
        for (VelocityMotor motor : motors) motor.setPIDF(pidf);
        return this;
    }

    public VelocityMotorGroup setPIDF(double p, double i, double d, double f) {
        return setPIDF(new PIDFCoefficients(p, i, d, f));
    }

    public double getP() {
        return motors.get(0).getP();
    }

    public VelocityMotorGroup setP(double p) {
        return setPIDF(p, getI(), getD(), getF());
    }

    public double getI() {
        return motors.get(0).getI();
    }

    public VelocityMotorGroup setI(double i) {
        return setPIDF(getP(), i, getD(), getF());
    }

    public double getD() {
        return motors.get(0).getD();
    }

    public VelocityMotorGroup setD(double d) {
        return setPIDF(getP(), getI(), d, getF());
    }

    public double getF() {
        return motors.get(0).getF();
    }

    public VelocityMotorGroup setF(double f) {
        return setPIDF(getP(), getI(), getD(), f);
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
