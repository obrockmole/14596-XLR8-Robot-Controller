package org.firstinspires.ftc.teamcode.CenterStage.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.CenterStage.Robot;
import org.firstinspires.ftc.teamcode.RoadRunner.Drive.MecanumDrive;
import org.firstinspires.ftc.teamcode.RoadRunner.TrajectorySequence.TrajectorySequence;

public abstract class BaseAuto extends OpMode {
    Robot robot;

    MecanumDrive drive;
    TrajectorySequence sequence;

    public void init() {
        robot = new Robot(hardwareMap);
        drive = new MecanumDrive(hardwareMap);

        initTrajectory();
    }

    public void start() {
        drive.setPoseEstimate(sequence.start());
        drive.followTrajectorySequenceAsync(sequence);
    }

    public void loop() {
        drive.update();
        robot.update()
                .log(telemetry);

        telemetry.addLine("-----Other-----");
        telemetry.addData("Runtime", getRuntime());
        telemetry.update();
    }

    public abstract void initTrajectory();
}
