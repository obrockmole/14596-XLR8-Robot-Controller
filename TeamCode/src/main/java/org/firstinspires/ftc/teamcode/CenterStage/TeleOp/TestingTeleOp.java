package org.firstinspires.ftc.teamcode.CenterStage.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.CenterStage.Robot;
import org.firstinspires.ftc.teamcode.Systems.Gamepad.GamepadButtons.Button;
import org.firstinspires.ftc.teamcode.Systems.Gamepad.GamepadButtons.Stick;
import org.firstinspires.ftc.teamcode.Systems.Gamepad.GamepadButtons.Trigger;

//@Disabled
@TeleOp(group = "TeleOp", name = "Testing TeleOp")
public class TestingTeleOp extends BaseTele {
    public void loop() {
        /* DRIVER */
        //Driving
        robot.standardDrive(driver.getStickY(Stick.LEFT_STICK), driver.getStickX(Stick.LEFT_STICK), driver.getStickX(Stick.RIGHT_STICK));

        //Driving speed, left bumper = slow
        if (driver.isDown(Button.LEFT_BUMPER)) robot.setSpeedScale(0.4);
        else robot.setSpeedScale(1);


        /* MANIPULATOR */
        //Arm positions, left bumper = low, right bumper = high, B = idle, Y = folded
        manipulator.onPress(Button.LEFT_BUMPER, () -> robot.currentArmStage = Robot.ArmStages.DEPLOYED)
                .onPress(Button.RIGHT_BUMPER, () -> robot.currentArmStage = Robot.ArmStages.DEPLOYED_LOW)
                .onPress(Button.B, () -> robot.currentArmStage = Robot.ArmStages.IDLE)
                .onPress(Button.Y, () -> robot.currentArmStage = Robot.ArmStages.FOLDED);

        //Lift positions, Dpad left = just out, Dpad right = fully up, Dpad up = halfway up, Dpad down = down
        manipulator.onPress(Button.DPAD_LEFT, () -> robot.setLiftPosition(robot.liftPositions[1]))
                .onPress(Button.DPAD_RIGHT, () -> robot.setLiftPosition(robot.liftPositions[3]))
                .onPress(Button.DPAD_UP, () -> robot.setLiftPosition(robot.liftPositions[2]))
                .onPress(Button.DPAD_DOWN, () -> robot.setLiftPosition(robot.liftPositions[0]));

        //Lift power, right stick y = lift power
        if (Math.abs(manipulator.getStickY(Stick.RIGHT_STICK)) > 0.1) robot.setLiftPower(-manipulator.getStickY(Stick.RIGHT_STICK));

        //Intake, left trigger = intake, right trigger = outtake
        robot.intake.setTargetPower(manipulator.getTrigger(Trigger.LEFT_TRIGGER) - manipulator.getTrigger(Trigger.RIGHT_TRIGGER) / 2.5);
        //Intake flippers, A button down = flip out, A button up = flip in
        manipulator.onChange(Button.X, () -> robot.intakeFlippers.toggleTargetPosition());

        //Pixel release, X button = toggle release
        manipulator.onPress(Button.A, () -> robot.pixelClamp.toggleTargetPosition());


        /* SHARED */
        //Drone launch and hang release, Guide = launch and release
        if (driver.isDown(Button.TOUCHPAD) && manipulator.isDown(Button.TOUCHPAD)) {
            robot.droneLauncher.setTargetPosition(1);
            robot.hangRelease.setTargetPosition(1);
        }

        super.loop();
        log();
    }
}
