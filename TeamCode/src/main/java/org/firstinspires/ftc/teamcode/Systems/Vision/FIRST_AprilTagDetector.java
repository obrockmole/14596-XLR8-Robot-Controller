package org.firstinspires.ftc.teamcode.Systems.Vision;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.hardware.camera.controls.ExposureControl;
import org.firstinspires.ftc.robotcore.external.hardware.camera.controls.GainControl;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class FIRST_AprilTagDetector {
    private WebcamName webcam;
    private VisionPortal visionPortal;
    private AprilTagProcessor processor;

    private double fx, fy, cx, cy;

    public FIRST_AprilTagDetector(HardwareMap hardwareMap, String cameraName) {
        this(hardwareMap.get(WebcamName.class, cameraName));
    }

    public FIRST_AprilTagDetector(WebcamName webcam) {
        this.webcam = webcam;
    }

    public FIRST_AprilTagDetector initCamera(double fx, double fy, double cx, double cy) {
        this.fx = fx;
        this.fy = fy;
        this.cx = cx;
        this.cy = cy;

        processor = new AprilTagProcessor.Builder()
                .setLensIntrinsics(fx, fy, cx, cy)
                .build();

        visionPortal = new VisionPortal.Builder()
                .setCamera(webcam)
                .addProcessor(processor)
                .build();

        return this;
    }

    public WebcamName getWebcam() {
        return webcam;
    }

    public FIRST_AprilTagDetector setWebcam(WebcamName webcam) {
        this.webcam = webcam;
        if (visionPortal != null) {
            visionPortal = new VisionPortal.Builder()
                    .setCamera(this.webcam)
                    .addProcessor(processor)
                    .build();
        }

        return this;
    }

    public AprilTagProcessor getProcessor() {
        return processor;
    }

    public FIRST_AprilTagDetector setProcessor(AprilTagProcessor processor) {
        this.processor = processor;
        if (visionPortal != null) {
            visionPortal = new VisionPortal.Builder()
                    .setCamera(webcam)
                    .addProcessor(this.processor)
                    .build();
        }

        return this;
    }

    public VisionPortal getVisionPortal() {
        return visionPortal;
    }

    public FIRST_AprilTagDetector setVisionPortal(VisionPortal visionPortal) {
        this.visionPortal = visionPortal;
        return this;
    }

    public double getFX() {
        return fx;
    }

    public FIRST_AprilTagDetector setFX(double fx) {
        this.fx = fx;
        return setLensIntrinsics(fx, fy, cx, cy);
    }

    public double getFY() {
        return fy;
    }

    public FIRST_AprilTagDetector setFY(double fy) {
        this.fy = fy;
        return setLensIntrinsics(fx, fy, cx, cy);
    }

    public double getCX() {
        return cx;
    }

    public FIRST_AprilTagDetector setCX(double cx) {
        this.cx = cx;
        return setLensIntrinsics(fx, fy, cx, cy);
    }

    public double getCY() {
        return cy;
    }

    public FIRST_AprilTagDetector setCY(double cy) {
        this.cy = cy;
        return setLensIntrinsics(fx, fy, cx, cy);
    }

    public FIRST_AprilTagDetector setLensIntrinsics(double fx, double fy, double cx, double cy) {
        this.fx = fx;
        this.fy = fy;
        this.cx = cx;
        this.cy = cy;

        processor = new AprilTagProcessor.Builder()
                .setLensIntrinsics(fx, fy, cx, cy)
                .build();

        setProcessor(processor);

        return this;
    }

    public ArrayList<AprilTagDetection> getDetections() {
        return processor.getDetections();
    }

    public ArrayList<AprilTagDetection> getFreshDetections() {
        return processor.getFreshDetections();
    }
}
