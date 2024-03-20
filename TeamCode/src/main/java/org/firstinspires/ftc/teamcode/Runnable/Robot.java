package org.firstinspires.ftc.teamcode.Runnable;

import com.qualcomm.hardware.lynx.LynxModule;
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
import org.firstinspires.ftc.teamcode.Systems.Odometry.Localizer;
import org.firstinspires.ftc.teamcode.Systems.Odometry.Odometry;
import org.firstinspires.ftc.teamcode.Systems.Odometry.OdometryPod;
import org.firstinspires.ftc.teamcode.Systems.Odometry.ThreeWheelLocalizer;
import org.firstinspires.ftc.teamcode.Systems.Sensors.BatteryVoltageSensor;
import org.firstinspires.ftc.teamcode.Systems.Sensors.ColorSensor;
import org.firstinspires.ftc.teamcode.Systems.Sensors.Encoder;
import org.firstinspires.ftc.teamcode.Systems.Sensors.IMU;
import org.firstinspires.ftc.teamcode.Systems.Servos.PositionServo;
import org.firstinspires.ftc.teamcode.Systems.Servos.PositionServoGroup;

import java.util.List;

public class Robot extends Drivetrain {
    private final Localizer localizer;

    public final MotorGroup lift;
    public final MotorGroup intake;

    public final PositionServoGroup grabbox;
    public final PositionServoGroup arm;
    public final PositionServoGroup intakeFlippers;
//    public final PositionServoGroup hangRelease; //Added in By Cole
    public final PositionServo leftHangRelease;
    public final PositionServo rightHangRelease;

    public final PositionServo pixelClamp;
    public final PositionServo droneLauncher;

    public enum ArmStages {
        FOLDED(0, 0),
        IDLE(0.1, 0),
        DEPLOYED(0.65, 0.25),
        DEPLOYED_LOW(0.45, 0.25);

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

    public final int[] liftPositions = {0, 700, 900, 1150};

    public Robot(HardwareMap hardwareMap) {
        super(new Motor(hardwareMap, "frontLeft", MotorList.GOBILDA_435, Motor.Mode.POWER, true),
                new Motor(hardwareMap, "backLeft", MotorList.GOBILDA_435, Motor.Mode.POWER, true),
                new Motor(hardwareMap, "frontRight", MotorList.GOBILDA_435, Motor.Mode.POWER, true),
                new Motor(hardwareMap, "backRight", MotorList.GOBILDA_435, Motor.Mode.POWER, true),

                new Odometry(
                        new OdometryPod(new Encoder(hardwareMap, "frontLeft", Encoder.Direction.FORWARD)),
                        new OdometryPod(new Encoder(hardwareMap, "backRight", Encoder.Direction.FORWARD)),
                        new OdometryPod(new Encoder(hardwareMap, "intake1", Encoder.Direction.REVERSE))
                ),

                new IMU(hardwareMap, "imu", new Parameters(new RevHubOrientationOnRobot(LogoFacingDirection.BACKWARD, UsbFacingDirection.UP)))
       );

        List<LynxModule> allHubs = hardwareMap.getAll(LynxModule.class);
        for (LynxModule module : allHubs) {
            module.setBulkCachingMode(LynxModule.BulkCachingMode.AUTO);
        }

        localizer = new ThreeWheelLocalizer(this.getOdometry());

        lift = new MotorGroup(
                new Motor(hardwareMap, "leftLift", MotorList.GOBILDA_312, Motor.Mode.POSITION, 20, false),
                new Motor(hardwareMap, "rightLift", MotorList.GOBILDA_312, Motor.Mode.POSITION, 20, true)
        );
        lift.setPIDF(0.01, 0, 0, 0);

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
                new PositionServo(hardwareMap, "leftGrabbox", 0, 1, false),
                new PositionServo(hardwareMap, "rightGrabbox", 0, 1, true)
        );

        intakeFlippers = new PositionServoGroup(
                new PositionServo(hardwareMap, "leftIntakeFlipper", 0.6, 1, true),
                new PositionServo(hardwareMap, "rightIntakeFlipper", 0.6, 1, true)
        );

//        hangRelease = new PositionServoGroup(
//                new PositionServo(hardwareMap, "leftHangRelease", 0, 1, false),
//                new PositionServo(hardwareMap, "rightHangRelease", 0, 1, true)
//        ); //Added hang release servo group. By Cole
        leftHangRelease = new PositionServo(hardwareMap, "leftHangRelease", 0, 1, false);
        rightHangRelease = new PositionServo(hardwareMap, "rightHangRelease", 0, 1, false);

        pixelClamp = new PositionServo(hardwareMap, "pixelClamp", 0.2, 1, true);

        droneLauncher = new PositionServo(hardwareMap, "droneLauncher", 0.15, 0.3, false);

        leftPixelSensor = new ColorSensor(hardwareMap, "leftPixelSensor", 4.8f);
        rightPixelSensor = new ColorSensor(hardwareMap, "rightPixelSensor", 4.8f);

        leftBlinkin = new BlinkinLEDDriver(hardwareMap, "leftBlinkin");
        rightBlinkin = new BlinkinLEDDriver(hardwareMap, "rightBlinkin");

        batteryVoltageSensor = new BatteryVoltageSensor(hardwareMap);
    }

    public Localizer getLocalizer() {
        return localizer;
    }

    public Robot initialize() {
        currentArmStage = ArmStages.IDLE;
        pixelClamp.setTargetPosition(0);

        intakeFlippers.setTargetPosition(0);

//        hangRelease.setTargetPosition(0); //Added in by Cole
        leftHangRelease.setTargetPosition(0);
        rightHangRelease.setTargetPosition(0.5);
        droneLauncher.setTargetPosition(0);

        leftBlinkin.setPattern(BlinkinLEDDriver.Pattern.OCEAN_PALETTE_WAVES);
        rightBlinkin.setPattern(BlinkinLEDDriver.Pattern.OCEAN_PALETTE_WAVES);

        update(false);

        return this;
    }

    public Robot update(boolean updateDriveMotors) {
        if (updateDriveMotors) super.update();

        lift.update();

        intake.update();
        intakeFlippers.update();

        if (arm.getTargetPosition() != currentArmStage.armPos) arm.setTargetPosition(currentArmStage.armPos);
        if (grabbox.getTargetPosition() != currentArmStage.grabboxPos) grabbox.setTargetPosition(currentArmStage.grabboxPos);
        arm.update();
        grabbox.update();
        pixelClamp.update();

        droneLauncher.update();

//        hangRelease.update(); //Added hangRelease update. By Cole
        leftHangRelease.update();
        rightHangRelease.update();

        return this;
    }

    public void setLiftPosition(int position) {
        if (lift.getMode() == Motor.Mode.POWER) lift.setMode(Motor.Mode.POSITION);

        lift.setTargetPosition(position);
    }

    public void setLiftPower(double power) {
        if (lift.getMode() == Motor.Mode.POSITION) lift.setMode(Motor.Mode.POWER);

        lift.setTargetPower(power);
    }

    public boolean atLiftPosition(int position) {
        return lift.atTargetPosition(0) && lift.atTargetPosition(1) && lift.getTargetPosition() == position;
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
        telemetry.addData("Released", rightHangRelease.getTargetPosition() == 0.3);
        telemetry.addLine();

        telemetry.addLine("-----Battery Voltage-----");
        telemetry.addData("Voltage", batteryVoltageSensor.getBatteryVoltage());

        telemetry.addLine();
        return this;
    }
}
