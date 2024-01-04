package org.firstinspires.ftc.teamcode.CenterStage.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Systems.Gamepad.Gamepad;
import org.firstinspires.ftc.teamcode.Systems.Gamepad.GamepadButtons;
import org.firstinspires.ftc.teamcode.Systems.Servos.NewPositionServoGroup;
import org.firstinspires.ftc.teamcode.Systems.Servos.PositionServo;

@TeleOp(group = "TeleOp", name = "New Position Servo Group Test Program")
public class NewPositionServoGroupTestProgram extends OpMode {
    NewPositionServoGroup intakeFlippers;
    Gamepad gamepad;

    public void init() {
        intakeFlippers = new NewPositionServoGroup(
                new PositionServo(hardwareMap, "leftIntakeFlipper", 0.12, 0.35, false),
                new PositionServo(hardwareMap, "rightIntakeFlipper", 0.12, 0.35, true)
        );

        gamepad = new Gamepad(gamepad1);
    }

    public void loop() {
        gamepad.onPress(GamepadButtons.Button.X, () -> intakeFlippers.toggleTargetPosition());

        intakeFlippers.update();
        gamepad.update();
    }
}
