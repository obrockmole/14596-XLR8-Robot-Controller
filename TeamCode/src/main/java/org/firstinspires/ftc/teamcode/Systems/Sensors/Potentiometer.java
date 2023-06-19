package org.firstinspires.ftc.teamcode.Systems.Sensors;

import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

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

    public double getAngleRough() {
        return getVoltage() * 81.8;
    }

    public double getAngle() {
        double voltage = getVoltage();
        double angle;

        if (voltage == 0) angle = 270;
        else angle = ((540 * voltage) + 891 - Math.sqrt(Math.pow(874960 * voltage, 2) - (962280 * voltage) + 793881)) / (4 * voltage);

        return Range.scale(angle, 270, 0, 0, 270);
    }
}
