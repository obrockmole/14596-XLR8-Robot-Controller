package org.firstinspires.ftc.teamcode.Samples;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Systems.Vision.PixyCam;

//@Disabled
@TeleOp(group = "Samples")
public class VisionPixyCam_Sample extends OpMode {
    PixyCam pixyCam;

    @Override
    public void init() {
        pixyCam = new PixyCam(hardwareMap, "pixy");
        pixyCam.initialize();
        pixyCam.engage();
    }

    @Override
    public void loop() {
        pixyCam.log(telemetry);
    }
}
