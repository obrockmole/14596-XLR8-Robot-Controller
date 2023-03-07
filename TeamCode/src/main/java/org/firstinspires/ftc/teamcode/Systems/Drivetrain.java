package org.firstinspires.ftc.teamcode.Systems;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.ArrayList;
import java.util.Arrays;

public class Drivetrain {
    private SinglePowerMotor frontLeft, backLeft, frontRight, backRight;

    int speedScale = 1;

    public Drivetrain() {}

    public Drivetrain(SinglePowerMotor frontLeft, SinglePowerMotor backLeft, SinglePowerMotor frontRight, SinglePowerMotor backRight) {
        this.frontLeft = frontLeft;
        this.backLeft = backLeft;
        this.frontRight = frontRight;
        this.backRight = backRight;
    }

    public ArrayList<SinglePowerMotor> getMotors() {
        return (ArrayList<SinglePowerMotor>) Arrays.asList(frontLeft, backLeft, frontRight, backRight);
    }

    public Drivetrain setMotors(SinglePowerMotor frontLeft, SinglePowerMotor backLeft, SinglePowerMotor frontRight, SinglePowerMotor backRight) {
        this.frontLeft = frontLeft;
        this.backLeft = backLeft;
        this.frontRight = frontRight;
        this.backRight = backRight;
        return this;
    }

    public int getSpeedScale() {
        return speedScale;
    }

    public void setSpeedScale(int speedScale) {
        this.speedScale = speedScale;
    }

    public void setInput(double forward, double rightward, double rotational) {
        double flPower =  forward - rightward - rotational;
        double blPower =  forward + rightward - rotational;
        double frPower =  forward + rightward + rotational;
        double brPower =  forward - rightward + rotational;

        frontLeft.setTargetPower(flPower * speedScale);
        backLeft.setTargetPower(blPower * speedScale);
        frontRight.setTargetPower(frPower * speedScale);
        backRight.setTargetPower(brPower * speedScale);
    }

    public void log(Telemetry telemetry) {
        telemetry.addLine("-----Motor Powers-----");
        telemetry.addData("Front Left Power: ", frontLeft.getPower());
        telemetry.addData("Back Left Power: ", backLeft.getPower());
        telemetry.addData("Front Right Power: ", frontRight.getPower());
        telemetry.addData("Back Right Power: ", backRight.getPower());

        telemetry.addLine();
        telemetry.addLine("-----Current Motor Positions-----");
        telemetry.addData("Front Left Position: ", frontLeft.getMotor().getCurrentPosition());
        telemetry.addData("Back Left Position: ", backLeft.getMotor().getCurrentPosition());
        telemetry.addData("Front Right Position: ", frontRight.getMotor().getCurrentPosition());
        telemetry.addData("Back Right Position: ", backRight.getMotor().getCurrentPosition());
    }

    public void update() {
        frontLeft.update();
        backLeft.update();
        frontRight.update();
        backRight.update();
    }
}
