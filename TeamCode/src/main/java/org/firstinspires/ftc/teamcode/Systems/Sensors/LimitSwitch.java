package org.firstinspires.ftc.teamcode.Systems.Sensors;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class LimitSwitch {
    private com.qualcomm.robotcore.hardware.TouchSensor sensor;

    private boolean currentState, previousState;

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

    public boolean isDown() {
        return sensor.isPressed();
    }

    public boolean isUp() {
        return !isDown();
    }

    public LimitSwitch onDown(Runnable func) {
        if (isDown())
            func.run();
        return this;
    }

    public LimitSwitch onUp(Runnable func) {
        if (isUp())
            func.run();
        return this;
    }

    public LimitSwitch onPress(Runnable func) {
        if (!previousState && currentState)
            func.run();
        return this;
    }

    public LimitSwitch onRelease(Runnable func) {
        if (previousState && !currentState)
            func.run();
        return this;
    }

    public LimitSwitch onChange(Runnable func) {
        if (previousState != currentState)
            func.run();
        return this;
    }

    public double getValue() {
        return sensor.getValue();
    }

    public String getCSVData() {
        return String.format(",%s", ((isDown()) ? 1 : 0));
    }

    public void update() {
        previousState = currentState;
        currentState = isDown();
    }

    public LimitSwitch log(Telemetry telemetry, HardwareMap hardwareMap) {
        telemetry.addData("Sensor", hardwareMap.getNamesOf(sensor).toArray()[0]);
        telemetry.addData("Pressed", isDown());
        telemetry.addData("Value", getValue());
        return this;
    }
}
