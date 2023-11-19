package org.firstinspires.ftc.teamcode.CenterStage.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Systems.Vision.ContourDetectionProcessorVP;
import org.firstinspires.ftc.teamcode.Systems.Vision.ContourDetectorVP;
import org.firstinspires.ftc.vision.VisionPortal;
import org.opencv.core.Scalar;

import java.util.ArrayList;

public class VisionTest extends OpMode {
    ContourDetectorVP contourDetector;

    ArrayList<ContourDetectionProcessorVP.PropPositions> propPositions = new ArrayList<>();
    ContourDetectionProcessorVP.PropPositions finalPropPosition;
    int leftCount, rightCount, centerCount, nullCount;

    public void init() {
        contourDetector = new ContourDetectorVP(hardwareMap, "Webcam", new Scalar(0, 0, 0), new Scalar(179, 255, 255), new Scalar(0, 0, 0), new Scalar(179, 255, 255), 100, 200, 400);
    }

    public void init_loop() {
        ContourDetectionProcessorVP.PropPositions pos = contourDetector.getPropPosition();
        propPositions.add(pos);

        switch (pos) {
            case LEFT:
                leftCount++;
                break;
            case RIGHT:
                rightCount++;
                break;
            case CENTER:
                centerCount++;
                break;
            case NULL:
                nullCount++;
                break;
        }

        if (propPositions.size() > 10000) {
            switch (propPositions.remove(0)) {
                case LEFT:
                    leftCount--;
                    break;
                case RIGHT:
                    rightCount--;
                    break;
                case CENTER:
                    centerCount--;
                    break;
                case NULL:
                    nullCount--;
                    break;
            }
        }

        if (leftCount >= Math.max(centerCount, Math.max(rightCount, nullCount)))
            finalPropPosition = ContourDetectionProcessorVP.PropPositions.LEFT;
        else if (rightCount >= Math.max(centerCount, Math.max(leftCount, nullCount)))
            finalPropPosition = ContourDetectionProcessorVP.PropPositions.RIGHT;
        else if (centerCount >= Math.max(leftCount, Math.max(rightCount, nullCount)))
            finalPropPosition = ContourDetectionProcessorVP.PropPositions.CENTER;
        else
            finalPropPosition = ContourDetectionProcessorVP.PropPositions.NULL;

        telemetry.addData("Camera State", contourDetector.getCameraState());
        telemetry.addData("Detected Prop Position", contourDetector.getPropPosition());
        telemetry.addData("Largest Contour Location", contourDetector.getLargestContourPos());
        telemetry.addData("Largest Contour Area", contourDetector.getLargestContourPos().toString());

        telemetry.addLine();
        telemetry.addData("Current Prop Position", finalPropPosition);
        telemetry.addData("Left Count", leftCount);
        telemetry.addData("Right Count", rightCount);
        telemetry.addData("Center Count", centerCount);
        telemetry.addData("Null Count", nullCount);
        telemetry.update();
    }

    public void start() {
        if (contourDetector.getCameraState() == VisionPortal.CameraState.STREAMING) {
            contourDetector.stopLiveView()
                    .stopStreaming();
        }

        /*if (leftCount >= Math.max(centerCount, Math.max(rightCount, nullCount)))
            finalPropPosition = ContourDetectionProcessorVP.PropPositions.LEFT;
        else if (rightCount >= Math.max(centerCount, Math.max(leftCount, nullCount)))
            finalPropPosition = ContourDetectionProcessorVP.PropPositions.RIGHT;
        else if (centerCount >= Math.max(leftCount, Math.max(rightCount, nullCount)))
            finalPropPosition = ContourDetectionProcessorVP.PropPositions.CENTER;
        else
            finalPropPosition = ContourDetectionProcessorVP.PropPositions.NULL;*/
    }

    public void loop() {
        telemetry.addData("Detected Prop Position", finalPropPosition);
        telemetry.update();
    }
}
