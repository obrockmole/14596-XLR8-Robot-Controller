package org.firstinspires.ftc.teamcode.Systems.Vision;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.internal.system.Deadline;

import java.util.concurrent.TimeUnit;

/* WARNING: UNTESTED, MAY NOT WORK */
public class HuskyLens {
    private com.qualcomm.hardware.dfrobot.HuskyLens huskyLens;
    private com.qualcomm.hardware.dfrobot.HuskyLens.Algorithm algorithm;
    private final Deadline rateLimit;

    private com.qualcomm.hardware.dfrobot.HuskyLens.Block[] blocks;
    private com.qualcomm.hardware.dfrobot.HuskyLens.Arrow[] arrows;

    public HuskyLens(com.qualcomm.hardware.dfrobot.HuskyLens huskyLens, com.qualcomm.hardware.dfrobot.HuskyLens.Algorithm algorithm) {
        this.huskyLens = huskyLens;
        setAlgorithm(algorithm);

        rateLimit = new Deadline(1, TimeUnit.SECONDS);
        rateLimit.expire();

        blocks = new com.qualcomm.hardware.dfrobot.HuskyLens.Block[0];
        arrows = new com.qualcomm.hardware.dfrobot.HuskyLens.Arrow[0];
    }

    public HuskyLens(HardwareMap hardwareMap, String name, com.qualcomm.hardware.dfrobot.HuskyLens.Algorithm algorithm) {
        this(hardwareMap.get(com.qualcomm.hardware.dfrobot.HuskyLens.class, name), algorithm);
    }

    public com.qualcomm.hardware.dfrobot.HuskyLens getHuskyLens() {
        return huskyLens;
    }

    public HuskyLens setHuskyLens(com.qualcomm.hardware.dfrobot.HuskyLens huskyLens) {
        this.huskyLens = huskyLens;
        return this;
    }

    public HuskyLens setHuskylens(HardwareMap hardwareMap, String name) {
        return setHuskyLens(hardwareMap.get(com.qualcomm.hardware.dfrobot.HuskyLens.class, name));
    }

    public com.qualcomm.hardware.dfrobot.HuskyLens.Algorithm getAlgorithm() {
        return algorithm;
    }

    public HuskyLens setAlgorithm(com.qualcomm.hardware.dfrobot.HuskyLens.Algorithm algorithm) {
        this.algorithm = algorithm;
        huskyLens.selectAlgorithm(algorithm);
        return this;
    }

    public com.qualcomm.hardware.dfrobot.HuskyLens.Block[] getBlocks() {
        return blocks;
    }

    public com.qualcomm.hardware.dfrobot.HuskyLens.Arrow[] getArrows() {
        return arrows;
    }

    public HuskyLens update() {
        if (!rateLimit.hasExpired()) {
            rateLimit.reset();

            blocks = algorithm != com.qualcomm.hardware.dfrobot.HuskyLens.Algorithm.LINE_TRACKING ? huskyLens.blocks() : new com.qualcomm.hardware.dfrobot.HuskyLens.Block[0];
            arrows = algorithm == com.qualcomm.hardware.dfrobot.HuskyLens.Algorithm.LINE_TRACKING ? huskyLens.arrows() : new com.qualcomm.hardware.dfrobot.HuskyLens.Arrow[0];
        }
        return this;
    }

    public HuskyLens log(Telemetry telemetry, HardwareMap hardwareMap) {
        telemetry.addData("Sensor", hardwareMap.getNamesOf(huskyLens).toArray()[0]);
        telemetry.addData("Algorithm", getAlgorithm().toString());
        telemetry.addData(algorithm == com.qualcomm.hardware.dfrobot.HuskyLens.Algorithm.LINE_TRACKING ? "# of Arrows" : "# of Blocks", algorithm == com.qualcomm.hardware.dfrobot.HuskyLens.Algorithm.LINE_TRACKING ? getArrows().length : getBlocks().length);
        return this;
    }
}
