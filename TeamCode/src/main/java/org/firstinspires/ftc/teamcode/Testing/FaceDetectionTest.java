package org.firstinspires.ftc.teamcode.Testing;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Systems.Vision.CascadeDetector;

//@Disabled
@TeleOp(group = "Testing")
public class FaceDetectionTest extends OpMode {
    CascadeDetector detector;

    @Override
    public void init() {
        detector = new CascadeDetector(hardwareMap, "Webcam", "frontalface_cascade.xml");
        detector.start();
    }

    @Override
    public void loop() {}
}
