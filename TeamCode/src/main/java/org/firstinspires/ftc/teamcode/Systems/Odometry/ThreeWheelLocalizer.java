package org.firstinspires.ftc.teamcode.Systems.Odometry;

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.teamcode.Systems.Geometry.Pose2d;
import org.firstinspires.ftc.teamcode.Systems.Movement.OdometryConstants;
import org.firstinspires.ftc.teamcode.Systems.Sensors.Encoder;

@Config
public class ThreeWheelLocalizer implements Localizer {
    private Encoder leftEncoder, rightEncoder, perpEncoder;

    public static double LATERAL_DISTANCE = 10; //Distance between parallel wheels in Inches
    public static double FORWARD_OFFSET = -2; //Center of robot to perpendicular wheel in Inches


    private Pose2d currentPosition = new Pose2d(0, 0, 0);

    private int previousLeftPosition = 0;
    private int previousRightPosition = 0;
    private int previousPerpPosition = 0;

    private double forwardVelocity = 0;
    private double lateralVelocity = 0;
    private double angularVelocity = 0;

    private long lastUpdateTime = System.nanoTime();

    public ThreeWheelLocalizer(Encoder leftEncoder, Encoder rightEncoder, Encoder perpEncoder) {
        this.leftEncoder = leftEncoder;
        this.rightEncoder = rightEncoder;
        this.perpEncoder = perpEncoder;
    }

    public ThreeWheelLocalizer(Odometry odometry) {
        this.leftEncoder = odometry.getEncoder(0);
        this.rightEncoder = odometry.getEncoder(1);
        this.perpEncoder = odometry.getEncoder(2);
    }

    public void update() {
        long currentTime = System.nanoTime();
        double deltaTime = (currentTime - lastUpdateTime) / 1e9; // Convert nanoseconds to seconds
        lastUpdateTime = currentTime;

        int leftPosition = leftEncoder.getCurrentPosition();
        int rightPosition = rightEncoder.getCurrentPosition();
        int perpPosition = perpEncoder.getCurrentPosition();

        int leftDelta = leftPosition - previousLeftPosition;
        int rightDelta = rightPosition - previousRightPosition;
        int perpDelta = perpPosition - previousPerpPosition;

        double leftDistance = ticksToInches(leftDelta);
        double rightDistance = ticksToInches(rightDelta);
        double perpDistance = ticksToInches(perpDelta);

        double deltaTheta = (rightDistance - leftDistance) / LATERAL_DISTANCE;
        currentPosition.heading += deltaTheta;
        double deltaCenter = (leftDistance + rightDistance) / 2;
        double deltaBack = perpDistance - (FORWARD_OFFSET * deltaTheta);

        double deltaX = deltaCenter * Math.cos(currentPosition.heading) - deltaBack * Math.sin(currentPosition.heading);
        double deltaY = deltaCenter * Math.sin(currentPosition.heading) + deltaBack * Math.cos(currentPosition.heading);

        currentPosition.x += deltaX;
        currentPosition.y += deltaY;

        forwardVelocity = deltaX / deltaTime;
        lateralVelocity = deltaY / deltaTime;
        angularVelocity = deltaTheta / deltaTime;

        previousLeftPosition = leftPosition;
        previousRightPosition = rightPosition;
        previousPerpPosition = perpPosition;
    }

    public double ticksToInches(int ticks) {
        return ticks * OdometryConstants.INCHES_PER_TICK;
    }

    public Pose2d getPoseEstimate() {
        return currentPosition;
    }

    public double getXEstimate() {
        return currentPosition.x;
    }

    public double getYEstimate() {
        return currentPosition.y;
    }

    public double getHeadingEstimate() {
        return currentPosition.heading;
    }

    public double getForwardVelocity() {
        return forwardVelocity;
    }

    public double getLateralVelocity() {
        return lateralVelocity;
    }

    public double getAngularVelocity() {
        return angularVelocity;
    }
}
