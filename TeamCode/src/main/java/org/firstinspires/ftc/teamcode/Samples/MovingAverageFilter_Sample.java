package org.firstinspires.ftc.teamcode.Samples;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Systems.DataFilters.KalmanFilter;
import org.firstinspires.ftc.teamcode.Systems.DataFilters.MovingAverageFilter;
import org.firstinspires.ftc.teamcode.Systems.Sensors.DistanceSensor;

/*FIXME*/
/* WARNING: UNTESTED, MAY NOT WORK */
@Disabled
@TeleOp(group = "Samples", name = "Moving Average Filter Sample")
public class MovingAverageFilter_Sample extends OpMode {
    DistanceSensor sensor;

    MovingAverageFilter movingAverageFilter;

    @Override
    public void init() {
        movingAverageFilter = new MovingAverageFilter(5); //Initializing the moving average filter.

        sensor = new DistanceSensor(hardwareMap, "distance"); //Initializing the distance sensor with the hardware map and name of the sensor.
    }

    @Override
    public void loop() {
        movingAverageFilter.update(sensor.getDistanceCM()); //Setting the measurement of the moving average filter and updating the filter.

        /*
            Log sensor data to telemetry.
         */
        telemetry.addData("Measured Distance (cm)", sensor.getDistanceCM());
        telemetry.addData("Estimated Distance (cm)", movingAverageFilter.getStateEstimate());
        telemetry.update();
    }
}
