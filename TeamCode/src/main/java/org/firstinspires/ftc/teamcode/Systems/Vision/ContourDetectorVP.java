package org.firstinspires.ftc.teamcode.Systems.Vision;

import com.arcrobotics.ftclib.geometry.Vector2d;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Scalar;

public class ContourDetectorVP {
    private final ContourDetectionProcessorVP contourDetectionProcessor;
    private final VisionPortal visionPortal;

    public ContourDetectorVP(HardwareMap hardwareMap, String cameraName, Scalar weakLowHSV, Scalar weakHighHSV, Scalar strictLowHSV, Scalar strictHighHSV, double minArea, double leftBounds, double rightBounds) {
        this(hardwareMap, hardwareMap.get(WebcamName.class, cameraName), weakLowHSV, weakHighHSV, strictLowHSV, strictHighHSV, minArea, leftBounds, rightBounds);
    }

    public ContourDetectorVP(HardwareMap hardwareMap, WebcamName webcam, Scalar weakLowHSV, Scalar weakHighHSV, Scalar strictLowHSV, Scalar strictHighHSV, double minArea, double leftBounds, double rightBounds) {
        contourDetectionProcessor = new ContourDetectionProcessorVP(weakLowHSV, weakHighHSV, strictLowHSV, strictHighHSV, minArea, leftBounds, rightBounds);

        visionPortal = new VisionPortal.Builder()
                .setCamera(webcam)
                .addProcessor(contourDetectionProcessor)
                .build();
    }

    public ContourDetectionProcessorVP.PropPositions getPropPosition() {
        return contourDetectionProcessor.getPropPosition();
    }

    public MatOfPoint getLargestContour() {
        return contourDetectionProcessor.getLargestContour();
    }

    public Vector2d getLargestContourPos() {
        return contourDetectionProcessor.getLargestContourPos();
    }

    public double getLargestContourArea() {
        return contourDetectionProcessor.getLargestContourArea();
    }

    public ContourDetectorVP stop() {
        contourDetectionProcessor.close();
        visionPortal.close();
        return this;
    }

    public VisionPortal.CameraState getCameraState() {
        return visionPortal.getCameraState();
    }

    public ContourDetectorVP stopLiveView() {
        visionPortal.stopLiveView();
        return this;
    }

    public ContourDetectorVP stopStreaming() {
        visionPortal.stopStreaming();
        return this;
    }
}
