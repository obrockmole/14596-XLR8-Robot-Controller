package org.firstinspires.ftc.teamcode.Samples;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Systems.DataFilters.KalmanFilter;
import org.firstinspires.ftc.teamcode.Systems.Sensors.DistanceSensor;

/*FIXME*/
/* WARNING: UNTESTED, MAY NOT WORK */
@Disabled
@TeleOp(group = "Samples", name = "Kalman Filter Sample")
public class KalmanFilter_Sample extends OpMode {
    DistanceSensor sensor;

    KalmanFilter kalmanFilter;

    @Override
    public void init() {
        kalmanFilter = new KalmanFilter(0, 0, 1, KalmanFilter.calculateKalmanGain(1, 0.1), 0, 0.01, 0.1); //Initializing the kalman filter.

        sensor = new DistanceSensor(hardwareMap, "distance"); //Initializing the distance sensor with the hardware map and name of the sensor.
    }

    @Override
    public void loop() {
        kalmanFilter.setMeasurement(sensor.getDistanceCM()) //Setting the measurement of the kalman filter.
                .update(); //Update the kalman filter.

        /*
            Log sensor data to telemetry.
         */
        telemetry.addData("Measured Distance (cm)", sensor.getDistanceCM());
        telemetry.addData("Estimated Distance (cm)", kalmanFilter.getStateEstimate());
        telemetry.update();
    }
}
