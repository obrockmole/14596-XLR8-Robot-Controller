package org.firstinspires.ftc.teamcode.Systems.Vision;

import com.arcrobotics.ftclib.geometry.Vector2d;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Scalar;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;

public class ContourDetector {
    private ContourDetectionPipeline contourDetectionPipeline;
    private final WebcamName webcamName;
    private final OpenCvCamera camera;
    private final int cameraMonitorViewId;

    public ContourDetector(HardwareMap hardwareMap, WebcamName webcamName) {
        cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        this.webcamName = webcamName;
        camera = OpenCvCameraFactory.getInstance().createWebcam(webcamName, cameraMonitorViewId);
    }

    public ContourDetector(HardwareMap hardwareMap, String cameraName) {
        this(hardwareMap, hardwareMap.get(WebcamName.class, cameraName));
    }

    public void start(Scalar weakLowHSV, Scalar weakHighHSV, Scalar strictLowHSV, Scalar strictHighHSV, double minArea, double leftBounds, double rightBounds) {
        contourDetectionPipeline = new ContourDetectionPipeline(weakLowHSV, weakHighHSV, strictLowHSV, strictHighHSV, minArea, leftBounds, rightBounds);

        camera.setPipeline(contourDetectionPipeline);
        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            public void onOpened() {
                //TODO: Get proper resolution values for the camera
                camera.startStreaming(640, 480);
            }

            public void onError(int errorCode) {}
        });
    }

    public ContourDetectionPipeline.PropPositions getPropPosition() {
        return contourDetectionPipeline.getPropPosition();
    }

    public MatOfPoint getLargestContour() {
        return contourDetectionPipeline.getLargestContour();
    }

    public Vector2d getLargestContourPos() {
        return contourDetectionPipeline.getLargestContourPos();
    }

    public double getLargestContourArea() {
        return contourDetectionPipeline.getLargestContourArea();
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
