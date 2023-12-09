package org.firstinspires.ftc.teamcode.Systems.Vision;

import com.arcrobotics.ftclib.geometry.Vector2d;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.opencv.imgproc.Moments;
import org.openftc.easyopencv.OpenCvPipeline;

import java.util.ArrayList;

public class ContourDetectionPipeline extends OpenCvPipeline {
    public enum PropPositions {
        LEFT,
        RIGHT,
        CENTER,
        NULL
    }

    private final Mat hsv = new Mat();
    private final Mat weakMask = new Mat();
    private final Mat coloredWeakMask = new Mat();
    private final Mat scaledMask = new Mat();
    private final Mat strictMask = new Mat();

    private final Mat kernelErosion, kernelDilation;

    private final Scalar weakLowHSV, weakHighHSV, strictLowHSV, strictHighHSV;

    private final double minArea;
    private final double leftBounds;
    private final double rightBounds;

    private ArrayList<MatOfPoint> contours;
    private MatOfPoint largestContour;
    private Vector2d largestContourPos;
    private double largestContourArea;

    private PropPositions previousPropPosition, currentPropPosition;

    public ContourDetectionPipeline(Scalar weakLowHSV, Scalar weakHighHSV, Scalar strictLowHSV, Scalar strictHighHSV, double minArea, double leftBounds, double rightBounds) {
        this.weakLowHSV = weakLowHSV;
        this.weakHighHSV = weakHighHSV;
        this.strictLowHSV = strictLowHSV;
        this.strictHighHSV = strictHighHSV;

        this.minArea = minArea;
        this.leftBounds = leftBounds;
        this.rightBounds = rightBounds;

        kernelErosion = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(5, 5));
        kernelDilation = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(4, 4));
    }

    public Mat processFrame(Mat input) {
        //Convert input to HSV
        Imgproc.cvtColor(input, hsv, Imgproc.COLOR_RGB2HSV);

        //Apply weak detection and output 'weakMask'
        Core.inRange(hsv, weakLowHSV, weakHighHSV, weakMask);

        //Erode and dilate 'weakMask' and output 'weakMask'
        Imgproc.erode(weakMask, weakMask, kernelErosion);
        Imgproc.dilate(weakMask, weakMask, kernelDilation);

        //Reapply the colors from 'hsv' to the white areas in 'weakMask' and output 'coloredWeakMask'
        coloredWeakMask.release();
        Core.bitwise_not(hsv, coloredWeakMask, weakMask);

        //Find the average color in 'coloredWeakMask'
        Scalar average = Core.mean(coloredWeakMask, weakMask);

        //Scale 'coloredWeakMask' values based on the average color and output 'scaledMask;
        coloredWeakMask.convertTo(scaledMask, -1, 150 / average.val[1], 0);

        //Apply strict detection and output 'strictMask'
        Core.inRange(scaledMask, strictLowHSV, strictHighHSV, strictMask);

        //Find contours in 'strictMask'
        contours.clear();
        Imgproc.findContours(strictMask, contours, new Mat(), Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);

        largestContourArea = -1;
        largestContour = null;

        for (MatOfPoint contour : contours) {
            double area = Imgproc.contourArea(contour);
            if (area > largestContourArea && area > minArea) {
                largestContour = contour;
                largestContourArea = area;
            }
        }

        if (largestContour != null) {
            Moments moment = Imgproc.moments(largestContour);
            largestContourPos = new Vector2d(moment.m10 / moment.m00, moment.m01 / moment.m00);

            Rect rect = Imgproc.boundingRect(largestContour);
            Imgproc.rectangle(input, rect, new Scalar(0, 255, 0));
        } else {
            largestContourPos = new Vector2d(-1, -1);
        }

        Imgproc.line(input, new Point(leftBounds, 0), new Point(leftBounds, input.rows()), new Scalar(0, 0, 255));
        Imgproc.line(input, new Point(rightBounds, 0), new Point(rightBounds, input.rows()), new Scalar(0, 0, 255));

        PropPositions propPosition;
        if (largestContour == null)
            propPosition = PropPositions.NULL;
        else if (largestContourPos.getX() < leftBounds)
            propPosition = PropPositions.LEFT;
        else if (largestContourPos.getX() > rightBounds)
            propPosition = PropPositions.RIGHT;
        else
            propPosition = PropPositions.CENTER;

        if (propPosition != previousPropPosition && propPosition != PropPositions.NULL)
            currentPropPosition = propPosition;
        previousPropPosition = propPosition;

        return input;
    }

    public PropPositions getPropPosition() {
        return currentPropPosition;
    }

    public MatOfPoint getLargestContour() {
        return largestContour;
    }

    public Vector2d getLargestContourPos() {
        return largestContourPos;
    }

    public double getLargestContourArea() {
        return largestContourArea;
    }
}
