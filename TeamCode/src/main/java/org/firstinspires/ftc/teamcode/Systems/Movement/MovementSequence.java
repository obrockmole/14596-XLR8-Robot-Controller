package org.firstinspires.ftc.teamcode.Systems.Movement;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Systems.Drivetrain;
import org.firstinspires.ftc.teamcode.Systems.Events.Event;
import org.firstinspires.ftc.teamcode.Systems.Events.TimedEvent;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public class MovementSequence extends ArrayList<Movement> {
    private Drivetrain drivetrain;

    private Movement currentMovement;
    private int currentMovementIndex = 0;

    private ArrayList<Event> events = new ArrayList<>();

    private boolean running = false;

    public MovementSequence(Drivetrain drivetrain) {
        super();
        this.drivetrain = drivetrain;
    }

    public MovementSequence addSequence(MovementSequence sequence) {
        addAll(sequence);
        return this;
    }

    public MovementSequence addMovement(Movement movement) {
        if (movement instanceof OdometryDrive)
            ((OdometryDrive) movement).setDrivetrain(drivetrain);
        else if (movement instanceof OdometryTurn)
            ((OdometryTurn) movement).setDrivetrain(drivetrain);
        add(movement);
        return this;
    }

    public MovementSequence blank() {
        addMovement(new BlankMovement());
        return this;
    }

    public MovementSequence waitSeconds(double waitTime) {
        addMovement(new WaitMovement(waitTime));
        return this;
    }

    public MovementSequence odometryDrive(double driveDistance, double strafeDistance, double power, double holdTime) {
        addMovement(new OdometryDrive(driveDistance, strafeDistance, power, holdTime));
        return this;
    }

    public MovementSequence odometryTurn(double turnDegrees, double power, double holdTime) {
        addMovement(new OdometryTurn(turnDegrees, power, holdTime));
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

    public MovementSequence addEvent(Event event) {
        events.add(event);
        return this;
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
        telemetry.addData("Running Status", currentMovement.isRunning());
        telemetry.addData("Completion Status", currentMovement.isComplete());
        telemetry.addLine();
        currentMovement.log(telemetry);
    }
}
