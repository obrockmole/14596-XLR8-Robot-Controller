package org.firstinspires.ftc.teamcode.Systems.Sensors;

import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Class representing a Potentiometer.
 */
public class Potentiometer {
    private AnalogInput sensor;

    /**
     * Constructs a new Potentiometer object with an AnalogInput.
     *
     * @param sensor The AnalogInput object that this Potentiometer will use.
     */
    public Potentiometer(AnalogInput sensor) {
        this.sensor = sensor;
    }

    /**
     * Constructs a new Potentiometer object with a HardwareMap and a device name.
     *
     * @param hardwareMap The HardwareMap object used to get the AnalogInput object.
     * @param name The name of the AnalogInput object in the HardwareMap.
     */
    public Potentiometer(HardwareMap hardwareMap, String name) {
        this(hardwareMap.get(AnalogInput.class, name));
    }

    /**
     * Returns the AnalogInput object that this Potentiometer uses.
     *
     * @return The AnalogInput object that this Potentiometer uses.
     */
    public AnalogInput getSensor() {
        return sensor;
    }

    /**
     * Sets the AnalogInput object that this Potentiometer will use.
     *
     * @param sensor The AnalogInput object to use.
     * @return The current Potentiometer instance.
     */
    public Potentiometer setSensor(AnalogInput sensor) {
        this.sensor = sensor;
        return this;
    }

    /**
     * Sets the AnalogInput object that this Potentiometer will use.
     *
     * @param hardwareMap The HardwareMap object used to get the AnalogInput object.
     * @param name The name of the AnalogInput object in the HardwareMap.
     * @return The current Potentiometer instance.
     */
    public Potentiometer setSensor(HardwareMap hardwareMap, String name) {
        return setSensor(hardwareMap.get(AnalogInput.class, name));
    }

    /**
     * Returns the voltage of the Potentiometer.
     *
     * @return The voltage of the Potentiometer.
     */
    public double getVoltage() {
        return sensor.getVoltage();
    }

    /**
     * Returns the angle of the Potentiometer.
     *
     * @return The angle of the Potentiometer.
     */
    public double getAngle() {
        double voltage = getVoltage();
        double angle;

        if (voltage == 0) angle = 270;
        else angle = ((2700 * voltage) + 4455 - Math.sqrt((21870000 * Math.pow(voltage, 2)) - (24057000 * voltage) + 19847025)) / (20 * voltage);

        return Range.scale(angle, 270, 0, 0, 270);
    }

    /**
     * Returns the CSV header for the Potentiometer's data.
     *
     * @return The CSV header as a string.
     */
    public String getCSVHeader() {
        return "Voltage,Angle";
    }

    /**
     * Returns the CSV data of the Potentiometer's current state.
     *
     * @return The CSV data as a string.
     */
    public String getCSVData() {
        return String.format("%s,%s", getVoltage(), getAngle());
    }

    /**
     * Logs the Potentiometer's data to telemetry.
     *
     * @param telemetry The Telemetry object used to log the data.
     * @param hardwareMap The HardwareMap object used to get the name of the AnalogInput.
     * @return The current Potentiometer instance.
     */
    public Potentiometer log(Telemetry telemetry, HardwareMap hardwareMap) {
        telemetry.addData("Sensor", hardwareMap.getNamesOf(sensor).toArray()[0]);
        telemetry.addData("Voltage", getVoltage());
        telemetry.addData("Angle", getAngle());
        return this;
    }
}
