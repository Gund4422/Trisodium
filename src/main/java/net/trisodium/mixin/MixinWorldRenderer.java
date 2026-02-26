package net.trisodium.mixin;

import net.minecraft.src.WorldRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldRenderer.class)
public class MixinWorldRenderer {
    // In Beta, this is the 'needsUpdate' flag
    @Shadow public boolean needsUpdate;

    @Inject(method = "updateRenderer", at = @At("HEAD"), cancellable = true)
    public void onUpdate(CallbackInfo ci) {
        // If we are using Trisodium, we stop the vanilla 
        // display list building immediately.
        if (this.needsUpdate) {
            // Your code to send block data to the GPU via VBOs goes here
            ci.cancel();
        }
    }
}
