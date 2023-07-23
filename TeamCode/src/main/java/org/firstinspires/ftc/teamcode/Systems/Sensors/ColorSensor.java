package org.firstinspires.ftc.teamcode.Systems.Sensors;

import android.graphics.Color;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ColorSensor {
    private com.qualcomm.robotcore.hardware.NormalizedColorSensor sensor;
    private final int[] rgbaValues = new int[4];
    private final float[] hsvValues = new float[3];

    public ColorSensor(com.qualcomm.robotcore.hardware.NormalizedColorSensor sensor, float gain) {
        sensor.setGain(gain);
        this.sensor = sensor;
    }

    public ColorSensor(com.qualcomm.robotcore.hardware.NormalizedColorSensor sensor) {
        this(sensor, 1);
    }

    public ColorSensor(HardwareMap hardwareMap, String name) {
        this(hardwareMap.get(com.qualcomm.robotcore.hardware.NormalizedColorSensor.class, name), 1);
    }

    public ColorSensor(HardwareMap hardwareMap, String name, float gain) {
        this(hardwareMap.get(com.qualcomm.robotcore.hardware.NormalizedColorSensor.class, name), gain);
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
        return rgbaValues[0];
    }

    public int getGreen() {
        return rgbaValues[1];
    }

    public int getBlue() {
        return rgbaValues[2];
    }

    public int getAlpha() {
        return rgbaValues[3];
    }

    public float getHue() {
        return hsvValues[0];
    }

    public float getSaturation() {
        return hsvValues[1];
    }

    public float getValue() {
        return hsvValues[2];
    }

    public int getColor() {
        return sensor.getNormalizedColors().toColor();
    }

    public String getCSVData() {
        return String.format(",%s,%s,%s,%s", getRed(), getGreen(), getBlue(), getAlpha());
    }

    public ColorSensor update() {
        NormalizedRGBA colors = sensor.getNormalizedColors();

        rgbaValues[0] = Color.red(colors.toColor());
        rgbaValues[1] = Color.green(colors.toColor());
        rgbaValues[2] = Color.blue(colors.toColor());
        rgbaValues[3] = Color.alpha(colors.toColor());

        Color.colorToHSV(colors.toColor(), hsvValues);
        return this;
    }

    public ColorSensor log(Telemetry telemetry, HardwareMap hardwareMap) {
        telemetry.addData("Sensor", hardwareMap.getNamesOf(sensor).toArray()[0]);
        telemetry.addData("RGBA", "%d, %d, %d, %d", getRed(), getGreen(), getBlue(), getAlpha());
        telemetry.addData("HSV", "%.2f, %.2f, %.2f", getHue(), getSaturation(), getValue());
        return this;
    }
}
