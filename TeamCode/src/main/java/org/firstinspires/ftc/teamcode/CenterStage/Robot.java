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
import org.firstinspires.ftc.teamcode.Systems.Gamepad.GamepadButtons;
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

//    public final PositionServoGroup grabbox;
//    public final PositionServoGroup arm;
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

    public final StateMachine liftDeployment;
    public final StateMachine liftRetraction;

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

        liftDeployment = new StateMachineBuilder()
                .state(LiftDeploymentStages.FOLD_GRAB_BOX)
                    .onEnter(() -> {
                        //grabbox.setTargetPosition(0);
                        //arm.setTargetPosition(0);
                    })
                    .transition(() -> true/* grab box is folded */, LiftDeploymentStages.EXTEND_LIFT)

                .state(LiftDeploymentStages.EXTEND_LIFT)
                    .onEnter(() -> {
                        //lift.setTargetPosition(liftPositions[1]);
                    })
                    .transition(() -> true/* lift is extended */, LiftDeploymentStages.DEPLOY_GRAB_BOX)

                .state(LiftDeploymentStages.DEPLOY_GRAB_BOX)
                    .onEnter(() -> {
                        //grabbox.setTargetPosition(1);
                        //arm.setTargetPosition(1);
                    })
                    .transition(() -> true/* grab box is deployed */, LiftDeploymentStages.IDLE)
                .build();

        liftRetraction = new StateMachineBuilder()
                .state(LiftRetractionStages.FOLD_GRAB_BOX)
                    .onEnter(() -> {
                        //if (lift.getCurrentPosition() < liftPositions[1] && lift.getTargetPosition() != liftPositions[0]) lift.setTargetPosition(liftPositions[1]);
                        //grabbox.setTargetPosition(0);
                        //arm.setTargetPosition(0);
                    })
                    .transition(() -> true/* grab box is folded and lift is high enough*/, LiftRetractionStages.RETRACT_LIFT)

                .state(LiftRetractionStages.RETRACT_LIFT)
                    .onEnter(() -> {
                        //lift.setTargetPosition(liftPositions[0]);
                    })
                    .transition(() -> true/* lift is retracted */, LiftRetractionStages.RELEASE_GRAB_BOX)

                .state(LiftRetractionStages.RELEASE_GRAB_BOX)
                    .onEnter(() -> {
                        //grabbox.setTargetPosition(0.2);
                        //arm.setTargetPosition(0.2);
                    })
                    .transition(() -> true/* grab box is released */, LiftRetractionStages.IDLE)
                .build();

//        lift = new Motor(hardwareMap, "lift", MotorList.REV_PLAN_25, Motor.Mode.POSITION, true);

        intake = new Motor(hardwareMap, "intake", MotorList.GOBILDA_435, Motor.Mode.POWER, true);

//        grabbox = new PositionServoGroup( //TODO: get bounds
//                new PositionServo(hardwareMap, "leftGrabbox", 0.0, 0.5, false),
//                new PositionServo(hardwareMap, "rightGrabbox", 0.0, 0.5, true)
//        );
//
//        arm = new PositionServoGroup( //TODO: get bounds
//                new PositionServo(hardwareMap, "leftArm", 0.0, 0.5, false),
//                new PositionServo(hardwareMap, "rightArm", 0.0, 0.5, true)
//        );

        /*pixelClamp = new PositionServoGroup(
                new PositionServo(hardwareMap, "leftPixelClamp", 0.14, 0.18, false),
                new PositionServo(hardwareMap, "rightPixelClamp", 0.06, 0.1, false)
        );*/

        intakeFlippers = new PositionServoGroup(
                new PositionServo(hardwareMap, "leftIntakeFlipper", 0.12, 0.35, false),
                new PositionServo(hardwareMap, "rightIntakeFlipper", 0.12, 0.35, true)
        );

        hangRelease = new PositionServoGroup(
                new PositionServo(hardwareMap, "leftHangRelease", 0.3, 0.45, false),
                new PositionServo(hardwareMap, "rightHangRelease", 0.3, 0.45, true)
        ); //Added hang release servo group. By Cole

        droneLauncher = new PositionServo(hardwareMap, "droneLauncher", 0.15, 0.3, false);

        batteryVoltageSensor = new BatteryVoltageSensor(hardwareMap);
    }

    public Robot setLiftPower(double power) {
//        if (lift.getCurrentPosition() < liftPositions[0] / 2 || liftDeployment.getState() != LiftDeploymentStages.IDLE) return this;

//        if ((power > 0 && lift.getPower() < power) || (power < 0 && lift.getPower() > power)) {
//            if (lift.getMode() != Motor.Mode.POWER) lift.setMode(Motor.Mode.POWER);
//            lift.setTargetPower(power);
//        }
        return this;
    }

    public Robot setLiftPosition(int position) {
//        if (lift.getMode() != Motor.Mode.POSITION) lift.setMode(Motor.Mode.POSITION);

        if (position == liftPositions[0]) {
            liftRetraction.start();
            return this;
        }

//        if (lift.getCurrentPosition() > liftPositions[0] / 2 && liftDeployment.getState() == LiftDeploymentStages.IDLE && liftRetraction.getState() == LiftRetractionStages.IDLE) {
//            lift.setTargetPosition(position);
//        } else if (liftDeployment.getState() == LiftDeploymentStages.IDLE && liftRetraction.getState() == LiftRetractionStages.IDLE) {
//            liftDeployment.start();
//        }
        return this;
    }

    public Robot initialize() {
//        lift.setTargetPosition(0);
//        grabbox.setTargetPosition(0);
//        arm.setTargetPosition(0);
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

//        grabbox.update();
//        arm.update();
//        pixelClamp.update();

        droneLauncher.update();

        hangRelease.update(); //Added hangRelease update. By Cole

        liftDeployment.update();
        liftRetraction.update();

        if (liftDeployment.getState() == LiftDeploymentStages.IDLE) {
            liftDeployment.reset();
            liftDeployment.stop();
        }

        if (liftRetraction.getState() == LiftRetractionStages.IDLE) {
            liftRetraction.reset();
            liftRetraction.stop();
        }

        return this;
    }

    public Robot log(Telemetry telemetry, boolean logOdometry, boolean logIMU) {
        super.log(telemetry, logOdometry, logIMU);

//        telemetry.addLine("-----Lift-----");
//        telemetry.addData("Power", lift.getPower());
//        telemetry.addData("Position", lift.getCurrentPosition());
//        telemetry.addLine();

        telemetry.addLine("-----Intake-----");
        telemetry.addData("Power", intake.getPower());
        telemetry.addLine();

        telemetry.addLine("-----Intake Flippers-----");
        telemetry.addData("Deployed", intakeFlippers.getTargetPosition() == 1);
        telemetry.addLine();

//        telemetry.addLine("-----Grabbox-----");
//        telemetry.addData("Deployed", grabbox.getTargetPosition() == 1);
//        telemetry.addLine();
//
//        telemetry.addLine("-----Arm-----");
//        telemetry.addData("Deployed", arm.getTargetPosition() == 1);
//        telemetry.addLine();

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
