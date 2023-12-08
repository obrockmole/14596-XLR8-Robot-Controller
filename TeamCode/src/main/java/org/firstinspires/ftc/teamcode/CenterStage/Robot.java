package org.firstinspires.ftc.teamcode.CenterStage;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot.LogoFacingDirection;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot.UsbFacingDirection;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU.Parameters;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Systems.Drivetrain;
import org.firstinspires.ftc.teamcode.Systems.Motors.Motor;
import org.firstinspires.ftc.teamcode.Systems.Motors.MotorGroup;
import org.firstinspires.ftc.teamcode.Systems.Motors.MotorList;
import org.firstinspires.ftc.teamcode.Systems.Odometry.Odometry;
import org.firstinspires.ftc.teamcode.Systems.Odometry.OdometryPod;
import org.firstinspires.ftc.teamcode.Systems.Sensors.BatteryVoltageSensor;
import org.firstinspires.ftc.teamcode.Systems.Sensors.Encoder;
import org.firstinspires.ftc.teamcode.Systems.Sensors.IMU;
import org.firstinspires.ftc.teamcode.Systems.Servos.ContinuousServo;
import org.firstinspires.ftc.teamcode.Systems.Servos.ContinuousServoGroup;
import org.firstinspires.ftc.teamcode.Systems.Servos.PositionServo;
import org.firstinspires.ftc.teamcode.Systems.Servos.PositionServoGroup;

public class Robot extends Drivetrain {
//    public final Motor intake, lift;
    public final Motor intake;
//    public final MotorGroup liftAngle;

//    public final PositionServo droneLauncher, droneAngle;
//    public final PositionServoGroup intakeGate, pixelDepositAngle, pixelDepositSlide, winch;

//    public final ContinuousServoGroup winchExtension;

    private final BatteryVoltageSensor batteryVoltageSensor;

    public int[] liftPositions = {0, 100, 500, 800};
    public int[] liftAngles = {0, 50, 120, 200};

    public Robot(HardwareMap hardwareMap) {
        super(new Motor(hardwareMap, "frontLeft", MotorList.GOBILDA_435, Motor.Mode.POWER, false),
                new Motor(hardwareMap, "backLeft", MotorList.GOBILDA_435, Motor.Mode.POWER, false),
                new Motor(hardwareMap, "frontRight", MotorList.GOBILDA_435, Motor.Mode.POWER, false),
                new Motor(hardwareMap, "backRight", MotorList.GOBILDA_435, Motor.Mode.POWER, false),

                new Odometry(
                        new OdometryPod(new Encoder(hardwareMap, "frontLeft", Encoder.Direction.FORWARD)),
                        new OdometryPod(new Encoder(hardwareMap, "backRight", Encoder.Direction.FORWARD)),
                        new OdometryPod(new Encoder(hardwareMap, "intake", Encoder.Direction.FORWARD))
                ),

                new IMU(hardwareMap, "imu", new Parameters(new RevHubOrientationOnRobot(LogoFacingDirection.UP, UsbFacingDirection.FORWARD)))
       );

        intake = new Motor(hardwareMap, "intake", MotorList.GOBILDA_435, Motor.Mode.POWER, false);
        /*lift = new Motor(hardwareMap, "lift", MotorList.REV_PLAN_25, Motor.Mode.POSITION, false);

        liftAngle = new MotorGroup(
                new Motor(hardwareMap, "liftAngleLeft", MotorList.GOBILDA_1620, Motor.Mode.POSITION, false),
                new Motor(hardwareMap, "liftAngleLeft", MotorList.GOBILDA_1620, Motor.Mode.POSITION, false)
        );

        droneLauncher = new PositionServo(hardwareMap, "droneLauncher", 0, 1, false);
        droneAngle = new PositionServo(hardwareMap, "droneAngle", 0, 1, false);

        intakeGate = new PositionServoGroup(
                new PositionServo(hardwareMap, "intakeGateLeft", 0, 1, false),
                new PositionServo(hardwareMap, "intakeGateRight", 0, 1, true)
        );
        pixelDepositAngle = new PositionServoGroup(
                new PositionServo(hardwareMap, "depositAngleLeft", 0, 1, false),
                new PositionServo(hardwareMap, "depositAngleRight", 0, 1, true)
        );
        pixelDepositSlide = new PositionServoGroup(
                new PositionServo(hardwareMap, "depositSlideLeft", 0, 1, false),
                new PositionServo(hardwareMap, "depositSlideRight", 0, 1, true)
        );
        winch = new PositionServoGroup(
                new PositionServo(hardwareMap, "winchLeft", 0, 1, false),
                new PositionServo(hardwareMap, "winchRight", 0, 1, false)
        );

        winchExtension = new ContinuousServoGroup(
                new ContinuousServo(hardwareMap, "winchExtensionLeft", false),
                new ContinuousServo(hardwareMap, "winchExtensionRight", false)
        );*/

        batteryVoltageSensor = new BatteryVoltageSensor(hardwareMap);
    }

    public Robot setSpeedScale(boolean slow, boolean fast) {
        if (slow == fast) setSpeedScale(0.8);
        else if (slow) setSpeedScale(0.5);
        else setSpeedScale(1);
        return this;
    }

    /*public Robot setLiftPower(double power) {
        if (lift.getMode() != Motor.Mode.POWER && power > lift.getPower()) lift.setMode(Motor.Mode.POWER);
        lift.setTargetPower(power);
        return this;
    }

    public Robot setLiftPosition(int position) {
        if (lift.getMode() != Motor.Mode.POSITION) lift.setMode(Motor.Mode.POSITION);
        lift.setTargetPosition(liftPositions[position]);
        return this;
    }

    public Robot setLiftAnglePower(double power) {
        if (liftAngle.getMode() != Motor.Mode.POWER && power > liftAngle.getPower()) liftAngle.setMode(Motor.Mode.POWER);
        liftAngle.setTargetPower(power);
        return this;
    }

    public Robot setLiftAnglePosition(int position) {
        if (liftAngle.getMode() != Motor.Mode.POSITION) liftAngle.setMode(Motor.Mode.POSITION);
        liftAngle.setTargetPosition(liftAngles[position]);
        return this;
    }*/

    public Robot update() {
        super.update();

        intake.update();
        /*lift.update();

        liftAngle.update();

        droneLauncher.update();
        droneAngle.update();

        intakeGate.update();
        pixelDepositAngle.update();
        pixelDepositSlide.update();
        winch.update();

        winchExtension.update();*/

        return this;
    }

    public Robot log(Telemetry telemetry) {
        super.log(telemetry);

        telemetry.addLine("-----Intake-----");
        telemetry.addData("Mode", intake.getMode());
        telemetry.addData("Power", intake.getPower());
        telemetry.addLine();

        telemetry.addLine("-----Battery Voltage-----");
        telemetry.addData("Voltage", batteryVoltageSensor.getBatteryVoltage());

        telemetry.addLine();
        return this;
    }
}
