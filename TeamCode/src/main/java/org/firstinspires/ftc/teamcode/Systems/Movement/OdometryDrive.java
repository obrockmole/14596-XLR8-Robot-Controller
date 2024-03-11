package org.firstinspires.ftc.teamcode.Systems.Movement;

import static org.firstinspires.ftc.teamcode.Systems.Movement.OdometryConstants.DRIVE_ACCEL;
import static org.firstinspires.ftc.teamcode.Systems.Movement.OdometryConstants.DRIVE_DEADBAND;
import static org.firstinspires.ftc.teamcode.Systems.Movement.OdometryConstants.DRIVE_GAIN;
import static org.firstinspires.ftc.teamcode.Systems.Movement.OdometryConstants.DRIVE_MAX_AUTO;
import static org.firstinspires.ftc.teamcode.Systems.Movement.OdometryConstants.DRIVE_TOLERANCE;
import static org.firstinspires.ftc.teamcode.Systems.Movement.OdometryConstants.ODOM_INCHES_PER_COUNT;
import static org.firstinspires.ftc.teamcode.Systems.Movement.OdometryConstants.STRAFE_ACCEL;
import static org.firstinspires.ftc.teamcode.Systems.Movement.OdometryConstants.STRAFE_DEADBAND;
import static org.firstinspires.ftc.teamcode.Systems.Movement.OdometryConstants.STRAFE_GAIN;
import static org.firstinspires.ftc.teamcode.Systems.Movement.OdometryConstants.STRAFE_MAX_AUTO;
import static org.firstinspires.ftc.teamcode.Systems.Movement.OdometryConstants.STRAFE_TOLERANCE;
import static org.firstinspires.ftc.teamcode.Systems.Movement.OdometryConstants.YAW_ACCEL;
import static org.firstinspires.ftc.teamcode.Systems.Movement.OdometryConstants.YAW_DEADBAND;
import static org.firstinspires.ftc.teamcode.Systems.Movement.OdometryConstants.YAW_GAIN;
import static org.firstinspires.ftc.teamcode.Systems.Movement.OdometryConstants.YAW_MAX_AUTO;
import static org.firstinspires.ftc.teamcode.Systems.Movement.OdometryConstants.YAW_TOLERANCE;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AngularVelocity;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;
import org.firstinspires.ftc.teamcode.Systems.Drivetrain;
import org.firstinspires.ftc.teamcode.Systems.Movement.OdometryConstants.ProportionalControl;

public class OdometryDrive extends Movement {
    private double driveDistance;
    private double strafeDistance;
    private double power;
    private double holdTime;

    private Drivetrain drivetrain;

    private double distanceDriven = 0;
    private double distanceStrafed = 0;
    private double heading = 0;
    private ElapsedTime holdTimer = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);

    private int rawDriveOdometer = 0;
    private int driveOdometerOffset = 0;
    private int rawStrafeOdometer = 0;
    private int strafeOdometerOffset = 0;
    private double rawHeading = 0;
    private double headingOffset = 0;

    private double turnRate = 0;


    private ProportionalControl driveController = new ProportionalControl(DRIVE_GAIN, DRIVE_ACCEL, DRIVE_MAX_AUTO, DRIVE_TOLERANCE, DRIVE_DEADBAND, false);
    private ProportionalControl strafeController = new ProportionalControl(STRAFE_GAIN, STRAFE_ACCEL, STRAFE_MAX_AUTO, STRAFE_TOLERANCE, STRAFE_DEADBAND, false);
    private ProportionalControl yawController = new ProportionalControl(YAW_GAIN, YAW_ACCEL, YAW_MAX_AUTO, YAW_TOLERANCE,YAW_DEADBAND, true);

    public OdometryDrive(double driveDistance, double strafeDistance, double power, double holdTime) {
        this.driveDistance = driveDistance;
        this.strafeDistance = strafeDistance;
        this.power = power;
        this.holdTime = holdTime;
    }

    public void setDrivetrain(Drivetrain drivetrain) {
        this.drivetrain = drivetrain;
    }

    public void setDriveDistance(double driveDistance) {
        this.driveDistance = driveDistance;
    }

    public void setStrafeDistance(double strafeDistance) {
        this.strafeDistance = strafeDistance;
    }

    public void setPower(double power) {
        this.power = power;
    }

    public void setHoldTime(double holdTime) {
        this.holdTime = holdTime;
    }

    public void readOdometry() {
        rawDriveOdometer = drivetrain.getOdometry().getEncoder(0).getCurrentPosition();
        rawStrafeOdometer =  drivetrain.getOdometry().getEncoder(2).getCurrentPosition();
        distanceDriven = (rawDriveOdometer - driveOdometerOffset) * ODOM_INCHES_PER_COUNT;
        distanceStrafed = (rawStrafeOdometer - strafeOdometerOffset) * ODOM_INCHES_PER_COUNT;
    }

    public void resetOdometry() {
        readOdometry();
        driveOdometerOffset = rawDriveOdometer;
        distanceDriven = 0.0;
        driveController.reset(0);

        strafeOdometerOffset = rawStrafeOdometer;
        distanceStrafed = 0.0;
        strafeController.reset(0);
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
        resetOdometry();
        resetHeading();

        driveController.reset(driveDistance, power);
        strafeController.reset(strafeDistance, power);
        yawController.reset();
        holdTimer.reset();
    }

    public void stop() {
        drivetrain.standardDrive(0, 0, 0);
    }

    public boolean move() {
        readOdometry();
        readHeading();

        drivetrain.standardDrive(driveController.getOutput(distanceDriven), -strafeController.getOutput(distanceStrafed), yawController.getOutput(heading));

        if (driveController.inPosition() && strafeController.inPosition() && yawController.inPosition()) {
            return holdTimer.time() > holdTime;
        } else {
            holdTimer.reset();
        }
        return false;
    }

    public void log(Telemetry telemetry) {
        telemetry.addLine("-----Odometry Drive-----");
        telemetry.addData("Drive Distance", driveDistance);
        telemetry.addData("Strafe Distance", strafeDistance);
        telemetry.addData("Hold Time", holdTime);
        telemetry.addLine("------------------------");
        telemetry.addData("Distance Driven", distanceDriven);
        telemetry.addData("Distance Strafed", distanceStrafed);
        telemetry.addData("Heading", heading);
        telemetry.addLine("------------------------");
        telemetry.addData("Drive Controller Output", driveController.getOutput(distanceDriven));
        telemetry.addData("Drive Controller At Position", driveController.inPosition());
        telemetry.addData("Strafe Controller Output", strafeController.getOutput(distanceStrafed));
        telemetry.addData("Strafe Controller At Position", strafeController.inPosition());
        telemetry.addData("Yaw Controller Output", yawController.getOutput(heading));
        telemetry.addData("Yaw Controller At Position", yawController.inPosition());
        telemetry.addLine();
    }
}
