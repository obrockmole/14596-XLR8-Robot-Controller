package org.firstinspires.ftc.teamcode.Samples;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Systems.Gamepad.Gamepad;
import org.firstinspires.ftc.teamcode.Systems.Sensors.ColorSensor;

//@Disabled
@TeleOp(group = "Samples")
public class SensorColor_Sample extends OpMode {
    Gamepad gamepad; //This example uses a custom gamepad. See Gamepad_Sample.java for more information

    ColorSensor sensor;

    @Override
    public void init() {
        sensor = new ColorSensor(hardwareMap, "color"); //Initializing the color sensor with the hardware map and the name of the sensor

        gamepad = new Gamepad(gamepad1); //Initialize the gamepad
    }

    @Override
    public void loop() {
        gamepad.setLEDColor(sensor.getColor(), -1); //Set the color of the gamepad LEDs to the color of the sensor

        /*
            Log sensor data to telemetry and update the object.
         */
        sensor.log(telemetry, hardwareMap)
                .update();
    }
}
