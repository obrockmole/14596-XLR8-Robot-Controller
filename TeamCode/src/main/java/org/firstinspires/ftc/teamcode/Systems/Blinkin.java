package org.firstinspires.ftc.teamcode.Systems;

import android.graphics.Color;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Blinkin {
    private RevBlinkinLedDriver blinkin;
    private Pattern pattern;

    public enum Pattern {
        //Fixed Pattern
        RAINBOW(RevBlinkinLedDriver.BlinkinPattern.RAINBOW_RAINBOW_PALETTE),
        PARTY_PALETTE(RevBlinkinLedDriver.BlinkinPattern.RAINBOW_PARTY_PALETTE),
        OCEAN_PALETTE(RevBlinkinLedDriver.BlinkinPattern.RAINBOW_OCEAN_PALETTE),
        LAVA_PALETTE(RevBlinkinLedDriver.BlinkinPattern.RAINBOW_LAVA_PALETTE),
        FOREST_PALETTE(RevBlinkinLedDriver.BlinkinPattern.RAINBOW_FOREST_PALETTE),
        RAINBOW_WITH_GLITTER(RevBlinkinLedDriver.BlinkinPattern.RAINBOW_WITH_GLITTER),
        CONFETTI(RevBlinkinLedDriver.BlinkinPattern.CONFETTI),
        RED_SHOT(RevBlinkinLedDriver.BlinkinPattern.SHOT_RED),
        BLUE_SHOT(RevBlinkinLedDriver.BlinkinPattern.SHOT_BLUE),
        WHITE_SHOT(RevBlinkinLedDriver.BlinkinPattern.SHOT_WHITE),
        RAINBOW_PALETTE_SINELON(RevBlinkinLedDriver.BlinkinPattern.SINELON_RAINBOW_PALETTE),
        PARTY_PALETTE_SINELON(RevBlinkinLedDriver.BlinkinPattern.SINELON_PARTY_PALETTE),
        OCEAN_PALETTE_SINELON(RevBlinkinLedDriver.BlinkinPattern.SINELON_OCEAN_PALETTE),
        LAVA_PALETTE_SINELON(RevBlinkinLedDriver.BlinkinPattern.SINELON_LAVA_PALETTE),
        FOREST_PALETTE_SINELON(RevBlinkinLedDriver.BlinkinPattern.SINELON_FOREST_PALETTE),
        RAINBOW_PALETTE_BPM(RevBlinkinLedDriver.BlinkinPattern.BEATS_PER_MINUTE_RAINBOW_PALETTE),
        PARTY_PALETTE_BPM(RevBlinkinLedDriver.BlinkinPattern.BEATS_PER_MINUTE_PARTY_PALETTE),
        OCEAN_PALETTE_BPM(RevBlinkinLedDriver.BlinkinPattern.BEATS_PER_MINUTE_OCEAN_PALETTE),
        LAVA_PALETTE_BPM(RevBlinkinLedDriver.BlinkinPattern.BEATS_PER_MINUTE_LAVA_PALETTE),
        FOREST_PALETTE_BPM(RevBlinkinLedDriver.BlinkinPattern.BEATS_PER_MINUTE_FOREST_PALETTE),
        FIRE_MEDIUM(RevBlinkinLedDriver.BlinkinPattern.FIRE_MEDIUM),
        FIRE_LARGE(RevBlinkinLedDriver.BlinkinPattern.FIRE_LARGE),
        RAINBOW_PALETTE_TWINKLES(RevBlinkinLedDriver.BlinkinPattern.TWINKLES_RAINBOW_PALETTE),
        PARTY_PALETTE_TWINKLES(RevBlinkinLedDriver.BlinkinPattern.TWINKLES_PARTY_PALETTE),
        OCEAN_PALETTE_TWINKLES(RevBlinkinLedDriver.BlinkinPattern.TWINKLES_OCEAN_PALETTE),
        LAVA_PALETTE_TWINKLES(RevBlinkinLedDriver.BlinkinPattern.TWINKLES_LAVA_PALETTE),
        FOREST_PALETTE_TWINKLES(RevBlinkinLedDriver.BlinkinPattern.TWINKLES_FOREST_PALETTE),
        RAINBOW_PALETTE_WAVES(RevBlinkinLedDriver.BlinkinPattern.COLOR_WAVES_RAINBOW_PALETTE),
        PARTY_PALETTE_WAVES(RevBlinkinLedDriver.BlinkinPattern.COLOR_WAVES_PARTY_PALETTE),
        OCEAN_PALETTE_WAVES(RevBlinkinLedDriver.BlinkinPattern.COLOR_WAVES_OCEAN_PALETTE),
        LAVA_PALETTE_WAVES(RevBlinkinLedDriver.BlinkinPattern.COLOR_WAVES_LAVA_PALETTE),
        FOREST_PALETTE_WAVES(RevBlinkinLedDriver.BlinkinPattern.COLOR_WAVES_FOREST_PALETTE),
        RED_LARSON_SCANNER(RevBlinkinLedDriver.BlinkinPattern.LARSON_SCANNER_RED),
        GRAY_LARSON_SCANNER(RevBlinkinLedDriver.BlinkinPattern.LARSON_SCANNER_GRAY),
        RED_CHASE(RevBlinkinLedDriver.BlinkinPattern.LIGHT_CHASE_RED),
        BLUE_CHASE(RevBlinkinLedDriver.BlinkinPattern.LIGHT_CHASE_BLUE),
        GRAY_CHASE(RevBlinkinLedDriver.BlinkinPattern.LIGHT_CHASE_GRAY),
        RED_HEARTBEAT(RevBlinkinLedDriver.BlinkinPattern.HEARTBEAT_RED),
        BLUE_HEARTBEAT(RevBlinkinLedDriver.BlinkinPattern.HEARTBEAT_BLUE),
        WHITE_HEARTBEAT(RevBlinkinLedDriver.BlinkinPattern.HEARTBEAT_WHITE),
        GRAY_HEARTBEAT(RevBlinkinLedDriver.BlinkinPattern.HEARTBEAT_GRAY),
        RED_BREATH(RevBlinkinLedDriver.BlinkinPattern.BREATH_RED),
        BLUE_BREATH(RevBlinkinLedDriver.BlinkinPattern.BREATH_BLUE),
        GRAY_BREATH(RevBlinkinLedDriver.BlinkinPattern.BREATH_GRAY),
        RED_STROBE(RevBlinkinLedDriver.BlinkinPattern.STROBE_RED),
        BLUE_STROBE(RevBlinkinLedDriver.BlinkinPattern.STROBE_BLUE),
        GOLD_STROBE(RevBlinkinLedDriver.BlinkinPattern.STROBE_GOLD),
        WHITE_STROBE(RevBlinkinLedDriver.BlinkinPattern.STROBE_WHITE),

        //Color 1 Pattern
        C1_BLEND_TO_BLACK(RevBlinkinLedDriver.BlinkinPattern.CP1_END_TO_END_BLEND_TO_BLACK),
        C1_LARSON_SCANNER(RevBlinkinLedDriver.BlinkinPattern.CP1_LARSON_SCANNER),
        C1_CHASE(RevBlinkinLedDriver.BlinkinPattern.CP1_LIGHT_CHASE),
        C1_HEARTBEAT_SLOW(RevBlinkinLedDriver.BlinkinPattern.CP1_HEARTBEAT_SLOW),
        C1_HEARTBEAT_MEDIUM(RevBlinkinLedDriver.BlinkinPattern.CP1_HEARTBEAT_MEDIUM),
        C1_HEARTBEAT_FAST(RevBlinkinLedDriver.BlinkinPattern.CP1_HEARTBEAT_FAST),
        C1_BREATH_SLOW(RevBlinkinLedDriver.BlinkinPattern.CP1_BREATH_SLOW),
        C1_BREATH_FAST(RevBlinkinLedDriver.BlinkinPattern.CP1_BREATH_FAST),
        C1_SHOT(RevBlinkinLedDriver.BlinkinPattern.CP1_SHOT),
        C1_STROBE(RevBlinkinLedDriver.BlinkinPattern.CP1_STROBE),

        //Color 2 Pattern
        C2_BLEND_TO_BLACK(RevBlinkinLedDriver.BlinkinPattern.CP2_END_TO_END_BLEND_TO_BLACK),
        C2_LARSON_SCANNER(RevBlinkinLedDriver.BlinkinPattern.CP2_LARSON_SCANNER),
        C2_CHASE(RevBlinkinLedDriver.BlinkinPattern.CP2_LIGHT_CHASE),
        C2_HEARTBEAT_SLOW(RevBlinkinLedDriver.BlinkinPattern.CP2_HEARTBEAT_SLOW),
        C2_HEARTBEAT_MEDIUM(RevBlinkinLedDriver.BlinkinPattern.CP2_HEARTBEAT_MEDIUM),
        C2_HEARTBEAT_FAST(RevBlinkinLedDriver.BlinkinPattern.CP2_HEARTBEAT_FAST),
        C2_BREATH_SLOW(RevBlinkinLedDriver.BlinkinPattern.CP2_BREATH_SLOW),
        C2_BREATH_FAST(RevBlinkinLedDriver.BlinkinPattern.CP2_BREATH_FAST),
        C2_SHOT(RevBlinkinLedDriver.BlinkinPattern.CP2_SHOT),
        C2_STROBE(RevBlinkinLedDriver.BlinkinPattern.CP2_STROBE),

        //Color 1 and 2 Pattern
        C1_C2_SPARKLE_1_ON_2(RevBlinkinLedDriver.BlinkinPattern.CP1_2_SPARKLE_1_ON_2),
        C1_C2_SPARKLE_2_ON_1(RevBlinkinLedDriver.BlinkinPattern.CP1_2_SPARKLE_2_ON_1),
        C1_C2_GRADIENT(RevBlinkinLedDriver.BlinkinPattern.CP1_2_COLOR_GRADIENT),
        C1_C2_BEATS_PER_MINUTE(RevBlinkinLedDriver.BlinkinPattern.CP1_2_BEATS_PER_MINUTE),
        C1_C2_BLEND_1_TO_2(RevBlinkinLedDriver.BlinkinPattern.CP1_2_END_TO_END_BLEND_1_TO_2),
        C1_C2_BLEND(RevBlinkinLedDriver.BlinkinPattern.CP1_2_END_TO_END_BLEND),
        C1_C2_NO_BLENDING(RevBlinkinLedDriver.BlinkinPattern.CP1_2_NO_BLENDING),
        C1_C2_TWINKLES(RevBlinkinLedDriver.BlinkinPattern.CP1_2_TWINKLES),
        C1_C2_WAVES(RevBlinkinLedDriver.BlinkinPattern.CP1_2_COLOR_WAVES),
        C1_C2_SINELON(RevBlinkinLedDriver.BlinkinPattern.CP1_2_SINELON),

        //Solid Color
        HOT_PINK(RevBlinkinLedDriver.BlinkinPattern.HOT_PINK),
        DARK_RED(RevBlinkinLedDriver.BlinkinPattern.DARK_RED),
        RED(RevBlinkinLedDriver.BlinkinPattern.RED),
        RED_ORANGE(RevBlinkinLedDriver.BlinkinPattern.RED_ORANGE),
        ORANGE(RevBlinkinLedDriver.BlinkinPattern.ORANGE),
        GOLD(RevBlinkinLedDriver.BlinkinPattern.GOLD),
        YELLOW(RevBlinkinLedDriver.BlinkinPattern.YELLOW),
        LIGHT_GREEN(RevBlinkinLedDriver.BlinkinPattern.LAWN_GREEN),
        LIME(RevBlinkinLedDriver.BlinkinPattern.LIME),
        DARK_GREEN(RevBlinkinLedDriver.BlinkinPattern.DARK_GREEN),
        GREEN(RevBlinkinLedDriver.BlinkinPattern.GREEN),
        CYAN(RevBlinkinLedDriver.BlinkinPattern.BLUE_GREEN),
        AQUA(RevBlinkinLedDriver.BlinkinPattern.AQUA),
        LIGHT_BLUE(RevBlinkinLedDriver.BlinkinPattern.SKY_BLUE),
        DARK_BLUE(RevBlinkinLedDriver.BlinkinPattern.DARK_BLUE),
        BLUE(RevBlinkinLedDriver.BlinkinPattern.BLUE),
        PURPLE(RevBlinkinLedDriver.BlinkinPattern.BLUE_VIOLET),
        VIOLET(RevBlinkinLedDriver.BlinkinPattern.VIOLET),
        WHITE(RevBlinkinLedDriver.BlinkinPattern.WHITE),
        GRAY(RevBlinkinLedDriver.BlinkinPattern.GRAY),
        DARK_GRAY(RevBlinkinLedDriver.BlinkinPattern.DARK_GRAY),
        BLACK(RevBlinkinLedDriver.BlinkinPattern.BLACK);

        private RevBlinkinLedDriver.BlinkinPattern pattern;
        private static Pattern[] elements = values();

        Pattern(RevBlinkinLedDriver.BlinkinPattern pattern) {
            this.pattern = pattern;
        }

        public static Pattern fromRevPattern(RevBlinkinLedDriver.BlinkinPattern pattern) {
            for (Pattern p : elements) {
                if (p.pattern == pattern) return p;
            }
            return null;
        }

        public static Pattern fromNumber(int number) {
            return elements[number % elements.length];
        }

        public Pattern next() {
            return elements[(this.ordinal() + 1) % elements.length];
        }

        public Pattern previous() {
            return elements[(this.ordinal() - 1) < 0 ? elements.length - 1 : this.ordinal() - 1];
        }
    }

    public Blinkin(RevBlinkinLedDriver blinkin) {
        this.blinkin = blinkin;
    }

    public Blinkin(HardwareMap hardwareMap, String name) {
        this(hardwareMap.get(RevBlinkinLedDriver.class, name));
    }

    public Pattern getPattern() {
        return pattern;
    }

    public RevBlinkinLedDriver.BlinkinPattern getRevPattern() {
        return pattern.pattern;
    }

    public Blinkin setPattern(Pattern pattern) {
        this.pattern = pattern;
        blinkin.setPattern(RevBlinkinLedDriver.BlinkinPattern.fromNumber(pattern.ordinal()));
        return this;
    }

    public Blinkin setPattern(RevBlinkinLedDriver.BlinkinPattern pattern) {
        this.pattern = Pattern.fromRevPattern(pattern);
        blinkin.setPattern(pattern);
        return this;
    }

    public Blinkin nextPattern() {
        pattern = pattern.next();
        setPattern(pattern);
        return this;
    }

    public Blinkin previousPattern() {
        pattern = pattern.previous();
        setPattern(pattern);
        return this;
    }
}
