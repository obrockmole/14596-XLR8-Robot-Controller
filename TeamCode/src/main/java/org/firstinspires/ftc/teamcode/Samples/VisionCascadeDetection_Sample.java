package org.firstinspires.ftc.teamcode.Samples;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.internal.system.AppUtil;
import org.firstinspires.ftc.teamcode.Systems.Vision.CascadeDetectionPipeline;
import org.firstinspires.ftc.teamcode.Systems.Vision.DrawStrategy;
import org.firstinspires.ftc.teamcode.Systems.Vision.VisionDetector;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

@Disabled
@TeleOp(group = "Samples", name = "Cascade Detection Sample")
public class VisionCascadeDetection_Sample extends OpMode implements DrawStrategy {
    VisionDetector cascadeDetector;
    CascadeDetectionPipeline pipeline;

    @Override
    public void init() {
        pipeline = new CascadeDetectionPipeline(this, AppUtil.ROOT_FOLDER + "/FIRST/CascadeFiles/frontalface_cascade.xml"); //Initialize the pipeline.
        cascadeDetector = new VisionDetector(hardwareMap, "Webcam", pipeline); //Initialize the detector.
        cascadeDetector.start(); //Start the detector.
        //To learn how this detector works, see https://www.will-berger.org/cascade-haar-explained/.
    }

    @Override
    public void loop() {}

    @Override
    public void drawOnFrame(Mat frame) {
        Rect rect = Imgproc.boundingRect(cascadeDetector.getDetection());
        Imgproc.rectangle(frame, rect, new Scalar(0, 255, 0));
    }
}
