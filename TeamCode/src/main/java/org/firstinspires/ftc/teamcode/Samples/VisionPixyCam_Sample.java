package org.firstinspires.ftc.teamcode.Samples;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Systems.Vision.Pixy;

//@Disabled
@TeleOp(group = "Samples", name = "Pixy Cam Sample")
public class VisionPixyCam_Sample extends OpMode {
    Pixy pixyCam;

    @Override
    public void init() {
        pixyCam = hardwareMap.get(Pixy.class, "pixy");
    }

    @Override
    public void loop() {
        byte[] data = pixyCam.readData();
        telemetry.addData("Byte 0", data[0]);
        telemetry.addData("Byte 1", data[1]);
        telemetry.addData("Byte 2", data[2]);
        telemetry.addData("Byte 3", data[3]);
        telemetry.addData("Byte 4", data[4]);
        telemetry.addData("Byte 5", data[5]);
        telemetry.addData("Byte 6", data[6]);
        telemetry.addData("Byte 7", data[7]);
        telemetry.addData("Byte 8", data[8]);
        telemetry.addData("Byte 9", data[9]);
        telemetry.addData("Byte 10", data[10]);
        telemetry.addData("Byte 11", data[11]);
        telemetry.addData("Byte 12", data[12]);
        telemetry.addData("Byte 13", data[13]);
        telemetry.update();
    }
}
