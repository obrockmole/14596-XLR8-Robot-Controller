package org.firstinspires.ftc.teamcode.Systems.Vision;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;
import org.openftc.apriltag.AprilTagDetection;
import org.openftc.apriltag.AprilTagDetectorJNI;

import java.util.ArrayList;

public class AprilTagDetectionPipeline extends OpenCvPipeline {
    private Mat gray = new Mat();
    private Scalar blue = new Scalar(7,197,235,255);

    private long detectorPointer;

    private double fx, fy, cx, cy;
    private double tagsize; // Units in meters

    private float decimation;
    private boolean needToSetDecimation;
    private final Object decimationSync = new Object();

    private ArrayList<AprilTagDetection> detections = new ArrayList<>();
    private ArrayList<AprilTagDetection> detectionsUpdate = new ArrayList<>();
    private final Object detectionsUpdateSync = new Object();

    public AprilTagDetectionPipeline(double tagsize, double fx, double fy, double cx, double cy) {
        this.tagsize = tagsize;
        this.fx = fx;
        this.fy = fy;
        this.cx = cx;
        this.cy = cy;

        detectorPointer = AprilTagDetectorJNI.createApriltagDetector(AprilTagDetectorJNI.TagFamily.TAG_36h11.string, 3, 3);
    }

    @Override
    public void finalize() {
        if (detectorPointer != 0) {
            AprilTagDetectorJNI.releaseApriltagDetector(detectorPointer);
            detectorPointer = 0;
        } else {
            System.out.println("AprilTagDetectionPipeline.finalize(): detectorPointer was NULL");
        }
    }

    @Override
    public Mat processFrame(Mat input) {
        Imgproc.cvtColor(input, gray, Imgproc.COLOR_RGB2GRAY);

        synchronized (decimationSync) {
            if(needToSetDecimation) {
                AprilTagDetectorJNI.setApriltagDetectorDecimation(detectorPointer, decimation);
                needToSetDecimation = false;
            }
        }

        detections = AprilTagDetectorJNI.runAprilTagDetectorSimple(detectorPointer, gray, tagsize, fx, fy, cx, cy);

        synchronized (detectionsUpdateSync) {
            detectionsUpdate = detections;
        }

        for (AprilTagDetection detection : detections) {
            ArrayList<MatOfPoint> corners = new ArrayList<>();
            for (Point corner : detection.corners) {
                corners.add(new MatOfPoint(corner));
            }

            Imgproc.polylines(input, corners, true, blue, 2);
            Imgproc.putText(input, String.valueOf(detection.id), detection.center, 0, 1, blue);
        }

        return input;
    }

    public void setDecimation(float decimation) {
        synchronized (decimationSync) {
            this.decimation = decimation;
            needToSetDecimation = true;
        }
    }

    public ArrayList<AprilTagDetection> getLatestDetections() {
        return detections;
    }

    public AprilTagDetection getLastDetection() {
        return detections.get(detections.size() - 1);
    }

    public ArrayList<AprilTagDetection> getDetectionsUpdate() {
        synchronized (detectionsUpdateSync) {
            ArrayList<AprilTagDetection> d = detectionsUpdate;
            detectionsUpdate = null;
            return d;
        }
    }
}
