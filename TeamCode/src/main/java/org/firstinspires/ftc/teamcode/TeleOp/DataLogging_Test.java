package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.internal.system.AppUtil;
import org.firstinspires.ftc.teamcode.Systems.DataLogger;
import org.firstinspires.ftc.teamcode.Systems.Gamepad.Gamepad;
import org.firstinspires.ftc.teamcode.Systems.Gamepad.GamepadButtons.Button;
import org.firstinspires.ftc.teamcode.Systems.Motors.MotorLookupTable;
import org.firstinspires.ftc.teamcode.Systems.Motors.TestingMotor_Logging;
import org.firstinspires.ftc.teamcode.Systems.Stopwatch;

//@Disabled
@TeleOp(group = "Testing")
public class DataLogging_Test extends OpMode {
    TestingMotor_Logging motor1;

    Gamepad gamepad;
    int[] positions; //List of saved positions to switch between

    DataLogger positionLogger;
    Stopwatch stopwatch;

    @Override
    public void init() {
        gamepad =  new Gamepad(gamepad1);

        motor1 = new TestingMotor_Logging(hardwareMap, "posMotor1", MotorLookupTable.GOBILDA_435, 10, false);
        positions = new int[]{0, 400, 750, 1200};

        motor1.setPIDF(0.05, 0, 0.001, 0.2)
                .setTargetPosition(positions[0]);

        positionLogger = new DataLogger(AppUtil.ROOT_FOLDER + "/FIRST/PositionMotor.csv");
        stopwatch = new Stopwatch();
    }

    @Override
    public void start() {
        positionLogger.startLogging("Time,Power,Position,TargetPosition");
        stopwatch.start();
    }

    @Override
    public void loop() {
        positionLogger.writeData(stopwatch.getTimeSeconds() + motor1.getCSVData());

        gamepad.onPress(Button.A, () -> motor1.setTargetPosition(positions[0]))
                .onPress(Button.B, () -> motor1.setTargetPosition(positions[1]))
                .onPress(Button.X, () -> motor1.setTargetPosition(positions[2]))
                .onPress(Button.Y, () -> motor1.setTargetPosition(positions[3]))
                .update();

        motor1.log(telemetry, hardwareMap)
                .update();
    }

    @Override
    public void stop() {
        positionLogger.stopLogging();
    }
}
