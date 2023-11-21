package org.firstinspires.ftc.teamcode.CenterStage.Autonomous.Red.BackboardSide;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.CenterStage.Autonomous.BaseAuto;

@Autonomous(group = "Red", name = "Park - Red Side Backboard")
public class Red_Backboard_Park extends BaseAuto {
    public void initTrajectory() {
        sequence = drive.trajectorySequenceBuilder(new Pose2d(12, -64, Math.toRadians(90)))
                //Park
                .lineTo(new Vector2d(46, -58))
                .build();
    }
}
