package org.firstinspires.ftc.teamcode.RoadRunner.Drive.Tuning;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

import org.firstinspires.ftc.teamcode.Systems.Motors.MotorLookupTable;
import org.firstinspires.ftc.teamcode.Systems.Motors.VelocityMotor;

//@Disabled
@Config
@TeleOp(group = "Tuning")
public class PIDTuner_VelocityMotor extends OpMode {
    private VelocityMotor motor;

    public static PIDFCoefficients PIDF_COEFFICIENTS = new PIDFCoefficients();
    public static int TARGET_VELOCITY = 0;

    @Override
    public void init() {
        motor = new VelocityMotor(hardwareMap, "velocityMotor", MotorLookupTable.GOBILDA_435, false);
        PIDF_COEFFICIENTS = motor.getPIDF();

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
    }

    @Override
    public void loop() {
        motor.setPIDF(PIDF_COEFFICIENTS)
                .setTargetVelocity(TARGET_VELOCITY)
                .log(telemetry, hardwareMap)
                .update();
    }
}