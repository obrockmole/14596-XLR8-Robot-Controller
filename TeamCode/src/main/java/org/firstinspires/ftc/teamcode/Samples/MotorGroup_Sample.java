package org.firstinspires.ftc.teamcode.Samples;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.Systems.Gamepad.Gamepad;
import org.firstinspires.ftc.teamcode.Systems.Gamepad.GamepadButtons.Button;
import org.firstinspires.ftc.teamcode.Systems.Motors.Motor;
import org.firstinspires.ftc.teamcode.Systems.Motors.MotorGroup;

@Disabled
@TeleOp(group = "Samples")
public class MotorGroup_Sample extends OpMode {
    MotorGroup powerMotors, positionMotors;

    Gamepad driver, manipulator; //This example uses custom gamepads. See GamepadSample.java for more information

    double[] powers; //List of saved powers to switch between
    int[] positions; //List of saved positions to switch between

    @Override
    public void init() {
        //Initializes custom gamepad's.
        driver =  new Gamepad(gamepad1);
        manipulator = new Gamepad(gamepad2);

        /*
          Initialize powerMotors with a list of motors set to power by specifying the mode as POWER in the constructor.
          This example uses a predefined motors in the constructor.
         */
        powerMotors = new MotorGroup(new Motor(hardwareMap.get(DcMotorEx.class, "powerMotor1"), Motor.Mode.POWER, 751.8 / 360, 10, false),
                new Motor(hardwareMap.get(DcMotorEx.class, "powerMotor2"), Motor.Mode.POWER, 751.8 / 360, 10, true));
        powers = new double[]{0, 0.3, 0.7, 1};

        /*
          Initialize positionMotors with a list of motors set to position control by specifying the mode as POSITION in the constructor.
          This example uses the hardware map and the motors name in the constructor.
         */
        positionMotors = new MotorGroup(new Motor(hardwareMap, "posMotor1", Motor.Mode.POSITION, 751.8 / 360, 10, false),
                new Motor(hardwareMap, "posMotor2", Motor.Mode.POSITION, 751.8 / 360, 10, true));
        positions = new int[]{0, 400, 750, 1200};

        powerMotors.setTargetPower(powers[0]);

        positionMotors.setPIDF(0.05, 0, 0.001, 0.4)
                .setTargetPosition(positions[0]);
    }

    @Override
    public void loop() {
        /*
          Change the state of powerMotor based on input from gamepad1.
         */
        driver.onPress(Button.A, () -> powerMotors.setTargetPower(powers[0])) //Set motor powers to 0
                .onPress(Button.B, () -> powerMotors.setTargetPower(powers[1])) //Set motor powers to 0.3
                .onPress(Button.X, () -> powerMotors.setTargetPower(powers[2])) //Set motor powers to 0.7
                .onPress(Button.Y, () -> powerMotors.setTargetPower(powers[3])) //Set motor powers to 1
                .update();

        /*
          Change the state of positionMotor based on input from gamepad2.
         */
        manipulator.onPress(Button.A, () -> positionMotors.setTargetPosition(positions[0])) //Set motor positions to 0
                .onPress(Button.B, () -> positionMotors.setTargetPosition(positions[1])) //Set motor positions to 400
                .onPress(Button.X, () -> positionMotors.setTargetPosition(positions[2])) //Set motor positions to 750
                .onPress(Button.Y, () -> positionMotors.setTargetPosition(positions[3])) //Set motor positions to 1200
                .update();

        powerMotors.update(); //Update powerMotor object
        positionMotors.update(); //Update positionMotor object

        powerMotors.log(telemetry, hardwareMap); //Log power motors
        positionMotors.log(telemetry, hardwareMap); //Log position motors
    }
}
