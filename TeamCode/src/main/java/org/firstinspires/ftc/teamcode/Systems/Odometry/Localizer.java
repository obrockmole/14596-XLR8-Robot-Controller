package org.firstinspires.ftc.teamcode.Systems.Odometry;

import org.firstinspires.ftc.teamcode.Systems.Geometry.Pose2d;

public interface Localizer {
    /**
     * Returns the pose estimate of the robot.
     *
     * @return The pose estimate of the robot.
     */
    Pose2d getPoseEstimate();

    /**
     * Returns the X position estimate of the robot.
     *
     * @return The X position estimate of the robot.
     */
    double getXEstimate();

    /**
     * Returns the Y position estimate of the robot.
     *
     * @return The Y position estimate of the robot.
     */
    double getYEstimate();

    /**
     * Returns the heading estimate of the robot.
     *
     * @return The heading estimate of the robot.
     */
    double getHeadingEstimate();

    /**
     * Returns the X velocity of the robot.
     *
     * @return The X velocity of the robot.
     */
    double getForwardVelocity();

    /**
     * Returns the Y velocity of the robot.
     *
     * @return The Y velocity of the robot.
     */
    double getLateralVelocity();

    /**
     * Returns the angular velocity of the robot.
     *
     * @return The angular velocity of the robot.
     */
    double getAngularVelocity();
}
