package org.firstinspires.ftc.teamcode.Systems.Servos;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.ArrayList;
import java.util.Arrays;

public class ContinuousServoGroup {
    ArrayList<ContinuousServo> servos;

    public ContinuousServoGroup(@NonNull ContinuousServo... servos) {
        this.servos = new ArrayList<>(Arrays.asList(servos));
    }

    public ArrayList<ContinuousServo> getServos() {
        return servos;
    }

    public ContinuousServoGroup setServos(@NonNull ContinuousServo... servos) {
        this.servos.clear();
        this.servos = new ArrayList<>(Arrays.asList(servos));
        return this;
    }

    public ContinuousServo getServo(int servo) {
        return servos.get(servo);
    }

    public ContinuousServoGroup setServo(int servo, @NonNull ContinuousServo newServo) {
        servos.set(servo, newServo);
        return this;
    }

    public boolean isReversed(int servo) {
        return servos.get(servo).isReversed();
    }

    public ContinuousServoGroup setReversed(boolean reversed) {
        for (ContinuousServo servo : servos)
            servo.setReversed(reversed);
        return this;
    }

    public double getPower(int servo) {
        return servos.get(servo).getPower();
    }

    public double getTargetPower(int servo) {
        return servos.get(servo).getTargetPower();
    }

    public ContinuousServoGroup setTargetPower(double targetPower) {
        for (ContinuousServo servo : servos)
            servo.setTargetPower(targetPower);
        return this;
    }

    public double getPowerError(int servo) {
        return servos.get(servo).getPowerError();
    }

    public String getCSVData(int servo) {
        return servos.get(servo).getCSVData();
    }

    public void update() {
        for (ContinuousServo servo : servos)
            servo.update();
    }

    public ContinuousServoGroup log(Telemetry telemetry, HardwareMap hardwareMap) {
        for (ContinuousServo servo : servos) {
            servo.log(telemetry, hardwareMap);
            telemetry.addLine();
        }

        return this;
    }
}
