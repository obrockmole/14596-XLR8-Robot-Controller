package org.firstinspires.ftc.teamcode.CenterStage.Autonomous;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.sfdev.assembly.state.StateMachine;
import com.sfdev.assembly.state.StateMachineBuilder;

import org.firstinspires.ftc.teamcode.RoadRunner.Drive.MecanumDrive;
import org.firstinspires.ftc.teamcode.RoadRunner.TrajectorySequence.TrajectorySequence;

@Autonomous(name = "FMS. why no woooooork")
public class AutoTest3 extends LinearOpMode {
    MecanumDrive drive;

    enum states {
        seq1,
        seq2,

        fallback1,
        fallback2
    }

    public void runOpMode() {
        drive = new MecanumDrive(hardwareMap);
        drive.setPoseEstimate(new Pose2d(0, 0, 0));

        ElapsedTime runtime = new ElapsedTime();

        TrajectorySequence sequence1 = drive.trajectorySequenceBuilder(new Pose2d(0, 0, 0))
            .lineTo(new Vector2d(20, 0))
            .lineTo(new Vector2d(20, 20))
            .build();

        TrajectorySequence sequence2 = drive.trajectorySequenceBuilder(new Pose2d(20, 20, 0))
            .lineTo(new Vector2d(0, 20))
            .lineTo(new Vector2d(0, 0))
            .build();

        StateMachine machine = new StateMachineBuilder()
            .state(states.seq1)
            .onEnter(() -> {
                drive.followTrajectorySequenceAsync(sequence1);
                runtime.reset();
            })
            .transition(() -> !drive.isBusy(), states.seq2)
            .transition(() -> runtime.seconds() > 5 && drive.isBusy(), states.fallback1, () -> telemetry.addLine("Falling back!"))

            .state(states.seq2)
            .onEnter(() -> {
                drive.followTrajectorySequenceAsync(sequence2);
                runtime.reset();
            })
            .transition(() -> runtime.seconds() > 5 && drive.isBusy(), states.fallback2, () -> telemetry.addLine("Falling back!"))

            .state(states.fallback1, true)
            .onEnter(() -> drive.followTrajectorySequenceAsync(
                drive.trajectorySequenceBuilder(drive.getPoseEstimate())
                .lineTo(new Vector2d(0, 0))
                .build()
            ))
            .transition(() -> !drive.isBusy(), states.seq1, () -> {
                runtime.reset();
                telemetry.addLine("Going back to main machine");
            })

            .state(states.fallback2, true)
            .onEnter(() -> drive.followTrajectorySequenceAsync(
                drive.trajectorySequenceBuilder(drive.getPoseEstimate())
                .lineTo(new Vector2d(20, 20))
                .build()
            ))
            .transition(() -> !drive.isBusy(), states.seq2, () -> {
                runtime.reset();
                telemetry.addLine("Going back to main machine");
            })
            .build();

        waitForStart();

        machine.start();

        while(opModeIsActive()) {
            drive.update();
            machine.update();
            telemetry.update();
        }
    }
}
