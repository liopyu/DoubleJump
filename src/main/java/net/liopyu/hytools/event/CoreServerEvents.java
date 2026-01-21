package net.liopyu.hytools.event;

import com.hypixel.hytale.server.core.event.events.BootEvent;
import com.hypixel.hytale.server.core.event.events.PrepareUniverseEvent;
import com.hypixel.hytale.server.core.event.events.ShutdownEvent;
import net.liopyu.neotale.api.eventbus.EventBusSubscriber;
import net.liopyu.neotale.api.events.SubscribeEvent;

@EventBusSubscriber
public final class CoreServerEvents {

    @SubscribeEvent
    public static void onBoot(BootEvent e) {
        // System.out.println("[NeoTale-AllEvents][Core] BootEvent");
    }

    @SubscribeEvent
    public static void onPrepareUniverse(PrepareUniverseEvent e) {
        // System.out.println("[NeoTale-AllEvents][Core] PrepareUniverseEvent");
    }

    @SubscribeEvent
    public static void onShutdown(ShutdownEvent e) {
        // System.out.println("[NeoTale-AllEvents][Core] ShutdownEvent");
    }
}
