package org.firstinspires.ftc.teamcode.Systems.Vision;

import com.arcrobotics.ftclib.geometry.Vector2d;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.opencv.core.MatOfPoint;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraException;
import org.openftc.easyopencv.OpenCvCameraFactory;

public class VisionDetector {
    private final WebcamName webcamName;
    private final OpenCvCamera camera;
    private final int cameraMonitorViewId;

    private final VisionPipeline pipeline;

    public VisionDetector(HardwareMap hardwareMap, WebcamName webcamName, VisionPipeline pipeline) {
        cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        this.webcamName = webcamName;
        camera = OpenCvCameraFactory.getInstance().createWebcam(webcamName, cameraMonitorViewId);

        this.pipeline = pipeline;
    }

    public VisionDetector(HardwareMap hardwareMap, String cameraName, VisionPipeline pipeline) {
        this(hardwareMap, hardwareMap.get(WebcamName.class, cameraName), pipeline);
    }

    public void start() {
        try {
            camera.setPipeline(pipeline);
            camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
                public void onOpened() {
                    //TODO: Get proper resolution values for the camera
                    camera.startStreaming(640, 480);
                }

                public void onError(int errorCode) {
                }
            });
        } catch (OpenCvCameraException ignored) {}
    }

    public void stop() {
//        camera.closeCameraDeviceAsync(camera::stopStreaming);
        try {
            camera.stopStreaming();
        } catch (OpenCvCameraException ignored) {}
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

    public MatOfPoint getDetection() {
        return pipeline.getDetection();
    }

    public Vector2d getDetectionPos() {
        return pipeline.getDetectionPos();
    }

    public double getDetectionArea() {
        return pipeline.getDetectionArea();
    }
}
