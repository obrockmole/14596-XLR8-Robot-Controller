package org.firstinspires.ftc.teamcode.Systems.Vision;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

import java.util.ArrayList;
import java.util.List;

public class ContourDetectionPipeline extends OpenCvPipeline {
    Mat hsv = new Mat();
    Mat mask = new Mat();

    Scalar lowerBound, upperBound;

    List<MatOfPoint> contours = new ArrayList<>();

    public ContourDetectionPipeline(Scalar lowerBound, Scalar upperBound) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    @Override
    public Mat processFrame(Mat input) {
        Imgproc.cvtColor(input, hsv, Imgproc.COLOR_RGB2HSV);
        Core.inRange(hsv, lowerBound, upperBound, mask);

        contours.clear();
        Imgproc.findContours(mask, contours, new Mat(), Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);

        for (MatOfPoint contour : contours) {
            if (Imgproc.contourArea(contour) > 50) {
                Rect rect = Imgproc.boundingRect(contour);
                Imgproc.rectangle(input, new Point(rect.x, rect.y),
                        new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 255, 0), 2);
            }
        }

        return input;
    }

    public Scalar getLowerBound() {
        return lowerBound;
    }

    public ContourDetectionPipeline setLowerBound(Scalar lowerBound) {
        this.lowerBound = lowerBound;
        return this;
    }

    public Scalar getUpperBound() {
        return upperBound;
    }

    public ContourDetectionPipeline setUpperBound(Scalar upperBound) {
        this.upperBound = upperBound;
        return this;
    }
}
