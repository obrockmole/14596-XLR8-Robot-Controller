package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.internal.system.AppUtil;
import org.firstinspires.ftc.teamcode.Systems.DataLogger;
import org.firstinspires.ftc.teamcode.Systems.Odometry.Odometry;
import org.firstinspires.ftc.teamcode.Systems.Odometry.OdometryPod;
import org.firstinspires.ftc.teamcode.Systems.Sensors.Encoder;
import org.firstinspires.ftc.teamcode.Systems.Stopwatch;

import java.io.File;

@TeleOp(group = "TeleOp", name = "Encoder Testing w/ Position Logging")
public class LoggingEncoderTest extends OpMode {
    Odometry odometry;
    DataLogger positionLogger;
    Stopwatch stopwatch;

    public void init() {
        odometry = new Odometry(
                new OdometryPod(new Encoder(hardwareMap, "frontLeft", Encoder.Direction.FORWARD)),
                new OdometryPod(new Encoder(hardwareMap, "backRight", Encoder.Direction.FORWARD)),
                new OdometryPod(new Encoder(hardwareMap, "perpOdom", Encoder.Direction.FORWARD))
        );

        positionLogger = new DataLogger(new File(AppUtil.ROOT_FOLDER + "/FIRST/LoggedData/", "Odometry.csv"));
        stopwatch = new Stopwatch();
    }

    public void start() {
        positionLogger.startLogging("Time,Left,Right,Perpendicular");
        stopwatch.start();
    }

    public void loop() {
        positionLogger.writeData(stopwatch.getTimeSeconds() + String.format(",%s,%s,%s", odometry.getCurrentPosition(0), odometry.getCurrentPosition(1), odometry.getCurrentPosition(2)));

        odometry.update()
                .log(telemetry, hardwareMap);
    }

    public void stop() {
        positionLogger.stopLogging();
    }
}
