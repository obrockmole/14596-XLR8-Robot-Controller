package org.firstinspires.ftc.teamcode.Systems.Vision;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;

public class CascadeDetector {
    private CascadeDetectionPipeline pipeline;
    private WebcamName webcamName;
    private OpenCvCamera camera;
    private int cameraMonitorViewId;
    private String cascadeFile;

    public CascadeDetector(HardwareMap hardwareMap, String cameraName, String cascadeFile) {
        this(hardwareMap, hardwareMap.get(WebcamName.class, cameraName), cascadeFile);
    }

    public CascadeDetector(HardwareMap hardwareMap, WebcamName webcamName, String cascadeFile) {
        cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        this.webcamName = webcamName;
        camera = OpenCvCameraFactory.getInstance().createWebcam(webcamName, cameraMonitorViewId);
    }

    public void start() {
        pipeline = new CascadeDetectionPipeline(cascadeFile);

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

    public String getCascadeFile() {
        return pipeline.getCascadeFile();
    }

    public CascadeDetector setCascadeFile(String cascadeFile) {
        pipeline.setCascadeFile(cascadeFile);
        return this;
    }

    public CascadeDetectionPipeline getPipeline() {
        return pipeline;
    }

    public CascadeDetector setPipeline(CascadeDetectionPipeline pipeline) {
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
