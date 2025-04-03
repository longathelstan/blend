package blend.mixins.minecraft;

import blend.Client;
import blend.event.EventBus;
import blend.event.InputHandleEvent;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {

    @Inject(
            method = "close",
            at = @At(
                    value = "HEAD"
            )
    )
    private void closeClient(CallbackInfo ci) {
        try {
            Client.INSTANCE.close();
        } catch (Exception e) {
            Client.INSTANCE.getLogger().error("Error closing down client", e);
        }
    }

    @Inject(
            method = "handleInputEvents",
            at = @At(
                    value = "HEAD"
            )
    )
    private void onHandleInputs(CallbackInfo ci) {
        EventBus.INSTANCE.post(InputHandleEvent.INSTANCE);
    }

}
