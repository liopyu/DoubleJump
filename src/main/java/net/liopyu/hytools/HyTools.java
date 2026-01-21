package net.liopyu.hytools;

import com.hypixel.hytale.logger.HytaleLogger;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import com.hypixel.hytale.server.worldgen.util.LogUtil;

import javax.annotation.Nonnull;

public class HyTools extends JavaPlugin {
    public static final HytaleLogger LOGGER = LogUtil.getLogger().getSubLogger("HyTools");

    public HyTools(@Nonnull JavaPluginInit init) {
        super(init);
    }

    @Override
    protected void setup() {
        super.setup();
        System.out.println("Loading HyTools!");
    }


}
