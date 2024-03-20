package org.firstinspires.ftc.teamcode.Systems.Movement;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@Config
public class OdometryConstants {
    public static final double TICKS_PER_REV = 2000;
    public static final double WHEEL_RADIUS = 0.9448819; //Inches
    public static final double INCHES_PER_TICK = (2 * WHEEL_RADIUS * Math.PI) / TICKS_PER_REV; //0.00296843403555

    public static final double DRIVE_GAIN = 0.03;
    public static final double DRIVE_ACCEL = 1.0;
    public static final double DRIVE_TOLERANCE = 0.5; //Inches

    public static final double STRAFE_GAIN = 0.03;
    public static final double STRAFE_ACCEL = 1;
    public static final double STRAFE_TOLERANCE = 0.5; //Inches

    public static final double YAW_GAIN = 0.02;
    public static final double YAW_ACCEL  = 2.0;
    public static final double YAW_TOLERANCE = 2.0; //Degrees

    static class ProportionalControl {
        double target = 0;
        double lastOutput = 0;
        double gain;
        double accelLimit;
        double outputLimit;
        double tolerance;
        boolean inPosition = false;
        ElapsedTime cycleTime = new ElapsedTime();

        public ProportionalControl(double gain, double accelLimit, double outputLimit, double tolerance) {
            this.gain = gain;
            this.accelLimit = accelLimit;
            this.outputLimit = outputLimit;
            this.tolerance = tolerance;
            reset(0.0);
        }

        public double getOutput(double input) {
            double error = target - input;
            double dV = cycleTime.seconds() * accelLimit;
            double output;

            inPosition = Math.abs(error) < tolerance;

            output = error * gain;
            output = Range.clip(output, -outputLimit, outputLimit);

            if ((output - lastOutput) > dV) {
                output = lastOutput + dV;
            } else if ((output - lastOutput) < -dV) {
                output = lastOutput - dV;
            }

            lastOutput = output;
            cycleTime.reset();
            return output;
        }

        public boolean inPosition(){
            return inPosition;
        }

        public void setOutputLimit(double outputLimit) {
            this.outputLimit = outputLimit;
        }

        public void reset(double setPoint) {
            this.target = setPoint;
            reset();
        }

        public void reset() {
            cycleTime.reset();
            inPosition = false;
            lastOutput = 0.0;
        }
    }
}