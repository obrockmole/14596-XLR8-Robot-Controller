package org.firstinspires.ftc.teamcode.Samples;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Systems.Sensors.Potentiometer;

@Disabled
@TeleOp(group = "Samples")
public class SensorPotentiometer_Sample extends OpMode {
    Potentiometer sensor;

    @Override
    public void init() {
        sensor = new Potentiometer(hardwareMap, "potentiometer"); //Initializing the potentiometer with the hardware map and the name of the sensor
    }

    @Override
    public void loop() {
        /*
            Log sensor data to telemetry and update the object.
         */
        sensor.log(telemetry, hardwareMap);
    }
}
