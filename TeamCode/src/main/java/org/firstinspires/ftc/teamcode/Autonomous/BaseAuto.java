package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.sfdev.assembly.state.StateMachine;
import com.sfdev.assembly.state.StateMachineBuilder;

import org.firstinspires.ftc.teamcode.RoadRunner.Drive.MecanumDrive;
import org.firstinspires.ftc.teamcode.RoadRunner.TrajectorySequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.Systems.AutoMenu.Menu;
import org.firstinspires.ftc.teamcode.Systems.AutoMenu.MenuItem;
import org.firstinspires.ftc.teamcode.Systems.Robot;
import org.firstinspires.ftc.teamcode.Systems.Stopwatch;
import org.firstinspires.ftc.teamcode.Systems.Gamepad.GamepadButtons.Button;

import java.util.HashMap;

public abstract class BaseAuto extends OpMode {
    protected Robot robot;
    protected StateMachine stateMachine;
    protected Stopwatch stopwatch;

    protected MecanumDrive drive;
    public TrajectorySequence sequence1, sequence2;

    enum States {
        START, END,

        FALLBACK1, FALLBACK2
    }

    public void init() {
        stopwatch = new Stopwatch();

        drive = new MecanumDrive(hardwareMap);

        initTrajectories();

        stateMachine = new StateMachineBuilder()
                .state(States.START)
                .onEnter(() -> {
                    drive.followTrajectorySequenceAsync(sequence1);
                    stopwatch.restart();
                })
                .transition(() -> !drive.isBusy(), States.END)
                .transition(() -> stopwatch.getTimeSeconds() > 6 && drive.isBusy(), States.FALLBACK1, () -> telemetry.addLine("Falling back!"))

                .state(States.END)
                .onEnter(() -> {
                    drive.followTrajectorySequenceAsync(sequence2);
                    stopwatch.restart();
                })
                .transition(() -> stopwatch.getTimeSeconds() > 6 && drive.isBusy(), States.FALLBACK2, () -> telemetry.addLine("Falling back!"))


                .state(States.FALLBACK1, true)
                .onEnter(() -> drive.followTrajectorySequenceAsync(
                        drive.trajectorySequenceBuilder(drive.getPoseEstimate())
                                .lineTo(sequence1.start().vec())
                                .build()))
                .transition(() -> !drive.isBusy(), States.START, () -> {
                    stopwatch.getTimeSeconds();
                    telemetry.addLine("Returning to main machine");
                })

                .state(States.FALLBACK2, true)
                .onEnter(() -> drive.followTrajectorySequenceAsync(
                        drive.trajectorySequenceBuilder(drive.getPoseEstimate())
                                .lineTo(sequence2.start().vec())
                                .build()))
                .transition(() -> !drive.isBusy(), States.END, () -> {
                    stopwatch.restart();
                    telemetry.addLine("Returning to main machine");
                })
                .build();
    }

    public void start() {
        stopwatch.start();
        stateMachine.start();
    }

    public void loop() {
        drive.update();
        stateMachine.update();
        telemetry.update();
    }

    public abstract void initTrajectories();
}
