package org.firstinspires.ftc.teamcode.CenterStage.Autonomous.Blue.BackboardSide;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.CenterStage.Autonomous.BaseAuto;
import org.firstinspires.ftc.teamcode.RoadRunner.TrajectorySequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.Systems.Vision.VisionDetector;

@Autonomous(group = "Blue", name = "Purple - Blue Backboard")
public class Blue_Backboard_Purple extends BaseAuto {
    public void initVision() {
        pipeline = getBluePipeline();
        contourDetector = new VisionDetector(hardwareMap, "Webcam", pipeline);
    }

    public Pose2d startPos() {
        return new Pose2d(12, 64, Math.toRadians(90));
    }

    public TrajectorySequence leftSequence(Pose2d startPos) {
        return drive.trajectorySequenceBuilder(startPos)
                //Drive to spike mark
                .lineToLinearHeading(new Pose2d(33, 30, Math.toRadians(180)))

                //Place purple pixel
                .addTemporalMarker(() -> {
                    robot.intake.setTargetPower(-0.35);
                })
                .waitSeconds(1.5)

                //Park
                .lineToConstantHeading(new Vector2d(50, 60))
                .addTemporalMarker(() -> {
                    robot.intake.setTargetPower(0);
                })
                .build();
    }

    public TrajectorySequence centerSequence(Pose2d startPos) {
        return drive.trajectorySequenceBuilder(startPos)
                //Drive to spike mark
                .lineToLinearHeading(new Pose2d(24, 25, Math.toRadians(180)))

                //Place purple pixel
                .addTemporalMarker(() -> {
                    robot.intake.setTargetPower(-0.35);
                })
                .waitSeconds(1.5)

                //Park
                .lineToConstantHeading(new Vector2d(50, 60))
                .addTemporalMarker(() -> {
                    robot.intake.setTargetPower(0);
                })
                .build();
    }

    public TrajectorySequence rightSequence(Pose2d startPos) {
        return drive.trajectorySequenceBuilder(startPos)
                //Drive to spike mark
                .setTangent(Math.toRadians(-70))
                .splineToLinearHeading(new Pose2d(10, 31, Math.toRadians(180)), Math.toRadians(180))

                //Place purple pixel
                .addTemporalMarker(() -> {
                    robot.intake.setTargetPower(-0.35);
                })
                .waitSeconds(1.5)

                //Park
                .lineToConstantHeading(new Vector2d(50, 60))
                .addTemporalMarker(() -> {
                    robot.intake.setTargetPower(0);
                })
                .build();
    }
}
