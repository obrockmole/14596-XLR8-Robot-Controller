package org.firstinspires.ftc.teamcode.Samples;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Systems.Gamepad.Gamepad;
import org.firstinspires.ftc.teamcode.Systems.Gamepad.GamepadButtons.Trigger;

@Disabled
@TeleOp(group = "Samples", name = "Gamepad Rumble Control Sample")
public class GamepadRumble_Sample extends OpMode {
    Gamepad gamepad;

    @Override
    public void init() {
        gamepad = new Gamepad(gamepad1); //Initialize the gamepad
    }

    @Override
    public void loop() {
        /*
          Rumble the gamepad based on input from gamepad1. The -1 for duration mean indefinite.
         */
        gamepad.onPress(Trigger.LEFT_TRIGGER, () -> gamepad.rumble(1, 0, -1)) //Rumble the left side
                .onRelease(Trigger.LEFT_TRIGGER, () -> gamepad.stopRumble()) //Stop rumbling
                .onPress(Trigger.RIGHT_TRIGGER, () -> gamepad.rumble(0, 1, -1)) //Rumble the right side
                .onRelease(Trigger.RIGHT_TRIGGER, () -> gamepad.stopRumble()) //Stop rumbling
                .update();
    }
}
