package org.firstinspires.ftc.teamcode.Samples;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Systems.Gamepad.Gamepad;
import org.firstinspires.ftc.teamcode.Systems.Gamepad.GamepadButtons.Button;

@Disabled
@TeleOp(group = "Samples", name = "Gamepad LED Control Sample")
public class GamepadLED_Sample extends OpMode {
    Gamepad gamepad;

    @Override
    public void init() {
        gamepad = new Gamepad(gamepad1); //Initialize the gamepad
    }

    @Override
    public void loop() {
        /*
          Change the LED color of the gamepad based on input from gamepad1. The -1 for duration mean indefinite.
         */
        gamepad.onPress(Button.A, () -> gamepad.setLEDColor(0, 255, 0, -1)) //Set the LED color to green
                .onPress(Button.B, () -> gamepad.setLEDColor(255, 0, 0, -1)) //Set the LED color to red
                .onPress(Button.X, () -> gamepad.setLEDColor(0, 0, 255, -1)) //Set the LED color to blue
                .onPress(Button.Y, () -> gamepad.setLEDColor(200, 200, 0, -1)) //Set the LED color to yellow
                .update();
    }
}
