package org.firstinspires.ftc.teamcode.Systems.Sensors;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.VoltageSensor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Class representing a Battery Voltage Sensor.
 */
public class BatteryVoltageSensor {
    private VoltageSensor voltageSensor;

    /**
     * Constructs a new BatteryVoltageSensor object with a VoltageSensor.
     *
     * @param voltageSensor The VoltageSensor object that this BatteryVoltageSensor will use.
     */
    public BatteryVoltageSensor(VoltageSensor voltageSensor) {
        this.voltageSensor = voltageSensor;
    }

    /**
     * Constructs a new BatteryVoltageSensor object with a HardwareMap.
     *
     * @param hardwareMap The HardwareMap object used to get the VoltageSensor object.
     */
    public BatteryVoltageSensor(HardwareMap hardwareMap) {
        this(hardwareMap.voltageSensor.iterator().next());
    }

    /**
     * Returns the voltage of the BatteryVoltageSensor.
     *
     * @return The voltage of the BatteryVoltageSensor.
     */
    public double getVoltage() {
        return voltageSensor.getVoltage();
    }

    /**
     * Returns the battery voltage of the BatteryVoltageSensor.
     *
     * @return The battery voltage of the BatteryVoltageSensor.
     */
    public double getBatteryVoltage() {
        return voltageSensor.getVoltage();
    }

    /**
     * Returns the CSV header for the BatteryVoltageSensor's data.
     *
     * @return The CSV header as a string.
     */
    public String getCSVHeader() {
        return "Voltage";
    }

    /**
     * Returns the CSV data of the BatteryVoltageSensor's current state.
     *
     * @return The CSV data as a string.
     */
    public String getCSVData() {
        return String.format("%s", getVoltage());
    }

    /**
     * Logs the BatteryVoltageSensor's data to telemetry.
     *
     * @param telemetry The Telemetry object used to log the data.
     * @param hardwareMap The HardwareMap object used to get the name of the VoltageSensor.
     * @return The current BatteryVoltageSensor instance.
     */
    public BatteryVoltageSensor log(Telemetry telemetry, HardwareMap hardwareMap) {
        telemetry.addData("Sensor", hardwareMap.getNamesOf(voltageSensor).toArray()[0]);
        telemetry.addData("Voltage", getVoltage());
        return this;
    }
}
