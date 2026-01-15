package net.liopyu.doublejump;

import com.hypixel.hytale.component.Component;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;

public class DoubleJumpState implements Component<EntityStore> {
    public boolean wasGrounded;
    public boolean usedDoubleJump;
    public boolean lastJumpPressed;

    @Override
    public Component<EntityStore> clone() {
        return this;
    }
}
