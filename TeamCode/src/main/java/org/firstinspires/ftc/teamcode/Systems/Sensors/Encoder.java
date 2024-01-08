package org.firstinspires.ftc.teamcode.Systems.Sensors;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Class representing an Encoder sensor.
 */
public class Encoder {
    private DcMotorEx motor;
    private Direction direction;

    /**
     * Enum representing the direction of rotation of the Encoder.
     */
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

    /**
     * Constructs a new Encoder object with a DcMotorEx and a direction.
     *
     * @param motor The DcMotorEx object that this Encoder will use.
     * @param direction The direction of rotation of the Encoder.
     */
    public Encoder(DcMotorEx motor, Direction direction) {
        DcMotor.RunMode currentRunMode = motor.getMode();
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(currentRunMode);

        this.motor = motor;
        this.direction = direction;
    }

    /**
     * Constructs a new Encoder object with a DcMotorEx.
     *
     * @param motor The DcMotorEx object that this Encoder will use.
     */
    public Encoder(DcMotorEx motor) {
        this(motor, Direction.FORWARD);
    }

    /**
     * Constructs a new Encoder object with a HardwareMap and a device name.
     *
     * @param hardwareMap The HardwareMap object used to get the DcMotorEx object.
     * @param name The name of the DcMotorEx object in the HardwareMap.
     */
    public Encoder(HardwareMap hardwareMap, String name) {
        this(hardwareMap.get(DcMotorEx.class, name), Direction.FORWARD);
    }

    /**
     * Constructs a new Encoder object with a HardwareMap, a device name, and a direction.
     *
     * @param hardwareMap The HardwareMap object used to get the DcMotorEx object.
     * @param name The name of the DcMotorEx object in the HardwareMap.
     * @param direction The direction of rotation of the Encoder.
     */
    public Encoder(HardwareMap hardwareMap, String name, Direction direction) {
        this(hardwareMap.get(DcMotorEx.class, name), direction);
    }

    /**
     * Returns the DcMotorEx object that this Encoder uses.
     *
     * @return The DcMotorEx object that this Encoder uses.
     */
    public DcMotorEx getMotor() {
        return motor;
    }

    /**
     * Sets the DcMotorEx object that this Encoder will use.
     *
     * @param motor The DcMotorEx object to use.
     * @return The current Encoder instance.
     */
    public Encoder setMotor(DcMotorEx motor) {
        this.motor = motor;
        return this;
    }

    /**
     * Returns the direction of rotation of the Encoder.
     *
     * @return The direction of rotation of the Encoder.
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * Sets the direction of rotation of the Encoder.
     *
     * @param direction The direction of rotation of the Encoder.
     * @return The current Encoder instance.
     */
    public Encoder setDirection(Direction direction) {
        this.direction = direction;
        return this;
    }

    /**
     * Returns the direction multiplier of the Encoder.
     *
     * @return The direction multiplier of the Encoder.
     */
    public int getDirectionMultiplier() {
        return getDirection().getMultiplier() * (motor.getDirection() == DcMotorSimple.Direction.FORWARD ? 1 : -1);
    }

    /**
     * Returns the current position of the Encoder.
     *
     * @return The current position of the Encoder.
     */
    public int getCurrentPosition() {
        return motor.getCurrentPosition() * getDirectionMultiplier();
    }

    /**
     * Returns the current velocity of the Encoder.
     *
     * @return The current velocity of the Encoder.
     */
    public double getCurrentVelocity() {
        return motor.getVelocity() * getDirectionMultiplier();
    }

    /**
     * Returns the CSV header for the Encoder's data.
     *
     * @return The CSV header as a string.
     */
    public String getCSVHeader() {
        return "Position,Velocity";
    }

    /**
     * Returns the CSV data of the Encoder's current state.
     *
     * @return The CSV data as a string.
     */
    public String getCSVData() {
        return String.format("%s,%s", getCurrentPosition(), motor.getVelocity());
    }

    /**
     * Logs the Encoder's data to telemetry.
     *
     * @param telemetry The Telemetry object used to log the data.
     * @param hardwareMap The HardwareMap object used to get the name of the DcMotorEx.
     * @return The current Encoder instance.
     */
    public Encoder log(Telemetry telemetry, HardwareMap hardwareMap) {
        telemetry.addData("Motor", hardwareMap.getNamesOf(motor).toArray()[0]);
        telemetry.addData("Position", getCurrentPosition());
        telemetry.addData("Velocity", motor.getVelocity());
        return this;
    }
}
