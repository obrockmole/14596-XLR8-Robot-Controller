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
        return (!outOfRange()) ? sensor.getDistance(DistanceUnit.MM) : -1;
    }

    public double getDistanceCM() {
        return (!outOfRange()) ? sensor.getDistance(DistanceUnit.CM) : -1;
    }

    public double getDistanceM() {
        return (!outOfRange()) ? sensor.getDistance(DistanceUnit.METER) : -1;
    }

    public double getDistanceIN() {
        return (!outOfRange()) ? sensor.getDistance(DistanceUnit.INCH) : -1;
    }

    public double getDistanceFT() {
        return (!outOfRange()) ? sensor.getDistance(DistanceUnit.INCH) / 12 : -1;
    }

    public double getDistanceYD() {
        return (!outOfRange()) ? sensor.getDistance(DistanceUnit.INCH) / 36 : -1;
    }

    public boolean outOfRange() {
        return getDistanceM() == DistanceUnit.infinity;
    }

    public String getCSVHeader() {
        return "Distance";
    }

    public String getCSVData() {
        return String.format("%s", getDistanceCM());
    }

    public DistanceSensor log(Telemetry telemetry, HardwareMap hardwareMap) {
        telemetry.addData("Sensor", hardwareMap.getNamesOf(sensor).toArray()[0]);
        telemetry.addData("Distance (cm)", getDistanceCM());
        telemetry.addData("Distance (in)", getDistanceIN());
        return this;
    }
}
