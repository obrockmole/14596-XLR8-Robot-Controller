package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Systems.Gamepad.GamepadButtons.Button;
import org.firstinspires.ftc.teamcode.Systems.Gamepad.GamepadButtons.Stick;

//@Disabled
@TeleOp(group = "Testing")
public class RobotClassTest extends NewBaseTele {
    public void loop() {
        robot.standardDrive(driver.getStickY(Stick.LEFT_STICK), driver.getStickX(Stick.LEFT_STICK), driver.getStickX(Stick.RIGHT_STICK));

        driver.onPress(Button.DPAD_LEFT, () -> robot.getOdometry().toggleServoRetraction(0))
                .onPress(Button.DPAD_RIGHT, () -> robot.getOdometry().toggleServoRetraction(1))
                .onPress(Button.DPAD_UP, () -> robot.getOdometry().toggleServoRetraction(2))
                .onPress(Button.A, () -> robot.getOdometry().extendAllPods())
                .onPress(Button.B, () -> robot.getOdometry().retractAllPods())
                .onPress(Button.X, () -> robot.getOdometry().toggleAllPods());
    }
}
