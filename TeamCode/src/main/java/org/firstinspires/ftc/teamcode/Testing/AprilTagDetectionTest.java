package org.firstinspires.ftc.teamcode.Testing;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Systems.Vision.AprilTagDetector;
import org.firstinspires.ftc.teamcode.Systems.Vision.CascadeDetector;

//@Disabled
@TeleOp(group = "Testing")
public class AprilTagDetectionTest extends OpMode {
    AprilTagDetector detector;

    @Override
    public void init() {
        detector = new AprilTagDetector(hardwareMap, "Webcam");
        detector.start();
    }

    @Override
    public void loop() {}
}
