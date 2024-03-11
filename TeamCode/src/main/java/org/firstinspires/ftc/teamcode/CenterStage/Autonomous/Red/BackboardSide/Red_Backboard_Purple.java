package org.firstinspires.ftc.teamcode.CenterStage.Autonomous.Red.BackboardSide;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.CenterStage.Autonomous.BaseAuto;
import org.firstinspires.ftc.teamcode.Systems.Movement.MovementSequence;
import org.firstinspires.ftc.teamcode.Systems.Movement.OdometryDrive;
import org.firstinspires.ftc.teamcode.Systems.Vision.VisionDetector;

@Autonomous(group = "Red", name = "Purple - Red Backboard")
public class Red_Backboard_Purple extends BaseAuto {
    public void initVision() {
        pipeline = getRedPipeline();
        contourDetector = new VisionDetector(hardwareMap, "Webcam", pipeline);
    }

    public MovementSequence leftSequence() {
        return new MovementSequence(robot)
                .odometryDrive(-32, 1.5, 0.8, 200)
                .odometryTurn(-90,0.6, 100)
                .afterMoving(() -> robot.intake.setTargetPower(-0.55))
                .waitSeconds(2)
                .afterMoving(() -> robot.intake.setTargetPower(0))
                .odometryTurn(90,0.6, 100)
                .odometryDrive(32, 0, 0.8, 200)
                .odometryTurn(-90,0.6, 100)
                .odometryDrive(-34, 0, 1, 0);
    }

    public MovementSequence centerSequence() {
        return new MovementSequence(robot)
                .odometryDrive(-44, -12, 0.8, 100)
                .odometryTurn(-90,0.6, 100)
                .afterMoving(() -> robot.intake.setTargetPower(-0.55))
                .waitSeconds(2)
                .afterMoving(() -> robot.intake.setTargetPower(0))
                .odometryTurn(90,0.6, 100)
                .odometryDrive(32, 0, 0.8, 200)
                .odometryTurn(-90,0.6, 100)
                .odometryDrive(-22, 0, 1, 0);
    }

    public MovementSequence rightSequence() {
        return new MovementSequence(robot)
                .odometryDrive(-32, 0, 0.8, 200)
                .odometryTurn(-90,0.6, 100)
                .odometryDrive(-24, 0, 0.8, 100)
                .afterMoving(() -> robot.intake.setTargetPower(-0.55))
                .waitSeconds(2)
                .afterMoving(() -> robot.intake.setTargetPower(0))
                .odometryTurn(90,0.6, 100)
                .odometryDrive(32, 0, 0.8, 200)
                .odometryTurn(-90,0.6, 100)
                .odometryDrive(-10, 0, 1, 0);
    }
}
