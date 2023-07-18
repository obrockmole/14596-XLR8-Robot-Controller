package org.firstinspires.ftc.teamcode.Samples;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Systems.Gamepad.Gamepad;
import org.firstinspires.ftc.teamcode.Systems.Gamepad.GamepadButtons.Button;
import org.firstinspires.ftc.teamcode.Systems.Servos.ContinuousServo;
import org.firstinspires.ftc.teamcode.Systems.Servos.ContinuousServoGroup;

@Disabled
@TeleOp(group = "Samples")
public class ServoGroupContinuous_Sample extends OpMode {
    ContinuousServoGroup servos;

    Gamepad gamepad; //This example uses a custom gamepad. See Gamepad_Sample.java for more information

    double[] powers; //List of saved powers to switch between

    @Override
    public void init() {
        //Initializes custom gamepad.
        gamepad =  new Gamepad(gamepad1);

        servos = new ContinuousServoGroup(new ContinuousServo(hardwareMap, "contServo1", false),
                new ContinuousServo(hardwareMap, "contServo2", true)); //Initialize servos

        powers = new double[]{0, 0.3, 0.7, 1}; //Define list of powers to switch between
        servos.setTargetPower(powers[0]); //Set initial servo power to 0
    }

    @Override
    public void loop() {
        /*
          Change the state of servos based on input from gamepad1.
         */
        gamepad.onPress(Button.A, () -> servos.setTargetPower(powers[0])) //Set servo powers to 0
            .onPress(Button.B, () -> servos.setTargetPower(powers[1])) //Set servo powers to 0.3
            .onPress(Button.X, () -> servos.setTargetPower(powers[2])) //Set servo powers to 0.7
            .onPress(Button.Y, () -> servos.setTargetPower(powers[3])) //Set servo powers to 1
            .update();

        /*
          Log servo data to telemetry and update the object.
         */
        servos.log(telemetry, hardwareMap)
                .update();
    }
}
