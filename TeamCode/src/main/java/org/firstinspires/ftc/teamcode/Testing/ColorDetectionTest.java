package org.firstinspires.ftc.teamcode.Testing;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Systems.Vision.ColorDetector;

//@Disabled
@TeleOp(group = "Testing")
public class ColorDetectionTest extends OpMode {
    ColorDetector detector;

    @Override
    public void init() {
        detector = new ColorDetector(hardwareMap, "Webcam");
        detector.start();
    }

    @Override
    public void loop() {}
}
