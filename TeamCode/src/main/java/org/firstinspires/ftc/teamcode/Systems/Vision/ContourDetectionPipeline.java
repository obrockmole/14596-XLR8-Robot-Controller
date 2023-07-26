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
    Mat lowMask = new Mat();
    Mat lowColoredMask = new Mat();
    Mat scaledMask = new Mat();
    Mat strictMask = new Mat();
    Mat strictColoredMask = new Mat();

    Scalar weakLowHSV, weakHighHSV, strictLowHSV, strictHighHSV;

    ArrayList<MatOfPoint> contours = new ArrayList<>();

    public ContourDetectionPipeline(Scalar weakLowHSV, Scalar weakHighHSV, Scalar strictLowHSV, Scalar strictHighHSV) {
        this.weakLowHSV = weakLowHSV;
        this.weakHighHSV = weakHighHSV;
        this.strictLowHSV = strictLowHSV;
        this.strictHighHSV = strictHighHSV;
    }

    @Override
    public Mat processFrame(Mat input) {
        //Convert input to HSV
        Imgproc.cvtColor(input, hsv, Imgproc.COLOR_RGB2HSV);

        //Apply weak detection and output 'lowMask'
        Core.inRange(hsv, weakLowHSV, weakHighHSV, lowMask);

        //Reapply the colors from 'hsv' to the white areas in 'lowMask' and output 'lowColoredMask'
        Core.bitwise_and(hsv, hsv, lowColoredMask, lowMask);

        //Find the average color in 'lowColoredMask'
        Scalar average = Core.mean(lowColoredMask, lowMask);

        //Scale 'lowColoredMask' values based on the average color and output 'scaledMask;
        lowColoredMask.convertTo(scaledMask, -1, 150 / average.val[1], 0);

        //Apply strict detection and output 'strictMask'
        Core.inRange(scaledMask, strictLowHSV, strictHighHSV, strictMask);

        //Find contours in 'strictMask'
        contours.clear();
        Imgproc.findContours(strictMask, contours, new Mat(), Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);

        for (MatOfPoint contour : contours) {
            if (Imgproc.contourArea(contour) > 50) {
                Rect rect = Imgproc.boundingRect(contour);
                Imgproc.rectangle(input, new Point(rect.x, rect.y),
                        new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 255, 0), 2);
            }
        }

        return input;
    }

    public Scalar getWeakLowHSV() {
        return weakLowHSV;
    }

    public ContourDetectionPipeline setWeakLowHSV(Scalar weakLowHSV) {
        this.weakLowHSV = weakLowHSV;
        return this;
    }

    public Scalar getWeakHighHSV() {
        return weakHighHSV;
    }

    public ContourDetectionPipeline setWeakHighHSV(Scalar weakHighHSV) {
        this.weakHighHSV = weakHighHSV;
        return this;
    }

    public Scalar getStrictLowHSV() {
        return strictLowHSV;
    }

    public ContourDetectionPipeline setStrictLowHSV(Scalar strictLowHSV) {
        this.strictLowHSV = strictLowHSV;
        return this;
    }

    public Scalar getStrictHighHSV() {
        return strictHighHSV;
    }

    public ContourDetectionPipeline setStrictHighHSV(Scalar strictHighHSV) {
        this.strictHighHSV = strictHighHSV;
        return this;
    }

    public ContourDetectionPipeline setWeakHSV(Scalar weakLowHSV, Scalar weakHighHSV) {
        this.weakLowHSV = weakLowHSV;
        this.weakHighHSV = weakHighHSV;
        return this;
    }

    public ContourDetectionPipeline setStrictHSV(Scalar strictLowHSV, Scalar strictHighHSV) {
        this.strictLowHSV = strictLowHSV;
        this.strictHighHSV = strictHighHSV;
        return this;
    }

    public ContourDetectionPipeline setHSV(Scalar weakLowHSV, Scalar weakHighHSV, Scalar strictLowHSV, Scalar strictHighHSV) {
        this.weakLowHSV = weakLowHSV;
        this.weakHighHSV = weakHighHSV;
        this.strictLowHSV = strictLowHSV;
        this.strictHighHSV = strictHighHSV;
        return this;
    }

    public ArrayList<MatOfPoint> getContours() {
        return new ArrayList<>(contours);
    }
}
