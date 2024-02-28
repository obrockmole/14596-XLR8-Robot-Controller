package org.firstinspires.ftc.teamcode.Systems.Sensors;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.Systems.DataFilters.KalmanFilter;
import org.firstinspires.ftc.teamcode.Systems.DataFilters.MovingAverageFilter;

public class AdvancedDistanceSensor {
    private final com.qualcomm.robotcore.hardware.DistanceSensor sensor;

    private final MovingAverageFilter movingAverageFilter;
    private final KalmanFilter kalmanFilter;

    public AdvancedDistanceSensor(HardwareMap hardwareMap, String name, MovingAverageFilter movingAverageFilter, KalmanFilter kalmanFilter) {
        this.sensor = hardwareMap.get(com.qualcomm.robotcore.hardware.DistanceSensor.class, name);
        this.movingAverageFilter = movingAverageFilter;
        this.kalmanFilter = kalmanFilter;
    }

    public double getDistance() {
        return (!outOfRange()) ? sensor.getDistance(DistanceUnit.MM) : -1;
    }

    public boolean outOfRange() {
        return sensor.getDistance(DistanceUnit.MM) == DistanceUnit.infinity;
    }

    public AdvancedDistanceSensor update() {
        movingAverageFilter.update(getDistance());

        kalmanFilter.setMeasurement(getDistance())
                .update();
        //.updateSimplified();

        return this;
    }

    public AdvancedDistanceSensor log(Telemetry telemetry, HardwareMap hardwareMap) {
        telemetry.addData("Sensor", hardwareMap.getNamesOf(sensor).toArray()[0]);
        telemetry.addData("Measured Distance (cm)", getDistance());
        telemetry.addData("MAF Distance (cm)", movingAverageFilter.getStateEstimate());
        telemetry.addData("KF Distance (cm)", kalmanFilter.getStateEstimate());
        return this;
    }
}
