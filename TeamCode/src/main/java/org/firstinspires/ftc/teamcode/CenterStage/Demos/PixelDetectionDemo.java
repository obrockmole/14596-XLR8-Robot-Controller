package org.firstinspires.ftc.teamcode.CenterStage.Demos;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.CenterStage.Robot;
import org.firstinspires.ftc.teamcode.CenterStage.TeleOp.BaseTele;
import org.firstinspires.ftc.teamcode.Systems.BlinkinLEDDriver;
import org.firstinspires.ftc.teamcode.Systems.Gamepad.GamepadButtons.Button;
import org.firstinspires.ftc.teamcode.Systems.Gamepad.GamepadButtons.Stick;
import org.firstinspires.ftc.teamcode.Systems.Sensors.ColorSensor;

//@Disabled
@TeleOp(group = "Demo", name = "Pixel Detection Demo")
public class PixelDetectionDemo extends OpMode {
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

    private ColorSensor leftPixelSensor;
    private ColorSensor rightPixelSensor;

    private BlinkinLEDDriver leftBlinkin;
    private BlinkinLEDDriver rightBlinkin;

    public void init() {
        leftPixelSensor = new ColorSensor(hardwareMap, "leftPixelSensor", 4.8f);
        rightPixelSensor = new ColorSensor(hardwareMap, "rightPixelSensor", 4.8f);

        leftBlinkin = new BlinkinLEDDriver(hardwareMap, "leftBlinkin");
        rightBlinkin = new BlinkinLEDDriver(hardwareMap, "rightBlinkin");

        leftBlinkin.setPattern(BlinkinLEDDriver.Pattern.OCEAN_PALETTE_WAVES);
        rightBlinkin.setPattern(BlinkinLEDDriver.Pattern.OCEAN_PALETTE_WAVES);
    }

    public void start() {
        leftBlinkin.setPattern(BlinkinLEDDriver.Pattern.BLACK);
        rightBlinkin.setPattern(BlinkinLEDDriver.Pattern.BLACK);
    }

    public void loop() {
        int[] leftColor = leftPixelSensor.getRGB();
        int[] rightColor = rightPixelSensor.getRGB();

        Pixels currentLeftPixel = getClosestColor(leftColor[0], leftColor[1], leftColor[2]);
        Pixels currentRightPixel = getClosestColor(rightColor[0], rightColor[1], rightColor[2]);

        if (currentLeftPixel != previousLeftPixel)
            updateLeftColor(currentLeftPixel);

        if (currentRightPixel != previousRightPixel)
            updateRightColor(currentRightPixel);

        previousLeftPixel = currentLeftPixel;
        previousRightPixel = currentRightPixel;

        double leftDistance = leftPixelSensor.getDistance(DistanceUnit.MM);
        double rightDistance = rightPixelSensor.getDistance(DistanceUnit.MM);

        gamepad1.rumble(((leftDistance < 7 && leftDistance >= 0) ? 0.3 : 0), ((rightDistance < 7 && rightDistance >= 0) ? 0.3 : 0), -1);

        leftPixelSensor.update();
        rightPixelSensor.update();
    }

    public void stop() {
        leftBlinkin.setPattern(BlinkinLEDDriver.Pattern.OCEAN_PALETTE_WAVES);
        rightBlinkin.setPattern(BlinkinLEDDriver.Pattern.OCEAN_PALETTE_WAVES);
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
                leftBlinkin.setPattern(BlinkinLEDDriver.Pattern.BLACK);
                break;
            case WHITE:
                leftBlinkin.setPattern(BlinkinLEDDriver.Pattern.WHITE);
                break;
            case YELLOW:
                leftBlinkin.setPattern(BlinkinLEDDriver.Pattern.YELLOW);
                break;
            case GREEN:
                leftBlinkin.setPattern(BlinkinLEDDriver.Pattern.GREEN);
                break;
            case PURPLE:
                leftBlinkin.setPattern(BlinkinLEDDriver.Pattern.PURPLE);
                break;
        }
    }

    private void updateRightColor(Pixels pixel) {
        switch (pixel) {
            case NONE:
                rightBlinkin.setPattern(BlinkinLEDDriver.Pattern.BLACK);
                break;
            case WHITE:
                rightBlinkin.setPattern(BlinkinLEDDriver.Pattern.WHITE);
                break;
            case YELLOW:
                rightBlinkin.setPattern(BlinkinLEDDriver.Pattern.YELLOW);
                break;
            case GREEN:
                rightBlinkin.setPattern(BlinkinLEDDriver.Pattern.GREEN);
                break;
            case PURPLE:
                rightBlinkin.setPattern(BlinkinLEDDriver.Pattern.PURPLE);
                break;
        }
    }
}
