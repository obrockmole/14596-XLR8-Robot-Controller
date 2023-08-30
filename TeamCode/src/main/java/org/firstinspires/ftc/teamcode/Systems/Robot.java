package org.firstinspires.ftc.teamcode.Systems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Systems.Motors.Motor;
import org.firstinspires.ftc.teamcode.Systems.Motors.MotorLookupTable;
import org.firstinspires.ftc.teamcode.Systems.Odometry.Odometry;
import org.firstinspires.ftc.teamcode.Systems.Odometry.OdometryPod;
import org.firstinspires.ftc.teamcode.Systems.Sensors.Encoder;
import org.firstinspires.ftc.teamcode.Systems.Servos.PositionServo;

public class Robot extends NewDrivetrain {
    public Robot(HardwareMap hardwareMap) {
        super(new Motor(hardwareMap, "frontLeft", MotorLookupTable.GOBILDA_435, Motor.Mode.POWER, 10, false),
                new Motor(hardwareMap, "backLeft", MotorLookupTable.GOBILDA_435, Motor.Mode.POWER, 10, false),
                new Motor(hardwareMap, "frontRight", MotorLookupTable.GOBILDA_435, Motor.Mode.POWER, 10, false),
                new Motor(hardwareMap, "backRight", MotorLookupTable.GOBILDA_435, Motor.Mode.POWER, 10, false),
                new Odometry(
                        new OdometryPod(new Encoder(hardwareMap, "frontLeft", Encoder.Direction.FORWARD), new PositionServo(hardwareMap, "leftOdometryServo", 0, 1, false)),
                        new OdometryPod(new Encoder(hardwareMap, "backLeft", Encoder.Direction.FORWARD), new PositionServo(hardwareMap, "rightOdometryServo", 0, 1, false)),
                        new OdometryPod(new Encoder(hardwareMap, "frontRight", Encoder.Direction.FORWARD), new PositionServo(hardwareMap, "centerOdometryServo", 0, 1, false))
                ),
                hardwareMap.get(IMU.class, "imu")
       );
    }

    public Robot update() {
        super.update();
        return this;
    }

    public Robot log(Telemetry telemetry) {
        super.log(telemetry);
        return this;
    }
}
