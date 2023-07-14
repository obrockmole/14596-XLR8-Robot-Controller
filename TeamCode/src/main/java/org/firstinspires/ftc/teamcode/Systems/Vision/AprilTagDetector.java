package org.firstinspires.ftc.teamcode.Systems.Vision;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.apriltag.AprilTagDetection;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;

public class AprilTagDetector {
    private AprilTagDetectionPipeline pipeline;
    private WebcamName webcamName;
    private OpenCvCamera camera;
    private int cameraMonitorViewId;

    public AprilTagDetector(HardwareMap hardwareMap, String cameraName) {
        this(hardwareMap, hardwareMap.get(WebcamName.class, cameraName));
    }

    public AprilTagDetector(HardwareMap hardwareMap, WebcamName webcamName) {
        cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        this.webcamName = webcamName;
        camera = OpenCvCameraFactory.getInstance().createWebcam(webcamName, cameraMonitorViewId);
    }

    public void start() {
        pipeline = new AprilTagDetectionPipeline(50d, 50d, 50d, 50d, 50d);

        camera.setPipeline(pipeline);
        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                camera.startStreaming(640, 480);
            }

            @Override
            public void onError(int errorCode) {}
        });
    }

    public int scan() {
        for (AprilTagDetection detection: pipeline.getLatestDetections()) {
            return detection.id;
        }
        return 0;
    }

    public void setDecimation(float decimation) {
        pipeline.setDecimation(decimation);
    }

    public AprilTagDetectionPipeline getPipeline() {
        return pipeline;
    }

    public AprilTagDetector setPipeline(AprilTagDetectionPipeline pipeline) {
        this.pipeline = pipeline;
        return this;
    }

    public WebcamName getWebcamName() {
        return webcamName;
    }

    public OpenCvCamera getCamera() {
        return camera;
    }

    public int getCameraMonitorViewId() {
        return cameraMonitorViewId;
    }
}
