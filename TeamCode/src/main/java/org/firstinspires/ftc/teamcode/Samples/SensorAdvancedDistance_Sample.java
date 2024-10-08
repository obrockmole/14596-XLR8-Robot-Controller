package org.firstinspires.ftc.teamcode.Samples;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Systems.DataFilters.KalmanFilter;
import org.firstinspires.ftc.teamcode.Systems.DataFilters.MovingAverageFilter;
import org.firstinspires.ftc.teamcode.Systems.Sensors.AdvancedDistanceSensor;

@Disabled
@TeleOp(group = "Samples", name = "Advanced Distance Sensor Sample")
public class SensorAdvancedDistance_Sample extends OpMode {
    AdvancedDistanceSensor sensor;

    MovingAverageFilter movingAverageFilter;
    KalmanFilter kalmanFilter;

    @Override
    public void init() {
        movingAverageFilter = new MovingAverageFilter(5); //Initializing the moving average filter.
        kalmanFilter = new KalmanFilter(0, 0, 1, KalmanFilter.calculateKalmanGain(1, 0.1), 0, 0.01, 0.1); //Initializing the kalman filter.

        sensor = new AdvancedDistanceSensor(hardwareMap, "distance", movingAverageFilter, kalmanFilter); //Initializing the distance sensor with the hardware map, name of the sensor, and the filters.

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry()); //Initializing the telemetry.
    }

    @Override
    public void loop() {
        /*
            Log sensor data to telemetry and update the object.
         */
        sensor.log(telemetry, hardwareMap)
                .update();
    }
}
