package org.firstinspires.ftc.teamcode.CenterStage.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Systems.DataLogger;
import org.firstinspires.ftc.teamcode.Systems.Odometry.Odometry;
import org.firstinspires.ftc.teamcode.Systems.Odometry.OdometryPod;
import org.firstinspires.ftc.teamcode.Systems.Sensors.Encoder;

import java.util.Locale;

@Disabled
@TeleOp(group = "TeleOp", name = "Odometry Logging")
public class OdometryLog extends OpMode {
    Odometry odometry;
    DataLogger dataLogger;

    public void init() {
        odometry = new Odometry(
                new OdometryPod(new Encoder(hardwareMap, "frontLeft", Encoder.Direction.REVERSE)),
                new OdometryPod(new Encoder(hardwareMap, "backRight", Encoder.Direction.REVERSE)),
                new OdometryPod(new Encoder(hardwareMap, "intake", Encoder.Direction.FORWARD))
        );

        dataLogger = new DataLogger("Odometry");
    }

    public void start() {
        dataLogger.startLogging("Time,Left,Right,Perpendicular");
    }

    public void loop() {
        odometry.log(telemetry, hardwareMap);
        dataLogger.writeData(String.format(Locale.US, "%f,%d,%d,%d", getRuntime(), odometry.getCurrentPosition(0), odometry.getCurrentPosition(1), odometry.getCurrentPosition(2)));
    }

    public void stop() {
        dataLogger.stopLogging();
    }
}
