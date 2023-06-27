package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Systems.Gamepad.GamepadButtons;
import org.firstinspires.ftc.teamcode.Systems.Gamepad.GamepadButtons.Stick;

//@Disabled
@TeleOp(group = "TeleOp")
public class RobotCentricDrive_Touchpad extends BaseTele {
    public void loop() {
        drivetrain.standardDrive(driver.getFingerY(GamepadButtons.TouchpadFinger.FINGER_1), -driver.getFingerX(GamepadButtons.TouchpadFinger.FINGER_1), driver.getStickX(Stick.RIGHT_STICK))
                .update();
    }
}
