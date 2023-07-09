package org.firstinspires.ftc.teamcode.Testing;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.Systems.Vision.ColorDetectionPipeline;
import org.firstinspires.ftc.teamcode.Systems.Vision.ColorDetector;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvPipeline;

import java.util.ArrayList;
import java.util.List;

//@Disabled
@Config
@TeleOp(group = "Testing")
public class VisionTuner_ColorBounds extends OpMode {
    VisionTunerPipeline pipeline;
    WebcamName webcamName;
    OpenCvCamera camera;
    int cameraMonitorViewId;

    public static Scalar lowerBound, upperBound;

    @Override
    public void init() {
        cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        webcamName = hardwareMap.get(WebcamName.class, "Webcam");
        camera = OpenCvCameraFactory.getInstance().createWebcam(webcamName, cameraMonitorViewId);

        pipeline = new VisionTunerPipeline(lowerBound, upperBound);

        camera.setPipeline(pipeline);
        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                camera.startStreaming(640, 480);
            }

            @Override
            public void onError(int errorCode) {}
        });

        FtcDashboard.getInstance().startCameraStream(camera, 0);
    }

    @Override
    public void init_loop() {
        pipeline.setLowerBound(lowerBound)
                .setUpperBound(upperBound);
    }

    @Override
    public void loop() {}
}

class VisionTunerPipeline extends OpenCvPipeline {
    Mat hsv = new Mat();
    Mat mask = new Mat();
    Mat output = new Mat();

    Scalar lowerBound, upperBound;

    public VisionTunerPipeline(Scalar lowerBound, Scalar upperBound) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    @Override
    public Mat processFrame(Mat input) {
        Imgproc.cvtColor(input, hsv, Imgproc.COLOR_RGB2HSV);
        Core.inRange(hsv, lowerBound, upperBound, mask);
        Core.bitwise_and(input, input, output, mask);

        return output;
    }

    public VisionTunerPipeline setLowerBound(Scalar lowerBound) {
        this.lowerBound = lowerBound;
        return this;
    }

    public VisionTunerPipeline setUpperBound(Scalar upperBound) {
        this.upperBound = upperBound;
        return this;
    }
}