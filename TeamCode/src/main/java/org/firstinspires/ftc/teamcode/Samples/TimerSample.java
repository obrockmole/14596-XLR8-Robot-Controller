package org.firstinspires.ftc.teamcode.Samples;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Systems.Gamepad.Gamepad;
import org.firstinspires.ftc.teamcode.Systems.Gamepad.GamepadButtons.Button;
import org.firstinspires.ftc.teamcode.Systems.Timer;

@Disabled
@Autonomous
public class TimerSample extends OpMode {
    Timer timer;

    Gamepad driver;

    enum State {
        STATE_1,
        STATE_2,
        STATE_3
    }
    State state;

    @Override
    public void init() {
        timer = new Timer();
        driver = new Gamepad(gamepad1);
        state = State.STATE_1;
    }

    @Override
    public void loop() {
        if (driver.onPress(Button.A)) timer.start();

        switch (state) {
            case STATE_1:
                telemetry.addLine("State: 1");
                if (timer.getTime() > 1000) {
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

        telemetry.addLine("Time: " + timer.getTime() + "ms");
        telemetry.addLine("Time (Seconds):" + timer.getTimeSeconds() + "s");
        telemetry.update();
    }
}
