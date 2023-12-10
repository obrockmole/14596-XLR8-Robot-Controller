package org.firstinspires.ftc.teamcode.CenterStage.Autonomous;

import com.arcrobotics.ftclib.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.CenterStage.Robot;
import org.firstinspires.ftc.teamcode.RoadRunner.Drive.MecanumDrive;
import org.firstinspires.ftc.teamcode.RoadRunner.TrajectorySequence.TrajectorySequence;
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

public abstract class BaseAuto extends OpMode implements DrawStrategy {
    public Robot robot;

    public MecanumDrive drive;
    public TrajectorySequence sequence;

    public VisionDetector contourDetector;
    public ContourDetectionPipeline pipeline;

    private final int LEFT_BOUNDS = 200;
    private final int RIGHT_BOUNDS = 400;

    public enum PropPositions {
        LEFT,
        RIGHT,
        CENTER,
        NULL
    }
    private final PropPositions DEFAULT_PROP_POSITION = PropPositions.CENTER; //This will be the default value if no detection is found

    private final ArrayList<PropPositions> propPositions = new ArrayList<>();
    private final Map<PropPositions, Integer> propPositionsCount = new HashMap<>();
    private PropPositions previousPropPosition = PropPositions.NULL, currentPropPosition = PropPositions.NULL, finalPropPosition = PropPositions.NULL;

    public void init() {
        robot = new Robot(hardwareMap);
        drive = new MecanumDrive(hardwareMap);

        initVision();
        contourDetector.start();
        for (PropPositions position : PropPositions.values())
            propPositionsCount.put(position, 0);

        initTrajectory();
    }

    public void init_loop() {
        MatOfPoint detection = contourDetector.getDetection();
        Vector2d detectionPos = contourDetector.getDetectionPos();
        double detectionArea = contourDetector.getDetectionArea();

        PropPositions propPosition = DEFAULT_PROP_POSITION;
        if (detection != null) {
            if (detectionPos.getX() < LEFT_BOUNDS)
                propPosition = PropPositions.LEFT;
            else if (detectionPos.getX() > RIGHT_BOUNDS)
                propPosition = PropPositions.RIGHT;
        }

        if (propPosition != previousPropPosition)
            currentPropPosition = propPosition;

        previousPropPosition = propPosition;
        propPositions.add(currentPropPosition);

        propPositionsCount.put(currentPropPosition, propPositionsCount.getOrDefault(currentPropPosition, 0) + 1);

        if (propPositions.size() > 10000) {
            PropPositions removedPos = propPositions.remove(0);
            propPositionsCount.put(removedPos, propPositionsCount.getOrDefault(removedPos, 1) - 1);
        }

        finalPropPosition = propPositionsCount.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(DEFAULT_PROP_POSITION);

        telemetry.addData("Current Prop Position", finalPropPosition);
        telemetry.addData("Left Count", propPositionsCount.get(PropPositions.LEFT));
        telemetry.addData("Right Count", propPositionsCount.get(PropPositions.RIGHT));
        telemetry.addData("Center Count", propPositionsCount.get(PropPositions.CENTER));
        telemetry.update();
    }

    public void start() {
        drive.setPoseEstimate(sequence.start());
        drive.followTrajectorySequenceAsync(sequence);
    }

    public void loop() {
        drive.update();
        robot.update()
                .log(telemetry);

        telemetry.addLine("-----Other-----");
        telemetry.addData("Runtime", getRuntime());
        telemetry.update();
    }

    public abstract void initVision();
    public abstract void initTrajectory();

    public void drawOnFrame(Mat frame) {
        Rect rect = Imgproc.boundingRect(contourDetector.getDetection());
        Imgproc.rectangle(frame, rect, new Scalar(0, 255, 0));

        Imgproc.line(frame, new Point(LEFT_BOUNDS, 0), new Point(LEFT_BOUNDS, frame.rows()), new Scalar(0, 0, 255));
        Imgproc.line(frame, new Point(RIGHT_BOUNDS, 0), new Point(RIGHT_BOUNDS, frame.rows()), new Scalar(0, 0, 255));
    }
}
