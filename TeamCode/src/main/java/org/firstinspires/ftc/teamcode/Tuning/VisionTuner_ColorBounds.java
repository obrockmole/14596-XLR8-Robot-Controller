package org.firstinspires.ftc.teamcode.Tuning;

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

    public static HSV weakLowHSV = new HSV(0, 0, 0), weakHighHSV = new HSV(0, 0, 0);
    public static HSV strictLowHSV = new HSV(0, 0, 0), strictHighHSV = new HSV(0, 0, 0);

    @Override
    public void init() {
        cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        webcamName = hardwareMap.get(WebcamName.class, "Webcam");
        camera = OpenCvCameraFactory.getInstance().createWebcam(webcamName, cameraMonitorViewId);

        pipeline = new VisionTunerPipeline(weakLowHSV.toScalar(), weakHighHSV.toScalar(), strictLowHSV.toScalar(), strictHighHSV.toScalar());

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

        FtcDashboard.getInstance().startCameraStream(camera, 30);
    }

    @Override
    public void init_loop() {
        pipeline.setHSV(weakLowHSV.toScalar(), weakHighHSV.toScalar(), strictLowHSV.toScalar(), strictHighHSV.toScalar());
    }

    @Override
    public void loop() {}
}

class VisionTunerPipeline extends OpenCvPipeline {
    Mat hsv = new Mat();
    Mat lowMask = new Mat();
    Mat lowColoredMask = new Mat();
    Mat scaledMask = new Mat();
    Mat strictMask = new Mat();
    Mat strictColoredMask = new Mat();

    Scalar weakLowHSV, weakHighHSV, strictLowHSV, strictHighHSV;

    public VisionTunerPipeline(Scalar weakLowHSV, Scalar weakHighHSV, Scalar strictLowHSV, Scalar strictHighHSV) {
        this.weakLowHSV = weakLowHSV;
        this.weakHighHSV = weakHighHSV;
        this.strictLowHSV = strictLowHSV;
        this.strictHighHSV = strictHighHSV;
    }

    @Override
    public Mat processFrame(Mat input) {
        Imgproc.cvtColor(input, hsv, Imgproc.COLOR_RGB2HSV);

        Core.inRange(hsv, weakLowHSV, weakHighHSV, lowMask);

        Core.bitwise_and(hsv, hsv, lowColoredMask, lowMask);

        Scalar average = Core.mean(lowColoredMask, lowMask);

        lowColoredMask.convertTo(scaledMask, -1, 150 / average.val[1], 0);

        Core.inRange(scaledMask, strictLowHSV, strictHighHSV, strictMask);

        Core.bitwise_and(hsv, hsv, strictColoredMask, strictMask);
        Imgproc.cvtColor(strictColoredMask, strictColoredMask, Imgproc.COLOR_HSV2RGB);

        return strictColoredMask;
    }

    public void setHSV(Scalar weakLowHSV, Scalar weakHighHSV, Scalar strictLowHSV, Scalar strictHighHSV) {
        this.weakLowHSV = weakLowHSV;
        this.weakHighHSV = weakHighHSV;
        this.strictLowHSV = strictLowHSV;
        this.strictHighHSV = strictHighHSV;
    }
}

class HSV {
    public double h;
    public double s;
    public double v;

    public HSV(double h, double s, double v) {
        this.h = h;
        this.s = s;
        this.v = v;
    }

    public Scalar toScalar() {
        return new Scalar(h, s, v);
    }
}