package org.firstinspires.ftc.teamcode.Tuning;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.function.Consumer;
import org.firstinspires.ftc.robotcore.external.function.Continuation;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.stream.CameraStreamSource;
import org.firstinspires.ftc.robotcore.internal.camera.calibration.CameraCalibration;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.VisionProcessor;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

//@Disabled
@Config
@TeleOp(group = "Tuning")
public class VisionTuner_ColorBounds_VP extends OpMode {
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

    private VisionTunerProcessorVP visionTunerProcessor;
    private VisionPortal visionPortal;

    public static HSV_VP weakLowHSV = new HSV_VP(0, 0, 0), weakHighHSV = new HSV_VP(179, 255, 255);
    public static HSV_VP strictLowHSV = new HSV_VP(0, 0, 0), strictHighHSV = new HSV_VP(179, 255, 255);

    public static VisionSteps returnVisionStep = VisionSteps.STRICT_MASK;

    public void init() {
        visionTunerProcessor = new VisionTunerProcessorVP(weakLowHSV, weakHighHSV, strictLowHSV, strictHighHSV);

        WebcamName webcam = hardwareMap.get(WebcamName.class, "Webcam");
        visionPortal = new VisionPortal.Builder()
                .setCamera(webcam)
                .addProcessor(visionTunerProcessor)
                .build();

        FtcDashboard.getInstance().startCameraStream(visionTunerProcessor, 30);
    }

    public void init_loop() {
        visionTunerProcessor.setHSV(weakLowHSV, weakHighHSV, strictLowHSV, strictHighHSV);
    }

    public void loop() {}

    public void stop() {
        visionPortal.close();
    }
}

class VisionTunerProcessorVP implements VisionProcessor, CameraStreamSource {
    private final Mat hsv = new Mat();
    private final Mat weakMask = new Mat();
    private final Mat coloredWeakMask = new Mat();
    private final Mat scaledMask = new Mat();
    private final Mat strictMask = new Mat();
    private final Mat coloredStrictMask = new Mat();

    private Scalar weakLowHSV, weakHighHSV, strictLowHSV, strictHighHSV;
    private VisionTuner_ColorBounds_VP.VisionSteps returnStep;

    private ArrayList<MatOfPoint> contours;
    private MatOfPoint largestContour;

    private final Paint lineColor;

    private final AtomicReference<Bitmap> lastFrame = new AtomicReference<>(Bitmap.createBitmap(1, 1, Bitmap.Config.RGB_565));

    public VisionTunerProcessorVP(Scalar weakLowHSV, Scalar weakHighHSV, Scalar strictLowHSV, Scalar strictHighHSV) {
        this.weakLowHSV = weakLowHSV;
        this.weakHighHSV = weakHighHSV;
        this.strictLowHSV = strictLowHSV;
        this.strictHighHSV = strictHighHSV;

        lineColor = new Paint();
        lineColor.setColor(Color.GREEN);
        lineColor.setAntiAlias(true);
        lineColor.setStrokeWidth(10);
        lineColor.setStrokeCap(Paint.Cap.ROUND);
        lineColor.setStrokeJoin(Paint.Join.ROUND);
    }

    public void init(int width, int height, CameraCalibration cameraCalibration) {
        lastFrame.set(Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565));
    }

    public Object processFrame(Mat input, long frameCaptureTime) {
        Imgproc.cvtColor(input, hsv, Imgproc.COLOR_RGB2HSV);

        Core.inRange(hsv, weakLowHSV, weakHighHSV, weakMask);
        Core.bitwise_and(hsv, hsv, coloredWeakMask, weakMask);

        Scalar average = Core.mean(coloredWeakMask, weakMask);
        coloredWeakMask.convertTo(scaledMask, -1, 150 / average.val[1], 0);

        Core.inRange(scaledMask, strictLowHSV, strictHighHSV, strictMask);
        Core.bitwise_and(hsv, hsv, coloredStrictMask, strictMask);

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

    public void onDrawFrame(Canvas canvas, int onscreenWidth, int onscreenHeight, float scaleBmpPxToCanvasPx, float scaleCanvasDensity, Object userContext) {
        if (largestContour != null && returnStep == VisionTuner_ColorBounds_VP.VisionSteps.CONTOURS) {
            Rect rect = Imgproc.boundingRect(largestContour);

            float[] points = {
                    rect.x * scaleBmpPxToCanvasPx,
                    rect.y * scaleBmpPxToCanvasPx,
                    (rect.x + rect.width) * scaleBmpPxToCanvasPx,
                    (rect.y + rect.height) * scaleBmpPxToCanvasPx
            };

            canvas.drawLine(points[0], points[1], points[0], points[3], lineColor);
            canvas.drawLine(points[0], points[1], points[2], points[1], lineColor);
            canvas.drawLine(points[0], points[3], points[2], points[3], lineColor);
            canvas.drawLine(points[2], points[1], points[2], points[3], lineColor);
        }
    }

    public void getFrameBitmap(Continuation<? extends Consumer<Bitmap>> continuation) {
        continuation.dispatch(bitmapConsumer -> bitmapConsumer.accept(lastFrame.get()));
    }

    public void setHSV(Scalar weakLowHSV, Scalar weakHighHSV, Scalar strictLowHSV, Scalar strictHighHSV) {
        this.weakLowHSV = weakLowHSV;
        this.weakHighHSV = weakHighHSV;
        this.strictLowHSV = strictLowHSV;
        this.strictHighHSV = strictHighHSV;
    }
}

class HSV_VP extends Scalar { //Scalar wont show up on FTC Dashboard so we need to use this instead
    public double h;
    public double s;
    public double v;

    public HSV_VP(double h, double s, double v) {
        super(h, s, v);
        this.h = h;
        this.s = s;
        this.v = v;
    }
}