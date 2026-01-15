package net.liopyu.doublejump.system;

import com.hypixel.hytale.component.*;
import com.hypixel.hytale.component.query.Query;
import com.hypixel.hytale.component.system.tick.EntityTickingSystem;
import com.hypixel.hytale.protocol.MovementStates;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.entity.entities.player.movement.MovementManager;
import com.hypixel.hytale.server.core.entity.movement.MovementStatesComponent;
import com.hypixel.hytale.server.core.modules.physics.component.Velocity;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import net.liopyu.doublejump.DoubleJumpState;
import net.liopyu.doublejump.util.LogUtil;

import javax.annotation.Nullable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class PlayerDoubleJumpSystem extends EntityTickingSystem<EntityStore> {

    private final ComponentType<EntityStore, Player> playerType = Player.getComponentType();
    private final ComponentType<EntityStore, MovementStatesComponent> movementStatesType = MovementStatesComponent.getComponentType();
    private final ComponentType<EntityStore, MovementManager> movementManagerType = MovementManager.getComponentType();
    private final ComponentType<EntityStore, Velocity> velocityType = Velocity.getComponentType();
    private final ComponentType<EntityStore, DoubleJumpState> stateType;

    public PlayerDoubleJumpSystem(ComponentType<EntityStore, DoubleJumpState> stateType) {
        this.stateType = stateType;
    }

    @Nullable
    @Override
    public Query<EntityStore> getQuery() {
        return Query.and(
                Player.getComponentType(),
                MovementStatesComponent.getComponentType(),
                MovementManager.getComponentType(),
                Velocity.getComponentType(),
                stateType
        );
    }

    @Override
    public void tick(float dt, int index, ArchetypeChunk<EntityStore> chunk, Store<EntityStore> store, CommandBuffer<EntityStore> commandBuffer) {
        Ref<EntityStore> ref = chunk.getReferenceTo(index);
        if (ref == null) return;

        Player player = chunk.getComponent(index, playerType);
        if (player == null) return;
player.sendMessage(Message.raw("yo y o yo"));
        MovementStatesComponent msc = store.getComponent(ref, movementStatesType);
        if (msc == null) {
            LogUtil.getLogger().atInfo().log("DoubleJump: no MovementStatesComponent for entityId=%d name=%s", player.getDisplayName(), player.getDisplayName());
            return;
        }

        MovementStates ms = msc.getMovementStates();
        boolean grounded = readBool(ms, "isGrounded", "getGrounded", "isOnGround", "getOnGround", "grounded", "onGround", "touchingGround");
        boolean jumpPressed = readBool(ms, "isJumpPressed", "getJumpPressed", "isJumping", "getJumping", "jumpPressed", "jumping", "wantsJump", "jump", "jumpRequest", "jumpRequested");

        DoubleJumpState state = store.ensureAndGetComponent(ref, stateType);

        if (grounded && !state.wasGrounded) {
            LogUtil.getLogger().atInfo().log("DoubleJump: landed entityId=%d name=%s ms=%s", player.getDisplayName(), player.getDisplayName(), String.valueOf(ms));
        }

        if (grounded) {
            if (state.usedDoubleJump) {
                LogUtil.getLogger().atInfo().log("DoubleJump: reset double jump entityId=%d name=%s", player.getDisplayName(), player.getDisplayName());
            }
            state.usedDoubleJump = false;
        }

        boolean jumpRisingEdge = jumpPressed && !state.lastJumpPressed;

        if (!grounded && jumpRisingEdge) {
            LogUtil.getLogger().atInfo().log("DoubleJump: jump press in air entityId=%d name=%s used=%s ms=%s", player.getDisplayName(), player.getDisplayName(), String.valueOf(state.usedDoubleJump), String.valueOf(ms));
        }

        if (!grounded && jumpRisingEdge && !state.usedDoubleJump) {
            state.usedDoubleJump = true;
            boolean applied = applyJumpImpulse(store, ref, player);
            LogUtil.getLogger().atInfo().log("DoubleJump: applied=%s entityId=%d name=%s", String.valueOf(applied), player.getDisplayName(), player.getDisplayName());
        }

        state.wasGrounded = grounded;
        state.lastJumpPressed = jumpPressed;
    }

    private boolean applyJumpImpulse(Store<EntityStore> store, Ref<EntityStore> ref, Player player) {
        MovementManager mm = store.getComponent(ref, movementManagerType);
        if (mm == null || mm.getSettings() == null) {
            LogUtil.getLogger().atInfo().log("DoubleJump: missing MovementManager/settings entityId=%d name=%s", player.getDisplayName(), player.getDisplayName());
            return false;
        }

        Velocity vel = store.ensureAndGetComponent(ref, velocityType);

        double beforeY = vel.getY();
        float jumpForce = mm.getSettings().jumpForce;
        vel.setY(Math.max(beforeY, (double)jumpForce));

        LogUtil.getLogger().atInfo().log(
                "DoubleJump: velocityY %.3f -> %.3f jumpForce=%.3f entityId=%d name=%s",
                beforeY, vel.getY(), jumpForce, player.getDisplayName(), player.getDisplayName()
        );

        return true;
    }

    private boolean readBool(Object obj, String... candidates) {
        if (obj == null) return false;

        Class<?> c = obj.getClass();

        for (String name : candidates) {
            try {
                Method m = c.getMethod(name);
                Object v = m.invoke(obj);
                if (v instanceof Boolean b) return b;
            } catch (Throwable ignored) {
            }
        }

        for (String name : candidates) {
            try {
                Field f = c.getDeclaredField(name);
                f.setAccessible(true);
                Object v = f.get(obj);
                if (v instanceof Boolean b) return b;
            } catch (Throwable ignored) {
            }
        }

        return false;
    }

}
