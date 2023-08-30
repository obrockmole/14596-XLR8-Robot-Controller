package org.firstinspires.ftc.teamcode.Systems.Odometry;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Systems.Sensors.Encoder;
import org.firstinspires.ftc.teamcode.Systems.Servos.PositionServo;

import java.util.ArrayList;

public class Odometry extends ArrayList<OdometryPod> {
    public Odometry(OdometryPod... pods) {
        super();
        for (OdometryPod pod : pods) {
            add(pod);
        }
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
        for (OdometryPod pod : pods) {
            add(pod);
        }
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

    public PositionServo getRetractionServo(int index) {
        return get(index).getRetractionServo();
    }

    public Odometry setRetractionServo(PositionServo retractionServo, int index) {
        get(index).setRetractionServo(retractionServo);
        return this;
    }

    public Odometry setRetractionServo(HardwareMap hardwareMap, String name, int index) {
        get(index).setRetractionServo(hardwareMap, name);
        return this;
    }

    public Odometry extendServo(int index) {
        get(index).extendServo();
        return this;
    }

    public Odometry retractServo(int index) {
        get(index).retractServo();
        return this;
    }

    public Odometry toggleServoRetraction(int index) {
        get(index).toggleServoRetraction();
        return this;
    }

    public Odometry extendAllPods() {
        for (OdometryPod pod : this) {
            pod.extendServo();
        }
        return this;
    }

    public Odometry retractAllPods() {
        for (OdometryPod pod : this) {
            pod.retractServo();
        }
        return this;
    }

    public Odometry toggleAllPods() {
        for (OdometryPod pod : this) {
            pod.toggleServoRetraction();
        }
        return this;
    }

    public boolean isRetracted(int index) {
        return get(index).isRetracted();
    }

    public Odometry update() {
        for (OdometryPod pod : this) {
            pod.update();
        }
        return this;
    }

    public Odometry log(Telemetry telemetry, HardwareMap hardwareMap) {
        for (OdometryPod pod : this) {
            pod.log(telemetry, hardwareMap);
            telemetry.addLine();
        }
        return this;
    }
}
