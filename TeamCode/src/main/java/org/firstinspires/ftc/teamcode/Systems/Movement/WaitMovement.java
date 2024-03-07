package org.firstinspires.ftc.teamcode.Systems.Movement;

import com.qualcomm.robotcore.util.ElapsedTime;

public class WaitMovement extends Movement {
    private double waitTime;
    private ElapsedTime timer = new ElapsedTime(ElapsedTime.Resolution.SECONDS);

    public WaitMovement(double waitTime) {
        this.waitTime = waitTime;
    }

    public void setWaitTime(double waitTime) {
        this.waitTime = waitTime;
    }

    void init() {
        timer.reset();
    }
    void stop() {}

    boolean move() {
        return timer.time() > waitTime;
    }
}
