package org.firstinspires.ftc.teamcode.Samples;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Systems.Vision.HuskyLens;

/* WARNING: UNTESTED, MAY NOT WORK */
@Disabled
@TeleOp(group = "Samples", name = "Husky Lens Tag Recognition Sample")
public class VisionHuskyLensTag_Sample extends OpMode {
    HuskyLens huskyLens;

    @Override
    public void init() {
        huskyLens = new HuskyLens(hardwareMap, "husky", com.qualcomm.hardware.dfrobot.HuskyLens.Algorithm.TAG_RECOGNITION);
    }

    @Override
    public void loop() {
        huskyLens.update();
        huskyLens.log(telemetry, hardwareMap);

        telemetry.addLine();
        telemetry.addLine();

        com.qualcomm.hardware.dfrobot.HuskyLens.Block[] blocks = huskyLens.getBlocks();
        for (com.qualcomm.hardware.dfrobot.HuskyLens.Block block : blocks) {
            telemetry.addData("ID", block.id);
            telemetry.addData("Center", block.x + ", " + block.y);
            telemetry.addData("Top Left", block.top + ", " + block.left);
            telemetry.addData("Size", block.height + ", " + block.width);
            telemetry.addLine();
        }
        telemetry.update();
    }
}
