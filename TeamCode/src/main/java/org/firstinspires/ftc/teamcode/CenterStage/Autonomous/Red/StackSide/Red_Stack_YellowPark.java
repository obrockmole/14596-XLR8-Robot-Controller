package org.firstinspires.ftc.teamcode.CenterStage.Autonomous.Red.StackSide;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.CenterStage.Autonomous.BaseAuto;
import org.firstinspires.ftc.teamcode.RoadRunner.TrajectorySequence.TrajectorySequenceBuilder;
import org.firstinspires.ftc.teamcode.Systems.Vision.ContourDetectionPipeline;
import org.firstinspires.ftc.teamcode.Systems.Vision.VisionDetector;
import org.opencv.core.Scalar;

//FIXME: Update to new version of BaseAuto
@Disabled
@Autonomous(group = "Red", name = "Yellow & Park - Red Side Stack")
public class Red_Stack_YellowPark extends BaseAuto {
    public void initVision() {
        pipeline = new ContourDetectionPipeline(this, new Scalar(170, 0, 0), new Scalar(179, 255, 255), new Scalar(20, 100, 110), new Scalar(100, 200, 200), 100);
        contourDetector = new VisionDetector(hardwareMap, "Webcam", pipeline);
    }

    @Override
    public Pose2d startPos() {
        return null;
    }

    @Override
    public Pose2d spikePos() {
        return null;
    }

    @Override
    public Pose2d backdropPos() {
        return null;
    }

    @Override
    public TrajectorySequenceBuilder pathBuilder(Pose2d startPos, Pose2d spikePos, Pose2d backdropPos) {
        return drive.trajectorySequenceBuilder(new Pose2d(-36, -64, Math.toRadians(90)))
                //Drive to backboard and place yellow pixel
                .setTangent(Math.toRadians(90))
                .splineToSplineHeading(new Pose2d(-32, -12, Math.toRadians(0)), Math.toRadians(0))
                .splineTo(new Vector2d(18, -12), Math.toRadians(0))
                .splineToSplineHeading(new Pose2d(40, -26, Math.toRadians(-20)), Math.toRadians(-60))
                .waitSeconds(1)

                //Park
                .lineTo(new Vector2d(44, -12));
    }
}
