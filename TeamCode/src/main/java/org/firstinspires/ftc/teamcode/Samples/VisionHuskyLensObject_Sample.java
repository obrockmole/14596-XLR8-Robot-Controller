package org.firstinspires.ftc.teamcode.Samples;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Systems.Sensors.HuskyLens;

//@Disabled
@TeleOp(group = "Samples", name = "Husky Lens Object Recognition Sample")
public class VisionHuskyLensObject_Sample extends OpMode {
    HuskyLens huskyLens;

    @Override
    public void init() {
        huskyLens = new HuskyLens(hardwareMap, "husky", com.qualcomm.hardware.dfrobot.HuskyLens.Algorithm.OBJECT_RECOGNITION);
    }

    @Override
    public void loop() {
        huskyLens.update();
        huskyLens.log(telemetry);

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
