package org.firstinspires.ftc.teamcode.CenterStage.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Systems.Gamepad.Gamepad;
import org.firstinspires.ftc.teamcode.CenterStage.Robot;
import org.firstinspires.ftc.teamcode.Systems.Vision.FIRST_AprilTagDetector;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;

import java.util.ArrayList;

public class BaseTele extends OpMode {
    Gamepad driver;
    Gamepad manipulator;

    Robot robot;

    private FIRST_AprilTagDetector aprilTagDetector;
    private boolean tagDetected = false;

    public void init() {
        driver = new Gamepad(gamepad1);
        manipulator = new Gamepad(gamepad2);

        robot = new Robot(hardwareMap);

        aprilTagDetector = new FIRST_AprilTagDetector(hardwareMap, "Webcam");
        //TODO: Get proper calibration values for the camera
        aprilTagDetector.initCamera(50d, 50d, 50d, 50d);
    }

    public void loop() {}

    public void update() {
        driver.update();
        manipulator.update();

        robot.update();

        ArrayList<AprilTagDetection> detections = aprilTagDetector.getDetections();
        ArrayList<AprilTagDetection> backdropDetections = new ArrayList<>();
        if (detections.size() > 0) {
            for (AprilTagDetection detection : detections) {
                if (detection.metadata != null && detection.id >= 1 && detection.id <= 6) {
                    tagDetected = true;
                    backdropDetections.add(detection);
                }
            }
        } else {
            tagDetected = false;
        }

        if (tagDetected && backdropDetections.size() > 0) {
            AprilTagDetection closestTag = backdropDetections.get(0);
            for (AprilTagDetection detection : backdropDetections) {
                if (detection.ftcPose.range < closestTag.ftcPose.range)
                    closestTag = detection;
            }
        }
    }

    public void log() {
        robot.log(telemetry);

        telemetry.addLine("-----Other-----");
        telemetry.addData("Runtime", getRuntime());
        telemetry.update();
    }
}
