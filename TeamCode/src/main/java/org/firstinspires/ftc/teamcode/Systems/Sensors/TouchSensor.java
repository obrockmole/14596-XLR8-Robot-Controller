package org.firstinspires.ftc.teamcode.Systems.Sensors;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class TouchSensor {
    private com.qualcomm.robotcore.hardware.TouchSensor sensor;

    private boolean currentState, previousState;

    public TouchSensor(com.qualcomm.robotcore.hardware.TouchSensor sensor) {
        this.sensor = sensor;
    }

    public TouchSensor(HardwareMap hardwareMap, String name) {
        this(hardwareMap.get(com.qualcomm.robotcore.hardware.TouchSensor.class, name));
    }

    public com.qualcomm.robotcore.hardware.TouchSensor getSensor() {
        return sensor;
    }

    public TouchSensor setSensor(com.qualcomm.robotcore.hardware.TouchSensor sensor) {
        this.sensor = sensor;
        return this;
    }

    public TouchSensor setSensor(HardwareMap hardwareMap, String name) {
        return setSensor(hardwareMap.get(com.qualcomm.robotcore.hardware.TouchSensor.class, name));
    }

    public boolean isDown() {
        return sensor.isPressed();
    }

    public boolean isUp() {
        return !isDown();
    }

    public TouchSensor onDown(Runnable func) {
        if (isDown())
            func.run();
        return this;
    }

    public TouchSensor onUp(Runnable func) {
        if (isUp())
            func.run();
        return this;
    }

    public TouchSensor onPress(Runnable func) {
        if (!previousState && currentState)
            func.run();
        return this;
    }

    public TouchSensor onRelease(Runnable func) {
        if (previousState && !currentState)
            func.run();
        return this;
    }

    public TouchSensor onChange(Runnable func) {
        if (previousState != currentState)
            func.run();
        return this;
    }

    public double getValue() {
        return sensor.getValue();
    }

    public void update() {
        previousState = currentState;
        currentState = isDown();
    }

    public TouchSensor log(Telemetry telemetry, HardwareMap hardwareMap) {
        telemetry.addData("Sensor", hardwareMap.getNamesOf(sensor));
        telemetry.addData("Pressed", isDown());
        telemetry.addData("Value", getValue());
        return this;
    }
}
