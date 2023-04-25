package org.firstinspires.ftc.teamcode.Systems.Motors;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.ArrayList;
import java.util.Arrays;

public class MotorGroup {
    ArrayList<Motor> motors;

    public MotorGroup(@NonNull Motor... motors) {
        this.motors.addAll(Arrays.asList(motors));
    }

    /**
     * Gets the current motor list starting with the leader.
     *
     * @return the list of motor
     */
    public ArrayList<Motor> getMotors() {
        return motors;
    }

    /**
     * Sets the controlled list of motors.
     *
     * @param motors the DcMotorEx objects
     * @return the updated MotorGroup object
     */
    public MotorGroup setMotors(@NonNull Motor... motors) {
        this.motors.clear();
        this.motors.addAll(Arrays.asList(motors));
        return this;
    }

    /**
     * Gets the current mode of operation for the motors.
     *
     * @return the motor's current mode
     */
    public Motor.Mode getMode() {
        return motors.get(0).getMode();
    }

    /**
     * Sets the mode of operation for the motors.
     *
     * @param mode the mode of operation to set
     * @return the updated MotorGroup object
     */
    public MotorGroup setMode(Motor.Mode mode) {
        for (Motor motor : motors) motor.setMode(mode);
        return this;
    }

    /**
     * Gets the reverse state of a motor at an index.
     *
     * @param motor the index of a motor
     * @return if the motor is reversed or not
     */
    public boolean isReversed(int motor) {
        return motors.get(motor).isReversed();
    }

    /**
     * Sets whether a motor at an index should be reversed or not.
     *
     * @param motor the target motor index
     * @param reversed the new reverse state of the motor
     * @return the updated MotorGroup object
     */
    public MotorGroup setReversed(int motor, boolean reversed) {
        motors.get(motor).setReversed(reversed);
        return this;
    }

    /**
     * Gets the current speed scale for the motor.
     *
     * @return the current speed scale
     */
    public double getSpeedScale() {
        return motors.get(0).getSpeedScale();
    }

    /**
     * Sets the current speed scale for the motor.
     *
     * @param speedScale the new speed scale
     * @return the updated Motor object
     */
    public MotorGroup setSpeedScale(double speedScale) {
        for (Motor motor : motors) motor.setSpeedScale(speedScale);
        return this;
    }

    /**
     * Sets the target power level for the motors.
     *
     * @param power the target power level to set
     * @return the updated MotorGroup object
     */
    public MotorGroup setTargetPower(double power) {
        for (Motor motor : motors) motor.setTargetPower(power);
        return this;
    }

    /**
     * Gets the current position of the motors.
     *
     * @return the lead motor's current position
     */
    public int getCurrentPosition() {
        return motors.get(0).getCurrentPosition();
    }

    /**
     * Gets the target position for the motors.
     *
     * @return the target position of the lead motor
     */
    public int getTargetPosition() {
        return motors.get(0).getTargetPosition();
    }

    /**
     * Sets the target position level for the motors.
     *
     * @param position the target position level to set
     * @return the updated MotorGroup object
     */
    public MotorGroup setTargetPosition(int position) {
        for (Motor motor : motors) motor.setTargetPosition(position);
        return this;
    }

    /**
     * Get the current p, i, d, and f values that control the PIDF function for the motors.
     *
     * @return list containing the p, i, d, and f values of the lead motor
     */
    public ArrayList<Double> getPIDF() {
        return motors.get(0).getPIDF();
    }

    /**
     * Set the PIDF function's p, i, d, and f values for the motors.
     *
     * @param p the new p value
     * @param i the new i value
     * @param d the new d value
     * @param f the new f value
     * @return the updated MotorGroup object
     */
    public MotorGroup setPIDF(double p, double i, double d, double f) {
        for (Motor motor : motors) motor.setPIDF(p, i, d, f);
        return this;
    }

    /**
     * Gets the current PIDF p value.
     *
     * @return the current p value of the motors
     */
    public double getP() {
        return motors.get(0).getP();
    }

    /**
     * Set the p value for the PIDF.
     *
     * @param p the new p value
     * @return the updated MotorGroup object
     */
    public MotorGroup setP(double p) {
        for (Motor motor : motors) motor.setP(p);
        return this;
    }

    /**
     * Gets the current PIDF i value.
     *
     * @return the current i value of the motors
     */
    public double getI() {
        return motors.get(0).getI();
    }

    /**
     * Set the i value for the PIDF.
     *
     * @param i the new i value
     * @return the updated MotorGroup object
     */
    public MotorGroup setI(double i) {
        for (Motor motor : motors) motor.setI(i);
        return this;
    }

    /**
     * Gets the current PIDF d value.
     *
     * @return the current d value of the motors
     */
    public double getD() {
        return motors.get(0).getD();
    }

    /**
     * Set the d value for the PIDF.
     *
     * @param d the new d value
     * @return the updated MotorGroup object
     */
    public MotorGroup setD(double d) {
        for (Motor motor : motors) motor.setD(d);
        return this;
    }

    /**
     * Gets the current PIDF f value.
     *
     * @return the current f value of the motors
     */
    public double getF() {
        return motors.get(0).getF();
    }

    /**
     * Set the f value for the PIDF.
     *
     * @param f the new f value
     * @return the updated MotorGroup object
     */
    public MotorGroup setF(double f) {
        for (Motor motor : motors) motor.setF(f);
        return this;
    }

    /**
     * Get the current encoder ticks per degree value for the motors.
     *
     * @return the ticks per degree of the motors
     */
    public double getTicksPerDegree() {
        return motors.get(0).getTicksPerDegree();
    }

    /**
     * Set the encoder ticks per degree for the motors
     *
     * @param ticksPerDegree the new ticks per degree
     * @return the updated MotorGroup object
     */
    public MotorGroup setTicksPerDegree(double ticksPerDegree) {
        for (Motor motor : motors) motor.setTicksPerDegree(ticksPerDegree);
        return this;
    }

    /**
     * Stop the motor and resets the encoder back to zero for every motor.
     */
    public void resetEncoder() {
        for (Motor motor : motors) motor.resetEncoder();
    }

    /**
     * Update the motor group and sets its power based on its current mode and travel direction of the leader.
     */
    public void update(double input) {
        for (Motor motor : motors) motor.update();
    }

    public MotorGroup log(Telemetry telemetry, HardwareMap hardwareMap) {
        for (Motor motor : motors) {
            motor.log(telemetry, hardwareMap);
            telemetry.addLine();
        }

        return this;
    }
}
