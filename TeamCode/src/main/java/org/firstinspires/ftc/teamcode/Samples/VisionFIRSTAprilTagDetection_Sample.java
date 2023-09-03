package org.firstinspires.ftc.teamcode.Samples;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Systems.Vision.FIRST_AprilTagDetector;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;

import java.util.ArrayList;

@Disabled
@TeleOp(group = "Samples", name = "FIRST April Tag Detection Sample")
public class VisionFIRSTAprilTagDetection_Sample extends OpMode {
    FIRST_AprilTagDetector aprilTagDetector;
    ArrayList<AprilTagDetection> detectedTags;

    @Override
    public void init() {
        aprilTagDetector = new FIRST_AprilTagDetector(hardwareMap, "Webcam");
        //TODO: Get proper calibration values for the camera
        aprilTagDetector.initCamera(50d, 50d, 50d, 50d); //Initialize the camera with the focal length and center of the camera

        detectedTags = new ArrayList<>();
    }

    @Override
    public void loop() {
        detectedTags = aprilTagDetector.getDetections(); //Get the list of detected tags
        telemetry.addData("Tags Detected", detectedTags.size()); //Log the number of detected tags

        //Log the data for all detected tags
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
