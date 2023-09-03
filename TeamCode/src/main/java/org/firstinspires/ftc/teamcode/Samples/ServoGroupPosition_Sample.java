package org.firstinspires.ftc.teamcode.Samples;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Systems.Gamepad.Gamepad;
import org.firstinspires.ftc.teamcode.Systems.Gamepad.GamepadButtons.Button;
import org.firstinspires.ftc.teamcode.Systems.Servos.PositionServo;
import org.firstinspires.ftc.teamcode.Systems.Servos.PositionServoGroup;

@Disabled
@TeleOp(group = "Samples", name = "Position Servo Group Sample")
public class ServoGroupPosition_Sample extends OpMode {
    PositionServoGroup servos;

    Gamepad gamepad; //This example uses a custom gamepad. See Gamepad_Sample.java for more information

    double[] positions; //List of saved positions to switch between

    @Override
    public void init() {
        //Initializes custom gamepad.
        gamepad =  new Gamepad(gamepad1);

        servos = new PositionServoGroup(new PositionServo(hardwareMap, "posServo1", 0, 1, false),
                new PositionServo(hardwareMap, "posServo2", 0, 1, true)); //Initializes servos with min position of 0, max position of 1

        positions = new double[]{0, 0.3, 0.7, 1}; //Define list of saved positions to switch between
        servos.setTargetPosition(positions[0]); //Set initial servo position to 0
    }

    @Override
    public void loop() {
        /*
          Change the state of servos based on input from gamepad1.
         */
        gamepad.onPress(Button.A, () -> servos.setTargetPosition(positions[0])) //Set servo positions to 0
                .onPress(Button.B, () -> servos.setTargetPosition(positions[1])) //Set servo positions to 0.3
                .onPress(Button.X, () -> servos.setTargetPosition(positions[2])) //Set servo positions to 0.7
                .onPress(Button.Y, () -> servos.setTargetPosition(positions[3])) //Set servo positions to 1
                .update();

        /*
          Log servo data to telemetry and update the object.
         */
        servos.log(telemetry, hardwareMap)
                .update();
    }
}
