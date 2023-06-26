package org.firstinspires.ftc.teamcode.TeleOp;

import android.os.Environment;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.Systems.DataLogger;
import org.firstinspires.ftc.teamcode.Systems.Gamepad.Gamepad;
import org.firstinspires.ftc.teamcode.Systems.Gamepad.GamepadButtons.Button;
import org.firstinspires.ftc.teamcode.Systems.Motors.Motor;
import org.firstinspires.ftc.teamcode.Systems.Motors.MotorLookupTable;
import org.firstinspires.ftc.teamcode.Systems.Motors.TestingMotor_Logging;
import org.firstinspires.ftc.teamcode.Systems.Timer;

import java.io.IOException;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

//@Disabled
@TeleOp(group = "Testing")
public class DataLogging_Test extends OpMode {
    TestingMotor_Logging motor1;

    Gamepad gamepad;
    int[] positions; //List of saved positions to switch between

    DataLogger positionLogger;
    Timer timer;

    @Override
    public void init() {
        gamepad =  new Gamepad(gamepad1);

        motor1 = new TestingMotor_Logging(hardwareMap, "posMotor1", MotorLookupTable.GOBILDA_435, 10, false);
        positions = new int[]{0, 400, 750, 1200};

        motor1.setPIDF(0, 0, 0, 0)
                .setTargetPosition(positions[0]);

        positionLogger = new DataLogger(Environment.getExternalStorageDirectory() + "/FIRST/PositionMotor.csv");
        timer = new Timer();
    }

    @Override
    public void start() {
        positionLogger.startLogging("Time,Power,Position,TargetPosition");
        timer.start();

        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
        executor.scheduleAtFixedRate(() -> positionLogger.writeData(timer.getTimeSeconds() + motor1.getCSVData()), 0, 100, TimeUnit.MILLISECONDS);
    }

    @Override
    public void loop() {
        gamepad.onPress(Button.A, () -> motor1.setTargetPosition(positions[0]))
                .onPress(Button.B, () -> motor1.setTargetPosition(positions[1]))
                .onPress(Button.X, () -> motor1.setTargetPosition(positions[2]))
                .onPress(Button.Y, () -> motor1.setTargetPosition(positions[3]))
                .update();

        motor1.log(telemetry, hardwareMap)
                .update();
    }

    @Override
    public void stop() {
        positionLogger.stopLogging();
    }
}
