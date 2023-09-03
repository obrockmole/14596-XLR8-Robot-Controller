package org.firstinspires.ftc.teamcode.Systems.Vision;

import com.qualcomm.robotcore.hardware.I2cAddr;
import com.qualcomm.robotcore.hardware.I2cDeviceSynch;
import com.qualcomm.robotcore.hardware.I2cDeviceSynchDevice;
import com.qualcomm.robotcore.hardware.configuration.annotations.DeviceProperties;
import com.qualcomm.robotcore.hardware.configuration.annotations.I2cDeviceType;

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

@I2cDeviceType
@DeviceProperties(name = "Pixy Cam v2", xmlTag = "PixyCam2")
public class PixyCam extends I2cDeviceSynchDevice<I2cDeviceSynch> implements Pixy {
    public PixyCam(I2cDeviceSynch deviceClient) {
        super(deviceClient, true);

        this.deviceClient.setI2cAddress(I2cAddr.create7bit(0x54));
        this.deviceClient.setReadWindow(new I2cDeviceSynch.ReadWindow(1, 26, I2cDeviceSynch.ReadMode.REPEAT));

        super.registerArmingStateCallback(false);
        this.deviceClient.engage();
    }

    @Override
    public Manufacturer getManufacturer() {
        return Manufacturer.Other;
    }

    @Override
    public String getDeviceName() {
        return "Pixy Cam v2";
    }

    @Override
    protected synchronized boolean doInitialize() {
        return true;
    }

    @Override
    public byte[] readData() {
        return new byte[] {this.deviceClient.read8(0), this.deviceClient.read8(1), this.deviceClient.read8(2), this.deviceClient.read8(3), this.deviceClient.read8(4), this.deviceClient.read8(5), this.deviceClient.read8(6), this.deviceClient.read8(7), this.deviceClient.read8(8), this.deviceClient.read8(9), this.deviceClient.read8(10), this.deviceClient.read8(11), this.deviceClient.read8(12), this.deviceClient.read8(13)};
    }

    @Override
    public void disable() {}

    @Override
    public String getDeviceType() {
        return "PixyCam";
    }

    public enum Register {
        REQ_GET_RESOLUTION((byte)12),
        REQ_GET_VERSION((byte)14),
        REQ_SET_CAMERA_BRIGHTNESS((byte)16),
        REQ_SET_SERVOS((byte)18),
        REQ_SET_LED((byte)20),
        REQ_SET_LABEL((byte)22),
        REQ_GET_FPS((byte)24),

        REQ_GET_BLOCKS((byte)32),

        REQ_GET_MAIN_FEATURES((byte)48),
        REQ_SET_MODE((byte)54),
        REQ_SET_VECTOR((byte)56),
        REQ_SET_NEXT_TURN((byte)58),
        REQ_SET_DEFAULT_TURN((byte)60),
        REQ_REVERSE_VECTOR((byte)62),

        REQ_GET_RGB((byte)112),

        RES_RESULT((byte)1),
        RES_RESOLUTION((byte)13),
        RES_VERSION((byte)15),

        RES_BLOCKS((byte)33),

        RES_MAIN_FEATURES((byte)49),

        FEATURE_TYPE_MAIN((byte)0x00),
        FEATURE_TYPE_ALL((byte)0x01),

        FEATURES_VECTOR((byte)1),
        FEATURES_INTERSECTION((byte)(1 << 1)),
        FEATURES_BARCODE((byte)(1 << 2)),
        FEATURES_ALL((byte)((byte)1 + (byte)(1 << 1) + (byte)(1 << 2))),

        BLOCKS_SIG_1((byte)1),
        BLOCKS_SIG_2((byte)(1 << 1)),
        BLOCKS_SIG_3((byte)(1 << 2)),
        BLOCKS_SIG_4((byte)(1 << 3)),
        BLOCKS_SIG_5((byte)(1 << 4)),
        BLOCKS_SIG_6((byte)(1 << 5)),
        BLOCKS_SIG_7((byte)(1 << 6)),
        BLOCKS_SIG_8((byte)(1 << 7)),
        BLOCKS_ALL_SIG((byte)255),
        MAX_BLOCKS_ALL((byte)255),

        LINE_FLAG_INVALID((byte)0x02),
        LINE_FLAG_INTERSECTION_PRESENT((byte)0x04),

        SAT_FLAG_SATURATE((byte)0x01);

        public int bVal;

        Register(int bVal) {
            this.bVal = bVal;
        }
    }
}
