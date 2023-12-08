package org.firstinspires.ftc.teamcode.CenterStage.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.Systems.Gamepad.GamepadButtons.Button;
import org.firstinspires.ftc.teamcode.Systems.Gamepad.GamepadButtons.Stick;
import org.firstinspires.ftc.teamcode.Systems.Gamepad.GamepadButtons.TouchpadFinger;
import org.firstinspires.ftc.teamcode.Systems.Gamepad.GamepadButtons.Trigger;

//@Disabled
@TeleOp(group = "TeleOp", name = "Testing TeleOp")
public class TestingTeleOp extends BaseTele {
    public void loop() {
        /*

        // DRIVER \\
        //Driving
        robot.standardDrive(driver.getStickY(Stick.LEFT_STICK), driver.getStickX(Stick.LEFT_STICK), driver.getStickX(Stick.RIGHT_STICK));

        //Driving speed, left bumper = slow, right bumper = fast
        robot.setSpeedScale(driver.isDown(Button.LEFT_BUMPER), driver.isDown(Button.RIGHT_BUMPER));

        //Winch Extension, right trigger = extend, left trigger = retract
        robot.winchExtension.setTargetPower(driver.getTrigger(Trigger.RIGHT_TRIGGER) - driver.getTrigger(Trigger.LEFT_TRIGGER));
        //Actuate winch, button down = open, button up = close
        if (driver.isDown(Button.A)) robot.winch.setPosition(1);
        else robot.winch.setPosition(0);

        //Drone launcher angle, toggle between down and up with X
        driver.onPress(Button.X, () -> robot.droneAngle.setTargetPosition(robot.droneAngle.getTargetPosition() == 1 ? 0 : 1));


        // MANIPULATOR \\
        //Intake, right trigger = intake, left trigger = outtake
        robot.intake.setTargetPower(manipulator.getTrigger(Trigger.LEFT_TRIGGER) - manipulator.getTrigger(Trigger.RIGHT_TRIGGER));

        //Manual lift control, stick up = extend, stick down = retract
        robot.setLiftPower(manipulator.getStickY(Stick.LEFT_STICK));
        //Manual lift angle control, stick up = angle up, stick down = angle down
        robot.setLiftAnglePower(manipulator.getStickY(Stick.RIGHT_STICK));

        //Intake gate, button down = up, button up = down
        if (manipulator.isDown(Button.RIGHT_BUMPER)) robot.intakeGate.setTargetPosition(1);
        else robot.intakeGate.setTargetPosition(0);

        //Pixel depositor, button down = open, button up = closed
        if (manipulator.isDown(Button.LEFT_BUMPER)) robot.pixelDepositSlide.setTargetPosition(1);
        else robot.pixelDepositSlide.setTargetPosition(0);

        //Pre-programmed lift positions, dpad-down = down, dpad-left = short, dpad-up = medium, dpad-right = max
        manipulator.onPress(Button.DPAD_UP, () -> robot.setLiftPosition(0))
                .onPress(Button.DPAD_DOWN, () -> robot.setLiftPosition(1))
                .onPress(Button.DPAD_LEFT, () -> robot.setLiftPosition(2))
                .onPress(Button.DPAD_RIGHT, () -> robot.setLiftPosition(3))
                .onPress(Button.LEFT_STICK, () -> robot.setLiftPosition(0))

                //Pre-programmed lift angles, A = ___, X = ___, Y = ___, B = ___
                .onDown(Button.A, () -> robot.setLiftAnglePosition(0))
                .onPress(Button.X, () -> robot.setLiftAnglePosition(1))
                .onDown(Button.Y, () -> robot.setLiftAnglePosition(2))
                .onDown(Button.B, () -> robot.setLiftAnglePosition(3))
                .onDown(Button.RIGHT_STICK, () -> robot.setLiftAnglePosition(0))

                //Auto score
                .onPress(Button.START, () -> scoringMachine.start());


        //Zak's big red button
        if (robot.droneAngle.getTargetPosition() == 1) {
            driver.setLEDColor(255, 0, 0, -1)
                    .onPress(TouchpadFinger.FINGER_1, () -> robot.droneLauncher.setPosition(1));

            manipulator.setLEDColor(255, 0, 0, -1)
                    .onPress(TouchpadFinger.FINGER_1, () -> robot.droneLauncher.setPosition(1));
        } else {
            driver.setLEDColor(0, 0, 0, -1);
            manipulator.setLEDColor(0, 0, 0, -1);
        }

        //Automatically adjust pixel deposit angle based on lift angle
        robot.pixelDepositAngle.setTargetPosition(Range.scale(robot.liftAngle.getCurrentPosition(0), robot.liftAngles[0], robot.liftAngles[robot.liftAngles.length - 1], 0, 1));

        update();
        log();

        */
    }
}
