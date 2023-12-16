package org.firstinspires.ftc.teamcode.Tuning;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvPipeline;

import java.util.ArrayList;

@Disabled
@Config
@TeleOp(group = "Tuning")
public class VisionTuner_ColorBounds extends OpMode {
    public enum VisionSteps {
        INPUT,
        HSV,
        WEAK_MASK,
        WEAK_COLORED,
        WEAK_AVERAGE,
        STRICT_MASK,
        STRICT_COLORED,
        CONTOURS
    }

    VisionTunerPipeline pipeline;
    WebcamName webcamName;
    OpenCvCamera camera;
    int cameraMonitorViewId;

    public static HSV weakLowHSV = new HSV(0, 0, 0), weakHighHSV = new HSV(179, 255, 255);
    public static HSV strictLowHSV = new HSV(0, 0, 0), strictHighHSV = new HSV(179, 255, 255);

    public static VisionTuner_ColorBounds.VisionSteps returnVisionStep = VisionTuner_ColorBounds.VisionSteps.INPUT;

    public void init() {
        cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        webcamName = hardwareMap.get(WebcamName.class, "Webcam");
        camera = OpenCvCameraFactory.getInstance().createWebcam(webcamName, cameraMonitorViewId);

        pipeline = new VisionTunerPipeline(weakLowHSV.toScalar(), weakHighHSV.toScalar(), strictLowHSV.toScalar(), strictHighHSV.toScalar());

        camera.setPipeline(pipeline);
        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            public void onOpened() {
                //TODO: Get proper resolution values for the camera
                camera.startStreaming(640, 480);
            }

            public void onError(int errorCode) {}
        });

        FtcDashboard.getInstance().startCameraStream(camera, 30);
    }

    public void init_loop() {
        pipeline.setHSV(weakLowHSV.toScalar(), weakHighHSV.toScalar(), strictLowHSV.toScalar(), strictHighHSV.toScalar());
        pipeline.setReturnStep(returnVisionStep);
    }

    public void loop() {}
}

class VisionTunerPipeline extends OpenCvPipeline {
    private final Mat hsv = new Mat();
    private final Mat weakMask = new Mat();
    private final Mat coloredWeakMask = new Mat();
    private final Mat scaledMask = new Mat();
    private final Mat strictMask = new Mat();
    private final Mat coloredStrictMask = new Mat();

    private final Mat kernelErosion, kernelDilation;

    private Scalar weakLowHSV, weakHighHSV, strictLowHSV, strictHighHSV;
    private VisionTuner_ColorBounds.VisionSteps returnStep;

    private final ArrayList<MatOfPoint> contours = new ArrayList<>();
    private MatOfPoint largestContour;

    public VisionTunerPipeline(Scalar weakLowHSV, Scalar weakHighHSV, Scalar strictLowHSV, Scalar strictHighHSV) {
        this.weakLowHSV = weakLowHSV;
        this.weakHighHSV = weakHighHSV;
        this.strictLowHSV = strictLowHSV;
        this.strictHighHSV = strictHighHSV;

        kernelErosion = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(5, 5));
        kernelDilation = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(4, 4));
    }

    public Mat processFrame(Mat input) {
        Imgproc.cvtColor(input, hsv, Imgproc.COLOR_RGB2HSV);

        Core.inRange(hsv, weakLowHSV, weakHighHSV, weakMask);

        Imgproc.erode(weakMask, weakMask, kernelErosion);
        Imgproc.dilate(weakMask, weakMask, kernelDilation);

        coloredWeakMask.release();
        Core.bitwise_not(input, coloredWeakMask, weakMask);

        Scalar average = Core.mean(coloredWeakMask, weakMask);
        coloredWeakMask.convertTo(scaledMask, -1, 150 / average.val[1], 0);

        Core.inRange(scaledMask, strictLowHSV, strictHighHSV, strictMask);
        coloredStrictMask.release();
        Core.bitwise_not(hsv, coloredStrictMask, strictMask);

        contours.clear();
        Imgproc.findContours(strictMask, contours, new Mat(), Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);

        double largestContourArea = -1;
        largestContour = null;

        for (MatOfPoint contour : contours) {
            double area = Imgproc.contourArea(contour);
            if (area > largestContourArea) {
                largestContour = contour;
                largestContourArea = area;
            }
        }

        if (largestContour != null && returnStep == VisionTuner_ColorBounds.VisionSteps.CONTOURS) {
            Rect rect = Imgproc.boundingRect(largestContour);

            Imgproc.rectangle(input, rect, new Scalar(0, 255, 0));
        }

        switch (returnStep) {
            case HSV:
                return hsv;
            case WEAK_MASK:
                return weakMask;
            case WEAK_COLORED:
                return coloredWeakMask;
            case WEAK_AVERAGE:
                return scaledMask;
            case STRICT_MASK:
                return strictMask;
            case STRICT_COLORED:
                return coloredStrictMask;
            default:
                return input;
        }
    }

    public void setHSV(Scalar weakLowHSV, Scalar weakHighHSV, Scalar strictLowHSV, Scalar strictHighHSV) {
        this.weakLowHSV = weakLowHSV;
        this.weakHighHSV = weakHighHSV;
        this.strictLowHSV = strictLowHSV;
        this.strictHighHSV = strictHighHSV;
    }

    public void setReturnStep(VisionTuner_ColorBounds.VisionSteps returnStep) {
        this.returnStep = returnStep;
    }
}

class HSV { //Scalar wont show up on FTC Dashboard so we need to use this instead
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