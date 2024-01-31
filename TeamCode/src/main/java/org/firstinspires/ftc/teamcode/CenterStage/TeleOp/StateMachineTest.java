package org.firstinspires.ftc.teamcode.CenterStage.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.sfdev.assembly.state.StateMachine;
import com.sfdev.assembly.state.StateMachineBuilder;

import org.firstinspires.ftc.teamcode.Systems.Gamepad.Gamepad;
import org.firstinspires.ftc.teamcode.Systems.Gamepad.GamepadButtons;

@TeleOp(group = "Testing", name = "State Machine Test (self stopping)")
public class StateMachineTest extends OpMode {
    StateMachine stateMachine;
    Gamepad gamepad;

    enum States {
        STATE1,
        STATE2,
        STATE3
    }

    public void init() {
        stateMachine = new StateMachineBuilder()
                .state(States.STATE1)
                .onEnter(() -> {
                    resetRuntime();
                    telemetry.addLine("Entering state 1");
                })
                .transition(() -> getRuntime() > 5, States.STATE2)

                .state(States.STATE2)
                .onEnter(() -> {
                    resetRuntime();
                    telemetry.addLine("Entering state 2");
                })
                .transition(() -> getRuntime() > 5, States.STATE3)

                .state(States.STATE3)
                .onEnter(() -> {
                    resetRuntime();
                    telemetry.addLine("Entering state 3");
                })
                .transition(() -> getRuntime() > 5, () -> {
                    stateMachine.reset();
                    stateMachine.stop();
                })
                .build();

        gamepad = new Gamepad(gamepad1);
    }

    public void loop() {
        gamepad.onPress(GamepadButtons.Button.A, () -> {
            if (!stateMachine.isRunning()) stateMachine.start();
        });
        gamepad.update();

        telemetry.addLine();
        telemetry.addData("Is running", stateMachine.isRunning());
        telemetry.addData("Runtime", getRuntime());
        telemetry.update();
    }
}
