package org.firstinspires.ftc.teamcode.RoadRunner.Drive.Tuning;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvPipeline;

//@Disabled
@Config
@TeleOp(group = "Tuning")
public class VisionTuner_ColorBounds extends OpMode {
    VisionTunerPipeline pipeline;
    WebcamName webcamName;
    OpenCvCamera camera;
    int cameraMonitorViewId;

    public static Scalar LOWER_BOUND = new Scalar(0, 0, 0), UPPER_BOUND = new Scalar(0, 0, 0);

    @Override
    public void init() {
        cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        webcamName = hardwareMap.get(WebcamName.class, "Webcam");
        camera = OpenCvCameraFactory.getInstance().createWebcam(webcamName, cameraMonitorViewId);

        pipeline = new VisionTunerPipeline(LOWER_BOUND, UPPER_BOUND);

        camera.setPipeline(pipeline);
        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                camera.startStreaming(640, 480);
            }

            @Override
            public void onError(int errorCode) {}
        });

        FtcDashboard.getInstance().startCameraStream(camera, 30);
    }

    @Override
    public void init_loop() {
        pipeline.setBounds(LOWER_BOUND, UPPER_BOUND);
    }

    @Override
    public void loop() {}
}

class VisionTunerPipeline extends OpenCvPipeline {
    Mat hsv = new Mat();
    Mat mask = new Mat();

    Scalar lowerBound, upperBound;

    public VisionTunerPipeline(Scalar lowerBound, Scalar upperBound) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    @Override
    public Mat processFrame(Mat input) {
        Imgproc.cvtColor(input, hsv, Imgproc.COLOR_RGB2HSV);
        Core.inRange(hsv, lowerBound, upperBound, mask);

        return mask;
    }

    public VisionTunerPipeline setBounds(Scalar lowerBound, Scalar upperBound) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        return this;
    }
}