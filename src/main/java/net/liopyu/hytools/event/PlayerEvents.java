package net.liopyu.hytools.event;

import com.hypixel.hytale.server.core.event.events.player.AddPlayerToWorldEvent;
import com.hypixel.hytale.server.core.event.events.player.DrainPlayerFromWorldEvent;
import com.hypixel.hytale.server.core.event.events.player.PlayerChatEvent;
import com.hypixel.hytale.server.core.event.events.player.PlayerConnectEvent;
import com.hypixel.hytale.server.core.event.events.player.PlayerCraftEvent;
import com.hypixel.hytale.server.core.event.events.player.PlayerDisconnectEvent;
import com.hypixel.hytale.server.core.event.events.player.PlayerInteractEvent;
import net.liopyu.neotale.api.eventbus.EventBusSubscriber;
import net.liopyu.neotale.api.events.SubscribeEvent;

@EventBusSubscriber
public final class PlayerEvents {

    @SubscribeEvent
    public static void onAddPlayerToWorld(AddPlayerToWorldEvent e) {
        // System.out.println("[NeoTale-AllEvents][Player] AddPlayerToWorldEvent");
    }

    @SubscribeEvent
    public static void onDrainPlayerFromWorld(DrainPlayerFromWorldEvent e) {
        // System.out.println("[NeoTale-AllEvents][Player] DrainPlayerFromWorldEvent");
    }

    @SubscribeEvent
    public static void onPlayerChat(PlayerChatEvent e) {
        // System.out.println("[NeoTale-AllEvents][Player] PlayerChatEvent");
    }

    @SubscribeEvent
    public static void onPlayerConnect(PlayerConnectEvent e) {
        //  System.out.println("[NeoTale-AllEvents][Player] PlayerConnectEvent");
    }

    @SubscribeEvent
    public static void onPlayerCraft(PlayerCraftEvent e) {
        // System.out.println("[NeoTale-AllEvents][Player] PlayerCraftEvent");
    }

    @SubscribeEvent
    public static void onPlayerDisconnect(PlayerDisconnectEvent e) {
        // System.out.println("[NeoTale-AllEvents][Player] PlayerDisconnectEvent");
    }

    @SubscribeEvent
    public static void onPlayerInteract(PlayerInteractEvent e) {
        // System.out.println("[NeoTale-AllEvents][Player] PlayerInteractEvent");
    }
}
