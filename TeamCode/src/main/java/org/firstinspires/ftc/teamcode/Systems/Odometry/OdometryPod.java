package org.firstinspires.ftc.teamcode.Systems.Odometry;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Systems.Sensors.Encoder;
import org.firstinspires.ftc.teamcode.Systems.Servos.PositionServo;

public class OdometryPod {
    private Encoder encoder;
    private PositionServo retractionServo;

    //TODO: Figure out correct positions
    private double extendedPosition = 0;
    private double retractedPosition = 0.5;

    public OdometryPod(Encoder encoder, PositionServo retractionServo) {
        this.encoder = encoder;
        this.retractionServo = retractionServo;
    }

    public Encoder getEncoder() {
        return encoder;
    }

    public OdometryPod setEncoder(Encoder encoder) {
        this.encoder = encoder;
        return this;
    }

    public DcMotorEx getEncoderMotor() {
        return encoder.getMotor();
    }

    public OdometryPod setEncoderMotor(DcMotorEx motor) {
        encoder.setMotor(motor);
        return this;
    }

    public Encoder.Direction getEncoderDirection() {
        return encoder.getDirection();
    }

    public OdometryPod setEncoderDirection(Encoder.Direction direction) {
        encoder.setDirection(direction);
        return this;
    }

    public int getCurrentPosition() {
        return encoder.getCurrentPosition();
    }

    public double getRawVelocity() {
        return encoder.getRawVelocity();
    }

    public double getCorrectedVelocity() {
        return encoder.getCorrectedVelocity();
    }

    public PositionServo getRetractionServo() {
        return retractionServo;
    }

    public OdometryPod setRetractionServo(PositionServo retractionServo) {
        this.retractionServo = retractionServo;
        return this;
    }

    public OdometryPod setRetractionServo(HardwareMap hardwareMap, String name) {
        this.retractionServo = new PositionServo(hardwareMap, name, 0, 1, false);
        return this;
    }

    public OdometryPod extendServo() {
        retractionServo.setTargetPosition(extendedPosition);
        return this;
    }

    public OdometryPod retractServo() {
        retractionServo.setTargetPosition(retractedPosition);
        return this;
    }

    public OdometryPod toggleServoRetraction() {
        if (retractionServo.getTargetPosition() == extendedPosition)
            retractServo();
        else
            extendServo();

        return this;
    }

    public boolean isRetracted() {
        return retractionServo.getTargetPosition() == retractedPosition;
    }

    public OdometryPod update() {
        retractionServo.update();
        return this;
    }

    public OdometryPod log(Telemetry telemetry, HardwareMap hardwareMap) {
        telemetry.addData("Encoder", hardwareMap.getNamesOf(encoder.getMotor()).toArray()[0]);
        telemetry.addData("Servo", hardwareMap.getNamesOf(retractionServo.getServo()).toArray()[0]);
        telemetry.addData("Encoder Position", getCurrentPosition());
        telemetry.addData("Encoder Velocity", getCorrectedVelocity());
        telemetry.addData("Retracted", isRetracted());
        return this;
    }
}
