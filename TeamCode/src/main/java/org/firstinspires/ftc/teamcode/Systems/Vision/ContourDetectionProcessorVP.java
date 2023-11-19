package org.firstinspires.ftc.teamcode.Systems.Vision;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextPaint;

import com.arcrobotics.ftclib.geometry.Vector2d;

import org.firstinspires.ftc.robotcore.external.function.Consumer;
import org.firstinspires.ftc.robotcore.external.function.Continuation;
import org.firstinspires.ftc.robotcore.external.stream.CameraStreamSource;
import org.firstinspires.ftc.robotcore.internal.camera.calibration.CameraCalibration;
import org.firstinspires.ftc.vision.VisionProcessor;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.opencv.imgproc.Moments;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class ContourDetectionProcessorVP implements VisionProcessor, CameraStreamSource {
    public enum PropPositions {
        LEFT,
        RIGHT,
        CENTER,
        NULL
    }

    private final Mat hsv = new Mat();
    private final Mat weakMask = new Mat();
    private final Mat weakColoredMask = new Mat();
    private final Mat scaledMask = new Mat();
    private final Mat strictMask = new Mat();
    private final Mat strictColoredMask = new Mat();
    private final Mat hierarchy = new Mat();

    private final Scalar weakLowHSV;
    private final Scalar weakHighHSV;
    private final Scalar strictLowHSV;
    private final Scalar strictHighHSV;

    private final double minArea;
    private final double leftBounds;
    private final double rightBounds;

    private ArrayList<MatOfPoint> contours;
    private MatOfPoint largestContour;
    private Vector2d largestContourPos;
    private double largestContourArea;

    private PropPositions previousPropPosition, currentPropPosition;

    private final Paint lineColor;
    private final TextPaint textPaint;

    private final AtomicReference<Bitmap> lastFrame = new AtomicReference<>(Bitmap.createBitmap(1, 1, Bitmap.Config.RGB_565));

    public ContourDetectionProcessorVP(Scalar weakLowHSV, Scalar weakHighHSV, Scalar strictLowHSV, Scalar strictHighHSV, double minArea, double leftBounds, double rightBounds) {
        this.weakLowHSV = weakLowHSV;
        this.weakHighHSV = weakHighHSV;
        this.strictLowHSV = strictLowHSV;
        this.strictHighHSV = strictHighHSV;

        this.minArea = minArea;
        this.leftBounds = leftBounds;
        this.rightBounds = rightBounds;

        lineColor = new Paint();
        lineColor.setColor(Color.GREEN);
        lineColor.setAntiAlias(true);
        lineColor.setStrokeWidth(10);
        lineColor.setStrokeCap(Paint.Cap.ROUND);
        lineColor.setStrokeJoin(Paint.Join.ROUND);

        textPaint = new TextPaint();
        textPaint.setColor(Color.WHITE);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(40);
        textPaint.setTypeface(Typeface.DEFAULT_BOLD);
    }

    public void init(int width, int height, CameraCalibration cameraCalibration) {
        lastFrame.set(Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565));
    }

    public Object processFrame(Mat input, long frameCaptureTime) {
        //Convert input to HSV
        Imgproc.cvtColor(input, hsv, Imgproc.COLOR_RGB2HSV);

        //Apply weak detection and output 'weakMask'
        Core.inRange(hsv, weakLowHSV, weakHighHSV, weakMask);

        //Reapply the colors from 'hsv' to the white areas in 'weakMask' and output 'weakColoredMask'
        Core.bitwise_and(hsv, hsv, weakColoredMask, weakMask);

        //Find the average color in 'weakColoredMask'
        Scalar average = Core.mean(weakColoredMask, weakMask);

        //Scale 'weakColoredMask' values based on the average color and output 'scaledMask;
        weakColoredMask.convertTo(scaledMask, -1, 150 / average.val[1], 0);

        //Apply strict detection and output 'strictMask'
        Core.inRange(scaledMask, strictLowHSV, strictHighHSV, strictMask);

        //Reapply the colors from 'hsv' to the white areas in 'strictMask' and output 'strictColoredMask'
        Core.bitwise_and(hsv, hsv, strictColoredMask, strictMask);

        //Find contours in 'strictMask'
        contours.clear();
        Imgproc.findContours(strictMask, contours, hierarchy, Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);

        largestContourArea = -1;
        largestContour = null;

        for (MatOfPoint contour : contours) {
            double area = Imgproc.contourArea(contour);
            if (area > largestContourArea && area > minArea) {
                largestContour = contour;
                largestContourArea = area;
            }
        }

        if (largestContour != null) {
            Moments moment = Imgproc.moments(largestContour);
            largestContourPos = new Vector2d(moment.m10 / moment.m00, moment.m01 / moment.m00);
        } else {
            largestContourPos = new Vector2d(-1, -1);
        }

        PropPositions propPosition;
        if (largestContour == null)
            propPosition = PropPositions.NULL;
        else if (largestContourPos.getX() < leftBounds)
            propPosition = PropPositions.LEFT;
        else if (largestContourPos.getX() > rightBounds)
            propPosition = PropPositions.RIGHT;
        else
            propPosition = PropPositions.CENTER;

        if (propPosition != previousPropPosition && propPosition != PropPositions.NULL)
            currentPropPosition = propPosition;
        previousPropPosition = propPosition;

        return input;
    }

    public void onDrawFrame(Canvas canvas, int onscreenWidth, int onscreenHeight, float scaleBmpPxToCanvasPx, float scaleCanvasDensity, Object userContext) {
        if (largestContour != null) {
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

            lineColor.setColor(Color.YELLOW);

            canvas.drawLine((float) (leftBounds * scaleBmpPxToCanvasPx), 0, (float) (leftBounds * scaleBmpPxToCanvasPx), onscreenHeight, lineColor);
            canvas.drawLine((float) (rightBounds * scaleBmpPxToCanvasPx), 0, (float) (rightBounds * scaleBmpPxToCanvasPx), onscreenHeight, lineColor);

            canvas.drawText(currentPropPosition.toString(), (float) (largestContourPos.getX() * scaleBmpPxToCanvasPx), (float) (largestContourPos.getY() * scaleBmpPxToCanvasPx), textPaint);
        }
    }

    public void getFrameBitmap(Continuation<? extends Consumer<Bitmap>> continuation) {
        continuation.dispatch(bitmapConsumer -> bitmapConsumer.accept(lastFrame.get()));
    }

    public PropPositions getPropPosition() {
        return currentPropPosition;
    }

    public MatOfPoint getLargestContour() {
        return largestContour;
    }

    public Vector2d getLargestContourPos() {
        return largestContourPos;
    }

    public double getLargestContourArea() {
        return largestContourArea;
    }

    public void close() {
        hierarchy.release();
    }
}
