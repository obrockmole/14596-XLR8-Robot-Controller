package org.firstinspires.ftc.teamcode.Systems.Odometry;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Systems.Sensors.Encoder;

public class OdometryPod {
    private Encoder encoder;

    public OdometryPod(Encoder encoder) {
        this.encoder = encoder;
    }

    public OdometryPod(HardwareMap hardwareMap, String name) {
        this(new Encoder(hardwareMap, name));
    }

    public OdometryPod(HardwareMap hardwareMap, String name, Encoder.Direction direction) {
        this(new Encoder(hardwareMap, name, direction));
    }

    public OdometryPod(DcMotorEx motor) {
        this(new Encoder(motor));
    }

    public OdometryPod(DcMotorEx motor, Encoder.Direction direction) {
        this(new Encoder(motor, direction));
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

    public OdometryPod log(Telemetry telemetry, HardwareMap hardwareMap) {
        telemetry.addData("Encoder", hardwareMap.getNamesOf(getEncoderMotor()).toArray()[0]);
        telemetry.addData("Encoder Position", getCurrentPosition());
        return this;
    }
}
