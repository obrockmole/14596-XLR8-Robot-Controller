package org.firstinspires.ftc.teamcode.Samples;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Systems.Vision.AprilTagDetector;

@Disabled
@TeleOp(group = "Samples")
public class VisionAprilTagDetection_Sample extends OpMode {
    AprilTagDetector detector;

    @Override
    public void init() {
        detector = new AprilTagDetector(hardwareMap, "Webcam"); //Initialize the detector.
        detector.start(); //Start the detector.
        //To learn how this detector works, see https://april.eecs.umich.edu/media/pdfs/olson2011tags.pdf.
    }

    @Override
    public void loop() {}
}
