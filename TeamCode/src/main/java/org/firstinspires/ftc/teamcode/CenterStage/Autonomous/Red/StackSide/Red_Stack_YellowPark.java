package org.firstinspires.ftc.teamcode.CenterStage.Autonomous.Red.StackSide;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.CenterStage.Autonomous.BaseAuto;

@Disabled
@Autonomous(group = "Red", name = "Yellow & Park - Red Side Stack")
public class Red_Stack_YellowPark extends BaseAuto {
    public void initTrajectory() {
        sequence = drive.trajectorySequenceBuilder(new Pose2d(-36, -64, Math.toRadians(90)))
                //Drive to backboard and place yellow pixel
                .setTangent(Math.toRadians(90))
                .splineToSplineHeading(new Pose2d(-32, -12, Math.toRadians(0)), Math.toRadians(0))
                .splineTo(new Vector2d(18, -12), Math.toRadians(0))
                .splineToSplineHeading(new Pose2d(40, -26, Math.toRadians(-20)), Math.toRadians(-60))
                .waitSeconds(1)

                //Park
                .lineTo(new Vector2d(44, -12))
                .build();
    }
}
