package org.firstinspires.ftc.teamcode.Systems.Movement;

import static org.firstinspires.ftc.teamcode.Systems.Movement.OdometryConstants.YAW_ACCEL;
import static org.firstinspires.ftc.teamcode.Systems.Movement.OdometryConstants.YAW_DEADBAND;
import static org.firstinspires.ftc.teamcode.Systems.Movement.OdometryConstants.YAW_GAIN;
import static org.firstinspires.ftc.teamcode.Systems.Movement.OdometryConstants.YAW_MAX_AUTO;
import static org.firstinspires.ftc.teamcode.Systems.Movement.OdometryConstants.YAW_TOLERANCE;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AngularVelocity;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;
import org.firstinspires.ftc.teamcode.Systems.Drivetrain;
import org.firstinspires.ftc.teamcode.Systems.Movement.OdometryConstants.ProportionalControl;

public class OdometryTurn extends Movement {
    public double turnDegrees;
    public double power;
    public double holdTime;

    private Drivetrain drivetrain;

    private double heading = 0;
    private ElapsedTime holdTimer = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);

    private double rawHeading = 0;
    private double headingOffset = 0;

    private double turnRate = 0;


    private ProportionalControl yawController = new ProportionalControl(YAW_GAIN, YAW_ACCEL, YAW_MAX_AUTO, YAW_TOLERANCE,YAW_DEADBAND, true);

    public OdometryTurn(double turnDegrees, double power, double holdTime) {
        this.turnDegrees = turnDegrees;
        this.power = power;
    }

    public void setDrivetrain(Drivetrain drivetrain) {
        this.drivetrain = drivetrain;
    }

    public void setTurnDegrees(double turnDegrees) {
        this.turnDegrees = turnDegrees;
    }

    public void setPower(double power) {
        this.power = power;
    }

    public void setHoldTime(double holdTime) {
        this.holdTime = holdTime;
    }

    public void readHeading() {
        YawPitchRollAngles orientation = drivetrain.getIMU().getYawPitchRoll();
        AngularVelocity angularVelocity = drivetrain.getIMU().getAngularVelocity(AngleUnit.DEGREES);

        rawHeading = orientation.getYaw(AngleUnit.DEGREES);
        heading = rawHeading - headingOffset;
        turnRate = angularVelocity.zRotationRate;
    }

    public void resetHeading() {
        readHeading();
        headingOffset = rawHeading;
        yawController.reset(0);
        heading = 0;
    }

    public void init() {
        resetHeading();
        yawController.reset(turnDegrees, power);
        holdTimer.reset();
    }
    public void stop() {
        drivetrain.standardDrive(0, 0, 0);
    }

    public boolean move() {
        readHeading();

        drivetrain.standardDrive(0, 0, yawController.getOutput(heading));

        if (yawController.inPosition()) {
            return holdTimer.time() > holdTime;
        } else {
            holdTimer.reset();
        }
        return false;
    }
}
