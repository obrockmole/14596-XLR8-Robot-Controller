package org.firstinspires.ftc.teamcode.Samples;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Systems.Sensors.ColorSensor;

@Disabled
@TeleOp(group = "Samples", name = "Color Sensor Sample")
public class SensorColor_Sample extends OpMode {
    ColorSensor sensor;

    @Override
    public void init() {
        sensor = new ColorSensor(hardwareMap, "color"); //Initializing the color sensor with the hardware map and the name of the sensor
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
