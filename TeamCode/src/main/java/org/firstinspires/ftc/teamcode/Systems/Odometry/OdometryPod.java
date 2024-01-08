package org.firstinspires.ftc.teamcode.Systems.Odometry;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Systems.Sensors.Encoder;

/**
 * Class representing a dead wheel Odometry Pod.
 * A pod is a subset of a robot's odometry system, which is used for tracking the robot's position.
 */
public class OdometryPod {
    private Encoder encoder;

    /**
     * Constructs a new OdometryPod object with an Encoder.
     *
     * @param encoder The Encoder object that this OdometryPod will use.
     */
    public OdometryPod(Encoder encoder) {
        this.encoder = encoder;
    }

    /**
     * Constructs a new OdometryPod object with the HardwareMap and device name.
     *
     * @param hardwareMap The HardwareMap object used to get the Encoder object.
     * @param name The name of the Encoder object in the HardwareMap.
     */
    public OdometryPod(HardwareMap hardwareMap, String name) {
        this(new Encoder(hardwareMap, name));
    }

    /**
     * Constructs a new OdometryPod object with the HardwareMap, device name, and direction.
     *
     * @param hardwareMap The HardwareMap object used to get the Encoder object.
     * @param name The name of the Encoder object in the HardwareMap.
     * @param direction The direction of the Encoder.
     */
    public OdometryPod(HardwareMap hardwareMap, String name, Encoder.Direction direction) {
        this(new Encoder(hardwareMap, name, direction));
    }

    /**
     * Constructs a new OdometryPod object with a DcMotorEx.
     *
     * @param motor The DcMotorEx object that this OdometryPod will use.
     */
    public OdometryPod(DcMotorEx motor) {
        this(new Encoder(motor));
    }

    /**
     * Constructs a new OdometryPod object with the a DcMotorEx and direction.
     *
     * @param motor The DcMotorEx object that this OdometryPod will use.
     * @param direction The direction of the Encoder.
     */
    public OdometryPod(DcMotorEx motor, Encoder.Direction direction) {
        this(new Encoder(motor, direction));
    }

    /**
     * Returns the Encoder object that this OdometryPod uses.
     *
     * @return The Encoder object that this OdometryPod uses.
     */
    public Encoder getEncoder() {
        return encoder;
    }

    /**
     * Sets the Encoder object that this OdometryPod will use.
     *
     * @param encoder The Encoder object to use.
     * @return The current OdometryPod instance.
     */
    public OdometryPod setEncoder(Encoder encoder) {
        this.encoder = encoder;
        return this;
    }

    /**
     * Returns the DcMotorEx object that this OdometryPod uses.
     *
     * @return The DcMotorEx object that this OdometryPod uses.
     */
    public DcMotorEx getEncoderMotor() {
        return encoder.getMotor();
    }

    /**
     * Sets the DcMotorEx object that this OdometryPod will use.
     *
     * @param motor The DcMotorEx object to use.
     * @return The current OdometryPod instance.
     */
    public OdometryPod setEncoderMotor(DcMotorEx motor) {
        encoder.setMotor(motor);
        return this;
    }

    /**
     * Returns the direction of the Encoder.
     *
     * @return The direction of the Encoder.
     */
    public Encoder.Direction getEncoderDirection() {
        return encoder.getDirection();
    }

    /**
     * Sets the direction of the Encoder.
     *
     * @param direction The direction of the Encoder.
     * @return The current OdometryPod instance.
     */
    public OdometryPod setEncoderDirection(Encoder.Direction direction) {
        encoder.setDirection(direction);
        return this;
    }

    /**
     * Returns the current position of the Encoder.
     *
     * @return The current position of the Encoder.
     */
    public int getCurrentPosition() {
        return encoder.getCurrentPosition();
    }

    /**
     * Returns the current velocity of the Encoder.
     *
     * @return The current velocity of the Encoder.
     */
    public double getCurrentVelocity() {
        return encoder.getCurrentVelocity();
    }

    /**
     * Returns the CSV header for the Encoder's data.
     *
     * @return The CSV header as a string.
     */
    public String getCSVHeader() {
        return encoder.getCSVHeader();
    }

    /**
     * Returns the CSV data of the Encoder's current state.
     *
     * @return The CSV data as a string.
     */
    public String getCSVData() {
        return encoder.getCSVData();
    }

    /**
     * Logs the Encoder's data to telemetry.
     *
     * @param telemetry The Telemetry object used to log the data.
     * @param hardwareMap The HardwareMap object used to get the name of the Encoder.
     * @return The current OdometryPod instance.
     */
    public OdometryPod log(Telemetry telemetry, HardwareMap hardwareMap) {
        telemetry.addData("Encoder", hardwareMap.getNamesOf(getEncoderMotor()).toArray()[0]);
        telemetry.addData("Encoder Position", getCurrentPosition());
        telemetry.addData("Encoder Velocity", getCurrentVelocity());
        return this;
    }
}
