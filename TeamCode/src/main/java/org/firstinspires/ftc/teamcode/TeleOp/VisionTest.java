package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Systems.Vision.ContourDetector;

//@Disabled
@TeleOp(group = "Testing")
public class VisionTest extends OpMode {
    ContourDetector detector;

    @Override
    public void init() {
        detector = new ContourDetector(hardwareMap, "Webcam");
        detector.start();
    }

    @Override
    public void loop() {}
}
