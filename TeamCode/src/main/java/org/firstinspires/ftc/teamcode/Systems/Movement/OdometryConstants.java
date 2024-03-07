package org.firstinspires.ftc.teamcode.Systems.Movement;

import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

public class OdometryConstants {
    public static final double ODOM_INCHES_PER_COUNT = 0.002969;

    public static final double DRIVE_GAIN = 0.03;
    public static final double DRIVE_ACCEL = 1.0;
    public static final double DRIVE_TOLERANCE = 1;
    public static final double DRIVE_DEADBAND = 0.2;
    public static final double DRIVE_MAX_AUTO = 0.6;

    public static final double STRAFE_GAIN = 0.03;
    public static final double STRAFE_ACCEL = 1;
    public static final double STRAFE_TOLERANCE = 1;
    public static final double STRAFE_DEADBAND = 0.2;
    public static final double STRAFE_MAX_AUTO = 0.6;

    public static final double YAW_GAIN = 0.018;
    public static final double YAW_ACCEL  = 2.0;
    public static final double YAW_TOLERANCE = 3.0;
    public static final double YAW_DEADBAND = 0.25;
    public static final double YAW_MAX_AUTO = 0.6;

    static class ProportionalControl {
        double lastOutput = 0;
        double gain;
        double accelLimit;
        double defaultOutputLimit;
        double liveOutputLimit;
        double setPoint = 0;
        double tolerance;
        double deadband;
        boolean circular;
        boolean inPosition = false;
        ElapsedTime cycleTime = new ElapsedTime();

        public ProportionalControl(double gain, double accelLimit, double outputLimit, double tolerance, double deadband, boolean circular) {
            this.gain = gain;
            this.accelLimit = accelLimit;
            this.defaultOutputLimit = outputLimit;
            this.liveOutputLimit = outputLimit;
            this.tolerance = tolerance;
            this.deadband = deadband;
            this.circular = circular;
            reset(0.0);
        }

        public double getOutput(double input) {
            double error = setPoint - input;
            double dV = cycleTime.seconds() * accelLimit;
            double output;

            if (circular)
                error = ((error + 180) % 360) - 180;

            inPosition = Math.abs(error) < tolerance;

            if (Math.abs(error) <= deadband) {
                output = 0;
            } else {
                output = error * gain;
                output = Range.clip(output, -liveOutputLimit, liveOutputLimit);

                if ((output - lastOutput) > dV) {
                    output = lastOutput + dV;
                } else if ((output - lastOutput) < -dV) {
                    output = lastOutput - dV;
                }
            }

            lastOutput = output;
            cycleTime.reset();
            return output;
        }

        public boolean inPosition(){
            return inPosition;
        }

        public double getSetpoint() {
            return setPoint;
        }

        public void reset(double setPoint, double powerLimit) {
            liveOutputLimit = Math.abs(powerLimit);
            this.setPoint = setPoint;
            reset();
        }

        public void reset(double setPoint) {
            liveOutputLimit = defaultOutputLimit;
            this.setPoint = setPoint;
            reset();
        }

        public void reset() {
            cycleTime.reset();
            inPosition = false;
            lastOutput = 0.0;
        }
    }
}