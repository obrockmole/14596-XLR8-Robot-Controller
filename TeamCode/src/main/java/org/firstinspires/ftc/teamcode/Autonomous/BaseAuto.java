package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Systems.MotorGroup;

public class BaseAuto extends OpMode {
    MotorGroup motorGroup;

    @Override
    public void init() {
        motorGroup.addPosition(1);
    }

    @Override
    public void loop() {}
}
