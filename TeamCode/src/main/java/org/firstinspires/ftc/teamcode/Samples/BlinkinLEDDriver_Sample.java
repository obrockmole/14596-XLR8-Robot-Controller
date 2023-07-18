package org.firstinspires.ftc.teamcode.Samples;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Systems.BlinkinLEDDriver;
import org.firstinspires.ftc.teamcode.Systems.BlinkinLEDDriver.Pattern;
import org.firstinspires.ftc.teamcode.Systems.Gamepad.Gamepad;
import org.firstinspires.ftc.teamcode.Systems.Gamepad.GamepadButtons.Button;

@Disabled
@TeleOp(group = "Samples")
public class BlinkinLEDDriver_Sample extends OpMode {
    Gamepad gamepad; //This example uses a custom gamepad. See Gamepad_Sample.java for more information

    BlinkinLEDDriver blinkin;

    @Override
    public void init() {
        gamepad = new Gamepad(gamepad1); //Initialize the gamepad

        blinkin = new BlinkinLEDDriver(hardwareMap, "blinkin"); //Initialize the blinkin
    }

    @Override
    public void loop() {
        /*
          Change the LED color based on input from gamepad1.
         */
        gamepad.onPress(Button.A, () -> blinkin.setPattern(Pattern.GREEN)) //Set the LED color to green
                .onPress(Button.B, () -> blinkin.setPattern(Pattern.RED)) //Set the LED color to red
                .onPress(Button.X, () -> blinkin.setPattern(Pattern.BLUE)) //Set the LED color to blue
                .onPress(Button.Y, () -> blinkin.setPattern(Pattern.YELLOW)) //Set the LED color to yellow

                .onPress(Button.LEFT_BUMPER, () -> blinkin.previousPattern()) //Set the LED color to the previous pattern
                .onPress(Button.RIGHT_BUMPER, () -> blinkin.nextPattern()) //Set the LED color to the next pattern
                .update();

        blinkin.log(telemetry, hardwareMap);
    }
}
