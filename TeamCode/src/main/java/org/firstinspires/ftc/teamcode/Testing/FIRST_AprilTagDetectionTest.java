package org.firstinspires.ftc.teamcode.Testing;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.Systems.Gamepad.GamepadButtons;
import org.firstinspires.ftc.teamcode.Systems.Gamepad.GamepadButtons.Button;
import org.firstinspires.ftc.teamcode.Systems.Vision.FIRST_AprilTagDetector;
import org.firstinspires.ftc.teamcode.TeleOp.BaseTele;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;

import java.util.ArrayList;

//@Disabled
@Config
@TeleOp(group = "Testing")
public class FIRST_AprilTagDetectionTest extends BaseTele {
    private FIRST_AprilTagDetector aprilTagDetector;
    private ArrayList<AprilTagDetection> detectedTags;

    private boolean calibrated = false;

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

        detectedTags = new ArrayList<>();
    }

    public void loop() {
        detectedTags = aprilTagDetector.getDetections();
        telemetry.addData("Tags Detected", detectedTags.size());

        for (AprilTagDetection detection : detectedTags) {
            telemetry.addData("ID", detection.id);
            if (detection.metadata != null) {
                telemetry.addData("X, Y, Z","%6.1f in, %6.1f in, %6.1f in", detection.ftcPose.x, detection.ftcPose.y, detection.ftcPose.z);
                telemetry.addData("Pitch, Roll, Yaw", "%6.1f deg, %6.1f deg, %6.1f deg", detection.ftcPose.pitch, detection.ftcPose.roll, detection.ftcPose.yaw);
                telemetry.addData("Dist, Bear, Elev", "%6.1f in, %6.1f deg, %6.1f deg", detection.ftcPose.range, detection.ftcPose.bearing, detection.ftcPose.elevation);
            } else {
                telemetry.addData("Center X, Y", "%6.0f px, %6.0f px", detection.center.x, detection.center.y);
            }
            telemetry.addLine();
        }

        telemetry.update();
    }
}
