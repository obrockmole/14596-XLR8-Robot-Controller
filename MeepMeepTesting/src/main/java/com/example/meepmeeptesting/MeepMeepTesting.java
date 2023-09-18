package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                .setConstraints(70, 50, Math.toRadians(180), Math.toRadians(180), 16)
                .followTrajectorySequence(drive ->
                            drive.trajectorySequenceBuilder(new Pose2d(64, -36, Math.toRadians(180)))
                                .lineToSplineHeading(new Pose2d(36, -40, Math.toRadians(90)))
                                .lineTo(new Vector2d(24, -60))
                                .waitSeconds(2)

                                .splineToConstantHeading(new Vector2d(12, -40), Math.toRadians(90))
                                .splineTo(new Vector2d(12, 24), Math.toRadians(90))
                                .splineToLinearHeading(new Pose2d(36, 40, Math.toRadians(90)), Math.toRadians(0))
                                .waitSeconds(3)

                                .splineToLinearHeading(new Pose2d(12, 20, Math.toRadians(90)), Math.toRadians(-90))
                                .lineTo(new Vector2d(12, -60))
                                .waitSeconds(2)

                                .splineTo(new Vector2d(12, 24), Math.toRadians(90))
                                .splineToLinearHeading(new Pose2d(36, 40, Math.toRadians(90)), Math.toRadians(0))
                                .waitSeconds(3)
                                .build()
                );

        meepMeep.setBackground(MeepMeep.Background.FIELD_POWERPLAY_OFFICIAL)
                .setDarkMode(true)
                .setBackgroundAlpha(0.9f)
                .addEntity(myBot)
                .start();
    }
}