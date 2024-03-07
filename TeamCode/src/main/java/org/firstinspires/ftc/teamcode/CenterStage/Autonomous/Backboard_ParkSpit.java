package org.firstinspires.ftc.teamcode.CenterStage.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Systems.Movement.MovementSequence;
import org.firstinspires.ftc.teamcode.Systems.Vision.VisionDetector;

@Autonomous(group = "RedBlue", name = "Park & Spit - Backboard")
public class Backboard_ParkSpit extends BaseAuto {
    public void initVision() {
        pipeline = getRedPipeline();
        contourDetector = new VisionDetector(hardwareMap, "Webcam", pipeline);
    }

    public MovementSequence leftSequence() {
        return new MovementSequence(robot)
                .odometryDrive(36, 0, 0.8, 0)
                .afterMoving(() -> robot.intake.setTargetPower(-0.4))
                .waitSeconds(2)
                .afterMoving(() -> robot.intake.setTargetPower(0));
    }

    public MovementSequence centerSequence() {
        return new MovementSequence(robot)
                .odometryDrive(36, 0, 0.8, 0);
    }

    public MovementSequence rightSequence() {
        return new MovementSequence(robot)
                .odometryDrive(36, 0, 0.8, 0)
                .afterMoving(() -> robot.intake.setTargetPower(-0.4))
                .waitSeconds(2)
                .afterMoving(() -> robot.intake.setTargetPower(0));
    }
}
