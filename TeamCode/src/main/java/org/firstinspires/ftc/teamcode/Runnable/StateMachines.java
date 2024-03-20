package org.firstinspires.ftc.teamcode.Runnable;

import com.sfdev.assembly.state.StateMachine;
import com.sfdev.assembly.state.StateMachineBuilder;

import org.firstinspires.ftc.teamcode.Systems.Motors.Motor;
import org.firstinspires.ftc.teamcode.Systems.Stopwatch;

public class StateMachines {
    private final Robot robot;
    private final Stopwatch stopwatch;

    public StateMachine deployment;
    public StateMachine slowDeployment;
    public StateMachine retraction;
    public StateMachine slowRetraction;

    private enum LiftDeploymentStages {
        FOLD_GRAB_BOX, EXTEND_LIFT, DEPLOY_GRAB_BOX, IDLE
    }

    private enum LiftRetractionStages {
        EXTEND_LIFT, FOLD_GRAB_BOX, RETRACT_LIFT, RELEASE_GRAB_BOX, IDLE
    }

    public StateMachines(Robot robot) {
        this.robot = robot;
        stopwatch = new Stopwatch();

        deployment = new StateMachineBuilder()
                .state(LiftDeploymentStages.FOLD_GRAB_BOX)
                .onEnter(() -> {
                    if (robot.lift.getMode() == Motor.Mode.POWER) robot.lift.setMode(Motor.Mode.POSITION);
                    robot.currentArmStage = Robot.ArmStages.FOLDED;
                    stopwatch.restart();
                })
                .transition(() -> (stopwatch.getTime() >= 100 && robot.arm.getPosition() == Robot.ArmStages.FOLDED.getArmPos() && robot.grabbox.getPosition() == Robot.ArmStages.FOLDED.getGrabboxPos()), LiftDeploymentStages.EXTEND_LIFT)

                .state(LiftDeploymentStages.EXTEND_LIFT)
                .onEnter(() -> {
                    robot.lift.setTargetPosition(robot.liftPositions[1]);
                })
                .transition(() -> (robot.lift.atTargetPosition(0) || robot.lift.atTargetPosition(1)), LiftDeploymentStages.DEPLOY_GRAB_BOX)

                .state(LiftDeploymentStages.DEPLOY_GRAB_BOX)
                .onEnter(() -> {
                    robot.currentArmStage = Robot.ArmStages.DEPLOYED;
                })
                .transition(() -> (robot.arm.getPosition() == Robot.ArmStages.DEPLOYED.getArmPos() && robot.grabbox.getPosition() == Robot.ArmStages.DEPLOYED.getGrabboxPos()), LiftDeploymentStages.IDLE)

                .state(LiftDeploymentStages.IDLE)
                .build();

        slowDeployment = new StateMachineBuilder()
                .state(LiftDeploymentStages.FOLD_GRAB_BOX)
                .onEnter(() -> {
                    if (robot.lift.getMode() == Motor.Mode.POWER) robot.lift.setMode(Motor.Mode.POSITION);
                    robot.currentArmStage = Robot.ArmStages.FOLDED;
                    stopwatch.restart();
                })
                .transition(() -> (stopwatch.getTimeSeconds() >= 1 && robot.arm.getPosition() == Robot.ArmStages.FOLDED.getArmPos() && robot.grabbox.getPosition() == Robot.ArmStages.FOLDED.getGrabboxPos()), LiftDeploymentStages.EXTEND_LIFT)

                .state(LiftDeploymentStages.EXTEND_LIFT)
                .onEnter(() -> {
                    robot.lift.setTargetPosition(robot.liftPositions[1]);
                    stopwatch.restart();
                })
                .transition(() -> (stopwatch.getTimeSeconds() >= 1 && (robot.lift.atTargetPosition(0) || robot.lift.atTargetPosition(1))), LiftDeploymentStages.DEPLOY_GRAB_BOX)

                .state(LiftDeploymentStages.DEPLOY_GRAB_BOX)
                .onEnter(() -> {
                    robot.currentArmStage = Robot.ArmStages.DEPLOYED;
                })
                .transition(() -> (robot.arm.getPosition() == Robot.ArmStages.DEPLOYED.getArmPos() && robot.grabbox.getPosition() == Robot.ArmStages.DEPLOYED.getGrabboxPos()), LiftDeploymentStages.IDLE)

                .state(LiftDeploymentStages.IDLE)
                .build();

        retraction = new StateMachineBuilder()
                .state(LiftDeploymentStages.EXTEND_LIFT)
                .onEnter(() -> {
                    if (robot.lift.getMode() == Motor.Mode.POWER) robot.lift.setMode(Motor.Mode.POSITION);
                    if (robot.lift.getCurrentPosition(0) < robot.liftPositions[1] && !robot.atLiftPosition(robot.liftPositions[1])) robot.setLiftPosition(robot.liftPositions[1]);
                })
                .transition(() -> (robot.lift.atTargetPosition(0) || robot.lift.atTargetPosition(1)), LiftDeploymentStages.FOLD_GRAB_BOX)

                .state(LiftRetractionStages.FOLD_GRAB_BOX)
                .onEnter(() -> {
                    robot.currentArmStage = Robot.ArmStages.FOLDED;
                    stopwatch.restart();
                })
                .transition(() -> (stopwatch.getTimeSeconds() >= 2 && robot.arm.getPosition() == Robot.ArmStages.FOLDED.getArmPos() && robot.grabbox.getPosition() == Robot.ArmStages.FOLDED.getGrabboxPos()), LiftRetractionStages.RETRACT_LIFT)

                .state(LiftRetractionStages.RETRACT_LIFT)
                .onEnter(() -> {
                    robot.setLiftPosition(robot.liftPositions[0]);
                })
                .transition(() -> (robot.lift.atTargetPosition(0) || robot.lift.atTargetPosition(1)), LiftRetractionStages.RELEASE_GRAB_BOX)

                .state(LiftRetractionStages.RELEASE_GRAB_BOX)
                .onEnter(() -> {
                    robot.currentArmStage = Robot.ArmStages.IDLE;
                })
                .transition(() -> (robot.arm.getPosition() == Robot.ArmStages.IDLE.getArmPos() && robot.grabbox.getPosition() == Robot.ArmStages.IDLE.getGrabboxPos()), LiftRetractionStages.IDLE)

                .state(LiftRetractionStages.IDLE)
                .build();

        slowRetraction = new StateMachineBuilder()
                .state(LiftDeploymentStages.EXTEND_LIFT)
                .onEnter(() -> {
                    if (robot.lift.getMode() == Motor.Mode.POWER) robot.lift.setMode(Motor.Mode.POSITION);
                    if (robot.lift.getCurrentPosition(0) < robot.liftPositions[1] && !robot.atLiftPosition(robot.liftPositions[1])) robot.setLiftPosition(robot.liftPositions[1]);
                    stopwatch.restart();
                })
                .transition(() -> (stopwatch.getTimeSeconds() >= 2.5 && (robot.lift.atTargetPosition(0) || robot.lift.atTargetPosition(1))), LiftDeploymentStages.FOLD_GRAB_BOX)

                .state(LiftRetractionStages.FOLD_GRAB_BOX)
                .onEnter(() -> {
                    robot.currentArmStage = Robot.ArmStages.FOLDED;
                    stopwatch.restart();
                })
                .transition(() -> (stopwatch.getTimeSeconds() >= 1 && robot.arm.getPosition() == Robot.ArmStages.FOLDED.getArmPos() && robot.grabbox.getPosition() == Robot.ArmStages.FOLDED.getGrabboxPos()), LiftRetractionStages.RETRACT_LIFT)

                .state(LiftRetractionStages.RETRACT_LIFT)
                .onEnter(() -> {
                    robot.setLiftPosition(robot.liftPositions[0]);
                    stopwatch.restart();
                })
                .transition(() -> (stopwatch.getTimeSeconds() >= 1 && (robot.lift.atTargetPosition(0) || robot.lift.atTargetPosition(1))), LiftRetractionStages.RELEASE_GRAB_BOX)

                .state(LiftRetractionStages.RELEASE_GRAB_BOX)
                .onEnter(() -> {
                    robot.currentArmStage = Robot.ArmStages.IDLE;
                })
                .transition(() -> (robot.arm.getPosition() == Robot.ArmStages.IDLE.getArmPos() && robot.grabbox.getPosition() == Robot.ArmStages.IDLE.getGrabboxPos()), LiftRetractionStages.IDLE)

                .state(LiftRetractionStages.IDLE)
                .build();
    }

    public void update() {
        if (!stopwatch.isRunning()) stopwatch.start();

        slowDeployment.update();
        deployment.update();
        slowRetraction.update();
        retraction.update();
    }
}
