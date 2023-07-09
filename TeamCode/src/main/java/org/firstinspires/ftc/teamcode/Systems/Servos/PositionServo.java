package org.firstinspires.ftc.teamcode.Systems.Servos;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class PositionServo {
    Servo servo;
    double targetPosition = 0;
    double[] scaleRange = new double[] { 0, 1 };

    public PositionServo(Servo servo, double minScale, double maxScale, boolean reversed) {
        this.servo = servo;
        setReversed(reversed);

        this.setScaleRange(minScale, maxScale);
    }

    public PositionServo(HardwareMap hardwareMap, String name, double minScale, double maxScale, boolean reversed) {
        this(hardwareMap.get(Servo.class, name), minScale, maxScale, reversed);
    }

    public Servo getServo() {
        return servo;
    }

    public PositionServo setServo(Servo servo) {
        this.servo = servo;
        return this;
    }

    public PositionServo setServo(HardwareMap hardwareMap, String name) {
        this.servo = hardwareMap.get(Servo.class, name);
        return this;
    }

    public boolean isReversed() {
        return servo.getDirection() == Servo.Direction.REVERSE;
    }

    public PositionServo setReversed(boolean reversed) {
        servo.setDirection(reversed ? Servo.Direction.REVERSE : Servo.Direction.FORWARD);
        return this;
    }

    public double[] getScaleRange() {
        return new double[] { scaleRange[0], scaleRange[1] };
    }

    public PositionServo setScaleRange(double minScale, double maxScale) {
        scaleRange = new double[] { minScale, maxScale };
        servo.scaleRange(minScale, maxScale);
        return this;
    }

    public double getScaleMin() {
        return scaleRange[0];
    }

    public PositionServo setScaleMin(double minScale) {
        scaleRange[0] = minScale;
        servo.scaleRange(minScale, scaleRange[1]);
        return this;
    }

    public double getScaleMax() {
        return scaleRange[1];
    }

    public PositionServo setScaleMax(double maxScale) {
        scaleRange[1] = maxScale;
        servo.scaleRange(scaleRange[0], maxScale);
        return this;
    }

    public double getTargetPosition() {
        return targetPosition;
    }

    public PositionServo setTargetPosition(double targetPosition) {
        this.targetPosition = targetPosition;
        return this;
    }

    public double getPosition() {
        return servo.getPosition();
    }

    public PositionServo setPosition(double position) {
        servo.setPosition(position);
        return this;
    }

    public PositionServo setPosition(double position, double minScale, double maxScale) {
        servo.scaleRange(minScale, maxScale);
        servo.setPosition(position);
        servo.scaleRange(scaleRange[0], scaleRange[1]);
        return this;
    }

    public PositionServo setPosition(double position, double[] scaleRange) {
        servo.scaleRange(scaleRange[0], scaleRange[1]);
        servo.setPosition(position);
        servo.scaleRange(this.scaleRange[0], this.scaleRange[1]);
        return this;
    }

    public double getPositionError() {
        return Math.abs(targetPosition - getPosition());
    }

    public String getCSVData() {
        return String.format(",%s,%s,%s", getPosition(), getTargetPosition(), getPositionError());
    }

    public void update() {
        setPosition(targetPosition);
    }

    public PositionServo log(Telemetry telemetry, HardwareMap hardwareMap) {
        telemetry.addData("Servo", hardwareMap.getNamesOf(servo).toArray()[0]);
        telemetry.addData("Current Position", getPosition());
        telemetry.addData("Target Position", getTargetPosition());
        telemetry.addData("Scale Range", getScaleRange().toString());
        telemetry.addData("Reversed", isReversed());
        return this;
    }
}
