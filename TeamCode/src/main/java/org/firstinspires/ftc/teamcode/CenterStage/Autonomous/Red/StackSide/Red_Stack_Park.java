package org.firstinspires.ftc.teamcode.CenterStage.Autonomous.Red.StackSide;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.CenterStage.Autonomous.BaseAuto;

@Autonomous(group = "Red", name = "Park - Red Side Stack")
public class Red_Stack_Park extends BaseAuto {
    public void initTrajectory() {
        sequence = drive.trajectorySequenceBuilder(new Pose2d(-36, -64, Math.toRadians(90)))
                //Park
                .setTangent(Math.toRadians(90))
                .splineToConstantHeading(new Vector2d(-32, -12), Math.toRadians(0))
                .splineTo(new Vector2d(44, -12), Math.toRadians(0))
                .build();
    }
}
