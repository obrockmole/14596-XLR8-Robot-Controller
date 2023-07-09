package org.firstinspires.ftc.teamcode.Systems.Vision;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;

public class FaceDetector {
    private FaceDetectionPipeline pipeline;
    private WebcamName webcamName;
    private OpenCvCamera camera;
    private int cameraMonitorViewId;

    public FaceDetector(HardwareMap hardwareMap, String cameraName) {
        this(hardwareMap, hardwareMap.get(WebcamName.class, cameraName));
    }

    public FaceDetector(HardwareMap hardwareMap, WebcamName webcamName) {
        cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        this.webcamName = webcamName;
        camera = OpenCvCameraFactory.getInstance().createWebcam(webcamName, cameraMonitorViewId);
    }

    public void start() {
        pipeline = new FaceDetectionPipeline("frontalface_default.xml");

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

    public FaceDetectionPipeline getPipeline() {
        return pipeline;
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
