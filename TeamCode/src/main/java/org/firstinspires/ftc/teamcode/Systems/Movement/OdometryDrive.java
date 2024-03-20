package org.firstinspires.ftc.teamcode.Systems.Movement;

import static org.firstinspires.ftc.teamcode.Systems.Movement.OdometryConstants.*;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Systems.Drivetrain;
import org.firstinspires.ftc.teamcode.Systems.Movement.OdometryConstants.ProportionalControl;
import org.firstinspires.ftc.teamcode.Systems.Odometry.Localizer;

public class OdometryDrive extends Movement {
    private Drivetrain drivetrain;
    private Localizer localizer;

    private double driveDistance;
    private double strafeDistance;
    public double turnDegrees;
    private double holdTime;
    private double timeoutTime;

    private double distanceDriven = 0;
    private double distanceStrafed = 0;
    private double heading = 0;

    private double rawDriveDistance = 0;
    private double driveDistanceOffset = 0;
    private double rawStrafeDistance = 0;
    private double strafeDistanceOffset = 0;
    private double rawHeading = 0;
    private double headingOffset = 0;

    private final ElapsedTime holdTimer = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);
    private final ElapsedTime timeoutTimer = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);

    private final ProportionalControl driveController = new ProportionalControl(DRIVE_GAIN, DRIVE_ACCEL, 0, DRIVE_TOLERANCE);
    private final ProportionalControl strafeController = new ProportionalControl(STRAFE_GAIN, STRAFE_ACCEL, 0, STRAFE_TOLERANCE);
    private final ProportionalControl yawController = new ProportionalControl(YAW_GAIN, YAW_ACCEL, 0, YAW_TOLERANCE);

    public OdometryDrive(double driveDistance, double strafeDistance, double turnDegrees, double outputLimit, double holdTime, double timeoutTime) {
        this.driveDistance = driveDistance;
        this.strafeDistance = strafeDistance;
        this.turnDegrees = turnDegrees;
        this.holdTime = holdTime;
        this.timeoutTime = timeoutTime;

        setOutputLimit(outputLimit);
    }

    public void setDrivetrain(Drivetrain drivetrain) {
        this.drivetrain = drivetrain;
    }

    public void setLocalizer(Localizer localizer) {
        this.localizer = localizer;
    }

    public void setDriveDistance(double driveDistance) {
        this.driveDistance = driveDistance;
    }

    public void setStrafeDistance(double strafeDistance) {
        this.strafeDistance = strafeDistance;
    }

    public void setTurnDegrees(double turnDegrees) {
        this.turnDegrees = turnDegrees;
    }

    public void setOutputLimit(double outputLimit) {
        driveController.setOutputLimit(outputLimit);
        strafeController.setOutputLimit(outputLimit);
        yawController.setOutputLimit(outputLimit);
    }

    public void setHoldTime(double holdTime) {
        this.holdTime = holdTime;
    }

    public void setTimeoutTime(double timeoutTime) {
        this.timeoutTime = timeoutTime;
    }

    public void readOdometry() {
        rawDriveDistance = localizer.getXEstimate();
        rawStrafeDistance = localizer.getYEstimate();
        rawHeading = Math.toDegrees(localizer.getHeadingEstimate());

        distanceDriven = rawDriveDistance - driveDistanceOffset;
        distanceStrafed = rawStrafeDistance - strafeDistanceOffset;
        heading = rawHeading - headingOffset;
    }

    public void resetOdometry() {
        readOdometry();

        driveDistanceOffset = rawDriveDistance;
        distanceDriven = 0.0;
        driveController.reset(0);

        strafeDistanceOffset = rawStrafeDistance;
        distanceStrafed = 0.0;
        strafeController.reset(0);

        headingOffset = rawHeading;
        yawController.reset(0);
        heading = 0;
    }

    public void init() {
        resetOdometry();

        driveController.reset(driveDistance);
        strafeController.reset(strafeDistance);
        yawController.reset(turnDegrees);
        holdTimer.reset();
        timeoutTimer.reset();
    }

    public void stop() {
        drivetrain.fieldCentricDrive(0, 0, 0);
    }

    public boolean move() {
        readOdometry();

        drivetrain.fieldCentricDrive(driveController.getOutput(distanceDriven), strafeController.getOutput(distanceStrafed), yawController.getOutput(heading));

        if (driveController.inPosition() && strafeController.inPosition() && yawController.inPosition())
            return holdTimer.time() > holdTime;
        else
            holdTimer.reset();

        return timeoutTimer.time() > timeoutTime;
    }

    public void log(Telemetry telemetry) {
        telemetry.addLine("-----Odometry Drive-----");
        telemetry.addData("Drive Distance", driveDistance);
        telemetry.addData("Strafe Distance", strafeDistance);
        telemetry.addData("Turn Degrees", turnDegrees);
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
