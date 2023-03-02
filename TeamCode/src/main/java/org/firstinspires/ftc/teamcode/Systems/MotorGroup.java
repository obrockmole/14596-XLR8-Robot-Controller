package org.firstinspires.ftc.teamcode.Systems;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collections;

public class MotorGroup {
    Motor leader;
    ArrayList<Motor> followers;

    public MotorGroup(@NonNull Motor leader, Motor... followers) {
        this.leader = leader;
        this.followers = new ArrayList<>();
        Collections.addAll(this.followers, followers);
    }

    /**
     * Gets the current motor list starting with the leader.
     *
     * @return the list of motor
     */
    public ArrayList<Motor> getMotors() {
        ArrayList<Motor> motors = new ArrayList<>();
        motors.add(leader);
        motors.addAll(followers);
        return motors;
    }

    /**
     * Sets the controlled list of motors starting with the leader and followed by the followers.
     *
     * @param leader the DcMotorEx object that represents the lead motor
     * @param followers list of follower motors
     * @return the updated MotorGroup object
     */
    public MotorGroup setMotors(@NonNull Motor leader, Motor... followers) {
        this.leader = leader;
        this.followers.clear();
        Collections.addAll(this.followers, followers);
        return this;
    }

    /**
     * Gets the current mode of operation for the motors.
     *
     * @return the lead motor's current mode
     */
    public Motor.Mode getMode() {
        return leader.getMode();
    }

    /**
     * Sets the mode of operation for the motors.
     *
     * @param mode the mode of operation to set
     * @return the updated MotorGroup object
     */
    public MotorGroup setMode(Motor.Mode mode) {
        leader.setMode(mode);
        return this;
    }

    /*---------------------------------------Power Motor---------------------------------------*/
    /**
     * Gets the list of power values for the motors.
     *
     * @return the list of power values for the lead motor
     */
    public ArrayList<Double> getPowers() {
        return leader.getPowers();
    }

    /**
     * Sets the list of power values for the motor.
     *
     * @param powers the array of power values to set
     * @return the updated MotorGroup object
     */
    public MotorGroup setPowers(@NonNull double... powers) {
        leader.setPowers(powers);
        return this;
    }

    /**
     * Adds a power value to the list of power values for the lead motor.
     *
     * @param power the power value to add
     * @return the updated MotorGroup object
     */
    public MotorGroup addPower(double power) {
        leader.addPower(power);
        return this;
    }

    /**
     * Gets the current power applied to the motors.
     *
     * @return the lead motor's current power
     */
    public double getPower() {
        return leader.getPower();
    }

    /**
     * Sets the power level of the motors.
     *
     * @param power the new power level
     * @return the updated MotorGroup object
     */
    public MotorGroup setPower(double power) {
        leader.setPower(power);
        for (Motor motor : followers) motor.setPower(power);
        return this;
    }

    /**
     * Gets the target power for the motors.
     *
     * @return the target power of the lead motor
     */
    public double getTargetPower(double power) {
        return leader.getTargetPower(power);
    }

    /**
     * Sets the target power level for the motors.
     * If the specified power is not in the list of available powers this method does nothing.
     *
     * @param power the target power level to set
     * @return the updated MotorGroup object
     */
    public MotorGroup setTargetPower(double power) {
        leader.setTargetPower(power);
        return this;
    }

    /**
     * Gets the index of a desired power.
     *
     * @param power the desired power
     * @return the index of the desired power
     */
    public int getPowerIndex(double power) {
        return leader.getPowerIndex(power);
    }

    /**
     * Gets the index of the target power level in the list of available powers.
     *
     * @return the target power index
     */
    public int getTargetPowerIndex() {
        return leader.getTargetPowerIndex();
    }

    /**
     * Sets the index of the target power level in the list of available powers.
     * If the specified index is above the range for the list of available powers this method does nothing.
     * If the index is -1 the motors speed will be set based on an inputted value.
     *
     * @param index the index of the target power level
     * @return the updated MotorGroup object
     */
    public MotorGroup setTargetPowerIndex(int index) {
        leader.setTargetPowerIndex(index);
        return this;
    }
    /*-----------------------------------------------------------------------------------------*/

    /*--------------------------------------Position Motor-------------------------------------*/
    /**
     * Gets the list of position values for the motors.
     *
     * @return the list of position values
     */
    public ArrayList<Integer> getPositions() {
        return leader.getPositions();
    }

    /**
     * Sets the list of position values for the motors.
     *
     * @param positions the array of position values to set
     * @return the updated MotorGroup object
     */
    public MotorGroup setPositions(@NonNull int... positions) {
        leader.setPositions(positions);
        return this;
    }

    /**
     * Adds a position value to the list of power values for the motors.
     *
     * @param position the position value to add
     * @return the updated MotorGroup object
     */
    public MotorGroup addPosition(int position) {
        leader.addPosition(position);
        return this;
    }

    /**
     * Gets the current position of the motors.
     *
     * @return the lead motor's current position
     */
    public int getCurrentPosition() {
        return leader.getCurrentPosition();
    }

    /**
     * Gets the target position for the motors.
     *
     * @return the target position of the lead motor
     */
    public int getTargetPosition() {
        return leader.getTargetPosition();
    }

    /**
     * Sets the target position level for the motors.
     * If the specified position is not in the list of available positions this method does nothing.
     *
     * @param position the target position level to set
     * @return the updated MotorGroup object
     */
    public MotorGroup setTargetPosition(int position) {
        leader.setTargetPosition(position);
        return this;
    }

    /**
     * Gets the index of a desired position.
     *
     * @param position the desired position
     * @return the index of the desired position
     */
    public int getPositionIndex(int position) {
        return leader.getPositionIndex(position);
    }

    /**
     * Gets the index of the target position level in the list of available positions.
     *
     * @return the target position index
     */
    public int getTargetPositionIndex() {
        return leader.getTargetPositionIndex();
    }

    /**
     * Sets the index of the target position level in the list of available positions.
     * If the specified index is above the range for the list of available positions this method does nothing.
     * If the index is -1 the motors speed will be set based on an inputted value.
     *
     * @param index the index of the target position level
     * @return the updated MotorGroup object
     */
    public MotorGroup setTargetPositionIndex(int index) {
        leader.setTargetPositionIndex(index);
        return this;
    }

    /**
     * Get the current p, i, d, and f values that control the PIDF function for the motors.
     *
     * @return list containing the p, i, d, and f values of the lead motor
     */
    public ArrayList<Double> getPIDF() {
        return leader.getPIDF();
    }

    /**
     * Set the PIDF function's p, i, d, and f values.
     *
     * @param p the new p value
     * @param i the new i value
     * @param d the new d value
     * @param f the new f value
     * @return the updated MotorGroup object
     */
    public MotorGroup setPIDF(double p, double i, double d, double f) {
        leader.setPIDF(p, i, d, f);
        return this;
    }

    /**
     * Gets the current PIDF p value.
     *
     * @return the current p value of the lead motor
     */
    public double getP() {
        return leader.getP();
    }

    /**
     * Set the p value for the PIDF.
     *
     * @param p the new p value
     * @return the updated MotorGroup object
     */
    public MotorGroup setP(double p) {
        leader.setP(p);
        return this;
    }

    /**
     * Gets the current PIDF i value.
     *
     * @return the current i value of the lead motor
     */
    public double getI() {
        return leader.getI();
    }

    /**
     * Set the i value for the PIDF.
     *
     * @param i the new i value
     * @return the updated MotorGroup object
     */
    public MotorGroup setI(double i) {
        leader.setI(i);
        return this;
    }

    /**
     * Gets the current PIDF d value.
     *
     * @return the current d value of the lead motor
     */
    public double getD() {
        return leader.getD();
    }

    /**
     * Set the d value for the PIDF.
     *
     * @param d the new d value
     * @return the updated MotorGroup object
     */
    public MotorGroup setD(double d) {
        leader.setD(d);
        return this;
    }

    /**
     * Gets the current PIDF f value.
     *
     * @return the current f value of the lead motor
     */
    public double getF() {
        return leader.getF();
    }

    /**
     * Set the f value for the PIDF.
     *
     * @param f the new f value
     * @return the updated MotorGroup object
     */
    public MotorGroup setF(double f) {
        leader.setF(f);
        return this;
    }

    /**
     * Get the current encoder ticks per degree value for the motors.
     *
     * @return the ticks per degree of the lead motor
     */
    public double getTicksPerDegree() {
        return leader.getTicksPerDegree();
    }

    /**
     * Set the encoder ticks per degree for the motors
     *
     * @param ticksPerDegree the new ticks per degree
     * @return the updated MotorGroup object
     */
    public MotorGroup setTicksPerDegree(double ticksPerDegree) {
        leader.setTicksPerDegree(ticksPerDegree);
        return this;
    }

    /**
     * Stop the motor and resets the encoder back to zero for every motor.
     */
    public void resetEncoder() {
        leader.resetEncoder();
        for (Motor motor : followers) motor.resetEncoder();
    }
    /*-----------------------------------------------------------------------------------------*/

    /**
     * Update the motor group and sets its power based on its current mode and travel direction of the leader.
     */
    public void update(double input) {
        double power;

        switch (leader.mode) {
            case POWER:
                power = getTargetPower(input) * (leader.isReversed() ? -1 : 1);
                break;

            case POSITION:
                power = leader.calculatePID();
                if (getTargetPositionIndex() == -1) power = input;
                break;

            default:
                power = 0;
                break;
        }

        setPower(power);
    }
}
