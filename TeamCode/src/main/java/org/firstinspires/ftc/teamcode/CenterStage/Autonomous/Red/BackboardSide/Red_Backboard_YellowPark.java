package org.firstinspires.ftc.teamcode.CenterStage.Autonomous.Red.BackboardSide;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.CenterStage.Autonomous.BaseAuto;
import org.firstinspires.ftc.teamcode.Systems.Vision.ContourDetectionPipeline;
import org.firstinspires.ftc.teamcode.Systems.Vision.VisionDetector;
import org.opencv.core.Scalar;

@Disabled
@Autonomous(group = "Red", name = "Yellow & Park - Red Side Backboard")
public class Red_Backboard_YellowPark extends BaseAuto {
    public void initVision() {
        pipeline = new ContourDetectionPipeline(this, new Scalar(170, 0, 0), new Scalar(179, 255, 255), new Scalar(20, 100, 110), new Scalar(100, 200, 200), 100);
        contourDetector = new VisionDetector(hardwareMap, "Webcam", pipeline);
    }

    public void initTrajectory() {
        sequence = drive.trajectorySequenceBuilder(new Pose2d(12, -64, Math.toRadians(90)))
                //Drive to backboard and place yellow pixel
                .lineToLinearHeading(new Pose2d(40, -50, Math.toRadians(30)))
                .waitSeconds(1)

                //Park
                .lineTo(new Vector2d(46, -58))
                .build();
    }
}
