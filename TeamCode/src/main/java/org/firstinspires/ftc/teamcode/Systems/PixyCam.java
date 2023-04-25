package org.firstinspires.ftc.teamcode.Systems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.I2cAddr;
import com.qualcomm.robotcore.hardware.I2cDeviceSynch;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/*
        Bytes    16-bit word    Description
        ----------------------------------------------------------------
        0, 1     y              sync: 0xaa55=normal object, 0xaa56=color code object
        2, 3     y              checksum (sum of all 16-bit words 2-6, that is, bytes 4-13)
        4, 5     y              signature number
        6, 7     y              x center of object
        8, 9     y              y center of object
        10, 11   y              width of object
        12, 13   y              height of object
*/

public class PixyCam {
    I2cDeviceSynch pixy;

    public PixyCam(I2cDeviceSynch pixy) {
        this.pixy = pixy;
    }

    public PixyCam(HardwareMap hardwareMap, String name) {
        pixy = hardwareMap.get(I2cDeviceSynch.class, name);
    }

    public void initialize() {
        pixy.setI2cAddress(I2cAddr.create7bit(0x54));

        I2cDeviceSynch.ReadWindow readWindow = new I2cDeviceSynch.ReadWindow(1, 26, I2cDeviceSynch.ReadMode.REPEAT);
        pixy.setReadWindow(readWindow);
    }

    public void engage() {
        pixy.engage();
    }

    public void update() {}

    public void log(Telemetry telemetry) {
        telemetry.addData("Byte 0", pixy.read8(0));
        telemetry.addData("Byte 1", pixy.read8(1));
        telemetry.addData("Byte 2", pixy.read8(2));
        telemetry.addData("Byte 3", pixy.read8(3));
        telemetry.addData("Byte 4", pixy.read8(4));
        telemetry.addData("Byte 5", pixy.read8(5));
        telemetry.addData("Byte 6", pixy.read8(6));
        telemetry.addData("Byte 7", pixy.read8(7));
        telemetry.addData("Byte 8", pixy.read8(8));
        telemetry.addData("Byte 9", pixy.read8(9));
        telemetry.addData("Byte 10", pixy.read8(10));
        telemetry.addData("Byte 11", pixy.read8(11));
        telemetry.addData("Byte 12", pixy.read8(12));
        telemetry.addData("Byte 13", pixy.read8(13));
        telemetry.update();
    }
}
