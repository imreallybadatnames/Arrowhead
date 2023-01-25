package de.dafuqs.arrowhead.mixin.client;

import de.dafuqs.arrowhead.api.ArrowheadBow;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Environment(EnvType.CLIENT)
@Mixin(AbstractClientPlayerEntity.class)
public abstract class AbstractClientPlayerEntityMixin {
	
	@Inject(method = "getFovMultiplier", at = @At(value = "TAIL"), locals = LocalCapture.CAPTURE_FAILSOFT, cancellable = true)
	private void arrowhead$applyCustomBowZoom(CallbackInfoReturnable<Float> cir, float f) {
		AbstractClientPlayerEntity thisPlayer = (AbstractClientPlayerEntity)(Object) this;
		ItemStack itemStack = thisPlayer.getActiveItem();
		if (thisPlayer.isUsingItem() && itemStack.getItem() instanceof ArrowheadBow arrowheadBow) {
			int i = thisPlayer.getItemUseTime();
			float g = (float) i / arrowheadBow.getZoom(itemStack);
			
			if (g > 1.0F) {
				g = 1.0F;
			} else {
				g *= g;
			}
			
			f *= 1.0F - g * 0.15F;
			
			cir.setReturnValue(MathHelper.lerp((MinecraftClient.getInstance().options.getFovEffectScale().getValue()).floatValue(), 1.0F, f));
		}
		
	}

}
