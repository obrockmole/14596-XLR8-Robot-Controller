package org.firstinspires.ftc.teamcode.CenterStage;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot.LogoFacingDirection;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot.UsbFacingDirection;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU.Parameters;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Systems.BlinkinLEDDriver;
import org.firstinspires.ftc.teamcode.Systems.Drivetrain;
import org.firstinspires.ftc.teamcode.Systems.Motors.Motor;
import org.firstinspires.ftc.teamcode.Systems.Motors.MotorGroup;
import org.firstinspires.ftc.teamcode.Systems.Motors.MotorList;
import org.firstinspires.ftc.teamcode.Systems.Odometry.Odometry;
import org.firstinspires.ftc.teamcode.Systems.Odometry.OdometryPod;
import org.firstinspires.ftc.teamcode.Systems.Sensors.BatteryVoltageSensor;
import org.firstinspires.ftc.teamcode.Systems.Sensors.ColorSensor;
import org.firstinspires.ftc.teamcode.Systems.Sensors.Encoder;
import org.firstinspires.ftc.teamcode.Systems.Sensors.IMU;
import org.firstinspires.ftc.teamcode.Systems.Servos.PositionServo;
import org.firstinspires.ftc.teamcode.Systems.Servos.PositionServoGroup;

public class Robot extends Drivetrain {
    public final MotorGroup lift;
    public final MotorGroup intake;

    public final PositionServoGroup grabbox;
    public final PositionServoGroup arm;
    public final PositionServoGroup intakeFlippers;
    public final PositionServoGroup hangRelease; //Added in By Cole

    public final PositionServo pixelClamp;
    public final PositionServo droneLauncher;

    public enum ArmStages {
        FOLDED(0.1, 0),
        IDLE(0, 0),
        DEPLOYED(0.7, 1),
        DEPLOYED_LOW(0.9, 1);

        private final double armPos, grabboxPos;

        ArmStages(double armPos, double grabboxPos) {
            this.armPos = armPos;
            this.grabboxPos = grabboxPos;
        }

        public double getArmPos() {
            return armPos;
        }

        public double getGrabboxPos() {
            return grabboxPos;
        }
    }
    public ArmStages currentArmStage = ArmStages.IDLE;

    public final ColorSensor leftPixelSensor;
    public final ColorSensor rightPixelSensor;

    public final BlinkinLEDDriver leftBlinkin;
    public final BlinkinLEDDriver rightBlinkin;

    private final BatteryVoltageSensor batteryVoltageSensor;

    public final int[] liftPositions = {0, 100, 500, 1000}; //TODO: find actual positions for lift (down, out, middle, max)

    public Robot(HardwareMap hardwareMap) {
        super(new Motor(hardwareMap, "frontLeft", MotorList.GOBILDA_435, Motor.Mode.POWER, false),
                new Motor(hardwareMap, "backLeft", MotorList.GOBILDA_435, Motor.Mode.POWER, false),
                new Motor(hardwareMap, "frontRight", MotorList.GOBILDA_435, Motor.Mode.POWER, false),
                new Motor(hardwareMap, "backRight", MotorList.GOBILDA_435, Motor.Mode.POWER, false),

                new Odometry(
                        new OdometryPod(new Encoder(hardwareMap, "frontLeft", Encoder.Direction.FORWARD)),
                        new OdometryPod(new Encoder(hardwareMap, "backRight", Encoder.Direction.FORWARD)),
                        new OdometryPod(new Encoder(hardwareMap, "intake1", Encoder.Direction.FORWARD))
                ),

                new IMU(hardwareMap, "imu", new Parameters(new RevHubOrientationOnRobot(LogoFacingDirection.UP, UsbFacingDirection.FORWARD)))
       );

        lift = new MotorGroup(
                new Motor(hardwareMap, "leftLift", MotorList.GOBILDA_435, Motor.Mode.POWER, false),
                new Motor(hardwareMap, "rightLift", MotorList.GOBILDA_435, Motor.Mode.POWER, true)
        );
//        lift.setPIDF(0.01, 0, 0, 0);

        intake = new MotorGroup(
                new Motor(hardwareMap, "intake1", MotorList.GOBILDA_435, Motor.Mode.POWER, true),
                new Motor(hardwareMap, "intake2", MotorList.GOBILDA_435, Motor.Mode.POWER, false)
        );
        intake.setSpeedScale(0.8);

        arm = new PositionServoGroup(
                new PositionServo(hardwareMap, "leftArm", 0, 1, false),
                new PositionServo(hardwareMap, "rightArm", 0, 1, true)
        );

        grabbox = new PositionServoGroup(
                new PositionServo(hardwareMap, "leftGrabbox", 0, 1, true),
                new PositionServo(hardwareMap, "rightGrabbox", 0, 1, false)
        );

        intakeFlippers = new PositionServoGroup(
                new PositionServo(hardwareMap, "leftIntakeFlipper", 0.6, 1, true),
                new PositionServo(hardwareMap, "rightIntakeFlipper", 0.6, 1, true)
        );

        hangRelease = new PositionServoGroup(
                new PositionServo(hardwareMap, "leftHangRelease", 0.26, 0.45, true),
                new PositionServo(hardwareMap, "rightHangRelease", 0.26, 0.45, false)
        ); //Added hang release servo group. By Cole

        pixelClamp = new PositionServo(hardwareMap, "pixelClamp", 0.2, 1, true);

        droneLauncher = new PositionServo(hardwareMap, "droneLauncher", 0.1, 0.3, false);

        leftPixelSensor = new ColorSensor(hardwareMap, "leftPixelSensor", 4.8f);
        rightPixelSensor = new ColorSensor(hardwareMap, "rightPixelSensor", 4.8f);

        leftBlinkin = new BlinkinLEDDriver(hardwareMap, "leftBlinkin");
        rightBlinkin = new BlinkinLEDDriver(hardwareMap, "rightBlinkin");

        batteryVoltageSensor = new BatteryVoltageSensor(hardwareMap);
    }

    public Robot initialize() {
//        lift.setTargetPosition(0);
        currentArmStage = ArmStages.IDLE;
        pixelClamp.setTargetPosition(0);

        intakeFlippers.setTargetPosition(0);

        hangRelease.setTargetPosition(0); //Added in by Cole
        droneLauncher.setTargetPosition(0);

        leftBlinkin.setPattern(BlinkinLEDDriver.Pattern.OCEAN_PALETTE_WAVES);
        rightBlinkin.setPattern(BlinkinLEDDriver.Pattern.OCEAN_PALETTE_WAVES);

        update(false);

        return this;
    }

    public Robot update(boolean updateDriveMotors) {
        if (updateDriveMotors) super.update();

        lift.update();
        if (lift.getPower() < 0) lift.setSpeedScale(0.4);
        else lift.setSpeedScale(1);

        intake.update();
        intakeFlippers.update();

        if (arm.getTargetPosition() != currentArmStage.armPos) arm.setTargetPosition(currentArmStage.armPos);
        if (grabbox.getTargetPosition() != currentArmStage.grabboxPos) grabbox.setTargetPosition(currentArmStage.grabboxPos);
        arm.update();
        grabbox.update();
        pixelClamp.update();

        droneLauncher.update();

        hangRelease.update(); //Added hangRelease update. By Cole

        return this;
    }

    public void setLiftPosition(int position) {
        if (lift.getMode() == Motor.Mode.POWER) {
            lift.setMode(Motor.Mode.POSITION);
            lift.setSpeedScale(2);
        }

        if (position == liftPositions[0])
            currentArmStage = ArmStages.IDLE;
        else if (currentArmStage == ArmStages.IDLE || currentArmStage == ArmStages.FOLDED)
            currentArmStage = ArmStages.DEPLOYED;

        lift.setTargetPosition(position);
    }

    public void setLiftPower(double power) {
        if (lift.getMode() == Motor.Mode.POSITION) {
            lift.setMode(Motor.Mode.POWER);
            lift.setSpeedScale(1);
        }

        lift.setTargetPower(power);
    }

    public Robot log(Telemetry telemetry, boolean logOdometry, boolean logIMU) {
        super.log(telemetry, logOdometry, logIMU);

        telemetry.addLine("-----Lift-----");
        telemetry.addData("Power", lift.getPower());
        telemetry.addData("Target Position", lift.getTargetPosition());
        telemetry.addData("Left Current Position", lift.getCurrentPosition(0));
        telemetry.addData("Right Current Position", lift.getCurrentPosition(1));
        telemetry.addLine();

        telemetry.addLine("-----Intake-----");
        telemetry.addData("Power", intake.getPower());
        telemetry.addLine();

        telemetry.addLine("-----Intake Flippers-----");
        telemetry.addData("Deployed", intakeFlippers.getTargetPosition() == 1);
        telemetry.addLine();

        telemetry.addLine("-----Arm-----");
        telemetry.addData("Stage", currentArmStage.toString());
        telemetry.addLine();

        telemetry.addLine("-----Pixel Clamp-----");
        telemetry.addData("Clamped", pixelClamp.getTargetPosition() == 1);
        telemetry.addLine();

        telemetry.addLine("-----Drone Launcher-----");
        telemetry.addData("Launched", droneLauncher.getTargetPosition() == 1);

        telemetry.addLine("-----Hang Release-----");
        telemetry.addData("Released", hangRelease.getTargetPosition() == 1);
        telemetry.addLine();

        telemetry.addLine("-----Battery Voltage-----");
        telemetry.addData("Voltage", batteryVoltageSensor.getBatteryVoltage());

        telemetry.addLine();
        return this;
    }
}
