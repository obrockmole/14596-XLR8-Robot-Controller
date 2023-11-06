package org.firstinspires.ftc.teamcode.Systems.Vision;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Scalar;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;

import java.util.ArrayList;

public class ContourDetector {
    private ContourDetectionPipeline pipeline;
    private final WebcamName webcamName;
    private final OpenCvCamera camera;
    private final int cameraMonitorViewId;

    public ContourDetector(HardwareMap hardwareMap, String cameraName) {
        this(hardwareMap, hardwareMap.get(WebcamName.class, cameraName));
    }

    public ContourDetector(HardwareMap hardwareMap, WebcamName webcamName) {
        cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        this.webcamName = webcamName;
        camera = OpenCvCameraFactory.getInstance().createWebcam(webcamName, cameraMonitorViewId);
    }

    public void start(Scalar weakLowHSV, Scalar weakHighHSV, Scalar strictLowHSV, Scalar strictHighHSV) {
        pipeline = new ContourDetectionPipeline(weakLowHSV, weakHighHSV, strictLowHSV, strictHighHSV);

        camera.setPipeline(pipeline);
        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                //TODO: Get proper resolution values for the camera
                camera.startStreaming(640, 480);
            }

            @Override
            public void onError(int errorCode) {}
        });
    }

    public Scalar getWeakLowHSV() {
        return pipeline.getWeakLowHSV();
    }

    public ContourDetector setWeakLowHSV(Scalar weakLowHSV) {
        pipeline.setWeakLowHSV(weakLowHSV);
        return this;
    }

    public Scalar getWeakHighHSV() {
        return pipeline.getWeakHighHSV();
    }

    public ContourDetector setWeakHighHSV(Scalar weakHighHSV) {
        pipeline.setWeakHighHSV(weakHighHSV);
        return this;
    }

    public Scalar getStrictLowHSV() {
        return pipeline.getStrictLowHSV();
    }

    public ContourDetector setStrictLowHSV(Scalar strictLowHSV) {
        pipeline.setStrictLowHSV(strictLowHSV);
        return this;
    }

    public Scalar getStrictHighHSV() {
        return pipeline.getStrictHighHSV();
    }

    public ContourDetector setStrictHighHSV(Scalar strictHighHSV) {
        pipeline.setStrictHighHSV(strictHighHSV);
        return this;
    }

    public ContourDetector setWeakHSV(Scalar weakLowHSV, Scalar weakHighHSV) {
        pipeline.setWeakHSV(weakLowHSV, weakHighHSV);
        return this;
    }

    public ContourDetector setStrictHSV(Scalar strictLowHSV, Scalar strictHighHSV) {
        pipeline.setStrictHSV(strictLowHSV, strictHighHSV);
        return this;
    }

    public ContourDetector setHSV(Scalar weakLowHSV, Scalar weakHighHSV, Scalar strictLowHSV, Scalar strictHighHSV) {
        pipeline.setHSV(weakLowHSV, weakHighHSV, strictLowHSV, strictHighHSV);
        return this;
    }

    public ArrayList<MatOfPoint> getContours() {
        return pipeline.getContours();
    }

    public ContourDetectionPipeline getPipeline() {
        return pipeline;
    }

    public ContourDetector setPipeline(ContourDetectionPipeline pipeline) {
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
