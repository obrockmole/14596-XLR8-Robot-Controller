package org.firstinspires.ftc.teamcode.Systems.Motors;

import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.ArrayList;
import java.util.Arrays;


public class Motor {
    DcMotorEx motor;

    double targetPower = 0;
    double speedScale = 1;

    PIDController pidController;
    int targetPosition = 0;
    int tolerance = 0;
    double p, i, d, f, ticksPerDegree;

    public enum Mode {
        POWER,
        POSITION,
        DISABLED
    }
    Mode mode = Mode.POWER;

    public DcMotorEx getMotor() {
        return motor;
    }

    public Motor setMotor(DcMotorEx motor) {
        this.motor = motor;
        return this;
    }

    public Motor setMotor(HardwareMap hardwareMap, String name) {
        this.motor = hardwareMap.get(DcMotorEx.class, name);
        return this;
    }

    public Mode getMode() {
        return mode;
    }

    public Motor setMode(Mode mode) {
        this.mode = mode;
        return this;
    }

    public boolean isReversed() {
        return motor.getDirection() == DcMotor.Direction.REVERSE;
    }

    public Motor setReversed(boolean reversed) {
        motor.setDirection(reversed ? DcMotor.Direction.REVERSE : DcMotor.Direction.FORWARD);
        return this;
    }

    public double getSpeedScale() {
        return speedScale;
    }

    public Motor setSpeedScale(double speedScale) {
        this.speedScale = speedScale;
        return this;
    }

    public int getTolerance() {
        return tolerance;
    }

    public Motor setTolerance(int tolerance) {
        this.tolerance = tolerance;
        return this;
    }

    public Motor(DcMotorEx motor, Mode mode, double ticksPerDegree, int tolerance, boolean reversed) {
        this.motor = motor;
        this.mode = mode;
        this.ticksPerDegree = ticksPerDegree;
        this.tolerance = tolerance;
        this.pidController = new PIDController(0, 0, 0);
        setReversed(reversed);
    }

    public Motor(HardwareMap hardwareMap, String name, Mode mode, double ticksPerDegree, int tolerance, boolean reversed) {
        this(hardwareMap.get(DcMotorEx.class, name), mode, ticksPerDegree, tolerance, reversed);
    }

    public double getPower() {
        return motor.getPower();
    }

    public Motor setPower(double power) {
        motor.setPower(power * speedScale);
        return this;
    }

    public double getTargetPower() {
        return targetPower;
    }

    public Motor setTargetPower(double power) {
        targetPower = power;
        return this;
    }

    public int getCurrentPosition() {
        return motor.getCurrentPosition();
    }

    public int getTargetPosition() {
        return targetPosition;
    }

    public Motor setTargetPosition(int position) {
        targetPosition = position;
        return this;
    }

    public ArrayList<Double> getPIDF() {
        return new ArrayList<>(Arrays.asList(p, i, d, f));
    }

    public Motor setPIDF(double p, double i, double d, double f) {
        this.p = p;
        this.i = i;
        this.d = d;
        this.f = f;
        pidController.setPID(p, i, d);
        return this;
    }

    public double getP() {
        return p;
    }

    public Motor setP(double p) {
        this.p = p;
        return this;
    }

    public double getI() {
        return i;
    }

    public Motor setI(double i) {
        this.i = i;
        return this;
    }

    public double getD() {
        return d;
    }

    public Motor setD(double d) {
        this.d = d;
        return this;
    }

    public double getF() {
        return f;
    }

    public Motor setF(double f) {
        this.f = f;
        return this;
    }

    public double getTicksPerDegree() {
        return ticksPerDegree;
    }

    public Motor setTicksPerDegree(double ticksPerDegree) {
        this.ticksPerDegree = ticksPerDegree;
        return this;
    }

    public double calculatePIDF() {
        double pid = pidController.calculate(getCurrentPosition(), getTargetPosition());
        double ff = Math.cos(Math.toRadians((getTargetPosition()) / getTicksPerDegree())) * f;

        return pid + ff;
    }

    public void resetEncoder() {
        DcMotor.RunMode currentRunMode = motor.getMode();
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(currentRunMode);
    }

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

    public Motor log(Telemetry telemetry, HardwareMap hardwareMap) {
        telemetry.addData("Motor", hardwareMap.getNamesOf(motor));
        telemetry.addData("Mode", mode);
        telemetry.addData("Reversed", isReversed());
        telemetry.addData("Current Power", getPower());
        telemetry.addData("Target Power", getTargetPower());
        telemetry.addData("Current Position", getCurrentPosition());
        telemetry.addData("Target Position", getTargetPosition());
        return this;
    }
}
