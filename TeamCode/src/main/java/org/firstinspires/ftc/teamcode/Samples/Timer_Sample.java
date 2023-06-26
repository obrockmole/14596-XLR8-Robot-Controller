package org.firstinspires.ftc.teamcode.Samples;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Systems.Gamepad.Gamepad;
import org.firstinspires.ftc.teamcode.Systems.Gamepad.GamepadButtons.Button;
import org.firstinspires.ftc.teamcode.Systems.Timer;

@Disabled
@TeleOp(group = "Samples")
public class Timer_Sample extends OpMode {
    Timer timer;

    Gamepad gamepad; //This example uses custom gamepads. See Gamepad_Sample.java for more information

    enum State { //State enum
        STATE_1,
        STATE_2,
        STATE_3
    }
    State state;

    @Override
    public void init() {
        timer = new Timer(); //Initialize timer
        state = State.STATE_1;

        gamepad = new Gamepad(gamepad1); //Initialize custom gamepad
    }

    @Override
    public void loop() {
        gamepad.onPress(Button.A, () -> timer.start()) //Start the timer when the A button is pressed
                .update();

        //Update the state every second and display the current state to telemetry
        switch (state) {
            case STATE_1:
                telemetry.addLine("State: 1"); //Display the current state to telemetry (this should change every second)
                if (timer.getTime() > 1000) { // getTime() returns the time in milliseconds, to get seconds use getTimeSeconds()
                    state = State.STATE_2; //Update the state
                    timer.restart(); //Restart the timer
                }
                break;

            case STATE_2:
                telemetry.addLine("State: 2");
                if (timer.getTime() > 1000) {
                    state = State.STATE_3;
                    timer.restart();
                }
                break;

            case STATE_3:
                telemetry.addLine("State: 3");
                if (timer.getTime() > 1000) {
                    state = State.STATE_1;
                    timer.restart();
                }
                break;
        }

        //Display current time on telemetry
        telemetry.addLine("Time: " + timer.getTime() + "ms");
        telemetry.addLine("Time (Seconds):" + timer.getTimeSeconds() + "s");
        telemetry.update();
    }
}
