package org.firstinspires.ftc.teamcode.Systems;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot.LogoFacingDirection;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot.UsbFacingDirection;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU.Parameters;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Systems.Motors.Motor;
import org.firstinspires.ftc.teamcode.Systems.Motors.MotorLookupTable;
import org.firstinspires.ftc.teamcode.Systems.Odometry.Odometry;
import org.firstinspires.ftc.teamcode.Systems.Odometry.OdometryPod;
import org.firstinspires.ftc.teamcode.Systems.Sensors.BatteryVoltageSensor;
import org.firstinspires.ftc.teamcode.Systems.Sensors.Encoder;
import org.firstinspires.ftc.teamcode.Systems.Sensors.IMU;
import org.firstinspires.ftc.teamcode.Systems.Servos.PositionServo;

public class Robot extends Drivetrain {
    private Motor intake, lift;
    private Motor leftHanger, rightHanger;

    private PositionServo deliveryPivot, deliveryRelease;

    private BatteryVoltageSensor batteryVoltageSensor;

    public Robot(HardwareMap hardwareMap) {
        super(new Motor(hardwareMap, "frontLeft", MotorLookupTable.GOBILDA_435, Motor.Mode.POWER, false),
                new Motor(hardwareMap, "backLeft", MotorLookupTable.GOBILDA_435, Motor.Mode.POWER, false),
                new Motor(hardwareMap, "frontRight", MotorLookupTable.GOBILDA_435, Motor.Mode.POWER, false),
                new Motor(hardwareMap, "backRight", MotorLookupTable.GOBILDA_435, Motor.Mode.POWER, false),
                new Odometry(
                        new OdometryPod(new Encoder(hardwareMap, "frontLeft", Encoder.Direction.FORWARD)),
                        new OdometryPod(new Encoder(hardwareMap, "backRight", Encoder.Direction.FORWARD)),
                        new OdometryPod(new Encoder(hardwareMap, "perpOdom", Encoder.Direction.FORWARD))
                ),
                new IMU(hardwareMap, "imu", new Parameters(new RevHubOrientationOnRobot(LogoFacingDirection.UP, UsbFacingDirection.FORWARD)))
       );

        //intake = new Motor(hardwareMap, "intake", MotorLookupTable.GOBILDA_6000, Motor.Mode.POWER, false);
        //lift = new Motor(hardwareMap, "lift", MotorLookupTable.GOBILDA_1150, Motor.Mode.POSITION, 10, false);
        //leftHanger = new Motor(hardwareMap, "leftHanger", MotorLookupTable.GOBILDA_6000, Motor.Mode.POSITION, 10,  false);
        //leftHanger.setPIDF(0.05, 0, 0.001, 0.4);
        //rightHanger = new Motor(hardwareMap, "rightHanger", MotorLookupTable.GOBILDA_6000, Motor.Mode.POSITION, 10, false);

        batteryVoltageSensor = new BatteryVoltageSensor(hardwareMap);
    }

    public Robot update() {
        super.update();

        //intake.update();
        //lift.update();
        //leftHanger.update();
        //backHanger.update();
        return this;
    }

    public Robot log(Telemetry telemetry) {
        super.log(telemetry);

        telemetry.addLine("-----Battery Voltage-----");
        telemetry.addData("Voltage", batteryVoltageSensor.getBatteryVoltage());

        telemetry.addLine();
        return this;
    }
}
