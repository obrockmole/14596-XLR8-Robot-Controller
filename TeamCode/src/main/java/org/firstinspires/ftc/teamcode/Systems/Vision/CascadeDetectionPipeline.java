package org.firstinspires.ftc.teamcode.Systems.Vision;

import com.arcrobotics.ftclib.geometry.Vector2d;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

public class CascadeDetectionPipeline extends VisionPipeline {
    private final DrawStrategy drawStrategy;

    Mat gray = new Mat();
    CascadeClassifier detector = new CascadeClassifier();
    String cascadeFile;

    MatOfRect detections = new MatOfRect();

    private MatOfPoint detection;
    private Vector2d detectionPos;
    private double detectionArea;

    public CascadeDetectionPipeline(DrawStrategy drawStrategy, String cascadeFile) {
        this.drawStrategy = drawStrategy;

        this.cascadeFile = cascadeFile;
        detector.load(cascadeFile);
    }

    public Mat processFrame(Mat input) {
        Imgproc.cvtColor(input, gray, Imgproc.COLOR_RGB2GRAY);

        detector.detectMultiScale(gray, detections);

        detection = null;
        detectionArea = -1;

        for (Rect rect : detections.toArray()) {
            double area = rect.area();
            if (area > detectionArea) {
                detection = new MatOfPoint(
                        new Point(rect.x, rect.y),
                        new Point(rect.x + rect.width, rect.y),
                        new Point(rect.x + rect.width, rect.y + rect.height),
                        new Point(rect.x, rect.y + rect.height)
                );
                detectionArea = area;
            }
        }

        drawStrategy.drawOnFrame(input);

        return input;
    }

    public String getCascadeFile() {
        return cascadeFile;
    }

    public CascadeDetectionPipeline setCascadeFile(String cascadeFile) {
        this.cascadeFile = cascadeFile;
        detector.load(cascadeFile);
        return this;
    }

    public MatOfPoint getDetection() {
        return detection;
    }

    public Vector2d getDetectionPos() {
        return detectionPos;
    }

    public double getDetectionArea() {
        return detectionArea;
    }
}
