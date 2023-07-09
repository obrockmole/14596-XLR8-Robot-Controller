package org.firstinspires.ftc.teamcode.Systems.Vision;

import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
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

        for (int i = 0; i < detections.toArray().length; i++) {
            Imgproc.rectangle(
                    input,
                    new Point(
                            detections.toArray()[i].x,
                            detections.toArray()[i].y),
                    new Point(
                            detections.toArray()[i].x + detections.toArray()[i].width,
                            detections.toArray()[i].y + detections.toArray()[i].height),
                    new Scalar(0, 255, 0), 2);
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
