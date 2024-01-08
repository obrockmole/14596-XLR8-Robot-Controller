package org.firstinspires.ftc.teamcode.Systems.Sensors;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

/**
 * Class representing a Distance Sensor.
 */
public class DistanceSensor {
    private com.qualcomm.robotcore.hardware.DistanceSensor sensor;

    /**
     * Constructs a new DistanceSensor object with a DistanceSensor.
     *
     * @param sensor The DistanceSensor object that this DistanceSensor will use.
     */
    public DistanceSensor(com.qualcomm.robotcore.hardware.DistanceSensor sensor) {
        this.sensor = sensor;
    }

    /**
     * Constructs a new DistanceSensor object with a HardwareMap and a device name.
     *
     * @param hardwareMap The HardwareMap object used to get the DistanceSensor object.
     * @param name The name of the DistanceSensor object in the HardwareMap.
     */
    public DistanceSensor(HardwareMap hardwareMap, String name) {
        this(hardwareMap.get(com.qualcomm.robotcore.hardware.DistanceSensor.class, name));
    }

    /**
     * Returns the DistanceSensor object that this DistanceSensor uses.
     *
     * @return The DistanceSensor object that this DistanceSensor uses.
     */
    public com.qualcomm.robotcore.hardware.DistanceSensor getSensor() {
        return sensor;
    }

    /**
     * Sets the DistanceSensor object that this DistanceSensor will use.
     *
     * @param sensor The DistanceSensor object to use.
     * @return The current DistanceSensor instance.
     */
    public DistanceSensor setSensor(com.qualcomm.robotcore.hardware.DistanceSensor sensor) {
        this.sensor = sensor;
        return this;
    }

    /**
     * Sets the DistanceSensor object that this DistanceSensor will use.
     *
     * @param hardwareMap The HardwareMap object used to get the DistanceSensor object.
     * @param name The name of the DistanceSensor object in the HardwareMap.
     * @return The current DistanceSensor instance.
     */
    public DistanceSensor setSensor(HardwareMap hardwareMap, String name) {
        return setSensor(hardwareMap.get(com.qualcomm.robotcore.hardware.DistanceSensor.class, name));
    }

    /**
     * Returns the distance measured by the DistanceSensor.
     *
     * @param distanceUnit The unit of measurement for the distance.
     * @return The distance measured by the DistanceSensor.
     */
    public double getDistance(DistanceUnit distanceUnit) {
        return sensor.getDistance(distanceUnit);
    }

    /**
     * Returns the distance measured by the DistanceSensor in millimeters.
     *
     * @return The distance measured by the DistanceSensor in millimeters.
     */
    public double getDistanceMM() {
        return (!outOfRange()) ? sensor.getDistance(DistanceUnit.MM) : -1;
    }

    /**
     * Returns the distance measured by the DistanceSensor in centimeters.
     *
     * @return The distance measured by the DistanceSensor in centimeters.
     */
    public double getDistanceCM() {
        return (!outOfRange()) ? sensor.getDistance(DistanceUnit.CM) : -1;
    }

    /**
     * Returns the distance measured by the DistanceSensor in meters.
     *
     * @return The distance measured by the DistanceSensor in meters.
     */
    public double getDistanceM() {
        return (!outOfRange()) ? sensor.getDistance(DistanceUnit.METER) : -1;
    }

    /**
     * Returns the distance measured by the DistanceSensor in inches.
     *
     * @return The distance measured by the DistanceSensor in inches.
     */
    public double getDistanceIN() {
        return (!outOfRange()) ? sensor.getDistance(DistanceUnit.INCH) : -1;
    }

    /**
     * Returns the distance measured by the DistanceSensor in feet.
     *
     * @return The distance measured by the DistanceSensor in feet.
     */
    public double getDistanceFT() {
        return (!outOfRange()) ? sensor.getDistance(DistanceUnit.INCH) / 12 : -1;
    }

    /**
     * Returns the distance measured by the DistanceSensor in yards.
     *
     * @return The distance measured by the DistanceSensor in yards.
     */
    public double getDistanceYD() {
        return (!outOfRange()) ? sensor.getDistance(DistanceUnit.INCH) / 36 : -1;
    }

    /**
     * Checks if the DistanceSensor is out of range.
     *
     * @return True if the DistanceSensor is out of range, false otherwise.
     */
    public boolean outOfRange() {
        return getDistanceM() == DistanceUnit.infinity;
    }

    /**
     * Returns the CSV header for the DistanceSensor's data.
     *
     * @return The CSV header as a string.
     */
    public String getCSVHeader() {
        return "Distance";
    }

    /**
     * Returns the CSV data of the DistanceSensor's current state.
     *
     * @return The CSV data as a string.
     */
    public String getCSVData() {
        return String.format("%s", getDistanceCM());
    }

    /**
     * Logs the DistanceSensor's data to telemetry.
     *
     * @param telemetry The Telemetry object used to log the data.
     * @param hardwareMap The HardwareMap object used to get the name of the DistanceSensor.
     * @return The current DistanceSensor instance.
     */
    public DistanceSensor log(Telemetry telemetry, HardwareMap hardwareMap) {
        telemetry.addData("Sensor", hardwareMap.getNamesOf(sensor).toArray()[0]);
        telemetry.addData("Distance (cm)", getDistanceCM());
        telemetry.addData("Distance (in)", getDistanceIN());
        return this;
    }
}
