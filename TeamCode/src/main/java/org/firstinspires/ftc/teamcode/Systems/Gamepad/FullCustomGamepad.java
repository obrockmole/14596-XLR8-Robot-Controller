package org.firstinspires.ftc.teamcode.Systems.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import io.github.jna4usb.purejavahidapi.HidDevice;
import io.github.jna4usb.purejavahidapi.HidDeviceInfo;
import io.github.jna4usb.purejavahidapi.InputReportListener;
import io.github.jna4usb.purejavahidapi.PureJavaHidApi;


public class FullCustomGamepad {
    private final double JOYSTICK_CLIP = 0.1;

    public double left_stick_x, left_stick_y, right_stick_x, right_stick_y, left_trigger, right_trigger, gyroX, gyroY, gyroZ, accelX, accelY, accelZ;
    public boolean dpad_up, dpad_down, dpad_left, dpad_right, a, b, x, y, left_bumper, right_bumper, left_stick, right_stick, share, option;

    public FullCustomGamepad() {
        List<HidDeviceInfo> deviceList = PureJavaHidApi.enumerateDevices();
        HidDeviceInfo controllerInfo = null;
        /*for (HidDeviceInfo deviceInfo : deviceList) {
            if (deviceInfo.getVendorId() == 1356 && deviceInfo.getProductId() == 2508) {
                controllerInfo = deviceInfo;
                break;
            }
        }*/
        controllerInfo = deviceList.get(0);

        HidDevice controller = null;
        try {
            controller = PureJavaHidApi.openDevice(controllerInfo);
        } catch (IOException e) {
            System.exit(1);
        }

        controller.setInputReportListener(new InputReportListener() {
            @Override
            public void onInputReport(HidDevice source, byte id, byte[] data, int length) {
                left_stick_x = data[1];
                left_stick_y = data[2];
                right_stick_x = data[3];
                right_stick_y = data[4];

                left_trigger = data[8];
                right_trigger = data[9];

                gyroX = data[13];
                gyroY = data[14];
                gyroZ = data[15];

                accelX = data[19];
                accelY = data[21];
                accelZ = data[23];

                dpad_up = new HashSet<>(Arrays.asList(7,0,1)).contains((data[5] & 0x0f));
                dpad_down = new HashSet<>(Arrays.asList(3,4,5)).contains((data[5] & 0x0f));
                dpad_left = new HashSet<>(Arrays.asList(5,6,7)).contains((data[5] & 0x0f));
                dpad_right = new HashSet<>(Arrays.asList(1,2,3)).contains((data[5] & 0x0f));

                a = (data[5] & (1 << 5)) != 0;
                b = (data[5] & (1 << 6)) != 0;
                x = (data[5] & (1 << 4)) != 0;
                y = (data[5] & (1 << 7)) != 0;

                left_bumper = (data[6] & (1 << 0)) != 0;
                right_bumper = (data[6] & (1 << 1)) != 0;
                left_stick = (data[6] & (1 << 6)) != 0;
                right_stick = (data[6] & (1 << 7)) != 0;
                share = (data[6] & (1 << 4)) != 0;
                option = (data[6] & (1 << 5)) != 0;
            }
        });
    }

    private static FullCustomGamepad instance = null;

    public static FullCustomGamepad getInstance() {
        if ( instance == null ) instance = new FullCustomGamepad();
        return instance;
    }

    public void log(Telemetry telemetry) {
        telemetry.addData("Left Stick","X: %6.1f, Y: %6.1f", left_stick_x, left_stick_y);
        telemetry.addData("Right Stick","X: %6.1f, Y: %6.1f", right_stick_x, right_stick_y);

        telemetry.addData("Left Trigger", "%6.1f", left_trigger);
        telemetry.addData("Right Trigger", "%6.1f", right_trigger);

        telemetry.addData("Gyro", "X: %6.1f, Y: %6.1f, Z: %6.1f", gyroX, gyroY, gyroZ);
        telemetry.addData("Accel", "X: %6.1f, Y: %6.1f, Z: %6.1f", accelX, accelY, accelZ);

        telemetry.addData("Dpad", "Up: %b, Down: %b, Left: %b, Right: %b", dpad_up, dpad_down, dpad_left, dpad_right);
        telemetry.addData("Buttons", "A: %b, B: %b, X: %b, Y: %b", a, b, x, y);

        telemetry.addData("Bumpers", "Left: %b, Right: %b", left_bumper, right_bumper);
        telemetry.addData("Sticks", "Left: %b, Right: %b", left_stick, right_stick);

        telemetry.addData("Share", "%b", share);
        telemetry.addData("Option", "%b", option);
    }
}
