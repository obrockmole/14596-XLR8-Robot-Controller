package org.firstinspires.ftc.teamcode.Samples;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Systems.Gamepad.Gamepad;
import org.firstinspires.ftc.teamcode.Systems.Gamepad.GamepadButtons.Button;
import org.firstinspires.ftc.teamcode.Systems.Servos.ContinuousServo;

@Disabled
@TeleOp(group = "Samples")
public class ContinuousServo_Sample extends OpMode {
    ContinuousServo servo;

    Gamepad gamepad; //This example uses a custom gamepad. See Gamepad_Sample.java for more information

    double[] powers; //List of saved powers to switch between

    @Override
    public void init() {
        //Initializes custom gamepad.
        gamepad = new Gamepad(gamepad1);

        servo = new ContinuousServo(hardwareMap, "contServo1", false); //Initializes a continuous servo

        powers = new double[]{0, 0.3, 0.7, 1}; //Define list of powers to switch between
        servo.setTargetPower(powers[0]); //Set initial servo power to 0
    }

    @Override
    public void loop() {
        /*
          Change the state of servos based on input from gamepad1.
         */
        gamepad.onPress(Button.A, () -> servo.setTargetPower(powers[0])) //Set servo power to 0
                .onPress(Button.B, () -> servo.setTargetPower(powers[1])) //Set servo power to 0.3
                .onPress(Button.X, () -> servo.setTargetPower(powers[2])) //Set servo power to 0.7
                .onPress(Button.Y, () -> servo.setTargetPower(powers[3])) //Set servo power to 1
                .update();

        /*
          Log servo data to telemetry and update the object.
         */
        servo.log(telemetry, hardwareMap)
                .update();
    }
}
