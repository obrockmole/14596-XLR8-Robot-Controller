package org.firstinspires.ftc.teamcode.Systems.Vision;

import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.apriltag.AprilTagDetection;
import org.openftc.apriltag.AprilTagDetectorJNI;
import org.openftc.easyopencv.OpenCvPipeline;

import java.util.ArrayList;

public class AprilTagDetectionPipeline extends OpenCvPipeline {
    private final Mat gray = new Mat();
    private final Scalar blue = new Scalar(7, 197, 235, 255);

    private long detectorPointer;

    private final double fx;
    private final double fy;
    private final double cx;
    private final double cy;
    private final double tagsize; // Units in meters

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

    protected void finalize() {
        if (detectorPointer != 0) {
            AprilTagDetectorJNI.releaseApriltagDetector(detectorPointer);
            detectorPointer = 0;
        } else {
            System.out.println("AprilTagDetectionPipeline.finalize(): detectorPointer was NULL");
        }
    }

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
            Imgproc.line(input, detection.corners[0], detection.corners[1], blue, 3);
            Imgproc.line(input, detection.corners[1], detection.corners[2], blue, 3);
            Imgproc.line(input, detection.corners[2], detection.corners[3], blue, 3);
            Imgproc.line(input, detection.corners[3], detection.corners[0], blue, 3);
            Imgproc.putText(input, String.valueOf(detection.id), detection.center, 0, 2, blue);
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
