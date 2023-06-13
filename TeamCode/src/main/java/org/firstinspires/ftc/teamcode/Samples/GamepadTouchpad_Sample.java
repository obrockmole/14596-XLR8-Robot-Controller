package org.firstinspires.ftc.teamcode.Samples;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Systems.Gamepad.Gamepad;
import org.firstinspires.ftc.teamcode.Systems.Gamepad.GamepadButtons.Button;
import org.firstinspires.ftc.teamcode.Systems.Gamepad.GamepadButtons.TouchpadFinger;

//@Disabled
@TeleOp(group = "Samples")
public class GamepadTouchpad_Sample extends OpMode {
    Gamepad gamepad;

    int f1Pressed = 0, f1Released = 0, f1Changed = 0, f2Pressed = 0, f2Released = 0, f2Changed = 0;

    @Override
    public void init() {
        gamepad = new Gamepad(gamepad1);
    }

    @Override
    public void loop() {
        gamepad.onPress(TouchpadFinger.FINGER_1, () -> f1Pressed++)
                .onPress(TouchpadFinger.FINGER_2, () -> f2Pressed++)
                .onRelease(TouchpadFinger.FINGER_1, () -> f1Released++)
                .onRelease(TouchpadFinger.FINGER_2, () -> f2Released++)
                .onChange(TouchpadFinger.FINGER_1, () -> f1Changed++)
                .onChange(TouchpadFinger.FINGER_2, () -> f2Changed++)
                .update();

        //Telemetry to display gamepad changes and values
        telemetry.addLine("Touchpad: " + gamepad.isDown(Button.TOUCHPAD));

        telemetry.addLine();
        telemetry.addLine("----------------------------------------");
        telemetry.addLine();

        telemetry.addLine("Finger 1: " + gamepad.isDown(TouchpadFinger.FINGER_1));
        telemetry.addLine("Finger 1 Pressed: " + f1Pressed);
        telemetry.addLine("Finger 1 Released: " + f1Released);
        telemetry.addLine("Finger 1 Changed: " + f1Changed);
        telemetry.addLine("Finger 1 Position: " + gamepad.getFingerX(TouchpadFinger.FINGER_1) + ", " + gamepad.getFingerY(TouchpadFinger.FINGER_1));
        telemetry.addLine("Finger 1 Position Delta: " + gamepad.getFingerXDelta(TouchpadFinger.FINGER_1) + ", " + gamepad.getFingerYDelta(TouchpadFinger.FINGER_1));

        telemetry.addLine();
        telemetry.addLine("----------------------------------------");
        telemetry.addLine();

        telemetry.addLine("Finger 2: " + gamepad.isDown(TouchpadFinger.FINGER_2));
        telemetry.addLine("Finger 2 Pressed: " + f2Pressed);
        telemetry.addLine("Finger 2 Released: " + f2Released);
        telemetry.addLine("Finger 2 Changed: " + f2Changed);
        telemetry.addLine("Finger 2 Position: " + gamepad.getFingerX(TouchpadFinger.FINGER_2) + ", " + gamepad.getFingerY(TouchpadFinger.FINGER_2));
        telemetry.addLine("Finger 2 Position Delta: " + gamepad.getFingerXDelta(TouchpadFinger.FINGER_2) + ", " + gamepad.getFingerYDelta(TouchpadFinger.FINGER_2));
    }
}
