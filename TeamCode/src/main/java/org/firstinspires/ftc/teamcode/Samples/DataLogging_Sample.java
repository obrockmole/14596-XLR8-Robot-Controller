package org.firstinspires.ftc.teamcode.Samples;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.internal.system.AppUtil;
import org.firstinspires.ftc.teamcode.Systems.DataLogger;
import org.firstinspires.ftc.teamcode.Systems.Gamepad.Gamepad;
import org.firstinspires.ftc.teamcode.Systems.Gamepad.GamepadButtons.Button;
import org.firstinspires.ftc.teamcode.Systems.Motors.Motor;
import org.firstinspires.ftc.teamcode.Systems.Motors.MotorLookupTable;
import org.firstinspires.ftc.teamcode.Systems.Stopwatch;

@Disabled
@TeleOp(group = "Samples", name = "Data Logging Sample")
public class DataLogging_Sample extends OpMode {
    Motor motor; //This example uses custom motors. See Motor_Sample.java for more information

    Gamepad gamepad; //This example uses custom gamepads. See Gamepad_Sample.java for more information
    int[] positions; //List of saved positions to switch between

    DataLogger motorLogger;
    Stopwatch stopwatch;

    @Override
    public void init() {
        gamepad =  new Gamepad(gamepad1); //Initialize custom gamepad

        motor = new Motor(hardwareMap, "motor", MotorLookupTable.GOBILDA_435, Motor.Mode.POSITION, 10, false); //Initialize custom motor
        positions = new int[]{0, 400, 750, 1200}; //Define list of positions to switch between

        motor.setPIDF(0.05, 0, 0.001, 0.2) //Set PIDF coefficients
                .setTargetPosition(positions[0]); //Set initial motor position to 0

        motorLogger = new DataLogger(AppUtil.ROOT_FOLDER + "/FIRST/LoggedData/Motor.csv"); //Initialize data logger with file path
        stopwatch = new Stopwatch();
    }

    @Override
    public void start() {
        motorLogger.startLogging("Time,Power,TargetPower,PowerError,Position,TargetPosition,PositionError"); //Create file and write header
        stopwatch.start(); //Start the stopwatch to get time
    }

    @Override
    public void loop() {
        motorLogger.writeData(stopwatch.getTimeSeconds() + motor.getCSVData()); //Write data to file

        /*
          Change the state of motor based on input from gamepad1.
         */
        gamepad.onPress(Button.A, () -> motor.setTargetPosition(positions[0]))
                .onPress(Button.B, () -> motor.setTargetPosition(positions[1]))
                .onPress(Button.X, () -> motor.setTargetPosition(positions[2]))
                .onPress(Button.Y, () -> motor.setTargetPosition(positions[3]))
                .update();

        /*
            Log motor data to telemetry and update the object.
         */
        motor.log(telemetry, hardwareMap)
                .update();
    }

    @Override
    public void stop() {
        motorLogger.stopLogging(); //Save the logged data and close the file
    }
}
