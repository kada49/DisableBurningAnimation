package it.kada49.mixin;

//#if MC>=12109
//$$ import net.minecraft.client.texture.Sprite;
//#endif

//#if MC>=12104
//$$ import net.minecraft.client.gui.hud.InGameOverlayRenderer;
//$$ import net.minecraft.client.render.VertexConsumerProvider;
//#else
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameOverlayRenderer;
//#endif

import it.kada49.DisableBurningAnimation;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(InGameOverlayRenderer.class)
public class InGameOverlayRendererMixin {
	@Inject(at = @At("HEAD"), method = "renderFireOverlay", cancellable = true)

	//#if MC>=12109
	//$$ private static void init(MatrixStack matrices, VertexConsumerProvider vertexConsumers, Sprite sprite, CallbackInfo info) {
	//#else
	//#if MC>=12104
	//$$ private static void init(MatrixStack matrices, VertexConsumerProvider vertexConsumers, CallbackInfo info) {
	//#else
	private static void init(MinecraftClient client, MatrixStack matrices, CallbackInfo info) {
	//#endif
	//#endif
		if (!DisableBurningAnimation.BURNING_ENABLED) info.cancel();
	}
}