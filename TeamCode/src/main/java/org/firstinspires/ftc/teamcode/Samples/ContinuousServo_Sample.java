package org.firstinspires.ftc.teamcode.Samples;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Systems.Gamepad.Gamepad;
import org.firstinspires.ftc.teamcode.Systems.Gamepad.GamepadButtons.Button;
import org.firstinspires.ftc.teamcode.Systems.Servos.ContinuousServo;

//@Disabled
@TeleOp(group = "Samples")
public class ContinuousServo_Sample extends OpMode {
    ContinuousServo servo;

    Gamepad driver; //This example uses a custom gamepad. See GamepadSample.java for more information

    double[] powers; //List of saved powers to switch between

    @Override
    public void init() {
        //Initializes custom gamepad.
        driver =  new Gamepad(gamepad1);

        servo = new ContinuousServo(hardwareMap, "contServo1", false); //Initializes servo with min position of 0, max position of 1

        powers = new double[]{0, 0.3, 0.7, 1};
        servo.setTargetPower(powers[0]);
    }

    @Override
    public void loop() {
        if (driver.onPress(Button.A))
            servo.setTargetPower(powers[0]); //Set servo power to 0
        else if (driver.onPress(Button.B))
            servo.setTargetPower(powers[1]); //Set servo power to 0.3
        else if (driver.onPress(Button.X))
            servo.setTargetPower(powers[2]); //Set servo power to 0.7
        else if (driver.onPress(Button.Y))
            servo.setTargetPower(powers[3]); //Set servo power to 1

        servo.update();
    }
}
