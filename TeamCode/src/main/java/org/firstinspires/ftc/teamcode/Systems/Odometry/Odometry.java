package org.firstinspires.ftc.teamcode.Systems.Odometry;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Systems.Sensors.Encoder;

import java.util.ArrayList;
import java.util.Arrays;

public class Odometry extends ArrayList<OdometryPod> {
    private double xPosition, yPosition, rotation, trackwidth;

    public Odometry(OdometryPod... pods) {
        super();
        this.addAll(Arrays.asList(pods));
    }

    public OdometryPod getPod(int index) {
        return get(index);
    }

    public Odometry setPod(int index, OdometryPod pod) {
        set(index, pod);
        return this;
    }

    public ArrayList<OdometryPod> getPods() {
        return this;
    }

    public Odometry setPods(OdometryPod... pods) {
        clear();
        this.addAll(Arrays.asList(pods));
        return this;
    }

    public Encoder getEncoder(int index) {
        return get(index).getEncoder();
    }

    public Odometry setEncoder(Encoder encoder, int index) {
        get(index).setEncoder(encoder);
        return this;
    }

    public DcMotorEx getEncoderMotor(int index) {
        return get(index).getEncoderMotor();
    }

    public Odometry setEncoderMotor(DcMotorEx motor, int index) {
        get(index).setEncoderMotor(motor);
        return this;
    }

    public Encoder.Direction getEncoderDirection(int index) {
        return get(index).getEncoderDirection();
    }

    public Odometry setEncoderDirection(Encoder.Direction direction, int index) {
        get(index).setEncoderDirection(direction);
        return this;
    }

    public int getCurrentPosition(int index) {
        return get(index).getCurrentPosition();
    }

    public double getRawVelocity(int index) {
        return get(index).getRawVelocity();
    }

    public double getCorrectedVelocity(int index) {
        return get(index).getCorrectedVelocity();
    }

    public double getXPosition() {
        return xPosition;
    }

    public double getYPosition() {
        return yPosition;
    }

    public double getRotation() {
        return rotation;
    }

    public Odometry update() {
        double distanceCal = 0.000528179599805;

        double lPos = getCurrentPosition(0);
        double rPos = getCurrentPosition(1);
        double pPos = getCurrentPosition(2);

        double xDelta = ((lPos + rPos + pPos) * distanceCal) / 3;
        double yDelta = ((lPos + rPos) * distanceCal) / 2;
        double rDelta = ((lPos - rPos) * distanceCal) / trackwidth;

        rotation += rDelta;
        xPosition += yDelta * Math.cos(rotation) - xDelta * Math.sin(rotation);
        yPosition += yDelta * Math.sin(rotation) + xDelta * Math.cos(rotation);

        return this;
    }

    public Odometry log(Telemetry telemetry, HardwareMap hardwareMap) {
        for (OdometryPod pod : this) {
            pod.log(telemetry, hardwareMap);
            telemetry.addLine();
        }
        telemetry.addLine();
        telemetry.addData("X Position", xPosition);
        telemetry.addData("Y Position", yPosition);
        telemetry.addData("Rotation (degrees)", rotation * 180 / Math.PI);
        return this;
    }
}
