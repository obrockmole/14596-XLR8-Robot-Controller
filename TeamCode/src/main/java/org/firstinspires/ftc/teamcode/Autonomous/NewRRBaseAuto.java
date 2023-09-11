package org.firstinspires.ftc.teamcode.Autonomous;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.RoadRunner.MecanumDrive;
import org.firstinspires.ftc.teamcode.Systems.Robot;
import org.firstinspires.ftc.teamcode.Systems.Stopwatch;

public abstract class NewRRBaseAuto extends OpMode {
    protected Robot robot;
    protected Stopwatch stopwatch;

    protected MecanumDrive drive;
    Action action;

    public void init() {
        robot = new Robot(hardwareMap);
        stopwatch = new Stopwatch();

        drive = new MecanumDrive(hardwareMap, new Pose2d(0, 0, 0));
        setAction();
    }

    public void start() {
        stopwatch.start();
        Actions.runBlocking(action);
    }

    public void loop() {
        robot.update();

        robot.log(telemetry);
        telemetry.addLine("-----Other-----");
        telemetry.addData("Runtime", getRuntime());
        telemetry.update();
    }

    public abstract void setAction();
}
