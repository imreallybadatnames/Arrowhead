package de.dafuqs.arrowhead.api.internal;

import org.jetbrains.annotations.ApiStatus;
import org.joml.Vector3f;

// pls dont use outside of arrowhead ;(
@ApiStatus.Internal
public interface LastCrossbowVelocityStore {
    Vector3f arrowhead$getLastCrossbowVelocity();
    void arrowhead$setLastCrossbowVelocity(Vector3f vec);
}
