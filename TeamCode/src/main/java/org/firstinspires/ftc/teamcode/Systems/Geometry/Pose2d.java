package org.firstinspires.ftc.teamcode.Systems.Geometry;

public class Pose2d {
    public double x;
    public double y;
    public double heading;

    public Pose2d(double x, double y, double heading) {
        this.x = x;
        this.y = y;
        this.heading = heading;
    }

    public Pose2d() {
        this(0, 0, 0);
    }

    public Pose2d(Pose2d pose) {
        this(pose.x, pose.y, pose.heading);
    }

    public Pose2d add(Pose2d pose) {
        return new Pose2d(this.x + pose.x, this.y + pose.y, this.heading + pose.heading);
    }

    public Pose2d subtract(Pose2d pose) {
        return new Pose2d(this.x - pose.x, this.y - pose.y, this.heading - pose.heading);
    }

    public Pose2d multiply(Pose2d pose) {
        return new Pose2d(this.x * pose.x, this.y * pose.y, this.heading * pose.heading);
    }

    public Pose2d divide(Pose2d pose) {
        return new Pose2d(this.x / pose.x, this.y / pose.y, this.heading / pose.heading);
    }

    public double distance(Pose2d pose) {
        return Math.hypot(pose.x - this.x, pose.y - this.y);
    }

    public String toString() {
        return "(" + x + ", " + y + ", " + heading + ")";
    }
}
