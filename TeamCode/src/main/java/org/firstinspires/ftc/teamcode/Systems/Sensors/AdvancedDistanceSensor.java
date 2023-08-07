package org.firstinspires.ftc.teamcode.Systems.Sensors;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.Systems.DataFilters.KalmanFilter;

public class AdvancedDistanceSensor {
    private com.qualcomm.robotcore.hardware.DistanceSensor sensor;
    private KalmanFilter filter;

    public AdvancedDistanceSensor(HardwareMap hardwareMap, String name, KalmanFilter filter) {
        this.sensor = hardwareMap.get(com.qualcomm.robotcore.hardware.DistanceSensor.class, name);
        this.filter = filter;
    }

    public double getDistance() {
        return (!outOfRange()) ? sensor.getDistance(DistanceUnit.CM) : -1;
    }

    public boolean outOfRange() {
        return getDistance() == DistanceUnit.infinity;
    }

    public AdvancedDistanceSensor log(Telemetry telemetry, HardwareMap hardwareMap) {
        telemetry.addData("Sensor", hardwareMap.getNamesOf(sensor).toArray()[0]);
        telemetry.addData("Measured Distance (cm)", getDistance());
        telemetry.addData("Filtered Distance (cm)", filter.getStateEstimate());
        return this;
    }

    public AdvancedDistanceSensor update() {
        filter.setMeasurement(getDistance())
                .update();
                //.updateSimplified();

        return this;
    }
}
