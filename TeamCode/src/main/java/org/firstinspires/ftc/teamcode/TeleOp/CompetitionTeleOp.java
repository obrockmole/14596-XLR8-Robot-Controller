package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Systems.Gamepad.GamepadButtons;

//@Disabled
@TeleOp(group = "TeleOp", name = "TeleOp")
public class CompetitionTeleOp extends BaseTele {
    public void loop() {
        robot.standardDrive(driver.getStickY(GamepadButtons.Stick.LEFT_STICK), driver.getStickX(GamepadButtons.Stick.LEFT_STICK), driver.getStickX(GamepadButtons.Stick.RIGHT_STICK));
        //robot.fieldCentricDrive(driver.getStickY(GamepadButtons.Stick.LEFT_STICK), driver.getStickX(GamepadButtons.Stick.LEFT_STICK), driver.getStickX(GamepadButtons.Stick.RIGHT_STICK));

        updateSystems();
        logSystems();
    }
}
