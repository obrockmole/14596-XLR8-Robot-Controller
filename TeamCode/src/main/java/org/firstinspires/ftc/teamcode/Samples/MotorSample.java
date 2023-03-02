package org.firstinspires.ftc.teamcode.Samples;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.Systems.Motor;

@TeleOp
@Disabled
public class MotorSample extends OpMode {
    Motor powerMotor;
    Motor positionMotor;

    @Override
    public void init() {
        /*
          Initialize powerMotor as a power motor by using doubles in the constructor.
          This example uses a predefined motor in the constructor.
         */
        powerMotor = new Motor(hardwareMap.get(DcMotorEx.class, "Power Motor"), 0, 0.5, 1);

        /*
          Initialize positionMotor as a position motor by using ints in the constructor.
          This example uses the hardware map and the motors name in the constructor.
         */
        positionMotor = new Motor(hardwareMap, "Position Motor", 0, 600, 1200, 2000);

        powerMotor.addPower(0.7)
                .setTargetPower(0.7);

        positionMotor.setPIDF(0.05, 0, 0.001, 0.4)
                .setTicksPerDegree(751.8 / 360);
    }

    @Override
    public void loop() {
        /*
          Change the state of powerMotor based on input from gamepad1.
         */
        if (gamepad1.a)
            powerMotor.setTargetPowerIndex(1); //Set motor power to 0.5
        else if (gamepad1.b)
            powerMotor.setTargetPowerIndex(3); //Set motor power to 0.7
        else if (gamepad1.y)
            powerMotor.setTargetPowerIndex(2); //Set motor power to 1

        /*
          Change the state of positionMotor based on input from gamepad2.
         */
        if (gamepad2.a)
            positionMotor.setTargetPositionIndex(1); //Set target position to 600
        else if (gamepad2.b)
            positionMotor.setTargetPositionIndex(2); //Set target position to 1200
        else if (gamepad2.y)
            positionMotor.setTargetPositionIndex(3); //Set target position to 2000

        powerMotor.update(gamepad1.left_stick_y); //Update powerMotor state
        positionMotor.update(gamepad2.left_stick_y); //Update positionMotor state
    }
}
