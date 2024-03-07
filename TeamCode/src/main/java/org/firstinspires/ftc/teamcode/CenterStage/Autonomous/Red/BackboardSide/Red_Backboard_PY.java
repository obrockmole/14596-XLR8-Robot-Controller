package org.firstinspires.ftc.teamcode.CenterStage.Autonomous.Red.BackboardSide;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.CenterStage.Autonomous.BaseAuto;
import org.firstinspires.ftc.teamcode.Systems.Movement.MovementSequence;
import org.firstinspires.ftc.teamcode.Systems.Vision.VisionDetector;

@Autonomous(group = "Red", name = "Purple & Yellow - Red Backboard")
public class Red_Backboard_PY extends BaseAuto {
    public void initVision() {
        pipeline = getRedPipeline();
        contourDetector = new VisionDetector(hardwareMap, "Webcam", pipeline);
    }

    public MovementSequence leftSequence() {
        return new MovementSequence(robot)
                .odometryDrive(-28, -24, 0.8, 0)
                .odometryTurn(90,0.6, 100)
                .afterMoving(() -> {
                    robot.intake.setTargetPower(-0.4);
                    stateMachines.deployment.start();
                })
                .waitSeconds(1.5)
                .odometryDrive(-28, -4, 0.6, 100)
                .afterMoving(() -> {
                    robot.pixelClamp.setTargetPosition(0);
                    robot.intake.setTargetPower(0);
                })
                .waitSeconds(1)
                .afterMoving(() -> stateMachines.retraction.start())
                .odometryDrive(0, -20, 1, 0);
    }

    public MovementSequence centerSequence() {
        return new MovementSequence(robot)
                .afterMoving(() -> {
                    robot.intake.setTargetPower(-0.4);
                    stateMachines.deployment.start();
                })
                .waitSeconds(1.5)
                .odometryDrive(-10, -12, 0.6, 100)
                .afterMoving(() -> {
                    robot.pixelClamp.setTargetPosition(0);
                    robot.intake.setTargetPower(0);
                })
                .waitSeconds(1)
                .afterMoving(() -> stateMachines.retraction.start())
                .odometryDrive(0, -24, 1, 0);
    }

    public MovementSequence rightSequence() {
        return new MovementSequence(robot)
                .afterMoving(() -> {
                    robot.intake.setTargetPower(-0.4);
                    stateMachines.deployment.start();
                })
                .waitSeconds(1.5)
                .odometryDrive(-4, 4, 0.6, 100)
                .afterMoving(() -> {
                    robot.pixelClamp.setTargetPosition(0);
                    robot.intake.setTargetPower(0);
                })
                .waitSeconds(1)
                .afterMoving(() -> stateMachines.retraction.start())
                .odometryDrive(0, -28, 1, 0);
    }
}
