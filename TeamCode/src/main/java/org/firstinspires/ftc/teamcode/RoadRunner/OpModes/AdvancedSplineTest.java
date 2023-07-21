package org.firstinspires.ftc.teamcode.RoadRunner.OpModes;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.RoadRunner.Drive.SampleMecanumDrive;

/*
 * This is an example of a more complex path to really test the tuning.
 */
//@Disabled
@Autonomous(group = "RoadRunner")
public class AdvancedSplineTest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        waitForStart();

        if (isStopRequested()) return;

        Trajectory traj = drive.trajectoryBuilder(new Pose2d())
                .splineTo(new Vector2d(24, 0), 0)
                .splineTo(new Vector2d(24, -24), -Math.PI / 2)
                .splineTo(new Vector2d(48, -20), 0)
                .splineTo(new Vector2d(64, -24), 0)
                .build();

        drive.followTrajectory(traj);

        sleep(2000);

        drive.followTrajectory(
                drive.trajectoryBuilder(traj.end(), true)
                        .splineTo(new Vector2d(48, -20), Math.PI)
                        .splineTo(new Vector2d(24, -24), Math.PI / 2)
                        .splineTo(new Vector2d(24, 0), Math.PI)
                        .splineTo(new Vector2d(0, 0), Math.PI)
                        .build()
        );
    }
}