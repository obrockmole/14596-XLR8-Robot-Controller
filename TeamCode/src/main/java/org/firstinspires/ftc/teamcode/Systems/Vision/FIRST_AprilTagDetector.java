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

    public FIRST_AprilTagDetector(HardwareMap hardwareMap, String cameraName) {
        this(hardwareMap.get(WebcamName.class, cameraName));
    }

    public FIRST_AprilTagDetector(WebcamName webcam) {
        this.webcam = webcam;
    }

    public FIRST_AprilTagDetector initCamera() {
        processor = new AprilTagProcessor.Builder().build();

        visionPortal = new VisionPortal.Builder()
                .setCamera(webcam)
                .addProcessor(processor)
                .build();

        return this;
    }

    public FIRST_AprilTagDetector setManualExposure(Telemetry telemetry, int exposureTime, int gain) {
        if (visionPortal == null) return this;

        ExposureControl exposureControl = visionPortal.getCameraControl(ExposureControl.class);
        if (exposureControl.getMode() != ExposureControl.Mode.Manual) {
            exposureControl.setMode(ExposureControl.Mode.Manual);
            sleep(50);
        }
        exposureControl.setExposure(exposureTime, TimeUnit.MILLISECONDS);
        sleep(20);

        GainControl gainControl = visionPortal.getCameraControl(GainControl.class);
        gainControl.setGain(gain);
        sleep(20);

        return this;
    }

    public void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
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

    public ArrayList<AprilTagDetection> getDetections() {
        return processor.getDetections();
    }
}
