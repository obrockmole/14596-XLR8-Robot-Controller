package org.firstinspires.ftc.teamcode.Tuning;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Systems.Servos.PositionServo;
import org.firstinspires.ftc.teamcode.Systems.Servos.PositionServoGroup;

@Disabled
@Config
@TeleOp(group = "Tuning")
public class PositionTuner_PositionServoGroup extends OpMode {
    private PositionServoGroup servos;

    public static String SERVO_1_NAME = "positionServo1";
    public static String SERVO_2_NAME = "positionServo1";

    public static double TARGET_POSITION = 0;
    public static double MIN_SCALE = 0;
    public static double MAX_SCALE = 1;
    public static boolean SERVO_1_REVERSED = false;
    public static boolean SERVO_2_REVERSED = false;

    @Override
    public void init() {
        servos = new PositionServoGroup(
                new PositionServo(hardwareMap, SERVO_1_NAME, MIN_SCALE, MAX_SCALE, SERVO_1_REVERSED),
                new PositionServo(hardwareMap, SERVO_2_NAME, MIN_SCALE, MAX_SCALE, SERVO_2_REVERSED)
        );
        servos.setTargetPosition(TARGET_POSITION);

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
    }

    @Override
    public void loop() {
        servos.setTargetPosition(TARGET_POSITION)
                .setReversed(0, SERVO_1_REVERSED)
                .setReversed(1, SERVO_2_REVERSED)
                .setScaleRange(MIN_SCALE, MAX_SCALE)
                .update();

        servos.log(telemetry, hardwareMap);
    }
}
