package org.firstinspires.ftc.teamcode.Systems.Sensors;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ColorSensor {
    private com.qualcomm.robotcore.hardware.NormalizedColorSensor sensor;
    private int[] rgbaValues = new int[4];
    private double[] hsvValues = new double[3];

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

    public double getHue() {
        return hsvValues[0];
    }

    public double getSaturation() {
        return hsvValues[1];
    }

    public double getValue() {
        return hsvValues[2];
    }

    public int getColor() {
        return sensor.getNormalizedColors().toColor();
    }

    public double[] rgbToHSV(double red, double green, double blue) {
        double maxColor = Math.max(red, Math.max(green, blue));
        double minColor = Math.min(red, Math.min(green, blue));
        double delta = maxColor - minColor;
        double hue = -1, saturation = -1;

        if (maxColor == minColor)
            hue = 0;
        else if (maxColor == red)
            hue = (60 * ((green - blue) / delta) + 360) % 360;
        else if (maxColor == green)
            hue = (60 * ((blue - red) / delta) + 120) % 360;
        else if (maxColor == blue)
            hue = (60 * ((red - green) / delta) + 240) % 360;

        if (maxColor == 0)
            saturation = 0;
        else
            saturation = (delta / maxColor) * 100;

        double value = maxColor * 100;

        return new double[] {hue, saturation, value};
    }

    public String getCSVHeader() {
        return "Red,Green,Blue,Alpha";
    }

    public String getCSVData() {
        return String.format("%s,%s,%s,%s", getRed(), getGreen(), getBlue(), getAlpha());
    }

    public ColorSensor update() {
        NormalizedRGBA colors = sensor.getNormalizedColors();

        rgbaValues = new int[] {
                Range.clip((int)(colors.red * 256), 0, 1),
                Range.clip((int)(colors.green * 256), 0, 1),
                Range.clip((int)(colors.blue * 256), 0, 1),
                Range.clip((int)(colors.alpha * 256), 0, 1)
        };

        hsvValues = rgbToHSV(colors.red, colors.green, colors.blue);
        return this;
    }

    public ColorSensor log(Telemetry telemetry, HardwareMap hardwareMap) {
        telemetry.addData("Sensor", hardwareMap.getNamesOf(sensor).toArray()[0]);
        telemetry.addData("RGBA", "%d, %d, %d, %d", getRed(), getGreen(), getBlue(), getAlpha());
        telemetry.addData("HSV", "%.2f, %.2f, %.2f", getHue(), getSaturation(), getValue());
        return this;
    }
}
