package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.Systems.Drivetrain;
import org.firstinspires.ftc.teamcode.Systems.SinglePowerMotor;

public class BaseTele extends OpMode {
    Drivetrain drivetrain;

    public void init() {
        SinglePowerMotor frontLeft = new SinglePowerMotor(hardwareMap.get(DcMotorEx.class, "frontLeft"));
        SinglePowerMotor backLeft = new SinglePowerMotor(hardwareMap.get(DcMotorEx.class, "backLeft"));
        SinglePowerMotor frontRight = new SinglePowerMotor(hardwareMap.get(DcMotorEx.class, "frontRight"));
        SinglePowerMotor backRight = new SinglePowerMotor(hardwareMap.get(DcMotorEx.class, "backRight"));

        drivetrain = new Drivetrain(frontLeft, backLeft, frontRight, backRight);
    }

    public void loop() {}
}
