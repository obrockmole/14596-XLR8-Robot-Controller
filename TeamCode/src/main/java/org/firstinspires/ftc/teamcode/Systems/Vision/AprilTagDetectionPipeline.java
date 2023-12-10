package org.firstinspires.ftc.teamcode.Systems.Vision;

import com.arcrobotics.ftclib.geometry.Vector2d;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.opencv.imgproc.Moments;
import org.openftc.apriltag.AprilTagDetection;
import org.openftc.apriltag.AprilTagDetectorJNI;

import java.util.ArrayList;

public class AprilTagDetectionPipeline extends VisionPipeline {
    private final DrawStrategy drawStrategy;

    private final Mat gray = new Mat();
    private final Scalar blue = new Scalar(7, 197, 235, 255);

    private final int targetTagID;

    private final double fx;
    private final double fy;
    private final double cx;
    private final double cy;
    private final double tagSize; // Units in meters

    private float decimation;
    private boolean needToSetDecimation;
    private final Object decimationSync = new Object();
    private long detectorPointer;

    private MatOfPoint detectedTag;
    private Vector2d detectedTagPos;
    private double detectedTagArea;

    public AprilTagDetectionPipeline(DrawStrategy drawStrategy, int targetTagID, double tagSize, double fx, double fy, double cx, double cy) {
        this.drawStrategy = drawStrategy;

        this.targetTagID = targetTagID;

        this.tagSize = tagSize;
        this.fx = fx;
        this.fy = fy;
        this.cx = cx;
        this.cy = cy;

        detectorPointer = AprilTagDetectorJNI.createApriltagDetector(AprilTagDetectorJNI.TagFamily.TAG_36h11.string, 3, 3);
    }

    public Mat processFrame(Mat input) {
        Imgproc.cvtColor(input, gray, Imgproc.COLOR_RGB2GRAY);

        synchronized (decimationSync) {
            if(needToSetDecimation) {
                AprilTagDetectorJNI.setApriltagDetectorDecimation(detectorPointer, decimation);
                needToSetDecimation = false;
            }
        }

        ArrayList<AprilTagDetection> detections = AprilTagDetectorJNI.runAprilTagDetectorSimple(detectorPointer, gray, tagSize, fx, fy, cx, cy);

        detectedTag = null;

        for (AprilTagDetection detection : detections) {
            if (detection.id == targetTagID) {
                detectedTag = new MatOfPoint(detection.corners[0], detection.corners[1], detection.corners[2], detection.corners[3]);
                break;
            }
        }

        if (detectedTag != null) {
            Moments moment = Imgproc.moments(detectedTag);
            detectedTagPos = new Vector2d(moment.m10 / moment.m00, moment.m01 / moment.m00);

            detectedTagArea = Imgproc.contourArea(detectedTag);

            Point[] corners = detectedTag.toArray();
            double triangle1Area = Math.abs(0.5 * (corners[0].x * (corners[1].y - corners[2].y) + corners[1].x * (corners[2].y - corners[0].y) + corners[2].x * (corners[0].y - corners[1].y)));
            double triangle2Area = Math.abs(0.5 * (corners[0].x * (corners[2].y - corners[3].y) + corners[2].x * (corners[3].y - corners[0].y) + corners[3].x * (corners[0].y - corners[2].y)));

            detectedTagArea = triangle1Area + triangle2Area;
        } else {
            detectedTagPos = new Vector2d(-1, -1);
            detectedTagArea = -1;
        }

        drawStrategy.drawOnFrame(input);

        return input;
    }

    protected void finalize() {
        if (detectorPointer != 0) {
            AprilTagDetectorJNI.releaseApriltagDetector(detectorPointer);
            detectorPointer = 0;
        } else {
            System.out.println("AprilTagDetectionPipeline.finalize(): detectorPointer was NULL");
        }
    }

    public void setDecimation(float decimation) {
        synchronized (decimationSync) {
            this.decimation = decimation;
            needToSetDecimation = true;
        }
    }

    public MatOfPoint getDetection() {
        return detectedTag;
    }

    public Vector2d getDetectionPos() {
        return detectedTagPos;
    }

    public double getDetectionArea() {
        return detectedTagArea;
    }
}
