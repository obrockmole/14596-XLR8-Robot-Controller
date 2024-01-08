package org.firstinspires.ftc.teamcode.Systems.Odometry;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Systems.Sensors.Encoder;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class representing an Odometry system with multiple OdometryPods.
 * The Odometry system is comprised of multiple pods, typically 2 or 3
 */
public class Odometry extends ArrayList<OdometryPod> {
    /**
     * Constructs a new Odometry object with a list of OdometryPods.
     *
     * @param pods The OdometryPods that this Odometry object will use.
     */
    public Odometry(@NonNull OdometryPod... pods) {
        super(Arrays.asList(pods));
    }

    /**
     * Constructs a new Odometry object with a list of Encoders.
     *
     * @param encoders The Encoders that this Odometry object will use.
     */
    public Odometry(@NonNull Encoder... encoders) {
        super();
        for (Encoder encoder : encoders)
            this.add(new OdometryPod(encoder));
    }

    /**
     * Returns the OdometryPod at the given index.
     *
     * @param pod The index of the OdometryPod.
     * @return The OdometryPod at the given index.
     */
    public OdometryPod getPod(int pod) {
        return get(pod);
    }

    /**
     * Sets the OdometryPod at the given index.
     *
     * @param pod The index to set the OdometryPod at.
     * @param newPod The OdometryPod to set.
     * @return The current Odometry instance.
     */
    public Odometry setPod(int pod, @NonNull OdometryPod newPod) {
        set(pod, newPod);
        return this;
    }

    /**
     * Returns the list of OdometryPods.
     *
     * @return The list of OdometryPods.
     */
    public ArrayList<OdometryPod> getPods() {
        return this;
    }

    /**
     * Sets the list of OdometryPods.
     *
     * @param pods The OdometryPods to set.
     * @return The current Odometry instance.
     */
    public Odometry setPods(@NonNull OdometryPod... pods) {
        clear();
        this.addAll(Arrays.asList(pods));
        return this;
    }

    /**
     * Returns the Encoder of the OdometryPod at the given index.
     *
     * @param pod The index of the OdometryPod.
     * @return The Encoder of the OdometryPod at the given index.
     */
    public Encoder getEncoder(int pod) {
        return get(pod).getEncoder();
    }

    /**
     * Sets the Encoder of the OdometryPod at the given index.
     *
     * @param encoder The Encoder to set.
     * @param pod The index of the OdometryPod.
     * @return The current Odometry instance.
     */
    public Odometry setEncoder(int pod, @NonNull Encoder encoder) {
        get(pod).setEncoder(encoder);
        return this;
    }

    /**
     * Returns the DcMotorEx of the OdometryPod at the given index.
     *
     * @param pod The index of the OdometryPod.
     * @return The DcMotorEx of the OdometryPod at the given index.
     */
    public DcMotorEx getEncoderMotor(int pod) {
        return get(pod).getEncoderMotor();
    }

    /**
     * Sets the DcMotorEx of the OdometryPod at the given index.
     *
     * @param motor The DcMotorEx to set.
     * @param pod The index of the OdometryPod.
     * @return The current Odometry instance.
     */
    public Odometry setEncoderMotor(int pod, @NonNull DcMotorEx motor) {
        get(pod).setEncoderMotor(motor);
        return this;
    }

    /**
     * Returns the direction of the Encoder of the OdometryPod at the given index.
     *
     * @param pod The index of the OdometryPod.
     * @return The direction of the Encoder of the OdometryPod at the given index.
     */
    public Encoder.Direction getEncoderDirection(int pod) {
        return get(pod).getEncoderDirection();
    }

    /**
     * Sets the direction of the Encoder of the OdometryPod at the given index.
     *
     * @param direction The direction to set.
     * @param pod The index of the OdometryPod.
     * @return The current Odometry instance.
     */
    public Odometry setEncoderDirection(int pod, Encoder.Direction direction) {
        get(pod).setEncoderDirection(direction);
        return this;
    }

    /**
     * Returns the current position of the Encoder of the OdometryPod at the given index.
     *
     * @param index The index of the OdometryPod.
     * @return The current position of the Encoder of the OdometryPod at the given index.
     */
    public int getCurrentPosition(int index) {
        return get(index).getCurrentPosition();
    }

    /**
     * Returns the current velocity of the Encoder of the OdometryPod at the given index.
     *
     * @param index The index of the OdometryPod.
     * @return The current velocity of the Encoder of the OdometryPod at the given index.
     */
    public double getCurrentVelocity(int index) {
        return get(index).getCurrentVelocity();
    }

    /**
     * Returns the CSV header for the Odometry's data.
     *
     * @return The CSV header as a string.
     */
    public String getCSVHeader() {
        return get(0).getCSVHeader();
    }

    /**
     * Returns the CSV data of the OdometryPod at the given index.
     *
     * @param index The index of the OdometryPod.
     * @return The CSV data as a string.
     */
    public String getCSVData(int index) {
        return get(index).getCSVData();
    }

    /**
     * Logs the Odometry's data to telemetry.
     *
     * @param telemetry The Telemetry object used to log the data.
     * @param hardwareMap The HardwareMap object used to get the name of the OdometryPod.
     * @return The current Odometry instance.
     */
    public Odometry log(Telemetry telemetry, HardwareMap hardwareMap) {
        for (OdometryPod pod : this) {
            pod.log(telemetry, hardwareMap);
            telemetry.addLine();
        }
        return this;
    }
}
