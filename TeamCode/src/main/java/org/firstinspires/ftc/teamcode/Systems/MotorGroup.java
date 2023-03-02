package org.firstinspires.ftc.teamcode.Systems;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MotorGroup {
    Motor leader;
    ArrayList<Motor> followers;

    public MotorGroup(@NonNull Motor leader, Motor... followers) {
        this.leader = leader;
        this.followers = new ArrayList<>();
        Collections.addAll(this.followers, followers);
    }

    public ArrayList<Motor> getMotors() {
        ArrayList<Motor> motors = new ArrayList<>();
        motors.add(leader);
        motors.addAll(followers);
        return motors;
    }

    public MotorGroup setMotors(@NonNull Motor leader, Motor... followers) {
        this.leader = leader;
        this.followers.clear();
        Collections.addAll(this.followers, followers);
        return this;
    }

    public Motor.Mode getMode() {
        return leader.getMode();
    }

    public MotorGroup setMode(Motor.Mode mode) {
        leader.setMode(mode);
        return this;
    }

    /*---------------------------------------Power Motor---------------------------------------*/
    public ArrayList<Double> getPowers() {
        return leader.getPowers();
    }

    public MotorGroup setPowers(@NonNull double... powers) {
        leader.setPowers(powers);
        return this;
    }

    public MotorGroup addPower(double power) {
        leader.addPower(power);
        return this;
    }

    public double getPower() {
        return leader.getPower();
    }

    public MotorGroup setPower(double power) {
        leader.setPower(power);
        for (Motor motor : followers) motor.setPower(power);
        return this;
    }

    public double getTargetPower() {
        return leader.getTargetPower();
    }

    public MotorGroup setTargetPower(double power) {
        if (!leader.getPowers().contains(power)) return this;

        leader.setTargetPower(power);
        return this;
    }

    public int getTargetPowerIndex() {
        return leader.getTargetPowerIndex();
    }

    public MotorGroup setTargetPowerIndex(int index) {
        if (index < 0|| index > leader.getPowers().size()) return this;

        leader.setTargetPowerIndex(index);
        return this;
    }
    /*-----------------------------------------------------------------------------------------*/

    /*--------------------------------------Position Motor-------------------------------------*/
    public ArrayList<Integer> getPositions() {
        return leader.getPositions();
    }

    public MotorGroup setPositions(@NonNull int... positions) {
        leader.setPositions(positions);
        return this;
    }

    public MotorGroup addPosition(int position) {
        leader.addPosition(position);
        return this;
    }

    public int getCurrentPosition() {
        return leader.getCurrentPosition();
    }

    public int getTargetPosition() {
        return leader.getTargetPosition();
    }

    public MotorGroup setTargetPosition(int position) {
        if (!leader.getPositions().contains(position)) return this;

        leader.setTargetPosition(position);
        return this;
    }

    public int getTargetPositionIndex() {
        return leader.getTargetPositionIndex();
    }

    public MotorGroup setTargetPositionIndex(int index) {
        if (index < 0|| index > leader.getPositions().size()) return this;

        leader.setTargetPositionIndex(index);
        return this;
    }

    public ArrayList<Double> getPIDF() {
        return leader.getPIDF();
    }

    public MotorGroup setPIDF(double p, double i, double d, double f) {
        leader.setPIDF(p, i, d, f);
        return this;
    }

    public double getP() {
        return leader.getP();
    }

    public MotorGroup setP(double p) {
        leader.setP(p);
        return this;
    }

    public double getI() {
        return leader.getI();
    }

    public MotorGroup setI(double i) {
        leader.setI(i);
        return this;
    }

    public double getD() {
        return leader.getD();
    }

    public MotorGroup setD(double d) {
        leader.setD(d);
        return this;
    }

    public double getF() {
        return leader.getF();
    }

    public MotorGroup setF(double f) {
        leader.setF(f);
        return this;
    }

    public double getTicksPerDegree() {
        return leader.getTicksPerDegree();
    }

    public MotorGroup setTicksPerDegree(double ticksPerDegree) {
        leader.setTicksPerDegree(ticksPerDegree);
        return this;
    }

    public void resetEncoder() {
        leader.resetEncoder();
    }
    /*-----------------------------------------------------------------------------------------*/

    public void update() {
        double power;

        switch (leader.mode) {
            case POWER:
                power = getTargetPower();
                break;

            case POSITION:
                power = leader.calculatePID();
                break;

            default:
                power = 0;
                break;
        }

        setPower(power * (leader.isReversed() ? -1 : 1));
    }
}
