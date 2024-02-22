package org.firstinspires.ftc.teamcode.Systems.Sensors;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import com.qualcomm.robotcore.hardware.SwitchableLight;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

/**
 * Class representing a Color Sensor.
 */
public class ColorSensor {
    private NormalizedColorSensor sensor;
    private int[] rgbaValues = new int[4];
    private double[] hsvValues = new double[3];

    /**
     * Constructs a new ColorSensor object with a NormalizedColorSensor and a gain.
     *
     * @param sensor The NormalizedColorSensor object that this ColorSensor will use.
     * @param gain The gain to set for the NormalizedColorSensor.
     */
    public ColorSensor(NormalizedColorSensor sensor, float gain) {
        sensor.setGain(gain);
        if (sensor instanceof SwitchableLight) {
            ((SwitchableLight)sensor).enableLight(true);
        }
        this.sensor = sensor;
    }

    /**
     * Constructs a new ColorSensor object with a NormalizedColorSensor.
     *
     * @param sensor The NormalizedColorSensor object that this ColorSensor will use.
     */
    public ColorSensor(NormalizedColorSensor sensor) {
        this(sensor, 1);
    }

    /**
     * Constructs a new ColorSensor object with a HardwareMap and a device name.
     *
     * @param hardwareMap The HardwareMap object used to get the NormalizedColorSensor object.
     * @param name The name of the NormalizedColorSensor object in the HardwareMap.
     */
    public ColorSensor(HardwareMap hardwareMap, String name) {
        this(hardwareMap.get(NormalizedColorSensor.class, name), 1);
    }

    /**
     * Constructs a new ColorSensor object with a HardwareMap, a device name, and a gain.
     *
     * @param hardwareMap The HardwareMap object used to get the NormalizedColorSensor object.
     * @param name The name of the NormalizedColorSensor object in the HardwareMap.
     * @param gain The gain to set for the NormalizedColorSensor.
     */
    public ColorSensor(HardwareMap hardwareMap, String name, float gain) {
        this(hardwareMap.get(NormalizedColorSensor.class, name), gain);
    }

    /**
     * Returns the NormalizedColorSensor object that this ColorSensor uses.
     *
     * @return The NormalizedColorSensor object that this ColorSensor uses.
     */
    public NormalizedColorSensor getSensor() {
        return sensor;
    }

    /**
     * Sets the NormalizedColorSensor object that this ColorSensor will use.
     *
     * @param sensor The NormalizedColorSensor object to use.
     * @return The current ColorSensor instance.
     */
    public ColorSensor setSensor(NormalizedColorSensor sensor) {
        this.sensor = sensor;
        return this;
    }

    /**
     * Sets the NormalizedColorSensor object that this ColorSensor will use.
     *
     * @param hardwareMap The HardwareMap object used to get the NormalizedColorSensor object.
     * @param name The name of the NormalizedColorSensor object in the HardwareMap.
     * @return The current ColorSensor instance.
     */
    public ColorSensor setSensor(HardwareMap hardwareMap, String name) {
        return setSensor(hardwareMap.get(NormalizedColorSensor.class, name));
    }

    /**
     * Returns the gain of the ColorSensor.
     *
     * @return The gain of the ColorSensor.
     */
    public float getGain() {
        return sensor.getGain();
    }

    /**
     * Sets the gain of the ColorSensor.
     *
     * @param gain The gain to set for the ColorSensor.
     * @return The current ColorSensor instance.
     */
    public ColorSensor setGain(float gain) {
        sensor.setGain(gain);
        return this;
    }

    /**
     * Increases the gain of the ColorSensor.
     *
     * @param gainIncrease The amount to increase the gain by.
     * @return The current ColorSensor instance.
     */
    public ColorSensor increaseGain(float gainIncrease) {
        sensor.setGain(getGain() + gainIncrease);
        return this;
    }

    /**
     * Returns the RGBA values of the ColorSensor.
     *
     * @return The RGBA values of the ColorSensor.
     */
    public int[] getRGBA() {
        return rgbaValues;
    }

    /**
     * Returns the RGB values of the ColorSensor.
     *
     * @return The RGB values of the ColorSensor.
     */
    public int[] getRGB() {
        return new int[] {getRed(), getGreen(), getBlue()};
    }

    /**
     * Returns the red value of the ColorSensor.
     *
     * @return The red value of the ColorSensor.
     */
    public int getRed() {
        return rgbaValues[0];
    }

    /**
     * Returns the green value of the ColorSensor.
     *
     * @return The green value of the ColorSensor.
     */
    public int getGreen() {
        return rgbaValues[1];
    }

    /**
     * Returns the blue value of the ColorSensor.
     *
     * @return The blue value of the ColorSensor.
     */
    public int getBlue() {
        return rgbaValues[2];
    }

    /**
     * Returns the alpha value of the ColorSensor.
     *
     * @return The alpha value of the ColorSensor.
     */
    public int getAlpha() {
        return rgbaValues[3];
    }

    /**
     * Returns the HSV values of the ColorSensor.
     *
     * @return The HSV values of the ColorSensor.
     */
    public double[] getHSV() {
        return hsvValues;
    }

    /**
     * Returns the hue value of the ColorSensor.
     *
     * @return The hue value of the ColorSensor.
     */
    public double getHue() {
        return hsvValues[0];
    }

    /**
     * Returns the saturation value of the ColorSensor.
     *
     * @return The saturation value of the ColorSensor.
     */
    public double getSaturation() {
        return hsvValues[1];
    }

    /**
     * Returns the value of the ColorSensor.
     *
     * @return The value of the ColorSensor.
     */
    public double getValue() {
        return hsvValues[2];
    }

    /**
     * Returns the color of the ColorSensor.
     *
     * @return The color of the ColorSensor.
     */
    public int getColor() {
        return sensor.getNormalizedColors().toColor();
    }

    /**
     * Converts RGB values to HSV values.
     *
     * @param red The red value of the ColorSensor.
     * @param green The green value of the ColorSensor.
     * @param blue The blue value of the ColorSensor.
     * @return The HSV values of the ColorSensor.
     */
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

    /**
     * Returns the distance measured by the ColorSensor if it is capable, otherwise returns -1.
     *
     * @param distanceUnit The unit of measurement for the distance.
     * @return The distance measured by the ColorSensor.
     */
    public double getDistance(DistanceUnit distanceUnit) {
        if (sensor instanceof DistanceSensor) {
            return !outOfRange() ? ((DistanceSensor) sensor).getDistance(distanceUnit) : -1;
        }
        return -1;
    }

    /**
     * Returns the distance measured by the ColorSensor in millimeters if it is capable, otherwise returns -1.
     *
     * @return The distance measured by the ColorSensor in millimeters.
     */
    public double getDistanceMM() {
        return getDistance(DistanceUnit.MM);
    }

    /**
     * Returns the distance measured by the ColorSensor in centimeters if it is capable, otherwise returns -1.
     *
     * @return The distance measured by the ColorSensor in centimeters.
     */
    public double getDistanceCM() {
        return getDistance(DistanceUnit.CM);
    }

    /**
     * Returns the distance measured by the ColorSensor in meters if it is capable, otherwise returns -1.
     *
     * @return The distance measured by the ColorSensor in meters.
     */
    public double getDistanceM() {
        return getDistance(DistanceUnit.METER);
    }

    /**
     * Returns the distance measured by the ColorSensor in inches if it is capable, otherwise returns -1.
     *
     * @return The distance measured by the ColorSensor in inches.
     */
    public double getDistanceIN() {
        return getDistance(DistanceUnit.INCH);
    }

    /**
     * Returns the distance measured by the ColorSensor in feet if it is capable, otherwise returns -1.
     *
     * @return The distance measured by the ColorSensor in feet.
     */
    public double getDistanceFT() {
        return getDistance(DistanceUnit.INCH) / 12;
    }

    /**
     * Returns the distance measured by the ColorSensor in yards if it is capable, otherwise returns -1.
     *
     * @return The distance measured by the ColorSensor in yards.
     */
    public double getDistanceYD() {
        return getDistance(DistanceUnit.INCH) / 36;
    }

    /**
     * Returns whether the ColorSensor is out of range if it is capable, otherwise returns true.
     *
     * @return Whether the ColorSensor is out of range.
     */
    public boolean outOfRange() {
        if (sensor instanceof DistanceSensor) {
            return ((DistanceSensor) sensor).getDistance(DistanceUnit.CM) == DistanceUnit.infinity;
        }
        return true;
    }

    /**
     * Returns the CSV header for the ColorSensor's data.
     *
     * @return The CSV header as a string.
     */
    public String getCSVHeader() {
        return "Red,Green,Blue,Alpha,Distance(cm)";
    }

    /**
     * Returns the CSV data of the ColorSensor's current state.
     *
     * @return The CSV data as a string.
     */
    public String getCSVData() {
        return String.format("%s,%s,%s,%s,%s", getRed(), getGreen(), getBlue(), getAlpha(), getDistanceCM());
    }

    /**
     * Updates the ColorSensor's data.
     *
     * @return The current ColorSensor instance.
     */
    public ColorSensor update() {
        NormalizedRGBA colors = sensor.getNormalizedColors();

        rgbaValues = new int[] {
                Range.clip((int)(colors.red * 256), 0, 255),
                Range.clip((int)(colors.green * 256), 0, 255),
                Range.clip((int)(colors.blue * 256), 0, 255),
                Range.clip((int)(colors.alpha * 256), 0, 255)
        };

        hsvValues = rgbToHSV(colors.red, colors.green, colors.blue);
        return this;
    }

    /**
     * Logs the ColorSensor's data to telemetry.
     *
     * @param telemetry The Telemetry object used to log the data.
     * @param hardwareMap The HardwareMap object used to get the name of the NormalizedColorSensor.
     * @return The current ColorSensor instance.
     */
    public ColorSensor log(Telemetry telemetry, HardwareMap hardwareMap) {
        telemetry.addData("Sensor", hardwareMap.getNamesOf(sensor).toArray()[0]);
        telemetry.addData("RGBA", "%d, %d, %d, %d", getRed(), getGreen(), getBlue(), getAlpha());
        telemetry.addData("HSV", "%.2f, %.2f, %.2f", getHue(), getSaturation(), getValue());
        telemetry.addData("Distance (cm)", getDistanceCM());
        return this;
    }
}
