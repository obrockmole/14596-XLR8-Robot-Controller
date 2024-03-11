package org.firstinspires.ftc.teamcode.CenterStage.Autonomous.Blue.BackboardSide;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.CenterStage.Autonomous.BaseAuto;
import org.firstinspires.ftc.teamcode.Systems.Movement.MovementSequence;
import org.firstinspires.ftc.teamcode.Systems.Vision.VisionDetector;

@Autonomous(group = "Blue", name = "Purple & Yellow - Blue Backboard")
public class Blue_Backboard_PY extends BaseAuto {
    public void initVision() {
        pipeline = getBluePipeline();
        contourDetector = new VisionDetector(hardwareMap, "Webcam", pipeline);
    }

    public MovementSequence leftSequence() {
        return new MovementSequence(robot)
                .odometryDrive(-32, 0, 0.8, 200)
                .odometryTurn(90,0.6, 100)
                .afterMoving(() -> {
                    robot.intake.setTargetPower(-0.55);
                    stateMachines.slowDeployment.start();
                })
                .waitSeconds(2)
                .odometryDrive(-32, 0, 0.85, 100)
                .afterMoving(() -> {
                    robot.pixelClamp.setTargetPosition(0);
                    robot.intake.setTargetPower(0);
                })
                .waitSeconds(1)
                .afterMoving(() -> stateMachines.retraction.start())
                .odometryTurn(90, 0.6, 100)
                .odometryDrive(-28, 0, 0.8, 0);
    }

    public MovementSequence centerSequence() {
        return new MovementSequence(robot)
                .odometryDrive(-44, 12, 0.8, 100)
                .odometryTurn(90,0.6, 100)
                .afterMoving(() -> {
                    robot.intake.setTargetPower(-0.55);
                    stateMachines.slowDeployment.start();
                })
                .waitSeconds(2)
                .odometryDrive(-20, 10, 0.8, 200)
                .afterMoving(() -> {
                    robot.pixelClamp.setTargetPosition(0);
                    robot.intake.setTargetPower(0);
                })
                .waitSeconds(1)
                .afterMoving(() -> stateMachines.retraction.start())
                .odometryTurn(90, 0.6, 100)
                .odometryDrive(-20, 0, 0.8, 0);
    }

    public MovementSequence rightSequence() {
        return new MovementSequence(robot)
                .odometryDrive(-32, -1.5, 0.9, 200)
                .odometryTurn(90, 0.6, 100)
                .odometryDrive(-24, 0, 0.8, 100)
                .afterMoving(() -> {
                    robot.intake.setTargetPower(-0.55);
                    stateMachines.slowDeployment.start();
                })
                .waitSeconds(2)
                .odometryDrive(-10, 2, 0.8, 200)
                .afterMoving(() -> {
                    robot.pixelClamp.setTargetPosition(0);
                    robot.intake.setTargetPower(0);
                })
                .waitSeconds(1)
                .afterMoving(() -> stateMachines.retraction.start())
                .odometryTurn(90, 0.6, 100)
                .odometryDrive(-20, 0, 0.8, 0); //Written by Cole
    }
}
