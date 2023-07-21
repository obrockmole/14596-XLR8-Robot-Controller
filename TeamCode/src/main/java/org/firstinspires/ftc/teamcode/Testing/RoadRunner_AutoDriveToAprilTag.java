package org.firstinspires.ftc.teamcode.Testing;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.RoadRunner.Drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.RoadRunner.TrajectorySequence.TrajectorySequenceBuilder;
import org.firstinspires.ftc.teamcode.Systems.Gamepad.GamepadButtons;
import org.firstinspires.ftc.teamcode.Systems.Gamepad.GamepadButtons.Button;
import org.firstinspires.ftc.teamcode.Systems.Vision.FIRST_AprilTagDetector;
import org.firstinspires.ftc.teamcode.TeleOp.BaseTele;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;

import java.util.ArrayList;

//@Disabled
@Config
@Autonomous(group = "Testing")
public class RoadRunner_AutoDriveToAprilTag extends OpMode {
    private SampleMecanumDrive drive;
    private TrajectorySequenceBuilder trajSeq;

    private FIRST_AprilTagDetector aprilTagDetector;
    private AprilTagDetection detectedTag;

    public static int TARGET_TAG = 0;

    private boolean targetFound = false, calibrated = false;

    public void init() {
        aprilTagDetector = new FIRST_AprilTagDetector(hardwareMap, "Webcam");
        aprilTagDetector.initCamera();

        if (aprilTagDetector.getVisionPortal().getCameraState() != VisionPortal.CameraState.STREAMING) {
            telemetry.addData("Camera", "Not streaming");
            telemetry.update();
            while (!calibrated && aprilTagDetector.getVisionPortal().getCameraState() != VisionPortal.CameraState.STREAMING) {
                aprilTagDetector.sleep(20);
            }
            calibrated = true;
        }

        detectedTag = new AprilTagDetection();

        drive = new SampleMecanumDrive(hardwareMap);
    }

    public void start() {
        ArrayList<AprilTagDetection> detections = aprilTagDetector.getDetections();
        for (AprilTagDetection detection : detections) {
            if (detection.metadata != null && detection.id == TARGET_TAG) {
                targetFound = true;
                detectedTag = detection;
                break;
            }
        }

        if (targetFound) {
            double d = detectedTag.ftcPose.range - 10, x, y, heading = -detectedTag.ftcPose.yaw;

            x = d * Math.sin(Math.toDegrees(detectedTag.ftcPose.bearing));
            y = Math.sqrt(Math.pow(d, 2) - Math.pow(x, 2));

            trajSeq = drive.trajectorySequenceBuilder(new Pose2d(0, 0, 0));
            trajSeq.lineToLinearHeading(new Pose2d(x, y, heading));
        }

        drive.followTrajectorySequenceAsync(trajSeq.build());
    }

    public void loop() {
        if (!targetFound) {
            ArrayList<AprilTagDetection> detections = aprilTagDetector.getDetections();
            for (AprilTagDetection detection : detections) {
                if (detection.metadata != null && detection.id == TARGET_TAG) {
                    targetFound = true;
                    detectedTag = detection;
                    break;
                }
            }

            if (targetFound) {
                double d = detectedTag.ftcPose.range - 10, x, y, heading = -detectedTag.ftcPose.yaw;

                x = d * Math.sin(Math.toDegrees(detectedTag.ftcPose.bearing));
                y = Math.sqrt(Math.pow(d, 2) - Math.pow(x, 2));

                if (detectedTag.ftcPose.x < 0)
                    x *= -1;

                trajSeq = drive.trajectorySequenceBuilder(new Pose2d(0, 0, 0));
                trajSeq.lineToLinearHeading(new Pose2d(x, y, heading));
            }
        }

        drive.update();
    }
}
