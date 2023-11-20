package org.firstinspires.ftc.teamcode.CenterStage.Autonomous.Red;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.CenterStage.Autonomous.BaseAuto;

@Autonomous(group = "Red", name = "One Cycle - Red Side Stack")
public class Red_Stack_OneCycle extends BaseAuto {
    public void initTrajectory() {
        sequence = drive.trajectorySequenceBuilder(new Pose2d(-36, -64, Math.toRadians(90)))
                //Place purple pixel
                .lineToSplineHeading(new Pose2d(-36, -36, Math.toRadians(0)))

                //Pick up white pixel from stack (Cycle one)
                .setTangent(Math.toRadians(90))
                .splineToConstantHeading(new Vector2d(-60, -12), Math.toRadians(180))
                .waitSeconds(1)

                //Drive to backboard and place yellow and white pixel (Cycle one)
                .setTangent(Math.toRadians(0))
                .splineToConstantHeading(new Vector2d(-32, -12), Math.toRadians(0))
                .splineTo(new Vector2d(18, -12), Math.toRadians(0))
                .splineToSplineHeading(new Pose2d(40, -26, Math.toRadians(-20)), Math.toRadians(-60))
                .waitSeconds(1)

                //Park
                .lineTo(new Vector2d(44, -12))
                .build();
    }
}
