package org.firstinspires.ftc.teamcode.CenterStage.Autonomous.Red.BackboardSide;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.CenterStage.Autonomous.BaseAuto;
import org.firstinspires.ftc.teamcode.RoadRunner.TrajectorySequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.Systems.Vision.VisionDetector;

@Autonomous(group = "Red", name = "Yellow & Purple - Red Backboard")
public class Red_Backboard_YP extends BaseAuto {
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
                .splineToSplineHeading(new Pose2d(12, -28, Math.toRadians(180)), Math.toRadians(80))
                .addTemporalMarker(1.4, () -> {
                    robot.intake.setTargetPower(-0.4);
                    //activate lift
                })
                .addTemporalMarker(2.4, () -> {
                    robot.intake.setTargetPower(0);
                })
                .splineToConstantHeading(new Vector2d(36, -29), Math.toRadians(0))
                .addDisplacementMarker(() -> {
                    //release yellow pixel
                })
                .waitSeconds(0.2)
                .addDisplacementMarker(() -> {
                    //lower lift
                })
                .setTangent(Math.toRadians(-45))
                .splineToConstantHeading(new Vector2d(52, -60), Math.toRadians(-45))
                .build();
    }

    public TrajectorySequence centerSequence(Pose2d startPos) {
        return drive.trajectorySequenceBuilder(startPos)
                .setReversed(true)
                .splineToSplineHeading(new Pose2d(12, -28, Math.toRadians(180)), Math.toRadians(80))
                .addTemporalMarker(1.6, () -> {
                    robot.intake.setTargetPower(-0.4);
                    //activate lift
                })
                .addTemporalMarker(2.6, () -> {
                    robot.intake.setTargetPower(0);
                })
                .splineToConstantHeading(new Vector2d(36, -36), Math.toRadians(0))
                .addDisplacementMarker(() -> {
                    //release yellow pixel
                })
                .waitSeconds(0.2)
                .addDisplacementMarker(() -> {
                    //lower lift
                })
                .setTangent(Math.toRadians(-45))
                .splineToConstantHeading(new Vector2d(52, -60), Math.toRadians(-45))
                .build();
    }

    public TrajectorySequence rightSequence(Pose2d startPos) {
        return drive.trajectorySequenceBuilder(startPos)
                .setReversed(true)
                .splineToSplineHeading(new Pose2d(24, -30, Math.toRadians(180)), Math.toRadians(0))
                .addTemporalMarker(1.9, () -> {
                    robot.intake.setTargetPower(-0.4);
                    //activate lift
                })
                .addTemporalMarker(2.9, () -> {
                    robot.intake.setTargetPower(0);
                })
                .splineToConstantHeading(new Vector2d(36, -42), Math.toRadians(-90))
                .addDisplacementMarker(() -> {
                    //release yellow pixel
                })
                .waitSeconds(0.2)
                .addDisplacementMarker(() -> {
                    //lower lift
                })
                .setTangent(Math.toRadians(-45))
                .splineToConstantHeading(new Vector2d(52, -60), Math.toRadians(-45))
                .build();
    }
}
