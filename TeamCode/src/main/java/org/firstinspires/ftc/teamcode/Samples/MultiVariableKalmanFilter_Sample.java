package org.firstinspires.ftc.teamcode.Samples;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Systems.DataFilters.KalmanFilter;
import org.firstinspires.ftc.teamcode.Systems.DataFilters.MovingAverageFilter;
import org.firstinspires.ftc.teamcode.Systems.DataFilters.MultiVariableKalmanFilter;
import org.firstinspires.ftc.teamcode.Systems.Sensors.AdvancedDistanceSensor;
import org.firstinspires.ftc.teamcode.Systems.Sensors.DistanceSensor;

//@Disabled
@TeleOp(group = "Samples")
public class MultiVariableKalmanFilter_Sample extends OpMode {
    DistanceSensor sensor1, sensor2;

    MultiVariableKalmanFilter multiVariableKalmanFilter;

    @Override
    public void init() {
        multiVariableKalmanFilter = new MultiVariableKalmanFilter(new double[][]{{0}, {0}}, new double[][]{{0}, {0}}, new double[][]{{0.1}}, new double[][]{{0}}, new double[][]{{0}}, new double[][]{{0.1}}, new double[][]{{1}}, new double[][]{{1}}, new double[][]{{1}}, new double[][]{{1}}, new double[][]{{1}}); //Initializing the multi-variable kalman filter.

        sensor1 = new DistanceSensor(hardwareMap, "distance"); //Initializing the distance sensor with the hardware map and name of the sensor.
        sensor2 = new DistanceSensor(hardwareMap, "distance2"); //Initializing the distance sensor with the hardware map and name of the sensor.
    }

    @Override
    public void loop() {
        multiVariableKalmanFilter.setMeasurements(new double[][]{{sensor1.getDistanceCM()}, {sensor2.getDistanceCM()}}) //Setting the measurements of the kalman filter.
                .update();

        /*
            Log sensor data to telemetry and update the object.
         */
        telemetry.addData("S1 - Measured Distance (cm)", sensor1.getDistanceCM());
        telemetry.addData("S1 - Estimated Distance (cm)", multiVariableKalmanFilter.getStateEstimates()[0][0]);

        telemetry.addData("S2 - Measured Distance (cm)", sensor2.getDistanceCM());
        telemetry.addData("S2 - Estimated Distance (cm)", multiVariableKalmanFilter.getStateEstimates()[1][0]);
        telemetry.update();
    }
}
