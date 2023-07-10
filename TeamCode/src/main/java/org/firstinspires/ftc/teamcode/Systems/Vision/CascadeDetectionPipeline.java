package org.firstinspires.ftc.teamcode.Systems.Vision;

import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.openftc.easyopencv.OpenCvPipeline;

public class CascadeDetectionPipeline extends OpenCvPipeline {
    Mat gray = new Mat();
    CascadeClassifier detector = new CascadeClassifier();

    MatOfRect detections = new MatOfRect();

    String cascadeFile;

    public CascadeDetectionPipeline(String cascadeFile) {
        this.cascadeFile = cascadeFile;
        detector.load(cascadeFile);
    }

    @Override
    public Mat processFrame(Mat input) {
        Imgproc.cvtColor(input, gray, Imgproc.COLOR_RGB2GRAY);

        detector.detectMultiScale(gray, detections);

        for (Rect rect : detections.toArray()) {
            Imgproc.rectangle(input, new Point(rect.x, rect.y),
                    new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 255, 0), 2);
        }

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
}
