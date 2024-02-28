package org.firstinspires.ftc.teamcode.CenterStage.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.CenterStage.Robot;
import org.firstinspires.ftc.teamcode.CenterStage.StateMachines;
import org.firstinspires.ftc.teamcode.Systems.BlinkinLEDDriver;
import org.firstinspires.ftc.teamcode.Systems.Gamepad.Gamepad;
import org.firstinspires.ftc.teamcode.Systems.Motors.Motor;

public class BaseTele extends OpMode {
    protected Gamepad driver;
    protected Gamepad manipulator;

    protected Robot robot;
    protected StateMachines stateMachines;

    private boolean endgameWarning = false;

    private enum Pixels {
        NONE(0, 0, 0),
        WHITE(210, 255, 255),
        YELLOW(130, 180, 40),
        GREEN(30, 100, 35),
        PURPLE(80, 110, 140);

        final int r, g, b;
        Pixels(int r, int g, int b) {
            this.r = r;
            this.g = g;
            this.b = b;
        }

        public double getDistance(int r, int g, int b) {
            int dr = this.r - r;
            int dg = this.g - g;
            int db = this.b - b;
            return Math.sqrt(dr * dr + dg * dg + db * db);
        }
    }
    private Pixels previousLeftPixel = Pixels.NONE;
    private Pixels previousRightPixel = Pixels.NONE;

    public void init() {
        driver = new Gamepad(gamepad1);
        driver.setLEDColor(130, 0, 130, -1);
        manipulator = new Gamepad(gamepad2);
        manipulator.setLEDColor(255, 255, 0, -1);

        robot = new Robot(hardwareMap);
        robot.initialize();

        stateMachines = new StateMachines(robot);
    }

    public void start() {
        robot.leftBlinkin.setPattern(BlinkinLEDDriver.Pattern.BLACK);
        robot.rightBlinkin.setPattern(BlinkinLEDDriver.Pattern.BLACK);
    }

    public void loop() {
        int[] leftColor = robot.leftPixelSensor.getRGB();
        int[] rightColor = robot.rightPixelSensor.getRGB();

        Pixels currentLeftPixel = getClosestColor(leftColor[0], leftColor[1], leftColor[2]);
        Pixels currentRightPixel = getClosestColor(rightColor[0], rightColor[1], rightColor[2]);

        if (!endgameWarning && getRuntime() >= 90 && getRuntime() <= 92) {
            endgameWarning = true;
            robot.leftBlinkin.setPattern(BlinkinLEDDriver.Pattern.RED_HEARTBEAT);
            robot.rightBlinkin.setPattern(BlinkinLEDDriver.Pattern.RED_HEARTBEAT);
        } else if (endgameWarning && getRuntime() > 93) {
            endgameWarning = false;
            updateLeftColor(currentLeftPixel);
            updateRightColor(currentRightPixel);
        } else {
            if (currentLeftPixel != previousLeftPixel)
                updateLeftColor(currentLeftPixel);

            if (currentRightPixel != previousRightPixel)
                updateRightColor(currentRightPixel);
        }

        previousLeftPixel = currentLeftPixel;
        previousRightPixel = currentRightPixel;

        double leftDistance = robot.leftPixelSensor.getDistance(DistanceUnit.MM);
        double rightDistance = robot.rightPixelSensor.getDistance(DistanceUnit.MM);

        driver.rumble(((leftDistance < 7 && leftDistance >= 0) ? 0.3 : 0), ((rightDistance < 7 && rightDistance >= 0) ? 0.3 : 0), -1);
        manipulator.rumble(((leftDistance < 7 && leftDistance >= 0) ? 0.3 : 0), ((rightDistance < 7 && rightDistance >= 0) ? 0.3 : 0), -1);

        driver.update();
        manipulator.update();

        robot.update(true);
        stateMachines.update();
    }

    /*public void update() {
        int[] leftColor = robot.leftPixelSensor.getRGB();
        int[] rightColor = robot.rightPixelSensor.getRGB();

        Pixels currentLeftPixel = getClosestColor(leftColor[0], leftColor[1], leftColor[2]);
        Pixels currentRightPixel = getClosestColor(rightColor[0], rightColor[1], rightColor[2]);

        if (!endgameWarning && getRuntime() >= 90 && getRuntime() <= 92) {
            endgameWarning = true;
            robot.leftBlinkin.setPattern(BlinkinLEDDriver.Pattern.RED_HEARTBEAT);
            robot.rightBlinkin.setPattern(BlinkinLEDDriver.Pattern.RED_HEARTBEAT);
        } else if (endgameWarning && getRuntime() > 93) {
            endgameWarning = false;
            updateLeftColor(currentLeftPixel);
            updateRightColor(currentRightPixel);
        } else {
            if (currentLeftPixel != previousLeftPixel)
                updateLeftColor(currentLeftPixel);

            if (currentRightPixel != previousRightPixel)
                updateRightColor(currentRightPixel);
        }

        previousLeftPixel = currentLeftPixel;
        previousRightPixel = currentRightPixel;

        double leftDistance = robot.leftPixelSensor.getDistance(DistanceUnit.MM);
        double rightDistance = robot.rightPixelSensor.getDistance(DistanceUnit.MM);

        driver.rumble(((leftDistance < 7 && leftDistance >= 0) ? 0.3 : 0), ((rightDistance < 7 && rightDistance >= 0) ? 0.3 : 0), -1);
        manipulator.rumble(((leftDistance < 7 && leftDistance >= 0) ? 0.3 : 0), ((rightDistance < 7 && rightDistance >= 0) ? 0.3 : 0), -1);

        driver.update();
        manipulator.update();

        robot.update(true);
        stateMachines.update();
    }*/

    public void stop() {
        robot.leftBlinkin.setPattern(BlinkinLEDDriver.Pattern.OCEAN_PALETTE_WAVES);
        robot.rightBlinkin.setPattern(BlinkinLEDDriver.Pattern.OCEAN_PALETTE_WAVES);
    }

    public void log() {
        robot.log(telemetry, false, false);

        telemetry.addLine("-----Other-----");
        telemetry.addData("Runtime", getRuntime());
        telemetry.update();
    }

    private Pixels getClosestColor(int r, int g, int b) {
        Pixels closestColor = Pixels.NONE;
        double minDistance = Double.MAX_VALUE;
        for (Pixels pixel : Pixels.values()) {
            double distance = pixel.getDistance(r, g, b);
            if (distance < minDistance) {
                minDistance = distance;
                closestColor = pixel;
            }
        }
        return closestColor;
    }

    private void updateLeftColor(Pixels pixel) {
        switch (pixel) {
            case NONE:
                robot.leftBlinkin.setPattern(BlinkinLEDDriver.Pattern.BLACK);
                break;
            case WHITE:
                robot.leftBlinkin.setPattern(BlinkinLEDDriver.Pattern.WHITE);
                break;
            case YELLOW:
                robot.leftBlinkin.setPattern(BlinkinLEDDriver.Pattern.YELLOW);
                break;
            case GREEN:
                robot.leftBlinkin.setPattern(BlinkinLEDDriver.Pattern.GREEN);
                break;
            case PURPLE:
                robot.leftBlinkin.setPattern(BlinkinLEDDriver.Pattern.PURPLE);
                break;
        }
    }

    private void updateRightColor(Pixels pixel) {
        switch (pixel) {
            case NONE:
                robot.rightBlinkin.setPattern(BlinkinLEDDriver.Pattern.BLACK);
                break;
            case WHITE:
                robot.rightBlinkin.setPattern(BlinkinLEDDriver.Pattern.WHITE);
                break;
            case YELLOW:
                robot.rightBlinkin.setPattern(BlinkinLEDDriver.Pattern.YELLOW);
                break;
            case GREEN:
                robot.rightBlinkin.setPattern(BlinkinLEDDriver.Pattern.GREEN);
                break;
            case PURPLE:
                robot.rightBlinkin.setPattern(BlinkinLEDDriver.Pattern.PURPLE);
                break;
        }
    }
}
