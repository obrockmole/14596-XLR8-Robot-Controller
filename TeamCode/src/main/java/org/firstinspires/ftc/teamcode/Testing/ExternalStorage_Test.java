package org.firstinspires.ftc.teamcode.Testing;

import android.os.Environment;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

//@Disabled
@TeleOp(group = "Testing")
public class ExternalStorage_Test extends OpMode {
    File file;
    FileWriter fileWriter;

    @Override
    public void init() {
        file = new File(Environment.getExternalStorageDirectory() + "/FIRST/test.csv");
        try {
            fileWriter = new FileWriter(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
