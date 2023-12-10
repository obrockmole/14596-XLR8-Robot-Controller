package org.firstinspires.ftc.teamcode.Systems.Vision;

import com.arcrobotics.ftclib.geometry.Vector2d;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.openftc.easyopencv.OpenCvPipeline;

public abstract class VisionPipeline extends OpenCvPipeline {
    public abstract Mat processFrame(Mat frame);
    public abstract MatOfPoint getDetection();
    public abstract Vector2d getDetectionPos();
    public abstract double getDetectionArea();
}
