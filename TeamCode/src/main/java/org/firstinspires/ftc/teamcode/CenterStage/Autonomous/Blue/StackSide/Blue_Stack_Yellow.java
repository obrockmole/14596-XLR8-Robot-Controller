package org.firstinspires.ftc.teamcode.CenterStage.Autonomous.Blue.StackSide;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.CenterStage.Autonomous.BaseAuto;
import org.firstinspires.ftc.teamcode.RoadRunner.TrajectorySequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.Systems.Vision.VisionDetector;

@Disabled
@Autonomous(group = "Blue", name = "Yellow - Blue Stack")
public class Blue_Stack_Yellow extends BaseAuto {
    public void initVision() {
        pipeline = getBluePipeline();
        contourDetector = new VisionDetector(hardwareMap, "Webcam", pipeline);
    }

    public Pose2d startPos() {
        return new Pose2d(-36, 64, Math.toRadians(90));
    }

    public TrajectorySequence leftSequence(Pose2d startPos) {
        return drive.trajectorySequenceBuilder(startPos)
                //Drive under truss
                .lineToLinearHeading(new Pose2d(-36, 12, Math.toRadians(180)))
                .lineToConstantHeading(new Vector2d(34, 12))
                //Extend lift
                .addTemporalMarker(() -> {
                    //Extend lift
                })

                //Drive to backdrop
                .lineToConstantHeading(new Vector2d(36, 40))

                //Drop yellow pixel
                .addTemporalMarker(() -> {
                    robot.pixelClamp.setTargetPosition(0);
                })
                .waitSeconds(1)
                //Retract lift
                .addTemporalMarker(() -> {
                    //Retract lift
                })

                //Park
                .lineToConstantHeading(new Vector2d(50, 12))
                .build();
    }

    public TrajectorySequence centerSequence(Pose2d startPos) {
        return drive.trajectorySequenceBuilder(startPos)
                //Drive under truss
                .lineToLinearHeading(new Pose2d(-36, 12, Math.toRadians(180)))
                .lineToConstantHeading(new Vector2d(34, 12))
                //Extend lift
                .addTemporalMarker(() -> {
                    //Extend lift
                })

                //Drive to backdrop
                .lineToConstantHeading(new Vector2d(36, 36))

                //Drop yellow pixel
                .addTemporalMarker(() -> {
                    robot.pixelClamp.setTargetPosition(0);
                })
                .waitSeconds(1)
                //Retract lift
                .addTemporalMarker(() -> {
                    //Retract lift
                })

                //Park
                .lineToConstantHeading(new Vector2d(50, 12))
                .build();
    }

    public TrajectorySequence rightSequence(Pose2d startPos) {
        return drive.trajectorySequenceBuilder(startPos)
                //Drive under truss
                .lineToLinearHeading(new Pose2d(-36, 12, Math.toRadians(180)))
                .lineToConstantHeading(new Vector2d(34, 12))
                //Extend lift
                .addTemporalMarker(() -> {
                    //Extend lift
                })

                //Drive to backdrop
                .lineToConstantHeading(new Vector2d(36, 32))

                //Drop yellow pixel
                .addTemporalMarker(() -> {
                    robot.pixelClamp.setTargetPosition(0);
                })
                .waitSeconds(1)
                //Retract lift
                .addTemporalMarker(() -> {
                    //Retract lift
                })

                //Park
                .lineToConstantHeading(new Vector2d(50, 12))
                .build();
    }
}
