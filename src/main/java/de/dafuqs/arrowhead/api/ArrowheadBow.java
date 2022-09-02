package de.dafuqs.arrowhead.api;

public interface ArrowheadBow {
	
	/**
	 * The higher this value, the more does the players view zoom in when using
	 * The normal bow has a zoom of 20
	 */
	default float getZoom() {
		return 20F;
	}
	
	/**
	 * The higher this value, the more velocity the shot projectile gets
	 * Note that this directly relates to damage with most projectiles, like arrows
	 * The normal bow equals a velocity mod of 1.0
	 */
	default float getProjectileVelocityModifier() {
		return 1.0F;
	}
	
	/**
	 * The lower this value, the more precise projectiles become
	 * The normal bow has uses divergence of 1.0
	 */
	default float getDivergence() {
		return 1.0F;
	}
	
}
