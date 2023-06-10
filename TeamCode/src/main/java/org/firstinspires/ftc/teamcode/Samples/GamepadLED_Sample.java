package org.firstinspires.ftc.teamcode.Samples;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Systems.Gamepad.Gamepad;
import org.firstinspires.ftc.teamcode.Systems.Gamepad.GamepadButtons.Button;

//@Disabled
@TeleOp(group = "Samples")
public class GamepadLED_Sample extends OpMode {
    Gamepad gamepad;

    @Override
    public void init() {
        gamepad = new Gamepad(gamepad1);
    }

    @Override
    public void loop() {
        gamepad.onPress(Button.A, () -> gamepad.setLEDColor(0, 255, 0, -1))
                .onPress(Button.B, () -> gamepad.setLEDColor(255, 0, 0, -1))
                .onPress(Button.X, () -> gamepad.setLEDColor(0, 0, 255, -1))
                .onPress(Button.Y, () -> gamepad.setLEDColor(200, 200, 0, -1))
                .update();
    }
}
