package org.firstinspires.ftc.teamcode.Systems.DataFilters;

/* WARNING: UNTESTED, MAY NOT WORK */
public class KalmanFilter {
    private double measurement;
    private double stateEstimate;
    private double estimateUncertainty;
    private double kalmanGain;
    private double controlInput;

    private double processNoiseUncertainty;
    private double measurementNoiseUncertainty;

    private double previousStateEstimate;
    private double previousEstimateUncertainty;

    public KalmanFilter() {
        this(0, 0, 1, 1, 0, 0.1, 0.4);
    }

    public KalmanFilter(double measurement, double stateEstimate, double estimateUncertainty, double kalmanGain, double controlInput, double processNoiseUncertainty, double measurementNoiseUncertainty) {
        this.measurement = measurement;
        this.stateEstimate = stateEstimate;
        this.estimateUncertainty = estimateUncertainty;
        this.kalmanGain = kalmanGain;
        this.controlInput = controlInput;

        this.processNoiseUncertainty = processNoiseUncertainty;
        this.measurementNoiseUncertainty = measurementNoiseUncertainty;

        this.previousStateEstimate = stateEstimate;
        this.previousEstimateUncertainty = estimateUncertainty;
    }

    public double getMeasurement() {
        return measurement;
    }

    public KalmanFilter setMeasurement(double measurement) {
        this.measurement = measurement;
        return this;
    }

    public double getStateEstimate() {
        return stateEstimate;
    }

    public KalmanFilter setStateEstimate(double stateEstimate) {
        this.stateEstimate = stateEstimate;
        return this;
    }

    public double getEstimateUncertainty() {
        return estimateUncertainty;
    }

    public KalmanFilter setEstimateUncertainty(double estimateUncertainty) {
        this.estimateUncertainty = estimateUncertainty;
        return this;
    }

    public double getKalmanGain() {
        return kalmanGain;
    }

    public KalmanFilter setKalmanGain(double kalmanGain) {
        this.kalmanGain = kalmanGain;
        return this;
    }

    public double getControlInput() {
        return controlInput;
    }

    public KalmanFilter setControlInput(double controlInput) {
        this.controlInput = controlInput;
        return this;
    }

    public double getProcessNoiseUncertainty() {
        return processNoiseUncertainty;
    }

    public KalmanFilter setProcessNoiseUncertainty(double processNoiseUncertainty) {
        this.processNoiseUncertainty = processNoiseUncertainty;
        return this;
    }

    public double getMeasurementNoiseUncertainty() {
        return measurementNoiseUncertainty;
    }

    public KalmanFilter setMeasurementNoiseUncertainty(double measurementNoiseUncertainty) {
        this.measurementNoiseUncertainty = measurementNoiseUncertainty;
        return this;
    }

    public double getPreviousStateEstimate() {
        return previousStateEstimate;
    }

    public KalmanFilter setPreviousStateEstimate(double previousStateEstimate) {
        this.previousStateEstimate = previousStateEstimate;
        return this;
    }

    public double getPreviousEstimateUncertainty() {
        return previousEstimateUncertainty;
    }

    public KalmanFilter setPreviousEstimateUncertainty(double previousEstimateUncertainty) {
        this.previousEstimateUncertainty = previousEstimateUncertainty;
        return this;
    }


    public static double calculateKalmanGain(double estimateUncertainty, double measurementNoiseUncertainty) {
        return estimateUncertainty / (estimateUncertainty + measurementNoiseUncertainty);
    }

    public double calculateStateEstimate(double stateEstimate, double kalmanGain, double measurement) {
        return stateEstimate + kalmanGain * (measurement - stateEstimate);
    }

    public double calculateEstimateUncertainty(double kalmanGain, double estimateUncertainty) {
        return (1 - kalmanGain) * estimateUncertainty;
    }

    public double calculateStateEstimatePrediction(double previousStateEstimate, double controlInput) {
        return previousStateEstimate + controlInput;
    }

    public double calculateEstimateUncertaintyPrediction(double previousEstimateUncertainty, double processNoiseUncertainty) {
        return previousEstimateUncertainty + processNoiseUncertainty;
    }

    public KalmanFilter update() {
        stateEstimate = calculateStateEstimatePrediction(previousStateEstimate, controlInput);
        estimateUncertainty = calculateEstimateUncertaintyPrediction(previousEstimateUncertainty, processNoiseUncertainty);

        kalmanGain = calculateKalmanGain(estimateUncertainty, measurementNoiseUncertainty);
        stateEstimate = calculateStateEstimate(stateEstimate, kalmanGain, measurement);
        estimateUncertainty = calculateEstimateUncertainty(kalmanGain, estimateUncertainty);

        previousStateEstimate = stateEstimate;
        previousEstimateUncertainty = estimateUncertainty;

        return this;
    }

    public KalmanFilter updateSimplified() {
        stateEstimate = calculateStateEstimatePrediction(stateEstimate, controlInput);
        estimateUncertainty = calculateEstimateUncertaintyPrediction(estimateUncertainty, processNoiseUncertainty);

        kalmanGain = calculateKalmanGain(estimateUncertainty, measurementNoiseUncertainty);
        stateEstimate = calculateStateEstimate(stateEstimate, kalmanGain, measurement);
        estimateUncertainty = calculateEstimateUncertainty(kalmanGain, estimateUncertainty);

        return this;
    }
}
