package org.firstinspires.ftc.teamcode.CenterStage.Autonomous;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "Testing Auto with BaseAuto")
public class AutoTest2 extends BaseAuto {
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
                .splineTo(new Vector2d(24, -12), Math.toRadians(0))
                .splineToLinearHeading(new Pose2d(40, -32, Math.toRadians(0)), Math.toRadians(-90))
                .waitSeconds(1)

                //Drive to stack and pick up two white pixels (Cycle two)
                /*.setTangent(Math.toRadians(90))
                .splineToLinearHeading(new Pose2d(24, -12, Math.toRadians(0)), Math.toRadians(180))
                .lineTo(new Vector2d(-60, -12))
                .waitSeconds(1)

                //Drive to backboard and place two white pixels (Cycle two)
                .lineTo(new Vector2d(24, -12))
                .splineToLinearHeading(new Pose2d(40, -32, Math.toRadians(0)), Math.toRadians(-90))
                .waitSeconds(1)

                //Drive to stack and pick up two white pixels (Cycle three)
                .setTangent(Math.toRadians(90))
                .splineToLinearHeading(new Pose2d(24, -12, Math.toRadians(0)), Math.toRadians(180))
                .lineTo(new Vector2d(-60, -12))
                .waitSeconds(1)

                //Drive to backboard and place two white pixels (Cycle three)
                .lineTo(new Vector2d(24, -12))
                .splineToLinearHeading(new Pose2d(40, -32, Math.toRadians(0)), Math.toRadians(-90))
                .waitSeconds(1)*/

                //Park
                .lineTo(new Vector2d(44, -12))
                .build();
    }
}
