package org.firstinspires.ftc.teamcode.CenterStage.Autonomous.Red.BackboardSide;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.CenterStage.Autonomous.BaseAuto;
import org.firstinspires.ftc.teamcode.RoadRunner.TrajectorySequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.Systems.Vision.VisionDetector;

@Autonomous(group = "Red", name = "Purple - Red Backboard")
public class Red_Backboard_Purple extends BaseAuto {
    public void initVision() {
        pipeline = getRedPipeline();
        contourDetector = new VisionDetector(hardwareMap, "Webcam", pipeline);
    }

    public Pose2d startPos() {
        return new Pose2d(12, -64, Math.toRadians(-90));
    }

    public TrajectorySequence leftSequence(Pose2d startPos) {
        return drive.trajectorySequenceBuilder(startPos)
                .setReversed(true)
                .splineToSplineHeading(new Pose2d(10, -30, Math.toRadians(180)), Math.toRadians(180))
                .addTemporalMarker(1.9, () -> {
                    robot.intake.setTargetPower(-0.4);
                })
                .addTemporalMarker(2.9, () -> {
                    robot.intake.setTargetPower(0);
                })
                .setTangent(Math.toRadians(0))
                .splineTo(new Vector2d(52, -60), Math.toRadians(0))
                .build();
    }

    public TrajectorySequence centerSequence(Pose2d startPos) {
        return drive.trajectorySequenceBuilder(startPos)
                .setReversed(true)
                .splineToSplineHeading(new Pose2d(20, -26, Math.toRadians(180)), Math.toRadians(0))
                .addTemporalMarker(1.8, () -> {
                    robot.intake.setTargetPower(-0.4);
                })
                .addTemporalMarker(2.8, () -> {
                    robot.intake.setTargetPower(0);
                })
                .splineTo(new Vector2d(52, -60), Math.toRadians(0))
                .build();
    }

    public TrajectorySequence rightSequence(Pose2d startPos) {
        return drive.trajectorySequenceBuilder(startPos)
                .setReversed(true)
                .splineToSplineHeading(new Pose2d(24, -30, Math.toRadians(180)), Math.toRadians(0))
                .addTemporalMarker(2, () -> {
                    robot.intake.setTargetPower(-0.4);
                })
                .addTemporalMarker(3, () -> {
                    robot.intake.setTargetPower(0);
                })
                .splineTo(new Vector2d(52, -60), Math.toRadians(0))
                .build();
    }
}
