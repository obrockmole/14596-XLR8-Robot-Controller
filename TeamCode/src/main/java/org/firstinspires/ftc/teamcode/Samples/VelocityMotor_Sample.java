package org.firstinspires.ftc.teamcode.Samples;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Systems.Gamepad.Gamepad;
import org.firstinspires.ftc.teamcode.Systems.Gamepad.GamepadButtons.Button;
import org.firstinspires.ftc.teamcode.Systems.Motors.MotorLookupTable;
import org.firstinspires.ftc.teamcode.Systems.Motors.VelocityMotor;

@Disabled
@TeleOp(group = "Samples")
public class VelocityMotor_Sample extends OpMode {
    VelocityMotor motor;

    Gamepad gamepad; //This example uses custom gamepads. See Gamepad_Sample.java for more information

    int[] velocities; //List of saved velocities to switch between

    @Override
    public void init() {
        //Initializes custom gamepad.
        gamepad =  new Gamepad(gamepad1);

        motor = new VelocityMotor(hardwareMap, "veloMotor1", MotorLookupTable.GOBILDA_435, false); //3600 is the max ticks per second for a GoBilda 435rpm motor according to Gunnar
        velocities = new int[]{0, 1200, 2400, 3600}; //Define list of velocities to switch between (in ticks per second)

        motor.setVelocityControllerCoefficients(0, 0, 0) //Set velocity PID coefficients
                .setFeedforwardControllerCoefficients(0, 0, 0) //Set feedforward PID coefficients
                .setTargetVelocity(velocities[0]); //Set initial velocity to 0 tps
    }

    @Override
    public void loop() {
        /*
          Change the state of motor based on input from gamepad1.
         */
        gamepad.onPress(Button.A, () -> motor.setTargetVelocity(velocities[0])) //Set motor velocity to 0 tps
                .onPress(Button.B, () -> motor.setTargetVelocity(velocities[1])) //Set motor velocity to 1200 tps
                .onPress(Button.X, () -> motor.setTargetVelocity(velocities[2])) //Set motor velocity to 2400 tps
                .onPress(Button.Y, () -> motor.setTargetVelocity(velocities[3])) //Set motor velocity to 3600 tps
                .update();

        /*
            Log motor data to telemetry and update the object.
         */
        motor.log(telemetry, hardwareMap)
                .update();
    }
}
