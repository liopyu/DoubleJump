package net.liopyu.doublejump.util;

import net.liopyu.doublejump.DoubleJump;

import java.util.Arrays;

public class HytaleHelperClass {
    public static void logInfo(Object... message) {
        DoubleJump.LOGGER.atInfo().log(Arrays.toString(message));
    }

    public static void logWarning(Object... message) {
        DoubleJump.LOGGER.atWarning().log(Arrays.toString(message));
    }

    public static void logError(Object... message) {
        DoubleJump.LOGGER.atSevere().log(Arrays.toString(message));
    }
}
