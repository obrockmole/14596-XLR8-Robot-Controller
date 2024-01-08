package org.firstinspires.ftc.teamcode.Systems.Sensors;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Class representing a Touch Sensor.
 */
public class TouchSensor {
    private com.qualcomm.robotcore.hardware.TouchSensor sensor;

    /**
     * Constructs a new TouchSensor object with a TouchSensor.
     *
     * @param sensor The TouchSensor object that this TouchSensor will use.
     */
    public TouchSensor(com.qualcomm.robotcore.hardware.TouchSensor sensor) {
        this.sensor = sensor;
    }

    /**
     * Constructs a new TouchSensor object with a HardwareMap and a device name.
     *
     * @param hardwareMap The HardwareMap object used to get the TouchSensor object.
     * @param name The name of the TouchSensor object in the HardwareMap.
     */
    public TouchSensor(HardwareMap hardwareMap, String name) {
        this(hardwareMap.get(com.qualcomm.robotcore.hardware.TouchSensor.class, name));
    }

    /**
     * Returns the TouchSensor object that this TouchSensor uses.
     *
     * @return The TouchSensor object that this TouchSensor uses.
     */
    public com.qualcomm.robotcore.hardware.TouchSensor getSensor() {
        return sensor;
    }

    /**
     * Sets the TouchSensor object that this TouchSensor will use.
     *
     * @param sensor The TouchSensor object to use.
     * @return The current TouchSensor instance.
     */
    public TouchSensor setSensor(com.qualcomm.robotcore.hardware.TouchSensor sensor) {
        this.sensor = sensor;
        return this;
    }

    /**
     * Sets the TouchSensor object that this TouchSensor will use.
     *
     * @param hardwareMap The HardwareMap object used to get the TouchSensor object.
     * @param name The name of the TouchSensor object in the HardwareMap.
     * @return The current TouchSensor instance.
     */
    public TouchSensor setSensor(HardwareMap hardwareMap, String name) {
        return setSensor(hardwareMap.get(com.qualcomm.robotcore.hardware.TouchSensor.class, name));
    }

    /**
     * Checks if the TouchSensor is pressed.
     *
     * @return True if the TouchSensor is pressed, false otherwise.
     */
    public boolean isPressed() {
        return sensor.isPressed();
    }

    /**
     * Returns the value of the TouchSensor.
     *
     * @return The value of the TouchSensor.
     */
    public double getValue() {
        return sensor.getValue();
    }

    /**
     * Returns the CSV header for the TouchSensor's data.
     *
     * @return The CSV header as a string.
     */
    public String getCSVHeader() {
        return "Pressed";
    }

    /**
     * Returns the CSV data of the TouchSensor's current state.
     *
     * @return The CSV data as a string.
     */
    public String getCSVData() {
        return String.format("%s", ((isPressed()) ? 1 : 0));
    }

    /**
     * Logs the TouchSensor's data to telemetry.
     *
     * @param telemetry The Telemetry object used to log the data.
     * @param hardwareMap The HardwareMap object used to get the name of the TouchSensor.
     * @return The current TouchSensor instance.
     */
    public TouchSensor log(Telemetry telemetry, HardwareMap hardwareMap) {
        telemetry.addData("Sensor", hardwareMap.getNamesOf(sensor).toArray()[0]);
        telemetry.addData("Pressed", isPressed());
        telemetry.addData("Value", getValue());
        return this;
    }
}
