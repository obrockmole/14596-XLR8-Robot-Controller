package org.firstinspires.ftc.teamcode.CenterStage.Autonomous.Red.BackboardSide;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.CenterStage.Autonomous.BaseAuto;
import org.firstinspires.ftc.teamcode.Systems.Vision.ContourDetectionPipeline;
import org.firstinspires.ftc.teamcode.Systems.Vision.VisionDetector;
import org.opencv.core.Scalar;

@Autonomous(group = "Red", name = "Purple & Park - Red Side Backboard")
public class Red_Backboard_PurplePark extends BaseAuto {
    public void initVision() {
        pipeline = new ContourDetectionPipeline(this, new Scalar(170, 0, 0), new Scalar(179, 255, 255), new Scalar(20, 100, 110), new Scalar(100, 200, 200), 100);
        contourDetector = new VisionDetector(hardwareMap, "Webcam", pipeline);
    }

    public void initTrajectory() {
        sequence = drive.trajectorySequenceBuilder(new Pose2d(12, -64, Math.toRadians(90)))
                //Place purple pixel
                .lineTo(new Vector2d(12, -36))
                .waitSeconds(1)

                //Park
                .setTangent(Math.toRadians(-90))
                .splineToConstantHeading(new Vector2d(46, -58), Math.toRadians(0))
                .build();
    }
}
