package org.firstinspires.ftc.teamcode.Systems.Vision;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.opencv.core.Scalar;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

public class ColorDetector {
    private ColorDetectionPipeline pipeline;
    private WebcamName webcamName;
    private OpenCvCamera camera;
    private int cameraMonitorViewId;

    public ColorDetector(HardwareMap hardwareMap, String cameraName) {
        this(hardwareMap, hardwareMap.get(WebcamName.class, cameraName));
    }

    public ColorDetector(HardwareMap hardwareMap, WebcamName webcamName) {
        cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        this.webcamName = webcamName;
        camera = OpenCvCameraFactory.getInstance().createWebcam(webcamName, cameraMonitorViewId);
    }

    public void start(Scalar lowerBound, Scalar upperBound) {
        pipeline = new ColorDetectionPipeline(lowerBound, upperBound);

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

    public Scalar getLowerBound() {
        return pipeline.getLowerBound();
    }

    public ColorDetector setLowerBound(Scalar lowerBound) {
        pipeline.setLowerBound(lowerBound);
        return this;
    }

    public Scalar getUpperBound() {
        return pipeline.getLowerBound();
    }

    public ColorDetector setUpperBound(Scalar upperBound) {
        pipeline.setUpperBound(upperBound);
        return this;
    }

    public ColorDetectionPipeline getPipeline() {
        return pipeline;
    }

    public ColorDetector setPipeline(ColorDetectionPipeline pipeline) {
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
