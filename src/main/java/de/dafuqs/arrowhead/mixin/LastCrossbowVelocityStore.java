package de.dafuqs.arrowhead.mixin;

import org.jetbrains.annotations.ApiStatus;
import org.joml.Vector3f;

@ApiStatus.Internal
interface LastCrossbowVelocityStore {
    Vector3f arrowhead$getLastCrossbowVelocity();
    void arrowhead$setLastCrossbowVelocity(Vector3f vec);
}
