package org.firstinspires.ftc.teamcode.Testing;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Systems.Motors.Motor;
import org.firstinspires.ftc.teamcode.Systems.Motors.MotorLookupTable;

//@Disabled
@Config
@TeleOp(group = "Tuning")
public class PIDTuner_PositionMotor extends OpMode {
    private Motor motor;

    public static MotorLookupTable motorType;
    public static double p = 0, i = 0, d = 0, f = 0;
    public static int targetPosition = 0;
    public static int tolerance = 0;

    @Override
    public void init() {
        motor = new Motor(hardwareMap, "positionMotor", MotorLookupTable.GOBILDA_435, Motor.Mode.POSITION, 0, false);

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
    }

    @Override
    public void loop() {
        if (motor.getMotorType() != motorType) motor.setMotorType(motorType);

        motor.setPIDF(p, i, d, f)
                .setTargetPosition(targetPosition)
                .setTolerance(tolerance)
                .update();

        motor.log(telemetry, hardwareMap);
    }
}