package org.firstinspires.ftc.teamcode.Testing;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.PIDCoefficients;

import org.firstinspires.ftc.teamcode.Systems.Motors.MotorLookupTable;
import org.firstinspires.ftc.teamcode.Systems.Motors.VelocityMotor;

//@Disabled
@Config
@TeleOp(group = "Tuning")
public class PIDTuner_VelocityMotor extends OpMode {
    private VelocityMotor motor;

    public static PIDCoefficients velocityPID = new PIDCoefficients();
    public static PIDCoefficients feedforwardPID = new PIDCoefficients();
    public static int targetVelocity = 0;

    @Override
    public void init() {
        motor = new VelocityMotor(hardwareMap, "velocityMotor", MotorLookupTable.GOBILDA_435, false);

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
    }

    @Override
    public void loop() {
        motor.setVelocityControllerCoefficients(velocityPID.p, velocityPID.i, velocityPID.d)
                .setFeedforwardControllerCoefficients(feedforwardPID.p, feedforwardPID.i, feedforwardPID.d)
                .setTargetVelocity(targetVelocity)
                .log(telemetry, hardwareMap)
                .update();
    }
}