package de.dafuqs.arrowhead.mixin;

import net.minecraft.entity.projectile.ProjectileEntity;
import org.joml.Vector3f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(ProjectileEntity.class)
public class ProjectileEntityMixin implements LastCrossbowVelocityStore {
    @Unique
    private Vector3f arrowhead$lastCrossbowVelocity;
    @Override
    public Vector3f arrowhead$getLastCrossbowVelocity() {
        return arrowhead$lastCrossbowVelocity;
    }

    @Override
    public void arrowhead$setLastCrossbowVelocity(Vector3f vec) {
        arrowhead$lastCrossbowVelocity = vec;
    }
}
