package org.firstinspires.ftc.teamcode.Systems.Motors;

public enum MotorLookupTable {
    /* Gobilda motors are named by RPM */
    GOBILDA_6000(6000, 1, 28),
    GOBILDA_1620(6000, 1 + (46.0 / 17.0), 28),
    GOBILDA_1150(6000, 1 + (46.0 / 11.0), 28),
    GOBILDA_435(6000, (1 + (46.0 / 17.0)) * (1 + (46.0 / 17.0)), 28),
    GOBILDA_312(6000, (1 + (46.0 / 17.0)) * (1 + (46.0 / 11.0)), 28),
    GOBILDA_223(6000, (1 + (46.0 / 11.0)) * (1 + (46.0 / 11.0)), 28),
    GOBILDA_117(6000, (1 + (46.0 / 17.0)) * (1 + (46.0 / 17.0)) * (1 + (46.0 / 17.0)), 28),
    GOBILDA_84(6000, (1 + (46.0 / 17.0)) * (1 + (46.0 / 17.0)) * (1 + (46.0 / 11.0)), 28),
    GOBILDA_60(6000, (1 + (46.0 / 17.0)) * (1 + (46.0 / 11.0)) * (1 + (46.0 / 11.0)), 28),
    GOBILDA_43(6000, (1 + (46.0 / 11.0)) * (1 + (46.0 / 11.0)) * (1 + (46.0 / 11.0)), 28),
    GOBILDA_30(6000, (1 + (46.0 / 17.0)) * (1 + (46.0 / 17.0)) * (1 + (46.0 / 17.0)) * (1 + (46.0 / 17.0)), 28),

    /* REV planetary motors are named by gear ratio */
    REV_PLAN_1(6000, 1, 28),
    REV_PLAN_3(6000, 84.0 / 29.0, 28),
    REV_PLAN_4(6000, 76.0 / 21.0, 28),
    REV_PLAN_5(6000, 68.0 / 13.0, 28),
    REV_PLAN_9(6000, 7056.0 / 841.0, 28),
    REV_PLAN_12(6000, 6384.0 / 609.0, 28),
    REV_PLAN_15(6000, 5712.0 / 377.0, 28),
    REV_PLAN_16(6000, 5776.0 / 441.0, 28),
    REV_PLAN_20(6000, 5168.0 / 273.0, 28),
    REV_PLAN_25(6000, 4624.0 / 169.0, 28),
    REV_PLAN_27(6000, 592704.0 / 24389.0, 28),
    REV_PLAN_36(6000, 536256.0 / 17661.0, 28),
    REV_PLAN_45(6000, 479808.0 / 10933.0, 28),
    REV_PLAN_48(6000, 485184.0 / 12789.0, 28),
    REV_PLAN_60(6000, 434112.0 / 7917.0, 28),
    REV_PLAN_64(6000, 438976.0 / 9261.0, 28),
    REV_PLAN_75(6000, 388416.0 / 4901.0, 28),
    REV_PLAN_80(6000, 392768.0 / 5733.0, 28),
    REV_PLAN_100(6000, 351424.0 / 3549.0, 28),
    REV_PLAN_125(6000, 314432.0 / 2197.0, 28),
    REV_COREHEX(9000, 72, 4),
    REV_HEX(REV_PLAN_1),
    REV_HEX_20(6000, 20, 28),
    REV_HEX_40(6000, 40, 28);

    public final int freeRPM;
    public final double RPM;
    public final double TPR;
    public final double TPD;
    public final double TPS;
    public final double gearRatio;
    public final int resolution;

    MotorLookupTable(int freeRPM, double gearRatio, int resolution) {
        this.freeRPM = freeRPM;
        this.RPM = freeRPM / gearRatio;
        this.TPR = gearRatio * resolution;
        this.TPD = TPR / 360;
        this.TPS = RPM * TPR / 60;
        this.gearRatio = gearRatio;
        this.resolution = resolution;
    }

    MotorLookupTable(MotorLookupTable motorType) {
        this(motorType.freeRPM, motorType.gearRatio, motorType.resolution);
    }
}