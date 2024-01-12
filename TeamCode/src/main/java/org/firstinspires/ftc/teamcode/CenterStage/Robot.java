package org.firstinspires.ftc.teamcode.CenterStage;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot.LogoFacingDirection;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot.UsbFacingDirection;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU.Parameters;
import com.sfdev.assembly.state.StateMachine;
import com.sfdev.assembly.state.StateMachineBuilder;

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
    //    public final Motor lift;
    public final Motor intake;

    public final PositionServoGroup grabbox;
    public final PositionServoGroup arm;
//    public final PositionServoGroup pixelClamp;
    public final PositionServoGroup intakeFlippers;
    public final PositionServoGroup hangRelease; //Added in By Cole

    public final PositionServo droneLauncher;

    enum LiftDeploymentStages {
        IDLE,
        FOLD_GRAB_BOX,
        EXTEND_LIFT,
        DEPLOY_GRAB_BOX
    }

    enum LiftRetractionStages {
        IDLE,
        FOLD_GRAB_BOX,
        RETRACT_LIFT,
        RELEASE_GRAB_BOX
    }

    enum ArmStages {
        IDLE(0.13, 0.06),
        FOLDED(0, 0.5),
        DEPLOYED(1, 0.62);

        double armPos, grabboxPos;

        ArmStages(double armPos, double grabboxPos) {
            this.armPos = armPos;
            this.grabboxPos = grabboxPos;
        }
    }

    public final StateMachine liftDeployment;
    public final StateMachine liftRetraction;
    private boolean liftMoving = false;
    private LiftDeploymentStages previousLiftDeploymentState = null;
    private LiftRetractionStages previousLiftRetractionState = null;
    private ArmStages currentArmStage = ArmStages.IDLE;

    private final BatteryVoltageSensor batteryVoltageSensor;

    public final int[] liftPositions = {0, 100, 500, 1000};

    public Robot(HardwareMap hardwareMap) {
        super(new Motor(hardwareMap, "frontLeft", MotorList.GOBILDA_435, Motor.Mode.POWER, false),
                new Motor(hardwareMap, "backLeft", MotorList.GOBILDA_435, Motor.Mode.POWER, false),
                new Motor(hardwareMap, "frontRight", MotorList.GOBILDA_435, Motor.Mode.POWER, false),
                new Motor(hardwareMap, "backRight", MotorList.GOBILDA_435, Motor.Mode.POWER, false),

                new Odometry(
                        new OdometryPod(new Encoder(hardwareMap, "frontLeft", Encoder.Direction.FORWARD)),
                        new OdometryPod(new Encoder(hardwareMap, "frontRight", Encoder.Direction.FORWARD)),
                        new OdometryPod(new Encoder(hardwareMap, "intake", Encoder.Direction.FORWARD))
                ),

                new IMU(hardwareMap, "imu", new Parameters(new RevHubOrientationOnRobot(LogoFacingDirection.UP, UsbFacingDirection.FORWARD)))
       );

//        lift = new Motor(hardwareMap, "lift", MotorList.REV_PLAN_25, Motor.Mode.POSITION, true);
//        lift.setPIDF(0, 0, 0, 0);

        intake = new Motor(hardwareMap, "intake", MotorList.GOBILDA_435, Motor.Mode.POWER, true);

        arm = new PositionServoGroup(
                new PositionServo(hardwareMap, "leftArm", 0.0, 0.7, false),
                new PositionServo(hardwareMap, "rightArm", 0.3, 1, true)
        );

        grabbox = new PositionServoGroup(
                new PositionServo(hardwareMap, "leftGrabbox", 0, 0.4, true),
                new PositionServo(hardwareMap, "rightGrabbox", 0.6, 1, false)
        );

        /*pixelClamp = new PositionServoGroup(
                new PositionServo(hardwareMap, "leftPixelClamp", 0.14, 0.18, false),
                new PositionServo(hardwareMap, "rightPixelClamp", 0.06, 0.1, false)
        );*/

        intakeFlippers = new PositionServoGroup(
                new PositionServo(hardwareMap, "leftIntakeFlipper", 0.14, 0.35, false),
                new PositionServo(hardwareMap, "rightIntakeFlipper", 0.12, 0.35, true)
        );

        hangRelease = new PositionServoGroup(
                new PositionServo(hardwareMap, "leftHangRelease", 0.3, 0.45, false),
                new PositionServo(hardwareMap, "rightHangRelease", 0.3, 0.45, true)
        ); //Added hang release servo group. By Cole

        droneLauncher = new PositionServo(hardwareMap, "droneLauncher", 0.15, 0.3, false);

        batteryVoltageSensor = new BatteryVoltageSensor(hardwareMap);


        liftDeployment = new StateMachineBuilder()
                .state(LiftDeploymentStages.FOLD_GRAB_BOX)
                .onEnter(() -> {
                    liftMoving = true;
                    grabbox.setTargetPosition(ArmStages.FOLDED.grabboxPos);
                    arm.setTargetPosition(ArmStages.FOLDED.armPos);
                })
                .transition(() -> (grabbox.getPosition() == ArmStages.FOLDED.grabboxPos && arm.getPosition() == ArmStages.FOLDED.armPos), LiftDeploymentStages.EXTEND_LIFT)

                .state(LiftDeploymentStages.EXTEND_LIFT)
                .onEnter(() -> {
                    //lift.setTargetPosition(liftPositions[1]);
                })
                .transition(() -> true/* lift is extended */, LiftDeploymentStages.DEPLOY_GRAB_BOX)

                .state(LiftDeploymentStages.DEPLOY_GRAB_BOX)
                .onEnter(() -> {
                    grabbox.setTargetPosition(ArmStages.DEPLOYED.grabboxPos);
                    arm.setTargetPosition(ArmStages.DEPLOYED.armPos);
                })
                .transition(() -> (grabbox.getPosition() == ArmStages.DEPLOYED.grabboxPos && arm.getPosition() == ArmStages.DEPLOYED.armPos), LiftDeploymentStages.IDLE)

                .state(LiftDeploymentStages.IDLE)
                .onEnter(() -> liftMoving = false)
                .build();

        liftRetraction = new StateMachineBuilder()
                .state(LiftRetractionStages.FOLD_GRAB_BOX)
                .onEnter(() -> {
                    liftMoving = true;
                    //if (lift.getCurrentPosition() < liftPositions[1] && lift.getTargetPosition() != liftPositions[0]) lift.setTargetPosition(liftPositions[1]);
                    grabbox.setTargetPosition(ArmStages.FOLDED.grabboxPos);
                    arm.setTargetPosition(ArmStages.FOLDED.armPos);
                })
                .transition(() -> (grabbox.getPosition() == ArmStages.FOLDED.grabboxPos && arm.getPosition() == ArmStages.FOLDED.armPos /* and lift is high enough*/), LiftRetractionStages.RETRACT_LIFT)

                .state(LiftRetractionStages.RETRACT_LIFT)
                .onEnter(() -> {
                    //lift.setTargetPosition(liftPositions[0]);
                })
                .transition(() -> true/* lift is retracted */, LiftRetractionStages.RELEASE_GRAB_BOX)

                .state(LiftRetractionStages.RELEASE_GRAB_BOX)
                .onEnter(() -> {
                    grabbox.setTargetPosition(ArmStages.IDLE.grabboxPos);
                    arm.setTargetPosition(ArmStages.IDLE.armPos);
                })
                .transition(() -> (grabbox.getPosition() == ArmStages.IDLE.grabboxPos && arm.getPosition() == ArmStages.IDLE.armPos), LiftRetractionStages.IDLE)

                .state(LiftRetractionStages.IDLE)
                .onEnter(() -> liftMoving = false)
                .build();
    }

    public void setLiftPosition(int position) {
        if (position == liftPositions[0] && !liftMoving) {
            liftRetraction.start();
            return;
        }

//        if (liftMoving) {
//            if (lift.getCurrentPosition() > liftPositions[1] / 2)
//                lift.setTargetPosition(position);
//            else
//                liftDeployment.start();
//        }
    }

    public Robot initialize() {
//        lift.setTargetPosition(0);
        arm.setTargetPosition(ArmStages.IDLE.armPos);
        grabbox.setTargetPosition(ArmStages.IDLE.grabboxPos);
//        pixelClamp.setTargetPosition(0);
        intakeFlippers.setTargetPosition(0);
        droneLauncher.setTargetPosition(0);
        hangRelease.setTargetPosition(0); //Added in by Cole

        update(false);

        return this;
    }

    public Robot update(boolean updateDriveMotors) {
        if (updateDriveMotors) super.update();

//        lift.update();

        intake.update();
        intakeFlippers.update();

        arm.update();
        grabbox.update();
//        pixelClamp.update();

        droneLauncher.update();

        hangRelease.update(); //Added hangRelease update. By Cole

//        liftDeployment.update();
//        liftRetraction.update();
//
//        if (!liftMoving && previousLiftDeploymentState != LiftDeploymentStages.IDLE) {
//            liftDeployment.reset();
//            liftDeployment.stop();
//        }
//
//        if (!liftMoving && previousLiftRetractionState != LiftRetractionStages.IDLE) {
//            liftRetraction.reset();
//            liftRetraction.stop();
//        }
//
//        previousLiftDeploymentState = (LiftDeploymentStages) liftDeployment.getState();
//        previousLiftRetractionState = (LiftRetractionStages) liftRetraction.getState();

        return this;
    }

    public Robot log(Telemetry telemetry, boolean logOdometry, boolean logIMU) {
        super.log(telemetry, logOdometry, logIMU);

//        telemetry.addLine("-----Lift-----");
//        telemetry.addData("Power", lift.getPower());
//        telemetry.addData("Target Position", lift.getTargetPosition());
//        telemetry.addData("Position", lift.getCurrentPosition());
//        telemetry.addLine();

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

//        telemetry.addLine("-----Pixel Clamp-----");
//        telemetry.addData("Clamped", pixelClamp.getTargetPosition() == 1);
//        telemetry.addLine();

        telemetry.addLine("-----Battery Voltage-----");
        telemetry.addData("Voltage", batteryVoltageSensor.getBatteryVoltage());

        telemetry.addLine();
        return this;
    }
}
