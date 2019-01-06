package juuxel.thisandthat.mixin;

import juuxel.thisandthat.lib.ModTags;
import juuxel.thisandthat.util.ModMultipart;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.TorchBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ViewableWorld;
import net.shadowfacts.simplemultipart.container.MultipartContainer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(TorchBlock.class)
public class TorchPlacementMixin {
    @Inject(method = "canPlaceAt", at = @At("RETURN"), cancellable = true, locals = LocalCapture.CAPTURE_FAILEXCEPTION)
    private void onCanPlaceAt(BlockState state, ViewableWorld world, BlockPos pos, CallbackInfoReturnable<Boolean> info,
                              BlockPos downPos, BlockState downState, Block downBlock) {
        BlockEntity entity = world.getBlockEntity(downPos);
        if (downBlock.matches(ModTags.INSTANCE.getPost())) {
            info.setReturnValue(true);
            info.cancel();
        } else if (entity instanceof MultipartContainer) {
            if (((MultipartContainer) entity).getParts().stream().anyMatch(p ->
                    p.getMultipart() instanceof ModMultipart && ((ModMultipart) p.getMultipart()).canSupportTorches(
                        p.getState(), world, pos
                    ))) {
                info.setReturnValue(true);
                info.cancel();
            }
        }
    }
}