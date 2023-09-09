package org.firstinspires.ftc.teamcode.RoadRunner.Tuning;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.RoadRunner.MecanumDrive;

public final class AdvancedSplineTest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        if (TuningOpModes.DRIVE_CLASS.equals(MecanumDrive.class)) {
            MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(0, 0, 0));

            waitForStart();

            Actions.runBlocking(
                drive.actionBuilder(drive.pose)
                        .splineTo(new Vector2d(24, 0), 0)
                        .splineTo(new Vector2d(24, -24), -Math.PI / 2)
                        .splineTo(new Vector2d(48, -20), 0)
                        .splineTo(new Vector2d(64, -24), 0)
                        .build());
        } else {
            throw new AssertionError();
        }
    }
}
