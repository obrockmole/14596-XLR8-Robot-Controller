package org.firstinspires.ftc.teamcode.Samples;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Systems.Gamepad.Gamepad;
import org.firstinspires.ftc.teamcode.Systems.Gamepad.GamepadButtons.Button;
import org.firstinspires.ftc.teamcode.Systems.Motors.MotorLookupTable;
import org.firstinspires.ftc.teamcode.Systems.Motors.VelocityMotor;
import org.firstinspires.ftc.teamcode.Systems.Motors.VelocityMotorGroup;

//@Disabled
@TeleOp(group = "Samples")
public class VelocityMotorGroup_Sample extends OpMode {
    VelocityMotorGroup motors;

    Gamepad gamepad; //This example uses a custom gamepad. See GamepadSample.java for more information

    int[] velocities; //List of saved velocities to switch between

    @Override
    public void init() {
        //Initializes custom gamepad.
        gamepad =  new Gamepad(gamepad1);

        motors = new VelocityMotorGroup(new VelocityMotor(hardwareMap, "veloMotor1", MotorLookupTable.GOBILDA_435, false), new VelocityMotor(hardwareMap, "veloMotor2", MotorLookupTable.GOBILDA_435, true)); //3600 is the max ticks per second for a GoBilda 435rpm motor according to Gunnar
        velocities = new int[]{0, 1200, 2400, 3600};

        motors.setVelocityControllerCoefficients(0, 0, 0)
                .setFeedforwardControllerCoefficients(0, 0, 0)
                .setTargetVelocity(velocities[0]);
    }

    @Override
    public void loop() {
        /*
          Change the state of the motors based on input from gamepad1.
         */
        gamepad.onPress(Button.A, () -> motors.setTargetVelocity(velocities[0])) //Set the motors velocities to 0 tps
                .onPress(Button.B, () -> motors.setTargetVelocity(velocities[1])) //Set the motors velocities to 1200 tps
                .onPress(Button.X, () -> motors.setTargetVelocity(velocities[2])) //Set the motors velocities to 2400 tps
                .onPress(Button.Y, () -> motors.setTargetVelocity(velocities[3])) //Set the motors velocities to 3600 tps
                .update();

        motors.log(telemetry, hardwareMap);
    }
}
