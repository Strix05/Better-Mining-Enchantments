package charble.betterminingenchants.mixin;

import charble.betterminingenchants.BetterMiningEnchants;
import charble.betterminingenchants.enchantment.ModEnchantments;
import charble.betterminingenchants.util.ModTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.input.SingleStackRecipeInput;
import net.minecraft.registry.BuiltinRegistries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;

@Mixin(Block.class)
public class BlockMixin
{
    @Inject(at = @At("RETURN"),
            method = "getDroppedStacks(Lnet/minecraft/block/BlockState;Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/entity/BlockEntity;Lnet/minecraft/entity/Entity;Lnet/minecraft/item/ItemStack;)Ljava/util/List;",
            cancellable = true)
    private static void InjectGetDroppedStacks(BlockState state, ServerWorld world, BlockPos pos, BlockEntity blockEntity, Entity entity, ItemStack stack, CallbackInfoReturnable<List<ItemStack>> cir)
    {
        List<ItemStack> newDrops = new ArrayList<ItemStack>();
        List<ItemStack> originalDrops = cir.getReturnValue();

        //Check if the tool used has smelting enchantment
        if(EnchantmentHelper.getLevel(world.getRegistryManager().get(RegistryKeys.ENCHANTMENT).getEntry(ModEnchantments.SMELTING).get(), stack) == 0)
        {
            cir.setReturnValue(originalDrops);
            return;
        }

        //Smelt the mined item if its smeltable and in the smelting drop loot table
        for(ItemStack drops : originalDrops)
        {

            //Check if the item has a recipe for smelting
            var recipe = world.getRecipeManager().getFirstMatch(RecipeType.SMELTING, new SingleStackRecipeInput(drops), world);

            //Smelt the item if it has a recipe and the smelting drop tag
            if(recipe.isPresent() && drops.isIn(ModTags.Items.SMELTING_DROP))
            {
                ItemStack smeltedStack = recipe.get().value().getResult(world.getRegistryManager()).copy();
                smeltedStack.setCount(drops.getCount());
                newDrops.add(smeltedStack);
            }
            else
            {
                newDrops.add(drops);
            }
        }
        cir.setReturnValue(newDrops);
    }

}
