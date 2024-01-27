package org.firstinspires.ftc.teamcode.CenterStage.Autonomous.Red.StackSide;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.CenterStage.Autonomous.BaseAuto;
import org.firstinspires.ftc.teamcode.RoadRunner.TrajectorySequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.Systems.Vision.VisionDetector;

@Autonomous(group = "Red", name = "Purple - Red Stack")
public class Red_Stack_Purple extends BaseAuto {
    public void initVision() {
        pipeline = getRedPipeline();
        contourDetector = new VisionDetector(hardwareMap, "Webcam", pipeline);
    }

    public Pose2d startPos() {
        return new Pose2d(-36, -64, Math.toRadians(-90));
    }

    public TrajectorySequence leftSequence(Pose2d startPos) {
        return drive.trajectorySequenceBuilder(startPos)
                //Drive to spike mark
                .lineToLinearHeading(new Pose2d(-44, -16, Math.toRadians(-90)))

                //Place purple pixel
                .addTemporalMarker(() -> {
                    robot.intake.setTargetPower(-0.35);
                })
                .waitSeconds(1.5)

                //Park
                .lineToConstantHeading(new Vector2d(-36, -12))
                .lineToLinearHeading(new Pose2d(50, -12, Math.toRadians(-90)))
                .addTemporalMarker(() -> {
                    robot.intake.setTargetPower(0);
                })
                .build();
    }

    public TrajectorySequence centerSequence(Pose2d startPos) {
        return drive.trajectorySequenceBuilder(startPos)
                //Drive to spike mark
                .lineToConstantHeading(new Vector2d(-36, -14))

                //Place purple pixel
                .addTemporalMarker(() -> {
                    robot.intake.setTargetPower(-0.35);
                })
                .waitSeconds(1.5)

                //Park
                .setTangent(Math.toRadians(10))
                .splineToConstantHeading(new Vector2d(50, -12), Math.toRadians(0))
                .addTemporalMarker(() -> {
                    robot.intake.setTargetPower(0);
                })
                .build();
    }

    public TrajectorySequence rightSequence(Pose2d startPos) {
        return drive.trajectorySequenceBuilder(startPos)
                //Drive to spike mark
                .setTangent(Math.toRadians(110))
                .splineToLinearHeading(new Pose2d(-34, -31, Math.toRadians(0)), Math.toRadians(0))

                //Place purple pixel
                .addTemporalMarker(() -> {
                    robot.intake.setTargetPower(-0.35);
                })
                .waitSeconds(1.5)

                //Park
                .setTangent(Math.toRadians(120))
                .splineToConstantHeading(new Vector2d(50, -12), Math.toRadians(0))
                .addTemporalMarker(() -> {
                    robot.intake.setTargetPower(0);
                })
                .build();
    }
}
