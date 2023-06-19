package org.firstinspires.ftc.teamcode.Systems.Sensors;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class LimitSwitch {
    private com.qualcomm.robotcore.hardware.TouchSensor sensor;

    public LimitSwitch(com.qualcomm.robotcore.hardware.TouchSensor sensor) {
        this.sensor = sensor;
    }

    public LimitSwitch(HardwareMap hardwareMap, String name) {
        this(hardwareMap.get(com.qualcomm.robotcore.hardware.TouchSensor.class, name));
    }

    public com.qualcomm.robotcore.hardware.TouchSensor getSensor() {
        return sensor;
    }

    public LimitSwitch setSensor(com.qualcomm.robotcore.hardware.TouchSensor sensor) {
        this.sensor = sensor;
        return this;
    }

    public LimitSwitch setSensor(HardwareMap hardwareMap, String name) {
        return setSensor(hardwareMap.get(com.qualcomm.robotcore.hardware.TouchSensor.class, name));
    }

    public boolean isPressed() {
        return sensor.isPressed();
    }

    public double getValue() {
        return sensor.getValue();
    }

    public LimitSwitch log(Telemetry telemetry, HardwareMap hardwareMap) {
        telemetry.addData("Sensor", hardwareMap.getNamesOf(sensor));
        telemetry.addData("Pressed", isPressed());
        telemetry.addData("Value", getValue());
        return this;
    }
}
