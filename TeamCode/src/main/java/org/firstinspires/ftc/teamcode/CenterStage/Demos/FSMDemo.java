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
        /* MANIPULATOR */
        //Arm positions, left bumper = low, right bumper = high, B = idle
        manipulator.onPress(Button.LEFT_BUMPER, () -> robot.currentArmStage = Robot.ArmStages.DEPLOYED)
                .onPress(Button.RIGHT_BUMPER, () -> robot.currentArmStage = Robot.ArmStages.DEPLOYED_LOW)
                .onPress(Button.B, () -> robot.currentArmStage = Robot.ArmStages.IDLE);

        //Lift positions, Dpad left = fast deployment FSM, Dpad up = slow deployment FSM, X = fast retraction FSM, Y = slow retraction FSM
        manipulator.onPress(Button.DPAD_LEFT, () -> stateMachines.fastDeployment.start())
                .onPress(Button.DPAD_UP, () -> stateMachines.slowDeployment.start())
                .onPress(Button.X, () -> stateMachines.fastRetraction.start())
                .onPress(Button.Y, () -> stateMachines.slowRetraction.start());

        //Lift power, right stick y = lift power
        if (Math.abs(manipulator.getStickY(Stick.RIGHT_STICK)) > 0.1) robot.setLiftPower(-manipulator.getStickY(Stick.RIGHT_STICK));

        super.loop();
        log();
    }
}
