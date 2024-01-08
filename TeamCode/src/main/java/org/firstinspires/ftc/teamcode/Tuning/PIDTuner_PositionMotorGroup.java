package org.firstinspires.ftc.teamcode.Tuning;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

import org.firstinspires.ftc.teamcode.Systems.Motors.Motor;
import org.firstinspires.ftc.teamcode.Systems.Motors.MotorGroup;
import org.firstinspires.ftc.teamcode.Systems.Motors.MotorList;

//@Disabled
@Config
@TeleOp(group = "Tuning")
public class PIDTuner_PositionMotorGroup extends OpMode {
    private MotorGroup motors;

    public static String MOTOR_1_NAME = "positionMotor1";
    public static String MOTOR_2_NAME = "positionMotor2";

    public static MotorList MOTOR_TYPE = MotorList.GOBILDA_435;
    public static PIDFCoefficients PIDF_COEFFICIENTS = new PIDFCoefficients(0, 0, 0, 0);
    public static int TARGET_POSITION = 0;
    public static int TOLERANCE = 0;
    public static  boolean MOTOR_1_REVERSED = false;
    public static  boolean MOTOR_2_REVERSED = false;

    @Override
    public void init() {
        motors = new MotorGroup(
                new Motor(hardwareMap, MOTOR_1_NAME, MOTOR_TYPE, Motor.Mode.POSITION, TOLERANCE, MOTOR_1_REVERSED),
                new Motor(hardwareMap, MOTOR_2_NAME, MOTOR_TYPE, Motor.Mode.POSITION, TOLERANCE, MOTOR_2_REVERSED)
        );

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
    }

    @Override
    public void loop() {
        if (motors.getMotorType() != MOTOR_TYPE) motors.setMotorType(MOTOR_TYPE);

        motors.setPIDF(PIDF_COEFFICIENTS.p, PIDF_COEFFICIENTS.i, PIDF_COEFFICIENTS.d, PIDF_COEFFICIENTS.f)
                .setTargetPosition(TARGET_POSITION)
                .setTolerance(TOLERANCE)
                .setReversed(0, MOTOR_1_REVERSED)
                .setReversed(1, MOTOR_2_REVERSED)
                .update();

        motors.log(telemetry, hardwareMap);
    }
}