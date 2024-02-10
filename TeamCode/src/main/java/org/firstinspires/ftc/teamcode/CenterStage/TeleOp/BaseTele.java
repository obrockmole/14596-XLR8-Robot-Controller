package org.firstinspires.ftc.teamcode.CenterStage.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.CenterStage.Robot;
import org.firstinspires.ftc.teamcode.CenterStage.StateMachines;
import org.firstinspires.ftc.teamcode.Systems.Gamepad.Gamepad;
import org.firstinspires.ftc.teamcode.Systems.Motors.Motor;

public class BaseTele extends OpMode {
    protected Gamepad driver;
    protected Gamepad manipulator;

    protected Robot robot;
    protected StateMachines stateMachines;

    public void init() {
        driver = new Gamepad(gamepad1);
        driver.setLEDColor(130, 0, 130, -1);
        manipulator = new Gamepad(gamepad2);
        manipulator.setLEDColor(255, 255, 0, -1);

        robot = new Robot(hardwareMap);
        robot.initialize();

        stateMachines = new StateMachines(robot);
    }

    public void start() {}

    public void loop() {}

    public void update() {
        driver.update();
        manipulator.update();

        robot.update(true);
//        stateMachines.update();

        if (robot.pixelClamp.getTargetPosition() == 1) manipulator.rumble(0.5, 0.5, -1);
        else if (manipulator.isRumbling()) manipulator.stopRumble();
    }

    public void log() {
        robot.log(telemetry, false, false);

        telemetry.addLine("-----Other-----");
        telemetry.addData("Runtime", getRuntime());
        telemetry.update();
    }

    public void setLiftPosition(int position) {
        if (position == robot.liftPositions[0])
            stateMachines.liftRetraction.start();
        else if (robot.lift.getCurrentPosition() > robot.liftPositions[1] / 2)
            robot.lift.setTargetPosition(position);
        else
            stateMachines.fullLiftDeployment.start();
    }

    public void setLiftPower(double power) {
        if (robot.lift.getMode() == Motor.Mode.POSITION) robot.lift.setMode(Motor.Mode.POWER);

        if (stateMachines.halfLiftDeployment.isRunning()) stateMachines.halfLiftDeployment.stop();
        if (stateMachines.fullLiftDeployment.isRunning()) stateMachines.fullLiftDeployment.stop();
        if (stateMachines.liftRetraction.isRunning()) stateMachines.liftRetraction.stop();

        robot.lift.setTargetPower(power);
    }
}
