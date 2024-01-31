package org.firstinspires.ftc.teamcode.CenterStage;

import com.sfdev.assembly.state.StateMachine;
import com.sfdev.assembly.state.StateMachineBuilder;

public class StateMachines {
    private final Robot robot;

    public StateMachine halfLiftDeployment;
    public StateMachine fullLiftDeployment;
    public StateMachine liftRetraction;

    private LiftDeploymentStages previousHalfLiftDeploymentState = LiftDeploymentStages.IDLE;
    private LiftDeploymentStages previousFullLiftDeploymentState = LiftDeploymentStages.IDLE;
    private LiftRetractionStages previousLiftRetractionState = LiftRetractionStages.IDLE;

    private enum LiftDeploymentStages {
        IDLE,
        FOLD_GRAB_BOX,
        EXTEND_LIFT,
        DEPLOY_GRAB_BOX
    }

    private enum LiftRetractionStages {
        IDLE,
        FOLD_GRAB_BOX,
        RETRACT_LIFT,
        RELEASE_GRAB_BOX
    }

    public StateMachines(Robot theRobot) {
        this.robot = theRobot;

        halfLiftDeployment = new StateMachineBuilder()
                .state(LiftDeploymentStages.FOLD_GRAB_BOX)
                .onEnter(() -> {
                    robot.currentArmStage = Robot.ArmStages.FOLDED;
                })
                .transition(() -> (robot.arm.getPosition() == Robot.ArmStages.FOLDED.getArmPos() && robot.grabbox.getPosition() == Robot.ArmStages.FOLDED.getGrabboxPos()), LiftDeploymentStages.EXTEND_LIFT)

                .state(LiftDeploymentStages.EXTEND_LIFT)
                .onEnter(() -> {
                    robot.lift.setTargetPosition(robot.liftPositions[1]);
                })
                .transition(robot.lift::atTargetPosition, LiftDeploymentStages.DEPLOY_GRAB_BOX)

                .state(LiftDeploymentStages.DEPLOY_GRAB_BOX)
                .onEnter(() -> {
                    robot.currentArmStage = Robot.ArmStages.HALF_DEPLOYED;
                })
                .transition(() -> (robot.arm.getPosition() == Robot.ArmStages.DEPLOYED.getArmPos() && robot.grabbox.getPosition() == Robot.ArmStages.DEPLOYED.getGrabboxPos()), LiftDeploymentStages.IDLE)

                .state(LiftDeploymentStages.IDLE)
                .build();

        fullLiftDeployment = new StateMachineBuilder()
                .state(LiftDeploymentStages.FOLD_GRAB_BOX)
                .onEnter(() -> {
                    robot.currentArmStage = Robot.ArmStages.FOLDED;
                })
                .transition(() -> (robot.arm.getPosition() == Robot.ArmStages.FOLDED.getArmPos() && robot.grabbox.getPosition() == Robot.ArmStages.FOLDED.getGrabboxPos()), LiftDeploymentStages.EXTEND_LIFT)

                .state(LiftDeploymentStages.EXTEND_LIFT)
                .onEnter(() -> {
                    robot.lift.setTargetPosition(robot.liftPositions[1]);
                })
                .transition(robot.lift::atTargetPosition, LiftDeploymentStages.DEPLOY_GRAB_BOX)

                .state(LiftDeploymentStages.DEPLOY_GRAB_BOX)
                .onEnter(() -> {
                    robot.currentArmStage = Robot.ArmStages.DEPLOYED;
                })
                .transition(() -> (robot.arm.getPosition() == Robot.ArmStages.DEPLOYED.getArmPos() && robot.grabbox.getPosition() == Robot.ArmStages.DEPLOYED.getGrabboxPos()), LiftDeploymentStages.IDLE)

                .state(LiftDeploymentStages.IDLE)
                .build();

        liftRetraction = new StateMachineBuilder()
                .state(LiftRetractionStages.IDLE)

                .state(LiftRetractionStages.FOLD_GRAB_BOX)
                .onEnter(() -> {
                    if (robot.lift.getCurrentPosition() <= robot.liftPositions[1] && robot.lift.getCurrentPosition() >= robot.liftPositions[1] / 2) robot.lift.setTargetPosition(robot.liftPositions[1]);
                    robot.currentArmStage = Robot.ArmStages.FOLDED;
                })
                .transition(() -> (robot.arm.getPosition() == Robot.ArmStages.FOLDED.getArmPos() && robot.grabbox.getPosition() == Robot.ArmStages.FOLDED.getGrabboxPos() /* and lift is high enough*/), LiftRetractionStages.RETRACT_LIFT)

                .state(LiftRetractionStages.RETRACT_LIFT)
                .onEnter(() -> {
                    robot.lift.setTargetPosition(robot.liftPositions[0]);
                })
                .transition(robot.lift::atTargetPosition, LiftRetractionStages.RELEASE_GRAB_BOX)

                .state(LiftRetractionStages.RELEASE_GRAB_BOX)
                .onEnter(() -> {
                    robot.currentArmStage = Robot.ArmStages.IDLE;
                })
                .transition(() -> (robot.arm.getPosition() == Robot.ArmStages.IDLE.getArmPos() && robot.grabbox.getPosition() == Robot.ArmStages.IDLE.getGrabboxPos()), LiftRetractionStages.IDLE)
                .build();
    }

    public void update() {
        halfLiftDeployment.update();
        fullLiftDeployment.update();
        liftRetraction.update();

        if (!halfLiftDeployment.isRunning() && previousHalfLiftDeploymentState != LiftDeploymentStages.IDLE) {
            halfLiftDeployment.reset();
            halfLiftDeployment.stop();
        }

        if (!fullLiftDeployment.isRunning() && previousFullLiftDeploymentState != LiftDeploymentStages.IDLE) {
            fullLiftDeployment.reset();
            fullLiftDeployment.stop();
        }

        if (!liftRetraction.isRunning() && previousLiftRetractionState != LiftRetractionStages.IDLE) {
            liftRetraction.reset();
            liftRetraction.stop();
        }

        previousHalfLiftDeploymentState = (LiftDeploymentStages) halfLiftDeployment.getState();
        previousFullLiftDeploymentState = (LiftDeploymentStages) fullLiftDeployment.getState();
        previousLiftRetractionState = (LiftRetractionStages) liftRetraction.getState();
    }
}
