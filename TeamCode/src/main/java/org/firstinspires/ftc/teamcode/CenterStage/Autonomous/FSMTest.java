package org.firstinspires.ftc.teamcode.CenterStage.Autonomous;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(group = "Testing", name = "Finite State Machine Test")
public class FSMTest extends BaseAuto {
    public void initTrajectories() {
        sequence1 = drive.trajectorySequenceBuilder(new Pose2d(0, 0, 0))
                .lineTo(new Vector2d(20,  20))
                .build();

        sequence2 = drive.trajectorySequenceBuilder(sequence1.end())
                .lineTo(new Vector2d(0, 0))
                .build();
    }
}
