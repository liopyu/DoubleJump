package net.liopyu.doublejump.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.*;

public class PluginFileLogger {
    private static Logger LOGGER;
    private static FileHandler HANDLER;

    public static Logger get() {
        return LOGGER;
    }

    public static void init(Path serverFolder) {
        if (LOGGER != null) return;

        try {
            Files.createDirectories(serverFolder);
            Path file = serverFolder.resolve("DoubleJump.log");

            LOGGER = Logger.getLogger("DoubleJumpFile");
            LOGGER.setUseParentHandlers(false);
            LOGGER.setLevel(Level.ALL);

            HANDLER = new FileHandler(file.toString(), true);
            HANDLER.setLevel(Level.ALL);
            HANDLER.setFormatter(new Formatter() {
                @Override
                public String format(LogRecord r) {
                    return String.format(
                            "%1$tF %1$tT.%1$tL|%2$s|%3$s%n",
                            r.getMillis(),
                            r.getLevel().getName(),
                            r.getMessage()
                    );
                }
            });

            LOGGER.addHandler(HANDLER);
        } catch (IOException e) {
            LOGGER = Logger.getLogger("DoubleJumpFile");
            LOGGER.log(Level.SEVERE, "Failed to init file logger: " + e);
        }
    }

    public static void close() {
        if (HANDLER != null) {
            HANDLER.flush();
            HANDLER.close();
            HANDLER = null;
        }
        LOGGER = null;
    }
}
