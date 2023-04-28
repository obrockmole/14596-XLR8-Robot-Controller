package org.firstinspires.ftc.teamcode.Samples;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Systems.Gamepad.Gamepad;
import org.firstinspires.ftc.teamcode.Systems.Gamepad.GamepadButtons.Button;
import org.firstinspires.ftc.teamcode.Systems.Timer;

//@Disabled
@Autonomous(group = "Samples")
public class Timer_Sample extends OpMode {
    Timer timer;

    Gamepad driver; //This example uses custom gamepads. See GamepadSample.java for more information

    enum State {
        STATE_1,
        STATE_2,
        STATE_3
    }
    State state;

    @Override
    public void init() {
        timer = new Timer();
        state = State.STATE_1;

        driver = new Gamepad(gamepad1);
    }

    @Override
    public void loop() {
        if (driver.onPress(Button.A)) timer.start(); //Start the timer when the A button is pressed

        //Update the state every second and display the current state to telemetry
        switch (state) {
            case STATE_1:
                telemetry.addLine("State: 1");
                if (timer.getTime() > 1000) { // getTime() returns the time in milliseconds, to get seconds use getTimeSeconds()
                    state = State.STATE_2;
                    timer.restart();
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
