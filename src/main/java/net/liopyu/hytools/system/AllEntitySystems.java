package net.liopyu.hytools.system;

import com.hypixel.hytale.component.ArchetypeChunk;
import com.hypixel.hytale.component.CommandBuffer;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.component.query.Query;
import com.hypixel.hytale.component.system.EntityEventSystem;
import com.hypixel.hytale.component.system.ISystem;
import com.hypixel.hytale.server.core.event.events.ecs.BreakBlockEvent;
import com.hypixel.hytale.server.core.event.events.ecs.DropItemEvent;
import com.hypixel.hytale.server.core.event.events.ecs.PlaceBlockEvent;
import com.hypixel.hytale.server.core.event.events.ecs.UseBlockEvent;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import net.liopyu.neotale.api.eventbus.EventBusSubscriber;
import net.liopyu.neotale.api.system.SubscribeSystem;
import net.liopyu.neotale.api.system.SystemStore;

import javax.annotation.Nonnull;

@EventBusSubscriber
public final class AllEntitySystems {

    @SubscribeSystem(store = SystemStore.ENTITY, priority = 0)
    public static ISystem<EntityStore> breakBlockSystem() {
        return BreakBlockSystem.INSTANCE;
    }

    @SubscribeSystem(store = SystemStore.ENTITY, priority = 1)
    public static ISystem<EntityStore> useBlockSystem() {
        return UseBlockSystem.INSTANCE;
    }

    @SubscribeSystem(store = SystemStore.ENTITY, priority = 2)
    public static ISystem<EntityStore> placeBlockSystem() {
        return PlaceBlockSystem.INSTANCE;
    }

    @SubscribeSystem(store = SystemStore.ENTITY, priority = 3)
    public static ISystem<EntityStore> dropItemSystem() {
        return DropItemSystem.INSTANCE;
    }

    public static final class BreakBlockSystem extends EntityEventSystem<EntityStore, BreakBlockEvent> {
        public static final BreakBlockSystem INSTANCE = new BreakBlockSystem();

        private BreakBlockSystem() {
            super(BreakBlockEvent.class);
        }

        @Override
        public void handle(int i, @Nonnull ArchetypeChunk<EntityStore> chunk, @Nonnull Store<EntityStore> store, @Nonnull CommandBuffer<EntityStore> buffer, @Nonnull BreakBlockEvent e) {
            //  System.out.println("[AllEntitySystems] BreakBlockSystem");
        }

        @Override
        public Query<EntityStore> getQuery() {
            return Query.any();
        }
    }

    public static final class UseBlockSystem extends EntityEventSystem<EntityStore, UseBlockEvent> {
        public static final UseBlockSystem INSTANCE = new UseBlockSystem();

        private UseBlockSystem() {
            super(UseBlockEvent.class);
        }

        @Override
        public void handle(int i, @Nonnull ArchetypeChunk<EntityStore> chunk, @Nonnull Store<EntityStore> store, @Nonnull CommandBuffer<EntityStore> buffer, @Nonnull UseBlockEvent e) {
            // System.out.println("[AllEntitySystems] UseBlockSystem");
        }

        @Override
        public Query<EntityStore> getQuery() {
            return Query.any();
        }
    }

    public static final class PlaceBlockSystem extends EntityEventSystem<EntityStore, PlaceBlockEvent> {
        public static final PlaceBlockSystem INSTANCE = new PlaceBlockSystem();

        private PlaceBlockSystem() {
            super(PlaceBlockEvent.class);
        }

        @Override
        public void handle(int i, @Nonnull ArchetypeChunk<EntityStore> chunk, @Nonnull Store<EntityStore> store, @Nonnull CommandBuffer<EntityStore> buffer, @Nonnull PlaceBlockEvent e) {
            // System.out.println("[AllEntitySystems] PlaceBlockSystem");
        }

        @Override
        public Query<EntityStore> getQuery() {
            return Query.any();
        }
    }

    public static final class DropItemSystem extends EntityEventSystem<EntityStore, DropItemEvent> {
        public static final DropItemSystem INSTANCE = new DropItemSystem();

        private DropItemSystem() {
            super(DropItemEvent.class);
        }

        @Override
        public void handle(int i, @Nonnull ArchetypeChunk<EntityStore> chunk, @Nonnull Store<EntityStore> store, @Nonnull CommandBuffer<EntityStore> buffer, @Nonnull DropItemEvent e) {
            // System.out.println("[AllEntitySystems] DropItemSystem");
        }

        @Override
        public Query<EntityStore> getQuery() {
            return Query.any();
        }
    }
}
