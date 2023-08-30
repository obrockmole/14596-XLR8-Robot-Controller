package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Systems.Gamepad.GamepadButtons;
import org.firstinspires.ftc.teamcode.Systems.Gamepad.GamepadButtons.Button;
import org.firstinspires.ftc.teamcode.Systems.Gamepad.GamepadButtons.Stick;

//@Disabled
@TeleOp(group = "TeleOp")
public class FieldCentricDrive_Touchpad extends BaseTele {
    public void loop() {
        driver.onPress(Button.BACK, () -> drivetrain.resetIMUYaw());

        drivetrain.fieldCentricDrive(driver.getFingerY(GamepadButtons.TouchpadFinger.FINGER_1), -driver.getFingerX(GamepadButtons.TouchpadFinger.FINGER_1), driver.getStickX(Stick.RIGHT_STICK))
                .update();
    }
}
