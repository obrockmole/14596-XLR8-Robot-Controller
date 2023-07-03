package org.firstinspires.ftc.teamcode.Samples;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Systems.Gamepad.Gamepad;
import org.firstinspires.ftc.teamcode.Systems.Gamepad.GamepadButtons.Button;
import org.firstinspires.ftc.teamcode.Systems.Stopwatch;

@Disabled
@TeleOp(group = "Samples")
public class Stopwatch_Sample extends OpMode {
    Stopwatch stopwatch;

    Gamepad gamepad; //This example uses custom gamepads. See Gamepad_Sample.java for more information

    enum State { //State enum
        STATE_1,
        STATE_2,
        STATE_3
    }
    State state;

    @Override
    public void init() {
        gamepad = new Gamepad(gamepad1); //Initialize custom gamepad

        stopwatch = new Stopwatch(); //Initialize timer

        state = State.STATE_1;
    }

    @Override
    public void loop() {
        gamepad.onPress(Button.A, () -> stopwatch.start()) //Start the timer when the A button is pressed
                .update();

        //Update the state every second and display the current state to telemetry
        switch (state) {
            case STATE_1:
                telemetry.addLine("State: 1"); //Display the current state to telemetry (this should change every second)
                if (stopwatch.getTime() > 1000) { // getTime() returns the time in milliseconds, to get seconds use getTimeSeconds()
                    state = State.STATE_2; //Update the state
                    stopwatch.restart(); //Restart the timer
                }
                break;

            case STATE_2:
                telemetry.addLine("State: 2");
                if (stopwatch.getTime() > 1000) {
                    state = State.STATE_3;
                    stopwatch.restart();
                }
                break;

            case STATE_3:
                telemetry.addLine("State: 3");
                if (stopwatch.getTime() > 1000) {
                    state = State.STATE_1;
                    stopwatch.restart();
                }
                break;
        }

        //Display current time on telemetry
        telemetry.addLine("Time: " + stopwatch.getTime() + "ms");
        telemetry.addLine("Time (Seconds):" + stopwatch.getTimeSeconds() + "s");
        telemetry.update();
    }
}
