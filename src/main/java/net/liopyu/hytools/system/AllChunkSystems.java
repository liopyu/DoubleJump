package net.liopyu.hytools.system;

import com.hypixel.hytale.component.AddReason;
import com.hypixel.hytale.component.CommandBuffer;
import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.RemoveReason;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.component.query.Query;
import com.hypixel.hytale.component.system.ISystem;
import com.hypixel.hytale.component.system.RefSystem;
import com.hypixel.hytale.server.core.universe.world.storage.ChunkStore;
import net.liopyu.neotale.api.eventbus.EventBusSubscriber;
import net.liopyu.neotale.api.system.SubscribeSystem;
import net.liopyu.neotale.api.system.SystemStore;

import javax.annotation.Nonnull;

@EventBusSubscriber
public final class AllChunkSystems {

    @SubscribeSystem(store = SystemStore.CHUNK, priority = 0)
    public static ISystem<ChunkStore> chunkWatcherA() {
        return ChunkWatcherA.INSTANCE;
    }

    @SubscribeSystem(store = SystemStore.CHUNK, priority = 1)
    public static ISystem<ChunkStore> chunkWatcherB() {
        return ChunkWatcherB.INSTANCE;
    }

    @SubscribeSystem(store = SystemStore.CHUNK, priority = 2)
    public static ISystem<ChunkStore> chunkWatcherC() {
        return ChunkWatcherC.INSTANCE;
    }

    public static final class ChunkWatcherA extends RefSystem<ChunkStore> {
        public static final ChunkWatcherA INSTANCE = new ChunkWatcherA();

        private ChunkWatcherA() {
        }

        @Override
        public Query<ChunkStore> getQuery() {
            return Query.any();
        }

        @Override
        public void onEntityAdded(@Nonnull Ref<ChunkStore> ref, @Nonnull AddReason reason, @Nonnull Store<ChunkStore> store, @Nonnull CommandBuffer<ChunkStore> buffer) {
            //System.out.println("[AllChunkSystems] ChunkWatcherA added=" + reason.name());
        }

        @Override
        public void onEntityRemove(@Nonnull Ref<ChunkStore> ref, @Nonnull RemoveReason reason, @Nonnull Store<ChunkStore> store, @Nonnull CommandBuffer<ChunkStore> buffer) {
            // System.out.println("[AllChunkSystems] ChunkWatcherA remove=" + reason.name());
        }
    }

    public static final class ChunkWatcherB extends RefSystem<ChunkStore> {
        public static final ChunkWatcherB INSTANCE = new ChunkWatcherB();

        private ChunkWatcherB() {
        }

        @Override
        public Query<ChunkStore> getQuery() {
            return Query.any();
        }

        @Override
        public void onEntityAdded(@Nonnull Ref<ChunkStore> ref, @Nonnull AddReason reason, @Nonnull Store<ChunkStore> store, @Nonnull CommandBuffer<ChunkStore> buffer) {
            // System.out.println("[AllChunkSystems] ChunkWatcherB added=" + reason.name());
        }

        @Override
        public void onEntityRemove(@Nonnull Ref<ChunkStore> ref, @Nonnull RemoveReason reason, @Nonnull Store<ChunkStore> store, @Nonnull CommandBuffer<ChunkStore> buffer) {
            // System.out.println("[AllChunkSystems] ChunkWatcherB remove=" + reason.name());
        }
    }

    public static final class ChunkWatcherC extends RefSystem<ChunkStore> {
        public static final ChunkWatcherC INSTANCE = new ChunkWatcherC();

        private ChunkWatcherC() {
        }

        @Override
        public Query<ChunkStore> getQuery() {
            return Query.any();
        }

        @Override
        public void onEntityAdded(@Nonnull Ref<ChunkStore> ref, @Nonnull AddReason reason, @Nonnull Store<ChunkStore> store, @Nonnull CommandBuffer<ChunkStore> buffer) {
            // System.out.println("[AllChunkSystems] ChunkWatcherC added=" + reason.name());
        }

        @Override
        public void onEntityRemove(@Nonnull Ref<ChunkStore> ref, @Nonnull RemoveReason reason, @Nonnull Store<ChunkStore> store, @Nonnull CommandBuffer<ChunkStore> buffer) {
            // System.out.println("[AllChunkSystems] ChunkWatcherC remove=" + reason.name());
        }
    }
}
