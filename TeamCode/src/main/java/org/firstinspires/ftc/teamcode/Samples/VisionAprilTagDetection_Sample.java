package org.firstinspires.ftc.teamcode.Samples;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Systems.Vision.AprilTagDetectionPipeline;
import org.firstinspires.ftc.teamcode.Systems.Vision.DrawStrategy;
import org.firstinspires.ftc.teamcode.Systems.Vision.VisionDetector;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

@Disabled
@TeleOp(group = "Samples", name = "April Tag Detection Sample")
public class VisionAprilTagDetection_Sample extends OpMode implements DrawStrategy {
    VisionDetector aprilTagDetector;
    AprilTagDetectionPipeline pipeline;

    @Override
    public void init() {
        pipeline = new AprilTagDetectionPipeline(this, 1, 50d, 50d, 50d, 50d, 50d); //Initialize the pipeline.
        aprilTagDetector = new VisionDetector(hardwareMap, "Webcam", pipeline); //Initialize the detector.
        aprilTagDetector.start(); //Start the detector.
        //To learn how this detector works, see https://april.eecs.umich.edu/media/pdfs/olson2011tags.pdf.
    }

    @Override
    public void loop() {}

    @Override
    public void drawOnFrame(Mat frame) {
        Rect rect = Imgproc.boundingRect(aprilTagDetector.getDetection());
        Imgproc.rectangle(frame, rect, new Scalar(0, 255, 0));
    }
}
