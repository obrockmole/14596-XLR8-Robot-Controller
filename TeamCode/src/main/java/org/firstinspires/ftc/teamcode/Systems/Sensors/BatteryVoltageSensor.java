package org.firstinspires.ftc.teamcode.Systems.Sensors;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.VoltageSensor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class BatteryVoltageSensor {
    VoltageSensor voltageSensor;

    public BatteryVoltageSensor(VoltageSensor voltageSensor) {
        this.voltageSensor = voltageSensor;
    }

    public BatteryVoltageSensor(HardwareMap hardwareMap) {
        this(hardwareMap.voltageSensor.iterator().next());
    }

    public double getVoltage() {
        return voltageSensor.getVoltage();
    }

    public double getBatteryVoltage() {
        return voltageSensor.getVoltage();
    }

    public String getCSVHeader() {
        return "Voltage";
    }

    public String getCSVData() {
        return String.format("%s", getVoltage());
    }

    public BatteryVoltageSensor log(Telemetry telemetry, HardwareMap hardwareMap) {
        telemetry.addData("Sensor", hardwareMap.getNamesOf(voltageSensor).toArray()[0]);
        telemetry.addData("Voltage", getVoltage());
        return this;
    }
}
