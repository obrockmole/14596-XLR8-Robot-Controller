package org.firstinspires.ftc.teamcode.CenterStage.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Systems.Odometry.Odometry;
import org.firstinspires.ftc.teamcode.Systems.Odometry.OdometryPod;
import org.firstinspires.ftc.teamcode.Systems.Sensors.Encoder;

@TeleOp(group = "TeleOp", name = "Encoder Testing")
public class EncoderTest extends OpMode {
    Odometry odometry;

    public void init() {
        odometry = new Odometry(
                new OdometryPod(new Encoder(hardwareMap, "frontLeft", Encoder.Direction.FORWARD)),
                new OdometryPod(new Encoder(hardwareMap, "backRight", Encoder.Direction.FORWARD)),
                new OdometryPod(new Encoder(hardwareMap, "perpOdom", Encoder.Direction.FORWARD))
        );
    }

    public void loop() {
        odometry.log(telemetry, hardwareMap);
    }
}
