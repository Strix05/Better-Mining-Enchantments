package charble.betterminingenchants.datagen;

import charble.betterminingenchants.enchantment.ModEnchantments;
import charble.betterminingenchants.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.EnchantmentTags;

import java.util.concurrent.CompletableFuture;

public class ModEnchantmentTagProvider extends FabricTagProvider.EnchantmentTagProvider {
    public ModEnchantmentTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {

        getOrCreateTagBuilder(ModTags.Enchantments.BETTER_MINING_EXCLUSIVE)
                .setReplace(false)
                .add(Enchantments.SILK_TOUCH)
                .add(ModEnchantments.SMELTING);

        getOrCreateTagBuilder(EnchantmentTags.TRADEABLE)
                .add(ModEnchantments.OBSIDIAN_EFFICIENCY)
                .add(ModEnchantments.SMELTING);

    }
}
