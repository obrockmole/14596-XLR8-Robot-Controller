package org.firstinspires.ftc.teamcode.Systems.Vision;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.openftc.easyopencv.OpenCvPipeline;

import java.util.ArrayList;
import java.util.List;

public class FaceDetectionPipeline extends OpenCvPipeline {
    Mat gray = new Mat();
    CascadeClassifier faceDetector = new CascadeClassifier();

    MatOfRect faceDetections = new MatOfRect();

    public FaceDetectionPipeline(String cascadeFile) {
        faceDetector.load(cascadeFile);
    }

    @Override
    public Mat processFrame(Mat input) {
        Imgproc.cvtColor(input, gray, Imgproc.COLOR_RGB2GRAY);

        faceDetector.detectMultiScale(gray, faceDetections);

        for (int i = 0; i < faceDetections.toArray().length; i++) {
            Imgproc.rectangle(
                    input,
                    new Point(
                            faceDetections.toArray()[i].x,
                            faceDetections.toArray()[i].y),
                    new Point(
                            faceDetections.toArray()[i].x + faceDetections.toArray()[i].width,
                            faceDetections.toArray()[i].y + faceDetections.toArray()[i].height),
                    new Scalar(0, 255, 0), 2);
        }

        return input;
    }
}
