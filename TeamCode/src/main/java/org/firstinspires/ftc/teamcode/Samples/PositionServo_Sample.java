package org.firstinspires.ftc.teamcode.Samples;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Systems.Gamepad.Gamepad;
import org.firstinspires.ftc.teamcode.Systems.Gamepad.GamepadButtons.Button;
import org.firstinspires.ftc.teamcode.Systems.Servos.PositionServo;

@Disabled
@TeleOp(group = "Samples")
public class PositionServo_Sample extends OpMode {
    PositionServo servo;

    Gamepad driver; //This example uses a custom gamepad. See GamepadSample.java for more information

    double[] positions; //List of saved positions to switch between

    @Override
    public void init() {
        //Initializes custom gamepad.
        driver =  new Gamepad(gamepad1);

        servo = new PositionServo(hardwareMap, "posServo1", 0, 1, false); //Initializes servo with min position of 0, max position of 1

        positions = new double[]{0, 0.3, 0.7, 1};
        servo.setTargetPosition(positions[0]);
    }

    @Override
    public void loop() {
        driver.onPress(Button.A, () -> servo.setTargetPosition(positions[0])) //Set servo positions to 0
                .onPress(Button.B, () -> servo.setTargetPosition(positions[1])) //Set servo positions to 0.3
                .onPress(Button.X, () -> servo.setTargetPosition(positions[2])) //Set servo positions to 0.7
                .onPress(Button.Y, () -> servo.setTargetPosition(positions[3])) //Set servo positions to 1
                .update();

        servo.update();

        servo.log(telemetry, hardwareMap);
    }
}
