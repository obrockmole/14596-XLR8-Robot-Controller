package org.firstinspires.ftc.teamcode.CenterStage.Autonomous;

import com.arcrobotics.ftclib.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Systems.Vision.ContourDetectionPipeline;
import org.firstinspires.ftc.teamcode.Systems.Vision.DrawStrategy;
import org.firstinspires.ftc.teamcode.Systems.Vision.VisionDetector;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Disabled
@Autonomous(group = "Testing", name = "Vision Test")
public class VisionTest extends OpMode implements DrawStrategy {
    public static int LEFT_BOUNDS = 160;
    public static int RIGHT_BOUNDS = 320;

    public enum PropPositions {
        LEFT,
        RIGHT,
        CENTER,
        NULL
    }

    VisionDetector contourDetector;
    ContourDetectionPipeline pipeline = new ContourDetectionPipeline(this, new Scalar(170, 0, 0), new Scalar(179, 255, 255), new Scalar(20, 100, 110), new Scalar(100, 200, 200), 100);

    ArrayList<PropPositions> propPositions = new ArrayList<>();
    Map<PropPositions, Integer> propPositionsCount = new HashMap<>();
    VisionTest.PropPositions previousPropPosition, currentPropPosition, finalPropPosition;

    public void init() {
        contourDetector = new VisionDetector(hardwareMap, "Webcam", pipeline);
        contourDetector.start();
    }

    public void init_loop() {
        MatOfPoint detection = contourDetector.getDetection();
        Vector2d detectionPos = contourDetector.getDetectionPos();
        double detectionArea = contourDetector.getDetectionArea();

        PropPositions propPosition;
        if (detection == null)
            propPosition = PropPositions.NULL;
        else if (detectionPos.getX() < LEFT_BOUNDS)
            propPosition = PropPositions.LEFT;
        else if (detectionPos.getX() > RIGHT_BOUNDS)
            propPosition = PropPositions.RIGHT;
        else
            propPosition = PropPositions.CENTER;

        if (propPosition != previousPropPosition && propPosition != PropPositions.NULL)
            currentPropPosition = propPosition;

        previousPropPosition = propPosition;
        propPositions.add(currentPropPosition);

        propPositionsCount.put(currentPropPosition, propPositionsCount.getOrDefault(currentPropPosition, 0) + 1);

        if (propPositions.size() > 10000) {
            PropPositions removedPos = propPositions.remove(0);
            propPositionsCount.put(removedPos, propPositionsCount.get(removedPos) - 1);
        }

        finalPropPosition = propPositionsCount.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(PropPositions.NULL);

        telemetry.addData("Detected Prop Position", propPosition);
        telemetry.addData("Largest Contour Location", detectionPos.toString());
        telemetry.addData("Largest Contour Area", detectionArea);

        telemetry.addLine();
        telemetry.addData("Current Prop Position", finalPropPosition);
        telemetry.addData("Left Count", propPositionsCount.getOrDefault(PropPositions.LEFT, 0));
        telemetry.addData("Right Count", propPositionsCount.getOrDefault(PropPositions.RIGHT, 0));
        telemetry.addData("Center Count", propPositionsCount.getOrDefault(PropPositions.CENTER, 0));
        telemetry.addData("Null Count", propPositionsCount.getOrDefault(PropPositions.NULL, 0));
        telemetry.update();
    }

    public void start() {
        contourDetector.stop();
    }

    public void loop() {
        telemetry.addData("Detected Prop Position", finalPropPosition);
        telemetry.update();
    }

    public void drawOnFrame(Mat frame) {
        MatOfPoint detection = contourDetector.getDetection();
        if (detection != null) {
            Rect rect = Imgproc.boundingRect(detection);
            Imgproc.rectangle(frame, rect, new Scalar(0, 255, 0), 4);
        }

        Imgproc.line(frame, new Point(LEFT_BOUNDS, 0), new Point(LEFT_BOUNDS, frame.rows()), new Scalar(0, 0, 255), 4);
        Imgproc.line(frame, new Point(RIGHT_BOUNDS, 0), new Point(RIGHT_BOUNDS, frame.rows()), new Scalar(0, 0, 255), 4);
    }
}
