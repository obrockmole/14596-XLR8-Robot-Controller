package org.firstinspires.ftc.teamcode.Samples;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Systems.Gamepad.Gamepad;
import org.firstinspires.ftc.teamcode.Systems.Gamepad.GamepadButtons.Button;
import org.firstinspires.ftc.teamcode.Systems.Timer;

@Disabled
@TeleOp(group = "Samples", name = "Timer Sample")
public class Timer_Sample extends OpMode {
    Timer timer;

    Gamepad gamepad; //This example uses custom gamepads. See Gamepad_Sample.java for more information

    enum State { //State enum
        STATE_1,
        STATE_2,
        STATE_3 {
            public State next() {
                return values()[0]; //Rollover to the first state
            }
        };

        public State next() { //Get the next state
            return values()[ordinal() + 1];
        }
    }
    State state;

    @Override
    public void init() {
        gamepad = new Gamepad(gamepad1); //Initialize custom gamepad

        timer = new Timer(1000, () -> state = state.next()); //Initialize timer. Note: length is in milliseconds

        state = State.STATE_1;
    }

    @Override
    public void loop() {
        gamepad.onPress(Button.A, () -> timer.start()) //Start the timer when the A button is pressed
                .update();

        timer.update(true); //Update the timer. Note: true is passed to restart the timer on completion, false will stop the timer on completion

        //Update telmetry to display the current state
        telemetry.addLine("State: " + state);
        //Display current time on telemetry
        telemetry.addLine("Time Left: " + timer.getTimeLeft() + "ms");
        telemetry.addLine("Time Left (Seconds): " + timer.getTimeLeftSeconds() + "s");
        telemetry.update();
    }
}
