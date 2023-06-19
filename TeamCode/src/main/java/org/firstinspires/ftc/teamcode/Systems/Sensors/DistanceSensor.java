package org.firstinspires.ftc.teamcode.Systems.Sensors;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class DistanceSensor {
    private com.qualcomm.robotcore.hardware.DistanceSensor sensor;

    public DistanceSensor(com.qualcomm.robotcore.hardware.DistanceSensor sensor) {
        this.sensor = sensor;
    }

    public DistanceSensor(HardwareMap hardwareMap, String name) {
        this(hardwareMap.get(com.qualcomm.robotcore.hardware.DistanceSensor.class, name));
    }

    public com.qualcomm.robotcore.hardware.DistanceSensor getSensor() {
        return sensor;
    }

    public DistanceSensor setSensor(com.qualcomm.robotcore.hardware.DistanceSensor sensor) {
        this.sensor = sensor;
        return this;
    }

    public DistanceSensor setSensor(HardwareMap hardwareMap, String name) {
        return setSensor(hardwareMap.get(com.qualcomm.robotcore.hardware.DistanceSensor.class, name));
    }

    public double getDistance(DistanceUnit distanceUnit) {
        return sensor.getDistance(distanceUnit);
    }

    public double getDistanceMM() {
        return sensor.getDistance(DistanceUnit.MM);
    }

    public double getDistanceCM() {
        return sensor.getDistance(DistanceUnit.CM);
    }

    public double getDistanceM() {
        return sensor.getDistance(DistanceUnit.METER);
    }

    public double getDistanceIN() {
        return sensor.getDistance(DistanceUnit.INCH);
    }

    public double getDistanceFT() {
        return sensor.getDistance(DistanceUnit.INCH) / 12;
    }

    public double getDistanceYD() {
        return sensor.getDistance(DistanceUnit.INCH) / 36;
    }

    public boolean outOfRange() {
        return getDistanceM() == com.qualcomm.robotcore.hardware.DistanceSensor.distanceOutOfRange;
    }

    public DistanceSensor log(Telemetry telemetry, HardwareMap hardwareMap) {
        telemetry.addData("Sensor", hardwareMap.getNamesOf(sensor));
        telemetry.addData("Distance (cm)", getDistanceCM());
        telemetry.addData("Distance (in)", getDistanceIN());
        return this;
    }
}
