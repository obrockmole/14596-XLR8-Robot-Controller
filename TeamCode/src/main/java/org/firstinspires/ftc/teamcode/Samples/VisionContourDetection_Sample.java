package org.firstinspires.ftc.teamcode.Samples;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Systems.Vision.ContourDetector;
import org.opencv.core.Scalar;

@Disabled
@TeleOp(group = "Samples")
public class VisionContourDetection_Sample extends OpMode {
    ContourDetector detector;

    @Override
    public void init() {
        detector = new ContourDetector(hardwareMap, "Webcam"); //Initialize the detector
        detector.start(new Scalar(0, 0, 0), new Scalar(179, 255, 255), new Scalar(80, 100, 100), new Scalar(160, 200, 200)); //Start the detector.
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
}
