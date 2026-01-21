package net.liopyu.hytools.event;

import com.hypixel.hytale.server.core.event.events.permissions.GroupPermissionChangeEvent;
import com.hypixel.hytale.server.core.event.events.permissions.PlayerGroupEvent;
import com.hypixel.hytale.server.core.event.events.permissions.PlayerPermissionChangeEvent;
import net.liopyu.neotale.api.eventbus.EventBusSubscriber;
import net.liopyu.neotale.api.events.SubscribeEvent;

@EventBusSubscriber
public final class PermissionEvents {

    @SubscribeEvent
    public static void onGroupPermissionChange(GroupPermissionChangeEvent e) {
        // System.out.println("[NeoTale-AllEvents][Permissions] GroupPermissionChangeEvent");
    }

    @SubscribeEvent
    public static void onPlayerGroup(PlayerGroupEvent e) {
        //  System.out.println("[NeoTale-AllEvents][Permissions] PlayerGroupEvent");
    }

    @SubscribeEvent
    public static void onPlayerPermissionChange(PlayerPermissionChangeEvent e) {
        // System.out.println("[NeoTale-AllEvents][Permissions] PlayerPermissionChangeEvent");
    }
}
