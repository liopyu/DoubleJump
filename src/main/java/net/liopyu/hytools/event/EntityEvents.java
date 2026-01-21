package net.liopyu.hytools.event;

import com.hypixel.hytale.server.core.event.events.entity.EntityEvent;
import com.hypixel.hytale.server.core.event.events.entity.EntityRemoveEvent;
import com.hypixel.hytale.server.core.event.events.entity.LivingEntityInventoryChangeEvent;
import net.liopyu.neotale.api.eventbus.EventBusSubscriber;
import net.liopyu.neotale.api.events.SubscribeEvent;

@EventBusSubscriber
public final class EntityEvents {

    @SubscribeEvent
    public static void onEntityEvent(EntityEvent e) {
        // System.out.println("[NeoTale-AllEvents][Entity] EntityEvent");
    }

    @SubscribeEvent
    public static void onEntityRemove(EntityRemoveEvent e) {
        // System.out.println("[NeoTale-AllEvents][Entity] EntityRemoveEvent");
    }

    @SubscribeEvent
    public static void onLivingEntityInventoryChange(LivingEntityInventoryChangeEvent e) {
        // System.out.println("[NeoTale-AllEvents][Entity] LivingEntityInventoryChangeEvent");
    }
}
