package org.firstinspires.ftc.teamcode.Systems.Movement;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public abstract class Movement {
    private final ArrayList<Runnable> beforeMovingActions = new ArrayList<>();
    private final ArrayList<Runnable> whileMovingActions = new ArrayList<>();
    private final ArrayList<Runnable> afterMovingActions = new ArrayList<>();

    private Callable<Boolean> alternateEndCondition = () -> false;
    private Runnable alternateEndAction = () -> {};

    private boolean movementComplete = false;
    private boolean running = false;

    public Movement addBeforeMovingAction(Runnable func){
        beforeMovingActions.add(func);
        return this;
    }

    public Movement addWhileMovingAction(Runnable func){
        whileMovingActions.add(func);
        return this;
    }

    public Movement addAfterMovingAction(Runnable func){
        afterMovingActions.add(func);
        return this;
    }

    public Movement setAlternateEndCondition(Callable<Boolean> condition){
        alternateEndCondition = condition;
        return this;
    }

    public Movement setAlternateEndCondition(Callable<Boolean> condition, Runnable alternateEndAction){
        alternateEndCondition = condition;
        this.alternateEndAction = alternateEndAction;
        return this;
    }

    public Movement setAlternateEndAction(Runnable action){
        alternateEndAction = action;
        return this;
    }

    public boolean isRunning() {
        return running;
    }

    public boolean isComplete() {
        return movementComplete;
    }

    public void execute() {
        if (!running && !movementComplete) {
            running = true;
            init();

            for (Runnable runner : beforeMovingActions) {
                runner.run();
            }
        }

        try {
            if (running && !movementComplete) {
                for (Runnable runner : whileMovingActions) {
                    runner.run();
                }

                if (move()) {
                    movementComplete = true;
                    running = false;
                    stop();
                }

                if (alternateEndCondition.call()) {
                    alternateEndAction.run();
                    movementComplete = true;
                    running = false;
                    stop();
                }
            }

            if (!running && movementComplete) {
                for (Runnable runner : afterMovingActions) {
                    runner.run();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void log(Telemetry telemetry) {}

    abstract void init();
    abstract void stop();
    abstract boolean move();
}
