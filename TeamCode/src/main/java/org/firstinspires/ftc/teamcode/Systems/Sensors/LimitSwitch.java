package org.firstinspires.ftc.teamcode.Systems.Sensors;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Class representing a Limit Switch.
 */
public class LimitSwitch {
    private com.qualcomm.robotcore.hardware.TouchSensor sensor;
    private boolean currentState, previousState;

    /**
     * Constructs a new LimitSwitch object with a TouchSensor.
     *
     * @param sensor The TouchSensor object that this LimitSwitch will use.
     */
    public LimitSwitch(com.qualcomm.robotcore.hardware.TouchSensor sensor) {
        this.sensor = sensor;
    }

    /**
     * Constructs a new LimitSwitch object with a HardwareMap and a device name.
     *
     * @param hardwareMap The HardwareMap object used to get the TouchSensor object.
     * @param name The name of the TouchSensor object in the HardwareMap.
     */
    public LimitSwitch(HardwareMap hardwareMap, String name) {
        this(hardwareMap.get(com.qualcomm.robotcore.hardware.TouchSensor.class, name));
    }

    /**
     * Returns the TouchSensor object that this LimitSwitch uses.
     *
     * @return The TouchSensor object that this LimitSwitch uses.
     */
    public com.qualcomm.robotcore.hardware.TouchSensor getSensor() {
        return sensor;
    }

    /**
     * Sets the TouchSensor object that this LimitSwitch will use.
     *
     * @param sensor The TouchSensor object to use.
     * @return The current LimitSwitch instance.
     */
    public LimitSwitch setSensor(com.qualcomm.robotcore.hardware.TouchSensor sensor) {
        this.sensor = sensor;
        return this;
    }

    /**
     * Sets the TouchSensor object that this LimitSwitch will use.
     *
     * @param hardwareMap The HardwareMap object used to get the TouchSensor object.
     * @param name The name of the TouchSensor object in the HardwareMap.
     * @return The current LimitSwitch instance.
     */
    public LimitSwitch setSensor(HardwareMap hardwareMap, String name) {
        return setSensor(hardwareMap.get(com.qualcomm.robotcore.hardware.TouchSensor.class, name));
    }

    /**
     * Checks if the LimitSwitch is pressed.
     *
     * @return True if the LimitSwitch is pressed, false otherwise.
     */
    public boolean isDown() {
        return sensor.isPressed();
    }

    /**
     * Checks if the LimitSwitch is not pressed.
     *
     * @return True if the LimitSwitch is not pressed, false otherwise.
     */
    public boolean isUp() {
        return !isDown();
    }

    /**
     * Returns the value of the LimitSwitch.
     *
     * @return The value of the LimitSwitch.
     */
    public double getValue() {
        return sensor.getValue();
    }

    /**
     * Returns the CSV header for the LimitSwitch's data.
     *
     * @return The CSV header as a string.
     */
    public String getCSVHeader() {
        return "Active";
    }

    /**
     * Returns the CSV data of the LimitSwitch's current state.
     *
     * @return The CSV data as a string.
     */
    public String getCSVData() {
        return String.format("%s", ((isDown()) ? 1 : 0));
    }

    /**
     * Logs the LimitSwitch's data to telemetry.
     *
     * @param telemetry The Telemetry object used to log the data.
     * @param hardwareMap The HardwareMap object used to get the name of the TouchSensor.
     * @return The current LimitSwitch instance.
     */
    public LimitSwitch log(Telemetry telemetry, HardwareMap hardwareMap) {
        telemetry.addData("Sensor", hardwareMap.getNamesOf(sensor).toArray()[0]);
        telemetry.addData("Pressed", isDown());
        telemetry.addData("Value", getValue());
        return this;
    }
}
