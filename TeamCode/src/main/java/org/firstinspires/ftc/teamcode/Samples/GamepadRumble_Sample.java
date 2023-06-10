package org.firstinspires.ftc.teamcode.Samples;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Systems.Gamepad.Gamepad;
import org.firstinspires.ftc.teamcode.Systems.Gamepad.GamepadButtons.Button;
import org.firstinspires.ftc.teamcode.Systems.Gamepad.GamepadButtons.Trigger;

//@Disabled
@TeleOp(group = "Samples")
public class GamepadRumble_Sample extends OpMode {
    Gamepad gamepad;

    @Override
    public void init() {
        gamepad = new Gamepad(gamepad1);
    }

    @Override
    public void loop() {
        gamepad.onPress(Trigger.LEFT_TRIGGER, () -> {
                    if (!gamepad.isRumbling())
                        gamepad.rumble(1, 0, -1);
                })
                .onRelease(Trigger.LEFT_TRIGGER, () -> {
                    if (!gamepad.isRumbling())
                        gamepad.rumble(0, 0, -1);
                })
                .onPress(Trigger.RIGHT_TRIGGER, () -> {
                    if (!gamepad.isRumbling())
                        gamepad.rumble(0, 1, -1);
                })
                .onRelease(Trigger.RIGHT_TRIGGER, () -> {
                    if (!gamepad.isRumbling())
                        gamepad.rumble(0, 0, -1);
                })
                .onPress(Button.A, () -> gamepad.rumbleBlips(10))
                .onPress(Button.B, () -> gamepad.stopRumble())
                .update();
    }
}
