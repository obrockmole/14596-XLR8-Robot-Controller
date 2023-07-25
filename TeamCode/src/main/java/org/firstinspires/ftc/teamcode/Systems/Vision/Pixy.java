package org.firstinspires.ftc.teamcode.Systems.Vision;

import com.arcrobotics.ftclib.hardware.HardwareDevice;

public interface Pixy extends HardwareDevice {
    byte[] readData();
}
