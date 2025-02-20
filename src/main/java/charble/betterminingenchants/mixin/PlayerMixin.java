package charble.betterminingenchants.mixin;

import charble.betterminingenchants.BetterMiningEnchants;
import charble.betterminingenchants.attribute.ModAttributes;
import charble.betterminingenchants.util.ModTags;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(PlayerEntity.class)
public class PlayerMixin {

    @Inject(at = @At("TAIL"),
            method = "getBlockBreakingSpeed",
            cancellable = true)
    private void injectGetBlockBreakingSpeed(BlockState block, CallbackInfoReturnable<Float> cir){
        PlayerEntity player = (PlayerEntity) (Object) this;

        if(block.isIn(ModTags.Blocks.OBSIDIAN_EFFICIENCY_MINEABLE)) {
            float originalret = cir.getReturnValue();
            originalret += (20.0f * ((float) player.getAttributeValue(ModAttributes.PLAYER_OBSIDIAN_MINING_EFFICIENCY) - 1));
            cir.setReturnValue(originalret);
        }
    }

    @Inject(at = @At("TAIL"),
            method = "createPlayerAttributes",
            cancellable = true)
    private static void injectedCreatePlayerAttributes(CallbackInfoReturnable<DefaultAttributeContainer.Builder> cir) {
        DefaultAttributeContainer.Builder builder = cir.getReturnValue();

        builder.add(ModAttributes.PLAYER_OBSIDIAN_MINING_EFFICIENCY);

        cir.setReturnValue(builder);
    }
}

