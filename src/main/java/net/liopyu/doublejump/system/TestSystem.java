package net.liopyu.doublejump.system;

import com.hypixel.hytale.component.ArchetypeChunk;
import com.hypixel.hytale.component.CommandBuffer;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.component.dependency.Dependency;
import com.hypixel.hytale.component.dependency.Order;
import com.hypixel.hytale.component.dependency.SystemDependency;
import com.hypixel.hytale.component.dependency.SystemTypeDependency;
import com.hypixel.hytale.component.query.Query;
import com.hypixel.hytale.component.system.EntityEventSystem;
import com.hypixel.hytale.component.system.ISystem;
import com.hypixel.hytale.component.system.tick.EntityTickingSystem;
import com.hypixel.hytale.logger.HytaleLogger;
import com.hypixel.hytale.math.vector.Vector3d;
import com.hypixel.hytale.protocol.ChangeVelocityType;
import com.hypixel.hytale.protocol.MovementStates;
import com.hypixel.hytale.protocol.packets.entities.ChangeVelocity;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.entity.movement.MovementStatesComponent;
import com.hypixel.hytale.server.core.event.events.ecs.BreakBlockEvent;
import com.hypixel.hytale.server.core.modules.debug.DebugUtils;
import com.hypixel.hytale.server.core.modules.entity.EntityModule;
import com.hypixel.hytale.server.core.modules.entity.component.TransformComponent;
import com.hypixel.hytale.server.core.modules.physics.component.Velocity;
import com.hypixel.hytale.server.core.modules.physics.systems.GenericVelocityInstructionSystem;
import com.hypixel.hytale.server.core.modules.splitvelocity.VelocityConfig;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.hypixel.hytale.server.worldgen.util.LogUtil;
import net.liopyu.doublejump.DoubleJump;
import net.liopyu.doublejump.DoubleJumpState;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Set;

public class TestSystem {

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
            player.moveTo(reference, 0, 1, 0, commandBuffer);


        }

        @Override
        public @Nullable Query<EntityStore> getQuery() {
            return Query.and(Velocity.getComponentType(), PlayerRef.getComponentType());
        }
    }

    public static class PlayerVelocityInstructionSystem extends EntityTickingSystem<EntityStore> {
        @Nonnull
        private final Set<Dependency<EntityStore>> dependencies;
        @Nonnull
        private final Query<EntityStore> query;

        public PlayerVelocityInstructionSystem() {
            this.dependencies = Set.of(new SystemDependency(Order.BEFORE, GenericVelocityInstructionSystem.class), new SystemTypeDependency(Order.AFTER, EntityModule.get().getVelocityModifyingSystemType()));
            this.query = Query.and(new Query[]{PlayerRef.getComponentType(), Velocity.getComponentType()});
        }

        public void tick(float dt, int index, @Nonnull ArchetypeChunk<EntityStore> archetypeChunk, @Nonnull Store<EntityStore> store, @Nonnull CommandBuffer<EntityStore> commandBuffer) {
            PlayerRef playerRefComponent = (PlayerRef) archetypeChunk.getComponent(index, PlayerRef.getComponentType());

            assert playerRefComponent != null;

            Velocity velocityComponent = (Velocity) archetypeChunk.getComponent(index, Velocity.getComponentType());

            assert velocityComponent != null;

            for (Velocity.Instruction instruction : velocityComponent.getInstructions()) {
                Vector3d velocity = instruction.getVelocity();
                VelocityConfig velocityConfig = instruction.getConfig();
                switch (instruction.getType()) {
                    case Set:
                        playerRefComponent.getPacketHandler().writeNoCache(new ChangeVelocity((float) velocity.x, (float) velocity.y, (float) velocity.z, ChangeVelocityType.Set, velocityConfig != null ? velocityConfig.toPacket() : null));
                        if (DebugUtils.DISPLAY_FORCES) {
                            TransformComponent transformComponent = (TransformComponent) archetypeChunk.getComponent(index, TransformComponent.getComponentType());

                            assert transformComponent != null;

                            World world = ((EntityStore) commandBuffer.getExternalData()).getWorld();
                            DebugUtils.addForce(world, transformComponent.getPosition(), velocity, velocityConfig);
                        }
                        break;
                    case Add:
                        playerRefComponent.getPacketHandler().writeNoCache(new ChangeVelocity((float) velocity.x, (float) velocity.y, (float) velocity.z, ChangeVelocityType.Add, velocityConfig != null ? velocityConfig.toPacket() : null));
                        if (DebugUtils.DISPLAY_FORCES) {
                            TransformComponent transformComponent = (TransformComponent) archetypeChunk.getComponent(index, TransformComponent.getComponentType());

                            assert transformComponent != null;

                            World world = ((EntityStore) commandBuffer.getExternalData()).getWorld();
                            DebugUtils.addForce(world, transformComponent.getPosition(), new Vector3d(velocity.x, velocity.y, velocity.z), velocityConfig);
                        }
                }
            }

            velocityComponent.getInstructions().clear();
        }

        @Nonnull
        public Set<Dependency<EntityStore>> getDependencies() {
            return this.dependencies;
        }

        @Nonnull
        public Query<EntityStore> getQuery() {
            return this.query;
        }
    }

    public static class DoubleJumpInitSystem extends EntityTickingSystem<EntityStore> {

        @Override
        public void onSystemRegistered() {
            System.out.println("[DoubleJump] DoubleJumpInitSystem registered");
        }

        @Override
        public void tick(
                float dt,
                int index,
                @Nonnull ArchetypeChunk<EntityStore> chunk,
                @Nonnull Store<EntityStore> store,
                @Nonnull CommandBuffer<EntityStore> commandBuffer
        ) {
            if (index != 0) {
                return;
            }

            // System.out.println("[DoubleJump] Init tick: chunkSize=" + chunk.size());
        }

        @Override
        public @Nullable Query<EntityStore> getQuery() {
            var componentType = Player.getComponentType();
            if (componentType != null) {
                return componentType;
            }
            return Query.any();
        }
    }

    public static class DoubleJumpTickSystem extends EntityTickingSystem<EntityStore> {

        private boolean lastGrounded = false;
        private boolean lastJumping = false;
        private int heartbeat = 0;

        @Override
        public void onSystemRegistered() {
            System.out.println("[DoubleJump] DoubleJumpTickSystem registered");
        }

        @Override
        public void tick(
                float dt,
                int index,
                @Nonnull ArchetypeChunk<EntityStore> chunk,
                @Nonnull Store<EntityStore> store,
                @Nonnull CommandBuffer<EntityStore> commandBuffer
        ) {
            if (true) {
                //DoubleJump.LOGGER.atInfo().log("Test");
                return;
            }
            var ref = chunk.getReferenceTo(index);

            var player = store.getComponent(ref, Player.getComponentType());
            if (player == null) {
                return;
            }

            var movement = store.getComponent(ref, MovementStatesComponent.getComponentType());
            if (movement == null) {
                if (heartbeat-- <= 0) {
                    System.out.println("[DoubleJump] Tick: player=" + player.getDisplayName() + " movement=null");
                    heartbeat = 40;
                }
                return;
            }

            MovementStates ms = movement.getMovementStates();

            boolean grounded = ms.onGround;
            boolean jumping = ms.jumping;

            if (grounded != lastGrounded) {
                System.out.println("[DoubleJump] Grounded changed: player=" + player.getDisplayName() + " grounded=" + grounded);
                lastGrounded = grounded;
            }

            if (jumping != lastJumping) {
                System.out.println("[DoubleJump] Jumping changed: player=" + player.getDisplayName() + " jumping=" + jumping);
                lastJumping = jumping;
            }

            if (heartbeat-- <= 0) {
                System.out.println("[DoubleJump] Heartbeat: player=" + player.getDisplayName()
                        + " grounded=" + grounded
                        + " jumping=" + jumping
                        + " dt=" + dt
                        + " chunkSize=" + chunk.size()
                        + " index=" + index);
                heartbeat = 40;
            }
        }

        @Override
        public @Nullable Query<EntityStore> getQuery() {
            var componentType = Player.getComponentType();
            if (componentType != null) {
                return componentType;
            }
            return Query.any();
        }
    }

}
