package net.liopyu.doublejump;

import com.hypixel.hytale.server.core.event.events.ecs.BreakBlockEvent;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import com.hypixel.hytale.server.worldgen.util.LogUtil;
import net.liopyu.doublejump.system.TestSystem;

import javax.annotation.Nonnull;

public class DoubleJump extends JavaPlugin {

    public DoubleJump(@Nonnull JavaPluginInit init) {
        super(init);
    }

    @Override
    protected void setup() {
        super.setup();
        System.out.println("Loading Double Jump! - Liopyu");
        var stateType = getEntityStoreRegistry().registerComponent(DoubleJumpState.class, DoubleJumpState::new);

        getEntityStoreRegistry().registerSystem(new TestSystem.BlockBreakEventSystem(BreakBlockEvent.class));


        LogUtil.getLogger().atInfo().log("DoubleJump: registered component + system");
    }

}
