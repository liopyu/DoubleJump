package net.liopyu.hytools.util;

import net.liopyu.hytools.HyTools;

import java.util.Arrays;

public class HytoolsHelperClass {
    public static void logInfo(Object... message) {
        HyTools.LOGGER.atInfo().log(Arrays.toString(message));
    }

    public static void logWarning(Object... message) {
        HyTools.LOGGER.atWarning().log(Arrays.toString(message));
    }

    public static void logError(Object... message) {
        HyTools.LOGGER.atSevere().log(Arrays.toString(message));
    }
}
