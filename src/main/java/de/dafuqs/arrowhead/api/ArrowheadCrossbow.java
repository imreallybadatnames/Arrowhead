package de.dafuqs.arrowhead.api;

public interface ArrowheadCrossbow {

	/**
	 * The higher this value, the more velocity the shot projectile gets
	 * Note that this directly relates to damage with most projectiles, like arrows
	 * The normal crossbow equals a velocity mod of 1.0
	 */
	default float getProjectileVelocityModifier() {
		return 1.0F;
	}
	
	/**
	 * The higher this value, the longer it takes for the crossbow to load
	 * The normal crossbow equals a pull time mod of 1.0
	 */
	default float getPullTimeModifier() {
		return 1.0F;
	}
	
	/**
	 * The lower this value, the more precise projectiles become
	 * The normal crossbow equals a divergence of 1.0
	 */
	default float getDivergenceMod() {
		return 1.0F;
	}
	
}
