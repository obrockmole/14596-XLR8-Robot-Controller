package org.firstinspires.ftc.teamcode.Systems.Events;

import com.qualcomm.robotcore.util.ElapsedTime;

public class TimedEvent extends Event {
    ElapsedTime elapsedTime = new ElapsedTime();

    public TimedEvent(int milliseconds, Runnable action) {
        super(() -> false, action);
        setCondition(() -> elapsedTime.milliseconds() > milliseconds);
    }

    public void reset() {
        super.reset();
        elapsedTime.reset();
    }
}