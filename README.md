# Arrowhead
Minecraft mod for Projectile Weapon Unmojankery

## Server Component
- Callbacks when shooting projectiles via Bow & Crossbow (being able to modify and query item stack and projectile, like attaching additional data)
- Interfaces 'ArrowheadBow' and 'Arrowhead Crossbow', which dehardcode Projectile Velocity, Pull Times, Divergence & Bow Zoom

## Client Component
- Bow Zoom & Crossbow Reloading rendering via mixin into HeldItemRenderer & AbstractClientPlayerEntity. Vanilla has them hardcoded to check for Items.BOW & Items.CROSSBOW. Without that, modded bows and crossbows have broken animations
