package org.firstinspires.ftc.teamcode.Systems.Servos;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.ArrayList;
import java.util.Arrays;

public class PositionServoGroup {
    ArrayList<PositionServo> servos;

    public PositionServoGroup(@NonNull PositionServo... servos) {
        this.servos = new ArrayList<>(Arrays.asList(servos));
    }

    public ArrayList<PositionServo> getServos() {
        return servos;
    }

    public PositionServoGroup setServos(@NonNull PositionServo... servos) {
        this.servos.clear();
        this.servos = new ArrayList<>(Arrays.asList(servos));
        return this;
    }

    public PositionServo getServo(int servo) {
        return servos.get(servo);
    }

    public PositionServoGroup setServo(int servo, @NonNull PositionServo newServo) {
        servos.set(servo, newServo);
        return this;
    }

    public boolean isReversed(int servo) {
        return servos.get(servo).isReversed();
    }

    public PositionServoGroup setReversed(int servo, boolean reversed) {
        servos.get(servo).setReversed(reversed);
        return this;
    }

    public double[] getScaleRange() {
        return servos.get(0).getScaleRange();
    }

    public PositionServoGroup setScaleRange(double minScale, double maxScale) {
        for (PositionServo servo : servos)
            servo.setScaleRange(minScale, maxScale);
        return this;
    }

    public double getScaleMin() {
        return servos.get(0).getScaleMin();
    }

    public PositionServoGroup setScaleMin(double minScale) {
        for (PositionServo servo : servos)
            servo.setScaleMin(minScale);
        return this;
    }

    public double getScaleMax() {
        return servos.get(0).getScaleMax();
    }

    public PositionServoGroup setScaleMax(double maxScale) {
        for (PositionServo servo : servos)
            servo.setScaleMax(maxScale);
        return this;
    }

    public double getTargetPosition() {
        return servos.get(0).getTargetPosition();
    }

    public PositionServoGroup setTargetPosition(double targetPosition) {
        for (PositionServo servo : servos) servo.setTargetPosition(targetPosition);
        return this;
    }

    public double getPosition() {
        return servos.get(0).getPosition();
    }

    public PositionServoGroup setPosition(double position) {
        for (PositionServo servo : servos) servo.setPosition(position);
        return this;
    }

    public PositionServoGroup setPosition(double position, double minScale, double maxScale) {
        for (PositionServo servo : servos) servo.setPosition(position, minScale, maxScale);
        return this;
    }

    public PositionServoGroup setPosition(double position, double[] scaleRange) {
        for (PositionServo servo : servos) servo.setPosition(position, scaleRange);
        return this;
    }

    public void update() {
        for (PositionServo servo : servos) servo.update();
    }

    public PositionServoGroup log(Telemetry telemetry, HardwareMap hardwareMap) {
        for (PositionServo servo : servos) {
            servo.log(telemetry, hardwareMap);
            telemetry.addLine();
        }

        return this;
    }
}
