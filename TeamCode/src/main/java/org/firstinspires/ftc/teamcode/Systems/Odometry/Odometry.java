package org.firstinspires.ftc.teamcode.Systems.Odometry;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Systems.Sensors.Encoder;

import java.util.ArrayList;
import java.util.Arrays;

public class Odometry extends ArrayList<OdometryPod> {
    public Odometry(OdometryPod... pods) {
        super();
        this.addAll(Arrays.asList(pods));
    }

    public Odometry(HardwareMap hardwareMap, String... names) {
        super();
        for (String name : names) {
            this.add(new OdometryPod(hardwareMap, name));
        }
    }

    public Odometry(HardwareMap hardwareMap, Encoder.Direction direction, String... names) {
        super();
        for (String name : names) {
            this.add(new OdometryPod(hardwareMap, name, direction));
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

    public Odometry log(Telemetry telemetry, HardwareMap hardwareMap) {
        for (OdometryPod pod : this) {
            pod.log(telemetry, hardwareMap);
            telemetry.addLine();
        }
        return this;
    }
}
