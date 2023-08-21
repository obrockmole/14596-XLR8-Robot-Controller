package org.firstinspires.ftc.teamcode.Systems.Sensors;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.Systems.DataFilters.KalmanFilter;
import org.firstinspires.ftc.teamcode.Systems.DataFilters.MovingAverageFilter;
import org.firstinspires.ftc.teamcode.Systems.DataFilters.MultiVariableKalmanFilter;

public class AdvancedDistanceSensor {
    private com.qualcomm.robotcore.hardware.DistanceSensor sensor;

    private MovingAverageFilter movingAverageFilter;
    private KalmanFilter kalmanFilter;

    public AdvancedDistanceSensor(HardwareMap hardwareMap, String name, MovingAverageFilter movingAverageFilter, KalmanFilter kalmanFilter) {
        this.sensor = hardwareMap.get(com.qualcomm.robotcore.hardware.DistanceSensor.class, name);
        this.movingAverageFilter = movingAverageFilter;
        this.kalmanFilter = kalmanFilter;
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
        telemetry.addData("MAF Distance (cm)", movingAverageFilter.getStateEstimate());
        telemetry.addData("KF Distance (cm)", kalmanFilter.getStateEstimate());
        return this;
    }

    public AdvancedDistanceSensor update() {
        movingAverageFilter.update(getDistance());

        kalmanFilter.setMeasurement(getDistance())
                .update();
                //.updateSimplified();

        return this;
    }
}
