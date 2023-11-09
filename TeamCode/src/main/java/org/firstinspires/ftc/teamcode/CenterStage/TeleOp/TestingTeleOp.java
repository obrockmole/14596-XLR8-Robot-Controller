package org.firstinspires.ftc.teamcode.CenterStage.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Systems.Gamepad.GamepadButtons.Button;
import org.firstinspires.ftc.teamcode.Systems.Gamepad.GamepadButtons.Stick;
import org.firstinspires.ftc.teamcode.Systems.Gamepad.GamepadButtons.TouchpadFinger;
import org.firstinspires.ftc.teamcode.Systems.Gamepad.GamepadButtons.Trigger;

//@Disabled
@TeleOp(group = "TeleOp", name = "Testing TeleOp")
public class TestingTeleOp extends BaseTele {
    double forward = 0, rightward = 0, rotational = 0;

    public void loop() {
        /* DRIVER */
        forward = driver.getStickY(Stick.LEFT_STICK);
        rightward = driver.getStickX(Stick.LEFT_STICK);
        rotational = driver.getStickX(Stick.RIGHT_STICK);

        driver.onDown(Button.LEFT_BUMPER, () -> robot.setSpeedScale(0.5))
                .onDown(Button.RIGHT_BUMPER, () -> robot.setSpeedScale(1))
                .onUp(Button.LEFT_BUMPER, () -> robot.setSpeedScale(0.8))
                .onUp(Button.RIGHT_BUMPER, () -> robot.setSpeedScale(0.8));

        robot.standardDrive(forward, rightward, rotational);

        robot.winchExtension.setPower(driver.getTrigger(Trigger.RIGHT_TRIGGER) - driver.getTrigger(Trigger.LEFT_TRIGGER));
        driver.onDown(Button.A, () -> robot.winch.setPosition(1))
                .onUp(Button.A, () -> robot.winch.setPosition(0));

        /* MANIPULATOR */
        robot.intake.setPower(manipulator.getTrigger(Trigger.RIGHT_TRIGGER) - manipulator.getTrigger(Trigger.LEFT_TRIGGER));
        robot.lift.setPower(manipulator.getStickY(Stick.LEFT_STICK));

        manipulator.onPress(Button.DPAD_UP, () -> robot.liftAngle.setTargetPosition(robot.liftAngles[0]))
                        .onPress(Button.DPAD_DOWN, () -> robot.liftAngle.setTargetPosition(robot.liftAngles[1]))
                        .onPress(Button.DPAD_LEFT, () -> robot.liftAngle.setTargetPosition(robot.liftAngles[2]))
                        .onPress(Button.DPAD_RIGHT, () -> robot.liftAngle.setTargetPosition(robot.liftAngles[3]))
                        .onPress(Button.B, () -> robot.intakeGate.setPosition(1))
                        .onRelease(Button.B, () -> robot.intakeGate.setPosition(0))
                        .onPress(Button.X, () -> robot.droneAngle.setPosition(1))
                        .onRelease(Button.X, () -> robot.droneAngle.setPosition(0))
                        .onPress(Button.Y, () -> robot.droneLauncher.setPosition(1))
                        .onDown(Button.A, () -> robot.pixelDepositAngle.setPosition(1))
                        .onUp(Button.A, () -> robot.pixelDepositAngle.setPosition(0));

        if (robot.droneAngle.getTargetPosition() == 1) { //Zak'z big red button
            manipulator.setLEDColor(255, 0, 0, -1)
                    .rumble(-1)
                    .onPress(TouchpadFinger.FINGER_1, () -> robot.droneLauncher.setPosition(1));
        } else {
            manipulator.setLEDColor(0, 0, 0, -1)
                    .stopRumble();
        }

        update();
        log();
    }
}
