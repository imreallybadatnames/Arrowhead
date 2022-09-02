package de.dafuqs.arrowhead.mixin;

import de.dafuqs.arrowhead.api.ArrowheadCrossbow;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CrossbowItem.class)
public class CrossbowItemMixin {
	
	@Inject(method = "getSpeed(Lnet/minecraft/item/ItemStack;)F", at = @At("RETURN"), cancellable = true)
	private static void getSpeed(ItemStack stack, CallbackInfoReturnable<Float> cir) {
		if(stack.getItem() instanceof ArrowheadCrossbow arrowheadCrossbow) {
			float speedMod = arrowheadCrossbow.getProjectileVelocityModifier();
			if (speedMod != 1.0) {
				cir.setReturnValue((float) Math.ceil(speedMod * (cir.getReturnValue())));
			}
		}
	}
	
	@Inject(method = "getPullTime(Lnet/minecraft/item/ItemStack;)I", at = @At("RETURN"), cancellable = true)
	private static void getPullTime(ItemStack stack, CallbackInfoReturnable<Integer> cir) {
		if (stack.getItem() instanceof ArrowheadCrossbow arrowheadCrossbow) {
			cir.setReturnValue((int) Math.ceil(cir.getReturnValueI() * arrowheadCrossbow.getPullTimeModifier()));
		}
	}
	
}
