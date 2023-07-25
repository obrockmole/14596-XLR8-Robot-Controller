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

    public static int TARGET_TAG = 1;

    private boolean targetFound = false;

    public void init() {
        aprilTagDetector = new FIRST_AprilTagDetector(hardwareMap, "Webcam");
        //TODO: Get proper calibration values for the camera
        aprilTagDetector.initCamera(50d, 50d, 50d, 50d);

        detectedTag = new AprilTagDetection();

        drive = new SampleMecanumDrive(hardwareMap);
        trajSeq = drive.trajectorySequenceBuilder(new Pose2d(0, 0, 0));
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
            setTrajSeq();
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
                setTrajSeq();
                drive.followTrajectorySequenceAsync(trajSeq.build());
            }
        }

        drive.update();
    }

    public void setTrajSeq() {
        double heading = detectedTag.ftcPose.yaw;
        double m = -1 / Math.tan(Math.toRadians(heading));

        double x, diff = 10 / Math.sqrt(1 + m * m);
        if (heading > 0)
            x = detectedTag.ftcPose.x + diff;
        else
            x = detectedTag.ftcPose.x - diff;
        double y = m * (x - detectedTag.ftcPose.x) + detectedTag.ftcPose.y;

        trajSeq = drive.trajectorySequenceBuilder(new Pose2d(0, 0, 0));
        trajSeq.lineToLinearHeading(new Pose2d(x, y, heading));
    }
}
