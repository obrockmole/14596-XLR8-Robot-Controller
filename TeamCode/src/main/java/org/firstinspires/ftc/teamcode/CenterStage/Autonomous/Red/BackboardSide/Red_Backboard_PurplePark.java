package org.firstinspires.ftc.teamcode.CenterStage.Autonomous.Red.BackboardSide;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.CenterStage.Autonomous.BaseAuto;

@Autonomous(group = "Red", name = "Purple & Park - Red Side Backboard")
public class Red_Backboard_PurplePark extends BaseAuto {
    public void initTrajectory() {
        sequence = drive.trajectorySequenceBuilder(new Pose2d(12, -64, Math.toRadians(90)))
                //Place purple pixel
                .lineTo(new Vector2d(12, -36))
                .waitSeconds(1)

                //Park
                .setTangent(Math.toRadians(-90))
                .splineToConstantHeading(new Vector2d(46, -58), Math.toRadians(0))
                .build();
    }
}
