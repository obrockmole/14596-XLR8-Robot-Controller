package org.firstinspires.ftc.teamcode.Systems.Motors;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.ArrayList;
import java.util.Arrays;

public class MotorGroup {
    ArrayList<Motor> motors;

    public MotorGroup(@NonNull Motor... motors) {
        this.motors = new ArrayList<>(Arrays.asList(motors));
    }

    public ArrayList<Motor> getMotors() {
        return motors;
    }

    public MotorGroup setMotors(@NonNull Motor... motors) {
        this.motors.clear();
        this.motors = new ArrayList<>(Arrays.asList(motors));
        return this;
    }

    public Motor getMotor(int motor) {
        return motors.get(motor);
    }

    public MotorGroup setMotor(int motor, @NonNull Motor newMotor) {
        motors.set(motor, newMotor);
        return this;
    }

    public MotorList getMotorType() {
        return motors.get(0).getMotorType();
    }

    public MotorGroup setMotorType(MotorList motorType) {
        for (Motor motor : motors) motor.setMotorType(motorType);
        return this;
    }

    public Motor.Mode getMode() {
        return motors.get(0).getMode();
    }

    public MotorGroup setMode(Motor.Mode mode) {
        for (Motor motor : motors) motor.setMode(mode);
        return this;
    }

    public boolean isReversed(int motor) {
        return motors.get(motor).isReversed();
    }

    public MotorGroup setReversed(int motor, boolean reversed) {
        motors.get(motor).setReversed(reversed);
        return this;
    }

    public double getSpeedScale() {
        return motors.get(0).getSpeedScale();
    }

    public MotorGroup setSpeedScale(double speedScale) {
        for (Motor motor : motors) motor.setSpeedScale(speedScale);
        return this;
    }

    public int getTolerance() {
        return motors.get(0).getTolerance();
    }

    public MotorGroup setTolerance(int tolerance) {
        for (Motor motor : motors) motor.setTolerance(tolerance);
        return this;
    }

    public double getPower() {
        return motors.get(0).getPower();
    }

    public MotorGroup setTargetPower(double power) {
        for (Motor motor : motors) motor.setTargetPower(power);
        return this;
    }

    public double getPowerError(int motor) {
        return motors.get(motor).getPowerError();
    }

    public int getCurrentPosition(int motor) {
        return motors.get(motor).getCurrentPosition();
    }

    public int getTargetPosition() {
        return motors.get(0).getTargetPosition();
    }

    public MotorGroup setTargetPosition(int position) {
        for (Motor motor : motors) motor.setTargetPosition(position);
        return this;
    }

    public int getPositionError(int motor) {
        return motors.get(motor).getPositionError();
    }

    public ArrayList<Double> getPIDF() {
        return motors.get(0).getPIDF();
    }

    public MotorGroup setPIDF(double p, double i, double d, double f) {
        for (Motor motor : motors) motor.setPIDF(p, i, d, f);
        return this;
    }

    public double getP() {
        return motors.get(0).getP();
    }

    public MotorGroup setP(double p) {
        for (Motor motor : motors) motor.setP(p);
        return this;
    }

    public double getI() {
        return motors.get(0).getI();
    }

    public MotorGroup setI(double i) {
        for (Motor motor : motors) motor.setI(i);
        return this;
    }

    public double getD() {
        return motors.get(0).getD();
    }

    public MotorGroup setD(double d) {
        for (Motor motor : motors) motor.setD(d);
        return this;
    }

    public double getF() {
        return motors.get(0).getF();
    }

    public MotorGroup setF(double f) {
        for (Motor motor : motors) motor.setF(f);
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

    public void resetEncoder() {
        for (Motor motor : motors) motor.resetEncoder();
    }

    public String getCSVData(int motor) {
        return motors.get(motor).getCSVData();
    }

    public void update() {
        for (Motor motor : motors) motor.update();
    }

    public MotorGroup log(Telemetry telemetry, HardwareMap hardwareMap) {
        for (Motor motor : motors) {
            motor.log(telemetry, hardwareMap);
            telemetry.addLine();
        }

        return this;
    }
}
