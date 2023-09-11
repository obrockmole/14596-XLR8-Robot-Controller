package org.firstinspires.ftc.teamcode.Autonomous;

import com.acmerobotics.roadrunner.Vector2d;

public class AutoSkeleton extends NewRRBaseAuto {
    public void setAction() {
        action = drive.actionBuilder(drive.pose)
                .splineTo(new Vector2d(24, 24), Math.PI / 2)
                .splineTo(new Vector2d(48, 0), Math.PI)
                .splineTo(new Vector2d(24, -24), 3 * Math.PI / 2)
                .splineTo(new Vector2d(0, 0), 2 * Math.PI)
                .build();
    }
}
