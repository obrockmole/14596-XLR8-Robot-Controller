package org.firstinspires.ftc.teamcode.CenterStage.Autonomous.Red.StackSide;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.CenterStage.Autonomous.BaseAuto;
import org.firstinspires.ftc.teamcode.Systems.Movement.MovementSequence;
import org.firstinspires.ftc.teamcode.Systems.Vision.VisionDetector;

@Autonomous(group = "Red", name = "Purple - Red Stack")
public class Red_Stack_Purple extends BaseAuto {
    public void initVision() {
        pipeline = getRedPipeline();
        contourDetector = new VisionDetector(hardwareMap, "Webcam", pipeline);
    }

    public MovementSequence leftSequence() {
        return new MovementSequence(robot)
                .odometryDrive(-28, 2, 0.7, 50)
                .odometryTurn(-90,0.6, 100)
                .afterMoving(() -> robot.intake.setTargetPower(-0.4))
                .waitSeconds(2)
                .odometryDrive(-8, 24, 0.7, 0)
                .afterMoving(() -> robot.intake.setTargetPower(0))
                .odometryDrive(-60, 0, 0.8, 100);
    }

    public MovementSequence centerSequence() {
        return new MovementSequence(robot)
                .odometryDrive(-44, 0, 0.7, 100)
                .afterMoving(() -> robot.intake.setTargetPower(-0.4))
                .waitSeconds(2)
                .odometryDrive(-6, -6, 0.7, 100)
                .afterMoving(() -> robot.intake.setTargetPower(0))
                .odometryTurn(-90, 0.7, 100)
                .odometryDrive(60, 0, 0.8, 100);
    }

    public MovementSequence rightSequence() {
        return new MovementSequence(robot)
                .odometryDrive(-28, -2, 0.7, 50)
                .odometryTurn(90,0.6, 100)
                .afterMoving(() -> robot.intake.setTargetPower(-0.4))
                .waitSeconds(2)
                .odometryDrive(-8, -24, 0.7, 0)
                .afterMoving(() -> robot.intake.setTargetPower(0))
                .odometryDrive(60, 0, 0.8, 100);
    }
}
