package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Systems.Gamepad.GamepadButtons;

//@Disabled
@TeleOp(group = "TeleOp", name = "Testing TeleOp")
public class TestingTeleOp extends BaseTele {
    public void loop() {
        robot.standardDrive(driver.getStickY(GamepadButtons.Stick.LEFT_STICK), driver.getStickX(GamepadButtons.Stick.LEFT_STICK), driver.getStickX(GamepadButtons.Stick.RIGHT_STICK));
        //robot.fieldCentricDrive(driver.getStickY(GamepadButtons.Stick.LEFT_STICK), driver.getStickX(GamepadButtons.Stick.LEFT_STICK), driver.getStickX(GamepadButtons.Stick.RIGHT_STICK));

        driver.onPress(GamepadButtons.Button.DPAD_LEFT, () -> robot.getOdometry().toggleServoRetraction(0))
                .onPress(GamepadButtons.Button.DPAD_RIGHT, () -> robot.getOdometry().toggleServoRetraction(1))
                .onPress(GamepadButtons.Button.DPAD_UP, () -> robot.getOdometry().toggleServoRetraction(2))
                .onPress(GamepadButtons.Button.A, () -> robot.getOdometry().extendAllPods())
                .onPress(GamepadButtons.Button.B, () -> robot.getOdometry().retractAllPods())
                .onPress(GamepadButtons.Button.X, () -> robot.getOdometry().toggleAllPods());

        updateSystems();
        logSystems();
    }
}
