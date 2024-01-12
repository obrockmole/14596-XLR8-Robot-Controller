package org.firstinspires.ftc.teamcode.CenterStage.Autonomous;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.RoadRunner.TrajectorySequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.RoadRunner.TrajectorySequence.TrajectorySequenceBuilder;
import org.firstinspires.ftc.teamcode.Systems.Vision.ContourDetectionPipeline;
import org.firstinspires.ftc.teamcode.Systems.Vision.VisionDetector;
import org.opencv.core.Scalar;

@Autonomous(group = "RedBlue", name = "Park - Backboard")
public class Backboard_Park extends BaseAuto {
    public void initVision() {
        pipeline = new ContourDetectionPipeline(this, new Scalar(170, 0, 0), new Scalar(179, 255, 255), new Scalar(20, 100, 110), new Scalar(100, 200, 200), 100);
        contourDetector = new VisionDetector(hardwareMap, "Webcam", pipeline);
    }

    public Pose2d startPos() {
        return new Pose2d(0, 0, 0);
    }

    public TrajectorySequence leftSequence(Pose2d startPos) {
        return centerSequence(startPos);
    }

    public TrajectorySequence centerSequence(Pose2d startPos) {
        return drive.trajectorySequenceBuilder(startPos)
                //Park
                .lineTo(new Vector2d(-44, 0))

                //Spit both pixels
                .addTemporalMarker(() -> {
                    robot.intake.setTargetPower(-0.4);
                })
                .waitSeconds(2)
                .addTemporalMarker(() -> {
                    robot.intake.setTargetPower(0);
                })
                .build();
    }

    public TrajectorySequence rightSequence(Pose2d startPos) {
        return centerSequence(startPos);
    }
}
