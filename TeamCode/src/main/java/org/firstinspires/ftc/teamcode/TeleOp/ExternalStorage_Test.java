package org.firstinspires.ftc.teamcode.TeleOp;

import android.os.Environment;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Systems.DataLogger;
import org.firstinspires.ftc.teamcode.Systems.Gamepad.Gamepad;
import org.firstinspires.ftc.teamcode.Systems.Gamepad.GamepadButtons.Button;
import org.firstinspires.ftc.teamcode.Systems.Motors.MotorLookupTable;
import org.firstinspires.ftc.teamcode.Systems.Motors.TestingMotor_Logging;
import org.firstinspires.ftc.teamcode.Systems.Timer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

//@Disabled
@TeleOp(group = "Testing")
public class ExternalStorage_Test extends OpMode {
    File file;
    FileWriter fileWriter;

    @Override
    public void init() {
        file = new File(Environment.getExternalStorageDirectory() + "/FIRST/test.csv");
    }

    @Override
    public void start() {
        try {
            fileWriter.write("test");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void loop() {
        telemetry.addLine(Environment.getExternalStorageDirectory().toString());
        telemetry.update();
    }

    @Override
    public void stop() {
        try {
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
