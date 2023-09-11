package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.Systems.Gamepad.GamepadButtons;

//@Disabled
@TeleOp(group = "TeleOp", name = "Testing TeleOp")
public class TestingTeleOp extends BaseTele {
    double forward = 0, rightward = 0, rotational = 0;

    public void loop() {
        forward = driver.getStickY(GamepadButtons.Stick.LEFT_STICK);
        rightward = driver.getStickX(GamepadButtons.Stick.LEFT_STICK);
        rotational = driver.getStickX(GamepadButtons.Stick.RIGHT_STICK);

        if (inBackstage)
            forward = Range.clip(forward, 0, 1);

        robot.standardDrive(forward, rightward, rotational);
        //robot.fieldCentricDrive(forward, rightward, rotational);

        updateSystems();
        logSystems();
    }
}
