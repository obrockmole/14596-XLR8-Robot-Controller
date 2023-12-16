package org.firstinspires.ftc.teamcode.CenterStage.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.sfdev.assembly.state.StateMachine;

import org.firstinspires.ftc.teamcode.CenterStage.Robot;
import org.firstinspires.ftc.teamcode.Systems.Gamepad.Gamepad;

public class BaseTele extends OpMode {
    Gamepad driver;
    Gamepad manipulator;

    Robot robot;

    enum ScoringStages {
        EXTEND_LIFT,
        DEPLOY_PIXEL,
        RETRACT_LIFT
    }

    StateMachine scoringMachine;

    public void init() {
        driver = new Gamepad(gamepad1);
        manipulator = new Gamepad(gamepad2);

        robot = new Robot(hardwareMap);

        /*scoringMachine = new StateMachineBuilder()
                .state(ScoringStages.EXTEND_LIFT)
                .onEnter(() -> robot.setLiftPosition(robot.liftPositions.length - 1))
                .transition(() -> Math.abs(robot.lift.getCurrentPosition() - robot.lift.getTargetPosition()) < robot.lift.getTolerance(), ScoringStages.DEPLOY_PIXEL)

                .state(ScoringStages.DEPLOY_PIXEL)
                .onEnter(() -> robot.pixelDepositSlide.setTargetPosition(1))
                .transitionTimed(1, ScoringStages.RETRACT_LIFT)

                .state(ScoringStages.RETRACT_LIFT)
                .onEnter(() -> {
                    robot.setLiftPosition(0);
                    robot.pixelDepositSlide.setTargetPosition(0);
                })
                .build();*/
    }

    public void loop() {}

    public void update() {
        driver.update();
        manipulator.update();

        robot.update(true);

//        scoringMachine.update();
    }

    public void log() {
        robot.log(telemetry);

        telemetry.addLine("-----Other-----");
        telemetry.addData("Runtime", getRuntime());
        telemetry.update();
    }
}
