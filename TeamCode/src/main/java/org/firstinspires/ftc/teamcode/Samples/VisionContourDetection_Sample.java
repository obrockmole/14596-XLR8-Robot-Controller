package org.firstinspires.ftc.teamcode.Samples;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Systems.Vision.ContourDetectionPipeline;
import org.firstinspires.ftc.teamcode.Systems.Vision.DrawStrategy;
import org.firstinspires.ftc.teamcode.Systems.Vision.VisionDetector;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

@Disabled
@TeleOp(group = "Samples", name = "Contour Detection Sample")
public class VisionContourDetection_Sample extends OpMode implements DrawStrategy {
    VisionDetector contourDetector;
    ContourDetectionPipeline pipeline;

    @Override
    public void init() {
        pipeline = new ContourDetectionPipeline(this, new Scalar(0, 0, 0), new Scalar(179, 255, 255), new Scalar(80, 100, 100), new Scalar(160, 200, 200), 100); //Initialize the pipeline.
        contourDetector = new VisionDetector(hardwareMap, "Webcam", pipeline); //Initialize the detector
        contourDetector.start(); //Start the detector.

        /*
            Note: The four Scalar parameters are the lower and upper bounds of the HSV color range to detect for both the first (weak) and second (strict) detections.
            OpenCV HSV ranges are 0-179 for H, 0-255 for S, and 0-255 for V.
            For RGB values, use detector.startRGB(new Scalar(R, G, B), new Scalar(R, G, B));

            The detection works by checking each pixel in the frame and seeing if it is within the specified range.
            It does this individually for the Hue, Saturation, and Value of each pixel.
            For example, it checks if the Hue of the pixel is between 170 and 179, the Saturation is between 110 and 255, and the Value is between 0 and 255.
            If all three of these conditions are met, the pixel is considered to be within the range.
         */
    }

    @Override
    public void loop() {}

    public void drawOnFrame(Mat frame) {
        Rect rect = Imgproc.boundingRect(contourDetector.getDetection());
        Imgproc.rectangle(frame, rect, new Scalar(0, 255, 0));
    }
}
