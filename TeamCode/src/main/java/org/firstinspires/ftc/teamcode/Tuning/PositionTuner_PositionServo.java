package org.firstinspires.ftc.teamcode.Tuning;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Systems.Servos.PositionServo;

@Disabled
@Config
@TeleOp(group = "Tuning")
public class PositionTuner_PositionServo extends OpMode {
    private PositionServo servo;

    public static String SERVO_NAME = "positionServo";

    public static double TARGET_POSITION = 0;
    public static double MIN_SCALE = 0;
    public static double MAX_SCALE = 1;
    public static boolean REVERSED = false;

    @Override
    public void init() {
        servo = new PositionServo(hardwareMap, SERVO_NAME, MIN_SCALE, MAX_SCALE, REVERSED);
        servo.setTargetPosition(TARGET_POSITION);

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
    }

    @Override
    public void loop() {
        servo.setTargetPosition(TARGET_POSITION)
                .setReversed(REVERSED)
                .setScaleRange(MIN_SCALE, MAX_SCALE)
                .update();

        servo.log(telemetry, hardwareMap);
    }
}
