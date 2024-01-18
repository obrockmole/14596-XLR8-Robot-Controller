package org.firstinspires.ftc.teamcode.Tuning;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

import org.firstinspires.ftc.teamcode.Systems.Motors.Motor;
import org.firstinspires.ftc.teamcode.Systems.Motors.MotorList;

//@Disabled
@Config
@TeleOp(group = "Tuning")
public class PowerTuner_Motor extends OpMode {
    private Motor motor;

    public static String MOTOR_NAME = "powerMotor";

    public static MotorList MOTOR_TYPE = MotorList.GOBILDA_435;
    public static double TARGET_POWER = 0;
    public static boolean REVERSED = false;

    @Override
    public void init() {
        motor = new Motor(hardwareMap, MOTOR_NAME, MOTOR_TYPE, Motor.Mode.POWER, REVERSED);

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
    }

    @Override
    public void loop() {
        if (motor.getMotorType() != MOTOR_TYPE) motor.setMotorType(MOTOR_TYPE);

        motor.setTargetPower(TARGET_POWER)
                .setReversed(REVERSED)
                .update();

        motor.log(telemetry, hardwareMap);
        telemetry.addData("Current Position", motor.getCurrentPosition());
        telemetry.update();
    }
}