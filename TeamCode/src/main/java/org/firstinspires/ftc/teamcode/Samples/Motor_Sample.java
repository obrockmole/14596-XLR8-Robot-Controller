package org.firstinspires.ftc.teamcode.Samples;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.Systems.Gamepad.Gamepad;
import org.firstinspires.ftc.teamcode.Systems.Gamepad.GamepadButtons.Button;
import org.firstinspires.ftc.teamcode.Systems.Motors.Motor;
import org.firstinspires.ftc.teamcode.Systems.Motors.MotorLookupTable;

@Disabled
@TeleOp(group = "Samples")
public class Motor_Sample extends OpMode {
    Motor powerMotor, positionMotor;

    Gamepad driver, manipulator; //This example uses custom gamepads. See Gamepad_Sample.java for more information

    double[] powers; //List of saved powers to switch between
    int[] positions; //List of saved positions to switch between

    @Override
    public void init() {
        //Initializes custom gamepad's.
        driver =  new Gamepad(gamepad1);
        manipulator = new Gamepad(gamepad2);

        /*
          Initialize powerMotor as a power motor by specifying the mode as POWER in the constructor.
          This example uses a predefined motor in the constructor.
         */
        powerMotor = new Motor(hardwareMap.get(DcMotorEx.class, "powerMotor1"), MotorLookupTable.GOBILDA_435, Motor.Mode.POWER, 10, false);
        powers = new double[]{0, 0.3, 0.7, 1}; //Define list of powers to switch between

        /*
          Initialize positionMotor as a position motor by specifying the mode as POSITION in the constructor.
          This example uses the hardware map and the motors name in the constructor.
         */
        positionMotor = new Motor(hardwareMap, "posMotor1", MotorLookupTable.GOBILDA_435, Motor.Mode.POSITION, 10, false);
        positions = new int[]{0, 400, 750, 1200}; //Define list of positions to switch between

        powerMotor.setTargetPower(powers[0]); //Set initial motor power to 0

        positionMotor.setPIDF(0.05, 0, 0.001, 0.4) //Set PIDF coefficients
                .setTargetPosition(positions[0]); //Set initial motor position to 0
    }

    @Override
    public void loop() {
        /*
          Change the state of powerMotor based on input from gamepad1.
         */
        driver.onPress(Button.A, () -> powerMotor.setTargetPower(powers[0])) //Set motor power to 0
                .onPress(Button.B, () -> powerMotor.setTargetPower(powers[1])) //Set motor power to 0.3
                .onPress(Button.X, () -> powerMotor.setTargetPower(powers[2])) //Set motor power to 0.7
                .onPress(Button.Y, () -> powerMotor.setTargetPower(powers[3])) //Set motor power to 1
                .update();

        /*
          Change the state of positionMotor based on input from gamepad2.
         */
        manipulator.onPress(Button.A, () -> positionMotor.setTargetPosition(positions[0])) //Set motor position to 0 ticks
                .onPress(Button.B, () -> positionMotor.setTargetPosition(positions[1])) //Set motor position to 400 ticks
                .onPress(Button.X, () -> positionMotor.setTargetPosition(positions[2])) //Set motor position to 750 ticks
                .onPress(Button.Y, () -> positionMotor.setTargetPosition(positions[3])) //Set motor position to 1200 ticks
                .update();

        /*
            Log motor data to telemetry and update the objects.
         */
        powerMotor.log(telemetry, hardwareMap)
                .update();
        positionMotor.log(telemetry, hardwareMap)
                .update();
    }
}
