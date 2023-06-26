package org.firstinspires.ftc.teamcode.Systems.Motors;

import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.ArrayList;
import java.util.Arrays;


public class TestingMotor_Logging {
    DcMotorEx motor;
    MotorLookupTable motorType;

    PIDController pidController;
    int targetPosition = 0;
    int tolerance;
    double p, i, d, f;

    public TestingMotor_Logging(DcMotorEx motor, MotorLookupTable motorType, int tolerance, boolean reversed) {
        this.motor = motor;
        this.motorType = motorType;
        this.tolerance = tolerance;
        this.pidController = new PIDController(0, 0, 0);
        setReversed(reversed);
    }

    public TestingMotor_Logging(HardwareMap hardwareMap, String name, MotorLookupTable motorType, int tolerance, boolean reversed) {
        this(hardwareMap.get(DcMotorEx.class, name), motorType, tolerance, reversed);
    }

    public TestingMotor_Logging(DcMotorEx motor, MotorLookupTable motorType, boolean reversed) {
        this(motor, motorType, 10, reversed);
    }

    public TestingMotor_Logging(HardwareMap hardwareMap, String name, MotorLookupTable motorType, boolean reversed) {
        this(hardwareMap.get(DcMotorEx.class, name), motorType, 10, reversed);
    }

    public DcMotorEx getMotor() {
        return motor;
    }

    public TestingMotor_Logging setMotor(DcMotorEx motor) {
        this.motor = motor;
        return this;
    }

    public TestingMotor_Logging setMotor(HardwareMap hardwareMap, String name) {
        this.motor = hardwareMap.get(DcMotorEx.class, name);
        return this;
    }

    public MotorLookupTable getMotorType() {
        return motorType;
    }

    public TestingMotor_Logging setMotorType(MotorLookupTable motorType) {
        this.motorType = motorType;
        return this;
    }

    public boolean isReversed() {
        return motor.getDirection() == DcMotor.Direction.REVERSE;
    }

    public TestingMotor_Logging setReversed(boolean reversed) {
        motor.setDirection(reversed ? DcMotor.Direction.REVERSE : DcMotor.Direction.FORWARD);
        return this;
    }

    public int getTolerance() {
        return tolerance;
    }

    public TestingMotor_Logging setTolerance(int tolerance) {
        this.tolerance = tolerance;
        return this;
    }

    public double getPower() {
        return motor.getPower();
    }

    public TestingMotor_Logging setPower(double power) {
        motor.setPower(power);
        return this;
    }

    public int getCurrentPosition() {
        return motor.getCurrentPosition();
    }

    public int getTargetPosition() {
        return targetPosition;
    }

    public TestingMotor_Logging setTargetPosition(int position) {
        targetPosition = position;
        return this;
    }

    public PIDController getPID() {
        return pidController;
    }

    public ArrayList<Double> getPIDF() {
        return new ArrayList<>(Arrays.asList(p, i, d, f));
    }

    public TestingMotor_Logging setPIDF(double p, double i, double d, double f) {
        this.p = p;
        this.i = i;
        this.d = d;
        this.f = f;
        pidController.setPID(p, i, d);
        return this;
    }

    public TestingMotor_Logging setPIDF(PIDController pidController, double f) {
        this.pidController = pidController;
        this.f = f;
        return this;
    }

    public double getP() {
        return p;
    }

    public TestingMotor_Logging setP(double p) {
        this.p = p;
        return this;
    }

    public double getI() {
        return i;
    }

    public TestingMotor_Logging setI(double i) {
        this.i = i;
        return this;
    }

    public double getD() {
        return d;
    }

    public TestingMotor_Logging setD(double d) {
        this.d = d;
        return this;
    }

    public double getF() {
        return f;
    }

    public TestingMotor_Logging setF(double f) {
        this.f = f;
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

    public String getCSVData() {
        return String.format(",%s,%s,%s", getPower(), getCurrentPosition(), getTargetPosition());
    }

    public void update() {
        double power;

        if (Math.abs(getCurrentPosition() - getTargetPosition()) < tolerance)
            power = getPower();
        else
            power = calculatePIDF();

        setPower(power);
    }

    public TestingMotor_Logging log(Telemetry telemetry, HardwareMap hardwareMap) {
        telemetry.addData("Motor", hardwareMap.getNamesOf(motor));
        telemetry.addData("Reversed", isReversed());
        telemetry.addData("Current Position", getCurrentPosition());
        telemetry.addData("Target Position", getTargetPosition());
        return this;
    }
}
