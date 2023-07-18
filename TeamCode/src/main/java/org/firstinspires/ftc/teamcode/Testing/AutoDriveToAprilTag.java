package org.firstinspires.ftc.teamcode.Testing;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.Systems.Gamepad.GamepadButtons;
import org.firstinspires.ftc.teamcode.Systems.Gamepad.GamepadButtons.Button;
import org.firstinspires.ftc.teamcode.Systems.Vision.FIRST_AprilTagDetector;
import org.firstinspires.ftc.teamcode.TeleOp.BaseTele;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

//@Disabled
@Config
@TeleOp(group = "Testing")
public class AutoDriveToAprilTag extends BaseTele {
    private FIRST_AprilTagDetector aprilTagDetector;
    private AprilTagDetection detectedTag;

    public static double TARGET_DISTANCE = 12;
    public static double SPEED_GAIN = 0.02;
    public static double STRAFE_GAIN = 0.015;
    public static double TURN_GAIN = 0.01;

    public static int TARGET_TAG = 0;

    private double forward, rightward, rotational;

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
    }

    public void loop() {
        ArrayList<AprilTagDetection> detections = aprilTagDetector.getDetections();
        for (AprilTagDetection detection : detections) {
            if (detection.metadata != null && detection.id == TARGET_TAG) {
                targetFound = true;
                detectedTag = detection;
                break;
            }
            targetFound = false;
        }

        if (targetFound) {
            telemetry.addLine("Tag " + TARGET_TAG + " found");
            telemetry.addData("Range",  "%5.1f inches", detectedTag.ftcPose.range);
            telemetry.addData("Bearing","%3.0f degrees", detectedTag.ftcPose.bearing);
            telemetry.addData("Yaw","%3.0f degrees", detectedTag.ftcPose.yaw);
        } else {
            telemetry.addLine("Target tag not found");
        }
        telemetry.addLine();

        if (driver.isDown(Button.A) && targetFound) {
            double distanceError = (detectedTag.ftcPose.range - TARGET_DISTANCE);
            double yawError = detectedTag.ftcPose.yaw;
            double headingError = detectedTag.ftcPose.bearing;

            forward = Range.clip(distanceError * SPEED_GAIN, -0.5, 0.5);
            rightward = Range.clip(-yawError * STRAFE_GAIN, -0.5, 0.5);
            rotational = Range.clip(headingError * TURN_GAIN, -0.5, 0.5);
        } else {
            forward = driver.getStickY(GamepadButtons.Stick.LEFT_STICK);
            rightward = driver.getStickX(GamepadButtons.Stick.LEFT_STICK);
            rotational = driver.getStickX(GamepadButtons.Stick.RIGHT_STICK);
        }

        drivetrain.standardDrive(driver.getStickY(GamepadButtons.Stick.LEFT_STICK), driver.getStickX(GamepadButtons.Stick.LEFT_STICK), driver.getStickX(GamepadButtons.Stick.RIGHT_STICK))
                .update();
    }
}
