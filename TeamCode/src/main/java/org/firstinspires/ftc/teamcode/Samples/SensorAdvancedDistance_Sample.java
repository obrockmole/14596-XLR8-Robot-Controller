package org.firstinspires.ftc.teamcode.Samples;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Systems.DataFilters.KalmanFilter;
import org.firstinspires.ftc.teamcode.Systems.Sensors.AdvancedDistanceSensor;

//@Disabled
@TeleOp(group = "Samples")
public class SensorAdvancedDistance_Sample extends OpMode {
    AdvancedDistanceSensor sensor;
    KalmanFilter filter;

    @Override
    public void init() {
        filter = new KalmanFilter(0, 0, 1, KalmanFilter.calculateKalmanGain(1, 0.1), 0, 0.01, 0.1); //Initializing the filter.
        sensor = new AdvancedDistanceSensor(hardwareMap, "distance", filter); //Initializing the distance sensor with the hardware map, name of the sensor, and the filter.
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
