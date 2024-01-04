package org.firstinspires.ftc.teamcode.Systems.Sensors;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Encoder {
    public enum Direction {
        FORWARD(1),
        REVERSE(-1);

        private final int directionMultiplier;

        Direction(int directionMultiplier) {
            this.directionMultiplier = directionMultiplier;
        }

        public int getMultiplier() {
            return directionMultiplier;
        }
    }

    private DcMotorEx motor;
    private Direction direction;

    public Encoder(DcMotorEx motor, Direction direction) {
        DcMotor.RunMode currentRunMode = motor.getMode();
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(currentRunMode);

        this.motor = motor;
        this.direction = direction;
    }

    public Encoder(DcMotorEx motor) {
        this(motor, Direction.FORWARD);
    }

    public Encoder(HardwareMap hardwareMap, String name, Direction direction) {
        this(hardwareMap.get(DcMotorEx.class, name), direction);
    }

    public Encoder(HardwareMap hardwareMap, String name) {
        this(hardwareMap.get(DcMotorEx.class, name), Direction.FORWARD);
    }

    public DcMotorEx getMotor() {
        return motor;
    }

    public Encoder setMotor(DcMotorEx motor) {
        this.motor = motor;
        return this;
    }

    public Direction getDirection() {
        return direction;
    }

    public Encoder setDirection(Direction direction) {
        this.direction = direction;
        return this;
    }

    public int getDirectionMultiplier() {
        return getDirection().getMultiplier() * (motor.getDirection() == DcMotorSimple.Direction.FORWARD ? 1 : -1);
    }

    public int getCurrentPosition() {
        return motor.getCurrentPosition() * getDirectionMultiplier();
    }

    public double getCurrentVelocity() {
        return motor.getVelocity() * getDirectionMultiplier();
    }

    public String getCSVHeader() {
        return "Position,Velocity";
    }

    public String getCSVData() {
        return String.format("%s,%s", getCurrentPosition(), motor.getVelocity());
    }

    public Encoder log(Telemetry telemetry, HardwareMap hardwareMap) {
        telemetry.addData("Motor", hardwareMap.getNamesOf(motor).toArray()[0]);
        telemetry.addData("Position", getCurrentPosition());
        telemetry.addData("Velocity", motor.getVelocity());
        return this;
    }
}
