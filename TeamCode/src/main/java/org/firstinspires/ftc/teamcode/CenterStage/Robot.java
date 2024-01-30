package org.firstinspires.ftc.teamcode.CenterStage;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot.LogoFacingDirection;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot.UsbFacingDirection;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU.Parameters;
import com.sfdev.assembly.state.StateMachine;
import com.sfdev.assembly.state.StateMachineBuilder;
import com.sfdev.assembly.transition.TransitionCondition;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Systems.Drivetrain;
import org.firstinspires.ftc.teamcode.Systems.Motors.Motor;
import org.firstinspires.ftc.teamcode.Systems.Motors.MotorList;
import org.firstinspires.ftc.teamcode.Systems.Odometry.Odometry;
import org.firstinspires.ftc.teamcode.Systems.Odometry.OdometryPod;
import org.firstinspires.ftc.teamcode.Systems.Sensors.BatteryVoltageSensor;
import org.firstinspires.ftc.teamcode.Systems.Sensors.Encoder;
import org.firstinspires.ftc.teamcode.Systems.Sensors.IMU;
import org.firstinspires.ftc.teamcode.Systems.Servos.PositionServo;
import org.firstinspires.ftc.teamcode.Systems.Servos.PositionServoGroup;

public class Robot extends Drivetrain {
    public final Motor lift;
    public final Motor intake;

    public final PositionServoGroup grabbox;
    public final PositionServoGroup arm;
    public final PositionServoGroup pixelClamp;
    public final PositionServoGroup intakeFlippers;
    public final PositionServoGroup hangRelease; //Added in By Cole

    public final PositionServo droneLauncher;

    private enum LiftDeploymentStages {
        IDLE,
        FOLD_GRAB_BOX,
        EXTEND_LIFT,
        DEPLOY_GRAB_BOX
    }

    private enum LiftRetractionStages {
        IDLE,
        FOLD_GRAB_BOX,
        RETRACT_LIFT,
        RELEASE_GRAB_BOX
    }

    public enum ArmStages {
        IDLE(0.14, 0.06),
        FOLDED(0, 0.5),
        HALF_DEPLOYED(0.5, 0.57),
        DEPLOYED(1, 0.64);

        private final double armPos, grabboxPos;

        ArmStages(double armPos, double grabboxPos) {
            this.armPos = armPos;
            this.grabboxPos = grabboxPos;
        }
    }

    public ArmStages currentArmStage = ArmStages.IDLE;

    public final StateMachine liftDeployment;
    public final StateMachine liftRetraction;
    private LiftDeploymentStages previousLiftDeploymentState = null;
    private LiftRetractionStages previousLiftRetractionState = null;

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
                        new OdometryPod(new Encoder(hardwareMap, "intake", Encoder.Direction.FORWARD))
                ),

                new IMU(hardwareMap, "imu", new Parameters(new RevHubOrientationOnRobot(LogoFacingDirection.UP, UsbFacingDirection.FORWARD)))
       );

        lift = new Motor(hardwareMap, "lift", MotorList.REV_PLAN_75, Motor.Mode.POWER, true);
//        lift.setPIDF(0.01, 0, 0, 0);

        intake = new Motor(hardwareMap, "intake", MotorList.GOBILDA_435, Motor.Mode.POWER, true);

        arm = new PositionServoGroup(
                new PositionServo(hardwareMap, "leftArm", 0.0, 0.7, false),
                new PositionServo(hardwareMap, "rightArm", 0.3, 1, true)
        );

        grabbox = new PositionServoGroup(
                new PositionServo(hardwareMap, "leftGrabbox", 0, 0.4, true),
                new PositionServo(hardwareMap, "rightGrabbox", 0.6, 1, false)
        );

        pixelClamp = new PositionServoGroup(
                new PositionServo(hardwareMap, "leftPixelClamp", 0.14, 0.28, false),
                new PositionServo(hardwareMap, "rightPixelClamp", 0.06, 0.22, false)
        );

        intakeFlippers = new PositionServoGroup(
                new PositionServo(hardwareMap, "leftIntakeFlipper", 0.14, 0.35, false),
                new PositionServo(hardwareMap, "rightIntakeFlipper", 0.12, 0.35, true)
        );

        hangRelease = new PositionServoGroup(
                new PositionServo(hardwareMap, "leftHangRelease", 0.29, 0.45, false),
                new PositionServo(hardwareMap, "rightHangRelease", 0.29, 0.45, true)
        ); //Added hang release servo group. By Cole

        droneLauncher = new PositionServo(hardwareMap, "droneLauncher", 0.15, 0.3, false);

        batteryVoltageSensor = new BatteryVoltageSensor(hardwareMap);


        liftDeployment = new StateMachineBuilder()
                .state(LiftDeploymentStages.FOLD_GRAB_BOX)
                .onEnter(() -> {
                    currentArmStage = ArmStages.FOLDED;
                })
                .transition(() -> (grabbox.getPosition() == ArmStages.FOLDED.grabboxPos && arm.getPosition() == ArmStages.FOLDED.armPos), LiftDeploymentStages.EXTEND_LIFT)

                .state(LiftDeploymentStages.EXTEND_LIFT)
                .onEnter(() -> {
                    lift.setTargetPosition(liftPositions[1]);
                })
                .transition(lift::atTargetPosition, LiftDeploymentStages.DEPLOY_GRAB_BOX)

                .state(LiftDeploymentStages.DEPLOY_GRAB_BOX)
                .onEnter(() -> {
                    currentArmStage = ArmStages.DEPLOYED;
                })
                .transition(() -> (grabbox.getPosition() == ArmStages.DEPLOYED.grabboxPos && arm.getPosition() == ArmStages.DEPLOYED.armPos), LiftDeploymentStages.IDLE)

                .state(LiftDeploymentStages.IDLE)
                .build();

        liftRetraction = new StateMachineBuilder()
                .state(LiftRetractionStages.FOLD_GRAB_BOX)
                .onEnter(() -> {
                    if (lift.getCurrentPosition() <= liftPositions[1] && lift.getCurrentPosition() >= liftPositions[1] / 2) lift.setTargetPosition(liftPositions[1]);
                    currentArmStage = ArmStages.FOLDED;
                })
                .transition(() -> (grabbox.getPosition() == ArmStages.FOLDED.grabboxPos && arm.getPosition() == ArmStages.FOLDED.armPos /* and lift is high enough*/), LiftRetractionStages.RETRACT_LIFT)

                .state(LiftRetractionStages.RETRACT_LIFT)
                .onEnter(() -> {
                    lift.setTargetPosition(liftPositions[0]);
                })
                .transition(lift::atTargetPosition, LiftRetractionStages.RELEASE_GRAB_BOX)

                .state(LiftRetractionStages.RELEASE_GRAB_BOX)
                .onEnter(() -> {
                    currentArmStage = ArmStages.IDLE;
                })
                .transition(() -> (grabbox.getPosition() == ArmStages.IDLE.grabboxPos && arm.getPosition() == ArmStages.IDLE.armPos), LiftRetractionStages.IDLE)

                .state(LiftRetractionStages.IDLE)
                .build();
    }

    public void setLiftPosition(int position) {
        if (!liftDeployment.isRunning() && !liftRetraction.isRunning()) {
            if (position == liftPositions[0])
                liftRetraction.start();
            else if (lift.getCurrentPosition() > liftPositions[1] / 2)
                lift.setTargetPosition(position);
            else
                liftDeployment.start();
        }
    }

    public Robot initialize() {
//        lift.setTargetPosition(0);
        currentArmStage = ArmStages.IDLE;
        pixelClamp.setTargetPosition(0);
        intakeFlippers.setTargetPosition(0);
        droneLauncher.setTargetPosition(0);
        hangRelease.setTargetPosition(0); //Added in by Cole

        update(false);

        return this;
    }

    public Robot update(boolean updateDriveMotors) {
        if (updateDriveMotors) super.update();

        lift.update();
//        if (lift.getPower() < 0) lift.setSpeedScale(0.2);
//        else lift.setSpeedScale(1);

        intake.update();
        intakeFlippers.update();

        if (arm.getTargetPosition() != currentArmStage.armPos) arm.setTargetPosition(currentArmStage.armPos);
        if (grabbox.getTargetPosition() != currentArmStage.grabboxPos) grabbox.setTargetPosition(currentArmStage.grabboxPos);
        arm.update();
        grabbox.update();
        pixelClamp.update();

        droneLauncher.update();

        hangRelease.update(); //Added hangRelease update. By Cole

        /*liftDeployment.update();
        liftRetraction.update();

        if (!liftDeployment.isRunning() && previousLiftDeploymentState != LiftDeploymentStages.IDLE) {
            liftDeployment.reset();
            liftDeployment.stop();
        }

        if (!liftRetraction.isRunning() && previousLiftRetractionState != LiftRetractionStages.IDLE) {
            liftRetraction.reset();
            liftRetraction.stop();
        }

        previousLiftDeploymentState = (LiftDeploymentStages) liftDeployment.getState();
        previousLiftRetractionState = (LiftRetractionStages) liftRetraction.getState();*/

        return this;
    }

    public Robot log(Telemetry telemetry, boolean logOdometry, boolean logIMU) {
        super.log(telemetry, logOdometry, logIMU);

        telemetry.addLine("-----Lift-----");
        telemetry.addData("Power", lift.getPower());
//        telemetry.addData("Target Position", lift.getTargetPosition());
//        telemetry.addData("Current Position", lift.getCurrentPosition());
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

        telemetry.addLine("-----Hang Release-----");
        telemetry.addData("Released", hangRelease.getTargetPosition() == 1);
        telemetry.addLine();

        telemetry.addLine("-----Pixel Clamp-----");
        telemetry.addData("Clamped", pixelClamp.getTargetPosition() == 1);
        telemetry.addLine();

        telemetry.addLine("-----Battery Voltage-----");
        telemetry.addData("Voltage", batteryVoltageSensor.getBatteryVoltage());

        telemetry.addLine();
        return this;
    }
}
