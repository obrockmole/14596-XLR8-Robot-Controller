package org.firstinspires.ftc.teamcode.CenterStage.Autonomous.Red.BackboardSide;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.CenterStage.Autonomous.BaseAuto;
import org.firstinspires.ftc.teamcode.RoadRunner.TrajectorySequence.TrajectorySequenceBuilder;
import org.firstinspires.ftc.teamcode.Systems.Vision.ContourDetectionPipeline;
import org.firstinspires.ftc.teamcode.Systems.Vision.VisionDetector;
import org.opencv.core.Scalar;

//TODO: Test and find proper location values
@Disabled
@Autonomous(group = "Red", name = "Yellow & Park - Red Side Backboard")
public class Red_Backboard_YellowPark extends BaseAuto {
    public void initVision() {
        pipeline = new ContourDetectionPipeline(this, new Scalar(170, 0, 0), new Scalar(179, 255, 255), new Scalar(20, 100, 110), new Scalar(100, 200, 200), 100);
        contourDetector = new VisionDetector(hardwareMap, "Webcam", pipeline);
    }

    public Pose2d startPos() {
        return new Pose2d(12, -64, Math.toRadians(90));
    }

    public Pose2d spikePos() {
        return new Pose2d();
    }

    public Pose2d backdropPos() {
        switch (finalPropPosition) {
            case LEFT:
                return new Pose2d(40, -40, Math.toRadians(0));
            case RIGHT:
                return new Pose2d(40, -50, Math.toRadians(0));
            default:
                return new Pose2d(40, -60, Math.toRadians(0));
        }
    }

    public TrajectorySequenceBuilder pathBuilder(Pose2d startPos, Pose2d spikePos, Pose2d backdropPos) {
        return drive.trajectorySequenceBuilder(startPos)
                //Drive to backboard and place yellow pixel
                .lineToLinearHeading(backdropPos)
                .waitSeconds(1)

                //Park
                .lineTo(new Vector2d(46, -58));
    }
}
