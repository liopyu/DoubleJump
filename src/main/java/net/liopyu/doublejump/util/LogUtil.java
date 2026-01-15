package net.liopyu.doublejump.util;

import com.hypixel.hytale.logger.HytaleLogger;

import javax.annotation.Nonnull;

public class LogUtil {
    private static final HytaleLogger LOGGER = HytaleLogger.get("DoubleJump");

    @Nonnull
    public static HytaleLogger getLogger() {
        return LOGGER;
    }
}
