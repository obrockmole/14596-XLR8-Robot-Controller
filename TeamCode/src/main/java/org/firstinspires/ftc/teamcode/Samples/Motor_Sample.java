package org.firstinspires.ftc.teamcode.Samples;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.Systems.Gamepad.Gamepad;
import org.firstinspires.ftc.teamcode.Systems.Gamepad.GamepadButtons.Button;
import org.firstinspires.ftc.teamcode.Systems.Motor;

@Disabled
@TeleOp(group = "Samples")
public class Motor_Sample extends OpMode {
    Motor powerMotor, positionMotor;

    Gamepad driver, manipulator; //This example uses custom gamepads. See GamepadSample.java for more information

    double[] powers; //List of saved powers to switch between
    int[] positions; //List of saved positions to switch between

    @Override
    public void init() {
        //Initializes custom gamepad's. See GamepadSample.java for more information
        driver =  new Gamepad(gamepad1);
        manipulator = new Gamepad(gamepad2);

        /*
          Initialize powerMotor as a power motor by specifying the mode as POWER in the constructor.
          This example uses a predefined motor in the constructor.
         */
        powerMotor = new Motor(hardwareMap.get(DcMotorEx.class, "PowerMotor"), Motor.Mode.POWER, 751.8 / 360, false);
        powers = new double[]{0, 0.3, 0.7, 1};

        /*
          Initialize positionMotor as a position motor by specifying the mode as POSITION in the constructor.
          This example uses the hardware map and the motors name in the constructor.
         */
        positionMotor = new Motor(hardwareMap, "PositionMotor", Motor.Mode.POSITION, 751.8 / 360, false);
        positions = new int[]{0, 400, 750, 1200};

        powerMotor.setTargetPower(powers[0]);

        positionMotor.setPIDF(0.05, 0, 0.001, 0.4)
                .setTargetPosition(positions[0]);
    }

    @Override
    public void loop() {
        /*
          Change the state of powerMotor based on input from gamepad1.
         */
        if (driver.onPress(Button.A))
            powerMotor.setTargetPower(powers[0]); //Set motor power to 0
        else if (driver.onPress(Button.B))
            powerMotor.setTargetPower(powers[1]); //Set motor power to 0.3
        else if (driver.onPress(Button.X))
            powerMotor.setTargetPower(powers[2]); //Set motor power to 0.7
        else if (driver.onPress(Button.Y))
            powerMotor.setTargetPower(powers[3]); //Set motor power to 1

        /*
          Change the state of positionMotor based on input from gamepad2.
         */
        if (manipulator.onPress(Button.A))
            powerMotor.setTargetPosition(positions[0]); //Set motor position to 0 ticks
        else if (manipulator.onPress(Button.B))
            powerMotor.setTargetPosition(positions[1]); //Set motor position to 400 ticks
        else if (manipulator.onPress(Button.X))
            powerMotor.setTargetPosition(positions[2]); //Set motor position to 750 ticks
        else if (manipulator.onPress(Button.Y))
            powerMotor.setTargetPosition(positions[3]); //Set motor position to 1200 ticks

        powerMotor.update(); //Update powerMotor object
        positionMotor.update(); //Update positionMotor object
    }
}
