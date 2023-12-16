package org.firstinspires.ftc.teamcode.CenterStage.Autonomous;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.RoadRunner.Drive.MecanumDrive;
import org.firstinspires.ftc.teamcode.RoadRunner.TrajectorySequence.TrajectorySequence;

@Disabled
@Autonomous(group = "@aa", name = "please work last cry")
public class AutoTest2 extends OpMode {
    MecanumDrive drive;
    TrajectorySequence trajSequence;
    Pose2d startPos;

    public void init() {
        drive = new MecanumDrive(hardwareMap);

        startPos = new Pose2d(-36, -64, Math.toRadians(90));
        trajSequence = drive.trajectorySequenceBuilder(startPos)
                //Park
                .setTangent(Math.toRadians(90))
                .splineToConstantHeading(new Vector2d(-32, -12), Math.toRadians(0))
                .splineTo(new Vector2d(44, -12), Math.toRadians(0))
                .build();
    }

    public void start() {
        drive.setPoseEstimate(startPos);
        drive.followTrajectorySequenceAsync(trajSequence);
    }

    public void loop() {
        drive.update();
    }
}
