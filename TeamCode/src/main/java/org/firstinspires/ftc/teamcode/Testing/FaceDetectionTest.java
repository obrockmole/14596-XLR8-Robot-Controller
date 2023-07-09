package org.firstinspires.ftc.teamcode.Testing;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Systems.Vision.ColorDetector;
import org.firstinspires.ftc.teamcode.Systems.Vision.FaceDetector;

//@Disabled
@TeleOp(group = "Testing")
public class FaceDetectionTest extends OpMode {
    FaceDetector detector;

    @Override
    public void init() {
        detector = new FaceDetector(hardwareMap, "Webcam");
        detector.start();
    }

    @Override
    public void loop() {}
}
