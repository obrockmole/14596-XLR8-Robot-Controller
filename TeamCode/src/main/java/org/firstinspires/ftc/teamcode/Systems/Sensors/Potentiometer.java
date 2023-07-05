package org.firstinspires.ftc.teamcode.Systems.Sensors;

import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Potentiometer {
    private AnalogInput sensor;

    public Potentiometer(AnalogInput sensor) {
        this.sensor = sensor;
    }

    public Potentiometer(HardwareMap hardwareMap, String name) {
        this(hardwareMap.get(AnalogInput.class, name));
    }

    public AnalogInput getSensor() {
        return sensor;
    }

    public Potentiometer setSensor(AnalogInput sensor) {
        this.sensor = sensor;
        return this;
    }

    public Potentiometer setSensor(HardwareMap hardwareMap, String name) {
        return setSensor(hardwareMap.get(AnalogInput.class, name));
    }

    public double getVoltage() {
        return sensor.getVoltage();
    }

    public double getAngle() {
        double voltage = getVoltage();
        double angle;

        if (voltage == 0) angle = 270;
        else angle = ((2700 * voltage) + 4455 - Math.sqrt((21870000 * Math.pow(voltage, 2)) - (24057000 * voltage) + 19847025)) / (20 * voltage);

        return Range.scale(angle, 270, 0, 0, 270);
    }

    public String getCSVData() {
        return String.format(",%s,%s", getVoltage(), getAngle());
    }

    public Potentiometer log(Telemetry telemetry, HardwareMap hardwareMap) {
        telemetry.addData("Sensor", hardwareMap.getNamesOf(sensor).toArray()[0]);
        telemetry.addData("Voltage", getVoltage());
        telemetry.addData("Angle", getAngle());
        return this;
    }
}
