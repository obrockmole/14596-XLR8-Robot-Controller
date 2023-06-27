package org.firstinspires.ftc.teamcode.Systems.Sensors;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ColorSensor {
    private com.qualcomm.robotcore.hardware.NormalizedColorSensor sensor;

    public ColorSensor(com.qualcomm.robotcore.hardware.NormalizedColorSensor sensor) {
        this.sensor = sensor;
    }

    public ColorSensor(HardwareMap hardwareMap, String name) {
        this(hardwareMap.get(com.qualcomm.robotcore.hardware.NormalizedColorSensor.class, name));
    }

    public com.qualcomm.robotcore.hardware.NormalizedColorSensor getSensor() {
        return sensor;
    }

    public ColorSensor setSensor(com.qualcomm.robotcore.hardware.NormalizedColorSensor sensor) {
        this.sensor = sensor;
        return this;
    }

    public ColorSensor setSensor(HardwareMap hardwareMap, String name) {
        return setSensor(hardwareMap.get(com.qualcomm.robotcore.hardware.NormalizedColorSensor.class, name));
    }

    public int getRed() {
        return (int)Range.scale(sensor.getNormalizedColors().red, 0, 1, 0, 255);
    }

    public int getGreen() {
        return (int)Range.scale(sensor.getNormalizedColors().green, 0, 1, 0, 255);
    }

    public int getBlue() {
        return (int)Range.scale(sensor.getNormalizedColors().blue, 0, 1, 0, 255);
    }

    public int getAlpha() {
        return (int)Range.scale(sensor.getNormalizedColors().alpha, 0, 1, 0, 255);
    }

    public int getColor() {
        return sensor.getNormalizedColors().toColor();
    }

    public ColorSensor log(Telemetry telemetry, HardwareMap hardwareMap) {
        telemetry.addData("Sensor", hardwareMap.getNamesOf(sensor));
        telemetry.addData("Red", getRed());
        telemetry.addData("Green", getGreen());
        telemetry.addData("Blue", getBlue());
        telemetry.addData("Alpha", getAlpha());
        return this;
    }
}
