package org.firstinspires.ftc.teamcode.CenterStage.Autonomous.Red.StackSide;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.CenterStage.Autonomous.BaseAuto;
import org.firstinspires.ftc.teamcode.RoadRunner.TrajectorySequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.Systems.Vision.VisionDetector;

@Autonomous(group = "Red", name = "Park - Red Stack")
public class Red_Stack_Park extends BaseAuto {
    public void initVision() {
        pipeline = getRedPipeline();
        contourDetector = new VisionDetector(hardwareMap, "Webcam", pipeline);
    }

    public Pose2d startPos() {
        return new Pose2d(-36, -64, Math.toRadians(-90));
    }

    public TrajectorySequence leftSequence(Pose2d startPos) {
        return centerSequence(startPos);
    }

    public TrajectorySequence centerSequence(Pose2d startPos) {
        return drive.trajectorySequenceBuilder(startPos)
                //Park
                .lineToLinearHeading(new Pose2d(-36, -12, Math.toRadians(0)))
                .lineToConstantHeading(new Vector2d(54, -12))

                //Spit pixels
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
