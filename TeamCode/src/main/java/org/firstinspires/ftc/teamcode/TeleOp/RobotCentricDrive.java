package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Systems.Gamepad.GamepadButtons.Stick;

//@Disabled
@TeleOp(group = "TeleOp")
public class RobotCentricDrive extends BaseTele {
    public void loop() {
        drivetrain.standardDrive(driver.getStickY(Stick.LEFT_STICK), driver.getStickX(Stick.LEFT_STICK), driver.getStickX(Stick.RIGHT_STICK))
                .update();
    }
}
