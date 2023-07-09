package org.firstinspires.ftc.teamcode.Systems.Vision;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

import java.util.ArrayList;
import java.util.List;

public class ColorDetectionPipeline extends OpenCvPipeline {
    Mat hsv = new Mat();
    Mat mask = new Mat();

    Scalar lowerBound, upperBound;

    List<MatOfPoint> contours = new ArrayList<MatOfPoint>();

    public ColorDetectionPipeline(Scalar lowerBound, Scalar upperBound) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    @Override
    public Mat processFrame(Mat input) {
        Imgproc.cvtColor(input, hsv, Imgproc.COLOR_RGB2HSV);
        Core.inRange(hsv, lowerBound, upperBound, mask);

        Imgproc.findContours(mask, contours, new Mat(), Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);

        for (MatOfPoint contour : contours) {
            if (Imgproc.contourArea(contour) > 10) {
                Imgproc.rectangle(
                        input,
                        new Point(
                                Imgproc.boundingRect(contour).x,
                                Imgproc.boundingRect(contour).y),
                        new Point(
                                Imgproc.boundingRect(contour).x + Imgproc.boundingRect(contour).width,
                                Imgproc.boundingRect(contour).y + Imgproc.boundingRect(contour).height),
                        new Scalar(0, 255, 0), 2);
            }
        }

        return input;
    }

    public Scalar getLowerBound() {
        return lowerBound;
    }

    public ColorDetectionPipeline setLowerBound(Scalar lowerBound) {
        this.lowerBound = lowerBound;
        return this;
    }

    public Scalar getUpperBound() {
        return upperBound;
    }

    public ColorDetectionPipeline setUpperBound(Scalar upperBound) {
        this.upperBound = upperBound;
        return this;
    }
}