package org.firstinspires.ftc.teamcode.Systems.Sensors;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ColorSensor {
    private com.qualcomm.robotcore.hardware.ColorSensor sensor;

    public ColorSensor(com.qualcomm.robotcore.hardware.ColorSensor sensor) {
        this.sensor = sensor;
    }

    public ColorSensor(HardwareMap hardwareMap, String name) {
        this(hardwareMap.get(com.qualcomm.robotcore.hardware.ColorSensor.class, name));
    }

    public com.qualcomm.robotcore.hardware.ColorSensor getSensor() {
        return sensor;
    }

    public ColorSensor setSensor(com.qualcomm.robotcore.hardware.ColorSensor sensor) {
        this.sensor = sensor;
        return this;
    }

    public ColorSensor setSensor(HardwareMap hardwareMap, String name) {
        return setSensor(hardwareMap.get(com.qualcomm.robotcore.hardware.ColorSensor.class, name));
    }

    public int getRed() {
        return sensor.red();
    }

    public int getGreen() {
        return sensor.green();
    }

    public int getBlue() {
        return sensor.blue();
    }

    public int getAlpha() {
        return sensor.alpha();
    }

    public int getHue() {
        return sensor.argb();
    }

    public ColorSensor enableLED() {
        sensor.enableLed(true);
        return this;
    }

    public ColorSensor disableLED() {
        sensor.enableLed(false);
        return this;
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
