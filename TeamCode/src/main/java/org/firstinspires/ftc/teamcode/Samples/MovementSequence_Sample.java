package org.firstinspires.ftc.teamcode.Samples;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.CenterStage.Robot;
import org.firstinspires.ftc.teamcode.Systems.Movement.MovementSequence;

@Disabled
@Autonomous(group = "Samples", name = "Movement Sequence Sample")
public class MovementSequence_Sample extends OpMode {
    Robot robot;
    MovementSequence movementSequence;

    public void init() {
        robot = new Robot(hardwareMap);

        movementSequence = new MovementSequence(robot);

        /*
            Drive distances and rotation are relative from the robots starting position at the beginning of each individual movement.
                +Forward, +Rightward, +Clockwise.
            The sequence will run through each movement in order, and will not move to the next movement until the current movement is complete.
            Add movements through the addMovement(movement) method, or individual methods, e.g. .odometryDrive(driveDistance, strafeDistance, power, holdTime) or .waitSeconds(seconds).
                Modify each movements before moving, while moving, and after moving actions through the .beforeMoving(), .whileMoving(), and .afterMoving() methods.
                    This will modify the last added movement to the sequence.
                    Before moving and after moving are activated once, while moving is activated every loop during motion.
                Movements can also be given an alternative end condition which will end the movement before intended through the .endCondition(condition) method.
                    A separate end action can also be set to run when the alternative end condition is met through .endAction(action) or .endCondition(condition, action).
                    This will modify the last added movement to the sequence.
            Add events to the sequence through the .addEvent(event) method or .event(condition, action) method.
                Events are ran globally independent of movements and other events, i.e. it does not matter what order they are added
                Timed events are ran globally from the start of the sequence, not from the previous timed event and can be set through .timedEvent(milliseconds, action).
                    I.e. .timedEvent(1000, () -> intake.setPower(0.5))
                         .timedEvent(2000, () -> intake.setPower(0))
                         Will set the intake to 0.5 power 1 second after the sequence starts and 0 power 2 seconds after the sequence starts (1 second after the power was set to 0.5).
            Relative events can be added through .marker(action)
                Unlike traditional events, these do not take a condition and are ran in relation to the rest of the sequence.
                    This means that order matters and they will run after the previous defined movement is complete.

            Current movement types:
                - BlankMovement (.blank()): A blank movement that does nothing, can be used as an alternative to markers with alternative end condition and action.
                - WaitMovement (.waitSeconds(double waitTime)): A blank movement that waits for a set amount of time in seconds.
                - OdometryDrive (.odometryDrive(double driveDistance, double strafeDistance, double power, double holdTime)): A drive movement that moves the robot a set distance in inches both forward and rightward.
                - OdometryTurn (.odometryTurn(double turnDegrees, double power, double holdTime)): A turn movement that turns the robot a set amount of degrees.
            Current event types:
                - BlankEvent: A blank event that does nothing, use not recommended.
                - TimedEvent (.timedEvent(int milliseconds, Runnable action)): A timed event that runs a set action after a set amount of time in milliseconds from the sequence start.
        */

        movementSequence.odometryDrive(-28, 0, 0.8, 0)
                .odometryTurn(90,0.6, 100)
                .waitSeconds(2)
                .odometryDrive(-26, 0, 0.8, 1000)
                .waitSeconds(1)
                .odometryDrive(-8, -28, 0.7, 0);
    }

    public void start() {
        movementSequence.start();
    }

    public void loop() {
        movementSequence.update();
        movementSequence.logMovement(telemetry);

        robot.update(true)
                .log(telemetry, true, true);
    }
}
