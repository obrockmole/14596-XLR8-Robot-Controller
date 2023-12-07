package org.firstinspires.ftc.teamcode.CenterStage.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Systems.Vision.ContourDetectionProcessorVP;
import org.firstinspires.ftc.teamcode.Systems.Vision.HuskyLens;

import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

@TeleOp(name = "HuskyLens Vision Test", group = "Testing")
public class HuskyLensVisionTest extends OpMode {
    HuskyLens huskyLens;

    ArrayList<Integer> propPositions = new ArrayList<>();
    int currentPropPosition = -1;

    public void init() {
        huskyLens = new HuskyLens(hardwareMap, "husky", com.qualcomm.hardware.dfrobot.HuskyLens.Algorithm.OBJECT_RECOGNITION);

        if (huskyLens.knock())
            telemetry.addLine("HuskyLens knocked successfully.");
        else
            telemetry.addLine("HuskyLens knock failed.");
    }

    public void init_loop() {
        huskyLens.update();

        com.qualcomm.hardware.dfrobot.HuskyLens.Block[] blocks = huskyLens.getBlocks();
        for (com.qualcomm.hardware.dfrobot.HuskyLens.Block block : blocks) {
            if (block.id == 1) {
                if (block.x < 120)
                    propPositions.add(0);
                else if (block.x > 200)
                    propPositions.add(2);
                else
                    propPositions.add(1);
            }
        }

        if (propPositions.size() > 1000) {
            propPositions.remove(0);
        }

        currentPropPosition = propPositions.stream()
                .collect(Collectors.groupingBy(i -> i, Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(-1);

        huskyLens.log(telemetry, hardwareMap);

        telemetry.addLine();
        telemetry.addData("Current Prop Position", currentPropPosition);
        telemetry.addData("Left Count", propPositions.stream().filter(i -> i == 0).count());
        telemetry.addData("Right Count", propPositions.stream().filter(i -> i == 1).count());
        telemetry.addData("Center Count", propPositions.stream().filter(i -> i == 2).count());
        telemetry.update();
    }

    public void loop() {
        telemetry.addData("Current Prop Position", currentPropPosition);
        telemetry.update();
    }
}
