package org.firstinspires.ftc.teamcode.CenterStage.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.CenterStage.Robot;
import org.firstinspires.ftc.teamcode.Systems.Gamepad.Gamepad;
import org.firstinspires.ftc.teamcode.Systems.Gamepad.GamepadButtons.Button;

//@Disabled
@TeleOp(group = "TeleOp", name = "Lift Testing")
public class LiftTest extends OpMode {
    Robot robot;
    Gamepad manipulator;

    public void init() {
        robot = new Robot(hardwareMap);
        robot.initialize();
        manipulator = new Gamepad(gamepad2);
    }

    public void loop() {
        manipulator.onPress(Button.DPAD_LEFT, () -> {
                    robot.currentArmStage = Robot.ArmStages.FOLDED;
                })
                .onPress(Button.DPAD_UP, () -> robot.lift.setTargetPosition(robot.liftPositions[1]))
                .onPress(Button.DPAD_RIGHT, () -> {
                    robot.currentArmStage = Robot.ArmStages.DEPLOYED;
                })
                .onPress(Button.DPAD_DOWN, () -> robot.lift.setTargetPosition(robot.liftPositions[0]))
                .onPress(Button.LEFT_BUMPER, () -> robot.liftDeployment.start())
                .onPress(Button.RIGHT_BUMPER, () -> robot.liftRetraction.start());

        manipulator.update();

        robot.update(false);
        robot.log(telemetry, false, false);
    }
}