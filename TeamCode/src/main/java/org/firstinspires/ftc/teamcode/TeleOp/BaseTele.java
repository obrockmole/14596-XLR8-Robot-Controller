package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Systems.Drivetrain;
import org.firstinspires.ftc.teamcode.Systems.Motor;

public class BaseTele extends OpMode {
    Drivetrain drivetrain;

    public void init() {
        Motor frontLeft = new Motor(hardwareMap, "frontLeft", Motor.Mode.POWER, 751.8 / 360, false);
        Motor backLeft = new Motor(hardwareMap, "backLeft", Motor.Mode.POWER, 751.8 / 360, false);
        Motor frontRight = new Motor(hardwareMap, "frontRight", Motor.Mode.POWER, 751.8 / 360, false);
        Motor backRight = new Motor(hardwareMap, "backRight", Motor.Mode.POWER, 751.8 / 360, false);

        drivetrain = new Drivetrain(frontLeft, backLeft, frontRight, backRight);
    }

    public void loop() {}
}
