package org.firstinspires.ftc.teamcode.CenterStage.Autonomous.Red.BackboardSide;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.CenterStage.Autonomous.BaseAuto;
import org.firstinspires.ftc.teamcode.RoadRunner.TrajectorySequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.Systems.Vision.VisionDetector;

@Disabled
@Autonomous(group = "Red", name = "Yellow - Red Backboard")
public class Red_Backboard_Yellow extends BaseAuto {
    public void initVision() {
        pipeline = getRedPipeline();
        contourDetector = new VisionDetector(hardwareMap, "Webcam", pipeline);
    }

    public Pose2d startPos() {
        return new Pose2d(12, -64, Math.toRadians(-90));
    }

    public TrajectorySequence leftSequence(Pose2d startPos) {
        return drive.trajectorySequenceBuilder(startPos)
                //Extend lift
                .addTemporalMarker(() -> {
                    //Extend lift
                })
                //Drive to backdrop
                .setTangent(Math.toRadians(70))
                .splineToLinearHeading(new Pose2d(36, -32, Math.toRadians(180)), Math.toRadians(90))

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
                .lineToConstantHeading(new Vector2d(50, -60))
                .addTemporalMarker(() -> {
                    robot.intake.setTargetPower(0);
                })
                .build();
    }

    public TrajectorySequence centerSequence(Pose2d startPos) {
        return drive.trajectorySequenceBuilder(startPos)
                //Extend lift
                .addTemporalMarker(() -> {
                    //Extend lift
                })
                //Drive to backdrop
                .setTangent(Math.toRadians(70))
                .splineToLinearHeading(new Pose2d(36, -36, Math.toRadians(180)), Math.toRadians(90))

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
                .lineToConstantHeading(new Vector2d(50, -60))
                .addTemporalMarker(() -> {
                    robot.intake.setTargetPower(0);
                })
                .build();
    }

    public TrajectorySequence rightSequence(Pose2d startPos) {
        return drive.trajectorySequenceBuilder(startPos)
                //Extend lift
                .addTemporalMarker(() -> {
                    //Extend lift
                })
                //Drive to backdrop
                .setTangent(Math.toRadians(70))
                .splineToLinearHeading(new Pose2d(36, -40, Math.toRadians(180)), Math.toRadians(90))

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
                .lineToConstantHeading(new Vector2d(50, -60))
                .addTemporalMarker(() -> {
                    robot.intake.setTargetPower(0);
                })
                .build();
    }
}
