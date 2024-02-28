package org.firstinspires.ftc.teamcode.Tuning;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Systems.Motors.Motor;
import org.firstinspires.ftc.teamcode.Systems.Motors.MotorGroup;
import org.firstinspires.ftc.teamcode.Systems.Motors.MotorList;

//@Disabled
@Config
@TeleOp(group = "Tuning")
public class PowerTuner_MotorGroup extends OpMode {
    private MotorGroup motors;

    public static String MOTOR_1_NAME = "powerMotor1";
    public static String MOTOR_2_NAME = "powerMotor2";

    public static MotorList MOTOR_TYPE = MotorList.GOBILDA_435;
    public static double TARGET_POWER = 0;
    public static boolean MOTOR_1_REVERSED = false;
    public static boolean MOTOR_2_REVERSED = false;

    @Override
    public void init() {
        motors = new MotorGroup(
                new Motor(hardwareMap, MOTOR_1_NAME, MOTOR_TYPE, Motor.Mode.POWER, MOTOR_1_REVERSED),
                new Motor(hardwareMap, MOTOR_2_NAME, MOTOR_TYPE, Motor.Mode.POWER, MOTOR_2_REVERSED)
        );

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
    }

    @Override
    public void loop() {
        if (motors.getMotorType() != MOTOR_TYPE) motors.setMotorType(MOTOR_TYPE);

        motors.setTargetPower(TARGET_POWER)
                .setReversed(0, MOTOR_1_REVERSED)
                .setReversed(1, MOTOR_2_REVERSED)
                .update();

        motors.log(telemetry, hardwareMap);
        telemetry.addData("Motor 1 Current Position", motors.getCurrentPosition(0));
        telemetry.addData("Motor 2 Current Position", motors.getCurrentPosition(1));
        telemetry.update();
    }
}