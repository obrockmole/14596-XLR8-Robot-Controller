package org.firstinspires.ftc.teamcode.Testing;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.controller.PIDController;
import com.arcrobotics.ftclib.controller.wpilibcontroller.SimpleMotorFeedforward;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Systems.Motors.MotorLookupTable;
import org.firstinspires.ftc.teamcode.Systems.Motors.VelocityMotor;

//@Disabled
@Config
@TeleOp
public class PIDTuner_VelocityMotor extends OpMode {
    private VelocityMotor motor;

    public static MotorLookupTable motorType;
    public static PIDController velocityController = new PIDController(0, 0, 0);
    public static SimpleMotorFeedforward feedforwardController = new SimpleMotorFeedforward(0, 0, 0);
    public static int targetVelocity = 0;

    @Override
    public void init() {
        motor = new VelocityMotor(hardwareMap, "velocityMotor", MotorLookupTable.GOBILDA_435, false);

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
    }

    @Override
    public void loop() {
        if (motor.getMotorType() != motorType) motor.setMotorType(motorType);

        motor.setVelocityController(velocityController)
                .setFeedforwardController(feedforwardController)
                .setTargetVelocity(targetVelocity)
                .update();

        motor.log(telemetry, hardwareMap);
    }
}