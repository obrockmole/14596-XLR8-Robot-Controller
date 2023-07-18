package org.firstinspires.ftc.teamcode.Samples;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.internal.system.AppUtil;
import org.firstinspires.ftc.teamcode.Systems.Vision.CascadeDetector;

@Disabled
@TeleOp(group = "Samples")
public class VisionCascadeDetection_Sample extends OpMode {
    CascadeDetector detector;

    @Override
    public void init() {
        detector = new CascadeDetector(hardwareMap, "Webcam", AppUtil.ROOT_FOLDER + "/FIRST/CascadeFiles/frontalface_cascade.xml"); //Initialize the detector. The Haar Cascade file to be used is passed as a parameter.
        detector.start(); //Start the detector.
        //To learn how this detector works, see https://www.will-berger.org/cascade-haar-explained/.
    }

    @Override
    public void loop() {}
}
