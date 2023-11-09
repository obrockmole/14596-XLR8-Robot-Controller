package org.firstinspires.ftc.teamcode.Systems.DataFilters;

import java.util.LinkedList;
import java.util.Queue;

/* WARNING: UNTESTED, MAY NOT WORK */
public class MovingAverageFilter {
    private final Queue<Double> buffer;
    private final int windowSize;
    private double sum;
    private double stateEstimate;

    public MovingAverageFilter(int windowSize) {
        this.buffer = new LinkedList<>();
        this.windowSize = windowSize;
        this.sum = 0;
        this.stateEstimate = 0;
    }

    public double getStateEstimate() {
        return stateEstimate;
    }

    public MovingAverageFilter setStateEstimate(double stateEstimate) {
        this.stateEstimate = stateEstimate;
        return this;
    }

    public MovingAverageFilter clear() {
        buffer.clear();
        sum = 0;
        stateEstimate = 0;

        return this;
    }

    public MovingAverageFilter update(double state) {
        buffer.add(state);
        sum += state;

        if (buffer.size() > windowSize)
            sum -= buffer.poll();

        setStateEstimate(sum / buffer.size());

        return this;
    }
}
