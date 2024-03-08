package it.kada49.mixin;

import it.kada49.DisableBurningAnimation;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameOverlayRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameOverlayRenderer.class)
public class InGameOverlayRendererMixin {
	@Inject(at = @At("HEAD"), method = "renderFireOverlay", cancellable = true)
	private static void init(MinecraftClient client, MatrixStack matrices, CallbackInfo info) {
		if (!DisableBurningAnimation.BURNING_ENABLED) info.cancel();
	}
}