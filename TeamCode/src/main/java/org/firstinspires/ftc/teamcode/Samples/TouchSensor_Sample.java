package org.firstinspires.ftc.teamcode.Samples;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Systems.Sensors.TouchSensor;

@Disabled
@TeleOp(group = "Samples")
public class TouchSensor_Sample extends OpMode {
    TouchSensor sensor;

    int pressed = 0, released = 0, changed = 0;

    @Override
    public void init() {
        sensor = new TouchSensor(hardwareMap, "touch"); //Initializing the touch sensor with the hardware map and the name of the sensor
    }

    @Override
    public void loop() {
        //Count sensor state changes
        sensor.onPress(() -> pressed++)
                .onRelease(() -> released++)
                .onChange(() -> changed++)
                .update();

        //Telemetry to display sensor changes and values
        telemetry.addData("Is Pressed", sensor.isDown());
        telemetry.addData("Times Pressed", pressed);
        telemetry.addData("Times Released", released);
        telemetry.addData("Times Changed", changed);
    }
}
