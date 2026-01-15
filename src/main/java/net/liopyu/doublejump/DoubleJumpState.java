package net.liopyu.doublejump;

import com.hypixel.hytale.component.Component;
import com.hypixel.hytale.component.ComponentType;
import com.hypixel.hytale.server.core.universe.Universe;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;


public class DoubleJumpState implements Component<EntityStore> {
    public boolean hadExtraJump = true;
    public boolean wasGrounded = false;
    public boolean wasJumpHeld = false;
    public int debugCooldown = 0;
    public double doubleJumpImpulse = 11.8;

    public static ComponentType<EntityStore, DoubleJumpState> getComponentType() {
        return Universe.get().getPlayerRefComponentType().getRegistry().registerComponent(DoubleJumpState.class, DoubleJumpState::new);
    }

    @Override
    public Component<EntityStore> clone() {
        var s = new DoubleJumpState();
        s.hadExtraJump = hadExtraJump;
        s.wasGrounded = wasGrounded;
        s.wasJumpHeld = wasJumpHeld;
        s.debugCooldown = debugCooldown;
        s.doubleJumpImpulse = doubleJumpImpulse;
        return s;
    }
}