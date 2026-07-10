package it.kada49.mixin;

//#if MC>=12109
//$$ import net.minecraft.client.texture.Sprite;
//#endif

//#if MC>=12104
//$$ import net.minecraft.client.gui.hud.InGameOverlayRenderer;
//#if MC>=260200
//$$ import net.minecraft.client.renderer.SubmitNodeCollector;
//#else
//$$ import net.minecraft.client.render.VertexConsumerProvider;
//#endif
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
	//#if MC>=260200
	//$$ @Inject(at = @At("HEAD"), method = "submitFire", cancellable = true)
	//#else
	@Inject(at = @At("HEAD"), method = "renderFireOverlay", cancellable = true)
	//#endif

	//#if MC>=260200
	//$$ private static void init(PoseStack matrices, SubmitNodeCollector vertexConsumers, TextureAtlasSprite sprite, CallbackInfo info) {
	//#else
	//#if MC>=12109
	//$$ private static void init(MatrixStack matrices, VertexConsumerProvider vertexConsumers, Sprite sprite, CallbackInfo info) {
	//#else
	//#if MC>=12104
	//$$ private static void init(MatrixStack matrices, VertexConsumerProvider vertexConsumers, CallbackInfo info) {
	//#else
	private static void init(MinecraftClient client, MatrixStack matrices, CallbackInfo info) {
	//#endif
	//#endif
	//#endif
		if (!DisableBurningAnimation.BURNING_ENABLED) info.cancel();
	}
}