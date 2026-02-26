package net.trisodium.mixin;

import net.minecraft.src.RenderGlobal;
import net.trisodium.Trisodium;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RenderGlobal.class)
public class MixinRenderGlobal {

    @Inject(method = "updateChunks", at = @At("HEAD"), cancellable = true)
    private void trisodium$updateChunks(CallbackInfo ci) {
        // We kill the vanilla Display List updates immediately
        if (Trisodium.isEnabled()) {
            Trisodium.getRenderer().update();
            ci.cancel();
        }
    }

    @Inject(method = "renderAllRenderLists", at = @At("HEAD"), cancellable = true)
    private void trisodium$renderAll(int i, double d, CallbackInfo ci) {
        // This stops the game from trying to call glCallLists
        if (Trisodium.isEnabled()) {
            Trisodium.getRenderer().draw(i, d);
            ci.cancel();
        }
    }
}
