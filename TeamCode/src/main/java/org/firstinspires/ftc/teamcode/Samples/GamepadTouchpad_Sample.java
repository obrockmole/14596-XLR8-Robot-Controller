package org.firstinspires.ftc.teamcode.Samples;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Systems.Gamepad.Gamepad;
import org.firstinspires.ftc.teamcode.Systems.Gamepad.GamepadButtons.TouchpadFinger;

@Disabled
@TeleOp(group = "Samples", name = "Gamepad Touchpad Sample")
public class GamepadTouchpad_Sample extends OpMode {
    Gamepad gamepad;

    int f1Pressed = 0, f1Released = 0, f1Changed = 0, f2Pressed = 0, f2Released = 0, f2Changed = 0;

    @Override
    public void init() {
        gamepad = new Gamepad(gamepad1); //Initialize the gamepad
    }

    @Override
    public void loop() {
        gamepad.onPress(TouchpadFinger.FINGER_1, () -> f1Pressed++) //Increment the number of times finger 1 was pressed
                .onRelease(TouchpadFinger.FINGER_1, () -> f1Released++) //Increment the number of times finger 1 was released
                .onChange(TouchpadFinger.FINGER_1, () -> f1Changed++) //Increment the number of times finger 1 changed

                .onPress(TouchpadFinger.FINGER_2, () -> f2Pressed++) //Increment the number of times finger 2 was pressed
                .onRelease(TouchpadFinger.FINGER_2, () -> f2Released++) //Increment the number of times finger 2 was released
                .onChange(TouchpadFinger.FINGER_2, () -> f2Changed++) //Increment the number of times finger 2 changed
                .update();

        //Telemetry to display finger 1 changes and values
        telemetry.addLine("Finger 1: " + gamepad.isDown(TouchpadFinger.FINGER_1));
        telemetry.addLine("Finger 1 Pressed: " + f1Pressed);
        telemetry.addLine("Finger 1 Released: " + f1Released);
        telemetry.addLine("Finger 1 Changed: " + f1Changed);
        telemetry.addLine("Finger 1 Position: " + gamepad.getFingerPosition(TouchpadFinger.FINGER_1)); //Current position of finger 1
        telemetry.addLine("Finger 1 Position Delta: " + gamepad.getFingerPositionDelta(TouchpadFinger.FINGER_1)); //Change in position of finger 1 since last update

        telemetry.addLine();
        telemetry.addLine("----------------------------------------");
        telemetry.addLine();

        //Telemetry to display finger 2 changes and values
        telemetry.addLine("Finger 2: " + gamepad.isDown(TouchpadFinger.FINGER_2));
        telemetry.addLine("Finger 2 Pressed: " + f2Pressed);
        telemetry.addLine("Finger 2 Released: " + f2Released);
        telemetry.addLine("Finger 2 Changed: " + f2Changed);
        telemetry.addLine("Finger 2 Position: " + gamepad.getFingerPosition(TouchpadFinger.FINGER_2)); //Current position of finger 2
        telemetry.addLine("Finger 2 Position Delta: " + gamepad.getFingerPositionDelta(TouchpadFinger.FINGER_2)); //Change in position of finger 2 since last update
    }
}
