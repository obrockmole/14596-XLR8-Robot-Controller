package org.firstinspires.ftc.teamcode.CenterStage.Autonomous.Red.BackboardSide;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.CenterStage.Autonomous.BaseAuto;
import org.firstinspires.ftc.teamcode.RoadRunner.TrajectorySequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.Systems.Vision.VisionDetector;

@Autonomous(group = "Red", name = "Purple & Yellow - Red Backboard")
public class Red_Backboard_PY extends BaseAuto {
    public void initVision() {
        pipeline = getRedPipeline();
        contourDetector = new VisionDetector(hardwareMap, "Webcam", pipeline);
    }

    public Pose2d startPos() {
        return new Pose2d(12, -64, Math.toRadians(-90));
    }

    public TrajectorySequence leftSequence(Pose2d startPos) {
        return drive.trajectorySequenceBuilder(startPos)
                //Drive to spike mark
                .setTangent(Math.toRadians(70))
                .splineToLinearHeading(new Pose2d(10, -31, Math.toRadians(180)), Math.toRadians(180))

                //Place purple pixel
                .addTemporalMarker(() -> {
                    robot.intake.setTargetPower(-0.35);
                    //Extend lift
                })
                .waitSeconds(1.5)

                //Drive to backdrop
                .lineToConstantHeading(new Vector2d(36, -32))

                //Drop yellow pixel
                .addTemporalMarker(() -> {
                    robot.intake.setTargetPower(0);
                    robot.pixelClamp.setTargetPosition(0);
                })
                .waitSeconds(1)
                //Retract lift
                .addTemporalMarker(() -> {
                    //Retract lift
                })

                //Park
                .lineToConstantHeading(new Vector2d(50, -60))
                .build();
    }

    public TrajectorySequence centerSequence(Pose2d startPos) {
        return drive.trajectorySequenceBuilder(startPos)
                //Drive to spike mark
                .lineToLinearHeading(new Pose2d(24, -25, Math.toRadians(180)))

                //Place purple pixel
                .addTemporalMarker(() -> {
                    robot.intake.setTargetPower(-0.35);
                })
                .waitSeconds(1.5)

                //Drive to backdrop
                .lineToConstantHeading(new Vector2d(36, -36))

                //Drop yellow pixel
                .addTemporalMarker(() -> {
                    robot.intake.setTargetPower(0);
                    robot.pixelClamp.setTargetPosition(0);
                })
                .waitSeconds(1)
                //Retract lift
                .addTemporalMarker(() -> {
                    //Retract lift
                })

                //Park
                .lineToConstantHeading(new Vector2d(50, -60))
                .build();
    }

    public TrajectorySequence rightSequence(Pose2d startPos) {
        return drive.trajectorySequenceBuilder(startPos)
                //Drive to spike mark
                .lineToLinearHeading(new Pose2d(33, -30, Math.toRadians(180)))

                //Place purple pixel
                .addTemporalMarker(() -> {
                    robot.intake.setTargetPower(-0.35);
                })
                .waitSeconds(1.5)

                //Drive to backdrop
                .lineToConstantHeading(new Vector2d(36, -40))

                //Drop yellow pixel
                .addTemporalMarker(() -> {
                    robot.intake.setTargetPower(0);
                    robot.pixelClamp.setTargetPosition(0);
                })
                .waitSeconds(1)
                //Retract lift
                .addTemporalMarker(() -> {
                    //Retract lift
                })

                //Park
                .lineToConstantHeading(new Vector2d(50, -60))
                .build();
    }
}
