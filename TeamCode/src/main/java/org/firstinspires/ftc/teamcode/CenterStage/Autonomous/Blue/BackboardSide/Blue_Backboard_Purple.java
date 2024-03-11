package org.firstinspires.ftc.teamcode.CenterStage.Autonomous.Blue.BackboardSide;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.CenterStage.Autonomous.BaseAuto;
import org.firstinspires.ftc.teamcode.Systems.Movement.MovementSequence;
import org.firstinspires.ftc.teamcode.Systems.Vision.VisionDetector;

@Autonomous(group = "Blue", name = "Purple - Blue Backboard")
public class Blue_Backboard_Purple extends BaseAuto {
    public void initVision() {
        pipeline = getRedPipeline();
        contourDetector = new VisionDetector(hardwareMap, "Webcam", pipeline);
    }

    public MovementSequence leftSequence() {
        return new MovementSequence(robot)
                .odometryDrive(-28, -24, 0.8, 0)
                .odometryTurn(90,0.6, 100)
                .afterMoving(() -> robot.intake.setTargetPower(-0.4))
                .waitSeconds(1.5)
                .odometryDrive(-10, -28, 0.7, 0)
                .afterMoving(() -> robot.intake.setTargetPower(0));
    }

    public MovementSequence centerSequence() {
        return new MovementSequence(robot)
                .odometryDrive(-42, -12, 0.8, 0)
                .odometryTurn(90,0.6, 100)
                .afterMoving(() -> robot.intake.setTargetPower(-0.4))
                .waitSeconds(1.5)
                .odometryDrive(-22, -42, 0.7, 0)
                .afterMoving(() -> robot.intake.setTargetPower(0));
    }

    public MovementSequence rightSequence() {
        return new MovementSequence(robot)
                .odometryDrive(-28, 0, 0.8, 0)
                .odometryTurn(90,0.6, 100)
                .afterMoving(() -> robot.intake.setTargetPower(-0.4))
                .waitSeconds(1.5)
                .odometryDrive(-34, -28, 0.7, 0)
                .afterMoving(() -> robot.intake.setTargetPower(0));
    }
}
