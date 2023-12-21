package org.firstinspires.ftc.teamcode.CenterStage.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Systems.Gamepad.GamepadButtons.Button;
import org.firstinspires.ftc.teamcode.Systems.Gamepad.GamepadButtons.Stick;
import org.firstinspires.ftc.teamcode.Systems.Gamepad.GamepadButtons.Trigger;

//@Disabled
@TeleOp(group = "TeleOp", name = "TeleOp")
public class CompetitionTeleOp extends BaseTele {
    public void loop() {
        /* DRIVER */
        //Driving
        robot.standardDrive(-driver.getStickY(Stick.LEFT_STICK), -driver.getStickX(Stick.LEFT_STICK), driver.getStickX(Stick.RIGHT_STICK));

        //Driving speed, left bumper = slow, right bumper = fast
        robot.setSpeedScale(driver.isDown(Button.LEFT_BUMPER));

        /* MANIPULATOR */
        //Intake, left trigger = intake, right trigger = outtake
        robot.intake.setTargetPower(manipulator.getTrigger(Trigger.LEFT_TRIGGER) - manipulator.getTrigger(Trigger.RIGHT_TRIGGER) / 2.5);

        update();
        log();
    }
}
