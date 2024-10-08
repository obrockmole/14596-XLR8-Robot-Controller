package org.firstinspires.ftc.teamcode.Tuning;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

import org.firstinspires.ftc.teamcode.Systems.Motors.Motor;
import org.firstinspires.ftc.teamcode.Systems.Motors.MotorList;

@Disabled
@Config
@TeleOp(group = "Tuning")
public class PIDTuner_PositionMotor extends OpMode {
    private Motor motor;

    public static String MOTOR_NAME = "positionMotor";

    public static MotorList MOTOR_TYPE = MotorList.GOBILDA_435;
    public static PIDFCoefficients PIDF_COEFFICIENTS = new PIDFCoefficients(0, 0, 0, 0);
    public static int TARGET_POSITION = 0;
    public static int TOLERANCE = 0;
    public static boolean REVERSED = false;

    @Override
    public void init() {
        motor = new Motor(hardwareMap, MOTOR_NAME, MOTOR_TYPE, Motor.Mode.POSITION, TOLERANCE, REVERSED);

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
    }

    @Override
    public void loop() {
        if (motor.getMotorType() != MOTOR_TYPE) motor.setMotorType(MOTOR_TYPE);

        motor.setPIDF(PIDF_COEFFICIENTS.p, PIDF_COEFFICIENTS.i, PIDF_COEFFICIENTS.d, PIDF_COEFFICIENTS.f)
                .setTargetPosition(TARGET_POSITION)
                .setTolerance(TOLERANCE)
                .setReversed(REVERSED)
                .update();

        motor.log(telemetry, hardwareMap);
    }
}