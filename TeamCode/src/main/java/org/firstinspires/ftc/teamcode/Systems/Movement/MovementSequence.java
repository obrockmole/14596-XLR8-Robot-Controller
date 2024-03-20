package org.firstinspires.ftc.teamcode.Systems.Movement;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Systems.Drivetrain;
import org.firstinspires.ftc.teamcode.Systems.Events.Event;
import org.firstinspires.ftc.teamcode.Systems.Events.TimedEvent;
import org.firstinspires.ftc.teamcode.Systems.Odometry.Localizer;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public class MovementSequence extends ArrayList<Movement> {
    private final Drivetrain drivetrain;
    private final Localizer localizer;

    private Movement currentMovement;
    private int currentMovementIndex = 0;

    private final ArrayList<Event> events = new ArrayList<>();

    private boolean running = false;

    private final double defaultPowerLimit = 1;
    private final int defaultHoldTime = 100; //Milliseconds
    private final int defaultTimout = 5000; //Milliseconds

    public MovementSequence(Drivetrain drivetrain, Localizer localizer) {
        super();
        this.drivetrain = drivetrain;
        this.localizer = localizer;
    }

    public MovementSequence addSequence(MovementSequence sequence) {
        addAll(sequence);
        return this;
    }

    public void addMovement(Movement movement) {
        if (movement instanceof OdometryDrive) {
            ((OdometryDrive) movement).setDrivetrain(drivetrain);
            ((OdometryDrive) movement).setLocalizer(localizer);
        }
        add(movement);
    }

    public MovementSequence blank() {
        addMovement(new BlankMovement());
        return this;
    }

    public MovementSequence waitSeconds(double waitTime) {
        addMovement(new WaitMovement(waitTime));
        return this;
    }

    public MovementSequence lineTo(double driveDistance, double strafeDistance, double powerLimit, double holdTime, double timeoutTime) {
        addMovement(new OdometryDrive(driveDistance, strafeDistance, 0, powerLimit, holdTime, timeoutTime));
        return this;
    }

    public MovementSequence lineTo(double driveDistance, double strafeDistance, double holdTime, double timeoutTime) {
        lineTo(driveDistance, strafeDistance, defaultPowerLimit, holdTime, timeoutTime);
        return this;
    }

    public MovementSequence lineTo(double driveDistance, double strafeDistance, double holdTime) {
        lineTo(driveDistance, strafeDistance, defaultPowerLimit, holdTime, defaultTimout);
        return this;
    }

    public MovementSequence lineTo(double driveDistance, double strafeDistance) {
        lineTo(driveDistance, strafeDistance, defaultPowerLimit, defaultHoldTime, defaultTimout);
        return this;
    }

    public MovementSequence lineToHeading(double driveDistance, double strafeDistance, double heading, double powerLimit, double holdTime, double timeoutTime) {
        addMovement(new OdometryDrive(driveDistance, strafeDistance, heading, powerLimit, holdTime, timeoutTime));
        return this;
    }

    public MovementSequence lineToHeading(double driveDistance, double strafeDistance, double heading, double holdTime, double timeoutTime) {
        lineToHeading(driveDistance, strafeDistance, heading, defaultPowerLimit, holdTime, timeoutTime);
        return this;
    }

    public MovementSequence lineToHeading(double driveDistance, double strafeDistance, double heading, double holdTime) {
        lineToHeading(driveDistance, strafeDistance, heading, defaultPowerLimit, holdTime, defaultTimout);
        return this;
    }

    public MovementSequence lineToHeading(double driveDistance, double strafeDistance, double heading) {
        lineToHeading(driveDistance, strafeDistance, heading, defaultPowerLimit, defaultHoldTime, defaultTimout);
        return this;
    }

    public MovementSequence turn(double turnDegrees, double powerLimit, double holdTime, double timeoutTime) {
        addMovement(new OdometryDrive(0, 0, turnDegrees, powerLimit, holdTime, timeoutTime));
        return this;
    }

    public MovementSequence turn(double turnDegrees, double holdTime, double timeoutTime) {
        turn(turnDegrees, defaultPowerLimit, holdTime, timeoutTime);
        return this;
    }

    public MovementSequence turn(double turnDegrees, double holdTime) {
        turn(turnDegrees, defaultPowerLimit, holdTime, defaultTimout);
        return this;
    }

    public MovementSequence turn(double turnDegrees) {
        turn(turnDegrees, defaultPowerLimit, defaultHoldTime, defaultTimout);
        return this;
    }

    public MovementSequence marker(Runnable action) {
        BlankMovement movement = new BlankMovement();
        movement.addBeforeMovingAction(action);
        addMovement(movement);
        return this;
    }

    public MovementSequence beforeMoving(Runnable action) {
        if (size() > 0)
            get(size() - 1).addBeforeMovingAction(action);
        return this;
    }

    public MovementSequence whileMoving(Runnable action) {
        if (size() > 0)
            get(size() - 1).addWhileMovingAction(action);
        return this;
    }

    public MovementSequence afterMoving(Runnable action) {
        if (size() > 0)
            get(size() - 1).addAfterMovingAction(action);
        return this;
    }

    public MovementSequence endCondition(Callable<Boolean> condition) {
        if (size() > 0)
            get(size() - 1).setAlternateEndCondition(condition);
        return this;
    }

    public MovementSequence endCondition(Callable<Boolean> condition, Runnable action) {
        if (size() > 0)
            get(size() - 1).setAlternateEndCondition(condition, action);
        return this;
    }

    public MovementSequence endAction(Runnable action) {
        if (size() > 0)
            get(size() - 1).setAlternateEndAction(action);
        return this;
    }

    public void addEvent(Event event) {
        events.add(event);
    }

    public MovementSequence event(Callable<Boolean> condition, Runnable action) {
        addEvent(new Event(condition, action));
        return this;
    }

    public MovementSequence timedEvent(int milliseconds, Runnable action) {
        addEvent(new TimedEvent(milliseconds, action));
        return this;
    }

    public void jumpTo(int index){
        currentMovementIndex = index;
    }

    public Movement getCurrentMovement() {
        return currentMovement;
    }

    public int getCurrentMovementIndex() {
        return currentMovementIndex;
    }

    public boolean isRunning() {
        return running;
    }

    public void start() {
        currentMovement = get(0);
        currentMovement.execute();

        for (Event event : events) {
            event.reset();
        }

        running = true;
    }

    public void stop() {
        if (currentMovement != null) {
            currentMovement.stop();
        }
        running = false;
    }

    public void update() {
        if (running) {
            for (Event event : events) {
                event.execute();
                if (event.hasExecuted())
                    events.remove(event);
            }

            if (currentMovement != null && currentMovementIndex < size()) {
                if (!currentMovement.isRunning() && currentMovement.isComplete()) {
                    currentMovementIndex++;
                    if (currentMovementIndex < size()) {
                        currentMovement = get(currentMovementIndex);
                        currentMovement.execute();
                    }
                } else {
                    currentMovement.execute();
                }
            }
        }
    }

    public void logMovement(Telemetry telemetry) {
        telemetry.addData("Current Movement", (currentMovementIndex + 1) + "/" + size());
        telemetry.addData("Running", currentMovement.isRunning());
        telemetry.addData("Completed", currentMovement.isComplete());
        telemetry.addLine();
        currentMovement.log(telemetry);
    }
}
