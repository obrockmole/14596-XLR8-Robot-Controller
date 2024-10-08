package org.firstinspires.ftc.teamcode.Samples;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Systems.Sensors.DistanceSensor;

@Disabled
@TeleOp(group = "Samples", name = "Distance Sensor Sample")
public class SensorDistance_Sample extends OpMode {
    DistanceSensor sensor;

    @Override
    public void init() {
        sensor = new DistanceSensor(hardwareMap, "distance"); //Initializing the distance sensor with the hardware map and the name of the sensor
    }

    @Override
    public void loop() {
        /*
            Log sensor data to telemetry and update the object.
         */
        sensor.log(telemetry, hardwareMap);
    }
}
