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
                .setConstraints(65, 50, Math.toRadians(120), Math.toRadians(120), 16)
                .followTrajectorySequence(drive ->
                            drive.trajectorySequenceBuilder(new Pose2d(12, -64, Math.toRadians(90)))
                                    //Place purple pixel
                                    .lineToSplineHeading(new Pose2d(12, -36, Math.toRadians(0)))

                                    //Park
                                    .setTangent(Math.toRadians(-90))
                                    .splineToConstantHeading(new Vector2d(46, -58), Math.toRadians(0))

                                .build()
                );

        meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.9f)
                .addEntity(myBot)
                .start();
    }
}