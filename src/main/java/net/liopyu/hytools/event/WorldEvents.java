package net.liopyu.hytools.event;

import com.hypixel.hytale.server.core.universe.world.events.AddWorldEvent;
import com.hypixel.hytale.server.core.universe.world.events.AllWorldsLoadedEvent;
import com.hypixel.hytale.server.core.universe.world.events.ChunkEvent;
import com.hypixel.hytale.server.core.universe.world.events.ChunkPreLoadProcessEvent;
import com.hypixel.hytale.server.core.universe.world.events.RemoveWorldEvent;
import com.hypixel.hytale.server.core.universe.world.events.StartWorldEvent;
import net.liopyu.neotale.api.eventbus.EventBusSubscriber;
import net.liopyu.neotale.api.events.SubscribeEvent;

@EventBusSubscriber
public final class WorldEvents {

    @SubscribeEvent
    public static void onAddWorld(AddWorldEvent e) {
        //  System.out.println("[NeoTale-AllEvents][World] AddWorldEvent");
    }

    @SubscribeEvent
    public static void onRemoveWorld(RemoveWorldEvent e) {
        // System.out.println("[NeoTale-AllEvents][World] RemoveWorldEvent");
    }

    @SubscribeEvent
    public static void onStartWorld(StartWorldEvent e) {
        //  System.out.println("[NeoTale-AllEvents][World] StartWorldEvent");
    }

    @SubscribeEvent
    public static void onAllWorldsLoaded(AllWorldsLoadedEvent e) {
        // System.out.println("[NeoTale-AllEvents][World] AllWorldsLoadedEvent");
    }

    @SubscribeEvent
    public static void onChunkEvent(ChunkEvent e) {
        // System.out.println("[NeoTale-AllEvents][World] ChunkEvent");
    }

    @SubscribeEvent
    public static void onChunkPreLoadProcess(ChunkPreLoadProcessEvent e) {
        // System.out.println("[NeoTale-AllEvents][World] ChunkPreLoadProcessEvent");
    }
}
