package org.firstinspires.ftc.teamcode.CenterStage.Demos;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.CenterStage.Robot;
import org.firstinspires.ftc.teamcode.CenterStage.TeleOp.BaseTele;
import org.firstinspires.ftc.teamcode.Systems.Gamepad.GamepadButtons.Button;
import org.firstinspires.ftc.teamcode.Systems.Gamepad.GamepadButtons.Stick;

//@Disabled
@TeleOp(group = "Demo", name = "Finite State Machine Demo")
public class FSMDemo extends BaseTele {
    public void loop() {
        //Arm positions, left bumper = low, right bumper = high, Y = idle, B = folded
        manipulator.onPress(Button.LEFT_BUMPER, () -> robot.currentArmStage = Robot.ArmStages.DEPLOYED)
                .onPress(Button.RIGHT_BUMPER, () -> robot.currentArmStage = Robot.ArmStages.DEPLOYED_LOW)
                .onPress(Button.Y, () -> robot.currentArmStage = Robot.ArmStages.FOLDED)
                .onPress(Button.A, () -> robot.currentArmStage = Robot.ArmStages.IDLE);

        //Lift positions, Dpad left = normal deployment FSM, Dpad right = normal deployment FSM, X = slow deployment FSM, B = slow retraction FSM
        manipulator.onPress(Button.DPAD_LEFT, () -> stateMachines.deployment.start())
                .onPress(Button.DPAD_RIGHT, () -> stateMachines.retraction.start())
                .onPress(Button.X, () -> stateMachines.slowDeployment.start())
                .onPress(Button.B, () -> stateMachines.slowRetraction.start());

        //Lift power, right stick y = lift power
        if (Math.abs(manipulator.getStickY(Stick.RIGHT_STICK)) > 0.1) robot.setLiftPower(-manipulator.getStickY(Stick.RIGHT_STICK) * 0.8);

        super.loop();
        log();
    }
}
