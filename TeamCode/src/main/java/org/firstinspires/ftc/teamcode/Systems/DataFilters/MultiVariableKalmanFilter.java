package org.firstinspires.ftc.teamcode.Systems.DataFilters;

import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;

public class MultiVariableKalmanFilter {
    public double[][] measurements;
    public double[][] stateEstimates;
    public double[][] estimateUncertainty;
    public double[][] kalmanGain;
    public double[][] controlInput;

    public double[][] processNoiseUncertainty;
    public double[][] measurementNoiseUncertainty;

    public double[][] modelMatrix;
    public double[][] controlMatrix;
    public double[][] sensorMatrix;
    public double[][] identityMatrix;

    public double[][] previousStateEstimates;
    public double[][] previousEstimateUncertainty;

    public MultiVariableKalmanFilter() {
        this(new double[2][1], new double[2][1], new double[2][2], new double[2][2], new double[2][1], new double[2][2], new double[2][2], new double[2][2], new double[2][2], new double[2][2], new double[2][2]);
    }

    public MultiVariableKalmanFilter(double[][] measurements, double[][] stateEstimates, double[][] estimateUncertainty, double[][] kalmanGain, double[][] controlInput, double[][] processNoiseUncertainty, double[][] measurementNoiseUncertainty, double[][] modelMatrix, double[][] controlMatrix, double[][] sensorMatrix, double[][] identityMatrix) {
        this.measurements = measurements;
        this.stateEstimates = stateEstimates;
        this.estimateUncertainty = estimateUncertainty;
        this.kalmanGain = kalmanGain;
        this.controlInput = controlInput;

        this.processNoiseUncertainty = processNoiseUncertainty;
        this.measurementNoiseUncertainty = measurementNoiseUncertainty;

        this.modelMatrix = modelMatrix;
        this.controlMatrix = controlMatrix;
        this.sensorMatrix = sensorMatrix;
        this.identityMatrix = identityMatrix;

        this.previousStateEstimates = stateEstimates;
        this.previousEstimateUncertainty = estimateUncertainty;
    }

    public double[][] getMeasurements() {
        return measurements;
    }

    public MultiVariableKalmanFilter setMeasurements(double[][] measurements) {
        this.measurements = measurements;
        return this;
    }

    public double[][] getStateEstimates() {
        return stateEstimates;
    }

    public MultiVariableKalmanFilter setStateEstimates(double[][] stateEstimates) {
        this.stateEstimates = stateEstimates;
        return this;
    }

    public double[][] getEstimateUncertainty() {
        return estimateUncertainty;
    }

    public MultiVariableKalmanFilter setEstimateUncertainty(double[][] estimateUncertainty) {
        this.estimateUncertainty = estimateUncertainty;
        return this;
    }

    public double[][] getKalmanGain() {
        return kalmanGain;
    }

    public MultiVariableKalmanFilter setKalmanGain(double[][] kalmanGain) {
        this.kalmanGain = kalmanGain;
        return this;
    }

    public double[][] getControlInput() {
        return controlInput;
    }

    public MultiVariableKalmanFilter setControlInput(double[][] controlInput) {
        this.controlInput = controlInput;
        return this;
    }

    public double[][] getProcessNoiseUncertainty() {
        return processNoiseUncertainty;
    }

    public MultiVariableKalmanFilter setProcessNoiseUncertainty(double[][] processNoiseUncertainty) {
        this.processNoiseUncertainty = processNoiseUncertainty;
        return this;
    }

    public double[][] getMeasurementNoiseUncertainty() {
        return measurementNoiseUncertainty;
    }

    public MultiVariableKalmanFilter setMeasurementNoiseUncertainty(double[][] measurementNoiseUncertainty) {
        this.measurementNoiseUncertainty = measurementNoiseUncertainty;
        return this;
    }

    public double[][] getModelMatrix() {
        return modelMatrix;
    }

    public MultiVariableKalmanFilter setModelMatrix(double[][] modelMatrix) {
        this.modelMatrix = modelMatrix;
        return this;
    }

    public double[][] getControlMatrix() {
        return controlMatrix;
    }

    public MultiVariableKalmanFilter setControlMatrix(double[][] controlMatrix) {
        this.controlMatrix = controlMatrix;
        return this;
    }

    public double[][] getSensorMatrix() {
        return sensorMatrix;
    }

    public MultiVariableKalmanFilter setSensorMatrix(double[][] sensorMatrix) {
        this.sensorMatrix = sensorMatrix;
        return this;
    }

    public double[][] getIdentityMatrix() {
        return identityMatrix;
    }

    public MultiVariableKalmanFilter setIdentityMatrix(double[][] identityMatrix) {
        this.identityMatrix = identityMatrix;
        return this;
    }

    public double[][] getPreviousStateEstimates() {
        return previousStateEstimates;
    }

    public MultiVariableKalmanFilter setPreviousStateEstimates(double[][] previousStateEstimates) {
        this.previousStateEstimates = previousStateEstimates;
        return this;
    }

    public double[][] getPreviousEstimateUncertainty() {
        return previousEstimateUncertainty;
    }

    public MultiVariableKalmanFilter setPreviousEstimateUncertainty(double[][] previousEstimateUncertainty) {
        this.previousEstimateUncertainty = previousEstimateUncertainty;
        return this;
    }


    private double[][] matrixAddition(double[][] matrix1, double[][] matrix2) {
        RealMatrix realMatrix1 = MatrixUtils.createRealMatrix(matrix1);
        RealMatrix realMatrix2 = MatrixUtils.createRealMatrix(matrix2);

        return realMatrix1.add(realMatrix2).getData();
    }

    private double[][] matrixSubtraction(double[][] matrix1, double[][] matrix2) {
        RealMatrix realMatrix1 = MatrixUtils.createRealMatrix(matrix1);
        RealMatrix realMatrix2 = MatrixUtils.createRealMatrix(matrix2);

        return realMatrix1.subtract(realMatrix2).getData();
    }

    private double[][] matrixMultiplication(double[][] matrix1, double[][] matrix2) {
        RealMatrix realMatrix1 = MatrixUtils.createRealMatrix(matrix1);
        RealMatrix realMatrix2 = MatrixUtils.createRealMatrix(matrix2);

        return realMatrix1.multiply(realMatrix2).getData();
    }

    private double[][] matrixDivision(double[][] matrix1, double[][] matrix2) {
        RealMatrix realMatrix1 = MatrixUtils.createRealMatrix(matrix1);
        RealMatrix realMatrix2 = MatrixUtils.createRealMatrix(matrix2);

        return realMatrix1.multiply(MatrixUtils.inverse(realMatrix2)).getData();
    }

    private double[][] transposeMatrix(double[][] matrix) {
        RealMatrix realMatrix = MatrixUtils.createRealMatrix(matrix);

        return realMatrix.transpose().getData();
    }

    private double[][] inverseMatrix(double[][] matrix) {
        RealMatrix realMatrix = MatrixUtils.createRealMatrix(matrix);

        return MatrixUtils.inverse(realMatrix).getData();
    }

    public double[][] calculateKalmanGain(double[][] estimateUncertainty, double[][] measurementNoiseUncertainty) {
        return matrixDivision(estimateUncertainty, matrixAddition(estimateUncertainty, measurementNoiseUncertainty));
    }

    public void update() {
        double[][] priorIStateEstimate = matrixAddition(matrixMultiplication(modelMatrix, stateEstimates), matrixMultiplication(controlMatrix, controlInput));

        double[][] priorIEstimateUncertainty = matrixAddition(matrixMultiplication(matrixMultiplication(modelMatrix, estimateUncertainty), transposeMatrix(modelMatrix)), processNoiseUncertainty);

        for (double[] measurement : measurements) {
            double[][] currentIMeasurement = {{measurement[0]}};
            double[][] currentIKalmanGain = matrixMultiplication(matrixMultiplication(priorIEstimateUncertainty, transposeMatrix(sensorMatrix)), inverseMatrix(matrixAddition(matrixMultiplication(matrixMultiplication(sensorMatrix, priorIEstimateUncertainty), transposeMatrix(sensorMatrix)), measurementNoiseUncertainty)));

            double[][] difference = matrixSubtraction(currentIMeasurement, matrixMultiplication(sensorMatrix, priorIStateEstimate));

            double[][] currentIStateEstimate = matrixAddition(priorIStateEstimate, matrixMultiplication(currentIKalmanGain, difference));
            double[][] currentIEstimateUncertainty = matrixMultiplication(matrixSubtraction(identityMatrix, matrixMultiplication(currentIKalmanGain, sensorMatrix)), priorIEstimateUncertainty);

            priorIStateEstimate = currentIStateEstimate;
            priorIEstimateUncertainty = currentIEstimateUncertainty;
        }

        setStateEstimates(priorIStateEstimate);
        setEstimateUncertainty(priorIEstimateUncertainty);

        setPreviousStateEstimates(stateEstimates);
        setPreviousEstimateUncertainty(estimateUncertainty);
    }
}
