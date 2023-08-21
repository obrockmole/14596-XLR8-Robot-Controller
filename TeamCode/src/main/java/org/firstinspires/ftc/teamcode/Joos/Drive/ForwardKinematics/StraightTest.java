package org.firstinspires.ftc.teamcode.Joos.Drive.ForwardKinematics;

import com.amarcolini.joos.command.CommandOpMode;
import com.amarcolini.joos.dashboard.JoosConfig;
import com.amarcolini.joos.followers.TrajectoryFollower;
import com.amarcolini.joos.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Joos.SampleBot;

@JoosConfig
@Autonomous(group = "Joos")
public class StraightTest extends CommandOpMode {
    public static double DISTANCE = 50;
    private SampleBot robot;

    @Override
    public void preInit() {
        robot = registerRobot(new SampleBot());
        robot.drive.setPoseEstimate(new Pose2d());

        schedule(robot.drive.followTrajectory(
                robot.drive.trajectoryBuilder(new Pose2d())
                        .forward(DISTANCE)
                        .build()
        ).onExecute(() -> {
            TrajectoryFollower follower = robot.drive.getTrajectoryFollower();
            telemetry.addData("targetVel", follower.getTrajectory().velocity(follower.elapsedTime()));
        }).then(() -> {
            Pose2d poseEstimate = robot.drive.getPoseEstimate();
            telemetry.setAutoClear(false);
            telemetry.addData("finalX", poseEstimate.x);
            telemetry.addData("finalY", poseEstimate.y);
            telemetry.addData("finalHeading", poseEstimate.heading);
        }));

        telemetry.addLine("Ready!");
    }
}
