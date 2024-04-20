package de.dafuqs.arrowhead.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import de.dafuqs.arrowhead.api.ArrowheadCrossbow;
import de.dafuqs.arrowhead.api.CrossbowShootingCallback;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(CrossbowItem.class)
public class CrossbowItemMixin {
	
	@Inject(method = "getSpeed(Lnet/minecraft/item/ItemStack;)F", at = @At("RETURN"), cancellable = true)
	private static void getSpeed(ItemStack stack, CallbackInfoReturnable<Float> cir) {
		if(stack.getItem() instanceof ArrowheadCrossbow arrowheadCrossbow) {
			float speedMod = arrowheadCrossbow.getProjectileVelocityModifier(stack);
			if (speedMod != 1.0) {
				cir.setReturnValue((float) Math.ceil(speedMod * (cir.getReturnValue())));
			}
		}
	}
	
	@Inject(method = "getPullTime(Lnet/minecraft/item/ItemStack;)I", at = @At("RETURN"), cancellable = true)
	private static void getPullTime(ItemStack stack, CallbackInfoReturnable<Integer> cir) {
		if (stack.getItem() instanceof ArrowheadCrossbow arrowheadCrossbow) {
			cir.setReturnValue((int) Math.ceil(cir.getReturnValueI() * arrowheadCrossbow.getPullTimeModifier(stack)));
		}
	}
	
	@Inject(method = "shoot(Lnet/minecraft/world/World;Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/util/Hand;Lnet/minecraft/item/ItemStack;Lnet/minecraft/item/ItemStack;FZFFF)V",
			at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/projectile/ProjectileEntity;setVelocity(DDDFF)V", shift = At.Shift.AFTER))
	private static void shoot(World world, LivingEntity shooter, Hand hand, ItemStack crossbow, ItemStack projectile, float soundPitch, boolean creative, float speed, float divergence, float simulated, CallbackInfo ci, @Local ProjectileEntity projectileEntity, @Local Vector3f vec3f) {
		if(crossbow.getItem() instanceof ArrowheadCrossbow arrowheadCrossbow) {
			projectileEntity.setVelocity(vec3f.x(), vec3f.y(), vec3f.z(), speed * arrowheadCrossbow.getProjectileVelocityModifier(crossbow), divergence * arrowheadCrossbow.getDivergenceMod(crossbow));
		}
		
		for(CrossbowShootingCallback callback : CrossbowShootingCallback.callbacks) {
			callback.trigger(world, shooter, hand, crossbow, projectile, projectileEntity);
		}
	}
	
}
