package org.firstinspires.ftc.teamcode.Runnable.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Systems.Odometry.Odometry;
import org.firstinspires.ftc.teamcode.Systems.Odometry.OdometryPod;
import org.firstinspires.ftc.teamcode.Systems.Sensors.Encoder;

//@Disabled
@TeleOp(group = "TeleOp", name = "Odometry Testing")
public class OdometryTest extends OpMode {
    Odometry odometry;

    public void init() {
        odometry = new Odometry(
                new OdometryPod(new Encoder(hardwareMap, "frontLeft", Encoder.Direction.REVERSE)),
                new OdometryPod(new Encoder(hardwareMap, "backRight", Encoder.Direction.REVERSE)),
                new OdometryPod(new Encoder(hardwareMap, "intake1", Encoder.Direction.FORWARD))
        );
    }

    public void loop() {
        odometry.log(telemetry, hardwareMap);
    }
}
