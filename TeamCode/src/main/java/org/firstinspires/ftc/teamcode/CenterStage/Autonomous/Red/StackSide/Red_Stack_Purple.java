package org.firstinspires.ftc.teamcode.CenterStage.Autonomous.Red.StackSide;

import com.acmerobotics.roadrunner.geometry.Pose2d;
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
        return new Pose2d(-12, -64, Math.toRadians(-90));
    }

    public TrajectorySequence leftSequence(Pose2d startPos) {
        return drive.trajectorySequenceBuilder(startPos)

                .build();
    }

    public TrajectorySequence centerSequence(Pose2d startPos) {
        return drive.trajectorySequenceBuilder(startPos)

                .build();
    }

    public TrajectorySequence rightSequence(Pose2d startPos) {
        return drive.trajectorySequenceBuilder(startPos)

                .build();
    }
}
