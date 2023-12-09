package org.firstinspires.ftc.teamcode.CenterStage.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Systems.Vision.ContourDetectionPipeline;
import org.firstinspires.ftc.teamcode.Systems.Vision.ContourDetector;
import org.opencv.core.Scalar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class VisionTest extends OpMode {
    ContourDetector contourDetector;

    ArrayList<ContourDetectionPipeline.PropPositions> propPositions = new ArrayList<>();
    Map<ContourDetectionPipeline.PropPositions, Integer> propPositionsCount = new HashMap<>();
    ContourDetectionPipeline.PropPositions finalPropPosition;

    public void init() {
        contourDetector = new ContourDetector(hardwareMap, "Webcam");
        contourDetector.start(new Scalar(170, 0, 0), new Scalar(179, 255, 255), new Scalar(20, 100, 110), new Scalar(100, 200, 200), 100, 200, 400);
    }

    public void init_loop() {
        ContourDetectionPipeline.PropPositions pos = contourDetector.getPropPosition();
        propPositions.add(pos);

        propPositionsCount.put(pos, propPositionsCount.getOrDefault(pos, 0) + 1);

        if (propPositions.size() > 10000) {
            ContourDetectionPipeline.PropPositions removedPos = propPositions.remove(0);
            propPositionsCount.put(removedPos, propPositionsCount.get(removedPos) - 1);
        }

        finalPropPosition = propPositionsCount.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(ContourDetectionPipeline.PropPositions.NULL);

        telemetry.addData("Detected Prop Position", contourDetector.getPropPosition());
        telemetry.addData("Largest Contour Location", contourDetector.getLargestContourPos());
        telemetry.addData("Largest Contour Area", contourDetector.getLargestContourPos().toString());

        telemetry.addLine();
        telemetry.addData("Current Prop Position", finalPropPosition);
        telemetry.addData("Left Count", propPositionsCount.getOrDefault(ContourDetectionPipeline.PropPositions.LEFT, 0));
        telemetry.addData("Right Count", propPositionsCount.getOrDefault(ContourDetectionPipeline.PropPositions.RIGHT, 0));
        telemetry.addData("Center Count", propPositionsCount.getOrDefault(ContourDetectionPipeline.PropPositions.CENTER, 0));
        telemetry.addData("Null Count", propPositionsCount.getOrDefault(ContourDetectionPipeline.PropPositions.NULL, 0));
        telemetry.update();
    }

    public void loop() {
        telemetry.addData("Detected Prop Position", finalPropPosition);
        telemetry.update();
    }
}
