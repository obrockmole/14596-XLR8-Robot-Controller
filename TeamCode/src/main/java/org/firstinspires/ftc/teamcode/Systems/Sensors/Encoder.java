package org.firstinspires.ftc.teamcode.Systems.Sensors;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Systems.Stopwatch;

public class Encoder {
    public enum Direction {
        FORWARD(1),
        REVERSE(-1);

        private int directionMultiplier;

        Direction(int directionMultiplier) {
            this.directionMultiplier = directionMultiplier;
        }

        public int getMultiplier() {
            return directionMultiplier;
        }
    }

    private DcMotorEx motor;
    private Stopwatch stopwatch;
    private Direction direction;

    private int lastPosition;
    private int velocityEstimateIndex;
    private double[] velocityEstimates;
    private double lastUpdateTime;

    public Encoder(DcMotorEx motor, Direction direction) {
        this.motor = motor;
        this.direction = direction;
        stopwatch = new Stopwatch();
        stopwatch.start();

        this.lastPosition = 0;
        this.velocityEstimates = new double[3];
        this.lastUpdateTime = stopwatch.getTimeSeconds();
    }

    public Encoder(HardwareMap hardwareMap, String name, Direction direction) {
        this(hardwareMap.get(DcMotorEx.class, name), direction);
    }

    public Encoder(DcMotorEx motor) {
        this(motor, Direction.FORWARD);
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

    private static double inverseOverflow(double input, double estimate) {
        int real = (int)input & 0xffff;
        real += ((real % 20) / 4) * 0x10000;
        real += Math.round((estimate - real) / (5 * 0x10000)) * 5 * 0x10000;

        return real;
    }

    public int getCurrentPosition() {
        int multiplier = getDirectionMultiplier();
        int currentPosition = motor.getCurrentPosition() * multiplier;
        if (currentPosition != lastPosition) {
            double currentTime = stopwatch.getTimeSeconds();
            double deltaTime = currentTime - lastUpdateTime;
            velocityEstimates[velocityEstimateIndex] = (currentPosition - lastPosition) / deltaTime;
            velocityEstimateIndex = (velocityEstimateIndex + 1) % 3;
            lastPosition = currentPosition;
            lastUpdateTime = currentTime;
        }
        return currentPosition;
    }

    public double getRawVelocity() {
        return motor.getVelocity() * getDirectionMultiplier();
    }

    public double getCorrectedVelocity() {
        double median = velocityEstimates[0] > velocityEstimates[1]
                ? Math.max(velocityEstimates[1], Math.min(velocityEstimates[0], velocityEstimates[2]))
                : Math.max(velocityEstimates[0], Math.min(velocityEstimates[1], velocityEstimates[2]));
        return inverseOverflow(getRawVelocity(), median);
    }
}
