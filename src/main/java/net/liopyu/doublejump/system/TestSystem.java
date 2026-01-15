package net.liopyu.doublejump.system;

import com.hypixel.hytale.component.ArchetypeChunk;
import com.hypixel.hytale.component.CommandBuffer;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.component.query.Query;
import com.hypixel.hytale.component.system.EntityEventSystem;
import com.hypixel.hytale.component.system.ISystem;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.event.events.ecs.BreakBlockEvent;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TestSystem implements ISystem<EntityStore> {

    public static class BlockBreakEventSystem extends EntityEventSystem<EntityStore, BreakBlockEvent> {

        public BlockBreakEventSystem(@Nonnull Class<BreakBlockEvent> eventType) {
            super(eventType);
        }

        @Override
        public void handle(
                int index,
                @Nonnull ArchetypeChunk<EntityStore> archetypeChunk,
                @Nonnull Store<EntityStore> store,
                @Nonnull CommandBuffer<EntityStore> commandBuffer,
                @Nonnull BreakBlockEvent event
        ) {
            var reference = archetypeChunk.getReferenceTo(index);
            var player = store.getComponent(reference, Player.getComponentType());
            player.sendMessage(Message.raw("Testing Testing 123"));
        }

        @Override
        public @Nullable Query<EntityStore> getQuery() {
            return PlayerRef.getComponentType();
        }
    }
}
