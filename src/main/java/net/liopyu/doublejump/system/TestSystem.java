package net.liopyu.doublejump.system;

import com.hypixel.hytale.component.ArchetypeChunk;
import com.hypixel.hytale.component.CommandBuffer;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.component.query.Query;
import com.hypixel.hytale.component.system.EntityEventSystem;
import com.hypixel.hytale.component.system.ISystem;
import com.hypixel.hytale.component.system.tick.EntityTickingSystem;
import com.hypixel.hytale.protocol.MovementStates;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.entity.movement.MovementStatesComponent;
import com.hypixel.hytale.server.core.event.events.ecs.BreakBlockEvent;
import com.hypixel.hytale.server.core.modules.physics.component.Velocity;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.hypixel.hytale.server.worldgen.util.LogUtil;
import net.liopyu.doublejump.DoubleJumpState;

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

    public static class DoubleJumpTickSystem extends EntityTickingSystem<EntityStore> {

        @Override
        public void tick(
                float dt,
                int index,
                @Nonnull ArchetypeChunk<EntityStore> chunk,
                @Nonnull Store<EntityStore> store,
                @Nonnull CommandBuffer<EntityStore> commandBuffer
        ) {
            var ref = chunk.getReferenceTo(index);

            var player = store.getComponent(ref, Player.getComponentType());
            var movement = store.getComponent(ref, MovementStatesComponent.getComponentType());
            var velocity = store.getComponent(ref, Velocity.getComponentType());
            var state = store.ensureAndGetComponent(ref, DoubleJumpState.getComponentType());

            if (player == null || movement == null || velocity == null || state == null) {
                return;
            }

            MovementStates ms = movement.getMovementStates();

            boolean grounded = ms.onGround;
            boolean jumpHeld = ms.jumping;

            if (state.debugCooldown <= 0) {
                LogUtil.getLogger().atInfo().log("DJ %s grounded=%s jumpHeld=%s states=%s",
                        player.getDisplayName(),
                        grounded,
                        jumpHeld,
                        ms.toString()
                );
                state.debugCooldown = 20;
            } else {
                state.debugCooldown--;
            }

            if (grounded) {
                state.hadExtraJump = true;
            }

            boolean jumpEdge = jumpHeld && !state.wasJumpHeld;

            if (!grounded && jumpEdge && state.hadExtraJump) {
                state.hadExtraJump = false;

                double y = velocity.getY();
                if (y < 0) {
                    velocity.setY(0);
                }

                velocity.addForce(0, state.doubleJumpImpulse, 0);

                LogUtil.getLogger().atInfo().log("DJ used by %s impulse=%s", player.getDisplayName(), state.doubleJumpImpulse);
            }

            state.wasJumpHeld = jumpHeld;
            state.wasGrounded = grounded;
        }

        @Nullable
        @Override
        public Query<EntityStore> getQuery() {
            return null;
        }
    }
}
