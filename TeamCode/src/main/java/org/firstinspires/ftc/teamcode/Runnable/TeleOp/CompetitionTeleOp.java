package org.firstinspires.ftc.teamcode.Runnable.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Runnable.Robot;
import org.firstinspires.ftc.teamcode.Systems.Gamepad.GamepadButtons.Button;
import org.firstinspires.ftc.teamcode.Systems.Gamepad.GamepadButtons.Stick;
import org.firstinspires.ftc.teamcode.Systems.Gamepad.GamepadButtons.Trigger;

//@Disabled
@TeleOp(group = "TeleOp", name = "TeleOp")
public class CompetitionTeleOp extends BaseTele {
    public void loop() {
        /* DRIVER */
        //Driving
        robot.standardDrive(driver.getStickY(Stick.LEFT_STICK), driver.getStickX(Stick.LEFT_STICK), driver.getStickX(Stick.RIGHT_STICK));

        //Driving speed, left bumper = slow
        if (driver.isDown(Button.LEFT_BUMPER)) robot.setSpeedScale(0.4);
        else robot.setSpeedScale(1);


        /* MANIPULATOR */
        //Arm positions, Dpad left = idle, Dpad right = deployed, Dpad up = half deployed, Dpad down = folded
        manipulator.onPress(Button.DPAD_LEFT, () -> robot.currentArmStage = Robot.ArmStages.IDLE)
                .onPress(Button.DPAD_RIGHT, () -> robot.currentArmStage = Robot.ArmStages.DEPLOYED)
                .onPress(Button.DPAD_UP, () -> robot.currentArmStage = Robot.ArmStages.DEPLOYED_LOW)
                .onPress(Button.DPAD_DOWN, () -> robot.currentArmStage = Robot.ArmStages.FOLDED);

        //Lift power, right stick y = lift power
        robot.lift.setTargetPower(-manipulator.getStickY(Stick.RIGHT_STICK));

        //Intake, left trigger = intake, right trigger = outtake
        robot.intake.setTargetPower(manipulator.getTrigger(Trigger.LEFT_TRIGGER) - manipulator.getTrigger(Trigger.RIGHT_TRIGGER) / 2);
        //Intake flippers, A button down = flip out, A button up = flip in
        manipulator.onChange(Button.X, () -> robot.intakeFlippers.toggleTargetPosition());

        //Pixel release, X button = toggle release
        manipulator.onPress(Button.A, () -> robot.pixelClamp.toggleTargetPosition());


        /* SHARED */
        //Drone launch and hang release, Guide = launch and release
        if (driver.isDown(Button.TOUCHPAD) && manipulator.isDown(Button.TOUCHPAD)) {
            robot.droneLauncher.setTargetPosition(1);
//            robot.hangRelease.setTargetPosition(1);
        }

        super.loop();
        log();
    }
}
