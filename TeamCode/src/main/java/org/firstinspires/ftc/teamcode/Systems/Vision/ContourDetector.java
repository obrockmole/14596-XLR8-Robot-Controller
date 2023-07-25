package org.firstinspires.ftc.teamcode.Systems.Vision;

import static org.opencv.core.CvType.CV_8UC3;
import static org.opencv.imgproc.Imgproc.COLOR_BGR2HSV;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;

public class ContourDetector {
    private ContourDetectionPipeline pipeline;
    private WebcamName webcamName;
    private OpenCvCamera camera;
    private int cameraMonitorViewId;

    public ContourDetector(HardwareMap hardwareMap, String cameraName) {
        this(hardwareMap, hardwareMap.get(WebcamName.class, cameraName));
    }

    public ContourDetector(HardwareMap hardwareMap, WebcamName webcamName) {
        cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        this.webcamName = webcamName;
        camera = OpenCvCameraFactory.getInstance().createWebcam(webcamName, cameraMonitorViewId);
    }

    public void start(Scalar lowerBound, Scalar upperBound) {
        pipeline = new ContourDetectionPipeline(lowerBound, upperBound);

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

    public void startRGB(Scalar lowerBound, Scalar upperBound) {
        Scalar lowerBoundBGR = new Scalar(lowerBound.val[2], lowerBound.val[1], lowerBound.val[0]);
        Scalar upperBoundBGR = new Scalar(upperBound.val[2], upperBound.val[1], upperBound.val[0]);

        Mat bgrLowerBoundMat = new Mat(1, 1, CV_8UC3, lowerBoundBGR), bgrUpperBoundMat = new Mat(1, 1, CV_8UC3, upperBoundBGR);
        Mat hsvLowerBoundMat = new Mat(), hsvUpperBoundMat = new Mat();

        Imgproc.cvtColor(bgrLowerBoundMat, hsvLowerBoundMat, COLOR_BGR2HSV);
        Imgproc.cvtColor(bgrUpperBoundMat, hsvUpperBoundMat, COLOR_BGR2HSV);

        Scalar hsvLowerBound = new Scalar(hsvLowerBoundMat.get(0, 0)), hsvUpperBound = new Scalar(hsvUpperBoundMat.get(0, 0));

        start(hsvLowerBound, hsvUpperBound);
    }

    public Scalar getLowerBound() {
        return pipeline.getLowerBound();
    }

    public ContourDetector setLowerBound(Scalar lowerBound) {
        pipeline.setLowerBound(lowerBound);
        return this;
    }

    public Scalar getUpperBound() {
        return pipeline.getLowerBound();
    }

    public ContourDetector setUpperBound(Scalar upperBound) {
        pipeline.setUpperBound(upperBound);
        return this;
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
