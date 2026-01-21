package net.liopyu.hytools.event;

import com.hypixel.hytale.server.core.event.events.ecs.BreakBlockEvent;
import com.hypixel.hytale.server.core.event.events.ecs.DropItemEvent;
import com.hypixel.hytale.server.core.event.events.ecs.InteractivelyPickupItemEvent;
import com.hypixel.hytale.server.core.event.events.ecs.PlaceBlockEvent;
import com.hypixel.hytale.server.core.event.events.ecs.SwitchActiveSlotEvent;
import com.hypixel.hytale.server.core.event.events.ecs.UseBlockEvent;
import com.hypixel.hytale.server.core.universe.world.events.ecs.ChunkSaveEvent;
import com.hypixel.hytale.server.core.universe.world.events.ecs.ChunkUnloadEvent;
import com.hypixel.hytale.server.core.universe.world.events.ecs.MoonPhaseChangeEvent;
import net.liopyu.neotale.api.eventbus.EventBusSubscriber;
import net.liopyu.neotale.api.events.SubscribeEvent;

@EventBusSubscriber
public final class EcsEvents {

    @SubscribeEvent
    public static void onBreakBlock(BreakBlockEvent e) {
        // System.out.println("[NeoTale-AllEvents][ECS] BreakBlockEvent");
    }

    @SubscribeEvent
    public static void onDropItem(DropItemEvent e) {
        // System.out.println("[NeoTale-AllEvents][ECS] DropItemEvent");
    }

    @SubscribeEvent
    public static void onInteractivelyPickupItem(InteractivelyPickupItemEvent e) {
        // System.out.println("[NeoTale-AllEvents][ECS] InteractivelyPickupItemEvent");
    }

    @SubscribeEvent
    public static void onPlaceBlock(PlaceBlockEvent e) {
        // System.out.println("[NeoTale-AllEvents][ECS] PlaceBlockEvent");
    }

    @SubscribeEvent
    public static void onSwitchActiveSlot(SwitchActiveSlotEvent e) {
        // System.out.println("[NeoTale-AllEvents][ECS] SwitchActiveSlotEvent");
    }

    @SubscribeEvent
    public static void onUseBlock(UseBlockEvent e) {
        //  System.out.println("[NeoTale-AllEvents][ECS] UseBlockEvent");
    }

    @SubscribeEvent
    public static void onChunkSave(ChunkSaveEvent e) {
        // System.out.println("[NeoTale-AllEvents][ECS] ChunkSaveEvent");
    }

    @SubscribeEvent
    public static void onChunkUnload(ChunkUnloadEvent e) {
        //  System.out.println("[NeoTale-AllEvents][ECS] ChunkUnloadEvent");
    }

    @SubscribeEvent
    public static void onMoonPhaseChange(MoonPhaseChangeEvent e) {
        // System.out.println("[NeoTale-AllEvents][ECS] MoonPhaseChangeEvent");
    }
}
