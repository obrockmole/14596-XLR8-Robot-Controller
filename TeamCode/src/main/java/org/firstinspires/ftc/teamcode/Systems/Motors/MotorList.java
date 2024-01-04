package org.firstinspires.ftc.teamcode.Systems.Motors;

public enum MotorList {
    /* Gobilda motors are named by RPM */
    /**
     * Gobilda 6000 RPM motor.
     */
    GOBILDA_6000(6000, 1, 28),
    /**
     * Gobilda 1620 RPM motor.
     */
    GOBILDA_1620(6000, 1 + (46.0 / 17.0), 28),
    /**
     * Gobilda 1150 RPM motor.
     */
    GOBILDA_1150(6000, 1 + (46.0 / 11.0), 28),
    /**
     * Gobilda 435 RPM motor.
     */
    GOBILDA_435(6000, (1 + (46.0 / 17.0)) * (1 + (46.0 / 17.0)), 28),
    /**
     * Gobilda 312 RPM motor.
     */
    GOBILDA_312(6000, (1 + (46.0 / 17.0)) * (1 + (46.0 / 11.0)), 28),
    /**
     * Gobilda 223 RPM motor.
     */
    GOBILDA_223(6000, (1 + (46.0 / 11.0)) * (1 + (46.0 / 11.0)), 28),
    /**
     * Gobilda 117 RPM motor.
     */
    GOBILDA_117(6000, (1 + (46.0 / 17.0)) * (1 + (46.0 / 17.0)) * (1 + (46.0 / 17.0)), 28),
    /**
     * Gobilda 84 RPM motor.
     */
    GOBILDA_84(6000, (1 + (46.0 / 17.0)) * (1 + (46.0 / 17.0)) * (1 + (46.0 / 11.0)), 28),
    /**
     * Gobilda 60 RPM motor.
     */
    GOBILDA_60(6000, (1 + (46.0 / 17.0)) * (1 + (46.0 / 11.0)) * (1 + (46.0 / 11.0)), 28),
    /**
     * Gobilda 43 RPM motor.
     */
    GOBILDA_43(6000, (1 + (46.0 / 11.0)) * (1 + (46.0 / 11.0)) * (1 + (46.0 / 11.0)), 28),
    /**
     * Gobilda 30 RPM motor.
     */
    GOBILDA_30(6000, (1 + (46.0 / 17.0)) * (1 + (46.0 / 17.0)) * (1 + (46.0 / 17.0)) * (1 + (46.0 / 17.0)), 28),

    /* REV planetary motors are named by gear ratio */
    /**
     * REV Planetary 1:1 motor.
     */
    REV_PLAN_1(6000, 1, 28),
    /**
     * REV Planetary 3:1 motor.
     */
    REV_PLAN_3(6000, 84.0 / 29.0, 28),
    /**
     * REV Planetary 4:1 motor.
     */
    REV_PLAN_4(6000, 76.0 / 21.0, 28),
    /**
     * REV Planetary 5:1 motor.
     */
    REV_PLAN_5(6000, 68.0 / 13.0, 28),
    /**
     * REV Planetary 9:1 motor.
     */
    REV_PLAN_9(6000, 7056.0 / 841.0, 28),
    /**
     * REV Planetary 12:1 motor.
     */
    REV_PLAN_12(6000, 6384.0 / 609.0, 28),
    /**
     * REV Planetary 15:1 motor.
     */
    REV_PLAN_15(6000, 5712.0 / 377.0, 28),
    /**
     * REV Planetary 16:1 motor.
     */
    REV_PLAN_16(6000, 5776.0 / 441.0, 28),
    /**
     * REV Planetary 20:1 motor.
     */
    REV_PLAN_20(6000, 5168.0 / 273.0, 28),
    /**
     * REV Planetary 25:1 motor.
     */
    REV_PLAN_25(6000, 4624.0 / 169.0, 28),
    /**
     * REV Planetary 27:1 motor.
     */
    REV_PLAN_27(6000, 592704.0 / 24389.0, 28),
    /**
     * REV Planetary 36:1 motor.
     */
    REV_PLAN_36(6000, 536256.0 / 17661.0, 28),
    /**
     * REV Planetary 45:1 motor.
     */
    REV_PLAN_45(6000, 479808.0 / 10933.0, 28),
    /**
     * REV Planetary 48:1 motor.
     */
    REV_PLAN_48(6000, 485184.0 / 12789.0, 28),
    /**
     * REV Planetary 60:1 motor.
     */
    REV_PLAN_60(6000, 434112.0 / 7917.0, 28),
    /**
     * REV Planetary 64:1 motor.
     */
    REV_PLAN_64(6000, 438976.0 / 9261.0, 28),
    /**
     * REV Planetary 75:1 motor.
     */
    REV_PLAN_75(6000, 388416.0 / 4901.0, 28),
    /**
     * REV Planetary 80:1 motor.
     */
    REV_PLAN_80(6000, 392768.0 / 5733.0, 28),
    /**
     * REV Planetary 100:1 motor.
     */
    REV_PLAN_100(6000, 351424.0 / 3549.0, 28),
    /**
     * REV Planetary 125:1 motor.
     */
    REV_PLAN_125(6000, 314432.0 / 2197.0, 28),
    /**
     * REV Core Hex motor.
     */
    REV_COREHEX(9000, 72, 4),
    /**
     * REV HD Hex motor.
     */
    REV_HEX(REV_PLAN_1),
    /**
     * REV HD Hex 20:1 motor.
     */
    REV_HEX_20(6000, 20, 28),
    /**
     * REV HD Hex 40:1 motor.
     */
    REV_HEX_40(6000, 40, 28);

    /**
     * The free RPM of the motor.
     */
    public final int freeRPM;
    /**
     * The RPM of the motor considering the gear ratio.
     */
    public final double RPM;
    /**
     * The ticks per rotation of the motor.
     */
    public final double TPR;
    /**
     * The ticks per degree of the motor.
     */
    public final double TPD;
    /**
     * The ticks per second of the motor.
     */
    public final double TPS;
    /**
     * The gear ratio of the motor.
     */
    public final double gearRatio;
    /**
     * The encoder resolution of the motor.
     */
    public final int resolution;


    /**
     * Constructs a new MotorList enum with given parameters.
     *
     * @param freeRPM The free RPM of the motor.
     * @param gearRatio The gear ratio of the motor.
     * @param resolution The encoder resolution of the motor.
     */
    MotorList(int freeRPM, double gearRatio, int resolution) {
        this.freeRPM = freeRPM;
        this.RPM = freeRPM / gearRatio;
        this.TPR = gearRatio * resolution;
        this.TPD = TPR / 360;
        this.TPS = RPM * TPR / 60;
        this.gearRatio = gearRatio;
        this.resolution = resolution;
    }

    /**
     * Constructs a copy of a MotorList enum.
     *
     * @param motor The motor to copy.
     */
    MotorList(MotorList motor) {
        this(motor.freeRPM, motor.gearRatio, motor.resolution);
    }
}
