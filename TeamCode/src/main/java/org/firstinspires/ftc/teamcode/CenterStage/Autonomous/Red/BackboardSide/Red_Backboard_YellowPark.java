package org.firstinspires.ftc.teamcode.CenterStage.Autonomous.Red.BackboardSide;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.CenterStage.Autonomous.BaseAuto;

@Disabled
@Autonomous(group = "Red", name = "Yellow & Park - Red Side Backboard")
public class Red_Backboard_YellowPark extends BaseAuto {
    public void initTrajectory() {
        sequence = drive.trajectorySequenceBuilder(new Pose2d(12, -64, Math.toRadians(90)))
                //Drive to backboard and place yellow pixel
                .lineToLinearHeading(new Pose2d(40, -50, Math.toRadians(30)))
                .waitSeconds(1)

                //Park
                .lineTo(new Vector2d(46, -58))
                .build();
    }
}
