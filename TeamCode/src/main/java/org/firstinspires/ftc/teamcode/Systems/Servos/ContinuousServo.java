package org.firstinspires.ftc.teamcode.Systems.Servos;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ContinuousServo {
    CRServo servo;
    double targetPower = 0;

    public ContinuousServo(CRServo servo, boolean reversed) {
        this.servo = servo;
        this.servo.setDirection(reversed ? CRServo.Direction.REVERSE : CRServo.Direction.FORWARD);
    }

    public ContinuousServo(HardwareMap hardwareMap, String name, boolean reversed) {
        this(hardwareMap.get(CRServo.class, name), reversed);
    }

    public CRServo getServo() {
        return servo;
    }

    public ContinuousServo setServo(CRServo servo) {
        this.servo = servo;
        return this;
    }

    public ContinuousServo setServo(HardwareMap hardwaremap, String name) {
        this.servo = hardwaremap.get(CRServo.class, name);
        return this;
    }

    public boolean isReversed() {
        return servo.getDirection() == CRServo.Direction.REVERSE;
    }

    public ContinuousServo setReversed(boolean reversed) {
        servo.setDirection(reversed ? CRServo.Direction.REVERSE : CRServo.Direction.FORWARD);
        return this;
    }

    public double getPower() {
        return servo.getPower();
    }

    private void setPower(double power) {
        servo.setPower(power);
    }

    public double getTargetPower() {
        return targetPower;
    }

    public ContinuousServo setTargetPower(double targetPower) {
        this.targetPower = targetPower;
        return this;
    }

    public double getPowerError() {
        return Math.abs(targetPower - getPower());
    }

    public String getCSVData() {
        return String.format(",%s,%s,%s", getPower(), getTargetPower(), getPowerError());
    }

    public void update() {
        servo.setPower(targetPower);
    }

    public ContinuousServo log(Telemetry telemetry, HardwareMap hardwareMap) {
        telemetry.addData("Servo", hardwareMap.getNamesOf(servo).toArray()[0]);
        telemetry.addData("Current Power", getPower());
        telemetry.addData("Target Power", getTargetPower());
        telemetry.addData("Reversed", isReversed());

        return this;
    }
}
